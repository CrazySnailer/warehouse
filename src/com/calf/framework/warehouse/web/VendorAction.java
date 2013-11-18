package com.calf.framework.warehouse.web;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.util.Constants;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.entity.TbWhVendor;
import com.calf.framework.warehouse.qry.VendorQry;
import com.calf.framework.warehouse.services.VendorService;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;


public class VendorAction extends BaseAction {

		VendorService vendorService;

		VendorQry qry;

		Page page;

		TbWhVendor entity;
		
		/**
		 * 列表
		 */
		@RequiresPermissions(value = "wh:vendor:view",requiresUser=true)
		public String list() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if(StringUtils.isNotBlank(super.qryHex)){
				qry = (VendorQry)ObjectUtils.getObjectFromHex(qryHex);
			}
			if(qry==null){
				qry = new VendorQry();
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			//设置默认排序号
			if(StringUtils.isBlank(qry.getOrderCol())){
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			qry.setUserInfo(userInfo);
			page = vendorService.findVendorPage(qry);
			return "list";
		}
		/**
		 * 新增
		 **/
		@RequiresPermissions(value = "wh:vendor:edit",requiresUser=true)
		public String toAdd() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = new TbWhVendor();
			super.event="ADD";
			super.title="新增";
			return "edit";
		}
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:vendor:edit",requiresUser=true)
		public String toEdit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = vendorService.get(TbWhVendor.class,entity.getVendorId());
			super.event="EDIT";
			super.title="修改";
			return "edit";
		}
		/**
		 * 修改动作
		 **/
		@RequiresPermissions(value = "wh:vendor:edit",requiresUser=true)
		public String edit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if("ADD".equals(super.event)){
				//新增操作
				entity.setVendorId(null);				
				entity.setDataStatus(Constants.YES);
				entity.setCreateUser(userInfo.getUserId());
				entity.setCreateDate(new Date());
				vendorService.saveVendor(entity);
				super.addAttribute("qry.orderCol", "createDate");
				super.addAttribute("qry.orderType", "0");
				super.saveMessage("供应商新增成功");
			}else if("EDIT".equals(super.event)){
				//修改操作
				TbWhVendor db = vendorService.get(TbWhVendor.class,entity.getVendorId());
				
				db.setVendorCode(entity.getVendorCode());
				db.setVendorName(entity.getVendorName());
				db.setShortName(entity.getShortName());
				db.setLinker(entity.getLinker());
				db.setLinkerTel(entity.getLinkerTel());
				db.setAddress(entity.getAddress());
				db.setEmail(entity.getEmail());
				db.setPostcode(entity.getPostcode());
				db.setRemark(entity.getRemark());
				
				db.setUpdateUser(userInfo.getUserId());
				db.setUpdateDate(new Date());
				
				vendorService.saveVendor(db);
				super.saveMessage("供应商修改保存成功");
				super.addAttribute("qryHex", super.qryHex);
			}
			super.redirectUrl = "/warehouse/vendor_list.action";
			return super.GLOBAL_SUCCESS;
		}
		/**
		 * 删除
		 **/
		@RequiresPermissions(value = "wh:vendor:edit",requiresUser=true)
		public String delete() throws Exception{			
			AdminUserInfo userInfo = super.getUserInfo();
			entity = vendorService.get(TbWhVendor.class,entity.getVendorId());
			if(entity.isCanDelete()){
				this.vendorService.deleteVendor(entity);
				super.renderJsonSuccess("删除成功!");
			}else{
				super.renderJsonError("该记录已被删除!");
			}
			return null;
		}
		
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:vendor:view",requiresUser=true)
		public String toView() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = vendorService.get(TbWhVendor.class,entity.getVendorId());
			super.title="供应商详细信息";
			return "view";
		}
		
		/**
		 * 判断是否唯一
		 * @return
		 */
		public String checkUnique()throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			boolean isCorrect = vendorService.isUnique(entity.getVendorId());
			super.rendText(String.valueOf(isCorrect));
			return null;
		}
		
		/**
		 * 选择供应商
		 */
		@RequiresPermissions(value = "wh:vendor:view",requiresUser=true)
		public String select() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if(StringUtils.isNotBlank(super.qryHex)){
				qry = (VendorQry)ObjectUtils.getObjectFromHex(qryHex);
			}
			if(qry==null){
				qry = new VendorQry();
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			//设置默认排序号
			if(StringUtils.isBlank(qry.getOrderCol())){
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			qry.setUserInfo(userInfo);
			page = vendorService.findVendorPage(qry);
			return "select";
		}
		
		public VendorQry getQry(){
			return this.qry;
		}
		public void setQry(VendorQry qry){
			this.qry = qry;
		}
		
		public VendorService getVendorService(){
			return this.vendorService;
		}
		public void setVendorService(VendorService vendorService){
			this.vendorService = vendorService;
		}
		public Page getPage() {
			return this.page;
		}
		public void setPage(Page page) {
			this.page = page;
		}
		public TbWhVendor getEntity(){
			return entity;
		}
		public void setEntity(TbWhVendor entity){
			this.entity = entity;
		}
}