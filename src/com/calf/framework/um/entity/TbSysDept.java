package com.calf.framework.um.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbSysDept implements java.io.Serializable {
	private static final long serialVersionUID = -9157965929239784978L;

	public static final String DEPT_LEVEL_PROV = "1";// 省
	public static final String DEPT_LEVEL_CITY = "2";// 市
	public static final String DEPT_LEVEL_COUNTY = "3";// 区
	public static final String DEPT_LEVEL_SCHOOL = "4";// 学校
	/**
	 * pk 机构ID
	 **/
	Long deptId;
	/**
	 * 机构编号
	 **/
	String deptCode;
	/**
	 * 行政区划
	 **/
	String areaCode;
	/**
	 * 上级机构
	 **/
	Long parentDeptId;
	/**
	 * 机构名称
	 **/
	String deptName;
	/**
	 * 机构性质 0机构 1部门
	 **/
	String deptKind;
	/**
	 * 机构级别
	 **/
	String deptLevel;
	/**
	 * 是否是叶子节点
	 **/
	String isLeaf;
	/**
	 * 机构全名
	 **/
	String fullName;
	/**
	 * 排序号
	 **/
	Long orderNum;
	/**
	 * 联系人
	 **/
	String linkMan;
	/**
	 * 联系电话
	 **/
	String linkTel;
	/**
	 * 联系地址
	 **/
	String linkAddr;
	/**
	 * 电子邮件
	 **/
	String linkEmail;
	/**
	 * 备注
	 **/
	String note;
	/**
	 * 树形机构编码
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

	public TbSysDept() {
	}

	public Long getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Long getParentDeptId() {
		return this.parentDeptId;
	}

	public void setParentDeptId(Long parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptKind() {
		return this.deptKind;
	}

	public void setDeptKind(String deptKind) {
		this.deptKind = deptKind;
	}

	public String getDeptLevel() {
		return this.deptLevel;
	}

	public void setDeptLevel(String deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}

	public String getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkTel() {
		return this.linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	public String getLinkAddr() {
		return this.linkAddr;
	}

	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}

	public String getLinkEmail() {
		return this.linkEmail;
	}

	public void setLinkEmail(String linkEmail) {
		this.linkEmail = linkEmail;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public String getDeptLevelStr() {
		return FormateUtil.getInstance().getNameByCode("g_um_dept_level",
				this.deptLevel);
	}

	public String getDeptKindStr() {
		return FormateUtil.getInstance().getNameByCode("g_um_dept_kind",
				deptKind);
	}

	public String getDataStatusStr() {
		return FormateUtil.getInstance().getNameByCode("g_pu_data_stauts",
				dataStatus);
	}
}