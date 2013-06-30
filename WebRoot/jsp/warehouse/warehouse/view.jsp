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
		<td class="right viewheader" style="width:20%">编号</td>
		<td style="width:80%">${entity.whCode}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">仓库名称</td>
		<td style="width:80%">${entity.whName}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">所属仓库</td>
		<td style="width:80%">${entity.parentId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">仓库类型   P物理仓  L逻辑仓</td>
		<td style="width:80%">${entity.whTypeStr}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">使用部门</td>
		<td style="width:80%">${entity.useDeptId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">管理模式 1简单  2复杂</td>
		<td style="width:80%">${entity.adminModeStr}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">联系地址</td>
		<td style="width:80%">${entity.address}</td>
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
		<td class="right viewheader" style="width:20%">电子邮箱</td>
		<td style="width:80%">${entity.email}</td>
	</tr>
	<tr>
	<th class="center" colspan="2"><input type="button" class="btn1" value="关 闭" onclick="javascript:parent.$.fn.colorbox.close();"/></th>
	</tr>
</table>
</body>
</html>