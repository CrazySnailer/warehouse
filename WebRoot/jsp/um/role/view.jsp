<%@ page language="java" contentType="text/html; charset=utf-8"%>
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
</head>
<body>
<table border="0" cellspacing="0" cellpadding="0" class="editTable">
	<tr>
		<th colspan="4" class="left">${title}</th>
	</tr>
	<tr>
		<td class="right" style="width:15%;">角色ID</td>
		<td style="width:35%;">${entity.roleId}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">角色名称</td>
		<td style="width:35%;">${entity.roleName}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">角色级别</td>
		<td style="width:35%;">${entity.roleLevelStr}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">备注</td>
		<td style="width:35%;">${entity.note}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">数据状态</td>
		<td style="width:35%;">${entity.dataStatusStr}</td>
	</tr>
	<tr>
	<th class="center" colspan="2"><input type="button" class="buttonInput" value="返 回" onclick="history.go(-1)"/></th>
	</tr>
</table>
</body>
</html>