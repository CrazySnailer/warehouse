package com.calf.framework.um.qry;

import java.io.Serializable;

import com.calf.framework.util.BaseAdminQuery;

public class ParamKindQry extends BaseAdminQuery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1586345914930425773L;
	
	/**
	 * 参数分类编码
	 **/
	String kindCode;
	/**
	 * 参数分类名称
	 **/
	String kindName;

	public String getKindCode() {
		return this.kindCode;
	}

	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}

	public String getKindName() {
		return this.kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

}