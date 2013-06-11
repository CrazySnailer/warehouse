package com.calf.framework.services.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import com.calf.framework.dao.HibernateDAO;
import com.calf.framework.dao.JdbcDAO;
import com.calf.framework.services.BaseService;

public class BaseServiceImpl implements BaseService {
	@Resource
	protected HibernateDAO hibernateDao;
	@Resource
	protected JdbcDAO jdbcDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.routdata.util.IBaseLogic#load(java.lang.Class,
	 * java.io.Serializable)
	 */
	public <T> T load(Class<T> entityClass, Serializable id) {
		return hibernateDao.load(entityClass, id);
	}

	public <T> T get(Class<T> entityClass, Serializable id) {
		return hibernateDao.get(entityClass, id);
	}

	public <T> T getIfNull(Class<T> entityClass, Serializable id) {
		if (id == null) {
			return null;
		} else {
			return hibernateDao.get(entityClass, id);
		}
	}
	
	protected void bulkUpdate(String sql,Object... param){
		hibernateDao.bulkUpdate(sql, param);
	}
	
	/* (non-Javadoc)
	 * @see com.routdata.util.IBaseLogic#remove(java.lang.Object)
	 */
	public void remove(Object o) {
		hibernateDao.remove(o);
	}
	
	/* (non-Javadoc)
	 * @see com.routdata.util.IBaseLogic#removeById(java.lang.Class, java.io.Serializable)
	 */
	public <T> void removeById(Class<T> entityClass, Serializable id) {
		hibernateDao.removeById(entityClass,id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.routdata.util.IBaseLogic#save(java.lang.Object)
	 */
	public void save(Object obj) {
		this.hibernateDao.save(obj);
	}
	
	public Serializable add(Object entity){
		return this.hibernateDao.add(entity);
	}

	public HibernateDAO getHibernateDao() {
		return hibernateDao;
	}

	public void setHibernateDao(HibernateDAO hibernateDao) {
		this.hibernateDao = hibernateDao;
	}

	public JdbcDAO getJdbcDao() {
		return jdbcDao;
	}

	public void setJdbcDao(JdbcDAO jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

}
