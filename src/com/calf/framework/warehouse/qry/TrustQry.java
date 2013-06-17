package com.calf.framework.warehouse.qry;

import java.io.Serializable;

import com.calf.framework.util.BaseAdminQuery;

public class TrustQry extends BaseAdminQuery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1937084884877261956L;
	
		/**
		 * 编码
		 **/
		String trustCode;		
	
		public String getTrustCode(){
			return this.trustCode;
		}
		public void setTrustCode(String trustCode){
			this.trustCode = trustCode;
		}
	
}