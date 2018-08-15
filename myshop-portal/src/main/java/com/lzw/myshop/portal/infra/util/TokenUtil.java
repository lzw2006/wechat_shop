package com.lzw.myshop.portal.infra.util;

import java.util.UUID;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
public class TokenUtil {

	public static String createToken() {
		return UUID.randomUUID().toString();
	}

}
