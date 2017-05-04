package com.myjava.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
public class TestHelloworld {
	@Test
	public void testName() throws Exception {
		//ini初始化factory
		Factory<org.apache.shiro.mgt.SecurityManager> factory =  new IniSecurityManagerFactory("classpath:user.ini") ;
		//获取SecurityManager实例
		SecurityManager manager = (SecurityManager) factory.getInstance() ;
		//将securityManager绑定给SecurityUtils
		SecurityUtils.setSecurityManager(manager);
		//获取Subject
		Subject sub = SecurityUtils.getSubject() ;
		//创建简单的uusernamepassword的token
		/**
		 * 第一步：收集用户登录信息
		 */
		UsernamePasswordToken token = new UsernamePasswordToken("admin","lxf") ;
		try{
			/**
			 * 第二步：登录验证
			 */
			//登录，会委托给securityManager的login方法
			sub.login(token);
		}catch(UnknownAccountException e){
			System.out.println("用户名错误");
		}catch(IncorrectCredentialsException e){
			System.out.println("密码错误");
		}
		sub.logout();
	}
}
