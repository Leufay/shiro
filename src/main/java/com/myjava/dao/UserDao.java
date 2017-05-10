package com.myjava.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.myjava.entity.User;

public interface UserDao extends BaseDao<User>{
	public User getByUserName(String username) ;
	public void uncollerationRoles(Serializable userId);
	public void collerationRoles(Map<Serializable,Object> param);
	
}
