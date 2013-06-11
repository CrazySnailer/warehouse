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
$().ready(function() {
        var mainFrameset = window.parent.window.document.getElementById("mainFrameset");
        mainFrameset.cols = "0,*";
})
</script>
</head>
<body>

<div class="c2main">
	<div class="c2left">
		<div class="ajaxtabdiv">
			<div class="div_tab_header">
			    <div class="div_tab_header_1">
			        <div class="corner-1-report_nav"></div>
			        <div class="corner-2-report_nav"></div>
			        <ul class="ajaxtabs">		            
			            <li><a href="#tab_2">待处理订单</a></li>
			            <li><a href="#tab_3">点击数</a></li>
			            <li><a href="#tab_4">填充率</a></li>
			        </ul>
			    </div>
			</div><!--div_tab_header-->
			
			<div class="div_tab_content">
	                <div id="tab_2" class="tab_content">
	                        <table width="100%" cellspacing="0" cellpadding="0" id="j_list_$ipage">
						    			    			        		<tbody>
											    			        		<tr>
        			<td class="thread">
    					<a href="http://www.oschina.net/job/detail/1156858_5914" target="_blank" title="php程序员" class="thread_type_1" style="">
    						php程序员
    					</a>
    					<span class="stat" style="margin-left:0;">（广州市炙乐网络科技有限公司）&nbsp;&nbsp;&nbsp;昨天(11:25) By 伍成芳</span>
    				</td>
        			<td class="last_post">1评/190阅</td>					
        		</tr>
											    			        		
							</tbody></table>
	                </div>
	                <div id="tab_3" class="tab_content">
	                	2
	                
	                </div>
	                <div id="tab_4" class="tab_content">
	                	3
	                </div>
	      	</div><!--div_tab_content-->
		</div><!--ajaxtabdiv-->
	</div><!--c2left-->

	<div class="c2right">	
		
	
	
	</div><!--c2right-->
</div><!--c2main-->




</body>
</html>