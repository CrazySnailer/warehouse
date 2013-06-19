<%@ page language="java" contentType="text/html; charset=utf-8"%>
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
	function gotoEdit(menuId){
		$('#edit_menuId').val(menuId);
		$('#editForm').submit();
	}
	function gotoDelete(menuId){
		jConfirm('删除该数据将不可恢复，确认删除?', '确认删除', function(cresult) {
			if(cresult){
				$.launchPage('<%=path %>/admin/um/menu_delete.action?entity.menuId='+menuId+'&data='+Math.random());
			}
		});	
	}
	
	function gotoView(menuId){
		$.colorbox({
			href:'<%=path %>/admin/um/menu_toView.action?entity.menuId='+menuId+'&data='+Math.random(),
			iframe:true,
			title: '查看菜单详情',
			width:"80%", 
			height:"90%"
		});
	}
	
	$().ready( function() {	
		<c:if test="${param.refresh=='true'}">
		var mf = window.parent.window.document.getElementById("leftMenuTree");
		mf.src="<%=path%>/admin/um/menu_tree.action?focus=${entity.menuId}&data="+Math.random();
		</c:if>
	});
</script>
</head>
<body>

<div class="ajaxtabdiv">
	<div class="div_tab_header">
	    <div class="div_tab_header_1">
	        <div class="corner-1-report_nav"></div>
	        <div class="corner-2-report_nav"></div>
	        <ul class="rptMenu ajaxtabs">		            
	            <li><a href="#tab_1">列表</a></li>
	            <shiro:hasPermission name="um:menu:edit"><li><a href="<%=path %>/admin/um/menu_toAdd.action?entity.parentId=${entity.menuId}">新增下级</a></li></shiro:hasPermission>
	        </ul>
	    </div>
	</div><!--div_tab_header-->
	
	<div class="div_tab_content_qry">
            <div id="tab_1" class="tab_content">
            
<div id="querytip" class="qrytip">loading....</div>

<form id="editForm" name="editForm" method="post" action="<%=path %>/admin/um/menu_toEdit.action">
	<input type="hidden" name="entity.menuId" id="edit_menuId" value=""/>
	<input type="hidden" name="qryHex" value="${qry.qryHex}"/>
</form>

<form id="listForm" name="listForm" method="post"  action="<%=path %>/admin/um/menu_saveList.action">
		<input type="hidden" name="entity.menuId" id="list_menuId" value="${entity.menuId}"/>
		<table border="0" cellspacing="0" cellpadding="0" class="listTable">
			<tr>
				<th>菜单名称</th>
				<th>菜单URL</th>
				<th>唯一标识值</th>
				<th>排序号</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${list}" var="b" varStatus="i">
			<tr>
				<td class="center">${b.name}</td>
				<td class="center">${b.url}</td>
				<td class="center">${b.idVal}</td>
				<td class="center">${b.orderNum}</td>
				<td class="center">
				
				<shiro:hasPermission name="um:menu:edit">
				<a href="#none" title="修改" onclick="gotoEdit('${b.menuId}')">修改</a>
				<a href="#none" title="删除" onclick="gotoDelete('${b.menuId}')">删除</a>
				</shiro:hasPermission>
				<a href="#none" title="查看" onclick="gotoView('${b.menuId}')">查看</a>				
				</td>
			</tr>
			</c:forEach>
		</table>
</form>
</div><!--tab_content-->
  	</div><!--div_tab_content_qry-->
</div><!--ajaxtabdiv-->
</body>
</html>