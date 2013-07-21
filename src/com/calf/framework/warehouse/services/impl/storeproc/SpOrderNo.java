/**
 * 
 */
package com.calf.framework.warehouse.services.impl.storeproc;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class SpOrderNo extends StoredProcedure {
	private final static String STORE_PROCEDURE_NAME = "pkg_flow_code.get_order_flow_no";
	
	/**
	 * 编译存储过程
	 * @param jdbcTemplate
	 */
	public SpOrderNo(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, STORE_PROCEDURE_NAME);
		declareParameter(new SqlParameter("p_flow_type",Types.VARCHAR));
		declareParameter(new SqlParameter("p_prefix",Types.VARCHAR));
		declareParameter(new SqlParameter("p_note",Types.VARCHAR));
		declareParameter(new SqlParameter("p_user_id",Types.INTEGER));
		declareParameter(new SqlOutParameter("po_order_code",Types.VARCHAR));
		compile();
	}
	
	/**
	 * 执行存储过程
	 * @param deptCode
	 * @return
	 */
	public String execute(String flowType,String prefix,String note,Long userId){
		 Map params = new HashMap();
		 params.put("p_flow_type", flowType);
		 params.put("p_prefix", prefix);
		 params.put("p_note", note);
		 params.put("p_user_id", userId);		 
		 Map result = super.execute(params);
		 return (String)result.get("po_order_code");
	 }
}
