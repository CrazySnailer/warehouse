package com.calf.framework.services;

import java.io.Serializable;

public interface BaseService {
	public <T> T load(Class<T> entityClass, Serializable id);

	public <T> T get(Class<T> entityClass, Serializable id);

	public <T> T getIfNull(Class<T> entityClass, Serializable id);

	public void save(Object obj);

	public void remove(Object o);

	public <T> void removeById(Class<T> entityClass, Serializable id);
	
	public Serializable add(Object entity);
}
