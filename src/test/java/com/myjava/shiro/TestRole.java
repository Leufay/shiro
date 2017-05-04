package com.myjava.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
/**
 * 判断用户有哪些角色
 * @author Mrlxf
 *
 */
public class TestRole {
	@Test
	public void testRole() {
		Subject sub = IniSecurity.initSecurity("classpath:shiro-role.ini") ;
		UsernamePasswordToken token = new UsernamePasswordToken("admin" , "lxf123") ;
		sub.login(token);
		//hasRole在没有满足的时候返回false
		boolean hasRole = sub.hasRole("role1") ;
		//checkRole在没有满足的时候抛出异常
		sub.checkRole("role3") ;
		System.out.println(hasRole);
	}
}
