package com.myjava.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.myjava.entity.Role;
import com.myjava.entity.User;

public interface UserService {
	/**
	 * 查找全部用户
	 * @return
	 */
	public Collection<User> findAll() ;		
	/**
	 * 根据用户ID查找用户
	 * @param id
	 * @return
	 */
	public User getById(Serializable id) ;			
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public User getByUserName(String username);		
	/**
	 * 增加用户
	 * @param user
	 */
	public void insert(User user) ;
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void update(User user) ;
	/**	
	 * 建立用户角色之间的联系
	 * @param uerId
	 * @param rolesIs
	 */
	public void correlationRoles(Serializable uerId , Serializable[] roleIds);
	
	/**
	 * 根据用户名查找所有的角色信息
	 * @param username
	 * @return
	 */
	public Collection<Role> findRolesByUsername(String username) ;
	
	public void deleteById(Serializable id) ;
	
}	
