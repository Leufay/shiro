package com.myjava.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.myjava.entity.User;
import com.myjava.service.UserService;
import com.myjava.utils.GlobalAttributes;
/**
 * 用来判断访问当前地址时是否已经经过认证
 * <br>未认证则会跳转到登录页面
 * <br>经过认证之后会将用户信息存入session中
 */
public class UserSessionFilter extends PathMatchingFilter{
	@Autowired
	private UserService userService ;
	/**
	 * 登录页面
	 */
	private String loginUrl ="/login/toLogin.action" ;
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		/**
		 * 判断是否已经认证
		 */
		if (subject.isAuthenticated() || subject.isRemembered()) {
			User user = userService.getByUserName(subject.getPrincipal() + "");
			//将用户信息存入session中
			subject.getSession().setAttribute(GlobalAttributes.GLOBAL_USER, user);
			return true ;
		}else{
			this.redirectToLogin(request, response);
			return false ;
		}
	}
	
	/**
	 * 认证失败重定向到登录页面
	 * @throws IOException 
	 */
	public void redirectToLogin(ServletRequest request , ServletResponse response) throws IOException{
		/**
		 * 保存当前请求
		 * 登录成功后会跳转到之前访问的页面
		 */
		WebUtils.saveRequest(request);
		request.setAttribute("msg", "请登录");
		WebUtils.issueRedirect(request, response, this.loginUrl);
	}

}
