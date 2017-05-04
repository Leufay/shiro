package com.myjava.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.myjava.dao.PermissionDao;
import com.myjava.entity.Permission;
import com.myjava.entity.User;
@Repository
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements PermissionDao{
	public PermissionDaoImpl() {
		super.setNameSpace("com.myjava.entity.Permission");
	}

	@Override
	public Collection<Permission> getPermissionsByUsername(String username) {
		return super.getSqlSession().selectList(getNameSpace()+".getPermissionsByUsername", username);
	}
	/**
	 * 关联权限-资源
	 */
	@Override
	public void correlationResource(Map<Serializable, Object> param) {
		getSqlSession().insert(getNameSpace()+".correlationResources",param) ;
	}
	
	
}
