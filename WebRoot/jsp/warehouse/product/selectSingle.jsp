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

<script language="javascript" src="<%=path%>/jsp/warehouse/orderin/order.js"></script>

<script type="text/javascript">
	function selectProduct(productId,productName){
		parent.$('#${param.productId}').val(productId);
		parent.$('#${param.productName}').val(productName);
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
        
			<form id="listForm" name="listForm" method="post"  action="<%=path %>/warehouse/product_select.action">
				<table border="0" cellspacing="0" cellpadding="0" class="qryTable">
					<tr>
						<td>
							<label>商品编码/名称</label><input type="text" name="qry.codeOrName" class="formTextS" id="codeOrName" value="${qry.codeOrName}" />
							<input type="submit" id="searchButton" class="btn1" value="查 询"/>
							<input type="hidden" name="uri" value="${param.uri}"/>
							<input type="hidden" name="productId" value="${param.productId}"/>
							<input type="hidden" name="productName" value="${param.productName}"/>
						</td>
					</tr>
				</table>
				
				
					<c:choose>
					<c:when test="${page.pageAmount==0}">
						<div class="nodatafound">
							没有查询到符合条件的商品
						</div>
					</c:when>
					<c:otherwise>		
					<table border="0" cellspacing="0" cellpadding="0" class="listTable">
						<tr>
							<th>商品分类</th>
							<th>商品编码</th>
							<th>商品条码/SKU</th>
							<th>商品名称</th>
							<th>品牌</th>
							<th>型号</th>
							<th>包装单位</th>
						</tr>
						<c:forEach items="${page.results}" var="b">
						<tr onclick="selectProduct('${b.productId}','${b.productName}')">
							<td class="center">${b.productClass.className}</td>
							<td class="center">${b.productCode}</td>
							<td class="center">${b.productSku}</td>
							<td class="center">${b.productName}</td>
							<td class="center">${b.brandName}</td>
							<td class="center">${b.modelNo}</td>
							<td class="center">${b.unitName}</td>
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


		</div>
    </div>
</div>
</body>
</html>