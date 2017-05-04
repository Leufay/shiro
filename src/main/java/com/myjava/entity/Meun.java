package com.myjava.entity;

import java.io.Serializable;
import java.util.List;

public class Meun implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id ;
	private Long moduleId ;						//模块ID
	private String name ;
	private String url ;
	private List<Button> children ; 			//菜单下的多个按钮
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Button> getChildren() {
		return children;
	}
	public void setChildren(List<Button> children) {
		this.children = children;
	}
	
	
	
	
	
	
	
}
