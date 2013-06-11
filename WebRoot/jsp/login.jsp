<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.calf.framework.util.Constants"%>
<%@ include file="/jsp/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title><%=Constants.PROJECT_NAME%></title>

<link href="<%=path%>/res/css/login.css" rel="stylesheet" type="text/css"/>
<script language="javascript" src="<%=path%>/res/js/jquery.js"></script>
<script language="JavaScript">
	function changeRadio(val){
		if(val=='1'){
			$('#userCode').val('zhoucl');
			$('#pwd').val('123456');
		}else if(val=='21'){
			$('#userCode').val('JXADMIN');
			$('#pwd').val('123456');
		}else if(val=='22'){
			$('#userCode').val('JXNCADMIN');
			$('#pwd').val('123456');
		}else if(val=='23'){
			$('#userCode').val('JXNCDH');
			$('#pwd').val('123456');
		}else if(val=='3'){
			$('#userCode').val('DHZXADMIN');
			$('#pwd').val('123456');
		}
	}
	
	$().ready(function() {
		changeRadio('1');
	});
</script>
</head>
<body>
<script type="text/javascript">
	if (self != top) {
		top.location = self.location;
	};
</script>
<div id="login">
	<div class="warp">
    	<div class="content">
            <h1><%=Constants.PROJECT_NAME%></h1>
			<form id="loginForm" name="loginForm" method="post" action="<%=path%>/admin/login_login.action">
                <div class="item"><div class="input"><div class="icon" title="用户名"></div><input type="text" id="userCode" name="userCode" placeholder="UserCode" value="zhoucl"/></div><label>用户名：</label></div>
                <div class="item"><div class="input"><div class="icon2" title="密码"></div><input type="password" id="password" name="pwd" placeholder="Pwd" value="123456"/></div><label>密码：</label></div>
                <div class="item"><div class="input"><div class="icon2" title="验证码"></div><input type="password" id="password" name="ddd" placeholder="ddd" value="123456"/></div><label>验证码：</label></div>
                <input type="submit" tabindex="3" value="" class="submit" />
                <input name="loginType" type="hidden" id="loginType1" value="ROOT_WH"/>
            </form>
            <p class="copyright"></p>
        </div>   
    </div>
</div>

</body>
</html>
