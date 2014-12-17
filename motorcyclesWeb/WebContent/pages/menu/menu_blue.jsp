<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import = "th.go.motorcycles.app.enjoy.main.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
//Get URLs for this jsp file
final String servURL		= Constants.SERV_URL;

response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

if(session == null || session.getAttribute("userBean") == null){ 
	response.sendRedirect(Constants.LOGIN_FAIL_URL);
}

//UserBean userBean = (UserBean)session.getAttribute("userBean");

%>

<title>Menu</title>
<link href="/motorcyclesWeb/css/menu.css" rel="stylesheet" type="text/css"/>
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
<link href="/motorcyclesWeb/css/style_blue.css" rel="stylesheet" />
<link href="/motorcyclesWeb/css/menu-styles2.css" rel="stylesheet">

<!-- JS -->
<script src="/motorcyclesWeb/js/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="/motorcyclesWeb/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="/motorcyclesWeb/js/menu-script.js" type="text/javascript"></script>

<style type="text/css">
ul,li {
	margin: 0px;
	padding: 0px;
}

.trace , .trace *{outline: 1px solid red;}
</style>
<script language="javascript" >
var gb_is_ie = ( typeof( document.all ) != "undefined" );
function lp_getSrc( ao_evt ){
	return ( gb_is_ie ) ? ao_evt.srcElement : ao_evt.target;
}
function lp_li_click( ao_evt, av_screen ){ 
	try{
		lo_ = lp_getSrc(ao_evt);
		
		if( lo_.innerHTML == lo_.innerHTML.replace("A","") ){
			var lv_txt = lo_.innerHTML;
			lp_set_title( lv_txt , av_screen);
		}
	}catch( ao_e ){}
}
function lp_logout(){
	top.location = "/motorcyclesWeb";
}
function lp_home(av_page_name){
	window.open("/motorcyclesWeb/pages/"+av_page_name+".jsp", "frame_main" );
}
function lp_set_title( av_title , av_screen){ 
	var lo_head   = top.mainFrame.frame_head;
	var lo_nvgt   = lo_head.document.getElementById( "navigator" );
	var lo_mode   = lo_head.document.getElementById( "mode" );
	var lo_screen = lo_head.document.getElementById( "screen_name" );

	if (av_screen != "")
	{
		av_title = av_title.replace("<BR>", "");
		lo_nvgt   . innerHTML = av_title;
		lo_screen . innerHTML = "รหัสหน้าจอ : <B>" + av_screen + "</B>";
	} else {
		lo_nvgt   . innerHTML = "";
		lo_screen . innerHTML = "";
	}	
}
</script>
</head>
<body onload="lp_initialize();" style="overflow-x:hidden; overflow-y:auto;" >

	<div class="wrapper " style='margin-top:0px;'>
    	<div class="container" >
        	<div class="span3 nomarginL" >
            	<h1 style="background:#086289; color:white;  text-transform:uppercase; font-size:2em; text-align:center ;margin:0 !important">
            		menu
            	</h1>
            	<div id='cssmenu' style="min-height:auto;  background:#086289; ">
                        <ul>
                           <li class='has-sub'><a href='#'><span>รายละเอียดการขาย</span></a>
                              <ul onclick="lp_ul_click();">
								<li onclick="lp_li_click(event , '0001');">
									<a href="javascript:lp_home('sale_detail');">บันทึกการขายรถ</a>
								</li>
								<li onclick="lp_li_click(event , '0002');">
									<a href="<%=servURL%>/EnjoyGenericSrv?service=servlet.SummarySaleDetailServlet" target="frame_main">สรุปการขายรถ</a>
								</li>
								<li onclick="lp_li_click(event , '0003');">
									<a href="<%=servURL%>/EnjoyGenericSrv?service=servlet.AddressDemoServlet" target="frame_main">ตัวอย่างตำบล อำเภอ จังหวัด</a>
								</li>
							  </ul>
                           </li>
                           <li class='has-sub'><a href='#'><span>ข้อมูลลูกค้า</span></a>
                             <ul onclick="lp_ul_click();">
								<li onclick="lp_li_click(event , '0003');">
									<a href="<%=servURL%>/EnjoyGenericSrv?service=servlet.CustomerServlet" target="frame_main">บันทึกข้อมูลลูกค้า</a>
								</li>
								<li onclick="lp_li_click(event , '0004');">
									<a href="<%=servURL%>/EnjoyGenericSrv?service=servlet.CustomerSearchServlet" target="frame_main" >ค้นหาข้อมูลลูกค้า</a>
								</li>
							  </ul>
                           </li>
                           <li class='has-sub'><a href='#'><span>ตั้งค่าระบบ</span></a>
                              <ul onclick="lp_ul_click();">
								<li onclick="lp_li_click(event , '0005');">
									<a href="javascript:lp_home('brand');">ข้อมูลยี่ห้อรถ</a>
								</li>
								<li onclick="lp_li_click(event , '0006');">
									<a href="javascript:lp_home('model');">ข้อมูลรุ่น</a>
								</li>
								<li onclick="lp_li_click(event , '0007');">
									<a href="javascript:lp_home('motorcycle_detail');">ข้อมูลรถ</a>
								</li>
							  </ul>
                           </li>
                           <li class=''>
	                            	<a href='#' onclick="lp_logout()" ><span>ออกจากระบบ</span></a>
                           </li>
                       
                        </ul>
                </div>
			</div>
		</div>
	</div>

</body>
</html>