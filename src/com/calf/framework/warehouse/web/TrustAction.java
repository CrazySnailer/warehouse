package com.calf.framework.warehouse.web;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.util.Constants;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.entity.TbWhTrust;
import com.calf.framework.warehouse.qry.TrustQry;
import com.calf.framework.warehouse.services.TrustService;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;


public class TrustAction extends BaseAction {

		TrustService trustService;

		TrustQry qry;

		Page page;

		TbWhTrust entity;
		
		/**
		 * 列表
		 */
		@RequiresPermissions(value = "wh:trust:view",requiresUser=true)
		public String list() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if(StringUtils.isNotBlank(super.qryHex)){
				qry = (TrustQry)ObjectUtils.getObjectFromHex(qryHex);
			}
			if(qry==null){
				qry = new TrustQry();
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			//设置默认排序号
			if(StringUtils.isBlank(qry.getOrderCol())){
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			qry.setUserInfo(userInfo);
			page = trustService.findTrustPage(qry);
			return "list";
		}
		/**
		 * 新增
		 **/
		@RequiresPermissions(value = "wh:trust:edit",requiresUser=true)
		public String toAdd() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = new TbWhTrust();
			super.event="ADD";
			super.title="新增";
			return "edit";
		}
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:trust:edit",requiresUser=true)
		public String toEdit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = trustService.get(TbWhTrust.class,entity.getTrustId());
			super.event="EDIT";
			super.title="修改";
			return "edit";
		}
		/**
		 * 修改动作
		 **/
		@RequiresPermissions(value = "wh:trust:edit",requiresUser=true)
		public String edit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if("ADD".equals(super.event)){
				//新增操作
				entity.setTrustId(null);				
				entity.setDataStatus(Constants.YES);
				entity.setCreateUser(userInfo.getUserId());
				entity.setCreateDate(new Date());
				trustService.saveTrust(entity);
				super.addAttribute("qry.orderCol", "createDate");
				super.addAttribute("qry.orderType", "0");
				super.saveMessage("委托货主新增成功");
			}else if("EDIT".equals(super.event)){
				//修改操作
				TbWhTrust db = trustService.get(TbWhTrust.class,entity.getTrustId());
				
				db.setTrustCode(entity.getTrustCode());
				db.setTrustName(entity.getTrustName());
				db.setShortName(entity.getShortName());
				db.setLinker(entity.getLinker());
				db.setLinkerTel(entity.getLinkerTel());
				db.setAddress(entity.getAddress());
				db.setEmail(entity.getEmail());
				db.setPostcode(entity.getPostcode());
				db.setRemark(entity.getRemark());
				
				db.setUpdateUser(userInfo.getUserId());
				db.setUpdateDate(new Date());
				
				trustService.saveTrust(db);
				super.saveMessage("委托货主修改保存成功");
				super.addAttribute("qryHex", super.qryHex);
			}
			super.redirectUrl = "/warehouse/trust_list.action";
			return super.GLOBAL_SUCCESS;
		}
		/**
		 * 删除
		 **/
		@RequiresPermissions(value = "wh:trust:edit",requiresUser=true)
		public String delete() throws Exception{			
			AdminUserInfo userInfo = super.getUserInfo();
			entity = trustService.get(TbWhTrust.class,entity.getTrustId());
			if(entity.isCanDelete()){
				this.trustService.deleteTrust(entity);
				super.renderJsonSuccess("删除成功!");
			}else{
				super.renderJsonError("该记录已被删除!");
			}
			return null;
		}
		
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:trust:view",requiresUser=true)
		public String toView() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = trustService.get(TbWhTrust.class,entity.getTrustId());
			super.title="委托货主详细信息";
			return "view";
		}
		
		/**
		 * 判断是否唯一
		 * @return
		 */
		public String checkUnique()throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			boolean isCorrect = trustService.isUnique(entity.getTrustId());
			super.rendText(String.valueOf(isCorrect));
			return null;
		}
		
		/**
		 * 选择供应商
		 * @return
		 * @throws Exception
		 */
		@RequiresPermissions(value = "wh:trust:view",requiresUser=true)
		public String select()throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if(StringUtils.isNotBlank(super.qryHex)){
				qry = (TrustQry)ObjectUtils.getObjectFromHex(qryHex);
			}
			if(qry==null){
				qry = new TrustQry();
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			//设置默认排序号
			if(StringUtils.isBlank(qry.getOrderCol())){
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			qry.setUserInfo(userInfo);
			page = trustService.findTrustPage(qry);
			return "select";
		}
		
		public TrustQry getQry(){
			return this.qry;
		}
		public void setQry(TrustQry qry){
			this.qry = qry;
		}
		
		public TrustService getTrustService(){
			return this.trustService;
		}
		public void setTrustService(TrustService trustService){
			this.trustService = trustService;
		}
		public Page getPage() {
			return this.page;
		}
		public void setPage(Page page) {
			this.page = page;
		}
		public TbWhTrust getEntity(){
			return entity;
		}
		public void setEntity(TbWhTrust entity){
			this.entity = entity;
		}
}