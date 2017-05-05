package com.myjava.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * 用户
 * @author Mrlxf
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable{
	private Long id ;				//id
	private String username ;		//用户名
	private String name ;			//真实姓名
	private String password ;
	private Set<Role> roles ;		//用户所有的角色
	private Boolean locked ;		//是否锁定
	private String salt ; 			//密码加密的盐
	/**
	 * 页面辅助属性，用来拼接字符串
	 */
	private String roleNames="" ;
	private String roleIds ="";
	
	
	public String getRoleNames() {
		List<Role> roleList = new ArrayList<>(roles);
		for(int i = 0 ; i<roleList.size();i++){
			Role role = roleList.get(i);
			if(i==roleList.size()-1){
				this.roleIds+=role.getId();
				roleNames+=role.getRoleName();
			}else{
				this.roleIds+=role.getId()+",";
				roleNames+=role.getRoleName()+",";
			}
		}
		return roleNames;
	}
	public String getRoleIds() {
		return this.roleIds;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public String getSalt() {
		return this.username;
	}
	public void setSalt(String salt) {
		this.salt = this.username;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
