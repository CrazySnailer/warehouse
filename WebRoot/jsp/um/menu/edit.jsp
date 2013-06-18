<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="com.calf.framework.util.CodeNameUtils"%>
<%@ include file="/jsp/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
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
	$(document).ready(function(){
		$('#selectParent').click(function (){
			$.colorbox({
				href:'<%=path %>/admin/um/menu_select.action?parentId=parentId&parentName=parentName&data='+Math.random(),
				iframe:true,
				title:'选择上级菜单',
				width:"80%",
				height:"90%"
			});
		});
		
		$('#btnAdd').click(function (){
			document.location.href = "<%=path %>/admin/um/menu_toAdd.action?entity.parentId=${entity.menuId}";
		});
		
		$('#btnDel').click(function (){
			jConfirm('确认删除该菜单?', '确认删除', function(cresult) {
			if(cresult){
					document.location.href = "<%=path %>/admin/um/menu_delete.action?entity.menuId=${entity.menuId}";
				}
			});
		});
		
		$('#btnrebuild').click(function (){
			document.location.href = "<%=path %>/admin/um/menu_forceBuild.action?entity.menuId=${entity.menuId}";
		});
		
		

		
		$('#menuType').val('${entity.menuType}');
	});
</script>
</head>
<body>

<div class="ajaxtabdiv11">
	<div class="div_tab_header">
	    <div class="div_tab_header_1">
	        <div class="corner-1-report_nav"></div>
	        <div class="corner-2-report_nav"></div>
	        <ul class="rptMenu ajaxtabs">
	            <li><a href="<%=path %>/admin/um/menu_list.action?entity.menuId=${entity.parentId}">列表</a></li>
	            <li><a href="#none" class="current">${title}</a></li>
	        </ul>
	    </div>
	</div><!--div_tab_header-->
	
	<div class="div_tab_content_qry">
            <div id="tab_1" class="tab_content">
            
            
<form id="validateForm" name="validateForm" action="<%=path %>/admin/um/menu_edit.action" method="post">
	<input type="hidden" name="qryHex" value="${qryHex}"/>
	<input type="hidden" name="event" value="${event}"/>
	<c:if test="${event=='EDIT'}">
	<input type="hidden" name="entity.menuId" class="formText {required: true,byteRangeLength:[0,12],messages: {required:'请输入菜单ID'}}" id="menuId" value="${entity.menuId}"/>
	</c:if>
	<table border="0" cellspacing="0" cellpadding="0" class="editTable">
		
		<tr>
			<td class="right" style="width:25%;"><label class="requiredtext">*</label><label class="lable2">上级菜单</label></td>
			<td style="width:75%;">
				<input type="text" name="parentName" class="formTextL ro" readonly="true" id="parentName" value="${entity.parent.name}"/>			
				<c:if test="${entity.menuId!=1}">
				<a href="#none" class="button" title="选择上级菜单" id="selectParent"><span>选择</span></a>
				</c:if>		
				<input type="hidden" name="entity.parentId" class="formTextL {required: true,byteRangeLength:[0,12],messages: {required:'请输入上级菜单'}}" id="parentId" value="${entity.parent.menuId}"/>
			</td>
		</tr>
		<tr>
			<td class="right"><label class="requiredtext">*</label><label class="lable2">菜单名称</label></td>
			<td>
			<input type="text" name="entity.name" class="formTextL {required: true,byteRangeLength:[0,100],messages: {required:'请输入菜单名称'}}" id="name" value="${entity.name}"/>
			</td>
		</tr>
		
		<tr>
			<td class="right"><label class="requiredtext">*</label><label class="lable2">类型</label></td>
			<td>
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.menuType\" id=\"menuType\" class=\"formSelectL {required: true,messages: {required:'请选择类型'}}\">","","g_um_menu_type")%>
			</td>
		</tr>
				
		<tr>
			<td class="right"><label class="requiredtext">*</label><label class="lable2">URL</label></td>
			<td>
			<input type="text" name="entity.url" class="formTextL {byteRangeLength:[0,100]}" id="url" value="${entity.url}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right"><label class="requiredtext">*</label><label class="lable2">唯一标识值</label></td>
			<td>
			<input type="text" name="entity.idVal" class="formTextL {required: true,byteRangeLength:[0,20],messages: {required:'请输入唯一标识值'}}" id="idVal" value="${entity.idVal}"/>
			</td>
		</tr>
		
		<tr>
			<td class="right"><label class="requiredtext">*</label><label class="lable2">排序号</label></td>
			<td>
			<input type="text" name="entity.orderNum" class="formTextL {required: true,byteRangeLength:[0,5],messages: {required:'请输入排序号'}}" id="orderNum" value="${entity.orderNum}"/>
			${entity.treeNo}
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<shiro:hasPermission name="um:menu:edit">
					<c:if test="${entity.menuId!=1}">
					<input type="submit" class="btn1" value="保 存"/>
					</c:if>
				</shiro:hasPermission>
			
				<shiro:hasPermission name="sys:admin:oper">
				<input type="button" class="btn1" value="重建排序" id="btnrebuild"/>
				</shiro:hasPermission>
				<!--<input type="button" class="buttonInput" value="批量修改" id="btnBatchEdit"/>-->
				<span id="savetip" class="ajaxtip">数据保存中，请稍候……</span>
			</td>
		</tr>
		
	</table>	
</form>

			</div><!--div_tab_content-->            
  	</div><!--div_tab_content_qry-->
</div><!--ajaxtabdiv-->

</body>
</html>