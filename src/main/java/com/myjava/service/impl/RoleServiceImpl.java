package com.myjava.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjava.dao.RoleDao;
import com.myjava.entity.Role;
import com.myjava.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDao roleDao ;
	@Override
	public void insert(Role role) {
		roleDao.save(role);
	}

	@Override
	public void delete(Serializable id) {
		roleDao.delete(id);
		
	}

	@Override
	public void correlationPermissions(Serializable roleId,
			Serializable[] permissionIds) {
		Map<Serializable,Object> param = new HashMap<>() ;
		param.put("roleId", roleId) ;
		param.put("permIds", permissionIds) ;
		roleDao.correlationPermissions(param);
	}

	@Override
	public void unCorrelationPermissions(Serializable roleId,
			Serializable[] permissionIds) {
		Map<Serializable,Object> param = new HashMap<>() ;
		param.put("roleId", roleId) ;
		param.put("permissions", permissionIds) ;
		roleDao.uncorrelationPermissions(param);
	}

	@Override
	public Collection<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public Role getById(Serializable id) {
		return roleDao.get(id);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

}
