package com.calf.framework.warehouse.services.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.util.Constants;
import com.calf.framework.warehouse.entity.TbWhWarehouse;
import com.calf.framework.warehouse.qry.WarehouseQry;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.services.WarehouseService;

@Service("warehouseService")
public class WarehouseServiceImpl extends BaseServiceImpl implements WarehouseService{
	/**
	 * 查找分页信息
	 */
	public Page findWarehousePage(WarehouseQry qry){
		Criteria criteria = hibernateDao.createCriteria(TbWhWarehouse.class);		
		CriteriaUtils.addEq(criteria, "whCode", qry.getWhCode());
		CriteriaUtils.addEq(criteria, "whName", qry.getWhName());
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addOrder(criteria,qry.getOrderCol(),qry.getOrderType());
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(), qry.getPageSize());
	}	
	
	/**
	 * 保存
	 **/
	public String saveWarehouse(TbWhWarehouse entity){
		super.save(entity);
		return null;
	}
	/**
	 * 删除
	 */
	public String deleteWarehouse(TbWhWarehouse entity){
		entity.setDataStatus(Constants.NO);
		super.save(entity);
		return null;
	}
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long whId){
		TbWhWarehouse entity = super.hibernateDao.get(TbWhWarehouse.class, whId);
		return entity==null;
	}
}