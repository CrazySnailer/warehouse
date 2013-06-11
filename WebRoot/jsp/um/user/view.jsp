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
		<td class="right" style="width:15%;">用户ID</td>
		<td style="width:35%;">${entity.userId}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">登录名</td>
		<td style="width:35%;">${entity.userCode}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">用户名</td>
		<td style="width:35%;">${entity.userName}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">密码</td>
		<td style="width:35%;">${entity.loginPwd}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">是否管理员</td>
		<td style="width:35%;">${entity.isAdminStr}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">所属机构</td>
		<td style="width:35%;">${entity.deptId}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">性别</td>
		<td style="width:35%;">${entity.sexStr}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">毕业学校</td>
		<td style="width:35%;">${entity.school}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">出生日期</td>
		<td style="width:35%;">${entity.birthdayStr}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">婚姻状况</td>
		<td style="width:35%;">${entity.marry}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">入职日期</td>
		<td style="width:35%;">${entity.joinDateStr}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">电子邮件</td>
		<td style="width:35%;">${entity.email}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">固定电话</td>
		<td style="width:35%;">${entity.tel}</td>
	</tr>
	<tr>
		<td class="right" style="width:15%;">手机</td>
		<td style="width:35%;">${entity.mobile}</td>
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