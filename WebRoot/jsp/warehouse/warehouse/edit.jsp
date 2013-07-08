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
<%@ include file="/jsp/include/basejs.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
			$('#whId').val('${entity.whId}');
	});
</script>
</head>
<body>
<div class="div_tab_header_box">
    <div class="div_tab_header_box_1">
        <div class="corner-1-report_nav"></div>
        <div class="corner-2-report_nav"></div>
        
        <div class="box-header left">${title}</div>
        <div class="box-content">
            
<form id="validateForm" name="validateForm" action="<%=path %>/warehouse/warehouse_edit.action" method="post">
	<input type="hidden" name="qryHex" value="${qryHex}"/>
	<input type="hidden" name="event" value="${event}"/>
	<table border="0" cellspacing="0" cellpadding="0" class="editTable">
	
		<c:choose>
			<c:when test="${event=='ADD'}">
			</c:when>
			<c:otherwise>
		<tr>
			<td class="right" style="width:20%">仓库ID</td>
			<td style="width:80%">
			${entity.whId}
			<input type="hidden" name="entity.whId" value="${entity.whId}"  id="whId"/>
			</td>
		</tr>			
			</c:otherwise>
		</c:choose>
		
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">编号</label></td>
			<td style="width:80%">
			<input type="text" name="entity.whCode" class="formText {required: true,byteRangeLength:[0,20],messages: {required:'请输入编号'}}" id="whCode" value="${entity.whCode}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">仓库名称</label></td>
			<td style="width:80%">
			<input type="text" name="entity.whName" class="formText {required: true,byteRangeLength:[0,100],messages: {required:'请输入仓库名称'}}" id="whName" value="${entity.whName}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">所属仓库</label></td>
			<td style="width:80%">
			<input type="text" name="entity.parentId" class="formText {byteRangeLength:[0,12]}" id="parentId" value="${entity.parentId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">仓库类型</label></td>
			<td style="width:80%">
		
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.whType\" id=\"whType\" class=\"formSelect {required: true,messages: {required:'请选择仓库类型'}}\">","","g_wh_wh_type")%>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">使用部门</label></td>
			<td style="width:80%">
			<input type="text" name="entity.useDeptId" class="formText {required: true,byteRangeLength:[0,12],messages: {required:'请输入使用部门'}}" id="useDeptId" value="${entity.useDeptId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">管理模式</label></td>
			<td style="width:80%">			
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.adminMode\" id=\"adminMode\" class=\"formSelect {required: true,messages: {required:'请选择管理模式'}}\">","","g_wh_admin_type")%>

			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">联系地址</label></td>
			<td style="width:80%">
			<input type="text" name="entity.address" class="formText {byteRangeLength:[0,150]}" id="address" value="${entity.address}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">联系人</label></td>
			<td style="width:80%">
			<input type="text" name="entity.linker" class="formText {byteRangeLength:[0,30]}" id="linker" value="${entity.linker}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">联系电话</label></td>
			<td style="width:80%">
			<input type="text" name="entity.linkerTel" class="formText {byteRangeLength:[0,50]}" id="linkerTel" value="${entity.linkerTel}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">电子邮箱</label></td>
			<td style="width:80%">
			<input type="text" name="entity.email" class="formText {byteRangeLength:[0,50]}" id="email" value="${entity.email}"/>
			
			</td>
		</tr>
		<tr>
		<th class="center" colspan="2"><input type="submit" class="btn1" value="保 存"/><input type="button" class="btn1" value="返 回" onclick="history.go(-1)"/></th>
		</tr>
	</table>	
</form>

		</div>
    </div>
</div>
</body>
</html>