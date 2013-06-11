<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.calf.framework.vo.AdminUserInfo"%>
<%@ page import="com.calf.framework.util.Constants"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.calf.framework.um.entity.TbSysMenu"%>

<%@ include file="/jsp/include/head.jsp"%>  
<%@ page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>用户菜单</title>
<link href="<%=path%>/res/css/base.css" rel="stylesheet" type="text/css"/>

<style type="text/css">

/*****Custom Classes*****/
.clearing { clear: both; }
.clearfix { overflow: hidden; }
.last { margin-bottom: 0; }
.screenReader { left: -9999px; position: absolute; top: -9999px; }

/*****Basic Layout*****/
div#container { margin: 0 auto; overflow: hidden; padding: 20px 0; width: 960px; }
div#content { float: left; width: 600px; }
div#sidebar { float: right; font-size: 12px; line-height: 18px; width: 300px; }

/*****Accordion Styles*****/
ul#accordion, ul#accordion ul { list-style: none; margin: 0; }
ul#accordion {border: 1px solid #c2ccd4;border-bottom: none;}
ul#accordion li {border-bottom: none;}
ul#accordion ul li { border: none; border-bottom: 1px solid #C2C8D1; color: #999; padding: 5px 10px; }
ul#accordion ul li:last-child { border-bottom: none; }
ul#accordion a.heading { 
	background: #F4FFF9;
	color: #999;
	display: block;
	font-size: 18px;
	line-height: 45px;
	padding: 0px 5px;
	text-decoration: none;
	border-bottom: 1px solid #c2ccd4;
	text-indent: 20px;
	background: url(http://my.adsmogo.com/Content/images/report_nav_bg.png) repeat-x;
}
ul#accordion a.heading:hover { 
background: url(http://my.adsmogo.com/Content/images/report_nav_bg.png) 0 -45px repeat-x;
color: #fff;
}
ul#accordion li.ui-accordion-selected a.heading, ul#accordion li.current a.heading { background: url(http://my.adsmogo.com/Content/images/report_nav_bg.png) 0 -45px repeat-x; color: #fff; }
ul#accordion li ul a { border-bottom: 1px solid #00B9D2; color: #025185; text-decoration: none; }
ul#accordion li ul a:hover { border-bottom: none; }
ul#accordion li ul .date { padding-right: 10px; }
ul#accordion li ul .count { padding-left: 10px; }

/*****Additional Custom Accordion Styles*****/
body#customAccordion ul#accordion li ul { display: none; }
body#customAccordion ul#accordion li.current ul { display: block; }



#reportNav { width:168px; float: left; font-size: 18px; line-height: 45px; position: relative; }
#reportNav ul { border: 1px solid #c2ccd4; border-bottom: none; }
#reportNav ul li { border-bottom: 1px solid #c2ccd4; text-indent: 20px; background: url(http://my.adsmogo.com/Content/images/report_nav_bg.png) repeat-x; }
#reportNav ul li a { text-decoration: none; color: #333; display: block; }
#reportNav ul li a:hover { color: #008dac; }
#reportNav ul li .current, #reportNav ul li .current:hover { background: url(http://my.adsmogo.com/Content/images/report_nav_bg.png) 0 -45px repeat-x; color: #fff; display: block; }

