package com.calf.framework.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.DefaultActionSupport;

import com.calf.framework.exception.ParameterException;
import com.calf.framework.exception.SessionLostException;
import com.calf.framework.util.Constants;
import com.calf.framework.vo.AdminUserInfo;

public abstract class BaseAction extends DefaultActionSupport {

	private static final long serialVersionUID = 3525445612504421307L;
	
	/**
     * Transient log to prevent session synchronization issues - children can use instance for logging.
     */
    protected final transient Log log = LogFactory.getLog(getClass());

	protected static String GLOBAL_SUCCESS = "global_success";
	
	protected static String GLOBAL_ERROR = "global_error";
	
	protected String redirectUrl;
	/**
	 * 0 immediate -1 user fired 3,4,5 stop second
	 */
	protected int autoRedirect = -1;
	
	/**
	 * 标题
	 */
	protected String title;
	/**
	 * 用户操作类型
	 */
	protected String event;
	/**
	 * 查询序列化值
	 */
	protected String qryHex;
	
	/**
     * Save the message in the session, appending if messages already exist
     *
     * @param msg the message to put in the session
     */
    @SuppressWarnings("unchecked")
    protected void saveMessage(String msg) {
        List messages = (List) getRequest().getAttribute("messages");
        if (messages == null) {
            messages = new ArrayList();
        }
        messages.add(msg);
        getRequest().setAttribute("messages", messages);
    }

	public BaseAction() {
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected Cookie[] getCookies() {
		return ServletActionContext.getRequest().getCookies();
	}

	protected HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	protected int getIntFromRequest(String parameter) {
		Object value = getRequest().getParameter(parameter);
		if (value != null) {
			try {
				return Integer.parseInt(((String[]) value)[0]);
			} catch (Exception e) {
				return 0;
			}
		}
		return 0;
	}

	protected String[] getValuesFromRequest(String parameter) {
		String[] values = this.getRequest().getParameterValues(parameter);
		if (values != null) {
			return values;
		}
		return new String[] {};
	}

	/**
	 * 获取session中的用户信息对象
	 */
	protected AdminUserInfo getUserInfo() throws SessionLostException {
		AdminUserInfo userInfo = (AdminUserInfo) this.getSession()
				.getAttribute(Constants.ADMIN_SESSION_USER_INFO);
		if (userInfo == null) {
			throw new SessionLostException();
		} else {
			return userInfo;
		}
	}

	/**
	 * 从request中获取String类型
	 * 
	 * @param parameter
	 * @param isNull
	 * @return
	 * @throws ParameterException
	 */
	public String getStringFromRequest(String parameter, boolean isNull)
			throws ParameterException {
		String value = this.getRequest().getParameter(parameter);
		if (StringUtils.isBlank(value)) {
			if (!isNull) {
				throw new ParameterException(parameter + " 参数不能为空");
			} else {
				return "";
			}
		} else {
			return value;
		}
	}

	/**
	 * 获取Long类型
	 */
	protected Long getLongFromRequest(String parameter, boolean isNull)
			throws ParameterException {
		String value = this.getRequest().getParameter(parameter);
		if (StringUtils.isBlank(value)) {
			if (!isNull) {
				throw new ParameterException(parameter + " 参数不能为空");
			} else {
				return null;
			}
		} else {
			return Long.parseLong(value);
		}
	}

	/**
	 * 获取double类型
	 */
	protected Double getDoubleFromRequest(String parameter, boolean isNull)
			throws ParameterException {
		String value = this.getRequest().getParameter(parameter);
		if (StringUtils.isBlank(value)) {
			if (!isNull) {
				throw new ParameterException(parameter + " 参数不能为空");
			} else {
				return null;
			}
		} else {
			return Double.parseDouble(value);
		}
	}

	/**
	 * 直接输出.
	 * 
	 * @param contentType
	 *            内容的类型.html,text,xml的值见后，json为"text/x-json;charset=UTF-8"
	 */
	private void render(HttpServletResponse response, String text,
			String contentType) {
		try {
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
		}
	}

	/**
	 * 写内容到输入页面
	 * 
	 * @param response
	 * @param content
	 * @throws IOException
	 */
	protected void rendText(HttpServletResponse response, String content) {
		render(response, content, "text/html;charset=UTF-8");
	}

	protected void rendText(String content) {
		System.out.println(content);
		render(getResponse(), content, "text/html;charset=UTF-8");
	}

	/**
	 * 直接输出纯HTML.
	 */
	protected void renderHtml(String text) {
		render(getResponse(), text, "text/html;charset=UTF-8");
	}

	/**
	 * 直接输出纯XML.
	 */
	protected void renderXML(String text) {
		render(getResponse(), text, "text/xml;charset=UTF-8");
	}

	/**
	 * 返回JSON成功信息
	 * 
	 * @param message
	 */
	protected void renderJsonSuccess(String message) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("status", SUCCESS);
		jsonMap.put("message", message);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		this.rendText(jsonObject.toString());
	}

	/**
	 * 返回JSON成功信息
	 * 
	 * @param message
	 */
	protected void renderJsonError(String message) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("status", ERROR);
		jsonMap.put("message", message);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		this.rendText(jsonObject.toString());
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQryHex() {
		return qryHex;
	}

	public void setQryHex(String qryHex) {
		this.qryHex = qryHex;
	}

	protected void addAttribute(String name, String value) {
		this.getRequest().setAttribute("param_" + name, value);
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public int getAutoRedirect() {
		return autoRedirect;
	}

	public void setAutoRedirect(int autoRedirect) {
		this.autoRedirect = autoRedirect;
	}
}