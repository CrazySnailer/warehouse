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
			$('#productId').val('${entity.productId}');
	});
</script>
</head>
<body>

<div class="div_tab_header_box">
    <div class="div_tab_header_box_1">
        <div class="corner-1-report_nav"></div>
        <div class="corner-2-report_nav"></div>
        
        <div class="box-header left">${title}</div>
        <div class="box-content">
            
            
            
<form id="validateForm" name="validateForm" action="<%=path %>/warehouse/product_edit.action" method="post">
	<input type="hidden" name="qryHex" value="${qryHex}"/>
	<input type="hidden" name="event" value="${event}"/>
	<table border="0" cellspacing="0" cellpadding="0" class="editTable">
	
		<c:choose>
			<c:when test="${event=='ADD'}">
			</c:when>
			<c:otherwise>
		<tr>
			<td class="right" style="width:20%">商品ID</td>
			<td style="width:80%">
			${entity.productId}
			<input type="hidden" name="entity.productId" value="${entity.productId}"  id="productId"/>
			</td>
		</tr>			
			</c:otherwise>
		</c:choose>
		
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">商品ID</label></td>
			<td style="width:80%">
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">所属分类</label></td>
			<td style="width:80%">
			<input type="text" name="entity.classId" class="formText {required: true,byteRangeLength:[0,12],messages: {required:'请输入所属分类'}}" id="classId" value="${entity.classId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">管理部门</label></td>
			<td style="width:80%">
			<input type="text" name="entity.deptId" class="formText {required: true,byteRangeLength:[0,12],messages: {required:'请输入管理部门'}}" id="deptId" value="${entity.deptId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">商品编码</label></td>
			<td style="width:80%">
			<input type="text" name="entity.productCode" class="formText {required: true,byteRangeLength:[0,30],messages: {required:'请输入商品编码'}}" id="productCode" value="${entity.productCode}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">商品条码/SKU</label></td>
			<td style="width:80%">
			<input type="text" name="entity.productSku" class="formText {required: true,byteRangeLength:[0,70],messages: {required:'请输入商品条码/SKU'}}" id="productSku" value="${entity.productSku}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">商品名称</label></td>
			<td style="width:80%">
			<input type="text" name="entity.productName" class="formText {required: true,byteRangeLength:[0,200],messages: {required:'请输入商品名称'}}" id="productName" value="${entity.productName}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">委托货主</label></td>
			<td style="width:80%">
			<input type="text" name="entity.trustId" class="formText {byteRangeLength:[0,12]}" id="trustId" value="${entity.trustId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">供应商</label></td>
			<td style="width:80%">
			<input type="text" name="entity.vendorId" class="formText {byteRangeLength:[0,12]}" id="vendorId" value="${entity.vendorId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">仓库存放位置</label></td>
			<td style="width:80%">
			<input type="text" name="entity.structId" class="formText {byteRangeLength:[0,12]}" id="structId" value="${entity.structId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">价值</label></td>
			<td style="width:80%">
			<input type="text" name="entity.productCost" class="formText {required: true,byteRangeLength:[0,12],messages: {required:'请输入价值'}}" id="productCost" value="${entity.productCostStr}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">品牌</label></td>
			<td style="width:80%">
			<input type="text" name="entity.brandName" class="formText {required: true,byteRangeLength:[0,100],messages: {required:'请输入品牌'}}" id="brandName" value="${entity.brandName}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">型号</label></td>
			<td style="width:80%">
			<input type="text" name="entity.modelNo" class="formText {required: true,byteRangeLength:[0,100],messages: {required:'请输入型号'}}" id="modelNo" value="${entity.modelNo}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">包装单位</label></td>
			<td style="width:80%">
			<input type="text" name="entity.unitName" class="formText {required: true,byteRangeLength:[0,10],messages: {required:'请输入包装单位'}}" id="unitName" value="${entity.unitName}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">大包装单位</label></td>
			<td style="width:80%">
			<input type="text" name="entity.bigUnitName" class="formText {byteRangeLength:[0,10]}" id="bigUnitName" value="${entity.bigUnitName}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">包装换算率</label></td>
			<td style="width:80%">
			<input type="text" name="entity.exchangeNum" class="formText {byteRangeLength:[0,5]}" id="exchangeNum" value="${entity.exchangeNum}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">管理方式</label></td>
			<td style="width:80%">
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.adminType\" id=\"adminType\" class=\"formSelect \">","","wh_admin_type")%>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">产地</label></td>
			<td style="width:80%">
			<input type="text" name="entity.makeLocation" class="formText {byteRangeLength:[0,200]}" id="makeLocation" value="${entity.makeLocation}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">生产商</label></td>
			<td style="width:80%">
			<input type="text" name="entity.makeFactory" class="formText {byteRangeLength:[0,200]}" id="makeFactory" value="${entity.makeFactory}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">保修期</label></td>
			<td style="width:80%">
			<input type="text" name="entity.warrantyDay" class="formText {byteRangeLength:[0,20]}" id="warrantyDay" value="${entity.warrantyDay}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">委托货主结算价</label></td>
			<td style="width:80%">
			<input type="text" name="entity.trustSettlePrice" class="formText {byteRangeLength:[0,12]}" id="trustSettlePrice" value="${entity.trustSettlePriceStr}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">供应商结算价</label></td>
			<td style="width:80%">
			<input type="text" name="entity.vendorSettlePrice" class="formText {byteRangeLength:[0,12]}" id="vendorSettlePrice" value="${entity.vendorSettlePriceStr}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="lable2">备注</label></td>
			<td style="width:80%">
			<input type="text" name="entity.remark" class="formText {byteRangeLength:[0,200]}" id="remark" value="${entity.remark}"/>
			
			</td>
		</tr>
		<tr>
		<th class="center" colspan="2"><input type="submit" class="btn1" value="保 存"/><input type="button" class="btn1" value="返 回" onclick="history.go(-1)"/></th>
		</tr>
	</table>	
</form>



		</div>            
  	</div>
</div>
</body>
</html>