<%@page import="th.go.motorcycles.app.enjoy.form.CustomerForm"%> 
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ include file="/pages/include/enjoyLoginInclude.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="customerForm" class="th.go.motorcycles.app.enjoy.form.CustomerForm" scope="session"/> 
<%@ page import="th.go.motorcycles.app.enjoy.bean.CustomerBean"%>
<%@ page import="java.util.*"%> 

<html> 
<head>
<title> เพิ่มรายละเอียดลูกค้า </title>  
<script language="JavaScript" type="text/JavaScript">
	$(document).ready(function(){
		
		$( "#provinceName" ).autocomplete({ 
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: "<%=servURL%>/EnjoyGenericSrv",
	                dataType: "json",
	                data: "pageAction=p&service=servlet.CustomerServlet&province=" + gp_trim(request.term),//request,
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
		
		$( "#districtName" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: "<%=servURL%>/EnjoyGenericSrv",
	                dataType: "json",
	                data: "pageAction=d&service=servlet.CustomerServlet&province=" + gp_trim($( "#provinceName" ).val()) + "&district=" + gp_trim(request.term),//request,
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
		
		$( "#subdistrictName" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: "<%=servURL%>/EnjoyGenericSrv",
	                dataType: "json",
	                data: "pageAction=s&service=servlet.CustomerServlet&province=" + gp_trim($( "#provinceName" ).val()) + "&district=" + gp_trim($( "#districtName" ).val()) + "&subdistrict=" + gp_trim(request.term),//request,
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
		 
		//Add new
		$('#custName').focus();
		$('#btnAdd').click(function(){ 
		    var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.CustomerServlet'; 
		    var pageAction			= "addRecord";
		    var newCusCode 			= "";
		    var lv_radio			= "";
		    var lv_params			= "";
			var flagUpdate          = false;
			
			if($('#custCode').val()!=""){
				flagUpdate          = true;
				pageAction          = "updateRecord";  
			}
			
			try{ 
				
				if($('#custName').val()=="" || $('#custSurname').val()=="" ||
			 	   $('#houseNumber').val()=="" || $('#subdistrictName').val()=="" ||
			 	   $('#districtName').val()=="" || $('#provinceName').val()==""){
					alert("please input require field !!");
					return;
				}
				
				if($('#idType1').is(':checked') === true) {
					lv_radio = "1";
				}else if($('#idType2').is(':checked') === true) {
					lv_radio = "2";
				}
		        //alert(lv_radio);		  
				lv_params 	= "service=" + $('#service').val()
				            + "&cusCode=" + gp_sanitizeURLString($('#custCode').val()); 
							+ "&custName=" + gp_sanitizeURLString($('#custName').val()) 
							+ "&custSurname=" + gp_sanitizeURLString($('#custSurname').val()) 
							+ "&houseNumber=" + gp_sanitizeURLString($('#houseNumber').val()) 
							+ "&mooNumber=" + gp_sanitizeURLString($('#mooNumber').val())
							+ "&soiName=" + gp_sanitizeURLString($('#soiName').val()) 
							+ "&streetName=" + gp_sanitizeURLString($('#streetName').val()) 
							+ "&subdistrictCode=" + gp_sanitizeURLString($('#subdistrictCode').val()) 
							+ "&districtCode=" + gp_sanitizeURLString($('#districtCode').val()) 
							+ "&provinceCode=" + gp_sanitizeURLString($('#provinceCode').val()) 
							+ "&idType=" + lv_radio 
							+ "&idNumber=" + gp_sanitizeURLString($('#idNumber').val()) 
							+ "&cusStatus=" + gp_sanitizeURLString($('#cusStatus').val()) 
							+ "&pageAction=" + pageAction; 
				$.ajax({
		            type: "POST",
		            async: false,
		            url: url,
		            data: lv_params,
		            beforeSend: "",
		            success: function(data){
		            	if(data.indexOf('OK') > -1){ 
		            		la_data		= data.split(":");
		            		newCusCode 	= la_data[1]; 
		            		if(flagUpdate){
		            		   alert("แก้ไขรายการเรียบร้อย  newCusCode ="+newCusCode); 
		            		}else{
		            		   alert("บันทึกรายการเรียบร้อย  newCusCode ="+newCusCode); 
		            		   $('#custCode').val(newCusCode);
		            		}
						}else{
							alert(data);
						}
		            }
		        });
			}catch(err){
				alert("btnAdd :: " + err);
			}
			
		});
		
	    $.getJSON( "ajax/test.json", function( data ) {
	    	 var items = [];
	         $.each( data, function( key, val ) {
	           items.push( "<li id='" + key + "'>" + val + "</li>" );
	         });
	        
	         console.log(items);
	    });
 
	});
	
	
    function lp_reset_page(){
    	 $('#custCode').val(""); 
		 $('#custName').val("");
		 $('#custSurname').val("");
		 $('#houseNumber').val("");
		 $('#mooNumber').val("");
		 $('#soiName').val("");
		 $('#streetName').val("");
		 $('#subdistrictName').val("");
		 $('#subdistrictCode').val("");
		 $('#districtName').val("");
		 $('#districtCode').val("");
		 $('#provinceName').val("");
		 $('#provinceCode').val("");
		 $('#idType').val("");
		 $('#idNumber').val("");
		 $('#cusStatus').val("");
	}
    

    function lp_onblur_province(){
    	if($('#provinceName').val()!="" ){  
    		document.getElementById("districtName").removeAttribute('disabled');
        }else{
        	document.getElementById("districtName").setAttribute('disabled', 'disabled'); 
   		 	$('#districtName').val("");
   		 	$('#districtCode').val("");
   		    $('#subdistrictName').val("");
   		 	$('#subdistrictCode').val("");
        }
    }
    
    function lp_onblur_district(){
        if($('#districtName').val()!=""){
        	document.getElementById("subdistrictName").removeAttribute('disabled');
        }else{
        	document.getElementById("subdistrictName").setAttribute('disabled', 'disabled'); 
   		    $('#subdistrictName').val("");
   		 	$('#subdistrictCode').val("");
        }
    }
 
    function lp_onblur_subdistrict(){
        if($('#subdistrictName').val()==""){
    		
        }
    }
    

    window.onload = function () {
	    	var stringURL    = window.document.URL.toString();  
	       // alert(stringURL);   
	        var action       = "";
	        var customerCode = "";
	        var parameterts  = stringURL.substring(1).split('&'); 
	        
	        if(parameterts.length==2){
	        	var page = parameterts[0].split('=');
	        	var pageAction = page[0].substring(1).split('?');
	        	
	        	if(pageAction[1]=="pageAction"){
	        		action = page[1];
	        	}
	        	 
		        var cusCode = parameterts[1].split('=');  
		        
		        if(cusCode[0]=="cusCode"){
		        	customerCode  = cusCode[1];
	        	} 
	        }else{
	        	lp_reset_page();
	        }
	         
	        if(action=="updateData"){
	            alert(action+" and "+customerCode);
		        var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.CustomerServlet'; 
			    var pageAction			= "findData"; 
			    var lv_params			= ""; 
				try{  
					
					lv_params 	= "service=" + $('#service').val() 
								+ "&cusCode="+customerCode
								+ "&pageAction=" + pageAction;
	
					$.ajax({
			            type: "POST", 
			            async: false,
			            url: url,
			            data: lv_params,
			            beforeSend: "",
			            success: function(data){
			            	if(data.indexOf('OK') > -1){   
			            		la_data		= data.split(":"); 
					          //  alert("get data ="+la_data);  
			            		 $('#custCode').val(la_data[1]);  
				           		 $('#cusStatus').val(la_data[2]);
				           		 $('#custName').val(la_data[3]);
				           		 $('#custSurname').val(la_data[4]);
				           		 $('#houseNumber').val(la_data[5]);
				           		 $('#mooNumber').val(la_data[6]);
				           		 $('#soiName').val(la_data[7]);
				           		 $('#streetName').val(la_data[8]); 
				           		 $('#subdistrictCode').val(la_data[9]); 
				           		 $('#subdistrictName').val(la_data[10]); 
				           		 $('#districtCode').val(la_data[11]);
				           		 $('#districtName').val(la_data[12]);
				           		 $('#provinceCode').val(la_data[13]); 
				           		 $('#provinceName').val(la_data[14]); 
				           		 $('#idNumber').val(la_data[15]);
				           		 if(la_data[16]=="1"){
				           		 	$('#idType1').prop('checked', true);
				           		 }else  if(la_data[16]=="2"){
				           			$('#idType2').prop('checked', true);
				           		 } 
			            	
							}else{
								alert(data);
							} 
			            }
			        });
				}catch(err){
					alert("updateData :: " + err);
				} 
    		}  
        }
    

 
</script>
	
<%@ include file="../menu/inc_theme.jsp"%>

</head>
<body> 
<form class="form-horizontal"  id="from_insert" action="<%=servURL%>/EnjoyGen ericSrv"> 
	<input type="hidden" id="service" name="service" value="servlet.CustomerServlet" />  
	<section class="vbox"> 
		<section>
			<section class="hbox stretch"> 
				<section id="content">
					<section class="vbox">
						<section class="scrollable padder">
						
						
							<div class="alert alert-block alert-error fade in">
				            	<h4 class="alert-heading">เพิ่มรายละเอียดลูกค้า</h4>
				          	</div>
							<div class="row">
								<div class="col-sm-12">
								     <section class="panel panel-default">
										<header class="panel-heading font-bold">รายละเอียดรหัสลูกค้า</header>
										<div class="panel-body">  
												<div class="form-group">
												    <label class="col-sm-2 control-label"  style="text-align:right" >รหัสลูกค้า <font color="red">*</font>:</label>
													<input type="text" id="custCode" name="custCode" disabled> 
													<input type="hidden" id="cusStatus" name="cusStatus" value="A" />
												</div>  
										</div>
									</section>
									<section class="panel panel-default">
										<header class="panel-heading font-bold">ข้อมูลลูกค้า</header> 
										<div class="panel-body"> 
											<div class="form-group"> 
											    <div class="col-sm-12">
													<div class="row">
													    <div class="col-md-1"style="width:120px;"></div> 
													    <div class="col-md-1"> 
													        <label class="col-sm-2 control-label" style="text-align:right" > 
															<input type="radio" name="idType" id="idType1" value="1" style="width:50px;" checked>บุคคลธรรมดา</label> 
														</div>
														 <div class="col-md-1">
													        <label class="col-sm-2 control-label" style="text-align:right"> 
															<input type="radio" name="idType" id="idType2" value="2" style="width:50px;">นิติบุคคล</label> 
														</div>
														 <div class="col-md-1"style="width:40px;"></div>  
														<label class="col-sm-1 control-label" style="text-align:right">เลขบัตรประชาชน</label> 
														<div class="col-md-2"> 
														    <input type="text" class="form-control" id="idNumber" name="idNumber" >
														</div> 
													</div>
												</div>  
												<div class="col-sm-12">
													<div class="row"> 
													    <label class="col-sm-2 control-label" style="text-align:right">ชื่อ <font color="red">*</font>:</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="custName" name="custName"> 
														</div>
														<label class="col-sm-2 control-label" style="text-align:right">นามสกุล <font color="red">*</font>:</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="custSurname" name="custSurname" >
														</div>
													</div>
												</div>
												<div class="col-sm-12">
													<div class="row"> 
													    <label class="col-sm-2 control-label" style="text-align:right">บ้านเลขที่ <font color="red">*</font>:</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="houseNumber" name="houseNumber" > 
														</div>
														<label class="col-sm-2 control-label" style="text-align:right">หมู่ที่ :</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="mooNumber" name="mooNumber" >
														</div>
													</div>
												</div>
												<div class="col-sm-12">
													<div class="row"> 
													    <label class="col-sm-2 control-label" style="text-align:right">ตรอก/ซอย :</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="soiName" name="soiName" > 
														</div>
														<label class="col-sm-2 control-label" style="text-align:right">ถนน :</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="streetName" name="streetName" >
														</div>
													</div>
												</div>
												<div class="col-sm-12">
													<div class="row">  
														<label class="col-sm-1 control-label" style="text-align:right">จังหวัด <font color="red">*</font>:</label>
														<div class="col-md-2">  
															<input class="form-control" id="provinceName" name="provinceName" value="<%=customerForm.getCustomerBean().getProvinceName()%>"  placeholder="จังหวัด" title="จังหวัด">
															<input type="hidden" id="provinceCode" name="provinceCode"  value="10" >
														</div> 
														<label class="col-sm-2 control-label" style="text-align:right">อำเภอ/เขต <font color="red">*</font>:</label>
														<div class="col-md-2"> 
															<input  class="form-control" id="districtName" name="districtName" value="<%=customerForm.getCustomerBean().getDistrictName()%>" placeholder="อำเภอ" title="อำเภอ"  disabled>
															<input type="hidden" id="districtCode" name="districtCode" value="100"  >
														</div>
													    <label class="col-sm-2 control-label" style="text-align:right">ตำบล/แขวง <font color="red">*</font>:</label>
														<div class="col-md-2"> 
															<input  class="form-control" id="subdistrictName" name="subdistrictName" placeholder="ตำบล" title="ตำบล"  value="<%=customerForm.getCustomerBean().getSubdistrictName()%>"  disabled> 
															<input type="hidden" id="subdistrictCode" name="subdistrictCode" value="122" > 
														</div>
													</div>
												</div>
											</div> 
										</div>
										<div class="form-group" align="center">	 
											<button class="btn btn-primary" id="btnAdd">บันทึก</button>   
											<button class="btn btn-primary" id="btnCancel" onclick="lp_reset_page();">เริ่มใหม่</button> 
										</div>  
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
