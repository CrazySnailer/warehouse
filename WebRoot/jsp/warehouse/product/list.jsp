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
	function gotoEdit(productId){
		$('#edit_productId').val(productId);
		$('#editForm').submit();
	}
	function gotoDelete(productId){
		jConfirm('该记录删除后将不能恢复，您确认要删除吗?', '确认删除', function(cresult) {
			if(cresult){
				$.ajax({
					url: '<%=path %>/warehouse/product_delete.action',
					data: 'entity.productId='+productId,
					dataType: "json",
					success: function(data) {
						if (data.status == "success") {
							 $.gotoPage(${page.pageNo});
						}else{
							jAlert('删除失败','提示信息');
						}
					}
				});
			}
		});	
	}
	function gotoView(productId){
		$.colorbox({
			href:'<%=path %>/warehouse/product_toView.action?entity.productId='+productId+'&data='+Math.random(),
			iframe:true,
			title: '查看商品管理',
			width:"80%", 
			height:"90%"
		});
	}
</script>
</head>
<body>
<div id="querytip" class="qrytip">loading....</div>

<form id="editForm" name="editForm" method="post" action="<%=path %>/warehouse/product_toEdit.action">
	<input type="hidden" name="entity.productId" id="edit_productId" value=""/>
	<input type="hidden" name="qryHex" value="${qry.qryHex}"/>
</form>
            
<div class="div_tab_header_box">
    <div class="div_tab_header_box_1">
        <div class="corner-1-report_nav"></div>
        <div class="corner-2-report_nav"></div>
        
        <div class="box-header left">商品管理</div>
        <div class="box-content">
        
        
                    
<form id="listForm" name="listForm" method="post"  action="<%=path %>/warehouse/product_list.action">
	<table border="0" cellspacing="0" cellpadding="0" class="qryTable">
		<tr>
			<td>
				<label>所属分类</label><input type="text" name="qry.classId" class="formTextS" id="classId" value="${qry.classId}" />
				<label>商品编码</label><input type="text" name="qry.productCode" class="formTextS" id="productCode" value="${qry.productCode}" />
				<label>商品名称</label><input type="text" name="qry.productName" class="formTextS" id="productName" value="${qry.productName}" />
				<label>委托货主</label><input type="text" name="qry.trustId" class="formTextS" id="trustId" value="${qry.trustId}" />
				<label>供应商</label><input type="text" name="qry.vendorId" class="formTextS" id="vendorId" value="${qry.vendorId}" />
			<input type="submit" id="searchButton" class="btn1" value="查 询"/>
			<shiro:hasPermission name="wh:product:edit">
			<input type="button" class="btn1" id="add_btn" value="新 增" onclick="$.launchPage('<%=path %>/warehouse/product_toAdd.action')" />
			</shiro:hasPermission>	
			</td>
		</tr>	
	</table>
	
	
		<c:choose>
		<c:when test="${page.pageAmount==0}">
			<div class="nodatafound">
				没有查询到符合条件的商品管理
			</div>
		</c:when>
		<c:otherwise>		
		<table border="0" cellspacing="0" cellpadding="0" class="listTable">
			<tr>
				<th>所属分类</th>
				<th>商品编码</th>
				<th>商品条码/SKU</th>
				<th>商品名称</th>
				<th>委托货主</th>
				<th>供应商</th>
				<th>仓库存放位置</th>
				<th>品牌</th>
				<th>型号</th>
				<th>包装单位</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${page.results}" var="b">
			<tr>
				<td class="center">${b.classId}</td>
				<td class="center">${b.productCode}</td>
				<td class="center">${b.productSku}</td>
				<td class="center">${b.productName}</td>
				<td class="center">${b.trustId}</td>
				<td class="center">${b.vendorId}</td>
				<td class="center">${b.structId}</td>
				<td class="center">${b.brandName}</td>
				<td class="center">${b.modelNo}</td>
				<td class="center">${b.unitName}</td>
				<td class="center" nowrap="true">
				<shiro:hasPermission name="wh:product:edit">
				<a href="#none" title="修改" onclick="gotoEdit('${b.productId}')">修改</a>&nbsp;
				<c:if test="${b.canDelete}">
				<a href="#none" title="删除" onclick="gotoDelete('${b.productId}')">删除</a>
				</c:if>				
				</shiro:hasPermission>
				<a href="#none" title="查看" onclick="gotoView('${b.productId}')">查看</a>
				</td>
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