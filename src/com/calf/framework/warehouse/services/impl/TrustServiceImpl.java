package com.calf.framework.warehouse.services.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.util.Constants;
import com.calf.framework.warehouse.entity.TbWhTrust;
import com.calf.framework.warehouse.qry.TrustQry;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.services.TrustService;

@Service("trustService")
public class TrustServiceImpl extends BaseServiceImpl implements TrustService{
	/**
	 * 查找分页信息
	 */
	public Page findTrustPage(TrustQry qry){
		Criteria criteria = hibernateDao.createCriteria(TbWhTrust.class);		
		CriteriaUtils.addEq(criteria, "trustCode", qry.getTrustCode());
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(), qry.getPageSize());
	}	
	
	/**
	 * 保存
	 **/
	public String saveTrust(TbWhTrust entity){
		super.save(entity);
		return null;
	}
	/**
	 * 删除
	 */
	public String deleteTrust(TbWhTrust entity){
		entity.setDataStatus(Constants.NO);
		super.save(entity);
		return null;
	}
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long trustId){
		TbWhTrust entity = super.hibernateDao.get(TbWhTrust.class, trustId);
		return entity==null;
	}
}