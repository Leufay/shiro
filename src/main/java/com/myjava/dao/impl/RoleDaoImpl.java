package com.myjava.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.myjava.dao.RoleDao;
import com.myjava.entity.Role;
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{
	public RoleDaoImpl() {
		super.setNameSpace("com.myjava.entity.Role");
	}
	/**
	 * 关联角色-权限
	 */
	@Override
	public void correlationPermissions(Map<Serializable, Object> param) {
		//先解除角色-权限之间的联系 
		this.uncorrelationPermissions(param);
		if(param.get("permIds")!=null){
			getSqlSession().insert(getNameSpace()+".correlationPermissions", param) ;
		}
	}
	/**
	 * 解除角色-权限关联
	 */
	@Override
	public void uncorrelationPermissions(Map<Serializable, Object> param) {
		getSqlSession().delete(getNameSpace()+".uncorrelationPermissions", param) ;
	}
	/**
	 * 删除角色<br>
	 * Note:重写父类中的delete方法
	 * 删除角色之前必须先解除用户角色之间联系
	 */
	@Override
	public void delete(Serializable id){
		//先解除用户角色之间的关联
		getSqlSession().delete(getNameSpace()+".uncorrleationUsers",id);
		super.delete(id);
	}
	
	@Override
	public List<String> getRolesByUsername(String username) {
		return getSqlSession().selectList(getNameSpace()+".getRolesByUsername" , username);
	}
}
