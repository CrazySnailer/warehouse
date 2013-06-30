package com.calf.framework.warehouse.web;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.util.Constants;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.entity.TbWhWarehouse;
import com.calf.framework.warehouse.qry.WarehouseQry;
import com.calf.framework.warehouse.services.WarehouseService;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;


public class WarehouseAction extends BaseAction {

		WarehouseService warehouseService;

		WarehouseQry qry;

		Page page;

		TbWhWarehouse entity;
		
		/**
		 * 列表
		 */
		@RequiresPermissions(value = "wh:warehouse:view",requiresUser=true)
		public String list() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if(StringUtils.isNotBlank(super.qryHex)){
				qry = (WarehouseQry)ObjectUtils.getObjectFromHex(qryHex);
			}
			if(qry==null){
				qry = new WarehouseQry();
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			//设置默认排序号
			if(StringUtils.isBlank(qry.getOrderCol())){
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			qry.setUserInfo(userInfo);
			page = warehouseService.findWarehousePage(qry);
			return "list";
		}
		/**
		 * 新增
		 **/
		@RequiresPermissions(value = "wh:warehouse:edit",requiresUser=true)
		public String toAdd() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = new TbWhWarehouse();
			super.event="ADD";
			super.title="新增";
			return "edit";
		}
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:warehouse:edit",requiresUser=true)
		public String toEdit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = warehouseService.get(TbWhWarehouse.class,entity.getWhId());
			super.event="EDIT";
			super.title="修改";
			return "edit";
		}
		/**
		 * 修改动作
		 **/
		@RequiresPermissions(value = "wh:warehouse:edit",requiresUser=true)
		public String edit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if("ADD".equals(super.event)){
				//新增操作
				entity.setWhId(null);				
				entity.setDataStatus(Constants.YES);
				entity.setCreateUser(userInfo.getUserId());
				entity.setCreateDate(new Date());
				
				if(entity.getParentId()!=null){
					entity.setParent(warehouseService.get(TbWhWarehouse.class,entity.getParentId()));
				}
				
				warehouseService.saveWarehouse(entity);
				super.addAttribute("qry.orderCol", "createDate");
				super.addAttribute("qry.orderType", "0");
				super.saveMessage("仓库新增成功");
			}else if("EDIT".equals(super.event)){
				//修改操作
				TbWhWarehouse db = warehouseService.get(TbWhWarehouse.class,entity.getWhId());
				
				db.setWhCode(entity.getWhCode());
				db.setWhName(entity.getWhName());
				
				if(entity.getParentId()!=null){
					db.setParent(warehouseService.get(TbWhWarehouse.class,entity.getParentId()));
				}
				
				db.setWhType(entity.getWhType());
				db.setUseDeptId(entity.getUseDeptId());
				db.setAdminMode(entity.getAdminMode());
				db.setAddress(entity.getAddress());
				db.setLinker(entity.getLinker());
				db.setLinkerTel(entity.getLinkerTel());
				db.setEmail(entity.getEmail());
				
				db.setUpdateUser(userInfo.getUserId());
				db.setUpdateDate(new Date());
				
				warehouseService.saveWarehouse(db);
				super.saveMessage("仓库修改保存成功");
				super.addAttribute("qryHex", super.qryHex);
			}
			super.redirectUrl = "/warehouse/warehouse_list.action";
			return super.GLOBAL_SUCCESS;
		}
		/**
		 * 删除
		 **/
		@RequiresPermissions(value = "wh:warehouse:edit",requiresUser=true)
		public String delete() throws Exception{			
			AdminUserInfo userInfo = super.getUserInfo();
			entity = warehouseService.get(TbWhWarehouse.class,entity.getWhId());
			if(entity.isCanDelete()){
				this.warehouseService.deleteWarehouse(entity);
				super.renderJsonSuccess("删除成功!");
			}else{
				super.renderJsonError("该记录已被删除!");
			}
			return null;
		}
		
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:warehouse:view",requiresUser=true)
		public String toView() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = warehouseService.get(TbWhWarehouse.class,entity.getWhId());
			super.title="仓库详细信息";
			return "view";
		}
		
		/**
		 * 判断是否唯一
		 * @return
		 */
		public String checkUnique()throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			boolean isCorrect = warehouseService.isUnique(entity.getWhId());
			super.rendText(String.valueOf(isCorrect));
			return null;
		}
		
		public WarehouseQry getQry(){
			return this.qry;
		}
		public void setQry(WarehouseQry qry){
			this.qry = qry;
		}
		
		public WarehouseService getWarehouseService(){
			return this.warehouseService;
		}
		public void setWarehouseService(WarehouseService warehouseService){
			this.warehouseService = warehouseService;
		}
		public Page getPage() {
			return this.page;
		}
		public void setPage(Page page) {
			this.page = page;
		}
		public TbWhWarehouse getEntity(){
			return entity;
		}
		public void setEntity(TbWhWarehouse entity){
			this.entity = entity;
		}
}