package com.calf.framework.um.entity;

import java.util.Date;

import com.calf.framework.util.CodeName;
import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbSysParamItem implements java.io.Serializable {
	private static final long serialVersionUID = -2327642448395136756L;
	/**
	 * pk 序列号
	 **/
	Long recId;
	/**
	 * 参数类别
	 **/
	String paramKindCode;
	/**
	 * 参数编码
	 **/
	String paramCode;
	/**
	 * 参数名称
	 **/
	String paramName;
	/**
	 * 数据状态
	 **/
	String dataStatus;
	/**
	 * 备注
	 **/
	String note;
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
	 * 排序号
	 */
	Long orderNum;
	public TbSysParamItem() {
	}

	public Long getRecId() {
		return this.recId;
	}

	public void setRecId(Long recId) {
		this.recId = recId;
	}

	public String getParamKindCode() {
		return this.paramKindCode;
	}

	public void setParamKindCode(String paramKindCode) {
		this.paramKindCode = paramKindCode;
	}

	public String getParamCode() {
		return this.paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getDataStatus() {
		return this.dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public String getDataStatusStr() {
		return FormateUtil.getInstance().getNameByCode("g_pu_data_stauts",
				dataStatus);
	}
	
	/**
	 * 转换成CODE NAME键值对
	 * @return
	 */
	public CodeName convertCodeName() {
		CodeName data = new CodeName();
		data.setCode(this.paramCode);
		data.setName(this.paramName);
		return data;
	}

	public Long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}
	
	
}