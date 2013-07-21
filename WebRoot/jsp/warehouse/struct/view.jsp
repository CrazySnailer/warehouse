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
		<td class="right viewheader" style="width:20%">仓库结构ID</td>
		<td style="width:80%">${entity.structId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">父级ID</td>
		<td style="width:80%">${entity.parentId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">编号</td>
		<td style="width:80%">${entity.structCode}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">名称</td>
		<td style="width:80%">${entity.structName}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">所属仓库</td>
		<td style="width:80%">${entity.whId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">类型  P储位 F货架 A库区 W仓库</td>
		<td style="width:80%">${entity.structTypeStr}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">排序号</td>
		<td style="width:80%">${entity.orderNum}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">全名</td>
		<td style="width:80%">${entity.fullName}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">树编号</td>
		<td style="width:80%">${entity.treeNo}</td>
	</tr>
	<tr>
	<th class="center" colspan="2"><input type="button" class="btn1" value="关 闭" onclick="javascript:parent.$.fn.colorbox.close();"/></th>
	</tr>
</table>
</body>
</html>