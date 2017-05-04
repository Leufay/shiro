package com.myjava.myrealm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.myjava.entity.User;
import com.myjava.service.UserService;
import com.myjava.service.impl.UserServiceImpl;
/**
 * 密码加密验证
 * @author Mrlxf
 *
 */
public class MyRealmEncipher extends AuthorizingRealm{
	private UserService userService = new UserServiceImpl() ;				//这里应该是注入
	private PasswordService passwordService ; 								//实际项目中应该注入
	/**
	 * 获取用户的权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = principals.getPrimaryPrincipal().toString() ;
		userService.getByUserName(username) ;
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo() ;
		Set<String> roleNames = new HashSet<>() ;
		authorizationInfo.setRoles(roleNames);
		Set<String> permissionNames = new HashSet<>() ;
		authorizationInfo.setStringPermissions(permissionNames);
		return authorizationInfo;
	}
	/**
	 * 验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString() ;				//用户输入的用户名
		String password = token.getCredentials().toString() ;			//用户输入的密码
		User user = userService.getByUserName(username) ;				//数据库查找
		if(user==null){
			throw new UnknownAccountException() ;
		}
		//将用户输入的密码加密
		String enPassword = passwordService.encryptPassword(password) ;
		
		if(!user.getPassword().equals(enPassword)){
			throw new IncorrectCredentialsException() ;
		}
		return new SimpleAuthenticationInfo(username , password , getName());
	}
	
}
