package com.calf.framework.warehouse.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import com.calf.framework.warehouse.entity.TbWhProduct;
import com.calf.framework.warehouse.qry.ProductQry;
import com.calf.framework.vo.Page;

public interface ProductService extends BaseService{
	/**
	 * 保存
	 **/
	public String saveProduct(TbWhProduct entity);
	/**
	 * 删除
	 */
	public String deleteProduct(TbWhProduct entity);
	/**
	 * 查找分页信息
	 */
	public Page findProductPage(ProductQry qry);
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long productId);
}