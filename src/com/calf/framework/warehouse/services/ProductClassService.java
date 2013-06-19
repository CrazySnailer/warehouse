package com.calf.framework.warehouse.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.warehouse.entity.TbWhProductClass;
import com.calf.framework.warehouse.qry.ProductClassQry;
import com.calf.framework.vo.Page;

public interface ProductClassService extends BaseService{
	public TbWhProductClass get(Long classId);
	/**
	 * 保存
	 **/
	public String saveProductClass(TbWhProductClass entity,boolean refreshTreeNo);
	/**
	 * 
	 */
	public String add(TbWhProductClass entity);
	/**
	 * 删除
	 */
	public String delete(TbWhProductClass entity);
	/**
	 * 根据上级查找子菜单
	 */
	public List findListByParent(Long parentId);
	/**
	 * 查找菜单树形结构
	 * @return
	 */
	public List findTree(ProductClassQry qry);
	/**
	 * 重建treeno编码
	 */
	public String saveRebuild(TbWhProductClass entity);
}