package com.calf.framework.warehouse.qry;

import java.io.Serializable;

import com.calf.framework.util.BaseAdminQuery;

public class ExpressQry extends BaseAdminQuery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1937084884877261956L;
	
		/**
		 * 物流公司名称
		 **/
		String expressName;		
	
		public String getExpressName(){
			return this.expressName;
		}
		public void setExpressName(String expressName){
			this.expressName = expressName;
		}
	
}