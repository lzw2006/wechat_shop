package com.lzw.myshop.portal.infra.adapter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class WechatProperty {

	private String appId;
	private String appSecret;
	private String wechatLoginUrl;

}
