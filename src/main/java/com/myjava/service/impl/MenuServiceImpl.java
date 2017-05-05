package com.myjava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjava.dao.MenuDao;
import com.myjava.service.MenuService;
public class MenuServiceImpl implements MenuService{
	private MenuDao menuDao ;
	@Override
	public String[] findAll() {
		return null;
	}

}
