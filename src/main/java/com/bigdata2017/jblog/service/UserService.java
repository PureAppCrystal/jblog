package com.bigdata2017.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigdata2017.jblog.repository.BlogDao;
import com.bigdata2017.jblog.repository.UserDao;
import com.bigdata2017.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	public boolean existUser( String id ) {
		UserVo userVo = userDao.get(id);
		return userVo != null;
	}

	public boolean insertMessage(UserVo vo) {
		
		boolean result = false;
		
		//1. 회원정보 저장
		result = userDao.insert(vo) == 1;
		if (result == false ) {
			return false;
		}
		
		//2. 블로그 생성
		result = blogDao.insert(vo) == 1;
		if (result == false ) {
			return false;
		}
		
		
		//3. 기본카테고리 생성
		
		return true;
	}
	
	public UserVo getUser(String id, String password) {
		 
		return userDao.getUser(id, password);
	}
	
	
}
