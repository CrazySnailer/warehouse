package com.calf.framework.warehouse.services.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.util.Constants;
import com.calf.framework.warehouse.entity.TbWhProduct;
import com.calf.framework.warehouse.qry.ProductQry;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.services.ProductService;

@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl implements ProductService{
	/**
	 * 查找分页信息
	 */
	public Page findProductPage(ProductQry qry){
		Criteria criteria = hibernateDao.createCriteria(TbWhProduct.class);		
		CriteriaUtils.addEq(criteria, "classId", qry.getClassId());
		CriteriaUtils.addEq(criteria, "productCode", qry.getProductCode());
		CriteriaUtils.addEq(criteria, "productName", qry.getProductName());
		CriteriaUtils.addEq(criteria, "trustId", qry.getTrustId());
		CriteriaUtils.addEq(criteria, "vendorId", qry.getVendorId());
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addOrder(criteria,qry.getOrderCol(),qry.getOrderType());
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(), qry.getPageSize());
	}	
	
	/**
	 * 保存
	 **/
	public String saveProduct(TbWhProduct entity){
		super.save(entity);
		return null;
	}
	/**
	 * 删除
	 */
	public String deleteProduct(TbWhProduct entity){
		entity.setDataStatus(Constants.NO);
		super.save(entity);
		return null;
	}
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(Long productId){
		TbWhProduct entity = super.hibernateDao.get(TbWhProduct.class, productId);
		return entity==null;
	}
}