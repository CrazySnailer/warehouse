<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.calf.framework.vo.AdminUserInfo"%>
<%@ page import="com.calf.framework.util.Constants"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.calf.framework.um.entity.TbSysMenu"%>

<%@ include file="/jsp/include/head.jsp"%>  
<%@ page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>用户菜单</title>
<link href="<%=path%>/res/css/base.css" rel="stylesheet" type="text/css"/>

<style type="text/css">

#reportNav { width:168px; float: left; font-size: 18px; line-height: 45px; position: relative; }
#reportNav ul { border: 1px solid #c2ccd4; border-bottom: none; }
#reportNav ul li { border-bottom: 1px solid #c2ccd4; text-indent: 20px; background: url(http://my.adsmogo.com/Content/images/report_nav_bg.png) repeat-x; }
#reportNav ul li a { text-decoration: none; color: #333; display: block; }
#reportNav ul li a:hover { color: #008dac; }
#reportNav ul li .current, #reportNav ul li .current:hover { background: url(http://my.adsmogo.com/Content/images/report_nav_bg.png) 0 -45px repeat-x; color: #fff; display: block; }

.corner-1-AppNav, .corner-2-AppNav, .corner-3-AppNav, .corner-4-AppNav { /*{ 设置圆角通用属性 }*/ background: url(http://my.adsmogo.com/Content/images/AppNav_bg.png) no-repeat; position: absolute; z-index: 100; font-size: 0; /*{ Hack in IE6 }*/ }
.corner-1-AppNav, .corner-2-AppNav { height: 5px; width: 5px; top: 0; }
.corner-3-AppNav, .corner-4-AppNav { height: 7px; width: 5px; bottom: -2px; }
.corner-1-AppNav { background-position: 0 0; left: 0; }
.corner-2-AppNav { background-position: -5px 0; right: 0 !important; /*{ Hack in IE 6 }*/ }
.corner-3-AppNav { background-position: 0 -5px; left: 0; }
.corner-4-AppNav { background-position: -5px -5px; right: 0 !important; /*{ Hack in IE 6 }*/ }

</style>

<script language="javascript" src="<%=path%>/res/js/jquery.js"></script>
<script language="javascript">
	$().ready(function() {
		$('#reportNav').find("ul li > a").click(function() {
			$('#reportNav').find("ul li > a").removeClass("current");
			$(this).addClass("current");
		});
	});
</script> 
<body>

        <%
        String topid = request.getParameter("topid");
		if(StringUtils.isNotBlank(topid)){
		%>
		<div id="reportNav" style="margin-bottom: 66px;">
        <div class="corner-1-AppNav">
        </div>
        <div class="corner-2-AppNav">
        </div>
        <div class="corner-3-AppNav">
        </div>
        <div class="corner-4-AppNav">
        </div>
        <ul>
		<%
			AdminUserInfo userInfo = (AdminUserInfo)session.getAttribute(Constants.ADMIN_SESSION_USER_INFO);
			List menuList = userInfo.getMenuList();		
			Long rootParent = Long.valueOf(topid);
			if(menuList != null){
				TbSysMenu menu = (TbSysMenu)menuList.get(0);			
				for(int i = 0,len = menuList.size()-1;i<=len;i++){
					menu = (TbSysMenu)menuList.get(i);				
					if(menu.getParentId()!=null&&menu.getParentId().longValue()==rootParent.longValue()){
		%>
                <li><a href="<%=path %><%=menu.getUrl()%>" target="mainFrame"><%=menu.getName()%></a> </li>     
		<%					
					}//end if menu.getParentId()!=null&
				}//end for
			}//end if menu list
		%>
			</ul>
    	</div>
		<%	
		}//end if stringutil
		%>
            
            
        
</body>
</html>