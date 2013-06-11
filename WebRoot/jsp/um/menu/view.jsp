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
		<td class="right" style="width:15%;">菜单ID</td>
		<td style="width:35%;">${entity.menuId}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">上级菜单</td>
		<td style="width:35%;">${entity.menuId}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">菜单名称</td>
		<td style="width:35%;">${entity.name}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">菜单URL</td>
		<td style="width:35%;">${entity.url}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">唯一标识值</td>
		<td style="width:35%;">${entity.idVal}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">是否叶子</td>
		<td style="width:35%;">${entity.isLeafStr}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">排序号</td>
		<td style="width:35%;">${entity.orderNum}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">是否需要购买</td>
		<td style="width:35%;">${entity.needBuyStr}</td>
	</tr>
	<tr>
	<th class="center" colspan="2"><input type="button" class="buttonInput" value="返 回" onclick="history.go(-1)"/></th>
	</tr>
</table>
</body>
</html>