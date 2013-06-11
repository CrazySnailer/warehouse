<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="com.calf.framework.util.CodeNameUtils"%>
<%@ include file="/jsp/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/res/css/base.css" rel="stylesheet" type="text/css"/>
<%@ include file="/jsp/include/basejs.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
			$('#deptLevel').val('${entity.deptLevel}');
			$('#deptKind').val('${entity.deptKind}');
			$('#dataStatus').val('${entity.dataStatus}');
	});
</script>
</head>
<body>
<form id="validateForm" name="validateForm" action="<%=path %>/admin/um/dept_edit.action" method="post">
	<input type="hidden" name="qryHex" value="${qryHex}"/>
	<input type="hidden" name="event" value="${event}"/>
	<table border="0" cellspacing="0" cellpadding="0" class="editTable">
		<tr>
			<th colspan="4" class="left">${title}</th>
		</tr>
		<tr>
			<td class="right" style="width:15%;">机构ID</td>
			<td style="width:35%;">
			<input type="text" name="entity.deptId" class="formText {required: true,byteRangeLength:[0,12],messages: {required:'请输入机构ID'}}" id="deptId" value="${entity.deptId}"/>
			<label class="required">*</label>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">机构编号</td>
			<td style="width:35%;">
			<input type="text" name="entity.deptCode" class="formText {required: true,byteRangeLength:[0,20],messages: {required:'请输入机构编号'}}" id="deptCode" value="${entity.deptCode}"/>
			<label class="required">*</label>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">行政区划</td>
			<td style="width:35%;">
			<input type="text" name="entity.areaCode" class="formText {byteRangeLength:[0,6]}" id="areaCode" value="${entity.areaCode}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">上级机构</td>
			<td style="width:35%;">
			<input type="text" name="entity.parentDeptId" class="formText {byteRangeLength:[0,12]}" id="parentDeptId" value="${entity.parentDeptId}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">机构名称</td>
			<td style="width:35%;">
			<input type="text" name="entity.deptName" class="formText {required: true,byteRangeLength:[0,200],messages: {required:'请选择机构名称'}}" id="deptName" value="${entity.deptName}"/>
			<label class="required">*</label>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">机构性质</td>
			<td style="width:35%;">
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.deptKind\" id=\"deptKind\" class=\"formSelect \">","","g_um_dept_kind")%>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">机构级别 </td>
			<td style="width:35%;">
			
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.deptLevel\" id=\"deptLevel\" class=\"formSelect \">","","g_um_dept_level")%>
			<label class="required">*</label>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">是否是叶子节点</td>
			<td style="width:35%;">
			<input type="text" name="entity.isLeaf" class="formText {byteRangeLength:[0,1]}" id="isLeaf" value="${entity.isLeaf}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">机构全名</td>
			<td style="width:35%;">
			<input type="text" name="entity.fullName" class="formText {byteRangeLength:[0,60]}" id="fullName" value="${entity.fullName}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">排序号</td>
			<td style="width:35%;">
			<input type="text" name="entity.orderNum" class="formText {byteRangeLength:[0,3]}" id="orderNum" value="${entity.orderNum}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">联系人</td>
			<td style="width:35%;">
			<input type="text" name="entity.linkMan" class="formText {byteRangeLength:[0,50]}" id="linkMan" value="${entity.linkMan}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">联系电话</td>
			<td style="width:35%;">
			<input type="text" name="entity.linkTel" class="formText {byteRangeLength:[0,50]}" id="linkTel" value="${entity.linkTel}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">联系地址</td>
			<td style="width:35%;">
			<input type="text" name="entity.linkAddr" class="formText {byteRangeLength:[0,100]}" id="linkAddr" value="${entity.linkAddr}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">电子邮件</td>
			<td style="width:35%;">
			<input type="text" name="entity.linkEmail" class="formText {required: true,byteRangeLength:[0,70],messages: {required:'请输入电子邮件'}}" id="linkEmail" value="${entity.linkEmail}"/>
			<label class="required">*</label>
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">备注</td>
			<td style="width:35%;">
			<input type="text" name="entity.note" class="formText {byteRangeLength:[0,200]}" id="note" value="${entity.note}"/>
			
			</td>
		</tr>
		<tr>
			<td class="right" style="width:15%;">树形机构编码</td>
			<td style="width:35%;">
			<input type="text" name="entity.treeNo" class="formText {byteRangeLength:[0,30]}" id="treeNo" value="${entity.treeNo}"/>
			
			</td>
		</tr>
		<tr>
		<th class="center" colspan="2"><span id="savetip" class="ajaxtip">数据保存中，请稍候……</span><input type="submit" class="buttonInput" value="保 存"/><input type="button" class="buttonInput" value="返 回" onclick="history.go(-1)"/></th>
		</tr>
	</table>	
</form>

</body>
</html>