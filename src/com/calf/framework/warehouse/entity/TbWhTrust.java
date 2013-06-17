package com.calf.framework.warehouse.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbWhTrust implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7210248768007789556L;
	
		/**
		 * 主键
		 * TRUST_ID
		 **/
		Long trustId;
		/**
		 * 编码
		 **/
		String trustCode;
		/**
		 * 名称（全称）
		 **/
		String trustName;
		/**
		 * 简称
		 **/
		String shortName;
		/**
		 * 联系人
		 **/
		String linker;
		/**
		 * 联系电话
		 **/
		String linkerTel;
		/**
		 * 联系地址
		 **/
		String address;
		/**
		 * 电子邮箱
		 **/
		String email;
		/**
		 * 邮编
		 **/
		String postcode;
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
	
	public TbWhTrust(){
	}
	
		public Long getTrustId(){
			return this.trustId;
		}		
		public void setTrustId(Long trustId){
			this.trustId = trustId;
		}
		public String getTrustCode(){
			return this.trustCode;
		}		
		public void setTrustCode(String trustCode){
			this.trustCode = trustCode;
		}
		public String getTrustName(){
			return this.trustName;
		}		
		public void setTrustName(String trustName){
			this.trustName = trustName;
		}
		public String getShortName(){
			return this.shortName;
		}		
		public void setShortName(String shortName){
			this.shortName = shortName;
		}
		public String getLinker(){
			return this.linker;
		}		
		public void setLinker(String linker){
			this.linker = linker;
		}
		public String getLinkerTel(){
			return this.linkerTel;
		}		
		public void setLinkerTel(String linkerTel){
			this.linkerTel = linkerTel;
		}
		public String getAddress(){
			return this.address;
		}		
		public void setAddress(String address){
			this.address = address;
		}
		public String getEmail(){
			return this.email;
		}		
		public void setEmail(String email){
			this.email = email;
		}
		public String getPostcode(){
			return this.postcode;
		}		
		public void setPostcode(String postcode){
			this.postcode = postcode;
		}
		public String getRemark(){
			return this.remark;
		}		
		public void setRemark(String remark){
			this.remark = remark;
		}
		public String getDataStatus(){
			return this.dataStatus;
		}		
		public void setDataStatus(String dataStatus){
			this.dataStatus = dataStatus;
		}
		public Long getCreateUser(){
			return this.createUser;
		}		
		public void setCreateUser(Long createUser){
			this.createUser = createUser;
		}
		public Date getCreateDate(){
			return this.createDate;
		}		
		public void setCreateDate(Date createDate){
			this.createDate = createDate;
		}
		public Long getUpdateUser(){
			return this.updateUser;
		}		
		public void setUpdateUser(Long updateUser){
			this.updateUser = updateUser;
		}
		public Date getUpdateDate(){
			return this.updateDate;
		}		
		public void setUpdateDate(Date updateDate){
			this.updateDate = updateDate;
		}
	
		public String getCreateDateStr(){
			return DateUtil.getInstance().dateToString(this.createDate,DateUtil.patternA);
		}
		public String getUpdateDateStr(){
			return DateUtil.getInstance().dateToString(this.updateDate,DateUtil.patternA);
		}
	
	
	public boolean isCanDelete(){
		return "1".equals(this.dataStatus);
	}
}