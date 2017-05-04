package com.myjava.resolver;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
/**
 * 自定义的permissionResolver
 * @author Mrlxf
 *
 */
public class MyPermissionResolver implements PermissionResolver{
	/**
	 * 将权限的字符串转换成permission实例
	 */
	@Override
	public Permission resolvePermission(String permissionString) {
		return new WildcardPermission(permissionString);
	}

}
