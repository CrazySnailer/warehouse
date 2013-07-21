package com.calf.framework.services.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.dao.HibernateDAO;
import com.calf.framework.dao.JdbcDAO;
import com.calf.framework.services.BaseService;
import com.calf.framework.util.Constants;
import com.calf.framework.vo.AdminUserInfo;

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
	
	/**
	 * 权限过滤
	 * @param criteria 查询对象
	 * @param orgAlias 过滤机构别名
	 */
	protected void filterData(Criteria criteria,AdminUserInfo userInfo,String orgAlias,String userAlias){
		if(userInfo.findResources(Constants.DATA_PRIV_ALL)){
			//查看所有数据	,不做过滤
		}else if(userInfo.findResources(Constants.DATA_PRIV_SELF_AND_BELOW_DEPT)){
			//本机构数据及本机构以下数据
			CriteriaUtils.addRightLike(criteria, orgAlias+".treeNo", userInfo.getDept().getTreeNo());
		}else if(userInfo.findResources(Constants.DATA_PRIV_SELF_DEPT)){
			//只能看本部门数据
			CriteriaUtils.addEq(criteria, orgAlias+".deptId", userInfo.getDept().getDeptId());
		}else if(userInfo.findResources(Constants.DATA_PRIV_SELF)&&StringUtils.isNotBlank(userAlias)){
			//只查看本人数据
			CriteriaUtils.addEq(criteria, userAlias, userInfo.getUserId());
		}else{
			if(StringUtils.isNotBlank(userAlias)){
				CriteriaUtils.addEq(criteria, userAlias, userInfo.getUserId());
			}else{
				//啥数据也不查，随便给安一个机构ID值
				CriteriaUtils.addEq(criteria, orgAlias+".deptId", Long.valueOf(-8864));
			}
		}
	}
	
	/**
	 * 权限过滤
	 * @param criteria 查询对象
	 * @param orgAlias 过滤机构别名
	 */
	protected void filterData(StringBuffer sql,Map params,AdminUserInfo userInfo,String orgAlias,String userAlias){
		if(userInfo.findResources(Constants.DATA_PRIV_ALL)){
			//查看所有数据	,不做过滤
		}else if(userInfo.findResources(Constants.DATA_PRIV_SELF_AND_BELOW_DEPT)){
			//本机构数据及本机构以下数据
			sql.append(" and ");
			sql.append(orgAlias);
			sql.append(".tree_no like :xxxxx_treeNo ");
			params.put("xxxxx_treeNo", userInfo.getDept().getTreeNo()+"%");
		}else if(userInfo.findResources(Constants.DATA_PRIV_SELF_DEPT)){
			//只能看本部门数据
			sql.append(" and ");
			sql.append(orgAlias);
			sql.append(".dept_id = :xxxxx_deptId ");
			params.put("xxxxx_deptId", userInfo.getDept().getDeptId());
		}else if(userInfo.findResources(Constants.DATA_PRIV_SELF)&&StringUtils.isNotBlank(userAlias)){
			sql.append(" and ");
			sql.append(userAlias);
			sql.append(" = :xxxxx_userId ");
			params.put("xxxxx_userId", userInfo.getUserId());
		}else{
			//如果没有设置
			if(StringUtils.isNotBlank(userAlias)){
				//人员别名存在则默认查看本人数据
				sql.append(" and ");
				sql.append(userAlias);
				sql.append(" = :xxxxx_userId ");
				params.put("xxxxx_userId", userInfo.getUserId());
			}else{
				//否则随便查一个不存在的机构ID
				sql.append(" and ");
				sql.append(orgAlias);
				sql.append(".dept_id = :xxxxx_deptId ");
				params.put("xxxxx_deptId", Long.valueOf(-8684));
			}
		}
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
