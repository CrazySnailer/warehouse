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
<script language="javascript" src="<%=path%>/jsp/warehouse/orderin/order.js"></script>

<script type="text/javascript">
	function refreshDetail(){
		//进行AJAX刷新
		$.ajax({
			url: '<%=path %>/warehouse/orderin_listDetail.action?data='+Math.random(),
			type: 'post',
			data: {},
			dataType:"html",
			success: function (resp) {
				$('#prodList tbody').html(resp);
			}
		});
	}
	$(document).ready(function(){
			$('#whId').val('${entity.whId}');
			
			$('#selectTrust').click(function(){
	        	$.colorbox({
					href:'<%=path %>/warehouse/trust_select.action?trustId=trustId&trustName=trustName&data='+Math.random(),
					iframe:true,
					width:"80%",
					height:"90%",
					title:"选择委托货主"
				});
	        });
	        
	        $('#selectVendor').click(function(){
	        	$.colorbox({
					href:'<%=path %>/warehouse/vendor_select.action?vendorId=vendorId&vendorName=vendorName&data='+Math.random(),
					iframe:true,
					width:"80%",
					height:"90%",
					title:"选择供应商"
				});
	        });
	        
	        
	        $('#add_product').click(function(){
	        	$.colorbox({
					href:'<%=path %>/warehouse/orderin_toAddDetail.action?data='+Math.random(),
					iframe:true,
					width:"80%",
					height:"90%",
					title:"添加订单商品明细",
					onClosed:function(){
						refreshDetail();
					}
				});
	        });
	        
	        $('#clearvendor').click(function(){
		    	$('#vendorId').val('');
		    	$('#vendorName').val('');
		    });
		    $('#cleartrust').click(function(){
		    	$('#trustId').val('');
		    	$('#trustName').val('');
		    });
		    
		    //添加已存在的数据
	});
	
	/**
	 * 
	 **/
	function openEditDialog(idx){
		$.colorbox({
			href:'<%=path %>/warehouse/orderin_toEditDetail.action?index='+idx+'&data='+Math.random(),
			iframe:true,
			width:"80%",
			height:"90%",
			title:"修改订单商品明细",
			onClosed:function(){
				refreshDetail();
			}
		});
	}
	
	function removeDetail(idx){
		$.ajax({
			url: '<%=path %>/warehouse/orderin_deleteDetail.action',
			type: 'post',
			data: {'index':idx,'data':Math.random()},
			dataType:"html",
			success: function (resp) {
				refreshDetail();
			}
		});
	}
</script>
</head>
<body>

<div class="div_tab_header_box">
    <div class="div_tab_header_box_1">
        <div class="corner-1-report_nav"></div>
        <div class="corner-2-report_nav"></div>
        
        <div class="box-header left">${title}</div>
        <div class="box-content">
            
            
            
