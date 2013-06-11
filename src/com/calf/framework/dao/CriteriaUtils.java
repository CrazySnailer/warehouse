package com.calf.framework.dao;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class CriteriaUtils {
	
	
	/**
	 * 设置不相等
	 * @param propertyName 属性名称

	 * @param criteria 查询类

	 * @param value 值

	 */
	public static void addNe(Criteria criteria,String propertyName,String value){
		if(StringUtils.isNotBlank(value)){
			criteria.add(Restrictions.ne(propertyName, StringUtils.trim(value)));
		}
	}
	
	/**
	 * 设置相等
	 * @param propertyName 属性名称

	 * @param criteria 查询类

	 * @param value 值

	 */
	public static void addEq(Criteria criteria,String propertyName,String value){
		if(StringUtils.isNotBlank(value)){
			criteria.add(Restrictions.eq(propertyName, StringUtils.trim(value)));
		}
	}
	
	/**
	 * 设置IN
	 * @param criteria
	 * @param propertyName
	 * @param values
	 */
	public static void addIn(Criteria criteria,String propertyName,Object... values){
		if(values!=null){
			criteria.add(Restrictions.in(propertyName, values));
		}
	}
	
	/**
	 * 设置相等
	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	public static void addEq(Criteria criteria,String propertyName,Date value){
		if(value!=null){
			criteria.add(Restrictions.eq(propertyName, value));
		}
	}
	
	/**
	 * 设置相等
	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	public static void addEq(Criteria criteria,String propertyName,Long value){
		if(value!=null){
			criteria.add(Restrictions.eq(propertyName, value));
		}
	}
	
	/**
	 * 设置左边LIKE,LIKE '%00001'
	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	public static void addLeftLike(Criteria criteria,String propertyName,String value){
		if(StringUtils.isNotBlank(value)){
			StringBuffer likeValue = new StringBuffer("%");
			likeValue.append(StringUtils.trim(value));
			criteria.add(Restrictions.like(propertyName, likeValue.toString()));
		}
	}
	
	/**
	 * 设置右边LIKE,LIKE '00001%'
	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	public static void addRightLike(Criteria criteria,String propertyName,String value){
		if(StringUtils.isNotBlank(value)){
			StringBuffer likeValue = new StringBuffer(StringUtils.trim(value));
			likeValue.append("%");
			criteria.add(Restrictions.like(propertyName, likeValue.toString()));
		}
	}
	
	/**
	 * 设置左右LIKE,LIKE '%00001%'
	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	public static void addFullLike(Criteria criteria,String propertyName,String value){
		if(StringUtils.isNotBlank(value)){
			StringBuffer likeValue = new StringBuffer("%");
			likeValue.append(StringUtils.trim(value));
			likeValue.append("%");
			criteria.add(Restrictions.like(propertyName, likeValue.toString()));
		}
	}
	
	/**
	 * 增加大于判断等于判断
	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	public static void addGe(Criteria criteria,String propertyName,Date value){
		if(value!=null){
			criteria.add(Restrictions.ge(propertyName, value));
		}
	}
	
	/**
	 * 增加大于判断等于判断
	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	public static void addGe(Criteria criteria,String propertyName,String value){
		if(StringUtils.isNotBlank(value)){
			criteria.add(Restrictions.ge(propertyName, value));
		}
	}
	
	/**
	 * 增加小于或等于判断

	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	public static void addLe(Criteria criteria,String propertyName,Date value){
		if(value!=null){
			criteria.add(Restrictions.le(propertyName, value));
		}
	}
	
	/**
	 * 增加小于或等于判断

	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	public static void addLe(Criteria criteria,String propertyName,String value){
		if(StringUtils.isNotBlank(value)){
			criteria.add(Restrictions.le(propertyName, value));
		}
	}
	
	/**
	 * 增加大于判断
	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	public static void addGt(Criteria criteria,String propertyName,Date value){
		if(value!=null){
			criteria.add(Restrictions.gt(propertyName, value));
		}
	}
	
	/**
	 * 增加小于判断
	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	public static void addLt(Criteria criteria,String propertyName,Date value){
		if(value!=null){
			criteria.add(Restrictions.lt(propertyName, value));
		}
	}
	
	/**
	 * 增加IN查询条件,in里面的数据类型为LONG
	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	public static void addInLong(Criteria criteria,String propertyName,String value){
		if(StringUtils.isNotBlank(value)){
			if(value.indexOf(",")>0){
				String[] tmp = value.split(",");
				Long[] values = new Long[tmp.length];
				for(int i = 0,len = tmp.length;i<len;i++){
					values[i] = Long.parseLong(tmp[i]);
				}
				criteria.add(Restrictions.in(propertyName, values));
			}else{
				criteria.add(Restrictions.eq(propertyName, Long.parseLong(value)));
			}
		}
	}
	
	/**
	 * 增加IN查询条件,in里面的数据类型为String
	 * @param criteria
	 * @param propertyName
	 * @param value
	 */
	public static void addInString(Criteria criteria,String propertyName,String value){
		if(StringUtils.isNotBlank(value)){
			if(value.indexOf(",")>0){
				String[] values = value.split(",");				
				criteria.add(Restrictions.in(propertyName, values));
			}else{
				criteria.add(Restrictions.eq(propertyName, value));
			}
		}
	}
	/**
	 * 添加排序
	 * @param criteria
	 * @param propertyName
	 * @param orderType
	 */
	public static void addOrder(Criteria criteria,String propertyName,int orderType){
		if(orderType==DESC){
			criteria.addOrder(Order.desc(propertyName));
		}else if(orderType==ASC){
			criteria.addOrder(Order.asc(propertyName));
		}
	}
	
	public static void addSql(Criteria criteria,String sql){
		criteria.add(Restrictions.sqlRestriction(sql));
		//加参数的形式
		//criteria.add(Restrictions.sqlRestriction("{alias}.age BETWEEN (?) AND (?)", ages, types)); 
	}
	/**
	 * 比较属性相等
	 * @param criteria
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public static void addEqProperty(Criteria criteria, String propertyName,
			String otherPropertyName) {
		if (propertyName != null && otherPropertyName != null) {
			criteria.add(Restrictions.eqProperty(propertyName,
					otherPropertyName));
			
		}
	}
	
	/**
	 * 比较属性不相等相等
	 * @param criteria
	 * @param propertyName
	 * @param otherPropertyName
	 */
	public static void addNeProperty(Criteria criteria, String propertyName,
			String otherPropertyName) {
		if (propertyName != null && otherPropertyName != null) {
			criteria.add(Restrictions.neProperty(propertyName,
					otherPropertyName));
		}
	}
	
	/**
	 * 降序
	 */
	public static final int DESC = 0;
	/**
	 * 升序
	 */
	public static final int ASC = 1;
}
