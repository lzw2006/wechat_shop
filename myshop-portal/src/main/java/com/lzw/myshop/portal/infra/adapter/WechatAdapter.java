package com.lzw.myshop.portal.infra.adapter;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.lzw.myshop.portal.common.HttpMethod;
import com.lzw.myshop.portal.infra.client.MyHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
@Slf4j
@Service
public class WechatAdapter {

	public WechatLoginReturn login(String url) throws IOException {
		String response = MyHttpClient.sendRequest(url, null, HttpMethod.GET, false);
		ReadContext context = JsonPath.parse(response);
		String opendId = context.read("$.openid");
		String sessionKey = context.read("$.session_key");
		String unionId = null;
		try {
			unionId = context.read("$.unionid");
		} catch (Exception ex) {
			log.info("unionid is not existed");
		}
		return new WechatLoginReturn(opendId, sessionKey, unionId);
	}

}
