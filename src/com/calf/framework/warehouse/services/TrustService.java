package com.calf.framework.warehouse.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.warehouse.entity.TbWhTrust;
import com.calf.framework.warehouse.qry.TrustQry;
import com.calf.framework.vo.Page;

public interface TrustService extends BaseService{
	/**
	 * 保存
	 **/
	public String saveTrust(TbWhTrust entity);
	/**
	 * 删除
	 */
	public String deleteTrust(TbWhTrust entity);
	/**
	 * 查找分页信息
	 */
	public Page findTrustPage(TrustQry qry);
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long trustId);
}