package com.calf.framework.warehouse.services.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.util.Constants;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.entity.TbWhOrderInD;
import com.calf.framework.warehouse.entity.TbWhOrderInM;
import com.calf.framework.warehouse.qry.OrderinQry;
import com.calf.framework.warehouse.services.OrderinService;
import com.calf.framework.warehouse.services.impl.storeproc.SpOrderNo;

@Service("orderinService")
public class OrderinServiceImpl extends BaseServiceImpl implements OrderinService{
	/**
	 * 查找分页信息
	 */
	public Page findOrderinPage(OrderinQry qry){
		Criteria criteria = hibernateDao.createCriteria(TbWhOrderInM.class);		
		CriteriaUtils.addEq(criteria, "orderNo", qry.getOrderNo());
		CriteriaUtils.addEq(criteria, "orderType", qry.getOrderInType());
		CriteriaUtils.addEq(criteria, "custOrderNo", qry.getCustOrderNo());
		CriteriaUtils.addEq(criteria, "whId", qry.getWhId());
		CriteriaUtils.addEq(criteria, "vendorId", qry.getVendorId());
		CriteriaUtils.addEq(criteria, "trustId", qry.getTrustId());
		CriteriaUtils.addEq(criteria, "linker", qry.getLinker());
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addOrder(criteria,qry.getOrderCol(),qry.getOrderType());
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(), qry.getPageSize());
	}	
	
	/**
	 * 保存
	 **/
	public String addOrderin(TbWhOrderInM entity){
		//生成订单号
		SpOrderNo sp = new SpOrderNo(super.jdbcDao.getJdbcTemplate());
		String orderNo = sp.execute("RKD", "RK", "入库单", entity.getCreateUser());
		entity.setOrderNo(orderNo);
		super.save(entity);
		
		//增加明细数据
		
		return null;
	}
	
	/**
	 * 保存
	 **/
	public String saveOrderin(TbWhOrderInM entity){
		super.save(entity);
		return null;
	}
	/**
	 * 删除
	 */
	public String deleteOrderin(TbWhOrderInM entity){
		entity.setDataStatus(Constants.NO);
		super.save(entity);
		return null;
	}
	
	/**
	 * 查找订单明细
	 */
	public List<TbWhOrderInD> findOrderDetail(TbWhOrderInM order) {
		return (List<TbWhOrderInD>)super.hibernateDao.find("from TbWhOrderInD d where d.order = ? order by d.orderDId asc");
	}

	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long orderId){
		TbWhOrderInM entity = super.hibernateDao.get(TbWhOrderInM.class, orderId);
		return entity==null;
	}
}