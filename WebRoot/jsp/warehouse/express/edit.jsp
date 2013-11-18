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
			$('#expressId').val('${entity.expressId}');
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
            
            
            
<form id="validateForm" name="validateForm" action="<%=path %>/warehouse/express_edit.action" method="post">
	<input type="hidden" name="qryHex" value="${qryHex}"/>
	<input type="hidden" name="event" value="${event}"/>
	<table border="0" cellspacing="0" cellpadding="0" class="editTable">
	
		<c:choose>
			<c:when test="${event=='ADD'}">
			</c:when>
			<c:otherwise>
		<tr>
			<td class="right" style="width:20%">物流公司ID</td>
			<td style="width:80%">
			${entity.expressId}
			<input type="hidden" name="entity.expressId" value="${entity.expressId}"  id="expressId"/>
			</td>
		</tr>			
			</c:otherwise>
		</c:choose>
		
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">物流公司ID</label></td>
			<td style="width:80%">
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">物流公司名称</label></td>
			<td style="width:80%">
			<input type="text" name="entity.expressName" class="formText {required: true,byteRangeLength:[0,200],messages: {required:'请输入物流公司名称'}}" id="expressName" value="${entity.expressName}"/>
			
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
			<input type="text" name="entity.linkAddress" class="formText {byteRangeLength:[0,150]}" id="linkAddress" value="${entity.linkAddress}"/>
			
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
  	</div>
</div>
</body>
</html>