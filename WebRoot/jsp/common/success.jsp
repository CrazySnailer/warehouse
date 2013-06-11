<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/include/head.jsp"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.Enumeration" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=path%>/res/css/base.css" rel="stylesheet" type="text/css"/>
<%@ include file="/jsp/include/basejs.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
		$('#id_submit').click(function(){
			$('#savetip').show();
			$('#submitform').submit();
			$('#id_submit').hide();
			$('#id_submit').attr("disabled", true);
		});
		
		<c:if test="${autoRedirect == 0 }">
				$('#savetip').show();
				$('#submitform').submit();
				$('#id_submit').hide();
				$('#id_submit').attr("disabled", true);
		</c:if>
		
		<c:if test="${autoclose}">
		//关闭窗口
		$('#id_colse').click(function(){
			parent.$.colorbox.close();
		});
		</c:if>
	});
</script>
</head>
<body>

<%
	String url = (String)request.getAttribute("redirectUrl");
	out.println("<form action='"+path+url+"' method='post' name='sf' id='submitform'>");
	Enumeration paramsNames = request.getAttributeNames();
	while (paramsNames.hasMoreElements()) { 
        String key = (String) paramsNames.nextElement();
        int idx = key.indexOf("param_");
        if(idx >-1) {
        	String value = (String)request.getAttribute(key);
        	out.println("<input type='hidden' name='"+key.substring(6)+"' value='"+value+"'/>");
        }
	}	
%>

	<div style="background: #fff;">
		<div class="messageBox">
			<div class="boxTop">
				<div class="boxTitle">提示信息&nbsp;</div>
				<a class="boxClose windowClose" href="#" hidefocus="true"></a>
			</div>
			<div class="boxMiddle">
				<div class="messageContent">
					<span class="icon success">&nbsp;</span>
					<span class="messageText">
						<c:forEach items="${messages}" var="b">
						${b}
						</c:forEach>
					</span>
				</div>
				
				<c:choose>
					<c:when test="${autoclose}">
						<input type="button" id="id_colse" class="buttonInput" value="确  定" hidefocus="true" />
					</c:when>
					<c:otherwise>
						<input type="submit" id="id_submit" class="buttonInput" value="确  定" hidefocus="true" /><span id="savetip" class="ajaxtip">正在前往指定页面，请稍候……</span>
					</c:otherwise>
				</c:choose>
			
			</div>
			<div class="boxBottom"></div>
		</div>
	</div>
	</form>
</body>
</html>