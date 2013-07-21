package com.calf.framework.warehouse.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.calf.framework.util.Constants;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.entity.TbWhStruct;
import com.calf.framework.warehouse.qry.StructQry;
import com.calf.framework.warehouse.services.StructService;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;


public class StructAction extends BaseAction {

		StructService structService;

		StructQry qry;

		Page page;

		TbWhStruct entity;
		
		/**
		 * 列表对象
		 */
		List list;
		
		@RequiresPermissions(value = "wh:struct:view",requiresUser=true)
		public String index()throws Exception {
			AdminUserInfo userInfo = super.getUserInfo();
			return "index";
		}
	
		@RequiresPermissions(value = "wh:struct:view",requiresUser=true)
		public String tree()throws Exception {
			AdminUserInfo userInfo = super.getUserInfo();
			if (qry == null) {
				qry = new StructQry();
			}
			qry.setUserInfo(userInfo);
			list = structService.findTree(qry);
			return "tree";
		}
		
		/**
		 * 列表
		 */
		@RequiresPermissions(value = "wh:struct:view",requiresUser=true)
		public String list() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if (qry == null) {
				qry = new StructQry();
			}
			qry.setUserInfo(userInfo);
			list = structService.findListByParent(entity.getStructId());
			return "list";
		}
		
		@RequiresPermissions(value = "wh:struct:view",requiresUser=true)
		public String select()throws Exception {
			AdminUserInfo userInfo = super.getUserInfo();		
			if (qry == null) {
				qry = new StructQry();
			}
			qry.setUserInfo(userInfo);		
			list = structService.findTree(qry);
			return "select";
		}
		
		/**
		 * 新增
		 **/
		@RequiresPermissions(value = "wh:struct:edit",requiresUser=true)
		public String toAdd() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			Assert.notNull(entity.getParentId());//父菜单不能为空
			entity.setParent(this.structService.get(entity.getParentId()));
			super.event="ADD";
			super.title="新增";
			return "edit";
		}
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:struct:edit",requiresUser=true)
		public String toEdit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = structService.get(TbWhStruct.class,entity.getStructId());
			super.event="EDIT";
			super.title="修改";
			return "edit";
		}
		/**
		 * 修改动作
		 **/
		@RequiresPermissions(value = "wh:struct:edit",requiresUser=true)
		public String edit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if("ADD".equals(super.event)){
				//新增操作
				entity.setStructId(null);				
				entity.setDataStatus(Constants.YES);
				entity.setCreateUser(userInfo.getUserId());
				entity.setCreateDate(new Date());
				entity.setParent(this.structService.get(entity.getParentId()));
				structService.add(entity);
				super.addAttribute("qry.orderCol", "createDate");
				super.addAttribute("qry.orderType", "0");
				super.saveMessage("仓库结构新增成功");
			}else if("EDIT".equals(super.event)){
				//修改操作
				TbWhStruct db = structService.get(TbWhStruct.class,entity.getStructId());
				
				db.setParent(this.structService.get(entity.getParentId()));
				db.setStructId(entity.getStructId());
				db.setParentId(entity.getParentId());
				db.setStructCode(entity.getStructCode());
				db.setStructName(entity.getStructName());
				db.setWhId(entity.getWhId());
				db.setStructType(entity.getStructType());
				db.setOrderNum(entity.getOrderNum());
				db.setFullName(entity.getFullName());
				db.setTreeNo(entity.getTreeNo());
				
				db.setUpdateUser(userInfo.getUserId());
				db.setUpdateDate(new Date());
				
				structService.saveStruct(db,entity.getOrderNum().longValue()!=db.getOrderNum().longValue());
				super.saveMessage("仓库结构修改成功");
				super.addAttribute("qryHex", super.qryHex);
			}
			super.addAttribute("refresh", "true");
			super.redirectUrl = "/warehouse/struct_list.action?entity.structId="+entity.getParentId();
			return super.GLOBAL_SUCCESS;
		}
		/**
		 * 删除
		 **/
		@RequiresPermissions(value = "wh:struct:edit",requiresUser=true)
		public String delete() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = structService.get(TbWhStruct.class,entity.getStructId());
			Long parentId = entity.getParent().getStructId();
			String result = this.structService.delete(entity);
			if("OK".equals(result)){
				super.saveMessage("删除成功");
				super.redirectUrl = "/warehouse/struct_list.action?entity.structId="+parentId;
				super.addAttribute("refresh", "true");
				return super.GLOBAL_SUCCESS;
			}else if("ERR01".equals(result)){
				super.saveMessage("删除失败，不是叶子节点，不能进行删除,请先删除叶子节点！");
				return super.GLOBAL_ERROR;
			}else{
				super.saveMessage("未知错误!");
				return super.GLOBAL_ERROR;
			}
		}
		
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:struct:view",requiresUser=true)
		public String toView() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = structService.get(TbWhStruct.class,entity.getStructId());
			super.title="仓库结构详细信息";
			return "view";
		}
		
		/**
		 * 强制重建treeNo
		 **/
		@RequiresPermissions(value = "sys:admin:oper",requiresUser=true)
		public String forceBuild() throws Exception {
			AdminUserInfo userInfo = super.getUserInfo();
			entity = structService.get(entity.getStructId());
			structService.saveRebuild(entity);
			super.saveMessage("数据重建成功");
			super.redirectUrl = "/warehouse/struct_toEdit.action?entity.structId="+entity.getStructId();
			super.addAttribute("refresh", "true");
			return super.GLOBAL_SUCCESS;
		}
		
		public StructQry getQry(){
			return this.qry;
		}
		public void setQry(StructQry qry){
			this.qry = qry;
		}
		
		public StructService getStructService(){
			return this.structService;
		}
		public void setStructService(StructService structService){
			this.structService = structService;
		}
		public Page getPage() {
			return this.page;
		}
		public void setPage(Page page) {
			this.page = page;
		}
		public TbWhStruct getEntity(){
			return entity;
		}
		public void setEntity(TbWhStruct entity){
			this.entity = entity;
		}
		
		public List getList() {
			return list;
		}

		public void setList(List list) {
			this.list = list;
		}
}