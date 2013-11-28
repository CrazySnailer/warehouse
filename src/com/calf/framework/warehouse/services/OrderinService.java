package com.calf.framework.warehouse.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.entity.TbWhOrderInD;
import com.calf.framework.warehouse.entity.TbWhOrderInM;
import com.calf.framework.warehouse.qry.OrderinQry;

public interface OrderinService extends BaseService{
	/**
	 * 保存
	 **/
	public String addOrderin(TbWhOrderInM entity);
	/**
	 * 保存
	 **/
	public String saveOrderin(TbWhOrderInM entity);
	/**
	 * 删除
	 */
	public String deleteOrderin(TbWhOrderInM entity);
	/**
	 * 查找分页信息
	 */
	public Page findOrderinPage(OrderinQry qry);
	/**
	 * 查找订单明细
	 * @param order
	 * @return
	 */
	public List<TbWhOrderInD> findOrderDetail(TbWhOrderInM order);
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long orderId);
	
}