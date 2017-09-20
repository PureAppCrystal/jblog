package com.bigdata2017.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bigdata2017.jblog.service.BlogService;
import com.bigdata2017.jblog.vo.BlogVo;

@Controller
@RequestMapping("/{id}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping("")
	public String index(
			@PathVariable String id,
			Model model) {
		
		BlogVo blogVo = blogService.getBlog( id );
		
		System.out.println(blogVo);
		model.addAttribute("title", blogVo.getTitle());
		model.addAttribute("logo", blogVo.getLogo());
		
		//CategoryVo categoryVo = categoryService.getCategory();
		//category
		
		//최신글
		//마지막 글을 출력 
		return "blog/blog-main";
	}
	
	//카테고리 번호를 받으면- 그 카테고리의 최신글을 출력.하고 
	//카테고리의 포스팅이 많으면 페이지컨트롤(나중에)
}
