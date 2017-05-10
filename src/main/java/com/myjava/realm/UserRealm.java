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
import com.myjava.entity.User;
import com.myjava.service.PermissionService;
import com.myjava.service.RoleService;
import com.myjava.service.UserService;
import com.myjava.utils.StringUtils;
/**
 * 用于获取用户的验证信息和授权信息
 * @author Mrlxf
 *
 */
public class UserRealm extends AuthorizingRealm{
	@Autowired
	private RoleService roleService  ;
	@Autowired
	private UserService userService ;
	@Autowired
	private PermissionService permissionService ;
	//保存当前用户的授权信息
	private SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
	/**
	 * 获取用户授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
			String username = (String) principals.getPrimaryPrincipal() ;
			/**
			 * 将用户角色信息放入SimplAuthorizationInfo
			 */
			this.addRoles(username);
			/**
			 * 将用户权限信息放入SimpleAuthorizationInfo中
			 */
			this.addPermissions(username);

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
	
	/**
	 * 设置角色信息
	 * @param username 当前用户用户名
	 */
	public void addRoles(String username){
		//根据用户名查找其所有的角色
		List<String> roleNames = roleService.getRolesByUsername(username) ;
		info.addRoles(roleNames);
	}
	
	/**
	 * 设置权限信息
	 * @param username 当前用户用户名
	 */
	public void addPermissions(String username){
		Set<String> stringPermissions = new HashSet<>() ;
		//根据用户名在数据库中查找对应的权限信息
		List<Permission> permissions = (List<Permission>) permissionService.findPermissionsByUsername(username) ;
		//遍历permission
		if(permissions.size()!=0){
			for(Permission p : permissions){
				String permStr = p.getPermNo() ;
				//如果permission权限标识有逗号则进行分割再放到set集合中
				if(permStr.contains(",")){
					Set<String> permsSet = StringUtils.convertStringToSet(permStr, ",") ;
					stringPermissions.addAll(permsSet);
				}else{
					stringPermissions.add(permStr) ;
				}
			}
			info.setStringPermissions(stringPermissions);
		}
	}
}
