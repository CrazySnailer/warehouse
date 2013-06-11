package com.calf.framework.um.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbSysMenu implements java.io.Serializable {
	private static final long serialVersionUID = -8875281926638485982L;
	/**
	 * pk 菜单ID
	 **/
	Long menuId;
	/**
	 * 上级菜单
	 **/
	TbSysMenu parent;
	/**
	 * 只是一个VO字段，不做HIBERNATE映射
	 */
	Long parentId;
	/**
	 * 菜单名称
	 **/
	String name;
	/**
	 * 菜单URL
	 **/
	String url;
	/**
	 * 唯一标识值
	 **/
	String idVal;
	/**
	 * 是否叶子
	 **/
	String isLeaf;
	/**
	 * 排序号
	 **/
	Long orderNum;
	/**
	 * 是否需要购买
	 **/
	String needBuy;
	/**
	 * 菜单级别
	 **/
	Long menuLevel;
	/**
	 * 菜单全名
	 **/
	String fullName;
	/**
	 * 菜单编号
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
	/**
	 * 是否选中
	 */
	boolean isCheck;
	/**
	 * 菜单类型
	 */
	String menuType;

	public TbSysMenu() {
	}


	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public TbSysMenu getParent() {
		return parent;
	}


	public void setParent(TbSysMenu parent) {
		this.parent = parent;
	}


	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIdVal() {
		return this.idVal;
	}

	public void setIdVal(String idVal) {
		this.idVal = idVal;
	}

	public String getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Long getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}

	public String getNeedBuy() {
		return this.needBuy;
	}

	public void setNeedBuy(String needBuy) {
		this.needBuy = needBuy;
	}

	public Long getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(Long menuLevel) {
		this.menuLevel = menuLevel;
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
	
	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	

	public String getMenuType() {
		return menuType;
	}


	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
	public String getCreateDateStr() {
		return DateUtil.getInstance().dateToString(this.createDate,
				DateUtil.patternA);
	}

	public String getUpdateDateStr() {
		return DateUtil.getInstance().dateToString(this.updateDate,
				DateUtil.patternA);
	}

	public String getIsLeafStr() {
		return FormateUtil.getInstance().getNameByCode("g_pu_yes_no", isLeaf);
	}

	public String getNeedBuyStr() {
		return FormateUtil.getInstance().getNameByCode("g_pu_yes_no", needBuy);
	}

	public String getDataStatusStr() {
		return FormateUtil.getInstance().getNameByCode("g_pu_data_stauts",
				dataStatus);
	}
}