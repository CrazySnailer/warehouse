package com.calf.framework.warehouse.web;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.util.Constants;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.warehouse.entity.TbWhInspection;
import com.calf.framework.warehouse.qry.InspectionQry;
import com.calf.framework.warehouse.services.InspectionService;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;

/**
 * 验货操作
 *
 */
public class InspectionAction extends BaseAction {

	InspectionService inspectionService;

	InspectionQry qry;

	Page page;

	TbWhInspection entity;

	/**
	 * 列表
	 */
	@RequiresPermissions(value = "wh:inspect:view", requiresUser = true)
	public String list() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if (StringUtils.isNotBlank(super.qryHex)) {
			qry = (InspectionQry) ObjectUtils.getObjectFromHex(qryHex);
		}
		if (qry == null) {
			qry = new InspectionQry();
			qry.setOrderCol("createDate");
			qry.setOrderType(Constants.DESC);
		}

		// 设置默认排序号
		if (StringUtils.isBlank(qry.getOrderCol())) {
			qry.setOrderCol("createDate");
			qry.setOrderType(Constants.DESC);
		}

		qry.setUserInfo(userInfo);
		page = inspectionService.findInspectionPage(qry);
		return "list";
	}

	/**
	 * 新增
	 **/
	@RequiresPermissions(value = "wh:inspect:edit", requiresUser = true)
	public String toAdd() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = new TbWhInspection();
		super.event = "ADD";
		super.title = "新增验货";
		return "edit";
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "wh:inspect:edit", requiresUser = true)
	public String toEdit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = inspectionService.get(TbWhInspection.class, entity.getInspectionId());
		super.event = "EDIT";
		super.title = "修改验货";
		return "edit";
	}

	/**
	 * 修改动作
	 **/
	@RequiresPermissions(value = "wh:inspect:edit", requiresUser = true)
	public String edit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if ("ADD".equals(super.event)) {
			// 新增操作
			entity.setInspectionId(null);
			entity.setDataStatus(Constants.YES);
			entity.setCreateUser(userInfo.getUserId());
			entity.setCreateDate(new Date());
			inspectionService.saveInspection(entity);
			super.addAttribute("qry.orderCol", "createDate");
			super.addAttribute("qry.orderType", "0");
			super.saveMessage("验货新增成功");
		} else if ("EDIT".equals(super.event)) {
			// 修改操作
			TbWhInspection db = inspectionService.get(TbWhInspection.class, entity.getInspectionId());

			db.setInspectionId(entity.getInspectionId());
			db.setOrderId(entity.getOrderId());
			db.setInspectionNo(entity.getInspectionNo());
			db.setOrderNum(entity.getOrderNum());
			db.setWhId(entity.getWhId());
			db.setDeptId(entity.getDeptId());
			db.setRemarks(entity.getRemarks());

			db.setUpdateUser(userInfo.getUserId());
			db.setUpdateDate(new Date());

			inspectionService.saveInspection(db);
			super.saveMessage("验货保存成功");
			super.addAttribute("qryHex", super.qryHex);
		}
		super.redirectUrl = "/warehouse/inspection_list.action";
		return super.GLOBAL_SUCCESS;
	}

	/**
	 * 删除
	 **/
	@RequiresPermissions(value = "wh:inspect:edit", requiresUser = true)
	public String delete() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = inspectionService.get(TbWhInspection.class, entity.getInspectionId());
		if (entity.isCanDelete()) {
			this.inspectionService.deleteInspection(entity);
			super.renderJsonSuccess("删除成功!");
		} else {
			super.renderJsonError("该记录已被删除!");
		}
		return null;
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "wh:inspect:view", requiresUser = true)
	public String toView() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = inspectionService.get(TbWhInspection.class, entity.getInspectionId());
		super.title = "验货详细信息";
		return "view";
	}

	/**
	 * 判断是否唯一
	 * 
	 * @return
	 */
	public String checkUnique() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		boolean isCorrect = inspectionService.isUnique(entity.getInspectionId());
		super.rendText(String.valueOf(isCorrect));
		return null;
	}

	public InspectionQry getQry() {
		return this.qry;
	}

	public void setQry(InspectionQry qry) {
		this.qry = qry;
	}

	public InspectionService getInspectionService() {
		return this.inspectionService;
	}

	public void setInspectionService(InspectionService inspectionService) {
		this.inspectionService = inspectionService;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public TbWhInspection getEntity() {
		return entity;
	}

	public void setEntity(TbWhInspection entity) {
		this.entity = entity;
	}
}