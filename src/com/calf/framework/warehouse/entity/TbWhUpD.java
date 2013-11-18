package com.calf.framework.warehouse.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbWhUpD implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7210248768007789556L;
	
		/**
		 * 主键
		 * 子表ID
		 **/
		Long upDId;
		/**
		 * 上架单ID
		 **/
		Long upId;
		/**
		 * 商品ID
		 **/
		Long productId;
		/**
		 * 验货单子表ID
		 **/
		Long inspectionDId;
		/**
		 * 仓库结构ID
		 **/
		Long structId;
		/**
		 * 数量
		 **/
		Long qty;
		/**
		 * 类型P计划 R实际
		 **/
		String upType;
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
	
	public TbWhUpD(){
	}
	
		public Long getUpDId(){
			return this.upDId;
		}		
		public void setUpDId(Long upDId){
			this.upDId = upDId;
		}
		public Long getUpId(){
			return this.upId;
		}		
		public void setUpId(Long upId){
			this.upId = upId;
		}
		public Long getProductId(){
			return this.productId;
		}		
		public void setProductId(Long productId){
			this.productId = productId;
		}
		public Long getInspectionDId(){
			return this.inspectionDId;
		}		
		public void setInspectionDId(Long inspectionDId){
			this.inspectionDId = inspectionDId;
		}
		public Long getStructId(){
			return this.structId;
		}		
		public void setStructId(Long structId){
			this.structId = structId;
		}
		public Long getQty(){
			return this.qty;
		}		
		public void setQty(Long qty){
			this.qty = qty;
		}
		public String getUpType(){
			return this.upType;
		}		
		public void setUpType(String upType){
			this.upType = upType;
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
	
	
	/*public boolean isCanDelete(){
		return "1".equals(this.dataStatus);
	}*/
}