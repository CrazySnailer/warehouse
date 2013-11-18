package com.calf.framework.warehouse.web;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.util.Constants;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.entity.TbWhExpress;
import com.calf.framework.warehouse.qry.ExpressQry;
import com.calf.framework.warehouse.services.ExpressService;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;


public class ExpressAction extends BaseAction {

		ExpressService expressService;

		ExpressQry qry;

		Page page;

		TbWhExpress entity;
		
		/**
		 * 列表
		 */
		@RequiresPermissions(value = "wh:express:view",requiresUser=true)
		public String list() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if(StringUtils.isNotBlank(super.qryHex)){
				qry = (ExpressQry)ObjectUtils.getObjectFromHex(qryHex);
			}
			if(qry==null){
				qry = new ExpressQry();
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			//设置默认排序号
			if(StringUtils.isBlank(qry.getOrderCol())){
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			qry.setUserInfo(userInfo);
			page = expressService.findExpressPage(qry);
			return "list";
		}
		/**
		 * 新增
		 **/
		@RequiresPermissions(value = "wh:express:edit",requiresUser=true)
		public String toAdd() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = new TbWhExpress();
			super.event="ADD";
			super.title="新增物流公司";
			return "edit";
		}
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:express:edit",requiresUser=true)
		public String toEdit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = expressService.get(TbWhExpress.class,entity.getExpressId());
			super.event="EDIT";
			super.title="修改物流公司";
			return "edit";
		}
		/**
		 * 修改动作
		 **/
		@RequiresPermissions(value = "wh:express:edit",requiresUser=true)
		public String edit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if("ADD".equals(super.event)){
				//新增操作
				entity.setExpressId(null);				
				entity.setDataStatus(Constants.YES);
				entity.setCreateUser(userInfo.getUserId());
				entity.setCreateDate(new Date());
				expressService.saveExpress(entity);
				super.addAttribute("qry.orderCol", "createDate");
				super.addAttribute("qry.orderType", "0");
				super.saveMessage("物流公司新增成功");
			}else if("EDIT".equals(super.event)){
				//修改操作
				TbWhExpress db = expressService.get(TbWhExpress.class,entity.getExpressId());
				
				db.setExpressId(entity.getExpressId());
				db.setExpressName(entity.getExpressName());
				db.setLinker(entity.getLinker());
				db.setLinkerTel(entity.getLinkerTel());
				db.setLinkAddress(entity.getLinkAddress());
				db.setEmail(entity.getEmail());
				db.setPostcode(entity.getPostcode());
				db.setRemark(entity.getRemark());
				
				db.setUpdateUser(userInfo.getUserId());
				db.setUpdateDate(new Date());
				
				expressService.saveExpress(db);
				super.saveMessage("物流公司保存成功");
				super.addAttribute("qryHex", super.qryHex);
			}
			super.redirectUrl = "/warehouse/express_list.action";
			return super.GLOBAL_SUCCESS;
		}
		/**
		 * 删除
		 **/
		@RequiresPermissions(value = "wh:express:edit",requiresUser=true)
		public String delete() throws Exception{			
			AdminUserInfo userInfo = super.getUserInfo();
			entity = expressService.get(TbWhExpress.class,entity.getExpressId());
			if(entity.isCanDelete()){
				this.expressService.deleteExpress(entity);
				super.renderJsonSuccess("删除成功!");
			}else{
				super.renderJsonError("该记录已被删除!");
			}
			return null;
		}
		
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "wh:express:view",requiresUser=true)
		public String toView() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = expressService.get(TbWhExpress.class,entity.getExpressId());
			super.title="物流公司详细信息";
			return "view";
		}
		
		/**
		 * 判断是否唯一
		 * @return
		 */
		public String checkUnique()throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			boolean isCorrect = expressService.isUnique(entity.getExpressId());
			super.rendText(String.valueOf(isCorrect));
			return null;
		}
		
		public ExpressQry getQry(){
			return this.qry;
		}
		public void setQry(ExpressQry qry){
			this.qry = qry;
		}
		
		public ExpressService getExpressService(){
			return this.expressService;
		}
		public void setExpressService(ExpressService expressService){
			this.expressService = expressService;
		}
		public Page getPage() {
			return this.page;
		}
		public void setPage(Page page) {
			this.page = page;
		}
		public TbWhExpress getEntity(){
			return entity;
		}
		public void setEntity(TbWhExpress entity){
			this.entity = entity;
		}
}