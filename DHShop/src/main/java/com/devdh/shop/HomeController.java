package com.devdh.shop;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/signIn")
	public String login(Principal principal) {
		if (principal == null) {
			return "sign/signIn";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/signUp")
	public String signUp(Principal principal) {
		if (principal == null) {
			return "sign/signUp";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/member/modifyPage")
	public String modify() {
		return "member/pwChk";
	}
	
	@RequestMapping("/access_denied_page")
	public String accessDeniedPage() {
		return "error/access_denied_page";
	}
	
}
