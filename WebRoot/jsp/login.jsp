<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.calf.framework.util.Constants"%>
<%@ include file="/jsp/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统登录</title>
<link href="<%=path%>/res/css/login2.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="<%=path%>/res/js/jquery.js"></script>

<script>
$().ready(function() {
	$(".i-text").focus(function(){
		$(this).addClass('h-light');
	});
	
	$(".i-text").focusout(function(){
		$(this).removeClass('h-light');
	});

	$( ".registerform" ).submit(function( event ) {
		var objtip=$(".error-box");
		var isvaild = true;
		
		if($('#username').val()===''){
			isvaild = false;
			objtip.text('请输入用户名！');
			event.preventDefault();
			$('#username').focus();
			return;
		}
		
		if($('#password').val()===''){
			isvaild = false;
			objtip.text('请输入密码！');
			event.preventDefault();
			$('#password').focus();
			return;
		}
		
		if($('#yzm').val()===''){
			isvaild = false;
			objtip.text('请输入验证码！');
			event.preventDefault();
			$('#yzm').focus();
			return;
	}	
});


});
</script>


</head>

<body>

<script type="text/javascript">
	if (self != top) {
		top.location = self.location;
	};
</script>

<div class="header">
  <h1 class="headerLogo"><a title="后台管理系统" target="_blank" href=""><img alt="logo" src="<%=path%>/res/images/logo.gif"></a></h1>
	<div class="headerNav">
		<!--
		<a target="_blank" href="">意见反馈</a>
		<a target="_blank" href="">帮助</a>
		-->	
	</div>
</div>

<div class="banner">

<div class="login-aside">
  <div id="o-box-up"></div>
  <div id="o-box-down"  style="table-layout:fixed;">
   <div class="error-box"></div>
   
   <form class="registerform" action="<%=path%>/admin/login_login.action">
                   <input name="loginType" type="hidden" id="loginType1" value="ROOT_WH"/>
   
   <div class="fm-item">
	   <label for="logonId" class="form-label">登录用户名：</label>
	   <input type="text" value="" maxlength="100" name="userCode" id="username" class="i-text" datatype="s1-100" nullmsg="请输入登录用户名！" errormsg="请输入登录用户名！"  >    
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item">
	   <label for="logonId" class="form-label">登陆密码：</label>
	   <input type="password" value="" maxlength="100" name="pwd" id="password" class="i-text" datatype="*1-16" nullmsg="请输入密码！" errormsg="密码范围在6~16位之间！">    
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item pos-r">
	   <label for="logonId" class="form-label">验证码</label>
	   <input type="text" value="" maxlength="100" id="yzm" class="i-text yzm" datatype="s1-100" nullmsg="请输入验证码！" >    
       <div class="ui-form-explain"><img src="<%=path%>/res/images/yzm.jpg" class="yzm-img" /></div>
  </div>
  
  <div class="fm-item">
	   <label for="logonId" class="form-label"></label>
	   <input type="submit" value="" tabindex="4" id="send-btn" class="btn-login"> 
       <div class="ui-form-explain"></div>
  </div>
  
  </form>
  
  </div>

</div>

	<div class="bd">
		<ul>
			<li style="background:url(<%=path%>/res/images/theme-pic1.jpg) #CCE1F3 center 0 no-repeat;"></li>
		</ul>
	</div>

	<div class="hd"><ul></ul></div>
</div>

<div class="banner-shadow"></div>

<div class="footer">
   <p>Copyright &copy; 2014.Company name All rights reserved.</p>
</div>
</body>
</html>
