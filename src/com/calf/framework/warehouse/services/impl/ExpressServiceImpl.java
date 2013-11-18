package com.calf.framework.warehouse.services.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.util.Constants;
import com.calf.framework.warehouse.entity.TbWhExpress;
import com.calf.framework.warehouse.qry.ExpressQry;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.services.ExpressService;

@Service("expressService")
public class ExpressServiceImpl extends BaseServiceImpl implements ExpressService{
	/**
	 * 查找分页信息
	 */
	public Page findExpressPage(ExpressQry qry){
		Criteria criteria = hibernateDao.createCriteria(TbWhExpress.class);		
		CriteriaUtils.addEq(criteria, "expressName", qry.getExpressName());
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addOrder(criteria,qry.getOrderCol(),qry.getOrderType());
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(), qry.getPageSize());
	}	
	
	/**
	 * 保存
	 **/
	public String saveExpress(TbWhExpress entity){
		super.save(entity);
		return null;
	}
	/**
	 * 删除
	 */
	public String deleteExpress(TbWhExpress entity){
		entity.setDataStatus(Constants.NO);
		super.save(entity);
		return null;
	}
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long expressId){
		TbWhExpress entity = super.hibernateDao.get(TbWhExpress.class, expressId);
		return entity==null;
	}
}