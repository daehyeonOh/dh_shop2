package com.devdh.shop.user.persistence;

import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.devdh.shop.user.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Inject
	SqlSession sqlSession;

	@Override
	public UserVO getUserById(String id) {
		return sqlSession.selectOne("user.selectUserById", id);
	}

	@Override
	public void insertLoginLog(Map<String, String> map) {
		sqlSession.insert("user.insertLoginLog", map);
	}

	@Override
	public int selectDisabledType(String id) {
		return sqlSession.selectOne("user.selectDisabledType", id);
	}

	@Override
	public int checkId(String id) {
		return sqlSession.selectOne("user.checkId", id);
	}

	@Override
	public int insertUser(UserVO vo) {
		return sqlSession.insert("user.insertUser", vo);
	}

	@Override
	public int updateUser(UserVO vo) {
		return sqlSession.update("user.updateUser", vo);
	}

	@Override
	public int pwCheck(Map<String, String> map) {
		return sqlSession.selectOne("user.checkPw", map);
	}

	
	
}
