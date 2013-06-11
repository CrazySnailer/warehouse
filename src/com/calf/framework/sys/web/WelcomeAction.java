package com.calf.framework.sys.web;

import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;

public class WelcomeAction extends BaseAction {
	private static final long serialVersionUID = 3870633869178615146L;

	@RequiresPermissions(value = "", requiresUser = true)
	public String execute() throws Exception {
		return "success";
	}

}
