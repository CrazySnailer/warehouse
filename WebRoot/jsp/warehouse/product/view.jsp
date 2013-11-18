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
		<td class="right viewheader" style="width:20%">商品ID</td>
		<td style="width:80%">${entity.productId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">所属分类</td>
		<td style="width:80%">${entity.classId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">管理部门</td>
		<td style="width:80%">${entity.deptId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">商品编码</td>
		<td style="width:80%">${entity.productCode}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">商品条码/SKU</td>
		<td style="width:80%">${entity.productSku}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">商品名称</td>
		<td style="width:80%">${entity.productName}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">委托货主</td>
		<td style="width:80%">${entity.trustId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">供应商</td>
		<td style="width:80%">${entity.vendorId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">仓库存放位置</td>
		<td style="width:80%">${entity.structId}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">价值</td>
		<td style="width:80%">${entity.productCostStr}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">品牌</td>
		<td style="width:80%">${entity.brandName}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">型号</td>
		<td style="width:80%">${entity.modelNo}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">包装单位</td>
		<td style="width:80%">${entity.unitName}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">大包装单位</td>
		<td style="width:80%">${entity.bigUnitName}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">包装换算率</td>
		<td style="width:80%">${entity.exchangeNum}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">管理方式</td>
		<td style="width:80%">${entity.adminTypeStr}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">产地</td>
		<td style="width:80%">${entity.makeLocation}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">生产商</td>
		<td style="width:80%">${entity.makeFactory}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">保修期</td>
		<td style="width:80%">${entity.warrantyDay}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">委托货主结算价</td>
		<td style="width:80%">${entity.trustSettlePriceStr}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">供应商结算价</td>
		<td style="width:80%">${entity.vendorSettlePriceStr}</td>
	</tr>
	<tr>
		<td class="right viewheader" style="width:20%">备注</td>
		<td style="width:80%">${entity.remark}</td>
	</tr>
	<tr>
	<th class="center" colspan="2"><input type="button" class="btn1" value="关 闭" onclick="javascript:parent.$.fn.colorbox.close();"/></th>
	</tr>
</table>
</body>
</html>