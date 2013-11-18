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
			$('#upId').val('${entity.upId}');
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
            
            
            
<form id="validateForm" name="validateForm" action="<%=path %>/warehouse/upframe_edit.action" method="post">
	<input type="hidden" name="qryHex" value="${qryHex}"/>
	<input type="hidden" name="event" value="${event}"/>
	<table border="0" cellspacing="0" cellpadding="0" class="editTable">
	
		<c:choose>
			<c:when test="${event=='ADD'}">
			</c:when>
			<c:otherwise>
		<tr>
			<td class="right" style="width:20%">上架单ID</td>
			<td style="width:80%">
			${entity.upId}
			<input type="hidden" name="entity.upId" value="${entity.upId}"  id="upId"/>
			</td>
		</tr>			
			</c:otherwise>
		</c:choose>
		
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">上架单ID</label></td>
			<td style="width:80%">
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">验货单ID</label></td>
			<td style="width:80%">
			<input type="text" name="entity.inspectionId" class="formText {byteRangeLength:[0,12]}" id="inspectionId" value="${entity.inspectionId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">订单ID</label></td>
			<td style="width:80%">
			<input type="text" name="entity.orderId" class="formText {byteRangeLength:[0,12]}" id="orderId" value="${entity.orderId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">仓库</label></td>
			<td style="width:80%">
			<input type="text" name="entity.whId" class="formText {required: true,byteRangeLength:[0,12],messages: {required:'请输入仓库'}}" id="whId" value="${entity.whId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">机构</label></td>
			<td style="width:80%">
			<input type="text" name="entity.deptId" class="formText {byteRangeLength:[0,12]}" id="deptId" value="${entity.deptId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">备注</label></td>
			<td style="width:80%">
			<input type="text" name="entity.remarks" class="formText {byteRangeLength:[0,200]}" id="remarks" value="${entity.remarks}"/>
			
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