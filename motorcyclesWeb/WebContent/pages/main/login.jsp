<%@ include file="/pages/include/enjoyLoginInclude.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>ระบบซื้อขายรถมอเตอร์ไซด์</title>
		<style type="text/css">
			button{
				border:0px;
				background-color:#ffffff;
				cursor:pointerk
			}
			.txtbox
			{
				font-size: 12px;
				color: #336bba;
				font-family: "Tahoma";
				background-color: #eef5fb;
				padding: 1px;
				border: 1px solid #a7c1e5;
			}
		</style>
		<script language="JavaScript" type="text/JavaScript">
			<!--
			function MM_preloadImages() { //v3.0
			    var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
			    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
			    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
			    document.getElementById("username").focus();
			}
			
			function MM_swapImgRestore() { //v3.0
			  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
			}
			
			function MM_findObj(n, d) { //v4.01
			  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
			    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
			  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
			  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
			  if(!x && d.getElementById) x=d.getElementById(n); return x;
			}
			
			function MM_swapImage() { //v3.0
			  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
			   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
			}
			
			//-->
			function lp_changeEnterToTab(e)
			{
				var keycode =(window.event) ? event.keyCode : e.keyCode;
				if(keycode == 13) {
					event.keyCode = 9;
				}
			} 
			
			function lp_changeEnterToTab_forPWD(e)
			{
				var keycode =(window.event) ? event.keyCode : e.keyCode;
				if(keycode == 13) {
					lp_submit_login();
				}
			} 
			
			$(document).ready(function(){
				$('#txtUser').focus();				
				$('#btnLogin').click(function(){
					
					//var url 	= '/motorcyclesWeb/LoginServlet';
					var url 	= '<%=servURL%>/EnjoyGenericSrv?service=servlet.LoginServlet';
					var userId	= null;
					var pass	= null;
					var params	= null;
					
					try{
						//var params = "op=edit&" + $('#frm').serialize();
						userId 	= $('#username').val();
						pass 	= $('#user_pwd').val();
						
						if (userId == "") {
							alert("กรุณาระบุรหัสผู้ใช่ก่อนทำการเข้าสู่ระบบ");
							$('#username').focus();
							return false;
						}
						if (pass == "") {
							alert("กรุณาระบุรหัสผ่านก่อนทำการเข้าสู่ระบบ");
							$('#user_pwd').focus();
							return false;
						}
						
						params 	= "userId=" + userId + "&passWord=" + pass;
						$.ajax({
							async:false,
				            type: "POST",
				            url: url,
				            data: params,
				            beforeSend: "",
				            success: function(data){
				            	//gp_progressBarOff();
				            	if(data.indexOf('OK') > -1){
							 		window.location.replace('/motorcyclesWeb/pages/menu/index.jsp');
								}else{
									alert(data);
								}
				            }
				        });
					}catch(err){
						alert("btnLogin :: " + err);
					}
					
				});
			});
		</script>	
	</head>
	<body style="background-color: #c30000;">
			<div class="container" style="width: 100%;height:100%">
				<div class="row vertical-offset-100">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">  
                            	<div style="height: 50px;"></div>                              
                                <div class="row-fluid user-row" style="width: 100%;text-align: center;">
                                    <img src="/motorcyclesWeb/images/Logo_login.jpg" class="img-responsive" alt="Conxole Admin"/>
                                </div>
                            </div>
                            <div class="panel-body">
                            	<form  name="formlogin"  accept-charset="UTF-8" role="form" class="form-signin" style="text-align: center;" action=""  >
                                    <fieldset>
                                       <br>
                                       <div style="text-align: center;width: 100%">
                                       		 <input class="form-control" placeholder="Username" id="username"  name="username" type="text"  maxlength="10"  onkeydown="lp_changeEnterToTab();">
	                                        <br>
	                                        <input class="form-control" placeholder="Password" id="user_pwd"  name="user_pwd"  type="password"  maxlength="10"  onkeydown="lp_changeEnterToTab_forPWD();">
	                                        <br></br>
	                                        <input class="btn btn-lg btn-success "  id="btnLogin" value="Login"  style="width: 80px;">
	                                        <br></br>
	                         			</div>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
				</div>
			</div>
	</body>
</html>
