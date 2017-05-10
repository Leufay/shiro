package com.myjava.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.myjava.dao.UserDao;
import com.myjava.entity.User;
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
	public UserDaoImpl(){
		super.setNameSpace("com.myjava.entity.User");
	}
	/**
	 * 根据用户名查找
	 */
	@Override
	public User getByUserName(String username) {
		return getSqlSession().selectOne(getNameSpace()+".getByUserName",username);
	}
	@Override
	public void collerationRoles(Map<Serializable,Object> param) {
		//先解除所有角色关联
		this.uncollerationRoles(param.get("userId")+"");
		getSqlSession().update(getNameSpace()+".collerationRoles", param) ;
	}
	
	/**
	 * 解除用户--角色关联
	 */
	@Override
	public void uncollerationRoles(Serializable userId) {
		getSqlSession().delete(getNameSpace()+".deleteRoles", userId+"") ;
	}
	/**
	 * 重写baseDao中的delete方法
	 */
	@Override
	public void delete(Serializable id) {
		//先解除外键关联
		this.uncollerationRoles(id);
		//删除用户
		super.delete(id);
		
	}
}