.corner-1-AppNav, .corner-2-AppNav, .corner-3-AppNav, .corner-4-AppNav { /*{ 设置圆角通用属性 }*/ background: url(http://my.adsmogo.com/Content/images/AppNav_bg.png) no-repeat; position: absolute; z-index: 100; font-size: 0; /*{ Hack in IE6 }*/ }
.corner-1-AppNav, .corner-2-AppNav { height: 5px; width: 5px; top: 0; }
.corner-3-AppNav, .corner-4-AppNav { height: 7px; width: 5px; bottom: -2px; }
.corner-1-AppNav { background-position: 0 0; left: 0; }
.corner-2-AppNav { background-position: -5px 0; right: 0 !important; /*{ Hack in IE 6 }*/ }
.corner-3-AppNav { background-position: 0 -5px; left: 0; }
.corner-4-AppNav { background-position: -5px -5px; right: 0 !important; /*{ Hack in IE 6 }*/ }


</style>

<script language="javascript" src="<%=path%>/res/js/jquery.js"></script>
<script language="JavaScript">
	$().ready(function() {
		$('ul#accordion a.heading').click(function() {
			$(this).css('outline','none');
			if($(this).parent().hasClass('current')) {
				$(this).siblings('ul').slideUp('slow',function() {
					$(this).parent().removeClass('current');
				});
			} else {
				$('ul#accordion li.current ul').slideUp('slow',function() {
					$(this).parent().removeClass('current');
				});
				$(this).siblings('ul').slideToggle('slow',function() {
					$(this).parent().toggleClass('current');
				});
			}
			return false;
		});
	});
</script> 
<body>

<div id="reportNav" style="margin-bottom: 66px;">
        <div class="corner-1-AppNav">
        </div>
        <div class="corner-2-AppNav">
        </div>
        <div class="corner-3-AppNav">
        </div>
        <div class="corner-4-AppNav">
        </div>
        <ul>
            <li><a class="current" href="/AppUser/app?begin=2013-06-09&amp;end=2013-06-09">
                广告数据</a> </li>
            <!--判断app是否有数据-->
            

          
           
            <li><a href="/AppUser/User?begin=2013-06-09&amp;end=2013-06-09">
                受众数量</a> </li>
            <li><a href="/AppUser/Country?begin=2013-06-09&amp;end=2013-06-09">
                受众位置</a> </li>
            <li><a href="/AppUser/Device?begin=2013-06-09&amp;end=2013-06-09">
                终端网络</a> </li>
            
            <li><a href="javascript:;" id="Version">版本分析</a></li>
            <li><a href="javascript:;" id="Version">版本分析</a></li>
            
            <li><a href="javascript:;" id="Version">版本分析</a></li>
            <li><a href="javascript:;" id="Version">版本分析</a></li>
            <li><a href="javascript:;" id="Version">版本分析</a></li>
            <li><a href="javascript:;" id="Version">版本分析</a></li>
            
        </ul>
    </div>
<!--
		<ul id="accordion">
			<li class="current">
				<a href="?section=recent" class="heading">Recent</a>
				<ul id="recent">
					<li><a href="#">Recent Entry Title</a></li>
					<li><a href="#">Recent Entry Title</a></li>
					<li><a href="#">Recent Entry Title</a></li>
					<li><a href="#">Recent Entry Title</a></li>
					<li><a href="#">Recent Entry Title</a></li>
				</ul>
			</li>
			<li>
				<a href="?section=popular" class="heading">Popular</a>
				<ul id="popular">
					<li><span class="date">08.16.2008</span> <a href="#">Popular Entry Title</a></li>
					<li><span class="date">06.12.2008</span> <a href="#">Popular Entry Title</a></li>
					<li><span class="date">04.12.2008</span> <a href="#">Popular Entry Title</a></li>
					<li><span class="date">06.12.2007</span> <a href="#">Popular Entry Title</a></li>
					<li><span class="date">03.12.2007</span> <a href="#">Popular Entry Title</a></li>
				</ul>
			</li>
			<li>
				<a href="?section=categories" class="heading">Categories</a>
				<ul id="categories">
					<li><a href="#">Category Name</a> <span class="count">7</span></li>
					<li><a href="#">Category Name</a> <span class="count">4</span></li>
					<li><a href="#">Category Name</a> <span class="count">15</span></li>
					<li><a href="#">Category Name</a> <span class="count">29</span></li>
					<li><a href="#">Category Name</a> <span class="count">8</span></li>
				</ul>
			</li>
			<li>
				<a href="?section=archive" class="heading">Archive</a>
				<ul id="archive">
					<li><a href="#">January 2009</a> <span class="count">4</span></li>
					<li><a href="#">December 2008</a> <span class="count">14</span></li>
					<li><a href="#">November 2008</a> <span class="count">12</span></li>
					<li><a href="#">October 2008</a> <span class="count">8</span></li>
					<li><a href="#">September 2008</a> <span class="count">18</span></li>
				</ul>
			</li>
		</ul>
-->
</body>
</html>