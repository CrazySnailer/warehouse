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
			$('#orderId').val('${entity.orderId}');
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
            
            
            
<form id="validateForm" name="validateForm" action="<%=path %>/warehouse/orderin_edit.action" method="post">
	<input type="hidden" name="qryHex" value="${qryHex}"/>
	<input type="hidden" name="event" value="${event}"/>
	<c:choose>
		<c:when test="${event=='ADD'}">
		</c:when>
		<c:otherwise>
		<input type="hidden" name="entity.orderId" value="${entity.orderId}"  id="orderId"/>			
		</c:otherwise>
	</c:choose>
	<table border="0" cellspacing="0" cellpadding="0" class="editTable">

		<tr>
			<td class="right" style="width:15%"><label class="requiredtext">*</label><label class="lable2">订单类型</label></td>
			<td style="width:35%">
		
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.orderType\" id=\"orderType\" class=\"formSelect {required: true,messages: {required:'请选择订单类型'}}\">","","wh_orderin_type")%>
			</td>
			
			<td class="right" style="width:15%"><label class="lable2">订单编号</label></td>
			<td class="left" style="width:35%">		
			<c:choose>
				<c:when test="${event=='ADD'}">
				自动生成
				</c:when>
				<c:otherwise>${entity.orderNo}</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%"><label class="requiredtext">*</label><label class="lable2">订单日期</label></td>
			<td style="width:35%">
			<input type="text" name="entity.orderDate" class="formText Wdate2 {required: true,messages: {required:'请输入订单日期'}}" id="orderDate" onclick="WdatePicker()" value="${entity.orderDateStr}"/>
			
			</td>
			<td class="right" style="width:15%"><label class="requiredtext">*</label><label class="lable2">订单处理时限</label></td>
			<td style="width:35%">			
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.limitHours\" id=\"limitHours\" class=\"formSelect {required: true,messages: {required:'请选择订单处理时限'}}\">","","wh_order_limit")%>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%"><label class="requiredtext">*</label><label class="lable2">仓库</label></td>
			<td style="width:35%">
			<select name="entity.whId" id="whId" class="formSelect {required: true,messages: {required:'请选择仓库'}}">
			<option value=""></option>
			<c:forEach items="${warehouseList}" var="b">
			<option value="${b.whId}">${b.whName}</option>
			</c:forEach>
			</select>
			
			</td>
			
			<td class="right" style="width:15%"><label class="lable2">客户单号</label></td>
			<td style="width:35%">
			<input type="text" name="entity.custOrderNo" class="formText {byteRangeLength:[0,50]}" id="custOrderNo" value="${entity.custOrderNo}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%"><label class="lable2">供应商</label></td>
			<td style="width:35%">
			<input type="text" name="entity.vendorId" class="formText {byteRangeLength:[0,12]}" id="vendorId" value="${entity.vendorId}"/>
			
			</td>
			<td class="right" style="width:15%"><label class="lable2">委托货主</label></td>
			<td style="width:35%">
			<input type="text" name="entity.trustId" class="formText {byteRangeLength:[0,12]}" id="trustId" value="${entity.trustId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%"><label class="requiredtext">*</label><label class="lable2">联系人</label></td>
			<td style="width:35%">
			<input type="text" name="entity.linker" class="formText {required: true,byteRangeLength:[0,50],messages: {required:'请输入联系人'}}" id="linker" value="${entity.linker}"/>
			
			</td>
			<td class="right" style="width:15%"><label class="requiredtext">*</label><label class="lable2">联系电话</label></td>
			<td style="width:35%">
			<input type="text" name="entity.linkerTel" class="formText {required: true,byteRangeLength:[0,30],messages: {required:'请输入联系电话'}}" id="linkerTel" value="${entity.linkerTel}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%"><label class="lable2">备注</label></td>
			<td style="width:85%" colspan="3">
			<input type="text" name="entity.remarks" class="formText {byteRangeLength:[0,200]}" id="remarks" value="${entity.remarks}"/>
			
			</td>
			
		</tr>
		<tr>
			<td class="right" style="width:15%"><label class="lable2">经办部门</label></td>
			<td style="width:35%">
			<input type="text" name="entity.handleDept" class="formText {byteRangeLength:[0,12]}" id="handleDept" value="${entity.handleDept}"/>
			
			</td>
			<td class="right" style="width:15%"><label class="lable2">经办人</label></td>
			<td style="width:35%">
			<input type="text" name="entity.handleId" class="formText {byteRangeLength:[0,12]}" id="handleId" value="${entity.handleId}"/>
			
			</td>
		</tr>
		<!--
		<tr>
			<td class="right" style="width:15%"><label class="lable2">审核时间</label></td>
			<td style="width:35%">
			<input type="text" name="entity.auditDate" class="formText {byteRangeLength:[0,7]}" id="auditDate" value="${entity.auditDateStr}"/>
			
			</td>
			<td class="right" style="width:15%"><label class="lable2">审核人</label></td>
			<td style="width:35%">
			<input type="text" name="entity.auditId" class="formText {byteRangeLength:[0,12]}" id="auditId" value="${entity.auditId}"/>
			
			</td>
		</tr>
		-->
		<tr>
		<th class="center" colspan="4"><input type="submit" class="btn1" value="保 存"/><input type="button" class="btn1" value="返 回" onclick="history.go(-1)"/></th>
		</tr>
	</table>	
</form>



		</div>            
  	</div>
</div>
</body>
</html>