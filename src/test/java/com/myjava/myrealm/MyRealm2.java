package com.myjava.myrealm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.myjava.entity.Permission;
import com.myjava.entity.Role;
import com.myjava.entity.User;
import com.myjava.service.UserService;
import com.myjava.service.impl.UserServiceImpl;
/**
 * 通过继承AuthenticatingRealm实现Realm
 * @author Mrlxf
 *
 */
public class MyRealm2 extends AuthorizingRealm{
	//service对数据库进行查找
	private UserService userService = new UserServiceImpl() ;
	/**
	 * 对用户登录的信息和数据库中的用户信息比较
	 * success：则返回用户的认证信息
	 * failed:则抛出异常
	 * @return 验证成功后返回的用户的验证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal() ;
		String password = (String) token.getCredentials() ;
		User user = userService.getByUserName(username);			//从数据库中查找相关信息
		if(user==null){
			throw new UnknownAccountException() ;					//用户名验证失败
		}
		if(!password.equals(user.getPassword())){
			throw new IncorrectCredentialsException() ;				//验证失败
		}
		//验证成功
		return new SimpleAuthenticationInfo(username,password,getName());
	}
	/**
	 * 获取授权信息
	 * 根据用户信息获得用户所拥有的的权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/**
		 * principals：身份，即主体的标识属性，可以是任何东西，如用户名、邮箱等，唯一即可。
		 * 一个主体可以有多个principals，但只有一个Primary principals，一般是用户名/密码/手机号。
		 */
		//获取身份标识
		String username = (String) principals.getPrimaryPrincipal() ;
		//根据用户提供的的用户名去数据库查找
		User user = userService.getByUserName(username);
		//stores roles and permissions as internal attributes.
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo() ;
		//获取所有的角色名称
		Set<Role> roles = user.getRoles() ;
		Set<String> roleNames = new HashSet<>() ;
		for(Role role : roles){
			roleNames.add(role.getRoleName()) ;
		}
		authorizationInfo.setRoles(roleNames);							//将角色信息放到authorizationInfo
		//获取所有的权限信息
		Set<String> permissions = new HashSet<>() ;
//		for(Role role : roles){
//			for(Permission p : role.getPermissions()){
//				permissions.add(p.getPermissionName()) ;
//			}
//		}
		authorizationInfo.setStringPermissions(permissions);			//将权限信息放到authorizationInfo
		
		return authorizationInfo;
	}

}
