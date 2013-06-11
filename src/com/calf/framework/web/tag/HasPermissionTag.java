package com.calf.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class HasPermissionTag extends ComponentTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 23762235349607427L;
	String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request,
			HttpServletResponse arg2) {
		return new HasPermissionComponent(stack,request);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		HasPermissionComponent compent = (HasPermissionComponent)super.getComponent();
		compent.setName(this.name);
	}
	
}
