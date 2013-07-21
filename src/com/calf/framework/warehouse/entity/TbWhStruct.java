package com.calf.framework.warehouse.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbWhStruct implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7210248768007789556L;

	TbWhStruct parent;

	/**
	 * 主键 仓库结构ID
	 **/
	Long structId;
	/**
	 * 父级ID
	 **/
	Long parentId;
	/**
	 * 编号
	 **/
	String structCode;
	/**
	 * 名称
	 **/
	String structName;
	/**
	 * 所属仓库
	 **/
	Long whId;
	/**
	 * 类型 P储位 F货架 A库区 W仓库
	 **/
	String structType;
	/**
	 * 排序号
	 **/
	Long orderNum;
	/**
	 * 全名
	 **/
	String fullName;
	/**
	 * 树编号
	 **/
	String treeNo;
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

	public TbWhStruct() {
	}

	public Long getStructId() {
		return this.structId;
	}

	public void setStructId(Long structId) {
		this.structId = structId;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getStructCode() {
		return this.structCode;
	}

	public void setStructCode(String structCode) {
		this.structCode = structCode;
	}

	public String getStructName() {
		return this.structName;
	}

	public void setStructName(String structName) {
		this.structName = structName;
	}

	public Long getWhId() {
		return this.whId;
	}

	public void setWhId(Long whId) {
		this.whId = whId;
	}

	public String getStructType() {
		return this.structType;
	}

	public void setStructType(String structType) {
		this.structType = structType;
	}

	public Long getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTreeNo() {
		return this.treeNo;
	}

	public void setTreeNo(String treeNo) {
		this.treeNo = treeNo;
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

	public String getCreateDateStr() {
		return DateUtil.getInstance().dateToString(this.createDate,
				DateUtil.patternA);
	}

	public String getUpdateDateStr() {
		return DateUtil.getInstance().dateToString(this.updateDate,
				DateUtil.patternA);
	}

	public String getStructTypeStr() {
		return FormateUtil.getInstance().getNameByCode("g_wh_struct_type",
				structType);
	}

	public String getDataStatusStr() {
		return FormateUtil.getInstance().getNameByCode("g_pu_yes_no",
				dataStatus);
	}

	public TbWhStruct getParent() {
		return this.parent;
	}

	public void setParent(TbWhStruct parent) {
		this.parent = parent;
	}

	public boolean isCanDelete() {
		return "1".equals(this.dataStatus);
	}
}