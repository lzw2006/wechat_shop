package com.lzw.myshop.portal.app.service;

import com.lzw.myshop.portal.app.dto.WechatLoginReqDto;
import com.lzw.myshop.portal.infra.adapter.WechatAdapter;
import com.lzw.myshop.portal.infra.adapter.WechatLoginReturn;
import com.lzw.myshop.portal.infra.adapter.WechatProperty;
import com.lzw.myshop.portal.infra.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
@Slf4j
@Service
public class WechatLoginApi {

	@Autowired
	private WechatProperty wechatProperty;

	@Autowired
	private WechatAdapter wechatAdapter;

	public String login(WechatLoginReqDto loginDto, HttpSession httpSession) {
		try {
			String loginUrl = getUrl(loginDto.getCode());
			WechatLoginReturn wechatLoginReturn = wechatAdapter.login(loginUrl);
			String token = TokenUtil.createToken();
			saveSession(token, wechatLoginReturn, httpSession);
			return token;
		} catch (IOException e) {
			throw new WechatLoginFailException("wechat login fail : {}", e);
		}
	}

	private String getUrl(String code) {
		return String
				.format(wechatProperty.getWechatLoginUrl(), wechatProperty.getAppId(), wechatProperty.getAppSecret(),
						code);
	}

	private void saveSession(String token, WechatLoginReturn wechatLoginReturn, HttpSession httpSession) {
		httpSession.setAttribute(token, wechatLoginReturn);
	}
}
