<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/include/head.jsp"%>
<%@page import="com.calf.framework.util.CodeNameUtils"%>
<%@page import="com.calf.framework.util.Constants"%>
	
	<c:forEach items="${detailList}" var="b" varStatus="idx">
	<tr>
		<td class="center">${b.product.productSku}</td>	
		<td class="center">${b.product.productCode}</td>
		<td class="center">${b.product.productName}</td>
		<td class="center">${b.product.brandName}/${b.product.modelNo}</td>
		<td class="center">${b.qty}</td>
		<td class="center">${b.remarks}</td>
		<td class="center" nowrap="true">
		<shiro:hasPermission name="wh:orderin:edit">
		<a href="#none" title="修改" onclick="openEditDialog(${idx.index})">修改</a>&nbsp;
		<a href="#none" title="删除" onclick="removeDetail(${idx.index})">删除</a>
		</shiro:hasPermission>
		</td>
	</tr>
	</c:forEach>