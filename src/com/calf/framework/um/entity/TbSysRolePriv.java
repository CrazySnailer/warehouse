package com.calf.framework.um.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;

public class TbSysRolePriv implements java.io.Serializable {
	private static final long serialVersionUID = -7734182964616253326L;
	
	public static final String ROLE_TYPE_ROLE = "R";
	public static final String ROLE_TYPE_DEPT = "D";
	public static final String ROLE_TYPE_USER = "U";
	
	/**
	 * pk null
	 **/
	Long recId;
	TbSysMenu menu;
	/**
	 * 赋值类型 R角色 D机构 U用户
	 **/
	String relType;
	/**
	 * 角色ID
	 **/
	Long roleId;
	/**
	 * 部门ID
	 **/
	Long deptId;
	/**
	 * 用户ID
	 **/
	Long userId;
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

	public TbSysRolePriv() {
	}

	public Long getRecId() {
		return this.recId;
	}

	public void setRecId(Long recId) {
		this.recId = recId;
	}

	public TbSysMenu getMenu() {
		return menu;
	}

	public void setMenu(TbSysMenu menu) {
		this.menu = menu;
	}

	public String getRelType() {
		return this.relType;
	}

	public void setRelType(String relType) {
		this.relType = relType;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

}