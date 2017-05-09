package com.myjava.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myjava.entity.User;
import com.myjava.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userService;
	
	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin(RedirectAttributes attrs) {
		attrs.addFlashAttribute("msg","验证失败") ;
		return "login/loginUI";
	}
	
	/**
	 * 登录
	 * <br>已经委托给FormAuthenticationFilter处理
	 * 此方法当登录认证失败会执行
	 */
//	@RequestMapping("/login")
//	public String login(User user, ModelMap mm , HttpSession session,HttpServletResponse response,RedirectAttributes attrs ) {
////		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername() , user.getPassword()) ;
////		token.setRememberMe(true);
////		//获取主体
////		Subject subject = SecurityUtils.getSubject() ;
////		//subject.login(token);
////		try {
////			//登录
////			subject.login(token);
////		} catch (AuthenticationException e) {
////			System.out.println("---------------用户名或者密码错误-------------------");
////			attrs.addFlashAttribute("msg","用户名或密码错误") ;
////			e.printStackTrace();
////			return "redirect:toLogin.action" ;
////		}
////		subject.getSession().setAttribute("user", userService.getByUserName(user.getUsername()));
//		return "redirect:index.action";
//	}
	
	@RequestMapping("/index.action")
	public String toIndex(HttpServletRequest request){
		//TODO 怎么获取前端传来的username
		String username = request.getParameter("username") ;
		System.out.println(username);
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
