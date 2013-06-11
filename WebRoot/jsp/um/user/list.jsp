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
	function gotoEdit(userId){
		$('#edit_userId').val(userId);
		$('#editForm').submit();
	}
	function gotoDelete(userId){
		jConfirm('确认删除该人员管理信息?', '确认删除', function(cresult) {
			if(cresult){
				$.ajax({
					url: '<%=path %>/admin/um/user_delete.action',
					data: 'entity.userId='+userId,
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
	
	function gotoEditRole(userId){
		$.colorbox({
				href:'<%=path %>/admin/um/user_toAssignRole.action?entity.userId='+userId+'&data='+Math.random(),
				iframe:true,
				width:"500px",
				height:"500px"
		});
	}
	
	$().ready( function() {	
		$('#pageSize').val('${page.pageSize}');
	});
</script>
</head>
<body>
<div id="querytip" class="qrytip">loading....</div>

<form id="editForm" name="editForm" method="post" action="<%=path %>/admin/um/user_toEdit.action">
	<input type="hidden" name="entity.userId" id="edit_userId" value=""/>
	<input type="hidden" name="qryHex" value="${qry.qryHex}"/>
</form>


<div class="frameShadow">
        <div class="corner-1">
        </div>
        <div class="corner-2">
        </div>
        <div class="corner-3">
        </div>
        <div class="corner-4">
        </div>
        
        
        
        <div class="frame_profile">
            
<form id="listForm" name="listForm" method="post"  action="<%=path %>/admin/um/user_list.action">
	<input type="hidden" name="qryit" value="1"/>
	<table border="0" cellspacing="0" cellpadding="0" class="qryTable">
		<tr>
			<th class="left" colspan="6">首页-&gt;人员管理查询</th>		
		</tr>		
		<tr>			
				<td class="right">机构</td>
				<td><input type="hidden" name="qry.deptId" id="deptId" value="${qry.deptId}" />
				<input type="text" name="qry.deptName" class="formTextS" id="deptName" value="${qry.deptName}" />
				</td>
				<td class="right">登录名</td>
				<td><input type="text" name="qry.userCode" class="formTextS" id="userCode" value="${qry.userCode}" /></td>
				<td class="right">用户名</td>
				<td><input type="text" name="qry.userName" class="formTextS" id="userName" value="${qry.userName}" /></td>
		</tr>		
		<tr>
				<td class="right">手机</td>
				<td><input type="text" name="qry.mobile" class="formTextS" id="mobile" value="${qry.mobile}" /></td>
				<td class="right">每页显示</td>
				<td colspan="4">
				<%=Constants.PAGE_STR_RPT%>
				<input type="submit" id="searchButton" class="buttonInput" value="查 询"/>
				<shiro:hasPermission name="um:user:edit">
				<input type="button" class="buttonInput" id="add_btn" value="新 增" onclick="$.launchPage('<%=path %>/admin/um/user_toAdd.action?entity.dept.deptId=${qry.deptId}')" />
				</shiro:hasPermission>
				</td>
		</tr>	
	</table>            
            
            <div class="clear">
            </div>
            
        </div>
    </div>


	
	
		<c:choose>
		<c:when test="${page.pageAmount==0 && param.qryit == '1'}">
			<div class="nodatafound">
				没有查询到指定数据
			</div>
		</c:when>
		<c:otherwise>		
		
		<div class="frameShadow">
	        <div class="corner-1">
	        </div>
	        <div class="corner-2">
	        </div>
	        <div class="corner-3">
	        </div>
	        <div class="corner-4">
	        </div>   
	        <div class="frame_profile">
        
		<table border="0" cellspacing="0" cellpadding="0" class="listTable">
			<tr>
				<th>登录名</th>
				<th>用户名</th>
				<th>是否管理员</th>
				<th>所属机构</th>
				<th>性别</th>
				<th>学历</th>
				<th>毕业学校</th>
				<th>出生日期</th>
				<th>婚姻状况</th>
				<th>电话</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${page.results}" var="b">
			<tr>
				<td class="center">${b.userCode}</td>
				<td class="center">${b.userName}</td>
				<td class="center">${b.isAdminStr}</td>
				<td class="center">${b.deptName}</td>
				<td class="center">${b.sexStr}</td>
				<td class="center">${b.eduStr}</td>
				<td class="center">${b.school}</td>
				<td class="center">${b.birthdayStr}</td>
				<td class="center">${b.marry}</td>
				<td class="center">${b.tel}/${b.mobile}</td>
				<td class="center">
				<a href="#none" class="ico ico-edit" title="修改" onclick="gotoEdit('${b.userId}')"/>&nbsp;
				<a href="#none" class="ico ico-delete" title="删除" onclick="gotoDelete('${b.userId}')" />&nbsp;
				<a href="<%=path %>/admin/um/user_toView.action?entity.userId=${b.userId}" class="ico ico-view" title="查看"/>&nbsp;
				<a href="#none" onclick="gotoEditRole('${b.userId}')" class="ico ico-settings" title="设置角色"/>&nbsp;
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
		
		
		<div class="clear">
            </div>
            
        </div>
    </div>
		</c:otherwise>
		</c:choose>
</form>

</body>
</html>