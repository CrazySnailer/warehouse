package com.calf.framework.warehouse.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.warehouse.entity.TbWhExpress;
import com.calf.framework.warehouse.qry.ExpressQry;
import com.calf.framework.vo.Page;

public interface ExpressService extends BaseService{
	/**
	 * 保存
	 **/
	public String saveExpress(TbWhExpress entity);
	/**
	 * 删除
	 */
	public String deleteExpress(TbWhExpress entity);
	/**
	 * 查找分页信息
	 */
	public Page findExpressPage(ExpressQry qry);
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long expressId);
}