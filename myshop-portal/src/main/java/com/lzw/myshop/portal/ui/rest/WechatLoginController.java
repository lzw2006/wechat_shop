package com.lzw.myshop.portal.ui.rest;

import com.lzw.myshop.portal.app.dto.WechatLoginReqDto;
import com.lzw.myshop.portal.app.service.WechatLoginApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
@RestController
public class WechatLoginController {

	@Autowired
	private WechatLoginApi wechatLoginApi;

	@PostMapping("/wx/login")
	public ResponseEntity login(@RequestBody WechatLoginReqDto reqDto, HttpSession httpSession) {
		String token = wechatLoginApi.login(reqDto, httpSession);
		return ResponseEntity.ok().body(token);
	}

}
