package com.myjava.dao;

import java.io.Serializable;
import java.util.Map;

import com.myjava.entity.Role;

public interface RoleDao extends BaseDao<Role>{
	/**
	 * 增加角色权限
	 * @param roleId
	 * @param permissionIds
	 */
	public void correlationPermissions(Map<Serializable,Object> param);
	/**
	 * 移除角色-权限之间的联系
	 * @param roleId
	 * @param permissionIds
	 */
	public void uncorrelationPermissions(Map<Serializable,Object> param) ;
}
