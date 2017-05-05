package com.myjava.dao.impl;

import java.awt.Menu;

import com.myjava.dao.MenuDao;
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao{
	
	public MenuDaoImpl(){
		super.setNameSpace("com.myjava.entity.Menu");
	}
}
