package com.calf.framework.warehouse.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbWhInspectionD implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7210248768007789556L;

	/**
	 * 主键 验货单子表ID
	 **/
	Long inspectionDId;
	/**
	 * 验货单ID
	 **/
	Long inspectionId;
	/**
	 * 订单子表ID
	 **/
	Long orderDId;
	/**
	 * 商品ID
	 **/
	Long productId;
	/**
	 * 订单结余数量
	 **/
	Long orderQty;
	/**
	 * 本次验货数量
	 **/
	Long qty;
	/**
	 * 批次
	 **/
	String batchNo;
	/**
	 * 生产日期
	 **/
	Date madeDate;
	/**
	 * 过期日期
	 **/
	Date exceedDate;
	/**
	 * 更新时间
	 **/
	Date updateDate;
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
	Date updateDate2;

	public TbWhInspectionD() {
	}

	public Long getInspectionDId() {
		return this.inspectionDId;
	}

	public void setInspectionDId(Long inspectionDId) {
		this.inspectionDId = inspectionDId;
	}

	public Long getInspectionId() {
		return this.inspectionId;
	}

	public void setInspectionId(Long inspectionId) {
		this.inspectionId = inspectionId;
	}

	public Long getOrderDId() {
		return this.orderDId;
	}

	public void setOrderDId(Long orderDId) {
		this.orderDId = orderDId;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getOrderQty() {
		return this.orderQty;
	}

	public void setOrderQty(Long orderQty) {
		this.orderQty = orderQty;
	}

	public Long getQty() {
		return this.qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getMadeDate() {
		return this.madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public Date getExceedDate() {
		return this.exceedDate;
	}

	public void setExceedDate(Date exceedDate) {
		this.exceedDate = exceedDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	public Date getUpdateDate2() {
		return this.updateDate2;
	}

	public void setUpdateDate2(Date updateDate2) {
		this.updateDate2 = updateDate2;
	}

	public String getMadeDateStr() {
		return DateUtil.getInstance().dateToString(this.madeDate, DateUtil.patternA);
	}

	public String getExceedDateStr() {
		return DateUtil.getInstance().dateToString(this.exceedDate, DateUtil.patternA);
	}

	public String getUpdateDateStr() {
		return DateUtil.getInstance().dateToString(this.updateDate, DateUtil.patternA);
	}

	public String getCreateDateStr() {
		return DateUtil.getInstance().dateToString(this.createDate, DateUtil.patternA);
	}
}