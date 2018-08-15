package com.lzw.myshop.portal.app.service;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
public class WechatLoginFailException extends RuntimeException {
	public WechatLoginFailException() {
		super();
	}

	public WechatLoginFailException(String message) {
		super(message);
	}

	public WechatLoginFailException(String message, Throwable cause) {
		super(message, cause);
	}
}
