package com.calf.framework.warehouse.qry;

import java.io.Serializable;

import com.calf.framework.util.BaseAdminQuery;

public class OrderinQry extends BaseAdminQuery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1937084884877261956L;

	/**
	 * 订单单号
	 **/
	String orderNo;
	/**
	 * 订单类型
	 **/
	String orderInType;
	/**
	 * 客户单号
	 **/
	String custOrderNo;
	/**
	 * 仓库
	 **/
	Long whId;
	/**
	 * 供应商
	 **/
	Long vendorId;
	/**
	 * 委托货主
	 **/
	Long trustId;
	/**
	 * 联系人
	 **/
	String linker;

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderInType() {
		return orderInType;
	}

	public void setOrderInType(String orderInType) {
		this.orderInType = orderInType;
	}

	public String getCustOrderNo() {
		return this.custOrderNo;
	}

	public void setCustOrderNo(String custOrderNo) {
		this.custOrderNo = custOrderNo;
	}

	public Long getWhId() {
		return this.whId;
	}

	public void setWhId(Long whId) {
		this.whId = whId;
	}

	public Long getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public Long getTrustId() {
		return this.trustId;
	}

	public void setTrustId(Long trustId) {
		this.trustId = trustId;
	}

	public String getLinker() {
		return this.linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

}