<form id="validateForm" name="validateForm" action="<%=path %>/warehouse/orderin_edit.action" method="post">
	<input type="hidden" name="qryHex" value="${qryHex}"/>
	<input type="hidden" name="event" value="${event}"/>
	<c:choose>
		<c:when test="${event=='ADD'}">
		</c:when>
		<c:otherwise>
		<input type="hidden" name="entity.orderId" value="${entity.orderId}"  id="orderId"/>			
		</c:otherwise>
	</c:choose>
	<table border="0" cellspacing="0" cellpadding="0" class="editTable">

		<tr>
			<td class="right" style="width:15%"><label class="requiredtext">*</label><label class="lable2">订单类型</label></td>
			<td style="width:35%">
		
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.orderType\" id=\"orderType\" class=\"formSelect {required: true,messages: {required:'请选择订单类型'}}\">","","wh_orderin_type")%>
			</td>
			
			<td class="right" style="width:15%"><label class="lable2">订单编号</label></td>
			<td class="left" style="width:35%">		
			<c:choose>
				<c:when test="${event=='ADD'}">
				自动生成
				</c:when>
				<c:otherwise>${entity.orderNo}</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%"><label class="requiredtext">*</label><label class="lable2">订单日期</label></td>
			<td style="width:35%">
			<input type="text" name="entity.orderDate" class="formText Wdate2 {required: true,messages: {required:'请输入订单日期'}}" id="orderDate" onclick="WdatePicker()" value="${entity.orderDateStr}"/>
			
			</td>
			<td class="right" style="width:15%"><label class="requiredtext">*</label><label class="lable2">订单处理时限</label></td>
			<td style="width:35%">			
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.limitHours\" id=\"limitHours\" class=\"formSelect {required: true,messages: {required:'请选择订单处理时限'}}\">","","wh_order_limit")%>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%"><label class="requiredtext">*</label><label class="lable2">仓库</label></td>
			<td style="width:35%">
			<select name="entity.whId" id="whId" class="formSelect {required: true,messages: {required:'请选择仓库'}}">
			<option value=""></option>
			<c:forEach items="${warehouseList}" var="b">
			<option value="${b.whId}">${b.whName}</option>
			</c:forEach>
			</select>
			
			</td>
			
			<td class="right" style="width:15%"><label class="lable2">客户单号</label></td>
			<td style="width:35%">
			<input type="text" name="entity.custOrderNo" class="formText {byteRangeLength:[0,50]}" id="custOrderNo" value="${entity.custOrderNo}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%"><label class="lable2">委托货主</label></td>
			<td style="width:35%">
			<input type="text" name="trustName" readonly="true" class="formText ro" id="trustName" value=""/>
			<input type="hidden" name="entity.trustId" class="formText {byteRangeLength:[0,12]}" id="trustId" value="${entity.trustId}"/>
			<a href="#none" id="selectTrust" class="ico ico-open" title="选择委托货主"></a>
			<a href="#none" id="cleartrust" class="ico ico-eraser" title="清除选择"></a>
			</td>
			<td class="right" style="width:15%"><label class="lable2">供应商</label></td>
			<td style="width:35%">
			<input type="text" name="vendorName" readonly="true" class="formText ro" id="vendorName" value=""/>
			<input type="hidden" name="entity.vendorId" class="formText {byteRangeLength:[0,12]}" id="vendorId" value="${entity.vendorId}"/>
			<a href="#none" id="selectVendor" class="ico ico-open" title="选择供应商"></a>
			<a href="#none" id="clearvendor" class="ico ico-eraser" title="清除选择"></a>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%"><label class="requiredtext">*</label><label class="lable2">联系人</label></td>
			<td style="width:35%">
			<input type="text" name="entity.linker" class="formText {required: true,byteRangeLength:[0,50],messages: {required:'请输入联系人'}}" id="linker" value="${entity.linker}"/>
			
			</td>
			<td class="right" style="width:15%"><label class="requiredtext">*</label><label class="lable2">联系电话</label></td>
			<td style="width:35%">
			<input type="text" name="entity.linkerTel" class="formText {required: true,byteRangeLength:[0,30],messages: {required:'请输入联系电话'}}" id="linkerTel" value="${entity.linkerTel}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%"><label class="lable2">备注</label></td>
			<td style="width:85%" colspan="3">
			<input type="text" name="entity.remarks" class="formText {byteRangeLength:[0,200]}" id="remarks" value="${entity.remarks}"/>
			
			</td>
			
		</tr>
		
	</table>
</form>
				
				<div class="box-header left">入库商品明细<input type="button" class="btn1" value="添加明细" id="add_product"/></div>
				<div class="box-content">
				<table border="0" cellspacing="0" cellpadding="0"  class="listTable" id="prodList">
					<thead>
						<tr>
							<th class="center">SKU码</th>
							<th class="center">商品编码</th>
							<th class="center">名称</th>
							<th class="center">品牌/型号</th>
							<th class="center">数量</th>
							<th class="center">备注</th>
							<th class="center">操作</th>
						</tr>
					</thead>
					<tbody>
					
					</tbody>
					
					<tfoot>
						<tr>
							<td class="center" colspan="7"><input type="submit" class="btn1" value="保 存"/><input type="button" class="btn1" value="返 回" onclick="history.go(-1)"/></td>
						</tr>
					</tfoot>
				</table>
				</div>

		</div>            
  	</div>
</div>
</body>
</html>