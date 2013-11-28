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
<link href="<%=path%>/res/css/colorbox.css" rel="stylesheet" type="text/css"/>

<%@ include file="/jsp/include/basejs.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		$('#selectProduct').click(function(){
	    	$.colorbox({
				href:'<%=path %>/warehouse/product_select.action?uri=selectSingle&productId=productId&productName=productName&data='+Math.random(),
				iframe:true,
				width:"80%",
				height:"90%",
				title:"选择商品"
			});
	    });
	});
</script>
</head>
<body>

<div class="div_tab_header_box">
    <div class="div_tab_header_box_1">
        <div class="corner-1-report_nav"></div>
        <div class="corner-2-report_nav"></div>
        
        <div class="box-content">
            
<form id="validateForm" name="validateForm" action="<%=path %>/warehouse/orderin_editDetail.action" method="post">
	<input type="hidden" name="qryHex" value="${qryHex}"/>
	<input type="hidden" name="event" value="${event}"/>
	<input type="hidden" name="index" value="${param.index}"/>
	<table border="0" cellspacing="0" cellpadding="0" class="editTable">
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">商品</label></td>
			<td style="width:80%">
			<input type="text" name="productName" readonly="true" class="formTextL ro" id="productName" value="${detail.product.productName}"/>
			<input type="hidden" name="detail.product.productId" class="formText {required: true,byteRangeLength:[0,12],messages: {required:'请选择商品'}}" id="productId" value="${detail.product.productId}"/>
			<a href="#none" id="selectProduct" class="ico ico-open" title="选择商品"></a>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:20%"><label class="requiredtext">*</label><label class="lable2">数量</label></td>
			<td style="width:80%">
			<input type="text" name="detail.qty" class="formTextL {required: true,positiveInteger:true,byteRangeLength:[0,12],messages: {required:'请输入数量'}}" id="qty" value="${detail.qty}"/>
			
			</td>
		</tr>
		
		<tr>
			<td class="right" style="width:20%"><label class="lable2">备注</label></td>
			<td style="width:80%">
			<input type="text" name="detail.remarks" class="formTextL {byteRangeLength:[0,300]}" id="remarks" value="${detail.remarks}"/>
			
			</td>
		</tr>
		<tr>
		<th class="center" colspan="2">
			<div class="buttons"  style="margin-left:150px;">
    			<button type="submit" class="positive" name="save">
        			<img src="<%=path %>/res/images/icon/search.png" alt=""/>保 存
    			</button>

    			<a href="#none" onclick="parent.$.colorbox.close();" class="negative">
        			<img src="<%=path %>/res/images/icon/cancel.png" alt=""/>取 消
    			</a>
			</div>		
		</tr>
	</table>	
</form>



		</div>            
  	</div>
</div>
</body>
</html>