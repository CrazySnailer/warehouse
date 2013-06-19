package com.calf.framework.warehouse.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.calf.framework.util.Constants;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.entity.TbWhProductClass;
import com.calf.framework.warehouse.qry.ProductClassQry;
import com.calf.framework.warehouse.services.ProductClassService;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;


public class ProductClassAction extends BaseAction {

		ProductClassService productClassService;

		ProductClassQry qry;

		Page page;

		TbWhProductClass entity;
		
		/**
		 * 列表对象
		 */
		List list;
		
		@RequiresPermissions(value = "wh:productClass:view",requiresUser=true)
		public String index()throws Exception {
			AdminUserInfo userInfo = super.getUserInfo();
			return "index";
		}
	
		@RequiresPermissions(value = "wh:productClass:view",requiresUser=true)
		public String tree()throws Exception {
			AdminUserInfo userInfo = super.getUserInfo();
			if (qry == null) {
				qry = new ProductClassQry();
			}
			qry.setUserInfo(userInfo);
			list = productClassService.findTree(qry);
			return "tree";
		}
		
		/**
		 * 列表
		 */
		@RequiresPermissions(value = "wh:productClass:view",requiresUser=true)
		public String list() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if (qry == null) {
				qry = new ProductClassQry();
			}
			qry.setUserInfo(userInfo);
			list = productClassService.findListByParent(entity.getClassId());
			return "list";
		}
		
		@RequiresPermissions(value = "wh:productClass:view",requiresUser=true)
		public String select()throws Exception {
			AdminUserInfo userInfo = super.getUserInfo();		
			if (qry == null) {
				qry = new ProductClassQry();
			}
			qry.setUserInfo(userInfo);		
			list = productClassService.findTree(qry);
			return "select";
		}
		
		/**
		 * 新增
		 **/
		@RequiresPermissions(value = "wh:productClass:edit",requiresUser=true)
		public String toAdd() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			Assert.notNull(entity.getParentId());//父菜单不能为空
			entity.setParent(this.productClassService.get(entity.getParentId()));
			super.event="ADD";
			super.title="新增";
			return "edit";
		}
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:productClass:edit",requiresUser=true)
		public String toEdit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = productClassService.get(TbWhProductClass.class,entity.getClassId());
			super.event="EDIT";
			super.title="修改";
			return "edit";
		}
		/**
		 * 修改动作
		 **/
		@RequiresPermissions(value = "wh:productClass:edit",requiresUser=true)
		public String edit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if("ADD".equals(super.event)){
				//新增操作
				entity.setClassId(null);				
				entity.setDataStatus(Constants.YES);
				entity.setCreateUser(userInfo.getUserId());
				entity.setCreateDate(new Date());
				entity.setParent(this.productClassService.get(entity.getParentId()));
				productClassService.add(entity);
				super.addAttribute("qry.orderCol", "createDate");
				super.addAttribute("qry.orderType", "0");
				super.saveMessage("商品分类新增成功");
			}else if("EDIT".equals(super.event)){
				//修改操作
				TbWhProductClass db = productClassService.get(TbWhProductClass.class,entity.getClassId());
				
				boolean refreshTreeNo = entity.getOrderNum().longValue()!=db.getOrderNum().longValue();						
				db.setParent(this.productClassService.get(entity.getParentId()));
				db.setClassCode(entity.getClassCode());
				db.setClassName(entity.getClassName());
				db.setOrderNum(entity.getOrderNum());
				
				db.setUpdateUser(userInfo.getUserId());
				db.setUpdateDate(new Date());
				
				productClassService.saveProductClass(db,refreshTreeNo);
				super.saveMessage("商品分类修改成功");
				super.addAttribute("qryHex", super.qryHex);
			}
			super.addAttribute("refresh", "true");
			super.redirectUrl = "/warehouse/productclass_list.action?entity.classId="+entity.getParentId();
			return super.GLOBAL_SUCCESS;
		}
		/**
		 * 删除
		 **/
		@RequiresPermissions(value = "wh:productClass:edit",requiresUser=true)
		public String delete() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = productClassService.get(TbWhProductClass.class,entity.getClassId());
			Long parentId = entity.getParent().getClassId();
			String result = this.productClassService.delete(entity);
			if("OK".equals(result)){
				super.saveMessage("删除成功");
				super.redirectUrl = "/warehouse/productclass_list.action?entity.classId="+parentId;
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
		@RequiresPermissions(value = "wh:productClass:view",requiresUser=true)
		public String toView() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = productClassService.get(TbWhProductClass.class,entity.getClassId());
			super.title="商品分类详细信息";
			return "view";
		}
		
		/**
		 * 强制重建treeNo
		 **/
		@RequiresPermissions(value = "sys:admin:oper",requiresUser=true)
		public String forceBuild() throws Exception {
			AdminUserInfo userInfo = super.getUserInfo();
			entity = productClassService.get(entity.getClassId());
			productClassService.saveRebuild(entity);
			super.saveMessage("数据重建成功");
			super.redirectUrl = "/warehouse/productclass_toEdit.action?entity.classId="+entity.getClassId();
			super.addAttribute("refresh", "true");
			return super.GLOBAL_SUCCESS;
		}
		
		public ProductClassQry getQry(){
			return this.qry;
		}
		public void setQry(ProductClassQry qry){
			this.qry = qry;
		}
		
		public ProductClassService getProductClassService(){
			return this.productClassService;
		}
		public void setProductClassService(ProductClassService productClassService){
			this.productClassService = productClassService;
		}
		public Page getPage() {
			return this.page;
		}
		public void setPage(Page page) {
			this.page = page;
		}
		public TbWhProductClass getEntity(){
			return entity;
		}
		public void setEntity(TbWhProductClass entity){
			this.entity = entity;
		}

		public List getList() {
			return list;
		}

		public void setList(List list) {
			this.list = list;
		}
}