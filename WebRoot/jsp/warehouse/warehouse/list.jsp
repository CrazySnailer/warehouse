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
<link href="<%=path%>/res/css/base.css?dddd" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/css/colorbox.css" rel="stylesheet" type="text/css"/>
<%@ include file="/jsp/include/basejs.jsp" %>
<script type="text/javascript">
	function gotoEdit(whId){
		$('#edit_whId').val(whId);
		$('#editForm').submit();
	}
	function gotoDelete(whId){
		jConfirm('确认删除该仓库信息?', '确认删除', function(cresult) {
			if(cresult){
				$.ajax({
					url: '<%=path %>/warehouse/warehouse_delete.action',
					data: 'entity.whId='+whId,
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
	function gotoView(whId){
		$.colorbox({
			href:'<%=path %>/warehouse/warehouse_toView.action?entity.whId='+whId+'&data='+Math.random(),
			iframe:true,
			title: '查看仓库',
			width:"80%", 
			height:"90%"
		});
	}
</script>
</head>
<body>
<div id="querytip" class="qrytip">loading....</div>

<form id="editForm" name="editForm" method="post" action="<%=path %>/warehouse/warehouse_toEdit.action">
	<input type="hidden" name="entity.whId" id="edit_whId" value=""/>
	<input type="hidden" name="qryHex" value="${qry.qryHex}"/>
</form>


<div class="div_tab_header_box">
    <div class="div_tab_header_box_1">
        <div class="corner-1-report_nav"></div>
        <div class="corner-2-report_nav"></div>
        
        <div class="box-header left">仓库管理</div>
        <div class="box-content">
			


	            

<form id="listForm" name="listForm" method="post"  action="<%=path %>/warehouse/warehouse_list.action">
	<table border="0" cellspacing="0" cellpadding="0" class="qryTable">
		<tr>
			<td>
				<label>编号</label><input type="text" name="qry.whCode" class="formTextS" id="whCode" value="${qry.whCode}" />
				<label>仓库名称</label><input type="text" name="qry.whName" class="formTextS" id="whName" value="${qry.whName}" />
				<input type="submit" id="searchButton" class="btn1" value="查 询"/>
				<shiro:hasPermission name="wh:warehouse:edit">
				<input type="button" class="btn1" id="add_btn" value="新 增" onclick="$.launchPage('<%=path %>/warehouse/warehouse_toAdd.action')" />
				</shiro:hasPermission>
			</td>
		</tr>	
	</table>
	
	
		<c:choose>
		<c:when test="${page.pageAmount==0}">
			<div class="nodatafound">
				没有查询到符合条件的仓库
			</div>
		</c:when>
		<c:otherwise>		
		<table border="0" cellspacing="0" cellpadding="0" class="listTable">
			<tr>
				<th>编号</th>
				<th>仓库名称</th>
				<th>所属仓库</th>
				<th>仓库类型</th>
				<th>使用部门</th>
				<th>管理模式</th>
				<th>联系人</th>
			<th>操作</th>
			</tr>
			<c:forEach items="${page.results}" var="b">
			<tr>
				<td class="center">${b.whCode}</td>
				<td class="center">${b.whName}</td>
				<td class="center">${b.parentId}</td>
				<td class="center">${b.whTypeStr}</td>
				<td class="center">${b.useDeptId}</td>
				<td class="center">${b.adminModeStr}</td>
				<td class="center">${b.linker}</td>
				<td class="center" nowrap="true">
				<shiro:hasPermission name="wh:warehouse:edit">
				<a href="#none" title="修改" onclick="gotoEdit('${b.whId}')">修改</a>&nbsp;
				<c:if test="${b.canDelete}">
				<a href="#none" title="删除" onclick="gotoDelete('${b.whId}')">删除</a>
				</c:if>				
				</shiro:hasPermission>
				<a href="#none" title="查看" onclick="gotoView('${b.whId}')">查看</a>
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