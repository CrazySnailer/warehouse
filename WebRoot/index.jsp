<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.calf.framework.vo.AdminUserInfo"%>
<%@ page import="com.calf.framework.util.Constants"%>
<%
String path = request.getContextPath();
response.setHeader("Pragma","No-cache");   
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);

//判断有没有登录，有登录直接跳转到首页
AdminUserInfo userInfo = (AdminUserInfo) session.getAttribute(Constants.ADMIN_SESSION_USER_INFO);
if (userInfo != null) {
	response.sendRedirect(path + "/admin/index.action");
}else{
	response.sendRedirect(path + "/jsp/login.jsp");
}
%>