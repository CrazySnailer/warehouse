package com.calf.framework.um.qry;

import java.io.Serializable;

import com.calf.framework.util.BaseAdminQuery;

public class DeptQry extends BaseAdminQuery implements Serializable {

	private static final long serialVersionUID = -1924261283194882411L;
	/**
	 * 查询类型  sch 学校  dept 机构
	 */
	String qryType;
	
	public String getQryType() {
		return qryType;
	}
	public void setQryType(String qryType) {
		this.qryType = qryType;
	}
}