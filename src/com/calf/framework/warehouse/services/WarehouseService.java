package com.calf.framework.warehouse.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.warehouse.entity.TbWhWarehouse;
import com.calf.framework.warehouse.qry.WarehouseQry;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;

public interface WarehouseService extends BaseService{
	/**
	 * 保存
	 **/
	public String saveWarehouse(TbWhWarehouse entity);
	/**
	 * 删除
	 */
	public String deleteWarehouse(TbWhWarehouse entity);
	/**
	 * 查找分页信息
	 */
	public Page findWarehousePage(WarehouseQry qry);
	/**
	 * 查找所有的物理仓库
	 */
	public List<TbWhWarehouse> findAllPhysicsWarehouse(AdminUserInfo userInfo);
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long whId);
}