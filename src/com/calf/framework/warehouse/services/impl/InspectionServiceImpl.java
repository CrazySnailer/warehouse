package com.calf.framework.warehouse.services.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.util.Constants;
import com.calf.framework.warehouse.entity.TbWhInspection;
import com.calf.framework.warehouse.qry.InspectionQry;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.services.InspectionService;

@Service("inspectionService")
public class InspectionServiceImpl extends BaseServiceImpl implements InspectionService{
	/**
	 * 查找分页信息
	 */
	public Page findInspectionPage(InspectionQry qry){
		Criteria criteria = hibernateDao.createCriteria(TbWhInspection.class);		
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addOrder(criteria,qry.getOrderCol(),qry.getOrderType());
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(), qry.getPageSize());
	}	
	
	/**
	 * 保存
	 **/
	public String saveInspection(TbWhInspection entity){
		super.save(entity);
		return null;
	}
	/**
	 * 删除
	 */
	public String deleteInspection(TbWhInspection entity){
		entity.setDataStatus(Constants.NO);
		super.save(entity);
		return null;
	}
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long inspectionId){
		TbWhInspection entity = super.hibernateDao.get(TbWhInspection.class, inspectionId);
		return entity==null;
	}
}