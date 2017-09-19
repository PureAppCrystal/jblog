package com.bigdata2017.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigdata2017.jblog.repository.UserDao;
import com.bigdata2017.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public boolean existUser( String id ) {
		UserVo userVo = userDao.get(id);
		return userVo != null;
	}

	public boolean insertMessage(UserVo vo) {
		int result = userDao.insert(vo);
		return result == 1;
	}
}
