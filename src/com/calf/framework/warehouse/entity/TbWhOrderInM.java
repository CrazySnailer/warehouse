package com.calf.framework.warehouse.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbWhOrderInM implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7210243768007789556L;

	/**
	 * 主键 订单ID
	 **/
	Long orderId;
	/**
	 * 订单单号
	 **/
	String orderNo;
	/**
	 * 订单类型
	 **/
	String orderType;
	/**
	 * 订单日期
	 **/
	Date orderDate;
	/**
	 * 订单处理时限
	 **/
	Long limitHours;
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
	/**
	 * 联系电话
	 **/
	String linkerTel;
	/**
	 * 备注
	 **/
	String remarks;
	/**
	 * 经办人
	 **/
	Long handleId;
	/**
	 * 经办部门
	 **/
	Long handleDept;
	/**
	 * 订单来源
	 **/
	String orderSource;
	/**
	 * 审核时间
	 **/
	Date auditDate;
	/**
	 * 审核人
	 **/
	Long auditId;
	/**
	 * 数据状态
	 **/
	String dataStatus;
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

	public TbWhOrderInM() {
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getLimitHours() {
		return this.limitHours;
	}

	public void setLimitHours(Long limitHours) {
		this.limitHours = limitHours;
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

	public String getLinkerTel() {
		return this.linkerTel;
	}

	public void setLinkerTel(String linkerTel) {
		this.linkerTel = linkerTel;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getHandleId() {
		return this.handleId;
	}

	public void setHandleId(Long handleId) {
		this.handleId = handleId;
	}

	public Long getHandleDept() {
		return this.handleDept;
	}

	public void setHandleDept(Long handleDept) {
		this.handleDept = handleDept;
	}

	public String getOrderSource() {
		return this.orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public Date getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public Long getAuditId() {
		return this.auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public String getDataStatus() {
		return this.dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
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

	public String getOrderDateStr() {
		return DateUtil.getInstance().dateToString(this.orderDate,
				DateUtil.patternA);
	}

	public String getAuditDateStr() {
		return DateUtil.getInstance().dateToString(this.auditDate,
				DateUtil.patternA);
	}

	public String getCreateDateStr() {
		return DateUtil.getInstance().dateToString(this.createDate,
				DateUtil.patternA);
	}

	public String getUpdateDateStr() {
		return DateUtil.getInstance().dateToString(this.updateDate,
				DateUtil.patternA);
	}

	public String getOrderTypeStr() {
		return FormateUtil.getInstance().getNameByCode("wh_orderin_type",
				orderType);
	}

	public String getOrderSourceStr() {
		return FormateUtil.getInstance().getNameByCode("wh_orderin_source",
				orderSource);
	}

	public String getDataStatusStr() {
		return FormateUtil.getInstance().getNameByCode("g_pu_data_stauts",
				dataStatus);
	}
	
	public String getLimitHoursStr() {
		return FormateUtil.getInstance().getNameByCode("wh_order_limit",this.limitHours.toString());
	}
	

	public boolean isCanDelete() {
		return "1".equals(this.dataStatus);
	}
}