package com.myjava.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjava.dao.UserDao;
import com.myjava.entity.Permission;
import com.myjava.entity.Role;
import com.myjava.entity.User;
import com.myjava.service.UserService;
import com.myjava.utils.PasswordHelper;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao ;
	@Override
	public Collection<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User getById(Serializable id) {
		return userDao.get(id);
	}

	@Override
	public User getByUserName(String username) {
		return userDao.getByUserName(username);
	}

	@Override
	public void insert(User user) {
		//密码加密
		new PasswordHelper().encryptPassword(user); 
		userDao.save(user);
	}

	@Override
	public void correlationRoles(Serializable userId, Serializable[] roleIds) {
		Map<Serializable,Object> param = new HashMap<>() ;
		param.put("userId",userId);
		param.put("roleIds",roleIds);
		userDao.collerationRoles(param);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	
	@Override
	public Collection<Role> findRolesByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Serializable id) {
		userDao.delete(id);
	}

	@Override
	public User getRolesByUsername(String username) {
		return userDao.getRolesByUsername(username);
	}

}
