package com.calf.framework.warehouse.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.warehouse.entity.TbWhStruct;
import com.calf.framework.warehouse.qry.StructQry;
import com.calf.framework.vo.Page;

public interface StructService extends BaseService{
	public TbWhStruct get(Long structId);
	public String add(TbWhStruct entity);
	/**
	 * 保存
	 **/
	public String saveStruct(TbWhStruct entity,boolean refreshTreeNo);
	/**
	 * 删除
	 */
	public String delete(TbWhStruct entity);
	/**
	 * 根据上级查找子菜单
	 */
	public List findListByParent(Long parentId);
	/**
	 * 查找菜单树形结构
	 * @return
	 */
	public List findTree(StructQry qry);
	/**
	 * 重建treeno编码
	 */
	public String saveRebuild(TbWhStruct entity);
}