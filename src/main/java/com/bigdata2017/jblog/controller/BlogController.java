package com.bigdata2017.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bigdata2017.jblog.service.BlogService;
import com.bigdata2017.jblog.service.FileUploadService;
import com.bigdata2017.jblog.vo.BlogVo;

@Controller
@RequestMapping("/{id}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired 
	private FileUploadService fileUploadService;
	
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
		
		//카테고리 번호를 받으면- 그 카테고리의 최신글을 출력.하고 
				//카테고리의 포스팅이 많으면 페이지컨트롤(나중에)
	}
	
	@RequestMapping({"/admin","/admin/basic"})
	public String adminBasic(
			@PathVariable String id,
			Model model) {
		
		//여기서 기본적으로 블로그 이름과 로고를 보내줘야 한다.
		BlogVo blogVo = blogService.getBlog( id );
		
		model.addAttribute("title", blogVo.getTitle());
		model.addAttribute("logo", blogVo.getLogo());
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/admin/modify", method=RequestMethod.POST)
	public String modify(
//			@PathVariable String id,
			//@RequestParam("title") String name
			@ModelAttribute BlogVo vo,
			@RequestParam("logo-file") MultipartFile multipartFile,
			Model model
			) {
		
		//이미지 url 가져오기 
		String url = fileUploadService.restore(multipartFile);
		vo.setLogo(url);
		
		//title 변경하는 곳 
		boolean result = blogService.updateBlog(vo);
		
		System.out.println("id:" +vo.getId());
		System.out.println("title : "+vo.getTitle());

		
		model.addAttribute("title", vo.getTitle());
		model.addAttribute("logo", vo.getLogo());
		
		return "blog/blog-admin-basic";
	}
	
	
	@RequestMapping("/admin/category")
	public String category ( @PathVariable String id) {
		System.out.println("!");
		return "blog/blog-admin-category";
	}
	
	
	@RequestMapping("/admin/write")
	public String write ( @PathVariable String id) {
		System.out.println("2");
		return "blog/blog-admin-write";
	}
			
	
	
	
	
}
