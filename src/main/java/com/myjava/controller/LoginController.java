package com.myjava.controller;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myjava.entity.User;
import com.myjava.service.PermissionService;
import com.myjava.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService ;
	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login/loginUI";
	}
	
	/**
	 * 登录
	 * @param user
	 * @param mm
	 * @param session
	 * @param response
	 * @param attrs
	 * @return
	 */
	@RequestMapping("/login")
	public String login(User user, ModelMap mm , HttpSession session,HttpServletResponse response,RedirectAttributes attrs ) {
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername() , user.getPassword()) ;
		//获取主体
		Subject subject = SecurityUtils.getSubject() ;
		//subject.login(token);
		try {
			//登录
			subject.login(token);
		} catch (AuthenticationException e) {
			System.out.println("---------------用户名或者密码错误-------------------");
			attrs.addFlashAttribute("msg","用户名或密码错误") ;
			e.printStackTrace();
			return "redirect:toLogin.action" ;
		}
		subject.getSession().setAttribute("user", userService.getByUserName(user.getUsername()));
		return "redirect:index.action";
	}
	
	@RequestMapping("/index.action")
	public String toIndex(){
		return "index";
	}
	
	@RequestMapping("/toRegister")
	public String toRegister(){
		return "registerUI";
	}
	
	@RequestMapping("/register")
	public String register(User user){
		userService.insert(user);
		return "redirect:toLogin.action" ;
	}
	
	@RequestMapping("/logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject() ;
		subject.logout(); 
		return "redirect:toLogin.action";
	}
}
