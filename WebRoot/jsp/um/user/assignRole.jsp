<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="com.calf.framework.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/include/head.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/res/css/base.css" rel="stylesheet" type="text/css"/>
<%@ include file="/jsp/include/basejs.jsp" %>
<script type="text/javascript">
	$().ready( function() {
		<c:forEach items="${existlist}" var="b">
		$('#b${b.roleId}').attr("checked",true);
		$('#a${b.roleId}').parents("tr").each(function(){
			$(this).find("td").css('backgroundColor', "#ffffd4" );
		});
		</c:forEach>
	});
</script>
</head>
<body>

<form id="validateForm" name="validateForm" method="post" action="<%=path %>/admin/um/user_assignRole.action">
		<input type="hidden" name="entity.userId" value="${entity.userId}"/>
		<table border="0" cellspacing="0" cellpadding="0" class="editTable">
			<tr>
				<th colspan="2" class="styleA left">设置用户角色，用户编号：${entity.userCode},用户姓名：${entity.userName}</th>
			</tr>
			<tr>
				<td colspan="2"><div class="userTipTitle">提示：钩中的表示该用户具有该角色</div></td>
			</tr>
			
			<c:forEach items="${roleList}" var="b">
			<tr>
				<td class="right" id="a${b.roleId}" style="width:10%;">
					<input type="checkbox" id="b${b.roleId}" name="roleIds" value="${b.roleId}"/> 
				 </td>
				<td style="width:90%;">${b.roleName}</td>				
			</tr>
			</c:forEach>
			
			<tr>
				<th class="center" colspan="2"><span id="savetip" class="ajaxtip">数据保存中，请稍候……</span><input type="submit" class="buttonInput" value="保 存"/></th>
			</tr>
		</table>
		
</form>

</body>
</html>