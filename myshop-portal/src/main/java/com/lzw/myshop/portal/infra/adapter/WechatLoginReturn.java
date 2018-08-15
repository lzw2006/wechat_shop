package com.lzw.myshop.portal.infra.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
@Data
@AllArgsConstructor
public class WechatLoginReturn implements Serializable {

	private String openId;
	private String sessionKey;
	private String unionId;

}
