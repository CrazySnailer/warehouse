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
		$('#isAdmin').val('${entity.isAdmin}');
		$('#sex').val('${entity.sex}');
		$('#edu').val('${entity.edu}');
		$('#isBackUser').val('${entity.isBackUser}');
		$('#dataStatus').val('${entity.dataStatus}');
	});
</script>
</head>
<body>
<form id="validateForm" name="validateForm" action="<%=path %>/admin/um/user_edit.action" method="post">
	<input type="hidden" name="qryHex" value="${qryHex}"/>
	<input type="hidden" name="event" value="${event}"/>
	<input type="hidden" name="entity.userId" id="userId" value="${entity.userId}"/>
	<table border="0" cellspacing="0" cellpadding="0" class="editTable">
		<tr>
			<th colspan="4" class="left">${title}</th>
		</tr>
		<tr>
			<td class="right" style="width:15%;">所属机构</td>
			<td colspan="3">
			<input type="text" class="formText ro" readonly="true" name="deptName" value="${entity.dept.deptName}"/>
			<input type="hidden" name="entity.dept.deptId" class="formText {required: true,byteRangeLength:[0,12],messages: {required:'请输入所属机构'}}" id="deptId" value="${entity.dept.deptId}"/>

			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">登录名</td>
			<td style="width:35%;">
			<input type="text" name="entity.userCode" class="formText {required: true,byteRangeLength:[0,50],messages: {required:'请输入登录名'}}" id="userCode" value="${entity.userCode}"/>
			<label class="required">*</label>
			</td>
			<td class="right" style="width:15%;">用户名</td>
			<td style="width:35%;">
			<input type="text" name="entity.userName" class="formText {required: true,byteRangeLength:[0,50],messages: {required:'请输入用户名'}}" id="userName" value="${entity.userName}"/>
			<label class="required">*</label>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">密码</td>
			<td style="width:35%;">
			<input type="text" name="entity.loginPwd" class="formText {required: true,byteRangeLength:[0,50],messages: {required:'请输入密码'}}" id="loginPwd" value="${entity.loginPwd}"/>
			<label class="required">*</label>
			</td>
			<td class="right" style="width:15%;">是否管理员</td>
			<td style="width:35%;">
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.isAdmin\" id=\"isAdmin\" class=\"formSelect {required: true,messages: {required:'请选择是否管理员'}}\">","","g_pu_yes_no")%>
			<label class="required">*</label>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">所属机构</td>
			<td style="width:35%;">
			<input type="hidden" name="entity.dept.deptId" class="formText {required: true,byteRangeLength:[0,12],messages: {required:'请输入所属机构'}}" id="deptId" value="${entity.dept.deptId}"/>
			<input type="text" class="formText ro" readonly="true" name="deptName" value="${entity.dept.deptName}"/>
			<label class="required">*</label>
			</td>
			<td class="right" style="width:15%;">性别</td>
			<td style="width:35%;">
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.sex\" id=\"sex\" class=\"formSelect \">","","g_pu_sex")%>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">毕业学校</td>
			<td style="width:35%;">
			<input type="text" name="entity.school" class="formText {byteRangeLength:[0,50]}" id="school" value="${entity.school}"/>
			
			</td>
			<td class="right" style="width:15%;">出生日期</td>
			<td style="width:35%;">
			<input type="text" name="entity.birthday" class="formText {byteRangeLength:[0,7]}" id="birthday" value="${entity.birthdayStr}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">婚姻状况</td>
			<td style="width:35%;">
			<input type="text" name="entity.marry" class="formText {byteRangeLength:[0,2]}" id="marry" value="${entity.marry}"/>
			
			</td>
			<td class="right" style="width:15%;">入职日期</td>
			<td style="width:35%;">
			<input type="text" name="entity.joinDate" class="formText {byteRangeLength:[0,7]}" id="joinDate" value="${entity.joinDateStr}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">电子邮件</td>
			<td style="width:35%;">
			<input type="text" name="entity.email" class="formText {byteRangeLength:[0,100]}" id="email" value="${entity.email}"/>
			
			</td>
			<td class="right" style="width:15%;">固定电话</td>
			<td style="width:35%;">
			<input type="text" name="entity.tel" class="formText {byteRangeLength:[0,50]}" id="tel" value="${entity.tel}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">手机</td>
			<td style="width:35%;">
			<input type="text" name="entity.mobile" class="formText {byteRangeLength:[0,50]}" id="mobile" value="${entity.mobile}"/>
			
			</td>
			<td class="right" style="width:15%;">备注</td>
			<td style="width:35%;">
			<input type="text" name="entity.note" class="formText {byteRangeLength:[0,200]}" id="note" value="${entity.note}"/>
			
			</td>
		</tr>
		<tr>
		<th class="center" colspan="4"><span id="savetip" class="ajaxtip">数据保存中，请稍候……</span><input type="submit" class="buttonInput" value="保 存"/><input type="button" class="buttonInput" value="返 回" onclick="history.go(-1)"/></th>
		</tr>
	</table>	
</form>

</body>
</html>