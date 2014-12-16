<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import = "th.go.motorcycles.app.enjoy.main.Constants"%>
<%
//Get URLs for this jsp file
final String servURL		= Constants.SERV_URL;
final String jsURL 			= Constants.JS_URL;
final String cssURL 		= Constants.CSS_URL;
final String imgURL 		= Constants.IMG_URL;

int 	errCode 	= 0;
String 	errMsg 		= ""; 
String 	msg 		= "";

response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

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

<!-- css -->
<link href="<%=cssURL%>/bootstrap.css" rel="stylesheet">
<link href="<%=cssURL%>/style.css" rel="stylesheet" />
<link href="<%=cssURL%>/menu-styles.css" rel="stylesheet">

<!-- JS -->
<script src="<%=jsURL%>/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="<%=jsURL%>/bootstrap.min.js" type="text/javascript"></script> 
<script src="<%=jsURL%>/menu-script.js" type="text/javascript"></script>
