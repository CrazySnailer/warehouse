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
	function gotoEdit(expressId){
		$('#edit_expressId').val(expressId);
		$('#editForm').submit();
	}
	function gotoDelete(expressId){
		jConfirm('该记录删除后将不能恢复，您确认要删除吗?', '确认删除', function(cresult) {
			if(cresult){
				$.ajax({
					url: '<%=path %>/warehouse/express_delete.action',
					data: 'entity.expressId='+expressId,
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
	function gotoView(expressId){
		$.colorbox({
			href:'<%=path %>/warehouse/express_toView.action?entity.expressId='+expressId+'&data='+Math.random(),
			iframe:true,
			title: '查看物流公司',
			width:"80%", 
			height:"90%"
		});
	}
</script>
</head>
<body>
<div id="querytip" class="qrytip">loading....</div>

<form id="editForm" name="editForm" method="post" action="<%=path %>/warehouse/express_toEdit.action">
	<input type="hidden" name="entity.expressId" id="edit_expressId" value=""/>
	<input type="hidden" name="qryHex" value="${qry.qryHex}"/>
</form>
            
<div class="div_tab_header_box">
    <div class="div_tab_header_box_1">
        <div class="corner-1-report_nav"></div>
        <div class="corner-2-report_nav"></div>
        
        <div class="box-header left">物流公司</div>
        <div class="box-content">
        
        
                    
<form id="listForm" name="listForm" method="post"  action="<%=path %>/warehouse/express_list.action">
	<table border="0" cellspacing="0" cellpadding="0" class="qryTable">
		<tr>
			<td>
				<label>物流公司名称</label><input type="text" name="qry.expressName" class="formTextS" id="expressName" value="${qry.expressName}" />
			<input type="submit" id="searchButton" class="btn1" value="查 询"/>
			<shiro:hasPermission name="wh:express:edit">
			<input type="button" class="btn1" id="add_btn" value="新 增" onclick="$.launchPage('<%=path %>/warehouse/express_toAdd.action')" />
			</shiro:hasPermission>	
			</td>
		</tr>	
	</table>
	
	
		<c:choose>
		<c:when test="${page.pageAmount==0}">
			<div class="nodatafound">
				没有查询到符合条件的物流公司
			</div>
		</c:when>
		<c:otherwise>		
		<table border="0" cellspacing="0" cellpadding="0" class="listTable">
			<tr>
				<th>物流公司名称</th>
				<th>联系人</th>
				<th>联系电话</th>
			<th>操作</th>
			</tr>
			<c:forEach items="${page.results}" var="b">
			<tr>
				<td class="center">${b.expressName}</td>
				<td class="center">${b.linker}</td>
				<td class="center">${b.linkerTel}</td>
				<td class="center" nowrap="true">
				<shiro:hasPermission name="wh:express:edit">
				<a href="#none" title="修改" onclick="gotoEdit('${b.expressId}')">修改</a>&nbsp;
				<c:if test="${b.canDelete}">
				<a href="#none" title="删除" onclick="gotoDelete('${b.expressId}')">删除</a>
				</c:if>				
				</shiro:hasPermission>
				<a href="#none" title="查看" onclick="gotoView('${b.expressId}')">查看</a>
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