package com.myjava.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import com.myjava.entity.Permission;
import com.myjava.entity.User;

public interface PermissionDao extends BaseDao<Permission>{
	public Collection<Permission> getPermissionsByUsername(String username) ;
	public void correlationResource(Map<Serializable,Object> param) ;
}	
