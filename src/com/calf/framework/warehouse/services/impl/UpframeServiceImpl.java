package com.calf.framework.warehouse.services.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.util.Constants;
import com.calf.framework.warehouse.entity.TbWhUp;
import com.calf.framework.warehouse.qry.UpframeQry;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.services.UpframeService;

@Service("upframeService")
public class UpframeServiceImpl extends BaseServiceImpl implements UpframeService{
	/**
	 * 查找分页信息
	 */
	public Page findUpframePage(UpframeQry qry){
		Criteria criteria = hibernateDao.createCriteria(TbWhUp.class);		
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addOrder(criteria,qry.getOrderCol(),qry.getOrderType());
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(), qry.getPageSize());
	}	
	
	/**
	 * 保存
	 **/
	public String saveUpframe(TbWhUp entity){
		super.save(entity);
		return null;
	}
	/**
	 * 删除
	 */
	public String deleteUpframe(TbWhUp entity){
		entity.setDataStatus(Constants.NO);
		super.save(entity);
		return null;
	}
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long upId){
		TbWhUp entity = super.hibernateDao.get(TbWhUp.class, upId);
		return entity==null;
	}
}