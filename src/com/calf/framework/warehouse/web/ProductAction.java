package com.calf.framework.warehouse.web;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.util.Constants;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.entity.TbWhProduct;
import com.calf.framework.warehouse.qry.ProductQry;
import com.calf.framework.warehouse.services.ProductService;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;


public class ProductAction extends BaseAction {

		ProductService productService;

		ProductQry qry;

		Page page;

		TbWhProduct entity;
		
		/**
		 * 列表
		 */
		@RequiresPermissions(value = "wh:product:view",requiresUser=true)
		public String list() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if(StringUtils.isNotBlank(super.qryHex)){
				qry = (ProductQry)ObjectUtils.getObjectFromHex(qryHex);
			}
			if(qry==null){
				qry = new ProductQry();
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			//设置默认排序号
			if(StringUtils.isBlank(qry.getOrderCol())){
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			qry.setUserInfo(userInfo);
			page = productService.findProductPage(qry);
			return "list";
		}
		/**
		 * 新增
		 **/
		@RequiresPermissions(value = "wh:product:edit",requiresUser=true)
		public String toAdd() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = new TbWhProduct();
			super.event="ADD";
			super.title="新增商品管理";
			return "edit";
		}
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:product:edit",requiresUser=true)
		public String toEdit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = productService.get(TbWhProduct.class,entity.getProductId());
			super.event="EDIT";
			super.title="修改商品管理";
			return "edit";
		}
		/**
		 * 修改动作
		 **/
		@RequiresPermissions(value = "wh:product:edit",requiresUser=true)
		public String edit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if("ADD".equals(super.event)){
				//新增操作
				entity.setProductId(null);				
				entity.setDataStatus(Constants.YES);
				entity.setCreateUser(userInfo.getUserId());
				entity.setCreateDate(new Date());
				productService.saveProduct(entity);
				super.addAttribute("qry.orderCol", "createDate");
				super.addAttribute("qry.orderType", "0");
				super.saveMessage("商品管理新增成功");
			}else if("EDIT".equals(super.event)){
				//修改操作
				TbWhProduct db = productService.get(TbWhProduct.class,entity.getProductId());
				
				db.setProductId(entity.getProductId());
				db.setClassId(entity.getClassId());
				db.setDeptId(entity.getDeptId());
				db.setProductCode(entity.getProductCode());
				db.setProductSku(entity.getProductSku());
				db.setProductName(entity.getProductName());
				db.setTrustId(entity.getTrustId());
				db.setVendorId(entity.getVendorId());
				db.setStructId(entity.getStructId());
				db.setProductCost(entity.getProductCost());
				db.setBrandName(entity.getBrandName());
				db.setModelNo(entity.getModelNo());
				db.setUnitName(entity.getUnitName());
				db.setBigUnitName(entity.getBigUnitName());
				db.setExchangeNum(entity.getExchangeNum());
				db.setAdminType(entity.getAdminType());
				db.setMakeLocation(entity.getMakeLocation());
				db.setMakeFactory(entity.getMakeFactory());
				db.setWarrantyDay(entity.getWarrantyDay());
				db.setTrustSettlePrice(entity.getTrustSettlePrice());
				db.setVendorSettlePrice(entity.getVendorSettlePrice());
				db.setRemark(entity.getRemark());
				
				db.setUpdateUser(userInfo.getUserId());
				db.setUpdateDate(new Date());
				
				productService.saveProduct(db);
				super.saveMessage("商品管理保存成功");
				super.addAttribute("qryHex", super.qryHex);
			}
			super.redirectUrl = "/warehouse/product_list.action";
			return super.GLOBAL_SUCCESS;
		}
		/**
		 * 删除
		 **/
		@RequiresPermissions(value = "wh:product:edit",requiresUser=true)
		public String delete() throws Exception{			
			AdminUserInfo userInfo = super.getUserInfo();
			entity = productService.get(TbWhProduct.class,entity.getProductId());
			if(entity.isCanDelete()){
				this.productService.deleteProduct(entity);
				super.renderJsonSuccess("删除成功!");
			}else{
				super.renderJsonError("该记录已被删除!");
			}
			return null;
		}
		
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:product:view",requiresUser=true)
		public String toView() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = productService.get(TbWhProduct.class,entity.getProductId());
			super.title="商品管理详细信息";
			return "view";
		}
		
		/**
		 * 判断是否唯一
		 * @return
		 */
		public String checkUnique()throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			boolean isCorrect = productService.isUnique(entity.getProductId());
			super.rendText(String.valueOf(isCorrect));
			return null;
		}
		
		public ProductQry getQry(){
			return this.qry;
		}
		public void setQry(ProductQry qry){
			this.qry = qry;
		}
		
		public ProductService getProductService(){
			return this.productService;
		}
		public void setProductService(ProductService productService){
			this.productService = productService;
		}
		public Page getPage() {
			return this.page;
		}
		public void setPage(Page page) {
			this.page = page;
		}
		public TbWhProduct getEntity(){
			return entity;
		}
		public void setEntity(TbWhProduct entity){
			this.entity = entity;
		}
}