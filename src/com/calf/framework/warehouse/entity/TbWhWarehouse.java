package com.calf.framework.warehouse.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbWhWarehouse implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7210248768007789556L;

	/**
	 * 主键 仓库ID
	 **/
	Long whId;
	/**
	 * 编号
	 **/
	String whCode;
	/**
	 * 仓库名称
	 **/
	String whName;
	/**
	 * 所属仓库
	 **/
	Long parentId;

	TbWhWarehouse parent;
	/**
	 * 仓库类型 P物理仓 L逻辑仓
	 **/
	String whType;
	/**
	 * 使用部门
	 **/
	Long useDeptId;
	/**
	 * 管理模式 1简单 2复杂
	 **/
	String adminMode;
	/**
	 * 联系地址
	 **/
	String address;
	/**
	 * 联系人
	 **/
	String linker;
	/**
	 * 联系电话
	 **/
	String linkerTel;
	/**
	 * 电子邮箱
	 **/
	String email;
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

	public TbWhWarehouse() {
	}

	public Long getWhId() {
		return this.whId;
	}

	public void setWhId(Long whId) {
		this.whId = whId;
	}

	public String getWhCode() {
		return this.whCode;
	}

	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}

	public String getWhName() {
		return this.whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public TbWhWarehouse getParent() {
		return parent;
	}

	public void setParent(TbWhWarehouse parent) {
		this.parent = parent;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getWhType() {
		return this.whType;
	}

	public void setWhType(String whType) {
		this.whType = whType;
	}

	public Long getUseDeptId() {
		return this.useDeptId;
	}

	public void setUseDeptId(Long useDeptId) {
		this.useDeptId = useDeptId;
	}

	public String getAdminMode() {
		return this.adminMode;
	}

	public void setAdminMode(String adminMode) {
		this.adminMode = adminMode;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getWhTypeStr() {
		return FormateUtil.getInstance().getNameByCode("g_wh_wh_type", whType);
	}

	public String getAdminModeStr() {
		return FormateUtil.getInstance().getNameByCode("g_wh_admin_type",
				adminMode);
	}

	public boolean isCanDelete() {
		return "1".equals(this.dataStatus);
	}
}