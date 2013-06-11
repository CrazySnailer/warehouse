<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/include/head.jsp"%>  
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=path%>/res/css/base.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/css/header.css" rel="stylesheet" type="text/css"/>
<script language="javascript" src="<%=path%>/res/js/jquery.js"></script>

<script type="text/javascript">
$().ready(function() {
    var $main = $("#main");
    $main.click( function() {
        var mainFrameset = window.parent.window.document.getElementById("mainFrameset");
        if(mainFrameset.cols == "190,6,*") {
            mainFrameset.cols = "0,6,*";
            $main.removeClass("leftArrow");
            $main.addClass("rightArrow");
        } else {
            mainFrameset.cols = "190,6,*";
            $main.removeClass("rightArrow");
            $main.addClass("leftArrow");
        }
    })
})
</script> 
</head>
<body class="middle">
<div id="main" class="main leftArrow" title="显示或隐藏菜单"></div>
</body>
</html>