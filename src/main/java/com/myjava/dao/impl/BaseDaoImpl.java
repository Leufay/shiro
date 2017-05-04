package com.myjava.dao.impl;

import java.io.Serializable;
import java.util.Collection;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.myjava.dao.BaseDao;

public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T>{
	protected String nameSpace ;			//命名空间
	@Autowired
	public void setSF(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	@Override
	public Collection<T> findAll() {
		return getSqlSession().selectList(nameSpace+".findAll", null);
	}

	@Override
	public T get(Serializable id) {
		return getSqlSession().selectOne(nameSpace+".get", id);
	}
	@Override
	public void save(T entity) {
		getSqlSession().insert(nameSpace+".insert", entity) ;
	}
	@Override
	public void delete(Serializable id) {
		getSqlSession().delete(getNameSpace()+".delete", id) ;
		
	}
	@Override
	public void update(T t) {
		getSqlSession().update(getNameSpace()+".update" , t) ;
	}
	
}
