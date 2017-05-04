package com.myjava.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjava.dao.PermissionDao;
import com.myjava.dao.RoleDao;
import com.myjava.entity.Permission;
import com.myjava.entity.User;
import com.myjava.service.PermissionService;
import com.myjava.utils.StringUtils;
@Service
public class PermissionServiceImpl implements PermissionService{
	@Autowired
	private PermissionDao permissionDao ;
	@Autowired
	private RoleDao roleDao ;
	
	@Override
	public void insert(Permission permission) {
		permissionDao.save(permission) ;
	}

	@Override
	public void delete(Serializable id) {
		//删除权限之前先删除权限和角色之间的关联
		Map<Serializable,Object> param = new HashMap<>() ;
		param.put("permId", id) ;
		roleDao.uncorrelationPermissions(param);
		permissionDao.delete(id);
	}

	@Override
	public Collection<Permission> findPermissionsByUsername(String username) {
		return permissionDao.getPermissionsByUsername(username);
	}

	@Override
	public List<Permission> findAll() {
		return (List<Permission>) permissionDao.findAll();
	}

	@Override
	public Permission get(Serializable permId) {
		return permissionDao.get(permId);
	}

	@Override
	public void update(Permission perm) {
		permissionDao.update(perm);
	}

	@Override
	public void setResources(String[] permUrls,
			Long permId) {
		Map<Serializable,Object> param = new HashMap<>() ;
		param.put("permId", permId) ;
		String permUrl = StringUtils.convertToString(permUrls, ",") ;
		param.put("permUrl", permUrl) ;
		permissionDao.correlationResource(param);
	}
	
	
	
}
