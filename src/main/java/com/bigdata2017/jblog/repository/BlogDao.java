package com.bigdata2017.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bigdata2017.jblog.vo.BlogVo;
import com.bigdata2017.jblog.vo.UserVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo vo) {
		int count = sqlSession.insert("blog.insert", vo );
		return count;
	}
	
	public BlogVo getBlog(String id) {
		BlogVo blogVo = sqlSession.selectOne("blog.getblog", id );
		return blogVo;
	}
	
	public int update(BlogVo vo) {
		return sqlSession.update("blog.update", vo);
		
	}
}
