package com.bigdata2017.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigdata2017.jblog.repository.BlogDao;
import com.bigdata2017.jblog.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	public BlogVo getBlog(String id ) {
		BlogVo vo =blogDao.getBlog(id); 
		return vo;
	}
}
