package com.myjava.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.myjava.entity.User;
/**
 * 未登录进行拦截
 * @author Mrlxf
 *
 */
public class PermissionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession() ;
		//判断用户是否登录
		User user = (User) session.getAttribute("user");
		if(user==null){
			System.out.println("----------------拦截器拦截------------------------");
			request.getRequestDispatcher("/").forward(request, response);
			//response.sendRedirect(request.getContextPath()+"/login/toLogin.action");					//未登录重定向到登录页面
			return false ;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
