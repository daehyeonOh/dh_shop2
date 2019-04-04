package com.devdh.shop.user.persistence;

import java.util.Map;

import com.devdh.shop.user.domain.UserVO;

public interface UserDAO {

	public UserVO getUserById(String id);
	
	public void insertLoginLog(Map<String, String> map);
	
	public int selectDisabledType(String id);
	
	public int checkId(String id);
	
	public int insertUser(UserVO vo);
	
	public int updateUser(UserVO vo);
	
	public int pwCheck(Map<String, String> map);
}
