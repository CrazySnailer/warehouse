package com.calf.framework.um.qry;

import java.io.Serializable;

import com.calf.framework.util.BaseAdminQuery;

public class UserQry extends BaseAdminQuery implements Serializable {

	private static final long serialVersionUID = -752498364247517589L;

	/**
	 * 登录名
	 **/
	String userCode;
	/**
	 * 用户名
	 **/
	String userName;
	/**
	 * 手机
	 **/
	String mobile;	
	/**
	 * 机构ID
	 */
	Long deptId;
	/**
	 * 机构名称
	 */
	String deptName;
	/**
	 * 树编码
	 */
	String treeNo;

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getTreeNo() {
		return treeNo;
	}

	public void setTreeNo(String treeNo) {
		this.treeNo = treeNo;
	}

}