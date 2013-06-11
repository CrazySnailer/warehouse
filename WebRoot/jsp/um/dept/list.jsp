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
<%@ include file="/jsp/include/basejs.jsp" %>
<script type="text/javascript">
	function gotoEdit(deptId){
		$('#edit_deptId').val(deptId);
		$('#editForm').submit();
	}
	function gotoDelete(deptId){
		jConfirm('确认删除该机构管理信息?', '确认删除', function(cresult) {
			if(cresult){
				$.ajax({
					url: '<%=path %>/admin/um/dept_delete.action',
					data: 'entity.deptId='+deptId,
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

<form id="editForm" name="editForm" method="post" action="<%=path %>/admin/um/dept_toEdit.action">
	<input type="hidden" name="entity.deptId" id="edit_deptId" value=""/>
	<input type="hidden" name="qryHex" value="${qry.qryHex}"/>
</form>

<form id="listForm" name="listForm" method="post"  action="<%=path %>/admin/um/dept_list.action">
	<input type="hidden" name="qryit" value="1"/>
	<table border="0" cellspacing="0" cellpadding="0" class="qryTable">
		<tr>
			<th class="left" colspan="1">首页-&gt;机构管理查询</th>		
		</tr>		
		<tr>			
			<td class="right">每页显示</td>
			<td>
			<%=Constants.PAGE_STR_RPT%>
			<input type="submit" id="searchButton" class="buttonInput" value="查 询"/>			
			<input type="button" class="buttonInput" id="add_btn" value="新 增" onclick="$.launchPage('<%=path %>/admin/um/dept_toAdd.action')" />
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
				<th>机构编号</th>
				<th>行政区划</th>
				<th>机构名称</th>
				<th>机构性质  0机构  1部门</th>
				<th>机构级别 </th>
				<th>是否是叶子节点</th>
				<th>机构全名</th>
				<th>排序号</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>联系地址</th>
				<th>电子邮件</th>
				<th>备注</th>
				<th>树形机构编码</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${page.results}" var="b">
			<tr>
				<td class="center">${b.deptCode}</td>
				<td class="center">${b.areaCode}</td>
				<td class="center">${b.deptNameStr}</td>
				<td class="center">${b.deptKindStr}</td>
				<td class="center">${b.deptLevel}</td>
				<td class="center">${b.isLeaf}</td>
				<td class="center">${b.fullName}</td>
				<td class="center">${b.orderNum}</td>
				<td class="center">${b.linkMan}</td>
				<td class="center">${b.linkTel}</td>
				<td class="center">${b.linkAddr}</td>
				<td class="center">${b.linkEmail}</td>
				<td class="center">${b.note}</td>
				<td class="center">${b.treeNo}</td>
				<td class="center">
				<a href="#none" class="ico ico-edit" title="修改" onclick="gotoEdit('${b.deptId}')"/>&nbsp;
				<a href="#none" class="ico ico-delete" title="删除" onclick="gotoDelete('${b.deptId}')" />&nbsp;
				<a href="<%=path %>/admin/um/dept_toView.action?entity.deptId=${b.deptId}" class="ico ico-view" title="查看"/>&nbsp;
				</td>
			</tr>
			</c:forEach>
		</table>
		
		<div class="pagerBar">		
			<script type="text/javascript">
			$().ready( function() {			
				$("#pager").pager({
					pagenumber: ${page.pageNo},
					pagecount: ${page.pageAmount},
					pagesize:${page.pageSize},
					pagerecordSize: ${page.recordSize},
					buttonClickCallback: $.gotoPage
				});
			});
			</script>
			<span id="pager"></span>
			<input type="hidden" name="qry.pageNo" id="pageNo" value="${page.pageNo}" />
		</div>
		</c:otherwise>
		</c:choose>
</form>

</body>
</html>