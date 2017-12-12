package com.bigdata2017.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bigdata2017.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 1. hanlder 종류 확인
		if (handler instanceof HandlerMethod == false) {
			return true;
		}

		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. Method @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Method에 @Auth 가 없다면,
		// Class(Type)에 붙에 있는 @Auth 받아오기
		if (auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}

		// 5. @Auth가 안붙어 있는 경우
		if (auth == null) {
			return true;
		}

		// 5. @Auth가 붙어 있기 인증여부 체크
		HttpSession session = request.getSession();

		if (session == null) { // 인증이 안되어 있음
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) { // 인증이 안되어 있음
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 로그인이 정상적으로 되어있는경우 이지점까지 넘어온다.

		// JBlog 주소의 주인만 통과된다. ( 주소의 주인만 관리자 )

		// * 구현해야될 내용 */
		// 현재 주소를 가져온다.
		// request.getContextPath()
		String url = request.getRequestURL().toString();
		System.out.println(url);

		String[] array;
		array = url.split("/");

		System.out.println(array[4]);
		System.out.println(authUser.getId());
		String urlId = array[4];

		if (urlId.equals(authUser.getId())) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/" + array[4]);
			return false;
		}
	}
}
