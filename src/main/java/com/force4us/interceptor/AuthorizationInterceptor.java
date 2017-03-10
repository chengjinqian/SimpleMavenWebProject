package com.force4us.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.force4us.domain.User;

public class AuthorizationInterceptor implements HandlerInterceptor {
	private static final String[] IGNORE_URI = { "/loginForm", "/login" };

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("AuthorizationInterceptor preHandle ------>");
		boolean flag = false;
		String servletPath = request.getServletPath();
		for (String string : IGNORE_URI) {
			if (servletPath.contains(string)) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			User user = (User) request.getSession().getAttribute("user");
			if (user == null) {
				System.out.println("AuthorizationInterceptor拦截请求： " + request.getServletPath());
				request.setAttribute("message", "清先登陆网站填写信息");
				request.getRequestDispatcher("login").forward(request, response);
			} else {
				System.out.println("AuthorizationInterceptor放行请求： ");
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("AuthorizationInterceptor postHandle ------>");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("AuthorizationInterceptor afterCompletion ------>");
	}

}
