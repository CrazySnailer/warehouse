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
<link href="<%=path%>/res/css/base.css?20130617" rel="stylesheet" type="text/css"/>
<%@ include file="/jsp/include/basejs.jsp" %>
</head>
<body>
<table border="0" cellspacing="0" cellpadding="0" class="editTable">
		<tr>
		<td class="right viewheader" style="width:20%">订单ID</td>
		<td style="width:80%">${entity.orderId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">订单单号</td>
		<td style="width:80%">${entity.orderNo}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">订单类型</td>
		<td style="width:80%">${entity.orderTypeStr}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">订单日期</td>
		<td style="width:80%">${entity.orderDateStr}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">订单处理时限</td>
		<td style="width:80%">${entity.limitHoursStr}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">客户单号</td>
		<td style="width:80%">${entity.custOrderNo}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">仓库</td>
		<td style="width:80%">${entity.whId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">供应商</td>
		<td style="width:80%">${entity.vendorId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">委托货主</td>
		<td style="width:80%">${entity.trustId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">联系人</td>
		<td style="width:80%">${entity.linker}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">联系电话</td>
		<td style="width:80%">${entity.linkerTel}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">备注</td>
		<td style="width:80%">${entity.remarks}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">经办人</td>
		<td style="width:80%">${entity.handleId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">经办部门</td>
		<td style="width:80%">${entity.handleDept}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">订单来源</td>
		<td style="width:80%">${entity.orderSourceStr}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">审核时间</td>
		<td style="width:80%">${entity.auditDateStr}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">审核人</td>
		<td style="width:80%">${entity.auditId}</td>
	</tr>
	<tr>
	<th class="center" colspan="2"><input type="button" class="btn1" value="关 闭" onclick="javascript:parent.$.fn.colorbox.close();"/></th>
	</tr>
</table>
</body>
</html>