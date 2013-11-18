package com.calf.framework.warehouse.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.warehouse.entity.TbWhInspection;
import com.calf.framework.warehouse.qry.InspectionQry;
import com.calf.framework.vo.Page;

public interface InspectionService extends BaseService{
	/**
	 * 保存
	 **/
	public String saveInspection(TbWhInspection entity);
	/**
	 * 删除
	 */
	public String deleteInspection(TbWhInspection entity);
	/**
	 * 查找分页信息
	 */
	public Page findInspectionPage(InspectionQry qry);
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long inspectionId);
}