<%@page import="th.go.motorcycles.app.enjoy.bean.CustomerBean"%>
<%@page import="th.go.motorcycles.app.enjoy.form.CustomerForm"%> 
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ include file="/pages/include/enjoyInclude.jsp"%>
<%@ page import="java.util.*"%>  
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<jsp:useBean id="customerForm" class="th.go.motorcycles.app.enjoy.form.CustomerForm" scope="session"/>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
	CustomerBean 	customerBean 	= customerForm.getCustomerBean(); 
%>
<html> 
<head>
<title> เพิ่มรายละเอียดลูกค้า </title>  
<script language="JavaScript" type="text/JavaScript">

	var gv_url 	= '<%=servURL%>/EnjoyGenericSrv';
 
	
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
	    $('#btnAdd').click(function(){
	        var flagUpdate   = false;
		    try{ 
		        
		    	if($('#custName').val()=="" || $('#custSurname').val()=="" ||
					 	   $('#houseNumber').val()=="" || $('#subdistrictName').val()=="" ||
					 	   $('#districtName').val()=="" || $('#provinceName').val()==""){
							alert("please input require field !!");
							return;
				}
		    	
		    	params 	  =  $('#frm').serialize() + "&pageAction=addRecord"; 
		    	
				if($('#cusCode').val()!=""){
					params 	= $('#frm').serialize() + "&pageAction=updateRecord"+ "&cusCode=" +$( "#cusCode" ).val(); 
				    flagUpdate = true;
				}
				 
				$.ajax({
					async:false,
		            type: "POST",
		            url: gv_url,
		            data:"service=servlet.CustomerServlet&" +params, 
		            beforeSend: "",
		            success: function(data){
		            	var jsonObj 			= null;
		            	var status				= null;
		            	var newCusCode			= null;
		            	var errMsg				= null;
		               //alert(data);
		            	 try{
		            		jsonObj = JSON.parse(data);
		            		status	= jsonObj.status;
		        		
		            		if(status=="SUCCESS"){
		            			newCusCode		= jsonObj.cusCode; 
		            			if(flagUpdate){ 
			            			alert("แก้ไขรายการเรียบร้อย  ");  
				            		window.location.replace(gv_url + "?service=servlet.CustomerServlet&pageAction=findData&cusCode=" + newCusCode);
		            			}else
		            				alert("บันทึกรายการเรียบร้อย  ");
			            		   window.location.replace(gv_url + "?service=servlet.CustomerServlet&pageAction=findData&cusCode=" + newCusCode);
		            		}else{
		            			errMsg = jsonObj.errMsg; 
		            			alert(errMsg);
		            		} 
		            	}catch(e){
		            		alert("in btnSave :: " + e);
		            	} 
		            	 
		            }
		        });
				 
		    }catch(e){
		    	alert("btnSubmit :: " + e);
		    }
		   
		});
	 
 
	});
	
	
    function lp_reset_page(){
    	var url					= '<%=servURL%>/EnjoyGenericSrv'; 
	    var pageAction			= "reset"; 
	    var lv_params			= ""; 
		try{  
			
			lv_params 	= "service=" + $('#service').val()  
			              +"&pageAction=" + pageAction;

			$.ajax({
	            type: "POST", 
	            async: false,
	            url: url,
	            data: lv_params,
	            beforeSend: "",
	            success: function(data){} 
		    });
		}catch(err){
			alert("reset :: " + err);
		}
		
		 
	}
      
</script>
	 
</head>
<body> 
<form class="form-horizontal" id="frm" action="<%=servURL%>/EnjoyGenericSrv">
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
													<input type="text" id="cusCode" name="cusCode" value="<%=customerBean.getCusCode()%>" disabled> 
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
															<input type="radio" name="idType" id="idType1" value="1" style="width:50px;" 
																	<%if(customerBean.getIdType().equals("1")){%> checked="checked" <%} %>>บุคคลธรรมดา</label> 
														</div>
														 <div class="col-md-1">
													        <label class="col-sm-2 control-label" style="text-align:right"> 
															<input type="radio" name="idType" id="idType2" value="2" style="width:50px;"  
																	<%if(customerBean.getIdType().equals("2")){%> checked="checked" <%} %>>นิติบุคคล</label> 
														</div>
														 <div class="col-md-1"style="width:40px;"></div>  
														<label class="col-sm-1 control-label" style="text-align:right">เลขบัตรประชาชน</label> 
														<div class="col-md-2"> 
														    <input type="text" class="form-control" id="idNumber" name="idNumber" value="<%=customerBean.getIdNumber()%>" >
														</div> 
													</div>
												</div>  
												<div class="col-sm-12">
													<div class="row"> 
													    <label class="col-sm-2 control-label" style="text-align:right">ชื่อ <font color="red">*</font>:</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="custName" name="custName" value="<%=customerBean.getCustName()%>"> 
														</div>
														<label class="col-sm-2 control-label" style="text-align:right">นามสกุล <font color="red">*</font>:</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="custSurname" name="custSurname" value="<%=customerBean.getCustSurname()%>"  >
														</div>
													</div>
												</div>
												<div class="col-sm-12">
													<div class="row"> 
													    <label class="col-sm-2 control-label" style="text-align:right">บ้านเลขที่ <font color="red">*</font>:</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="houseNumber" name="houseNumber" value="<%=customerBean.getHouseNumber()%>" > 
														</div>
														<label class="col-sm-2 control-label" style="text-align:right">หมู่ที่ :</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="mooNumber" name="mooNumber" value="<%=customerBean.getMooNumber()%>" >
														</div>
													</div>
												</div>
												<div class="col-sm-12">
													<div class="row"> 
													    <label class="col-sm-2 control-label" style="text-align:right">ตรอก/ซอย :</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="soiName" name="soiName"  value="<%=customerBean.getSoiName()%>" > 
														</div>
														<label class="col-sm-2 control-label" style="text-align:right">ถนน :</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="streetName" name="streetName"  value="<%=customerBean.getStreetName()%>"  >
														</div>
													</div>
												</div>
												<div class="col-sm-12">
													<div class="row">  
														<label class="col-sm-1 control-label" style="text-align:right">จังหวัด <font color="red">*</font>:</label>
														<div class="col-md-2">  
															<input class="form-control" id="provinceName" name="provinceName"  placeholder="จังหวัด" title="จังหวัด" value="<%=customerBean.getProvinceName()%>" >
															<input type="hidden" id="provinceCode" name="provinceCode" >
														</div> 
														<label class="col-sm-2 control-label" style="text-align:right">อำเภอ/เขต <font color="red">*</font>:</label>
														<div class="col-md-2"> 
															<input  class="form-control" id="districtName" name="districtName"  placeholder="อำเภอ" title="อำเภอ"  value="<%=customerBean.getDistrictName()%>">
															<input type="hidden" id="districtCode" name="districtCode" >
														</div>
													    <label class="col-sm-2 control-label" style="text-align:right">ตำบล/แขวง <font color="red">*</font>:</label>
														<div class="col-md-2"> 
															<input  class="form-control" id="subdistrictName" name="subdistrictName"  placeholder="ตำบล" title="ตำบล"  value="<%=customerBean.getSubdistrictName()%>"> 
															<input type="hidden" id="subdistrictCode" name="subdistrictCode"> 
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
