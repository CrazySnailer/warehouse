package com.calf.framework.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.calf.framework.util.BeanUtils;
import com.calf.framework.vo.Page;

public class HibernateDAO extends HibernateDaoSupport {
	public <T> Criteria createCriteria(Class<T> entityClass) {
		Criteria criteria = getSession().createCriteria(entityClass);
		return criteria;
	}

	/**
	 * 如果找不到符合条件的纪录,将返回null
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityClass, Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 如果找不到符合条件的纪录,将会报出ObjectNotFoundEcception
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T load(Class<T> entityClass, Serializable id) {
		return (T) getHibernateTemplate().load(entityClass, id);
	}

	public List execustSqlForList(String sql, Object[] param) {
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createSQLQuery("sql").list();
					}
				});
	}

	public void bulkUpdate(String sql, Object... param) {
		this.getHibernateTemplate().bulkUpdate(sql, param);
	}

	/**
	 * 根据名称进行查找
	 */
	public List queryListByNameQuery(String nameQuery, Object... values) {
		Query query = getSession().getNamedQuery(nameQuery);
		for (int i = 0, len = values.length; i < len; i++) {
			if (values[i] instanceof String) {
				query.setString(i, (String) values[i]);
			} else if (values[i] instanceof Integer) {
				query.setInteger(i, (Integer) values[i]);
			} else if (values[i] instanceof Long) {
				query.setLong(i, (Long) values[i]);
			} else {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	/**
	 * 增加数据
	 */
	public Serializable add(Object entity) {
		return getHibernateTemplate().save(entity);
	}

	/**
	 * 保存对象.
	 */
	public void save(Object o) {
		getHibernateTemplate().saveOrUpdate(o);

	}

	/**
	 * 删除对象.
	 */
	public void remove(Object o) {
		getHibernateTemplate().delete(o);
	}

	/**
	 * 根据ID删除对象.
	 */
	public <T> void removeById(Class<T> entityClass, Serializable id) {
		remove(get(entityClass, id));
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void clear() {
		getHibernateTemplate().clear();
	}

	public List find(String hql, Object... values) {
		Assert.hasText(hql);
		return getHibernateTemplate().find(hql, values);
	}

	public List find(String hql) {
		Assert.hasText(hql);
		return getHibernateTemplate().find(hql);
	}

	/**
	 * 返回查询结果的记录数
	 */
	public int count(String hql, Object values) {
		Assert.hasText(hql);
		List count = getHibernateTemplate().find(hql, values);
		return ((Long) count.get(0)).intValue();
	}

	public int count(String hql, Object[] values) {
		Assert.hasText(hql);
		List count = getHibernateTemplate().find(hql, values);
		return ((Long) count.get(0)).intValue();
	}

	/**
	 * 查找前多少行数据
	 * 
	 * @param hql
	 *            hql
	 * @param maxResult
	 *            最大值
	 * @param values
	 *            参数
	 * @return
	 */
	public List findTopList(String hql, int maxResult, Object... values) {
		Query query = createQuery(hql, values);
		return query.setMaxResults(maxResult).list();
	}

	/**
	 * 分页查询函数，使用hql.
	 * 
	 * @param pageNo
	 *            页号,从1开始.
	 */
	public Page pagedQuery(String hql, int pageNo, int pageSize, Object[] values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count (*) "
				+ removeSelect(removeOrders(hql));
		List countlist = getHibernateTemplate().find(countQueryString, values);
		int totalCount = 0;
		if (countlist.get(0) instanceof java.lang.Long)
			totalCount = ((Long) countlist.get(0)).intValue();
		else
			totalCount = ((Integer) countlist.get(0)).intValue();

		if (totalCount < 1)
			return new Page();
		// 实际查询返回分页对象
		int startIndex = (pageNo - 1) * pageSize;
		Query query = createQuery(hql, values);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();

		return new Page(list, pageNo, pageSize, totalCount);
	}

	public Page pagedQuery(Criteria criteria, int pageNo, int pageSize) {
		Assert.notNull(criteria);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		CriteriaImpl impl = (CriteriaImpl) criteria;

		// 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
		Projection projection = impl.getProjection();
		List<CriteriaImpl.OrderEntry> orderEntries;
		try {
			orderEntries = (List) BeanUtils.forceGetProperty(impl,
					"orderEntries");
			BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		// 执行查询
		logger.debug(criteria.setProjection(Projections.rowCount()));
		int totalCount = ((Integer) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue();

		// 将之前的Projection和OrderBy条件重新设回去

		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}

		try {
			BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		// 返回分页对象
		if (totalCount < 1)
			return new Page();

		int startIndex = (pageNo - 1) * pageSize;
		List list = criteria.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();
		return new Page(list, pageNo, pageSize, totalCount);
	}

	public Query createQuery(String hql, Object[] values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql
				+ " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
}
