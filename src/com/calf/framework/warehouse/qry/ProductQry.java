package com.calf.framework.warehouse.qry;

import java.io.Serializable;

import com.calf.framework.util.BaseAdminQuery;

public class ProductQry extends BaseAdminQuery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1937084884877261956L;

	/**
	 * 所属分类
	 **/
	Long classId;
	/**
	 * 商品编码
	 **/
	String productCode;
	/**
	 * 商品名称
	 **/
	String productName;
	/**
	 * 编码或者名字
	 */
	String codeOrName;
	/**
	 * 委托货主
	 **/
	Long trustId;
	/**
	 * 供应商
	 **/
	Long vendorId;

	public Long getClassId() {
		return this.classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getTrustId() {
		return this.trustId;
	}

	public void setTrustId(Long trustId) {
		this.trustId = trustId;
	}

	public Long getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getCodeOrName() {
		return codeOrName;
	}

	public void setCodeOrName(String codeOrName) {
		this.codeOrName = codeOrName;
	}

}