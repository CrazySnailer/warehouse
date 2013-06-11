<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/include/head.jsp"%>  
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>权限管理</title>
<link href="<%=path%>/res/css/base.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/js/ztree/css/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/js/ztree/css/demo.css" rel="stylesheet" type="text/css"/>

<script language="javascript" src="<%=path%>/res/js/jquery.js"></script>
<script language="javascript" src="<%=path%>/res/js/ztree/js/jquery.ztree.core-3.0.js"></script>
<script language="javascript" src="<%=path%>/res/js/ztree/js/jquery.ztree.excheck-3.0.js"></script>

<script language="JavaScript"><!--

	function getCheckedNodes(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
		$.each(nodes,function(i, item){
			alert(item.name);
		});	
	}
	
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: zTreeOnClick
		}
	};
	
	function zTreeOnClick(event, treeId, treeNode) {
		$('#edit_menuId').val(treeNode.id);
		$('#editForm').submit();
	}

	var zNodes =[
		<c:forEach items="${list}" var="b" varStatus="myVar">
		<c:if test="${myVar.index == 0}">{ id:${b.menuId}, pId:0, name:"${b.name}",treeNo:"${b.treeNo}", open:true,isParent:true<c:if test="${b.check}">,checked:true</c:if>}<c:if test="${!myVar.last}">,</c:if></c:if>
		<c:if test="${myVar.index != 0}">{ id:${b.menuId}, pId:${b.parent.menuId}, name:"${b.name}",treeNo:"${b.treeNo}"<c:if test="${b.check}">,checked:true</c:if>}<c:if test="${!myVar.last}">,</c:if></c:if>
		</c:forEach>
	];

	$(document).ready(function(){
		$('.ztree').height($(document).height()-150);
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		
		$('#btnSavePriv').click(function(){
			var postData = "entity.roleId=${param.roleId}";
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = zTree.getCheckedNodes(true);
			$.each(nodes,function(i, item){
				postData +="&chkIds=" + item.id;
			});
			
			$('#savetip').show();
			$.ajax({
				  url: '<%=path%>/admin/um/role_editRolePriv.action',
				  type: 'POST',
				  data: postData,
				  dataType: "json",
				  success: function(data) {
					if (data.status == "success") {
						alert(data.message);
					}else{
						alert('保存失败');
					}
					$('#savetip').hide();
				  }
			});
		});
	});
	
--></script>
</head>
<body>
	<div class="box">
		<div class="box-content" style="overflow: auto;height: 80%;">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	<div>
	<span id="savetip" class="ajaxtip">数据保存中，请稍候……</span><input type="button" id="btnSavePriv" class="buttonInput" value="保存" id="btnSavePriv" style="float:left;"/>
	</div>
</body>   
 </html>