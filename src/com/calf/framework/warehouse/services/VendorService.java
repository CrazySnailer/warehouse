package com.calf.framework.warehouse.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.warehouse.entity.TbWhVendor;
import com.calf.framework.warehouse.qry.VendorQry;
import com.calf.framework.vo.Page;

public interface VendorService extends BaseService{
	/**
	 * 保存
	 **/
	public String saveVendor(TbWhVendor entity);
	/**
	 * 删除
	 */
	public String deleteVendor(TbWhVendor entity);
	/**
	 * 查找分页信息
	 */
	public Page findVendorPage(VendorQry qry);
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long vendorId);
}