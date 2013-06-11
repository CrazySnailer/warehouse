package com.calf.framework.util;

import java.util.HashMap;
import java.util.Map;

/**  
 * 全局常量定义类
 *
 */
public class Constants {
	/**
	 * 数据查看权限--可查看所有数据
	 */
	public final static String DATA_PRIV_ALL = "PRIV_ALL";
	/**
	 * 数据查看权限--查看全省数据
	 */
	public final static String DATA_PRIV_PROV = "PRIV_PROV";
	/**
	 * 数据查看权限--查看全市数据
	 */
	public final static String DATA_PRIV_CITY = "PRIV_CITY";
	/**
	 * 数据查看权限--查看全区/县数据
	 */
	public final static String DATA_PRIV_COUNTY = "PRIV_COUNTY";
	/**
	 * 数据查看权限--查看自身及以下机构数据
	 */
	public final static String DATA_PRIV_SELF_AND_BELOW_DEPT = "PRIV_SELF_BELOW_DEPT";
	/**
	 * 数据查看权限--本机构数据
	 */
	public final static String DATA_PRIV_SELF_DEPT = "PRIV_SELF_DEPT";
	/**
	 * 数据查看权限--只能查看自身数据
	 */
	public final static String DATA_PRIV_SELF = "PRIV_SELF";
	
	/**
	 * 用户数据对象
	 */
	public static final String ADMIN_SESSION_USER_INFO = "userInfo";
	
	
	/**
	 * 
	 */
	public static final String PROJECT_NAME = "测试工程项目";
	public static String VERSION = "v1.0.2596";
	
	/**
	 * 正常
	 */
	public final static String YES = "1";
	/**
	 * 删除
	 */
	public final static String NO = "0";
	
	/**
	 * 降序
	 */
	public static final int DESC = 0;
	/**
	 * 升序
	 */
	public static final int ASC = 1;
	
	/**
	 * 参数数据列表,KEY---LIST
	 */
	public static Map params = new HashMap();
	
	/**
	 * 分页str
	 */
	public static String PAGE_STR = "<label>每页显示</label><select name=\"qry.pageSize\" id=\"pageSize\" class=\"formSelectPageSize\"><option value=\"15\">					15				</option>				<option value=\"50\">					50				</option>				<option value=\"100\">					100				</option>			</select>";
	/**
	 * 分页STR,用于报表
	 */
	public static String PAGE_STR_RPT = "<select name=\"qry.pageSize\" id=\"pageSize\" class=\"formSelectPageSize\"><option value=\"15\">					15				</option>				<option value=\"50\">					50				</option>				<option value=\"100\">					100				</option>			</select>";
	
	public static String UPLOAD_IMAGE_DIR = "/upload/images/";
}
