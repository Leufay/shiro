package com.myjava.resolver;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
/**
 * 自定义的rolePermissionResolver
 * 根据角色role转换成相应的权限Permission集合
 * @author Mrlxf
 *
 */
public class MyRolePermissionResolver implements RolePermissionResolver{

	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		if("role1".equals(roleString)){			//例如如果是role1，则应该有system:user:*权限
			String permissionString = "system:user:*" ;
			return Arrays.asList((Permission)new WildcardPermission(permissionString)) ;
		}
		return null;
	}

	
}
