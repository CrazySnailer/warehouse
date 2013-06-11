<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/include/head.jsp"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.Enumeration" %>
<%
Exception exception = (Exception) request.getAttribute("exception");
exception.printStackTrace();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=path%>/res/css/base.css" rel="stylesheet" type="text/css"/>
<%@ include file="/jsp/include/basejs.jsp" %>
</head>
<body>

	<div style="background: #fff;">
		<div class="messageBox">
			<div class="boxTop">
				<div class="boxTitle">操作失败&nbsp;</div>
				<a class="boxClose windowClose" href="#" hidefocus="true"></a>
			</div>
			<div class="boxMiddle">
				<div class="messageContent">
					<span class="icon error">&nbsp;</span>
					<span class="messageText">
							<%
							String exClsName = exception.getClass().getName();
							if("org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException".equals(exClsName)){
								out.println("数据异常：");
							}else{
								out.println("操作失败：");
							}
							%>
							<br/>
							<%=StringUtils.replace(exception.getMessage(),";","<br/>") %>
					</span>
				</div>
				<input type="button" class="buttonInput" value="返 回" hidefocus="true" onclick="history.go(-1)"/>
			</div>
			<div class="boxBottom"></div>
		</div>
	</div>
	</form>
</body>
</html>