package com.bigdata2017.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bigdata2017.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo vo) {
		
		int result = sqlSession.insert("user.insert", vo);
		return result;                                       
	}

	public UserVo get(String id) {
		return sqlSession.selectOne("user.getById", id);
	}
}
