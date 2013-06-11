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
		<td class="right" style="width:15%;">机构ID</td>
		<td style="width:35%;">${entity.deptId}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">机构编号</td>
		<td style="width:35%;">${entity.deptCode}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">行政区划</td>
		<td style="width:35%;">${entity.areaCode}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">上级机构</td>
		<td style="width:35%;">${entity.parentDeptId}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">机构名称</td>
		<td style="width:35%;">${entity.deptNameStr}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">机构性质  0机构  1部门</td>
		<td style="width:35%;">${entity.deptKindStr}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">机构级别 </td>
		<td style="width:35%;">${entity.deptLevel}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">是否是叶子节点</td>
		<td style="width:35%;">${entity.isLeaf}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">机构全名</td>
		<td style="width:35%;">${entity.fullName}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">排序号</td>
		<td style="width:35%;">${entity.orderNum}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">联系人</td>
		<td style="width:35%;">${entity.linkMan}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">联系电话</td>
		<td style="width:35%;">${entity.linkTel}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">联系地址</td>
		<td style="width:35%;">${entity.linkAddr}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">电子邮件</td>
		<td style="width:35%;">${entity.linkEmail}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">备注</td>
		<td style="width:35%;">${entity.note}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">树形机构编码</td>
		<td style="width:35%;">${entity.treeNo}</td>
	</tr>
	<tr>
	<th class="center" colspan="2"><input type="button" class="buttonInput" value="返 回" onclick="history.go(-1)"/></th>
	</tr>
</table>
</body>
</html>