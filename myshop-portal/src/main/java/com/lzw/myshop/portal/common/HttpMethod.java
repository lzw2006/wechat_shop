package com.lzw.myshop.portal.common;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
public enum HttpMethod {

	GET("get"), POST("post");

	private String method;

	HttpMethod(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}
}
