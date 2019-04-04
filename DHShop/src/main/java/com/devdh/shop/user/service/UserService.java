package com.devdh.shop.user.service;

import java.security.Principal;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.ModelAndView;

import com.devdh.shop.user.domain.UserVO;

public interface UserService extends UserDetailsService{

	public void insertLoginLog(String id, String ip);
	
	public int selectDisabledType(String id);
	
	public int checkId(String id);
	
	public ModelAndView signUp(UserVO vo);
	
	public ModelAndView modify(UserVO vo);
	
	public ModelAndView pwCheckToModifyPage(String password, Principal principal);
	
}
