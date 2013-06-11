package com.calf.framework.web.tag;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.components.Component;

import com.calf.framework.util.Constants;
import com.calf.framework.vo.AdminUserInfo;
import com.opensymphony.xwork2.util.ValueStack;

public class HasPermissionComponent extends Component {
	AdminUserInfo userInfo;
	String name;

	public HasPermissionComponent(ValueStack stack, HttpServletRequest request) {
		super(stack);
		HttpSession session = request.getSession();
		this.userInfo = (AdminUserInfo)session.getAttribute(Constants.ADMIN_SESSION_USER_INFO);		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean start(Writer writer) {
		return userInfo.findResources(name);
	}

}
