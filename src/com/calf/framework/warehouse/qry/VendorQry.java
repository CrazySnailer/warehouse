package com.calf.framework.warehouse.qry;

import java.io.Serializable;

import com.calf.framework.util.BaseAdminQuery;

public class VendorQry extends BaseAdminQuery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1937084884877261956L;
	
		/**
		 * 编码
		 **/
		String vendorCode;		
		/**
		 * 名称
		 **/
		String vendorName;		
	
		public String getVendorCode(){
			return this.vendorCode;
		}
		public void setVendorCode(String vendorCode){
			this.vendorCode = vendorCode;
		}
		public String getVendorName(){
			return this.vendorName;
		}
		public void setVendorName(String vendorName){
			this.vendorName = vendorName;
		}
	
}