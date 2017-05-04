package com.myjava.myrealm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
/**
 * 自定义的Realm实现
 * @author Mrlxf
 *
 */
public class MyRealm1 implements Realm{

	@Override
	public String getName() {
		return "MyRealm1";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		//仅支持UsernamePasswordToken
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取用户名
		String username = (String) token.getPrincipal() ;
		//获取密码
		String password = (String) token.getCredentials() ;
		if(!"admin".equals(username)){
			throw new UnknownAccountException() ;
		}
		if("lxf123".equals(password)){
			throw new IncorrectCredentialsException() ;
		}
		return new SimpleAuthenticationInfo(username , password,getName());
	}

}
