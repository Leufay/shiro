package com.myjava.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.myjava.entity.Permission;
import com.myjava.entity.Role;
import com.myjava.entity.User;
import com.myjava.service.PermissionService;
import com.myjava.service.UserService;
import com.myjava.utils.StringUtils;
/**
 * 用于获取用户的验证信息和授权信息
 * @author Mrlxf
 *
 */
public class UserRealm extends AuthorizingRealm{
	@Autowired
	private UserService userService  ;
	@Autowired
	private PermissionService permissionService ;
	/**
	 * 获取用户授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
		String username = (String) principals.getPrimaryPrincipal() ;
		Set<String> stringPermissions = new HashSet<>() ;
		List<Permission> permissions = (List<Permission>) permissionService.findPermissionsByUsername(username) ;
		User u = userService.getRolesByUsername(username) ;
		Set<Role> roles = u.getRoles() ;
		Set<String> roleNames = new HashSet<>() ;
		if(roles.size()!=0){
			for (Role role : roles) {
				roleNames.add(role.getRoleName()) ;
			}
		}
		if(permissions.size()!=0){
			for(Permission p : permissions){
				String permStr = p.getPermNo() ;
				if(permStr.contains(",")){
					Set<String> permsSet = StringUtils.convertStringToSet(permStr, ",") ;
					stringPermissions.addAll(permsSet);
				}else{
					stringPermissions.add(permStr) ;
				}
			}
		}
		info.addRoles(roleNames);
		info.setStringPermissions(stringPermissions);
		return info ;
	}
	/**
	 * 获取用户身份信息
	 * CredentialsMatcher会进行密码验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//用户输入的用户名和密码
		String username = (String) token.getPrincipal() ;
		//数据库查找的user
		User accountUser = userService.getByUserName(username) ;
		//验证
		if(accountUser == null){
			throw new UnknownAccountException() ;
		}
		//会调用credentialMatcher进行密码验证
		return new SimpleAuthenticationInfo(accountUser.getUsername(),accountUser.getPassword(),new SimpleByteSource(accountUser.getSalt()),getName());
	}
	
}
