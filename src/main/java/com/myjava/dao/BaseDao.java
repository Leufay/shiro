package com.myjava.dao;

import java.io.Serializable;
import java.util.Collection;

public interface BaseDao<T> {
	public Collection<T> findAll() ;
	public T get(Serializable id) ;
	public void save(T entity) ;
	public void delete(Serializable id) ;
	public void update(T t ) ;
}
