package com.calf.framework.warehouse.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbWhProductClass implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7210248768007789556L;

	TbWhProductClass parent;

	/**
	 * 主键 分类ID
	 **/
	Long classId;
	/**
	 * 父级ID
	 **/
	Long parentId;
	/**
	 * 分类编码
	 **/
	String classCode;
	/**
	 * 分类名称
	 **/
	String className;
	/**
	 * 排序号
	 **/
	Long orderNum;
	/**
	 * 是否叶子
	 **/
	String isLeaf;
	/**
	 * 级别
	 **/
	Long treeLevel;
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

	public TbWhProductClass() {
	}

	public Long getClassId() {
		return this.classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Long getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}

	public String getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Long getTreeLevel() {
		return this.treeLevel;
	}

	public void setTreeLevel(Long treeLevel) {
		this.treeLevel = treeLevel;
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

	public TbWhProductClass getParent() {
		return this.parent;
	}

	public void setParent(TbWhProductClass parent) {
		this.parent = parent;
	}

	public boolean isCanDelete() {
		return "1".equals(this.dataStatus);
	}
}