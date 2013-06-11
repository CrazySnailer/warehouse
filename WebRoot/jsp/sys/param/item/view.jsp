<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/admin/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/res/admin/css/base.css" rel="stylesheet" type="text/css"/>
<%@ include file="/jsp/admin/include/basejs.jsp" %>
</head>
<body>
<table border="0" cellspacing="0" cellpadding="0" class="editTable">
	<tr>
		<th colspan="4" class="left">${title}</th>
	</tr>
	<tr>
		<td class="right" style="width:15%;">序列号</td>
		<td style="width:35%;">${entity.recId}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">参数类别</td>
		<td style="width:35%;">${entity.paramKindCode}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">参数编码</td>
		<td style="width:35%;">${entity.paramCode}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">参数名称</td>
		<td style="width:35%;">${entity.paramName}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">数据状态</td>
		<td style="width:35%;">${entity.dataStatusStr}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">备注</td>
		<td style="width:35%;">${entity.note}</td>
	</tr>
	<tr>
	<th class="center" colspan="2"><input type="button" class="buttonInput" value="返 回" onclick="history.go(-1)"/></th>
	</tr>
</table>
</body>
</html>