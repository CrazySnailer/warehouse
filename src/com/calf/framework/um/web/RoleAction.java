package com.calf.framework.um.web;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.calf.framework.um.entity.TbSysRole;
import com.calf.framework.um.qry.RoleQry;
import com.calf.framework.um.services.RoleService;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;

public class RoleAction extends BaseAction {
	/**
	 * 
	 */
	RoleService roleService;
	/**
	 * 查询对象
	 */
	RoleQry qry;
	/**
	 * 分页对象
	 **/
	Page page;
	/**
	 * 实体类
	 */
	TbSysRole entity;

	/**
	 * 列表
	 */
	@RequiresPermissions(value = "um:role:view",requiresUser=true)
	public String list() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if (StringUtils.isNotBlank(super.qryHex)) {
			qry = (RoleQry) ObjectUtils.getObjectFromHex(qryHex);
		}
		if (qry == null) {
			qry = new RoleQry();
		}
		qry.setUserInfo(userInfo);
		page = roleService.findRolePage(qry);
		return "list";
	}

	/**
	 * 新增
	 **/
	@RequiresPermissions(value = "um:role:edit",requiresUser=true)
	public String toAdd() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = new TbSysRole();
		super.event = "ADD";
		super.title = "新增角色";
		return "edit";
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "um:role:edit",requiresUser=true)
	public String toEdit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = roleService.get(entity.getRoleId());
		super.event = "EDIT";
		super.title = "修改角色";
		return "edit";
	}

	/**
	 * 修改动作
	 **/
	@RequiresPermissions(value = "um:role:edit",requiresUser=true)
	public String edit() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		if ("ADD".equals(super.event)) {
			// 新增操作
			entity.setRoleId(null);
			entity.setDataStatus("1");
			entity.setCreateUser(userInfo.getUserId());
			entity.setCreateDate(new Date());
			roleService.add(entity);
			super.saveMessage("角色管理新增成功");
		} else if ("EDIT".equals(super.event)) {
			// 修改操作
			TbSysRole db = roleService.get(entity.getRoleId());

			db.setRoleName(entity.getRoleName());
			db.setRoleLevel(entity.getRoleLevel());
			db.setNote(entity.getNote());

			entity.setUpdateUser(userInfo.getUserId());
			entity.setUpdateDate(new Date());

			roleService.update(db);
			super.saveMessage("角色管理修改成功");
			super.addAttribute("qryHex", super.qryHex);
		}
		super.redirectUrl = "/admin/um/role_list.action";
		return super.GLOBAL_SUCCESS;
	}

	/**
	 * 删除
	 **/
	@RequiresPermissions(value = "um:role:edit",requiresUser=true)
	public String delete() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = roleService.get(entity.getRoleId());
		this.roleService.delete(entity);
		super.renderJsonSuccess("删除成功!");
		return null;
	}

	/**
	 * 修改
	 **/
	@RequiresPermissions(value = "um:role:view",requiresUser=true)
	public String toView() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		entity = roleService.get(entity.getRoleId());
		super.title = "角色管理详细信息";
		return "view";
	}
	
	/**
	 * 保存角色权限信息 
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions(value = "um:role:edit",requiresUser=true)
	public String editRolePriv() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		Assert.notNull(this.entity);
		Assert.notNull(this.entity.getRoleId());
		String[] chkIds = super.getValuesFromRequest("chkIds");
		this.roleService.saveRolePriv(entity.getRoleId(), chkIds,userInfo.getUserId());
		super.renderJsonSuccess("保存成功");
		return null;
	}

	public RoleQry getQry() {
		return this.qry;
	}

	public void setQry(RoleQry qry) {
		this.qry = qry;
	}

	public RoleService getRoleService() {
		return this.roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public TbSysRole getEntity() {
		return entity;
	}

	public void setEntity(TbSysRole entity) {
		this.entity = entity;
	}
}