package com.calf.framework.warehouse.qry;

import java.io.Serializable;

import com.calf.framework.util.BaseAdminQuery;

public class WarehouseQry extends BaseAdminQuery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1937084884877261956L;
	
		/**
		 * 编号
		 **/
		String whCode;		
		/**
		 * 仓库名称
		 **/
		String whName;		
	
		public String getWhCode(){
			return this.whCode;
		}
		public void setWhCode(String whCode){
			this.whCode = whCode;
		}
		public String getWhName(){
			return this.whName;
		}
		public void setWhName(String whName){
			this.whName = whName;
		}
	
}