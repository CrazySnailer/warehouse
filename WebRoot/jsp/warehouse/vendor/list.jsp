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
	function gotoEdit(vendorId){
		$('#edit_vendorId').val(vendorId);
		$('#editForm').submit();
	}
	function gotoDelete(vendorId){
		jConfirm('确认删除该供应商信息?', '确认删除', function(cresult) {
			if(cresult){
				$.ajax({
					url: '<%=path %>/warehouse/vendor_delete.action',
					data: 'entity.vendorId='+vendorId,
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
	function gotoView(vendorId){
		$.colorbox({
			href:'<%=path %>/warehouse/vendor_toView.action?entity.vendorId='+vendorId+'&data='+Math.random(),
			iframe:true,
			title: '查看供应商',
			width:"80%", 
			height:"90%"
		});
	}
</script>
</head>
<body>
<div id="querytip" class="qrytip">loading....</div>

<form id="editForm" name="editForm" method="post" action="<%=path %>/warehouse/vendor_toEdit.action">
	<input type="hidden" name="entity.vendorId" id="edit_vendorId" value=""/>
	<input type="hidden" name="qryHex" value="${qry.qryHex}"/>
</form>

<div class="ajaxtabdiv">
	<div class="div_tab_header">
	    <div class="div_tab_header_1">
	        <div class="corner-1-report_nav"></div>
	        <div class="corner-2-report_nav"></div>
	        <ul class="rptMenu ajaxtabs">		            
	            <li><a href="#tab_1">供应商列表</a></li>
	            <shiro:hasPermission name="wh:vendor:edit"><li><a href="<%=path %>/warehouse/vendor_toAdd.action">新增</a></li></shiro:hasPermission>
	        </ul>
	    </div>
	</div><!--div_tab_header-->
	
	<div class="div_tab_content_qry">
            <div id="tab_1" class="tab_content">
<form id="listForm" name="listForm" method="post"  action="<%=path %>/warehouse/vendor_list.action">
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
				<th>联系电话</th>
				<th>电子邮箱</th>
			<th>操作</th>
			</tr>
			<c:forEach items="${page.results}" var="b">
			<tr>
				<td class="center">${b.vendorCode}</td>
				<td class="center">${b.vendorName}</td>
				<td class="center">${b.shortName}</td>
				<td class="center">${b.linker}</td>
				<td class="center">${b.linkerTel}</td>
				<td class="center">${b.email}</td>
				<td class="center" nowrap="true">
				<shiro:hasPermission name="wh:vendor:edit">
				<a href="#none" title="修改" onclick="gotoEdit('${b.vendorId}')">修改</a>&nbsp;
				<c:if test="${b.canDelete}">
				<a href="#none" title="删除" onclick="gotoDelete('${b.vendorId}')">删除</a>
				</c:if>				
				</shiro:hasPermission>
				<a href="#none" title="查看" onclick="gotoView('${b.vendorId}')">查看</a>
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
		</div><!--tab_content-->
  	</div><!--div_tab_content_qry-->
</div><!--ajaxtabdiv-->
</body>
</html>