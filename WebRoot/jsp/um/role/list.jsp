<%@ page language="java" contentType="text/html; charset=utf-8"%>
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
	function gotoEdit(roleId){
		$('#edit_roleId').val(roleId);
		$('#editForm').submit();
	}
	
	function gotoEditRole(roleId){
		$.colorbox({
			href:'<%=path%>/admin/um/menu_chkTree.action?roleId='+roleId+'&data='+Math.random(),
			iframe:true,
			title: '设置权限',
			width:"500px", 
			height:"90%"
		});
	}
	
	function gotoDelete(roleId){
		jConfirm('确认删除该角色管理信息?', '确认删除', function(cresult) {
			if(cresult){
				$.ajax({
					url: '<%=path %>/admin/um/role_delete.action',
					data: 'entity.roleId='+roleId,
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

<form id="editForm" name="editForm" method="post" action="<%=path %>/admin/um/role_toEdit.action">
	<input type="hidden" name="entity.roleId" id="edit_roleId" value=""/>
	<input type="hidden" name="qryHex" value="${qry.qryHex}"/>
</form>


<div class="ajaxtabdiv">
	<div class="div_tab_header">
	    <div class="div_tab_header_1">
	        <div class="corner-1-report_nav"></div>
	        <div class="corner-2-report_nav"></div>
	        <ul class="rptMenu ajaxtabs">		            
	            <li><a href="#tab_1">角色列表</a></li>
	            <li><a href="<%=path %>/admin/um/role_toAdd.action">新增</a></li>
	        </ul>
	    </div>
	</div><!--div_tab_header-->
	
	<div class="div_tab_content_qry">
            <div id="tab_1" class="tab_content">
            
<form id="listForm" name="listForm" method="post"  action="<%=path %>/admin/um/role_list.action">
	<input type="hidden" name="qryit" value="1"/>
	<table border="0" cellspacing="0" cellpadding="0" class="qryTable">
	
		<tr>			
			<td><label>角色名称</label><input type="text" name="qry.roleName" class="formTextS" id="roleName" value="${qry.roleName}" />
			<label>角色级别</label><input type="text" name="qry.roleLevel" class="formTextS" id="roleLevel" value="${qry.roleLevel}" />
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
				<th>角色名称</th>
				<th>角色级别</th>
				<th>备注</th>
				<th>数据状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${page.results}" var="b">
			<tr>
				<td class="center">${b.roleName}</td>
				<td class="center">${b.roleLevelStr}</td>
				<td class="center">${b.note}</td>
				<td class="center">${b.dataStatusStr}</td>
				<td class="center">
				<a href="#none" class="ico ico-edit" title="修改" onclick="gotoEdit('${b.roleId}')"/>&nbsp;
				<a href="#none" class="ico ico-delete" title="删除" onclick="gotoDelete('${b.roleId}')" />&nbsp;
				<a href="#none" onclick="gotoEditRole('${b.roleId}')" class="ico ico-settings" title="设置权限"/>&nbsp;
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