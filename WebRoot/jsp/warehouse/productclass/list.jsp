<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="com.calf.framework.util.CodeNameUtils"%>
<%@page import="com.calf.framework.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/include/head.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/res/css/base.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/css/colorbox.css" rel="stylesheet" type="text/css"/>
<%@ include file="/jsp/include/basejs.jsp" %>
<script type="text/javascript">
	function gotoEdit(classId){
		$('#edit_classId').val(classId);
		$('#editForm').submit();
	}
	function gotoDelete(classId){
		jConfirm('删除该数据将不可恢复，确认删除?', '确认删除', function(cresult) {
			if(cresult){
				$.launchPage('<%=path %>/warehouse/productclass_delete.action?entity.classId='+classId+'&data='+Math.random());
			}
		});	
	}
	
	function gotoView(classId){
		$.colorbox({
			href:'<%=path %>/warehouse/productclass_toView.action?entity.classId='+classId+'&data='+Math.random(),
			iframe:true,
			title: '查看商品分类',
			width:"80%", 
			height:"90%"
		});
	}
	
	$().ready( function() {	
		<c:if test="${param.refresh=='true'}">
		var mf = window.parent.window.document.getElementById("leftMenuTree");
		mf.src="<%=path%>/warehouse/productclass_tree.action?focus=${entity.classId}&data="+Math.random();
		</c:if>
	});
</script>
</head>
<body>
<div id="querytip" class="qrytip">loading....</div>

<form id="editForm" name="editForm" method="post" action="<%=path %>/warehouse/productclass_toEdit.action">
	<input type="hidden" name="entity.classId" id="edit_classId" value=""/>
	<input type="hidden" name="qryHex" value="${qry.qryHex}"/>
</form>

<div class="ajaxtabdiv">
	<div class="div_tab_header">
	    <div class="div_tab_header_1">
	        <div class="corner-1-report_nav"></div>
	        <div class="corner-2-report_nav"></div>
	        <ul class="rptMenu ajaxtabs">		            
	            <li><a href="#tab_1">商品分类列表</a></li>
	            <shiro:hasPermission name="wh:productClass:edit"><li><a href="<%=path %>/warehouse/productclass_toAdd.action?entity.parentId=${entity.classId}">新增</a></li></shiro:hasPermission>
	        </ul>
	    </div>
	</div><!--div_tab_header-->
	
	<div class="div_tab_content_qry">
            <div id="tab_1" class="tab_content">
<form id="listForm" name="listForm" method="post"  action="<%=path %>/warehouse/productclass_list.action">
	
	<c:choose>
		<c:when test="${list== null || fn:length(list) == 0}">
			<div class="nodatafound2">
				没有子节点信息<shiro:hasPermission name="wh:productClass:edit">，您可以
				<input type="button" class="btn1" onclick="$.launchPage('<%=path %>/warehouse/productclass_toAdd.action?entity.parentId=${entity.classId}')" value="新增子节点"/>
				</shiro:hasPermission>
			</div>
		</c:when>
		<c:otherwise>
		<table border="0" cellspacing="0" cellpadding="0" class="listTable">
			<tr>
				<th>分类编码</th>
				<th>分类名称</th>
				<th>排序号</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${list}" var="b">
			<tr>
				<td class="center">${b.classCode}</td>
				<td class="center">${b.className}</td>
				<td class="center">${b.orderNum}</td>
				<td class="center" nowrap="true">
				
				<shiro:hasPermission name="um:menu:edit">
				<c:if test="${b.classId!=1}">
				<a href="#none" title="修改" onclick="gotoEdit('${b.classId}')">修改</a>
				<a href="#none" title="删除" onclick="gotoDelete('${b.classId}')">删除</a>
				</c:if>
				</shiro:hasPermission>
				<a href="#none" title="查看" onclick="gotoView('${b.classId}')">查看</a>
				
				</td>
			</tr>
			</c:forEach>
		</table>
		</c:otherwise>
		</c:choose>
</form>
		</div><!--tab_content-->
  	</div><!--div_tab_content_qry-->
</div><!--ajaxtabdiv-->
</body>
</html>