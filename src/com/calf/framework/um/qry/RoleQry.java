package com.calf.framework.um.qry;

import java.io.Serializable;

import com.calf.framework.util.BaseAdminQuery;

public class RoleQry extends BaseAdminQuery implements Serializable {

	private static final long serialVersionUID = -7179458611155179126L;

	/**
	 * 角色名称
	 **/
	String roleName;
	/**
	 * 角色级别 （1省教育局 2市教育局 3县教育局 4学校）
	 **/
	String roleLevel;

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

}