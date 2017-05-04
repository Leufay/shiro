package com.myjava.entity;

import java.io.Serializable;
/**
 * 页面按钮
 * 
 * @author Mrlxf
 *
 */
@SuppressWarnings("serial")
public class Button implements Serializable{
	private Long id ;
	private Long menuId ;							//按钮归属于一个页面，页面和菜单一对一
	private String buttonNo ;
	private String name ;
	private String url ;
	
	
	
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public String getButtonNo() {
		return buttonNo;
	}
	public void setButtonNo(String buttonNo) {
		this.buttonNo = buttonNo;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
