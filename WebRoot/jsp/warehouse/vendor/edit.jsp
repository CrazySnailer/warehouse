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
			$('#vendorId').val('${entity.vendorId}');
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
	            <li><a href="<%=path %>/warehouse/vendor_list.action">供应商列表</a></li>
	            <li><a href="#none" class="current">${title}</a></li>
	        </ul>
	    </div>
	</div><!--div_tab_header-->
	
	<div class="div_tab_content_qry">
            <div id="tab_1" class="tab_content">
            
<form id="validateForm" name="validateForm" action="<%=path %>/warehouse/vendor_edit.action" method="post">
	<input type="hidden" name="qryHex" value="${qryHex}"/>
	<input type="hidden" name="event" value="${event}"/>
	<table border="0" cellspacing="0" cellpadding="0" class="editTable">
	
		<c:choose>
			<c:when test="${event=='ADD'}">
			</c:when>
			<c:otherwise>
		<tr>
			<td class="right" style="width:20%">供应商ID</td>
			<td style="width:80%">
			${entity.vendorId}
			<input type="hidden" name="entity.vendorId" value="${entity.vendorId}"  id="vendorId"/>
			</td>
		</tr>			
			</c:otherwise>
		</c:choose>
		
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">编码</label></td>
			<td style="width:80%">
			<input type="text" name="entity.vendorCode" class="formText {required: true,byteRangeLength:[0,20],messages: {required:'请输入编码'}}" id="vendorCode" value="${entity.vendorCode}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">名称</label></td>
			<td style="width:80%">
			<input type="text" name="entity.vendorName" class="formText {required: true,byteRangeLength:[0,200],messages: {required:'请输入名称'}}" id="vendorName" value="${entity.vendorName}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">简称</label></td>
			<td style="width:80%">
			<input type="text" name="entity.shortName" class="formText {required: true,byteRangeLength:[0,50],messages: {required:'请输入简称'}}" id="shortName" value="${entity.shortName}"/>
			
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
			<td class="right" style="width:20%"><label class="lable2">联系地址</label></td>
			<td style="width:80%">
			<input type="text" name="entity.address" class="formText {byteRangeLength:[0,150]}" id="address" value="${entity.address}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">电子邮箱</label></td>
			<td style="width:80%">
			<input type="text" name="entity.email" class="formText {byteRangeLength:[0,50]}" id="email" value="${entity.email}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">邮编</label></td>
			<td style="width:80%">
			<input type="text" name="entity.postcode" class="formText {byteRangeLength:[0,10]}" id="postcode" value="${entity.postcode}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">备注</label></td>
			<td style="width:80%">
			<input type="text" name="entity.remark" class="formText {byteRangeLength:[0,300]}" id="remark" value="${entity.remark}"/>
			
			</td>
		</tr>
		<tr>
		<th class="center" colspan="2"><input type="submit" class="btn1" value="保 存"/><input type="button" class="btn1" value="返 回" onclick="history.go(-1)"/></th>
		</tr>
	</table>	
</form>

		</div>            
  	</div><!--div_tab_content-->
</div><!--ajaxtabdiv-->
</body>
</html>