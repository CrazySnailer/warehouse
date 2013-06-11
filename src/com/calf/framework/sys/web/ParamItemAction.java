package com.calf.framework.sys.web;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.sys.services.ParamItemService;
import com.calf.framework.sys.services.ParamKindService;
import com.calf.framework.um.entity.TbSysParamItem;
import com.calf.framework.um.qry.ParamItemQry;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;

public class ParamItemAction extends BaseAction {
	/**
	 * 
	 */
	ParamItemService paramItemService;
	/**
	 * 参数类别服务
	 */
	ParamKindService paramKindService;
	/**
	 * 查询对象
	 */
	ParamItemQry qry;

	/**
	 * 分页对象
	 **/
	Page page;
	/**
	 * 实体类
	 */
	TbSysParamItem entity;

	@RequiresPermissions(value = "sys:param:view",requiresUser=true)
	public String index() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		return "index";
	}

	/**
	 * 列表
	 */
	@RequiresPermissions(value = "sys:param:view",requiresUser=true)
	public String list() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if (StringUtils.isNotBlank(super.qryHex)) {
			qry = (ParamItemQry) ObjectUtils.getObjectFromHex(qryHex);
		}
		if (qry == null) {
			qry = new ParamItemQry();
		}
		qry.setUserInfo(userInfo);
		page = paramItemService.findParamItemPage(qry);
		return "list";
	}

	/**
	 * 新增
	 **/
	@RequiresPermissions(value = "sys:param:edit",requiresUser=true)
	public String toAdd() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if(entity==null){
			entity = new TbSysParamItem();
		}
		super.event = "ADD";
		super.title = "新增";
		return "edit";
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "sys:param:edit",requiresUser=true)
	public String toEdit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = paramItemService.get(entity.getRecId());
		super.event = "EDIT";
		super.title = "修改";
		return "edit";
	}

	/**
	 * 修改动作
	 **/
	@RequiresPermissions(value = "sys:param:edit",requiresUser=true)
	public String edit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if ("ADD".equals(super.event)) {
			// 新增操作
			entity.setRecId(null);
			entity.setDataStatus("1");
			entity.setCreateUser(userInfo.getUserId());
			entity.setCreateDate(new Date());
			paramItemService.add(entity);
			super.saveMessage("参数设置新增成功");;
		} else if ("EDIT".equals(super.event)) {
			// 修改操作
			TbSysParamItem db = paramItemService.get(entity.getRecId());

			db.setParamKindCode(entity.getParamKindCode());
			db.setParamCode(entity.getParamCode());
			db.setParamName(entity.getParamName());
			db.setDataStatus(entity.getDataStatus());
			db.setNote(entity.getNote());

			entity.setUpdateUser(userInfo.getUserId());
			entity.setUpdateDate(new Date());

			paramItemService.update(db);
			super.saveMessage("参数设置修改成功");
			super.addAttribute("qryHex", super.qryHex);
		}
		super.redirectUrl = "/admin/param/item_list.action";
		return super.GLOBAL_SUCCESS;
	}

	/**
	 * 删除
	 **/
	@RequiresPermissions(value = "sys:param:edit",requiresUser=true)
	public String delete() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = paramItemService.get(entity.getRecId());
		this.paramItemService.delete(entity);
		super.renderJsonSuccess("删除成功!");
		return null;
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "sys:param:view",requiresUser=true)
	public String toView() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = paramItemService.get(entity.getRecId());
		super.title = "参数设置详细信息";
		return "view";
	}

	public ParamItemQry getQry() {
		return this.qry;
	}

	public void setQry(ParamItemQry qry) {
		this.qry = qry;
	}

	public ParamItemService getParamItemService() {
		return this.paramItemService;
	}

	public void setParamItemService(ParamItemService paramItemService) {
		this.paramItemService = paramItemService;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public TbSysParamItem getEntity() {
		return entity;
	}

	public void setEntity(TbSysParamItem entity) {
		this.entity = entity;
	}

}