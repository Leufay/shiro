package com.myjava.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

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
	
	/**
	 * 根据用户名称查找其所有的角色
	 * @param username
	 * @return	用户所拥有的的角色名称的List
	 */
	public List<String> getRolesByUsername(String username) ;
}
