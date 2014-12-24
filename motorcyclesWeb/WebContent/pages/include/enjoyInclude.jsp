<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import = "th.go.motorcycles.app.enjoy.main.Constants"%>
<%
//Get URLs for this jsp file
final String servURL		= Constants.SERV_URL;
final String jsURL 			= Constants.JS_URL;
final String cssURL 		= Constants.CSS_URL;
final String imgURL 		= Constants.IMG_URL;

response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

if(session == null || session.getAttribute("userBean") == null){ 
	response.sendRedirect(Constants.LOGIN_FAIL_URL);
}

//UserBean userBean = (UserBean)session.getAttribute("userBean");

%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="generator" content="editplus">
<meta name="author" content="">
<meta name="keywords" content="">
<meta name="description" content="">   

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="format-detection" content="telephone=no">
<meta name="description" content="">
<meta name="author" >  
<meta name="keywords" content="">

<link  href="/motorcyclesWeb/theme/css/bootstrap.css" rel="stylesheet">
<link  href="/motorcyclesWeb/theme/css/animate.css" rel="stylesheet">
<link  href="/motorcyclesWeb/theme/css/font-awesome.min.css" rel="stylesheet">
<link  href="/motorcyclesWeb/theme/css/font.css" rel="stylesheet">
<link  href="/motorcyclesWeb/theme/js/select2/select2.css" rel="stylesheet">
<link  href="/motorcyclesWeb/theme/js/select2/theme.css" rel="stylesheet">
<link  href="/motorcyclesWeb/theme/js/fuelux/fuelux.css" rel="stylesheet">
<link  href="/motorcyclesWeb/theme/js/datepicker/datepicker.css" rel="stylesheet">
<link  href="/motorcyclesWeb/theme/js/slider/slider.css" rel="stylesheet">
<link  href="/motorcyclesWeb/theme/css/app.css" rel="stylesheet">

<script src="/motorcyclesWeb/theme/js/jquery.min.js"></script>
<script src="/motorcyclesWeb/theme/js/bootstrap.js"></script>
<script src="/motorcyclesWeb/theme/js/app.js"></script> 
<script src="/motorcyclesWeb/theme/js/slimscroll/jquery.slimscroll.min.js"></script>
<script src="/motorcyclesWeb/theme/js/fuelux/fuelux.js"></script>
<script src="/motorcyclesWeb/theme/js/datepicker/bootstrap-datepicker.js"></script>
<script src="/motorcyclesWeb/theme/js/slider/bootstrap-slider.js"></script>
<script src="/motorcyclesWeb/theme/js/file-input/bootstrap-filestyle.min.js"></script>
<script src="/motorcyclesWeb/theme/js/libs/moment.min.js"></script>
<script src="/motorcyclesWeb/theme/js/combodate/combodate.js"></script>
<script src="/motorcyclesWeb/theme/js/select2/select2.min.js"></script>
<script src="/motorcyclesWeb/theme/js/wysiwyg/jquery.hotkeys.js"></script>
<script src="/motorcyclesWeb/theme/js/wysiwyg/bootstrap-wysiwyg.js"></script>
<!-- 
<script src="/motorcyclesWeb/theme/js/wysiwyg/demo.js"></script>
<script src="/motorcyclesWeb/theme/js/markdown/epiceditor.min.js"></script>
<script src="/motorcyclesWeb/theme/js/markdown/demo.js"></script>
 -->
<script src="/motorcyclesWeb/theme/js/app.plugin.js"></script>

<!-- css -->
<link href="<%=cssURL%>/bootstrap.css" rel="stylesheet">
<link href="<%=cssURL%>/style.css" rel="stylesheet" />
<link href="<%=cssURL%>/menu-styles.css" rel="stylesheet">
<link href="<%=cssURL%>/menu-styles2.css" rel="stylesheet">
<link href="<%=cssURL%>/jquery-ui.css" rel="stylesheet">
<link href="<%=cssURL%>/bootstrap-datetimepicker.min.css" rel="stylesheet">

<!-- JS -->
<script src="<%=jsURL%>/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="<%=jsURL%>/bootstrap.min.js" type="text/javascript"></script> 
<script src="<%=jsURL%>/menu-script.js" type="text/javascript"></script>
<script src="<%=jsURL%>/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="<%=jsURL%>/EnjoyUtil.js" type="text/javascript"></script>
<script src="<%=jsURL%>/jquery-ui.js" type="text/javascript"></script>
<script src="<%=jsURL%>/jquery.tablesorter.js" type="text/javascript"></script>
