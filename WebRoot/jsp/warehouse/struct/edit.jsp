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
				href:'<%=path %>/warehouse/struct_select.action?parentId=parentId&parentName=parentName&data='+Math.random(),
				iframe:true,
				title:'选择上级菜单',
				width:"80%",
				height:"90%"
			});
		});
		
		$('#btnAdd').click(function (){
			document.location.href = "<%=path %>/warehouse/struct_toAdd.action?entity.parentId=${entity.structId}";
		});
		<shiro:hasPermission name="sys:admin:oper">
		$('#btnrebuild').click(function (){
			document.location.href = "<%=path %>/warehouse/struct_forceBuild.action?entity.structId=${entity.structId}";
		});
		</shiro:hasPermission>
		
		$('#structId').val('${entity.structId}');
	});
</script>
</head>
<body>
<div class="ajaxtabdiv11">
	<div class="div_tab_header">
	    <div class="div_tab_header_1">
	        <div class="corner-1-report_nav"></div>
	        <div class="corner-2-report_nav"></div>
	        <ul class="ajaxtabs">		            
				<li><a href="<%=path %>/warehouse/struct_list.action?entity.structId=${entity.parent.classId}">商品分类列表</a></li>
	            <li><a href="#none" class="current">${title}</a></li>
	        </ul>
	    </div>
	</div><!--div_tab_header-->
	
	<div class="div_tab_content_qry">
            <div id="tab_1" class="tab_content">
            
<form id="validateForm" name="validateForm" action="<%=path %>/warehouse/struct_edit.action" method="post">
	<input type="hidden" name="qryHex" value="${qryHex}"/>
	<input type="hidden" name="event" value="${event}"/>
	<table border="0" cellspacing="0" cellpadding="0" class="editTable">
	
		<c:choose>
			<c:when test="${event=='ADD'}">
			</c:when>
			<c:otherwise>
		<tr>
			<td class="right" style="width:20%">仓库结构ID</td>
			<td style="width:80%">
			${entity.structId}
			<input type="hidden" name="entity.structId" value="${entity.structId}"  id="structId"/>
			</td>
		</tr>			
			</c:otherwise>
		</c:choose>
		
		<tr>
			<td class="right" style="width:25%;"><label class="requiredtext">*</label><label class="lable2">上级</label></td>
			<td style="width:75%;">
				<input type="text" name="parentName" class="formTextL ro" readonly="true" id="parentName" value="${entity.parent.name}"/>			
				<c:if test="${entity.structId!=1}">
				<a href="#none" class="button" title="选择上级" id="selectParent"><span>选择</span></a>
				</c:if>		
				<input type="hidden" name="entity.parentId" class="{required: true,byteRangeLength:[0,12],messages: {required:'请输入上级菜单'}}" id="parentId" value="${entity.parent.structId}"/>
			</td>
		</tr>
		
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">仓库结构ID</label></td>
			<td style="width:80%">
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">父级ID</label></td>
			<td style="width:80%">
			<input type="text" name="entity.parentId" class="formTextL {byteRangeLength:[0,12]}" id="parentId" value="${entity.parentId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">编号</label></td>
			<td style="width:80%">
			<input type="text" name="entity.structCode" class="formTextL {required: true,byteRangeLength:[0,20],messages: {required:'请输入编号'}}" id="structCode" value="${entity.structCode}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">名称</label></td>
			<td style="width:80%">
			<input type="text" name="entity.structName" class="formTextL {required: true,byteRangeLength:[0,100],messages: {required:'请输入名称'}}" id="structName" value="${entity.structName}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">所属仓库</label></td>
			<td style="width:80%">
			<input type="text" name="entity.whId" class="formTextL {required: true,byteRangeLength:[0,12],messages: {required:'请输入所属仓库'}}" id="whId" value="${entity.whId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">类型  P储位 F货架 A库区 W仓库</label></td>
			<td style="width:80%">
			<input type="text" name="entity.structType" class="formTextL {required: true,byteRangeLength:[0,2],messages: {required:'请输入类型  P储位 F货架 A库区 W仓库'}}" id="structType" value="${entity.structType}"/>
			
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.structType\" id=\"structType\" class=\"formSelectL {required: true,messages: {required:'请选择类型  P储位 F货架 A库区 W仓库'}}\">","","g_wh_struct_type")%>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">排序号</label></td>
			<td style="width:80%">
			<input type="text" name="entity.orderNum" class="formTextL {required: true,byteRangeLength:[0,5],messages: {required:'请输入排序号'}}" id="orderNum" value="${entity.orderNum}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">全名</label></td>
			<td style="width:80%">
			<input type="text" name="entity.fullName" class="formTextL {byteRangeLength:[0,100]}" id="fullName" value="${entity.fullName}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">树编号</label></td>
			<td style="width:80%">
			<input type="text" name="entity.treeNo" class="formTextL {byteRangeLength:[0,30]}" id="treeNo" value="${entity.treeNo}"/>
			
			</td>
		</tr>
		<tr>
		<th class="center" colspan="2">
		<input type="submit" class="btn1" value="保 存"/>
		<input type="button" class="btn1" value="返 回" onclick="history.go(-1)"/>
		<shiro:hasPermission name="sys:admin:oper">
		<input type="button" class="btn1" value="重建排序" id="btnrebuild"/>
		</shiro:hasPermission>
		</th>
		</tr>
	</table>	
</form>

		</div><!--div_tab_content-->      
  	</div><!--div_tab_content_qry-->
</div><!--ajaxtabdiv-->
</body>
</html>