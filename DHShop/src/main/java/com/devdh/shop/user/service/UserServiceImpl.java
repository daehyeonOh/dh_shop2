package com.devdh.shop.user.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.devdh.shop.user.domain.UserVO;
import com.devdh.shop.user.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	UserDAO dao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		UserVO user = dao.getUserById(id);
		
		if (user == null) {
			throw new UsernameNotFoundException("해당 아이디 없음.");
		}
		
		return user;
	}

	@Override
	public void insertLoginLog(String id, String ip) {
		
		Map<String, String> map = new HashMap<>();
		map.put("user_id", id);
		map.put("ip", ip);
		
		dao.insertLoginLog(map);
	}

	@Override
	public int selectDisabledType(String id) {
		return dao.selectDisabledType(id);
	}

	@Override
	public int checkId(String id) {
		return dao.checkId(id);
	}

	@Transactional
	@Override
	public ModelAndView signUp(UserVO vo) {
		
		ModelAndView mav = new ModelAndView();
		
		String id = vo.getId();
		int idCheck = dao.checkId(id);
		if (idCheck == 0) {
			vo.setPassword(passwordEncoder.encode(vo.getPassword()));
			int result = dao.insertUser(vo);
			if (result == 1) {
				mav.setViewName("redirect:/");
				mav.addObject("result", "success");
			} else {
				mav.addObject("result", "serverError");
				mav.setViewName("redirect:/");
			}
		} else {
			mav.addObject("result", "idDuplication");
		}
		
		return mav;
	}

	@Override
	public ModelAndView modify(UserVO vo) {
		
		ModelAndView mav = new ModelAndView();
		
		if (vo.getPassword() != null) {
			vo.setPassword(passwordEncoder.encode(vo.getPassword()));
		}
		
		int result = dao.updateUser(vo);
		if (result == 1) {
			mav.addObject("result", "success");
			mav.setViewName("redirect:/");
		} else {
			mav.addObject("result", "fail");
			mav.setViewName("redirect:member/modify");
		}
		
		return mav;
	}

	@Override
	public ModelAndView pwCheckToModifyPage(String password, Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		
		Map<String, String> map = new HashMap<>();
		map.put("id", principal.getName());
		map.put("password", password);
		
		int result = dao.pwCheck(map);
		System.out.println(result);
		if (result == 1) {
			mav.setViewName("member/modify");
			
		} else {
			mav.setViewName("redirect:member/modifyPage");
		}
		
		return mav;
	}

}
