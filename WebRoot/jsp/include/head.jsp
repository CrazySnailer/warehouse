<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<%
String path = request.getContextPath();
if("/".equals(path)){
    path = "";
}
String projectName = "autocode demo system";
%>