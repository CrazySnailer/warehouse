<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.calf.framework.vo.AdminUserInfo"%>
<%@ page import="com.calf.framework.util.Constants"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.calf.framework.um.entity.TbSysMenu"%>
<%@ include file="/jsp/include/head.jsp"%>
<%@page import="com.calf.framework.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="<%=path%>/res/css/base.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/res/css/header.css" rel="stylesheet" type="text/css"> 
<script language="javascript" src="<%=path%>/res/js/jquery.js"></script>
<script type="text/javascript">
$().ready(function() {
	$('#menu').find("li > a").click(function() {
		$('#menu').find("li > a").removeClass("selected");
		$(this).addClass("selected");
		if($(this).attr('href')!='<%=path %>/admin/welcome.action'){
			var mainFrameset = window.parent.window.document.getElementById("mainFrameset");
        	mainFrameset.cols = "178,*";
        }
	});
});
</script>
</head>
<body class="header">
<div>
	<div id="logo"><!--<img src="<%=path %>/res/admin/images/logo.gif">--><%=Constants.PROJECT_NAME%></div>
	<ul id="menu">
                <li><a href="<%=path %>/admin/welcome.action" target="mainFrame" class="selected">
                    <span>&nbsp;首页&nbsp;</span></a></li>
                
<%
		AdminUserInfo userInfo = (AdminUserInfo)session.getAttribute(Constants.ADMIN_SESSION_USER_INFO);
		List menuList = userInfo.getMenuList();
		Long rootParent = null;
		if(menuList != null){
			TbSysMenu menu = (TbSysMenu)menuList.get(0);
			rootParent = menu.getMenuId();
			
			for(int i = 0,len = menuList.size()-1;i<=len;i++){
				menu = (TbSysMenu)menuList.get(i);				
				if(menu.getParentId()!=null&&menu.getParentId().longValue()==rootParent.longValue()){
%>
                <li><a href="<%=path%>/admin/index_menu.action?topid=<%=menu.getMenuId()%>" target="leftFrame">
                    <span><%=menu.getName()%></span></a></li>                
<%					
					
				}
			}
		}
%>                

                
            </ul>


		<ul id="navbtn">
			<li><a href="<%=path %>/index!toChangePassword.action" target="mainFrame">${userInfo.user.userName}</a>，您好！</li>
			<li class="twoword"><a href="<%=path %>/admin/login_logout.action">退 出</a></li>
		</ul>
			
	<div id="info">

	</div>
	<div class="clear"></div>	
</div>
</body>
</html>