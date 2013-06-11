<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/include/head.jsp"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>菜单管理</title>
<link href="<%=path%>/res/css/base.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/js/ztree/css/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/js/ztree/css/demo.css" rel="stylesheet" type="text/css"/>

<script language="javascript" src="<%=path%>/res/js/jquery.js"></script>
<script language="javascript" src="<%=path%>/res/js/ztree/js/jquery.ztree.core-3.0.js"></script>

<script language="JavaScript"><!--
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
		parent.$('#${param.parentId}').val(treeNode.id);
		parent.$('#${param.parentName}').val(treeNode.name);
		parent.$.colorbox.close();	
	}

	var zNodes =[
		<c:forEach items="${list}" var="b" varStatus="myVar">
		<c:if test="${myVar.index == 0}">{ id:${b.menuId}, pId:0, name:"${b.name}",treeNo:"${b.treeNo}", open:true,isParent:true}<c:if test="${!myVar.last}">,</c:if></c:if>
		<c:if test="${myVar.index != 0}">{ id:${b.menuId}, pId:${b.parent.menuId}, name:"${b.name}",treeNo:"${b.treeNo}"}<c:if test="${!myVar.last}">,</c:if></c:if>
		</c:forEach>
	];

	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	
--></script>
</head>
<body>

<form id="editForm" name="editForm" method="post" target="rightMenuTree" action="<%=path %>/admin/um/menu_toEdit.action">
	<input type="hidden" name="entity.menuId" id="edit_menuId" value=""/>
</form>

<div class="box">
	<div class="box-content">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</div>
</body>   
 </html>