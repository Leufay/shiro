package com.myjava.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
/**
 * if 当前用户如果是通过rememberMe登录则会被踢回登录页面
 * <br> 登录成功后会返回到之前访问的页面
 * @author Mrlxf
 *
 */
public class MyAuthenticationFilter extends AccessControlFilter{

	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		//if user is authenticated return true otherwise return false
		return getSubject(request, response).isAuthenticated() ;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		saveRequestAndRedirectToLogin(request, response);
		return false;
	}
	

}
