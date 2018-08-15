package com.lzw.myshop.portal.infra.client;

import com.lzw.myshop.portal.common.HttpMethod;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.util.Assert;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.Collections;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
public class MyHttpClient {

	private static final CloseableHttpClient httpClient;

	private static final CloseableHttpClient retryHttpClient;

	private static final Integer ABNORMAL_CODE = 300;

	private static final String UTF8 = "UTF-8";

	private static final Integer MAX_RETRY = 3;

	private static final Integer KEEP_ALIVE_TIME = 60 * 1000;

	static {
		httpClient = HttpClients.custom().setConnectionManager(createPoolingHttpClientConnectionManager())
				.setKeepAliveStrategy(createKeepAliveStrategy()).build();
		retryHttpClient = HttpClients.custom().setConnectionManager(createPoolingHttpClientConnectionManager())
				.setKeepAliveStrategy(createKeepAliveStrategy()).setRetryHandler(createRequestRetryHandler()).build();
	}

	public static String sendRequest(String url, String data, HttpMethod httpMethod, boolean needRetry)
			throws IOException {

		Assert.notEmpty(Collections.singleton(url), "url should not be empty");
		Assert.notEmpty(Collections.singleton(httpMethod), "httpMethod should not be empty");

		ResponseHandler<String> rh = new ResponseHandler<String>() {
			@Override
			public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				StatusLine statusLine = response.getStatusLine();
				HttpEntity entity = response.getEntity();
				if (statusLine.getStatusCode() >= ABNORMAL_CODE) {
					throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
				}
				if (entity == null) {
					throw new ClientProtocolException("Response contains no content");
				}
				InputStream inputStream = entity.getContent();
				int contentLength = (int) entity.getContentLength();
				byte[] tmp = new byte[contentLength];
				while (inputStream.read(tmp) != -1) {}
				return new String(tmp, UTF8);
			}
		};

		String response;
		if (httpMethod.equals(HttpMethod.GET)) {
			HttpGet httpGet = new HttpGet(url);
			response = needRetry ? retryHttpClient.execute(httpGet, rh) : httpClient.execute(httpGet, rh);
		} else if (httpMethod.equals(HttpMethod.POST)) {
			Assert.notEmpty(Collections.singleton(data), "data should not be empty when http method is post");
			HttpPost httpPost = new HttpPost(url);
			StringEntity stringEntity = new StringEntity(data);
			httpPost.setEntity(stringEntity);
			response = needRetry ? retryHttpClient.execute(httpPost, rh) : httpClient.execute(httpPost, rh);
		} else {
			throw new UnsupportedHttpMethodException("unsupport" + httpMethod.getMethod() + "method");
		}
		return response;
	}

	private static HttpRequestRetryHandler createRequestRetryHandler() {
		return new HttpRequestRetryHandler() {

			@Override
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= MAX_RETRY) {
					// Do not retry if over max retry count
					return false;
				}
				if (exception instanceof InterruptedIOException) {
					// Timeout
					return false;
				}
				if (exception instanceof UnknownHostException) {
					// Unknown host
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {
					// Connection refused
					return false;
				}
				if (exception instanceof SSLException) {
					// SSL handshake exception
					return false;
				}
				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if (idempotent) {
					// Retry if the sendRequest is considered idempotent
					return true;
				}
				return false;
			}
		};
	}

	public static PoolingHttpClientConnectionManager createPoolingHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);
		return cm;
	}

	public static ConnectionKeepAliveStrategy createKeepAliveStrategy() {
		return new ConnectionKeepAliveStrategy() {
			@Override
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				HeaderElementIterator it = new BasicHeaderElementIterator(
						response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					if (value != null && param.equalsIgnoreCase("timeout")) {
						return Long.parseLong(value) * 1000;
					}
				}
				return KEEP_ALIVE_TIME;
			}
		};
	}
}
