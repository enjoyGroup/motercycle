<%@ page language="java" contentType="text/html; charset=tis-620" pageEncoding="tis-620"%>
<%@ page import="th.go.motorcycles.app.enjoy.bean.UserDetailsBean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
	UserDetailsBean userDeatil = (UserDetailsBean) request.getSession().getAttribute("userBean");
%>
<head>
<title>�к����͢��ö������䫴�</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
/*td{
	   border:1px solid red;
	}
	
	div{
	   border:1px solid  green;
	}*/
	.header_03
	{
		background-image: url(../images/head/header_02.jpg);
		background-repeat:no-repeat;
		background-position:center left;
	
	
	}
</style>
<script>
function lp_initialize(){}
function lp_ul_click(){}
</script>
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

<!-- css -->
<link href="/motorcyclesWeb/css/bootstrap.css" rel="stylesheet">
<link href="/motorcyclesWeb/css/style_red.css" rel="stylesheet" />
<link href="/motorcyclesWeb/css/menu-styles.css" rel="stylesheet">

<!-- JS -->
<script src="/motorcyclesWeb/js/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="/motorcyclesWeb/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="/motorcyclesWeb/js/menu-script.js" type="text/javascript"></script>

</head>
<link href="/motorcyclesWeb/css/blue.css" type="text/css" rel="stylesheet">
<body style="margin:0px;overflow:hidden;background-color:yellow;">
	<div class="headwrap">
    	<div class="row">
        	<div class="brand span4">
            	<img src="/motorcyclesWeb/images/logo.png" >
            </div>
            
            <div class="span8 user"  style="margin-right:4%;">
            	<div class="font14"><img src="/motorcyclesWeb/images/icon-user.jpg" alt="">���ͼ����ҹ <span class="text_white"><%=userDeatil.getUserName() %>&nbsp;&nbsp;<%=userDeatil.getUserSurname() %></span></div>
                <div class="font12"></div>
                
                <ul>
                    <li><a class="logout" href="#">Logout</a></li>
                	<li class="date">������к� : <span class="text_white">24/08/2557 14:00:09</span> </li>
                </ul>
                
            </div>
        </div><!-- container -->
    </div><!-- headwrap -->

</body>
</html>
  