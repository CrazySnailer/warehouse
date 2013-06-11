package com.calf.framework.um.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbSysRole implements java.io.Serializable {
	private static final long serialVersionUID = -7797685495543919539L;
	/**
	 * pk 角色ID
	 **/
	Long roleId;
	/**
	 * 角色名称
	 **/
	String roleName;
	/**
	 * 角色级别 （1省教育局 2市教育局 3县教育局 4学校）
	 **/
	String roleLevel;
	/**
	 * 备注
	 **/
	String note;
	/**
	 * 数据状态
	 **/
	String dataStatus;
	/**
	 * 创建人
	 **/
	Long createUser;
	/**
	 * 创建时间
	 **/
	Date createDate;
	/**
	 * 更新人
	 **/
	Long updateUser;
	/**
	 * 更新时间
	 **/
	Date updateDate;

	public TbSysRole() {
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleLevel() {
		return this.roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDataStatus() {
		return this.dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Long getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateDateStr() {
		return DateUtil.getInstance().dateToString(this.createDate,
				DateUtil.patternA);
	}

	public String getUpdateDateStr() {
		return DateUtil.getInstance().dateToString(this.updateDate,
				DateUtil.patternA);
	}

	public String getRoleLevelStr() {
		return FormateUtil.getInstance().getNameByCode("g_um_dept_level",
				roleLevel);
	}

	public String getDataStatusStr() {
		return FormateUtil.getInstance().getNameByCode("g_pu_data_stauts",
				dataStatus);
	}
}