package com.myjava.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjava.dao.ModuleDao;
import com.myjava.entity.Module;
import com.myjava.service.ModuleService;
@Service
public class ModuleServiceImpl implements ModuleService{
	@Autowired
	private ModuleDao moduleDao ;
	@Override
	public Collection<Module> findAll() {
		return moduleDao.findAll();
	}

}
