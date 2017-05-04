package com.myjava.entity;

import java.io.Serializable;
/**
 * 权限
 * @author Mrlxf
 *
 */
@SuppressWarnings("serial")
public class Permission implements Serializable{
	private Long id ;
	private String permName ;
	private String descr ; 					//权限描述
	private String available  ; 			//是否可用
	private String permType ; 				
	private String permNo ;					//权限标识例如:system:user:add
	private String url ;
	private String icon ;
	
	
	public String getPermNo() {
		return permNo;
	}
	public void setPermNo(String permNo) {
		this.permNo = permNo;
	}
	public String getPermType() {
		return permType;
	}
	public void setPermType(String permType) {
		this.permType = permType;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPermName() {
		return permName;
	}
	public void setPermName(String permName) {
		this.permName = permName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
