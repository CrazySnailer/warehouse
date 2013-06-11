package com.calf.framework.sys.web;

import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;

public class IndexAction extends BaseAction {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3870633869178615246L;
	
	
	
	@RequiresPermissions(value = "",requiresUser=true)
	public String execute() throws Exception {
		return "index";
	}
	
	/**
	 * 左边菜单
	 * @return
	 */
	@RequiresPermissions(value = "",requiresUser=true)
	public String menu(){
		return "menu";
	}
	
	/**
	 * 头部信息
	 * @return
	 */
	@RequiresPermissions(value = "",requiresUser=true)
	public String header(){
		return "header";
	}
	
	/**
	 * 中间部分
	 * @return
	 */
	@RequiresPermissions(value = "",requiresUser=true)
	public String middle(){
		return "middle";
	}
}
