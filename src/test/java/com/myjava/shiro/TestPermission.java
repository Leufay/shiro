package com.myjava.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 判断某个用户有哪些权限
 * @author Mrlxf
 *
 */
public class TestPermission {
	@Test
	public void testPermission() throws Exception {
		Subject sub = IniSecurity.initSecurity("classpath:shiro-permission.ini") ;
		//SecurityManager securityManager = SecurityUtils.getSecurityManager() ;
		
		UsernamePasswordToken token = new UsernamePasswordToken("admin","lxf123") ;
		sub.login(token);
		boolean isPer = sub.isPermitted("system:buyer:delete") ;
		System.out.println(isPer);
		sub.logout();
	}
}
