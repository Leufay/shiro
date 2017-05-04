package com.myjava.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
/**
 * 角色
 * @author Mrlxf
 *
 */
@SuppressWarnings("serial")
public class Role implements Serializable{	
	private Long id ;								//id
	private String roleName ;
	private Boolean avialiable ;					//是否可用
	private String descr ;							//角色描述
	private List<Permission> permissions ;			//角色所拥有的权限
	
	
	
	/**
	 * 页面辅助属性
	 */
	private String permIds ;
	private String permNames ;
	
	
	
	public String getPermIds() {
		
		return this.permIds;
	}
	public void setPermIds(String permIds) {
		this.permIds = permIds;
	}
	public String getPermNames() {
		this.permIds="" ;
		this.permNames="" ;
		Permission perm = null ;
		for (int i = 0; i < this.permissions.size(); i++) {
			perm = permissions.get(i) ;
			if (i==permissions.size()-1) {
				this.permIds += perm.getId() ;
				this.permNames += perm.getPermName() ;
			}else{
				this.permIds += perm.getId()+"," ;
				this.permNames += perm.getPermName()+"," ;
			}
		}
		return this.permNames;
	}
	public void setPermNames(String permNames) {
		this.permNames = permNames;
	}
	public Boolean getAvialiable() {
		return avialiable;
	}
	public void setAvialiable(Boolean avialiable) {
		this.avialiable = avialiable;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
