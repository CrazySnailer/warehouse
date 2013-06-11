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
<link href="<%=path%>/res/js/ztree/css/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/js/ztree/css/demo.css?20120923" rel="stylesheet" type="text/css"/>

<style type="text/css">

.ztree li button.ico_docu {
margin-right: 2px;
background: url('<%=path%>/res/js/ztree/css/img/tree-leaf.gif');
vertical-align: middle;
}


body {
 font-family: arial, 宋体, serif;
 font-size:12px;
 margin:5px;
}

#nav {
 width:148px;
 line-height: 24px; 
 list-style-type: none;
 text-align:left;
 display: block;
 
    /*定义整个ul菜单的行高和背景色*/
}
/*==================一级目录===================*/
#nav a {
 --width: 160px; 
 display: block;
 padding-left:20px;
}
#nav li {
 background:#FFF; 
 --border-bottom:#FFF 1px solid;
 float:left;
}

#nav li a.levela{
 	margin-bottom: -1px;
	height: 27px;
	padding: 0 12px;
	border: solid #E6E6E6;
	border-width: 1px 0;
	background: url(../../res/images/bg_jdleft.jpg) #E6E6E6 repeat-x 0 -30px;
	font-weight: bold;
	line-height: 27px;
	cursor: pointer;
}
#nav li a.levela:hover{
	background: url(../../res/images/bg_jdleft.jpg) #E6E6E6 repeat-x 0 -30px;
	background-position: 0 0;
}

#nav li a:hover{
 background:#CC0000; /*一级目录onMouseOver显示的背景色*/
}
#nav a:link  {
 color:#666; text-decoration:none;
}
#nav a:visited  {
 color:#666;text-decoration:none;
}
#nav a:hover  {
 color:#000;text-decoration:none;font-weight:bold;
}
/*==================二级目录===================*/
#nav li ul {
 list-style:none;
 text-align:left;
}
#nav li ul li{ 
 background: #EBEBEB; /*二级目录的背景色*/
}
#nav li ul a{
         padding-left:20px;
         width:148px;
 /* padding-left二级目录中文字向右移动，但Width必须重新设置=(总宽度-padding-left)*/
}
/*下面是二级目录的链接样式*/
#nav li ul a:link  {
 color:#666; text-decoration:none;
}
#nav li ul a:visited  {
 color:#666;text-decoration:none;
}
#nav li ul a:hover {
 color:#F3F3F3;
 text-decoration:none;
 font-weight:normal;
 background:#CC0000;
 /* 二级onmouseover的字体颜色、背景色*/
}
/*==============================*/
#nav li:hover ul {
 left: auto;
}
#nav li.sfhover ul {
 left: auto;
}
#content {
 clear: left; 
}
-->



</style>


<script language="javascript" src="<%=path%>/res/js/jquery.js"></script>
<script language="javascript" src="<%=path%>/res/js/ztree/js/jquery.ztree.core-3.0.js"></script>
<script language="JavaScript">

	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: zTreeOnClick
		}
	};
	
	function zTreeOnClick(event, treeId, treeNode) {
		if(treeNode.url_a){
       		var mainFrame = window.parent.window.document.getElementById("mainFrame");
       		mainFrame.src="<%=path%>"+treeNode.url_a;	           
       	}
	}
	
	var zNodes = [
	<%
		AdminUserInfo userInfo = (AdminUserInfo)session.getAttribute(Constants.ADMIN_SESSION_USER_INFO);
		
		List menuList = userInfo.getMenuList();
		
		
		if(menuList != null){		
			StringBuffer menuStr = new StringBuffer(); 
			for(int i = 0,len = menuList.size()-1;i<=len;i++){
				TbSysMenu menu = (TbSysMenu)menuList.get(i);
			
				menuStr.append("{id:");
				menuStr.append(menu.getMenuId());
				
				if(i==0){
					menuStr.append(",pId:0,isParent:true,open:'true'");
				}else{
					menuStr.append(",pId:");					
					menuStr.append(menu.getParentId());
				}	
				menuStr.append(",name:'");
				if("1".equals(menu.getIsLeaf())){
					if(StringUtils.isBlank(menu.getUrl())){
						menuStr.append(menu.getName()+"(待)");
					}else{
						menuStr.append(menu.getName());
					}
				}else{
					menuStr.append(menu.getName());
				}
				
				menuStr.append("',");
				if(StringUtils.isBlank(menu.getUrl())){
					menuStr.append("url_a:''}");
				}else{
					menuStr.append("url_a:'");
	           		menuStr.append(menu.getUrl());
	           		menuStr.append("'}");
				}
				
				if(i!=len){
           			menuStr.append(",\r\n");
           		}
			}
			out.println(menuStr.toString());
		}
	%>
	];
	
	var menutree = null;
	$().ready(function() {
		//$.fn.zTree.init($("#treeDemo"), setting, zNodes);
        
        
    
  var allPanels = $('#nav > li');
  //allPanels.find('ul').show();
    
  $('#nav > li > a').click(function() {
    //allPanels.slideUp();
    $(this).parent().find('ul').slideToggle();
    return false;
  });


	});
</script> 
<body>
<!--<div class="box">
	<div class="box-header">功能菜单</div>	
	<ul id="treeDemo" class="ztree"></ul>
</div>-->

<ul id="nav">
<li><a href="#Menu=ChildMenu1" class="levela" onclick="DoMenu('ChildMenu1')">懒人建站</a>
 <ul id="ChildMenu1">
 <li><a href="http://www.51xuediannao.com">懒人建站1</a></li>
 <li><a href="http://www.51xuediannao.com/js">js代码2</a></li>
 <li><a href="http://www.51xuediannao.com">懒人建站3</a></li>
 <li><a href="http://www.51xuediannao.com/js">js代码4</a></li>
 <li><a href="#">懒人建站</a></li>
 <li><a href="#">懒人建站</a></li>
 </ul>
</li>
<li><a href="#Menu=ChildMenu2" class="levela" onclick="DoMenu('ChildMenu2')">懒人建站</a>
 <ul id="ChildMenu2">
 <li><a href="#">懒人建站xxxx</a></li>
 <li><a href="#">懒人建站xxx</a></li>
 <li><a href="#">懒人建站xx</a></li>
 <li><a href="#">懒人建站xx</a></li>
 <li><a href="#">懒人建站xx</a></li>
 <li><a href="#">懒人建站xx</a></li>
 </ul>
</li>
</ul>

</body>
</html>