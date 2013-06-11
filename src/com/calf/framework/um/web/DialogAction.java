package com.calf.framework.um.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.um.qry.DeptQry;
import com.calf.framework.um.services.DeptService;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.web.BaseAction;

public class DialogAction extends BaseAction {
	/**
	 * 
	 */
	DeptService deptService;

	/**
	 * LIST对象
	 */
	List list;
	
	/**
	 * 选择机构
	 * @return
	 * @throws Exception
	 */
	public String selectDept() throws Exception {
		AdminUserInfo userInfo = super.getUserInfo();
		
		String uri = super.getStringFromRequest("uri", false);
		
		String qryType = super.getStringFromRequest("qryType", true);
		
		//默认查询类型为机构
		if(StringUtils.isBlank(qryType)){
			qryType = "dept";
		}
		
		DeptQry qry = new DeptQry();
		qry.setQryType(qryType);
		
		qry.setUserInfo(userInfo);

		list = this.deptService.findDeptTree(qry);
		return uri;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
}