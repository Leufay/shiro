package com.myjava.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.myjava.entity.Permission;
import com.myjava.entity.User;

public interface PermissionService {
	
	public List<Permission> findAll() ;
	
	public Permission get(Serializable permId) ;
	
	/**
	 * 增加权限
	 * @param permission
	 */
	public void insert(Permission permission) ;
	/**
	 * 删除权限
	 * @param id
	 */
	public void delete(Serializable id);
	
	/**
	 * 根据用户名查找所有的权限信息
	 * @param username
	 * @return
	 */
	public Collection<Permission> findPermissionsByUsername(String username) ;
	
	public void update(Permission perm) ;
	
	public void setResources(String[] permUrls, Long permId) ;
	

}
