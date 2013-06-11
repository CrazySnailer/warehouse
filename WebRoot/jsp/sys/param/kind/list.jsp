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
	function gotoDelete(kindCode){
		jConfirm('确认删除该参数分类信息?', '确认删除', function(cresult) {
			if(cresult){
				$.ajax({
					url: '<%=path %>/admin/param/kind_delete.action',
					data: 'entity.kindCode='+kindCode,
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

<form id="editForm" name="editForm" method="post" action="<%=path %>/admin/param/kind_toEdit.action">
	<input type="hidden" name="entity.kindCode" id="edit_kindCode" value=""/>
	<input type="hidden" name="qryHex" value="${qry.qryHex}"/>
</form>


<form id="listForm" name="listForm" method="post"  action="<%=path %>/admin/param/kind_list.action">
	<input type="hidden" name="qryit" value="1"/>
	
<div class="ajaxtabdiv">
	<div class="div_tab_header">
	    <div class="div_tab_header_1">
	        <div class="corner-1-report_nav"></div>
	        <div class="corner-2-report_nav"></div>
	        <ul class="rptMenu ajaxtabs">		            
	            <li><a href="#tab_1">字典分类列表</a></li>
	            <li><a href="<%=path %>/admin/param/kind_toAdd.action">新增</a></li>
	        </ul>
	    </div>
	</div><!--div_tab_header-->
	
	<div class="div_tab_content_qry">
            <div id="tab_1" class="tab_content">
                   <table border="0" cellspacing="0" cellpadding="0" class="qryTable">
						<tr>			
								<td>
								<label>分类编码</label>
								<input type="text" name="qry.kindCode" class="formTextS" id="kindCode" value="${qry.kindCode}" />
								<label>分类名称</label><input type="text" name="qry.kindName" class="formTextS" id="kindName" value="${qry.kindName}" />
							<input type="submit" id="searchButton" class="btn1" value="查询"/>		
							</td>
						</tr>	
					</table> 
            </div>

		
	
		<c:choose>
		<c:when test="${page.pageAmount==0 && param.qryit == '1'}">
			<div class="nodatafound">
				没有查询到指定数据
			</div>
		</c:when>
		<c:otherwise>		
		<table border="0" cellspacing="0" cellpadding="0" class="listTable">
			<tr>
				<th>分类编码</th>
				<th>分类名称</th>
				<th>数据状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${page.results}" var="b">
			<tr>
				<td class="center">${b.kindCode}</td>
				<td class="center"><a href="<%=path %>/admin/param/kind_toView.action?entity.kindCode=${b.kindCode}">${b.kindName}</a></td>
				<td class="center">${b.dataStatusStr}</td>
				<td class="center">
				<a href="#none" title="修改" onclick="gotoEdit('${b.kindCode}')">修改</a>&nbsp;
				<a href="#none" title="删除" onclick="gotoDelete('${b.kindCode}')">删除</a>&nbsp;
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
  	</div><!--div_tab_content-->
</div><!--ajaxtabdiv-->
</body>
</html>