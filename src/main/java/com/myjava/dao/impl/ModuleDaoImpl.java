package com.myjava.dao.impl;

import org.springframework.stereotype.Repository;

import com.myjava.dao.ModuleDao;
import com.myjava.entity.Module;
@Repository
public class ModuleDaoImpl extends BaseDaoImpl<Module> implements ModuleDao{
	public ModuleDaoImpl() {
		super.setNameSpace("com.myjava.entity.Module");
	}
}
