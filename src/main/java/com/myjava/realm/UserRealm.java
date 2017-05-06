package com.myjava.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
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
		Subject subject = SecurityUtils.getSubject() ;
		Session session = subject.getSession() ;
		SimpleAuthorizationInfo info = null ;
		//判断当前shiro session 中有没有当前subject的授权信息
		//如果没有则去数据库查询，并且将查询到的授权信息放入到shiro session中，否则直接从session中取
		if(session.getAttribute("authorizationInfo")==null){
			System.out.println("-----------------当前session无授权信息，查询数据库---------------------");
			info = new SimpleAuthorizationInfo() ;
			//获取用户名
			String username = (String) principals.getPrimaryPrincipal() ;
			Set<String> stringPermissions = new HashSet<>() ;
			//根据用户名在数据库中查找对应的权限信息
			List<Permission> permissions = (List<Permission>) permissionService.findPermissionsByUsername(username) ;
			User u = userService.getRolesByUsername(username) ;
			Set<Role> roles = u.getRoles() ;
			Set<String> roleNames = new HashSet<>() ;
			//判断角色
			if(roles.size()!=0){
				for (Role role : roles) {
					roleNames.add(role.getRoleName()) ;
				}
			}
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
			}
			info.addRoles(roleNames);
			info.setStringPermissions(stringPermissions);
			//将用户权限信息放入shiro session中
			//TODO 放入session中后怎么及时更新
			session.setAttribute("authorizationInfo", info);
		}else{
			info = (SimpleAuthorizationInfo) session.getAttribute("authorizationInfo") ;
		}
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
