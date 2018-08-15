package com.lzw.myshop.portal.infra.client;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
public class UnsupportedHttpMethodException extends RuntimeException {
	public UnsupportedHttpMethodException() {
		super();
	}

	public UnsupportedHttpMethodException(String message) {
		super(message);
	}
}
