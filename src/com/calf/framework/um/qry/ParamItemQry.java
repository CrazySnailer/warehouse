package com.calf.framework.um.qry;

import java.io.Serializable;

import com.calf.framework.util.BaseAdminQuery;

public class ParamItemQry extends BaseAdminQuery implements Serializable {

	private static final long serialVersionUID = -2572732745879657833L;

	/**
	 * 参数类别
	 **/
	String paramKindCode;
	/**
	 * 参数编码
	 **/
	String paramCode;

	public String getParamKindCode() {
		return this.paramKindCode;
	}

	public void setParamKindCode(String paramKindCode) {
		this.paramKindCode = paramKindCode;
	}

	public String getParamCode() {
		return this.paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

}