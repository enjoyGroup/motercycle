<%@page import="th.go.motorcycles.app.enjoy.form.EntrySaleDetailForm"%> 
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ include file="/pages/include/enjoyInclude.jsp"%>
<%@ page import="th.go.motorcycles.app.enjoy.form.EntrySaleDetailForm, th.go.motorcycles.app.enjoy.bean.CustomerBean, th.go.motorcycles.app.enjoy.bean.CustomerBean, th.go.motorcycles.app.enjoy.bean.ProductBean"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<jsp:useBean id="entrySaleDetailForm" class="th.go.motorcycles.app.enjoy.form.EntrySaleDetailForm" scope="session"/>  

<% 
	CustomerBean 	customerBean 	= entrySaleDetailForm.getCustomerBean();
	ProductBean 	productBean 	= entrySaleDetailForm.getProductBean();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<title>บันทึกการขาย</title>
<style>
	.input-disabled{
	    background-color:#EBEBE4;
	    border:1px solid #ABADB3;
	    color:rgb(84, 84, 84);
	}
</style>  
<script language="JavaScript" type="text/JavaScript">
	
	var gv_service 		= null;
	var gv_url 			= '<%=servURL%>/EnjoyGenericSrv';
	
	$(document).ready(function(){
		
		gv_service = "service=" + $('#service').val();
		
		$('#btnPrint').click(function(){
		    try{
		    	document.getElementById("pageActionPDF").value 	= "pdf";
				document.getElementById("frm").target 			= "_blank";
			    document.getElementById("frm").submit();
		    }catch(e){
		    	alert("btnPrint :: " + e);
		    }
		    
		}); 
		
		$( "#provinceName" ).autocomplete({ 
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: gv_url,
	                dataType: "json",
	                data: gv_service + "&pageAction=p&provinceName=" + gp_trim(request.term),//request,
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
	                url: gv_url,
	                dataType: "json",
	                data: gv_service + "&pageAction=d&provinceName=" + gp_trim($( "#provinceName" ).val()) + "&districtName=" + gp_trim(request.term),//request,
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
	                url: gv_url,
	                dataType: "json",
	                data: gv_service + "&pageAction=s&provinceName=" + gp_trim($( "#provinceName" ).val()) + "&districtName=" + gp_trim($( "#districtName" ).val()) + "&subdistrictName=" + gp_trim(request.term),//request,
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
		
		$( "#brandName" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: gv_url,
	                dataType: "json",
	                data: gv_service + "&pageAction=getBrandName&brandName=" + gp_trim(request.term),//request,
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
		    	  //lp_getProdDtl();
		      }
		});
		
		$( "#model" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: gv_url,
	                dataType: "json",
	                data: gv_service + "&pageAction=getModel&brandName="+gp_trim($("#brandName").val())+"&model=" + gp_trim(request.term),//request,
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
		    	  lp_getProdDtl();
		      }
		});
		
		$( "#cusCode" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: gv_url,
	                dataType: "json",
	                data: gv_service + "&pageAction=getCustCode&cusCode=" + gp_trim(request.term),//request,
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
		    	  lp_getCustDtl();
		      }
		});
		
		$( "#custName" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: gv_url,
	                dataType: "json",
	                data: gv_service + "&pageAction=getCustName&custName=" + gp_trim(request.term),//request,
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
		    	  lp_getCustDtlByName();
		      }
		});
		
		$( "#custSurname" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: gv_url,
	                dataType: "json",
	                data: gv_service + "&pageAction=getCustSurName&custName="+$("#custName").val()+"&custSurname=" + gp_trim(request.term),//request,
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
		    	  lp_getCustDtlByName();
		      }
		});
		
	});
	
	function lp_getCustDtl(){
		
		var lv_cusCode = null;
		
		try{
			lv_cusCode = gp_trim($("#cusCode").val());
			
			if(lv_cusCode==""){
				return;
			}
			
			params 	= gv_service + "&pageAction=getCustDtl&cusCode=" + lv_cusCode;
			$.ajax({
				async:false,
	            type: "POST",
	            url: gv_url,
	            data: params,
	            beforeSend: "",
	            success: function(data){
	            	var jsonObj 			= null;
	            	var status				= null;
	            	
	            	try{
	            		jsonObj = JSON.parse(data);
	            		status	= jsonObj.status;
	            		
	            		if(status=="SUCCESS"){
	            			$("#cusCode").val(jsonObj.cusCode);
	            			$("#custName").val(jsonObj.custName);
	            			$("#custSurname").val(jsonObj.custSurname);
	            			$("#houseNumber").val(jsonObj.houseNumber);
	            			$("#mooNumber").val(jsonObj.mooNumber);
	            			$("#soiName").val(jsonObj.soiName);
	            			$("#streetName").val(jsonObj.streetName);
	            			$("#subdistrictName").val(jsonObj.subdistrictName);
	            			$("#districtName").val(jsonObj.districtName);
	            			$("#provinceName").val(jsonObj.provinceName);
	            			
	            			if(jsonObj.idType=="1"){
	            				$("#idType1").prop('checked', true);
	            			}else{
	            				$("#idType2").prop('checked', true);
	            			}
	            			
	            			$("#idNumber").val(jsonObj.idNumber);
	            			$("#cusStatus").val(jsonObj.cusStatus);
	            			
	            		}else{
	            			$("#cusCode").val("");
	            			$("#custName").val("");
	            			$("#custSurname").val("");
	            			$("#houseNumber").val("");
	            			$("#mooNumber").val("");
	            			$("#soiName").val("");
	            			$("#streetName").val("");
	            			$("#subdistrictName").val("");
	            			$("#districtName").val("");
	            			$("#provinceName").val("");
	            			$("#idType").val("");
	            			$("#idNumber").val("");
	            			$("#cusStatus").val("");
	            		}
	            	}catch(e){
	            		alert("in lp_getCustDtl :: " + e);
	            	}
	            }
	        });
			
		}catch(e){
			alert("lp_getCustDtl :: " + e);
		}
	}
	
	function lp_getCustDtlByName(){
		
		var lv_custName 		= null;
		var lv_custSurname 		= null;
		
		try{
			lv_custName 		= gp_trim($("#custName").val());
			lv_custSurname 		= gp_trim($("#custSurname").val());
			//alert(lv_custName + " " + lv_custSurname);
			if(lv_custName=="" || lv_custSurname==""){
				return;
			}
			
			params 	= gv_service + "&pageAction=getCustDtlByName&custName=" + lv_custName + "&custSurname=" + lv_custSurname;
			$.ajax({
				async:false,
	            type: "POST",
	            url: gv_url,
	            data: params,
	            beforeSend: "",
	            success: function(data){
	            	var jsonObj 			= null;
	            	var status				= null;
	            	
	            	try{
	            		jsonObj = JSON.parse(data);
	            		status	= jsonObj.status;
	            		//alert(status);
	            		if(status=="SUCCESS"){
	            			$("#cusCode").val(jsonObj.cusCode);
	            			$("#custName").val(jsonObj.custName);
	            			$("#custSurname").val(jsonObj.custSurname);
	            			$("#houseNumber").val(jsonObj.houseNumber);
	            			$("#mooNumber").val(jsonObj.mooNumber);
	            			$("#soiName").val(jsonObj.soiName);
	            			$("#streetName").val(jsonObj.streetName);
	            			$("#subdistrictName").val(jsonObj.subdistrictName);
	            			$("#districtName").val(jsonObj.districtName);
	            			$("#provinceName").val(jsonObj.provinceName);
	            			
	            			if(jsonObj.idType=="1"){
	            				$("#idType1").prop('checked', true);
	            			}else{
	            				$("#idType2").prop('checked', true);
	            			}
	            			
	            			$("#idNumber").val(jsonObj.idNumber);
	            			$("#cusStatus").val(jsonObj.cusStatus);
	            			
	            		}else{
	            			$("#cusCode").val("");
	            			$("#custName").val("");
	            			$("#custSurname").val("");
	            			$("#houseNumber").val("");
	            			$("#mooNumber").val("");
	            			$("#soiName").val("");
	            			$("#streetName").val("");
	            			$("#subdistrictName").val("");
	            			$("#districtName").val("");
	            			$("#provinceName").val("");
	            			$("#idType").val("");
	            			$("#idNumber").val("");
	            			$("#cusStatus").val("");
	            		}
	            	}catch(e){
	            		alert("in lp_getCustDtl :: " + e);
	            	}
	            }
	        });
			
		}catch(e){
			alert("lp_getCustDtl :: " + e);
		}
	}
	
	function lp_getProdDtl(){
		
		var lv_model 		= null;
		var lv_brandName 	= null;
		
		try{
			lv_model 		= gp_trim($("#model").val());
			lv_brandName 	= gp_trim($("#brandName").val());
			
			if(lv_model==""){
				$("#brandName").val("");
				$("#chassis").val("");
    			$("#engineNo").val("");
    			$("#size").val("");
				return;
			}
			
			params 	= gv_service + "&pageAction=getProdDtl&brandName=" + lv_brandName + "&model=" + lv_model;
			$.ajax({
				async:false,
	            type: "POST",
	            url: gv_url,
	            data: params,
	            beforeSend: "",
	            success: function(data){
	            	var jsonObj 			= null;
	            	var status				= null;
	            	
	            	try{
	            		jsonObj = JSON.parse(data);
	            		status	= jsonObj.status;
	            		
	            		if(status=="SUCCESS"){
	            			$("#brandName").val(jsonObj.brandName);
	            			$("#chassis").val(jsonObj.chassis);
	            			$("#engineNo").val(jsonObj.engineNo);
	            			$("#size").val(jsonObj.size);
	            			
	            		}else{
	            			$("#brandName").val("");
	            			$("#chassis").val("");
	            			$("#engineNo").val("");
	            			$("#size").val("");
	            		}
	            	}catch(e){
	            		alert("in lp_getProdDtl :: " + e);
	            	}
	            }
	        });
			
		}catch(e){
			alert("lp_getProdDtl :: " + e);
		}
	}

    window.onload = function () {
	    	
    }
    
