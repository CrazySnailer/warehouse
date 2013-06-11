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
	function gotoEdit(recId){
		$('#edit_recId').val(recId);
		$('#editForm').submit();
	}
	function gotoDelete(recId){
		jConfirm('确认删除该参数设置信息?', '确认删除', function(cresult) {
			if(cresult){
				$.ajax({
					url: '<%=path %>/admin/param/item_delete.action',
					data: 'entity.recId='+recId,
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
	
	$().ready( function() {	
		$('#pageSize').val('${page.pageSize}');
	});
</script>
</head>
<body>
<div id="querytip" class="qrytip">loading....</div>

<form id="editForm" name="editForm" method="post" action="<%=path %>/admin/param/item_toEdit.action">
	<input type="hidden" name="entity.recId" id="edit_recId" value=""/>
	<input type="hidden" name="qryHex" value="${qry.qryHex}"/>
</form>

<div class="ajaxtabdiv">
	<div class="div_tab_header">
	    <div class="div_tab_header_1">
	        <div class="corner-1-report_nav"></div>
	        <div class="corner-2-report_nav"></div>
	        <ul class="rptMenu ajaxtabs">		            
	            <li><a href="#tab_1">字典列表</a></li>
	            <li><a href="<%=path %>/admin/param/item_toAdd.action?entity.paramKindCode=${qry.paramKindCode}">新增</a></li>
	        </ul>
	    </div>
	</div><!--div_tab_header-->
	
	<div class="div_tab_content_qry">
            <div id="tab_1" class="tab_content">
            
			<form id="listForm" name="listForm" method="post"  action="<%=path %>/admin/param/item_list.action">
				<input type="hidden" name="qryit" value="1"/>
				<table border="0" cellspacing="0" cellpadding="0" class="qryTable">	
					<tr>			
							<td>
							<label>参数类别</label><input type="text" name="qry.paramKindCode" class="formTextS" id="paramKindCode" value="${qry.paramKindCode}" />
							<label>参数编码</label><input type="text" name="qry.paramCode" class="formTextS" id="paramCode" value="${qry.paramCode}" />
						<input type="submit" id="searchButton" class="btn1" value="查 询"/>			
						</td>
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
				<th>参数类别</th>
				<th>参数编码</th>
				<th>参数名称</th>
				<th>数据状态</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${page.results}" var="b">
			<tr>
				<td class="center">${b.paramKindCode}</td>
				<td class="center">${b.paramCode}</td>
				<td class="center">${b.paramName}</td>
				<td class="center">${b.dataStatusStr}</td>
				<td class="center">${b.note}</td>
				<td class="center">
				<a href="#none" title="修改" onclick="gotoEdit('${b.recId}')">修改</a>&nbsp;
				<a href="#none" title="删除" onclick="gotoDelete('${b.recId}')">删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		
		<input type="hidden" name="qry.pageNo" id="pageNo" value="${page.pageNo}" />
		<input type="hidden" name="qry.pageSize" id="pageSize" value="${page.pageSize}" />		
		<div class="pagination">${page.pagination}</div>
		
		
		</c:otherwise>
		</c:choose>
</form>
			</div><!--tab_content-->
  	</div><!--div_tab_content_qry-->
</div><!--ajaxtabdiv-->	
</body>
</html>