<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/include/head.jsp"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>机构管理</title>
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
		$('#edit_deptId').val(treeNode.id);
		$('#editForm').submit();
	}

	var zNodes =[
		<c:forEach items="${list}" var="b" varStatus="myVar">
		<c:if test="${myVar.index == 0}">{ id:${b.deptId}, pId:0, name:"${b.deptName}",treeNo:"${b.treeNo}", open:true,isParent:true}<c:if test="${!myVar.last}">,</c:if></c:if>
		<c:if test="${myVar.index != 0}">{ id:${b.deptId}, pId:${b.parentDeptId}, name:"${b.deptName}",treeNo:"${b.treeNo}"}<c:if test="${!myVar.last}">,</c:if></c:if>
		</c:forEach>
	];

	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	
--></script>
</head>
<body>

<form id="editForm" name="editForm" method="post" target="rightDeptTree" action="<%=path %>/admin/um/dept_toEdit.action">
	<input type="hidden" name="entity.deptId" id="edit_deptId" value=""/>
</form>

<div class="box">
	<h2 class="box-header">机构管理</h2>
	<ul id="treeDemo" class="ztree"></ul>
</div>
</body>   
 </html>