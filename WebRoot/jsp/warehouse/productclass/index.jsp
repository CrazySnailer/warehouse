<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品分类管理</title>
</head>
 <frameset cols="250,*" id="mainFramesetMenu" frameborder="no" rows="*">
    <frame src="<%=path%>/warehouse/productclass_tree.action" name="leftMenuTree" id="leftMenuTree" marginwidth="0" marginheight="0">
    <frame src="" frameborder="no" scrolling="auto" noresize="noresize" name="rightMenuTree">
  </frameset>
<noframes>

<BODY>
<P>You Browse doesn't support frame!</P>
</BODY>
</noframes>

</html>