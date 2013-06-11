package com.calf.framework.um.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

/**
 * 说明:ENEITY类 代码生成器生成,如有建议或意见，请EMAIL：zhoucl@routdata.com Copyright: Copyright
 * (c) 2011 深圳络道科技
 * 
 * @author
 * 
 */
public class TbSysParamKind implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1632237139276819315L;
	
	/**
	 * pk 参数分类编码
	 **/
	String kindCode;
	/**
	 * 参数分类名称
	 **/
	String kindName;
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

	public TbSysParamKind() {
	}

	public String getKindCode() {
		return this.kindCode;
	}

	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}

	public String getKindName() {
		return this.kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
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

	public String getDataStatusStr() {
		return FormateUtil.getInstance().getNameByCode("g_pu_data_stauts",
				dataStatus);
	}
}