</script>
	 
</head>
<body> 
<form class="form-horizontal" id="frm" action="<%=servURL%>/EnjoyGenericSrv">
	<input type="hidden" id="service" name="service" value="servlet.EntrySaleDetailServlet" />  
	<input type="hidden" id="pageActionPDF" name="pageActionPDF" />  
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
										<header class="panel-heading font-bold">รายละเอียดใบกำกับภาษี</header>
										<div class="panel-body">
											<table border="0" width="100%">
												<tr>
													<td width="20%">
														<label class="col-sm-2 control-label"  style="text-align:right" >เลขที่ใบกำกับ :</label>
													</td>
													<td width="30%">
														<input  type="text" 
															id="invoiceId" 
															name="invoiceId" 
															value="<%=entrySaleDetailForm.getInvoiceId() %>"
															onkeypress="return false;"
					                                        onkeydown="return false;"
					                                        class="input-disabled" 
					                                        readonly="readonly" />
													</td>
													<td width="20%">
														<label class="col-sm-2 control-label"  style="text-align:right" >วันที่บันทึก :</label>
													</td>
													<td width="30%">
														<input  type="text" 
															id="recordAddDate" 
															name="recordAddDate" 
															value="<%=entrySaleDetailForm.getRecordAddDate() %>"
															onkeypress="return false;"
					                                        onkeydown="return false;"
					                                        class="input-disabled" 
					                                        readonly="readonly" /> 
													</td>
												</tr>
											</table> 
										</div>
									</section>
									<section class="panel panel-default">
										<header class="panel-heading font-bold">ข้อมูลลูกค้า</header> 
										<div class="panel-body"> 
											<table border="0" width="100%">
												<tr>
													<td width="13%">
														<label class="col-sm-2 control-label" style="text-align:right">รหัสลูกค้า <font color="red">*</font>:</label>
													</td>
													<td width="20%">
														<input  type="text" 
																size="15"
																id="cusCode" 
																name="cusCode"
																onblur="lp_getCustDtl();"
																value="<%=customerBean.getCusCode() %>"
														/>
													</td>
													<td width="13%">
														<label class="col-sm-2 control-label" style="text-align:right">ชื่อ<font color="red">*</font>:</label>
													</td>
													<td width="20%">
														<input  type="text" 
																	size="20"
																	id="custName" 
																	name="custName"
																	onblur="lp_getCustDtlByName();"
																	value="<%=customerBean.getCustName() %>"
															/>
													</td>
													<td width="14%">
														<label class="col-sm-2 control-label" style="text-align:right">นามสกุล<font color="red">*</font>:</label>
													</td>
													<td width="20%">
														<input  type="text" 
																	size="20"
																	id="custSurname" 
																	name="custSurname"
																	onblur="lp_getCustDtlByName();"
																	value="<%=customerBean.getCustSurname() %>"
															/>
													</td>
												</tr>
												<tr>
													<td colspan="6">
														<label class="col-sm-2 control-label" style="text-align:right">
														<b>ข้อมูลผู้เสียภาษี</b> <font color="red">*</font>:</label>
													</td>
												</tr>
												<tr>
													<td colspan="6">
														<label class="col-sm-2 control-label" style="text-align:right">
															<input  type="radio" 
																	id="idType1" 
																	name="idType" 
																	value="1" 
																	<%if(customerBean.getIdType().equals("1")){%> checked="checked" <%} %> 
															/>
															เลขที่บัตรประชาชน
														</label>
														<label class="col-sm-2 control-label" style="text-align:right">
															<input  type="radio" 
																	id="idType2" 
																	name="idType" 
																	value="2" 
																	<%if(customerBean.getIdType().equals("2")){%> checked="checked" <%} %> 
															/>
															เลขที่ผู้เสียภาษี
														</label>
														<input  type="text"
																id="idNumber" 
																name="idNumber"
																size="50"
																maxlength="13"
																value="<%=customerBean.getIdNumber() %>"
														/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">บ้านเลขที่ <font color="red">*</font>:</label>
													</td>
													<td>
														<input  type="text"
																id="houseNumber" 
																name="houseNumber"
																size="20"
																value="<%=customerBean.getHouseNumber() %>"
														/>
													</td>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">หมู่ที่:</label>
													</td>
													<td colspan="3" align="left">
														<input  type="text"
																	id="mooNumber" 
																	name="mooNumber"
																	size="20"
																	value="<%=customerBean.getMooNumber() %>"
															/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">ตรอกซอย:</label>
													</td>
													<td>
														<input  type="text" 
																size="20"
																id="soiName" 
																name="soiName"
																value="<%=customerBean.getSoiName() %>"
														/>
													</td>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">ถนน:</label>
													</td>
													<td>
														<input  type="text" 
																size="20"
																id="streetName" 
																name="streetName"
																value="<%=customerBean.getStreetName() %>"
														/>
													</td>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">จังหวัด<font color="red">*</font>:</label>
													</td>
													<td>
														<input  type="text" 
																size="20"
																id="provinceName" 
																name="provinceName"
																value="<%=customerBean.getProvinceName() %>"
														/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">อำเภอ/เขต <font color="red">*</font>:</label>
													</td>
													<td>
														<input  type="text"
																id="districtName" 
																name="districtName"
																size="20"
																value="<%=customerBean.getDistrictName() %>"
														/>
													</td>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">ตำบล/แขวง <font color="red">*</font>:</label>
													</td>
													<td colspan="3" align="left">
														<input  type="text"
																	id="subdistrictName" 
																	name="subdistrictName"
																	size="20"
																	value="<%=customerBean.getSubdistrictName() %>"
															/>
													</td>
												</tr>
											</table>
										</div>
										<header class="panel-heading font-bold">ข้อมูลสินค้า</header> 
										<div class="panel-body"> 
											<table border="0" width="100%">
												<tr>
													<td width="13%">
														<label class="col-sm-2 control-label" style="text-align:right">ยี่ห้อ <font color="red">*</font>:</label>
													</td>
													<td width="20%">
														<input  type="text" 
																size="20"
																id="brandName" 
																name="brandName"
																value="<%=productBean.getBrandName() %>"
														/>
													</td>
													<td width="13%">
														<label class="col-sm-2 control-label" style="text-align:right">รุ่น<font color="red">*</font>:</label>
													</td>
													<td width="54%" align="left">
														<input  type="text" 
																size="20"
																id="model" 
																name="model"
																onblur="lp_getProdDtl();"
																value="<%=productBean.getModel() %>"
														/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">เลขตัวถัง <font color="red">*</font>:</label>
													</td>
													<td>
														<input  type="text" 
																id="chassis" 
																name="chassis"
																value="<%=productBean.getChassis() %>"
																size="3"
																class="input-disabled" 
					                                       		readonly="readonly"															
														/>
														<input  type="text" 
																size="10"
																id="chassis2" 
																name="chassis2"
														/>
													</td>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">เลขเครื่องยนต์<font color="red">*</font>:</label>
													</td>
													<td align="left">
														<input  type="text" 
																id="engineNo" 
																name="engineNo"
																value="<%=productBean.getEngineNo() %>"
																size="3"
																class="input-disabled" 
					                                       		readonly="readonly"															
														/>
														<input  type="text" 
																size="10"
																id="engineNo2" 
																name="engineNo2"
														/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">ซีซี <font color="red">*</font>:</label>
													</td>
													<td colspan="3">
														<input  type="text" 
																size="20"
																id="size" 
																name="size"
																value="<%=productBean.getSize() %>"
														/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">จำนวนเงินที่ขาย <font color="red">*</font>:</label>
													</td>
													<td>
														<input  type="text" 
																size="20"
																id="priceAmount" 
																name="priceAmount"
																value="<%=entrySaleDetailForm.getPriceAmount() %>"
														/>
													</td>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">ภาษี 7%:</label>
													</td>
													<td align="left">
														<input  type="text" 
																size="20"
																id="vatAmount" 
																name="vatAmount"
																onkeypress="return false;"
						                                        onkeydown="return false;"
						                                        class="input-disabled" 
						                                        readonly="readonly" 
																value="<%=entrySaleDetailForm.getVatAmount() %>"
														/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">รวมสุทธิ:</label>
													</td>
													<td>
														<input  type="text" 
																size="20"
																id="totalAmount" 
																name="totalAmount"
																onkeypress="return false;"
						                                        onkeydown="return false;"
						                                        class="input-disabled" 
						                                        readonly="readonly" 
																value="<%=entrySaleDetailForm.getTotalAmount() %>"
														/>
													</td>
													<td>
														<label class="col-sm-2 control-label" style="text-align:right">หมายเหต:</label>
													</td>
													<td align="left">
														<input  type="text" 
																size="20"
																id="remark" 
																name="remark"
																value="<%=entrySaleDetailForm.getRemark() %>"
														/>
													</td>
												</tr>
												<tr>
													<td colspan="4" align="left">
														<label class="col-sm-2 control-label" style="text-align:right">
															<input  type="checkbox" 
																	id="flagAddSales" 
																	name="flagAddSales" 
																	<%if(entrySaleDetailForm.getFlagAddSales().equalsIgnoreCase("Y")){%> checked="checked" <%} %> 
																	value="Y" />
															มีการส่งเสริมการขาย
														</label>
														<input  type="text" 
																size="20"
																id="commAmount" 
																name="commAmount"
																onkeypress="return false;"
						                                        onkeydown="return false;"
						                                        class="input-disabled" 
						                                        readonly="readonly" 
																value="<%=entrySaleDetailForm.getCommAmount() %>"
														/>
													</td>
												</tr>
												<tr>
													<td colspan="4" align="left">
														<label class="col-sm-2 control-label" style="text-align:right">
															<b>พิมพ์แบบ</b>
														</label>
														<label class="col-sm-2 control-label" style="text-align:right">
															<input  type="radio" 
																	id="printType1" 
																	name="printType" 
																	<%if(entrySaleDetailForm.getPrintType().equalsIgnoreCase("1")){%> checked="checked" <%} %> 
																	value="Y" />
															มีโครง
														</label>
														<label class="col-sm-2 control-label" style="text-align:right">
															<input  type="radio" 
																	id="printType2" 
																	name="printType" 
																	<%if(entrySaleDetailForm.getPrintType().equalsIgnoreCase("2")){%> checked="checked" <%} %> 
																	value="Y" />
															ไม่มีโครง
														</label>
													</td>
												</tr>
											</table>
											<br/>
											<div class="form-group" align="center">	 
												<input type="button" class="btn btn-primary" id="btnPrev" name="btnPrev" value="รายการก่อนหน้า" />  
												<input type="button" class="btn btn-primary" id="btnSave" name="btnSave" value="บันทึก" />
												<input type="button" class="btn btn-primary" id="btnPrint" name="btnPrint" value="พิมพ์" />
												<input type="button" class="btn btn-primary" id="btnReset" name="btnReset" value="เริ่มใหม่" />
												<input type="button" class="btn btn-primary" id="btnNext" name="btnNext" value="รายการถัดไป" />
											</div>  
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
