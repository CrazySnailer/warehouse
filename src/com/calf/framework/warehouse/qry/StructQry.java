package com.calf.framework.warehouse.qry;

import java.io.Serializable;

import com.calf.framework.util.BaseAdminQuery;

public class StructQry extends BaseAdminQuery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1937084884877261956L;
	
		/**
		 * 编号
		 **/
		String structCode;		
		/**
		 * 名称
		 **/
		String structName;		
	
		public String getStructCode(){
			return this.structCode;
		}
		public void setStructCode(String structCode){
			this.structCode = structCode;
		}
		public String getStructName(){
			return this.structName;
		}
		public void setStructName(String structName){
			this.structName = structName;
		}
	
}