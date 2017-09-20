package com.bigdata2017.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bigdata2017.jblog.service.UserService;
import com.bigdata2017.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		userService.insertMessage(vo);
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login( HttpSession session,
			@RequestParam(value="id", required=true, defaultValue = "") String id,
			@RequestParam(value="password", required=true, defaultValue = "") String password ) {
		UserVo userVo = userService.getUser(id, password);
		if (userVo == null) {
			return "user/login";
		}
		session.setAttribute("authUser", userVo);
		return "";
	}
}
