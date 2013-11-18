package com.calf.framework.warehouse.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.warehouse.entity.TbWhUp;
import com.calf.framework.warehouse.qry.UpframeQry;
import com.calf.framework.vo.Page;

public interface UpframeService extends BaseService{
	/**
	 * 保存
	 **/
	public String saveUpframe(TbWhUp entity);
	/**
	 * 删除
	 */
	public String deleteUpframe(TbWhUp entity);
	/**
	 * 查找分页信息
	 */
	public Page findUpframePage(UpframeQry qry);
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long upId);
}