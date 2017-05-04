package com.myjava.service;

import java.io.Serializable;
import java.util.Collection;

import com.myjava.entity.Role;

public interface RoleService {
	public Collection<Role> findAll() ;
	public Role getById(Serializable id) ;
	public void update(Role role) ;
	public void insert(Role role) ;
	public void delete(Serializable id) ;
	/**
	 * 增加角色-权限之间的联系
	 * @param roleId	角色ID
	 * @param permissionIds	权限ID数组
	 */
	public void correlationPermissions(Serializable roleId , Serializable[] permissionIds);
	/**
	 * 移除角色-权限之间的联系
	 * @param roleId
	 * @param permissionIds
	 */
	public void unCorrelationPermissions(Serializable roleId ,Serializable[] permissionIds);
}
