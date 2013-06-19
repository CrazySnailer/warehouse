<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/include/head.jsp"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品分类</title>
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
		$('#edit_classId').val(treeNode.id);
		$('#editForm').submit();
	}

	var zNodes =[
		<c:forEach items="${list}" var="b" varStatus="myVar">
		<c:if test="${myVar.index == 0}">{ id:${b.classId}, pId:0, name:"${b.className}",treeNo:"${b.treeNo}", open:true,isParent:true}<c:if test="${!myVar.last}">,</c:if></c:if>
		<c:if test="${myVar.index != 0}">{ id:${b.classId}, pId:${b.parent.classId}, name:"${b.className}",treeNo:"${b.treeNo}"}<c:if test="${!myVar.last}">,</c:if></c:if>
		</c:forEach>
	];

	$(document).ready(function(){
		var treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		<c:if test="${param.focus!=null&&param.focus!=''}">
		var node = treeObj.getNodeByParam("id", ${param.focus}, null);
		treeObj.selectNode(node);
		if(node.isParent){
			treeObj.expandNode(node, true, false, true);
		}
		</c:if>
	});
	
--></script>
</head>
<body>

<form id="editForm" name="editForm" method="get" target="rightMenuTree" action="<%=path %>/warehouse/productclass_list.action">
	<input type="hidden" name="entity.classId" id="edit_classId" value=""/>
</form>

<div class="div_tab_header_box">
    <div class="div_tab_header_box_1">
        <div class="corner-1-report_nav"></div>
        <div class="corner-2-report_nav"></div>
        
        <div class="box-header">商品分类管理</div>
        <div class="box-content">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
    </div>
</div>
		
</body>   
 </html>