package com.myjava.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestRealm {
	@Test
	public void testRealm() throws Exception {
		Subject sub = IniSecurity.initSecurity("classpath:shiro-realm.ini") ;
		UsernamePasswordToken token = new UsernamePasswordToken("admin" , "12") ;
		try{
			sub.login(token);
		}catch(AuthenticationException e){
			System.out.println("登录失败");
		}
		sub.logout(); 
	}
}
