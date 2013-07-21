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
	function gotoEdit(structId){
		$('#edit_structId').val(structId);
		$('#editForm').submit();
	}
	function gotoDelete(structId){
		jConfirm('删除该数据将不可恢复，确认删除?', '确认删除', function(cresult) {
			if(cresult){
				$.launchPage('<%=path %>/warehouse/struct_delete.action?entity.structId='+structId+'&data='+Math.random());
			}
		});	
	}
	
	function gotoView(structId){
		$.colorbox({
			href:'<%=path %>/warehouse/struct_toView.action?entity.structId='+structId+'&data='+Math.random(),
			iframe:true,
			title: '查看商品分类',
			width:"80%", 
			height:"90%"
		});
	}
	
	$().ready( function() {	
		<c:if test="${param.refresh=='true'}">
		var mf = window.parent.window.document.getElementById("leftMenuTree");
		mf.src="<%=path%>/warehouse/struct_tree.action?focus=entity.${table.key.javaName}&data="+Math.random();
		</c:if>
	});
</script>
</head>
<body>
<div id="querytip" class="qrytip">loading....</div>

<form id="editForm" name="editForm" method="post" action="<%=path %>/warehouse/struct_toEdit.action">
	<input type="hidden" name="entity.structId" id="edit_structId" value=""/>
	<input type="hidden" name="qryHex" value="${qry.qryHex}"/>
</form>

<div class="ajaxtabdiv">
	<div class="div_tab_header">
	    <div class="div_tab_header_1">
	        <div class="corner-1-report_nav"></div>
	        <div class="corner-2-report_nav"></div>
	        <ul class="rptMenu ajaxtabs">		            
	            <li><a href="#tab_1">商品分类列表</a></li>
	            <shiro:hasPermission name="wh:struct:edit"><li><a href="<%=path %>/warehouse/struct_toAdd.action?entity.parentId=${entity.structId}">新增</a></li></shiro:hasPermission>
	        </ul>
	    </div>
	</div><!--div_tab_header-->
	
	<div class="div_tab_content_qry">
            <div id="tab_1" class="tab_content">
<form id="listForm" name="listForm" method="post"  action="<%=path %>/warehouse/struct_list.action">
	
		<table border="0" cellspacing="0" cellpadding="0" class="listTable">
			<tr>
				<th>编号</th>
				<th>名称</th>
				<th>所属仓库</th>
				<th>类型  P储位 F货架 A库区 W仓库</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${list}" var="b">
			<tr>
				<td class="center">${b.structCode}</td>
				<td class="center">${b.structName}</td>
				<td class="center">${b.whId}</td>
				<td class="center">${b.structTypeStr}</td>
				<td class="center" nowrap="true">
				
				<shiro:hasPermission name="um:menu:edit">
				<a href="#none" title="修改" onclick="gotoEdit('${b.structId}')">修改</a>
				<a href="#none" title="删除" onclick="gotoDelete('${b.structId}')">删除</a>
				</shiro:hasPermission>
				<a href="#none" title="查看" onclick="gotoView('${b.structId}')">查看</a>
				
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