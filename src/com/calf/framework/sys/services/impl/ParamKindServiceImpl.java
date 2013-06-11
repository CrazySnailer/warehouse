package com.calf.framework.sys.services.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.sys.services.ParamKindService;
import com.calf.framework.um.entity.TbSysParamKind;
import com.calf.framework.um.qry.ParamKindQry;
import com.calf.framework.util.Constants;
import com.calf.framework.vo.Page;


/**
 *  service 实现
 */
@Service("paramKindService")
public class ParamKindServiceImpl extends BaseServiceImpl implements ParamKindService{

	/**
	 * 查找分页信息
	 */
	public Page findParamKindPage(ParamKindQry qry){
		Criteria criteria = super.hibernateDao.createCriteria(TbSysParamKind.class);
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addEq(criteria, "kindCode", qry.getKindCode());
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(), qry.getPageSize());
	}
	
	/**
	 * 获取对象
	 */
	public TbSysParamKind get(String kindCode){
		return super.get(TbSysParamKind.class, kindCode);
	}
	
	/**
	 * 新增
	 **/
	public TbSysParamKind add(TbSysParamKind entity){
		super.add(entity);
		return null;
	}
	
	/**
	 * 更新
	 */
	public String update(TbSysParamKind entity){
		super.save(entity);
		return null;
	}	
	
	/**
	 * 删除
	 */
	public String delete(TbSysParamKind entity){
		super.remove(entity);
		return null;
	}
	
}