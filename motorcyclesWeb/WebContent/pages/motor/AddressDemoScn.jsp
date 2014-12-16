<%@ include file="/pages/include/enjoyInclude.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="th.go.motorcycles.app.enjoy.form.AddressDemoForm"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="addressDemoForm" class="th.go.motorcycles.app.enjoy.form.AddressDemoForm" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
	$(document).ready(function(){
	
		$('#btnReset').click(function(){
		    var lo_pageAction			= null;
		    var lo_frm					= null;
		    var url 					= '<%=servURL%>/EnjoyGenericSrv';
		    
		    try{
		    	
		    	params 	= $('#frm').serialize() + "&pageAction=new";
				$.ajax({
					async:false,
		            type: "POST",
		            url: url,
		            data: params,
		            beforeSend: "",
		            success: function(data){
		            	window.location.replace('<%=servURL%>/pages/motor/AddressDemoScn.jsp');
		            }
		        });
		    	
		    }catch(e){
		    	alert("btnReset :: " + e);
		    }
		    
		});
		
		$('#btnSubmit').click(function(){
		    var lo_pageAction			= null;
		    var lo_frm					= null;
		    var url 					= '<%=servURL%>/EnjoyGenericSrv';
		    var la_idName               = new Array("province", "district", "subdistrict");
		    var la_msg               	= new Array("จังหวัด", "อำเภอ", "ตำบล");
	        var lo_obj;
		    
		    try{
		    	
		    	for(var i=0;i<la_idName.length;i++){
		            lo_obj          = eval('document.getElementById("' + la_idName[i] + '")');
		            
		            if(gp_trim(lo_obj.value)==""){
		            	alert("กรุณาระบุ " + la_msg[i]);
		                return false;
		            }
		            
		        }
		    	
		    	params 	= $('#frm').serialize() + "&pageAction=save";
				$.ajax({
					async:false,
		            type: "POST",
		            url: url,
		            data: params,
		            beforeSend: "",
		            success: function(data){
		            	var jsonObj 			= null;
		            	var status				= null;
		            	var provinceId			= null;
		            	var districtId			= null;
		            	var subdistrictId		= null;
		            	var errMsg				= null;
		            	
		            	try{
		            		jsonObj = JSON.parse(data);
		            		status	= jsonObj.status;
		            		//alert(status);
		            		if(status=="SUCCESS"){
		            			provinceId		= jsonObj.provinceId;
		            			districtId		= jsonObj.districtId;
		            			subdistrictId	= jsonObj.subdistrictId;
		            			
		            			alert("provinceId::" + provinceId + ", districtId::" + districtId + ", subdistrictId::" + subdistrictId);
		            		}else{
		            			errMsg = jsonObj.errMsg;
		            			
		            			alert(errMsg);
		            		}
		            	}catch(e){
		            		alert("in btnSubmit :: " + e);
		            	}
		            }
		        });
		    	
		    }catch(e){
		    	alert("btnSubmit :: " + e);
		    }
		    
		});
		
		$( "#province" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: "<%=servURL%>/EnjoyGenericSrv",
	                dataType: "json",
	                data: "pageAction=p&service=servlet.AddressDemoServlet&province=" + gp_trim(request.term),//request,
	                success: function( data, textStatus, jqXHR) {
	                    var items = data;
	                    response(items);
	                },
	                error: function(jqXHR, textStatus, errorThrown){
	                     alert( textStatus);
	                }
	            });
	          },
		      minLength: 0,//กี่ตัวอักษรถึงทำงาน
		      open: function() {
					//Data return กลับมาแล้วทำไรต่อ
		      },
		      close: function() {

		      },
		      focus:function(event,ui) {

		      },
		      select: function( event, ui ) {
		    	//เมื่อเลือก Data แล้ว
		      }
		});
		
		$( "#district" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: "<%=servURL%>/EnjoyGenericSrv",
	                dataType: "json",
	                data: "pageAction=d&service=servlet.AddressDemoServlet&province=" + gp_trim($( "#province" ).val()) + "&district=" + gp_trim(request.term),//request,
	                success: function( data, textStatus, jqXHR) {
	                    var items = data;
	                    response(items);
	                },
	                error: function(jqXHR, textStatus, errorThrown){
	                     alert( textStatus);
	                }
	            });
	          },
		      minLength: 0,//กี่ตัวอักษรถึงทำงาน
		      open: function() {
					//Data return กลับมาแล้วทำไรต่อ
		      },
		      close: function() {

		      },
		      focus:function(event,ui) {

		      },
		      select: function( event, ui ) {
		    	//เมื่อเลือก Data แล้ว
		      }
		});
		
		$( "#subdistrict" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: "<%=servURL%>/EnjoyGenericSrv",
	                dataType: "json",
	                data: "pageAction=s&service=servlet.AddressDemoServlet&province=" + gp_trim($( "#province" ).val()) + "&district=" + gp_trim($( "#district" ).val()) + "&subdistrict=" + gp_trim(request.term),//request,
	                success: function( data, textStatus, jqXHR) {
	                    var items = data;
	                    response(items);
	                },
	                error: function(jqXHR, textStatus, errorThrown){
	                     alert( textStatus);
	                }
	            });
	          },
		      minLength: 0,//กี่ตัวอักษรถึงทำงาน
		      open: function() {
					//Data return กลับมาแล้วทำไรต่อ
		      },
		      close: function() {

		      },
		      focus:function(event,ui) {

		      },
		      select: function( event, ui ) {
		    	//เมื่อเลือก Data แล้ว
		      }
		});
		
	});
	
</script>
</head>
<body>
	<form id="frm" action="<%=servURL%>/EnjoyGenericSrv">
		<input type="hidden" id="service" name="service" value="servlet.AddressDemoServlet" />
		
		<section class="vbox">
			<section>
				<section class="hbox stretch">
					<section id="content">
						<section class="vbox">
							<section class="scrollable padder">
								<div class="alert alert-block alert-error fade in">
					            	<h4 class="alert-heading">ตัวอย่างตำบล อำเภอ จังวัด</h4>
					          	</div>
					          	<div class="row">
									<div class="col-sm-12">
										<section class="panel panel-default">
											<header class="panel-heading font-bold">ตัวอย่างตำบล อำเภอ จังวัด</header>
											<div class="panel-body">
												<span>จังหวัด : </span>
												<input id="province" name="province" value="<%=addressDemoForm.getProvince() %>" placeholder="จังหวัด" title="จังหวัด"><br/><br/>
												<span>อำเภอ : </span>
												<input id="district" name="district" value="<%=addressDemoForm.getDistrict() %>" placeholder="อำเภอ" title="อำเภอ"><br/><br/>
												<span>ตำบล : </span>
												<input id="subdistrict" name="subdistrict" value="<%=addressDemoForm.getSubdistrict() %>" placeholder="ตำบล" title="ตำบล">
											</div>
												<br/>
												<table border="0" cellpadding="0" cellspacing="5" class="table span12" style="width:95%;" >
													<tr align="center">
														<td>
															<input type="button" class="btn btn-danger" id="btnSubmit" name="btnPrint" value="บันทึก" />&nbsp;
															<input type="reset" class="btn btn-danger" id="btnReset" name="btnReset" value="เริ่มใหม่" />
														</td>
													</tr>
												</table>
										</section>
									</div>
								</div>
					          </section>
						</section>
					</section>
				</section>
			</section>
		</section>
	</form>
</body>
</html>