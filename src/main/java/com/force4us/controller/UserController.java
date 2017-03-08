package com.force4us.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.force4us.domain.User;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static List<User> users = null;

	public UserController() {
		super();
		users = new ArrayList<User>();
	}

	private static final Log logger = LogFactory.getLog(UserController.class);

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm() {
		logger.info("register GET方法被调用");
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestParam("loginname") String loginname, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		logger.info("register POST方法被调用");
		User user = new User();
		user.setLoginname(loginname);
		user.setPassword(password);
		user.setUsername(username);
		users.add(user);
		return "login";
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam("loginname") String loginname, @RequestParam("password") String password,
			ModelAndView mv) {
		logger.info("登录名： " + loginname + " 密码" + password);
		for (User user : users) {
			if (user.getLoginname().equals(loginname) && user.getPassword().equals(password)) {
				mv.addObject("user", user);
				mv.setViewName("welcome");
				return mv;
			}
		}
		mv.setViewName("login");
		return mv;
	}

}
