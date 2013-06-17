package com.calf.framework.warehouse.services.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.util.Constants;
import com.calf.framework.warehouse.entity.TbWhVendor;
import com.calf.framework.warehouse.qry.VendorQry;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.services.VendorService;

@Service("vendorService")
public class VendorServiceImpl extends BaseServiceImpl implements VendorService{
	/**
	 * 查找分页信息
	 */
	public Page findVendorPage(VendorQry qry){
		Criteria criteria = hibernateDao.createCriteria(TbWhVendor.class);		
		CriteriaUtils.addEq(criteria, "vendorCode", qry.getVendorCode());
		CriteriaUtils.addEq(criteria, "vendorName", qry.getVendorName());
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addOrder(criteria,qry.getOrderCol(),qry.getOrderType());
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(), qry.getPageSize());
	}	
	
	/**
	 * 保存
	 **/
	public String saveVendor(TbWhVendor entity){
		super.save(entity);
		return null;
	}
	/**
	 * 删除
	 */
	public String deleteVendor(TbWhVendor entity){
		entity.setDataStatus(Constants.NO);
		super.save(entity);
		return null;
	}
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long vendorId){
		TbWhVendor entity = super.hibernateDao.get(TbWhVendor.class, vendorId);
		return entity==null;
	}
}