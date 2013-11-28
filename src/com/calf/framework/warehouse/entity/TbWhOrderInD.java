package com.calf.framework.warehouse.entity;

import com.calf.framework.util.FormateUtil;

public class TbWhOrderInD implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 721024338007789556L;

	/**
	 * 主键 订单字表ID
	 **/
	Long orderDId;
	/**
	 * 订单
	 */
	TbWhOrderInM order;
	/**
	 * 商品ID
	 **/
	TbWhProduct product;
	/**
	 * 数量
	 **/
	Long qty;
	/**
	 * 价值
	 **/
	Double productValue;
	/**
	 * 金额
	 **/
	Double amt;
	/**
	 * 备注
	 **/
	String remarks;

	public TbWhOrderInD() {
	}

	public Long getOrderDId() {
		return this.orderDId;
	}

	public void setOrderDId(Long orderDId) {
		this.orderDId = orderDId;
	}

	public Long getQty() {
		return this.qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public TbWhProduct getProduct() {
		return product;
	}

	public void setProduct(TbWhProduct product) {
		this.product = product;
	}

	public Double getProductValue() {
		return this.productValue;
	}

	public void setProductValue(Double productValue) {
		this.productValue = productValue;
	}

	public Double getAmt() {
		return this.amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public TbWhOrderInM getOrder() {
		return order;
	}

	public void setOrder(TbWhOrderInM order) {
		this.order = order;
	}

	public String getProductValueStr() {
		return FormateUtil.getInstance().formateDouble(this.productValue);
	}

	public String getAmtStr() {
		if(this.productValue!=null&&this.qty!=null){
			return FormateUtil.getInstance().formateDouble(this.productValue*this.qty);
		}else{
			return "0.00";
		}
	}

}