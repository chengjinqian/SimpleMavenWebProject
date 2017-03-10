package com.force4us.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.force4us.domain.Book;
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

	// 以下代码用来学习@RequestMapping和@RequestParam的用法
	// @RequestMapping(value = "/register", method = RequestMethod.GET)
	// public String registerForm() {
	// logger.info("register GET方法被调用");
	// return "register";
	// }
	//
	// @RequestMapping(value = "/register", method = RequestMethod.POST)
	// public String register(@RequestParam("loginname") String loginname,
	// @RequestParam("username") String username,
	// @RequestParam("password") String password) {
	// logger.info("register POST方法被调用");
	// User user = new User();
	// user.setLoginname(loginname);
	// user.setPassword(password);
	// user.setUsername(username);
	// users.add(user);
	// return "login";
	// }
	//
	// @RequestMapping(value = "/login")
	// public ModelAndView login(@RequestParam("loginname") String loginname,
	// @RequestParam("password") String password,
	// ModelAndView mv) {
	// logger.info("登录名： " + loginname + " 密码" + password);
	// for (User user : users) {
	// if (user.getLoginname().equals(loginname) &&
	// user.getPassword().equals(password)) {
	// mv.addObject("user", user);
	// mv.setViewName("welcome");
	// return mv;
	// }
	// }
	// mv.setViewName("login");
	// return mv;
	// }

	// 以下代码用来验证格式转换的
	// @RequestMapping(value = "/testForm")
	// public String loginForm() {
	// logger.info("---------------");
	// return "testForm";
	// }
	//
	// @RequestMapping(value = "/test", method = RequestMethod.POST)
	// public String test(@ModelAttribute User user, Model model) {
	// logger.info(user);
	// //model.addAttribute("user", user);
	// return "success";
	// }
	@RequestMapping(value = "/login")
	public ModelAndView login(String loginname, String password, ModelAndView mv, HttpSession httpSession) {
		if (loginname != null && loginname.equals("chengjinqian") && password != null && password.equals("123456")) {
			User user = new User();
			user.setLoginname(loginname);
			user.setPassword(password);
			user.setUsername("管理员");
			httpSession.setAttribute("user", user);
			mv.setViewName("redirect:shop");
		} else {
			mv.addObject("message", "登录名出现错误，请重新登陆");
			mv.setViewName("loginForm");
		}
		return mv;
	}

	@RequestMapping("/shop")
	public String shop(Model model) {
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("chengjinqian"));
		model.addAttribute("books", books);
		return "shopForm";
	}

}
