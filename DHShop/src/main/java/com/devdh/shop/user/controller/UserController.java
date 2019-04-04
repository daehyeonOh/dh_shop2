package com.devdh.shop.user.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.devdh.shop.user.domain.UserVO;
import com.devdh.shop.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Inject
	UserService userService;
	
	@GetMapping("/{id}")
	public int checkId(@PathVariable String id) {
		return userService.checkId(id);
	}

	@PostMapping("/")
	public ModelAndView signUp(@ModelAttribute UserVO vo) {
		return userService.signUp(vo);
	}
	
	@PostMapping("/pwChk")
	public ModelAndView pwCheckToModifyPage(@RequestParam String password, Principal principal) {
		return userService.pwCheckToModifyPage(password, principal);
	}
	
	@PatchMapping("/")
	public ModelAndView modify(@ModelAttribute UserVO vo, Principal principal) {
		vo.setId(principal.getName());
		return userService.modify(vo);
	}
}
