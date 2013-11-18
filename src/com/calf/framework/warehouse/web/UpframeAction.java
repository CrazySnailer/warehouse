package com.calf.framework.warehouse.web;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.util.Constants;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.entity.TbWhUp;
import com.calf.framework.warehouse.qry.UpframeQry;
import com.calf.framework.warehouse.services.UpframeService;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;


public class UpframeAction extends BaseAction {

		UpframeService upframeService;

		UpframeQry qry;

		Page page;

		TbWhUp entity;
		
		/**
		 * 列表
		 */
		@RequiresPermissions(value = "wh:upframe:view",requiresUser=true)
		public String list() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if(StringUtils.isNotBlank(super.qryHex)){
				qry = (UpframeQry)ObjectUtils.getObjectFromHex(qryHex);
			}
			if(qry==null){
				qry = new UpframeQry();
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			//设置默认排序号
			if(StringUtils.isBlank(qry.getOrderCol())){
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			qry.setUserInfo(userInfo);
			page = upframeService.findUpframePage(qry);
			return "list";
		}
		/**
		 * 新增
		 **/
		@RequiresPermissions(value = "wh:upframe:edit",requiresUser=true)
		public String toAdd() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = new TbWhUp();
			super.event="ADD";
			super.title="新增验货";
			return "edit";
		}
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:upframe:edit",requiresUser=true)
		public String toEdit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = upframeService.get(TbWhUp.class,entity.getUpId());
			super.event="EDIT";
			super.title="修改验货";
			return "edit";
		}
		/**
		 * 修改动作
		 **/
		@RequiresPermissions(value = "wh:upframe:edit",requiresUser=true)
		public String edit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if("ADD".equals(super.event)){
				//新增操作
				entity.setUpId(null);				
				entity.setDataStatus(Constants.YES);
				entity.setCreateUser(userInfo.getUserId());
				entity.setCreateDate(new Date());
				upframeService.saveUpframe(entity);
				super.addAttribute("qry.orderCol", "createDate");
				super.addAttribute("qry.orderType", "0");
				super.saveMessage("验货新增成功");
			}else if("EDIT".equals(super.event)){
				//修改操作
				TbWhUp db = upframeService.get(TbWhUp.class,entity.getUpId());
				
				db.setUpId(entity.getUpId());
				db.setInspectionId(entity.getInspectionId());
				db.setOrderId(entity.getOrderId());
				db.setWhId(entity.getWhId());
				db.setDeptId(entity.getDeptId());
				db.setRemarks(entity.getRemarks());
				
				db.setUpdateUser(userInfo.getUserId());
				db.setUpdateDate(new Date());
				
				upframeService.saveUpframe(db);
				super.saveMessage("验货保存成功");
				super.addAttribute("qryHex", super.qryHex);
			}
			super.redirectUrl = "/warehouse/upframe_list.action";
			return super.GLOBAL_SUCCESS;
		}
		/**
		 * 删除
		 **/
		@RequiresPermissions(value = "wh:upframe:edit",requiresUser=true)
		public String delete() throws Exception{			
			AdminUserInfo userInfo = super.getUserInfo();
			entity = upframeService.get(TbWhUp.class,entity.getUpId());
			if(entity.isCanDelete()){
				this.upframeService.deleteUpframe(entity);
				super.renderJsonSuccess("删除成功!");
			}else{
				super.renderJsonError("该记录已被删除!");
			}
			return null;
		}
		
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:upframe:view",requiresUser=true)
		public String toView() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = upframeService.get(TbWhUp.class,entity.getUpId());
			super.title="验货详细信息";
			return "view";
		}
		
		/**
		 * 判断是否唯一
		 * @return
		 */
		public String checkUnique()throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			boolean isCorrect = upframeService.isUnique(entity.getUpId());
			super.rendText(String.valueOf(isCorrect));
			return null;
		}
		
		public UpframeQry getQry(){
			return this.qry;
		}
		public void setQry(UpframeQry qry){
			this.qry = qry;
		}
		
		public UpframeService getUpframeService(){
			return this.upframeService;
		}
		public void setUpframeService(UpframeService upframeService){
			this.upframeService = upframeService;
		}
		public Page getPage() {
			return this.page;
		}
		public void setPage(Page page) {
			this.page = page;
		}
		public TbWhUp getEntity(){
			return entity;
		}
		public void setEntity(TbWhUp entity){
			this.entity = entity;
		}
}