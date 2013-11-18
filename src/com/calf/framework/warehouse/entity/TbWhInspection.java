package com.calf.framework.warehouse.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class TbWhInspection implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7210248768007789556L;
	
		/**
		 * 主键
		 * 验货单ID
		 **/
		Long inspectionId;
		/**
		 * 订单ID
		 **/
		Long orderId;
		/**
		 * 验货单号,规则YH+订单号+序号
		 **/
		String inspectionNo;
		/**
		 * 验货次时
		 **/
		Long orderNum;
		/**
		 * 仓库
		 **/
		Long whId;
		/**
		 * 机构
		 **/
		Long deptId;
		/**
		 * 备注
		 **/
		String remarks;
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
	
	public TbWhInspection(){
	}
	
		public Long getInspectionId(){
			return this.inspectionId;
		}		
		public void setInspectionId(Long inspectionId){
			this.inspectionId = inspectionId;
		}
		public Long getOrderId(){
			return this.orderId;
		}		
		public void setOrderId(Long orderId){
			this.orderId = orderId;
		}
		public String getInspectionNo(){
			return this.inspectionNo;
		}		
		public void setInspectionNo(String inspectionNo){
			this.inspectionNo = inspectionNo;
		}
		public Long getOrderNum(){
			return this.orderNum;
		}		
		public void setOrderNum(Long orderNum){
			this.orderNum = orderNum;
		}
		public Long getWhId(){
			return this.whId;
		}		
		public void setWhId(Long whId){
			this.whId = whId;
		}
		public Long getDeptId(){
			return this.deptId;
		}		
		public void setDeptId(Long deptId){
			this.deptId = deptId;
		}
		public String getRemarks(){
			return this.remarks;
		}		
		public void setRemarks(String remarks){
			this.remarks = remarks;
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