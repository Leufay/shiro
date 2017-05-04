package com.myjava.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestAuthorization {
	@Test
	public void testAuthorization() throws Exception {
		IniSecurity.initSecurity("shiro-authorizer.ini") ;
		Subject sub = SecurityUtils.getSubject() ;
		UsernamePasswordToken token = new UsernamePasswordToken("admin" ,"lxf123") ;
		try {
			sub.login(token);
		} catch (AccountException e) {
			System.out.println("验证失败.....");
		}
		sub.logout();
	}
}
