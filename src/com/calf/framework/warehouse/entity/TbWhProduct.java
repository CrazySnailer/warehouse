package com.calf.framework.warehouse.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbWhProduct implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7210248738007789556L;
	/**
	 * 主键 商品ID
	 **/
	Long productId;
	/**
	 * 所属分类
	 **/
	Long classId;
	/**
	 * 管理部门
	 **/
	Long deptId;
	/**
	 * 商品编码
	 **/
	String productCode;
	/**
	 * 商品条码/SKU
	 **/
	String productSku;
	/**
	 * 商品名称
	 **/
	String productName;
	/**
	 * 委托货主
	 **/
	Long trustId;
	/**
	 * 供应商
	 **/
	Long vendorId;
	/**
	 * 仓库存放位置
	 **/
	Long structId;
	/**
	 * 价值
	 **/
	Double productCost;
	/**
	 * 品牌
	 **/
	String brandName;
	/**
	 * 型号
	 **/
	String modelNo;
	/**
	 * 包装单位
	 **/
	String unitName;
	/**
	 * 大包装单位
	 **/
	String bigUnitName;
	/**
	 * 包装换算率
	 **/
	Long exchangeNum;
	/**
	 * 管理方式
	 **/
	String adminType;
	/**
	 * 产地
	 **/
	String makeLocation;
	/**
	 * 生产商
	 **/
	String makeFactory;
	/**
	 * 保修期
	 **/
	String warrantyDay;
	/**
	 * 委托货主结算价
	 **/
	Double trustSettlePrice;
	/**
	 * 供应商结算价
	 **/
	Double vendorSettlePrice;
	/**
	 * 备注
	 **/
	String remark;
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

	public TbWhProduct() {
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getClassId() {
		return this.classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductSku() {
		return this.productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
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

	public Long getStructId() {
		return this.structId;
	}

	public void setStructId(Long structId) {
		this.structId = structId;
	}

	public Double getProductCost() {
		return this.productCost;
	}

	public void setProductCost(Double productCost) {
		this.productCost = productCost;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModelNo() {
		return this.modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getBigUnitName() {
		return this.bigUnitName;
	}

	public void setBigUnitName(String bigUnitName) {
		this.bigUnitName = bigUnitName;
	}

	public Long getExchangeNum() {
		return this.exchangeNum;
	}

	public void setExchangeNum(Long exchangeNum) {
		this.exchangeNum = exchangeNum;
	}

	public String getAdminType() {
		return this.adminType;
	}

	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}

	public String getMakeLocation() {
		return this.makeLocation;
	}

	public void setMakeLocation(String makeLocation) {
		this.makeLocation = makeLocation;
	}

	public String getMakeFactory() {
		return this.makeFactory;
	}

	public void setMakeFactory(String makeFactory) {
		this.makeFactory = makeFactory;
	}

	public String getWarrantyDay() {
		return this.warrantyDay;
	}

	public void setWarrantyDay(String warrantyDay) {
		this.warrantyDay = warrantyDay;
	}

	public Double getTrustSettlePrice() {
		return this.trustSettlePrice;
	}

	public void setTrustSettlePrice(Double trustSettlePrice) {
		this.trustSettlePrice = trustSettlePrice;
	}

	public Double getVendorSettlePrice() {
		return this.vendorSettlePrice;
	}

	public void setVendorSettlePrice(Double vendorSettlePrice) {
		this.vendorSettlePrice = vendorSettlePrice;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getProductCostStr() {
		return FormateUtil.getInstance().formateDouble(this.productCost);
	}

	public String getTrustSettlePriceStr() {
		return FormateUtil.getInstance().formateDouble(this.trustSettlePrice);
	}

	public String getVendorSettlePriceStr() {
		return FormateUtil.getInstance().formateDouble(this.vendorSettlePrice);
	}

	public String getCreateDateStr() {
		return DateUtil.getInstance().dateToString(this.createDate, DateUtil.patternA);
	}

	public String getUpdateDateStr() {
		return DateUtil.getInstance().dateToString(this.updateDate, DateUtil.patternA);
	}

	public String getAdminTypeStr() {
		return FormateUtil.getInstance().getNameByCode("wh_admin_type", adminType);
	}

	public boolean isCanDelete() {
		return "1".equals(this.dataStatus);
	}
}