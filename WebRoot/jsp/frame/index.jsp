<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.calf.framework.util.Constants"%>
<%@ include file="/jsp/include/head.jsp"%>  
<html>
<head>
<title><%=Constants.PROJECT_NAME%></title>
</head>
<frameset id="indexframeset" name="fsetname" rows="70,*" cols="*" frameborder="NO" border="0" framespacing="0">
  <frame name="topFrame" scrolling="NO" noresize src="<%=path%>/admin/index_header.action" >
  <frameset cols="178,*" id="mainFrameset" frameborder="no" rows="*">
    <frame src="<%=path%>/admin/index_menu.action" name="leftFrame" marginwidth="0" marginheight="0">
    <frame src="<%=path%>/admin/welcome.action" scrolling="auto" noresize="noresize" name="mainFrame" id="mainFrame">
  </frameset>
  
</frameset>

<noframes>

<BODY>
<P>You Browse doesn't support frame!</P>
</BODY>
</noframes>

</html>