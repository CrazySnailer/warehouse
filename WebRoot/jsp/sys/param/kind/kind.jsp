<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="com.calf.framework.util.CodeNameUtils"%>
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
	function gotoEdit(kindCode){
		$('#edit_kindCode').val(kindCode);
		$('#editForm').submit();
	}
	
	$().ready( function() {	
		$('#pageSize').val('${page.pageSize}');
	});
</script>
</head>
<body>
<div id="querytip" class="qrytip">loading....</div>

<form id="editForm" name="editForm" method="post" target="rightItemFrm" action="<%=path %>/admin/param/item_list.action">
	<input type="hidden" name="qry.paramKindCode" id="edit_kindCode" value=""/>
</form>

<form id="listForm" name="listForm" method="post"  action="<%=path %>/admin/param/kind_kind.action">
	<input type="hidden" name="qryit" value="1"/>
	<table border="0" cellspacing="0" cellpadding="0" class="qryTable">
		<tr>
			<td><input type="text" name="qry.kindCode" class="formTextS" id="kindCode" value="${qry.kindCode}" /><input type="submit" id="searchButton" class="btn2" style="margin-top:1px;" value="查询"/></td>
		</tr>	
	</table>
	
		<c:choose>
		<c:when test="${page.pageAmount==0 && param.qryit == '1'}">
			<div class="nodatafound">
				没有查询到指定数据
			</div>
		</c:when>
		<c:otherwise>		
		<table border="0" cellspacing="0" cellpadding="0" class="listTable">
			<tr>
				<th>分类名称</th>
			</tr>
			<c:forEach items="${page.results}" var="b">
			<tr>
				<td class="center"><a href="#none" onclick="gotoEdit('${b.kindCode}')">${b.kindName}</a></td>
			</tr>
			</c:forEach>
		</table>
		
		<input type="hidden" name="qry.pageNo" id="pageNo" value="${page.pageNo}" />
		<input type="hidden" name="qry.pageSize" id="pageSize" value="${page.pageSize}" />		
		<div class="pagination">${page.paginationSimple}</div>
		</c:otherwise>
		</c:choose>
</form>

</body>
</html>