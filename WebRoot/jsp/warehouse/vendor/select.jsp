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
<link href="<%=path%>/res/css/colorbox.css" rel="stylesheet" type="text/css"/>
<%@ include file="/jsp/include/basejs.jsp" %>
<script type="text/javascript">
	function selectTrust(vendorId,vendorName){
		parent.$('#${param.vendorId}').val(vendorId);
		parent.$('#${param.vendorName}').val(vendorName);
		parent.$.colorbox.close();	
	}
</script>
</head>
<body>
<div id="querytip" class="qrytip">loading....</div>

<div class="div_tab_header_box">
    <div class="div_tab_header_box_1">
        <div class="corner-1-report_nav"></div>
        <div class="corner-2-report_nav"></div>
        
        <div class="box-content">
        
<form id="listForm" name="listForm" method="post"  action="<%=path %>/warehouse/vendor_select.action">
	<input type="hidden" name="vendorId" value="${param.vendorId}"/>
	<input type="hidden" name="vendorName" value="${param.vendorName}"/>
	<table border="0" cellspacing="0" cellpadding="0" class="qryTable">
		<tr>
			<td>
				<label>编码</label><input type="text" name="qry.vendorCode" class="formTextS" id="vendorCode" value="${qry.vendorCode}" />
				<label>名称</label><input type="text" name="qry.vendorName" class="formTextS" id="vendorName" value="${qry.vendorName}" />
			<input type="submit" id="searchButton" class="btn1" value="查 询"/>			
			</td>
		</tr>	
	</table>
	
	
		<c:choose>
		<c:when test="${page.pageAmount==0}">
			<div class="nodatafound">
				没有查询到符合条件的供应商
			</div>
		</c:when>
		<c:otherwise>		
		<table border="0" cellspacing="0" cellpadding="0" class="listTable">
			<tr>
				<th>编码</th>
				<th>名称</th>
				<th>简称</th>
				<th>联系人</th>
			</tr>
			<c:forEach items="${page.results}" var="b">
			<tr onclick="selectTrust('${b.vendorId}','${b.vendorName}')">
				<td class="center cursorhand">${b.vendorCode}</td>
				<td class="center cursorhand">${b.vendorName}</td>
				<td class="center cursorhand">${b.shortName}</td>
				<td class="center cursorhand">${b.linker}</td>
			</tr>
			</c:forEach>
		</table>
		
		<input type="hidden" name="qry.pageNo" id="pageNo" value="${page.pageNo}" />
		<input type="hidden" name="qry.pageSize" id="pageNo" value="${page.pageSize}" />
		<div class="pagination">${page.pagination}</div>
		<input type="hidden" name="qry.orderCol" id="orderBy" value="${qry.orderCol}" />
		<input type="hidden" name="qry.orderType" id="order" value="${qry.orderType}" />
		</c:otherwise>
		</c:choose>
</form>
		</div><!--tab_content-->
  	</div><!--div_tab_content_qry-->
</div><!--ajaxtabdiv-->
</body>
</html>