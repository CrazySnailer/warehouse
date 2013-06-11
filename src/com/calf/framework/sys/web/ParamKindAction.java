package com.calf.framework.sys.web;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.sys.services.ParamKindService;
import com.calf.framework.um.entity.TbSysParamKind;
import com.calf.framework.um.qry.ParamKindQry;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;

public class ParamKindAction extends BaseAction {
	/**
	 * 
	 */
	ParamKindService paramKindService;
	/**
	 * 查询对象
	 */
	ParamKindQry qry;
	/**
	 * 分页对象
	 **/
	Page page;
	/**
	 * 实体类
	 */
	TbSysParamKind entity;

	/**
	 * 列表
	 */
	@RequiresPermissions(value = "sys:param:list",requiresUser=true)
	public String list() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if (StringUtils.isNotBlank(super.qryHex)) {
			qry = (ParamKindQry) ObjectUtils.getObjectFromHex(qryHex);
		}
		if (qry == null) {
			qry = new ParamKindQry();
		}
		qry.setUserInfo(userInfo);
		page = paramKindService.findParamKindPage(qry);
		return "list";
	}
	
	/**
	 * 列表
	 */
	@RequiresPermissions(value = "sys:param:list",requiresUser=true)
	public String kind() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if (StringUtils.isNotBlank(super.qryHex)) {
			qry = (ParamKindQry) ObjectUtils.getObjectFromHex(qryHex);
		}
		if (qry == null) {
			qry = new ParamKindQry();
		}
		qry.setUserInfo(userInfo);
		page = paramKindService.findParamKindPage(qry);
		return "kind";
	}

	/**
	 * 新增
	 **/
	@RequiresPermissions(value = "sys:param:edit",requiresUser=true)
	public String toAdd() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = new TbSysParamKind();
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
		entity = paramKindService.get(entity.getKindCode());
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
			entity.setCreateUser(userInfo.getUserId());
			entity.setCreateDate(new Date());
			entity.setDataStatus("1");
			paramKindService.add(entity);
			super.saveMessage("参数类别新增成功");
		} else if ("EDIT".equals(super.event)) {
			// 修改操作
			TbSysParamKind db = paramKindService.get(entity.getKindCode());
			db.setKindName(entity.getKindName());
			//entity.setUpdateUser(userInfo.getUserId());
			entity.setUpdateDate(new Date());

			paramKindService.update(db);
			super.saveMessage("参数类别修改成功");
			super.addAttribute("qryHex", super.qryHex);
		}
		super.redirectUrl = "/admin/param/kind_list.action";
		return super.GLOBAL_SUCCESS;
	}

	/**
	 * 删除
	 **/
	@RequiresPermissions(value = "sys:param:edit",requiresUser=true)
	public String delete() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = paramKindService.get(entity.getKindCode());
		this.paramKindService.delete(entity);
		super.renderJsonSuccess("删除成功!");
		return null;
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "sys:param:list",requiresUser=true)
	public String toView() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = paramKindService.get(entity.getKindCode());
		super.title = "参数分类详细信息";
		return "view";
	}

	public ParamKindQry getQry() {
		return this.qry;
	}

	public void setQry(ParamKindQry qry) {
		this.qry = qry;
	}

	public ParamKindService getParamKindService() {
		return this.paramKindService;
	}

	public void setParamKindService(ParamKindService paramKindService) {
		this.paramKindService = paramKindService;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public TbSysParamKind getEntity() {
		return entity;
	}

	public void setEntity(TbSysParamKind entity) {
		this.entity = entity;
	}
}