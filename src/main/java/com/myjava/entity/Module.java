package com.myjava.entity;

import java.awt.Menu;
import java.io.Serializable;
import java.util.List;
/**
 * 模块
 * @author Mrlxf
 *
 */
@SuppressWarnings("serial")
public class Module implements Serializable{
	private Long id ;
	private String name ;				//模块名称
	private List<Menu> children ;				//模块下的子菜单
	private String url ; 				//模块url
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	
	
}
