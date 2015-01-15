<%@ include file="/pages/include/enjoyInclude.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page import="th.go.motorcycles.app.enjoy.form.EntrySaleDetailForm, th.go.motorcycles.app.enjoy.bean.CustomerBean, th.go.motorcycles.app.enjoy.bean.CustomerBean, th.go.motorcycles.app.enjoy.bean.ProductBean"%>
<%@ page import="java.util.*"%>
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
		
		gv_service 	= "service=" + $('#service').val();
		var d 		= new Date();
	    var toDay 	= d.getDate() + '/' + (d.getMonth() + 1) + '/' + (d.getFullYear() + 543);
		
		try{
			$(".dateFormat").datepicker({ changeMonth: true, changeYear: true,dateFormat: 'dd/mm/yy', isBuddhist: true, defaultDate: toDay,dayNames: ['อาทิตย์','จันทร์','อังคาร','พุธ','พฤหัสบดี','ศุกร์','เสาร์'],
	            dayNamesMin: ['อา.','จ.','อ.','พ.','พฤ.','ศ.','ส.'],
	            monthNames: ['มกราคม','กุมภาพันธ์','มีนาคม','เมษายน','พฤษภาคม','มิถุนายน','กรกฎาคม','สิงหาคม','กันยายน','ตุลาคม','พฤศจิกายน','ธันวาคม'],
	            monthNamesShort: ['ม.ค.','ก.พ.','มี.ค.','เม.ย.','พ.ค.','มิ.ย.','ก.ค.','ส.ค.','ก.ย.','ต.ค.','พ.ย.','ธ.ค.']});
		}catch(e){
			alert(e);
		}
		
		$('.input-disabled').live( "keypress", function(e) {
			
			try{
				if( e.which != 9 ) {
			        return false;
			    }
			}catch(e){
				alert("onkeypress :: " + e);
			}
		});
		
		$('.input-disabled').live( "keydown", function(e) {
			
			try{
				if( e.which != 9 ) {
			        return false;
			    }
			}catch(e){
				alert("onkeydown :: " + e);
			}
		});
		
		
		$('#btnAddDate').on('click',function(){
			var lv_invoiceId = null;
			var lv_userLevel = null;
			
			try{
				lv_invoiceId 		= gp_trim($("#invoiceId").val());
	    		lv_userLevel 		= gp_trim($("#userLevel").val());
	    		
	    		if(lv_invoiceId!="" && parseInt(lv_userLevel) < 9){
	    			return;
	    		}
				
				$('#recordAddDate').focus();
			}catch(e){
				alert("btnAddDate :: " + e);
			}
		});
		
		$('#btnPrint').click(function(){
		    try{
				lv_invoiceId 		= gp_trim($("#invoiceId").val());
		    	if (lv_invoiceId != "") {
			    	document.getElementById("pageActionPDF").value 	= "pdf";
					document.getElementById("frm").target 			= "_blank";
				    document.getElementById("frm").submit();
		    	} else {
					alert("กรุณาบันทึกรายการ ก่อนทำการพิมพ์");		    		
		    	}
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
		
		/*$( "#cusCode" ).autocomplete({
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
		});*/
		
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
		
		$( "#idNumber" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: gv_url,
	                dataType: "json",
	                data: gv_service + "&pageAction=getIdNumber&idNumber=" + gp_trim(request.term),
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
		    	  lp_getCustDtlByIdNumber();
		      }
		});
		
		$('#btnReset').click(function(){
		    
		    try{
				$.ajax({
					async:false,
		            type: "POST",
		            url: gv_url,
		            data: gv_service + "&pageAction=reset",
		            beforeSend: "",
		            success: function(data){
		            	window.location.replace('/motorcyclesWeb/pages/motor/EntrySaleDetailScn.jsp');
		            }
		        });
		    	
		    }catch(e){
		    	alert("btnReset :: " + e);
		    }
		    
		});
		
	$('#btnSave').click(function(){
		    
		    try{
		    	if(!lp_validate()){
		    		return;
		    	}
				$.ajax({
					async:false,
		            type: "POST",
		            url: gv_url,
		            data: gv_service + "&pageAction=save&" + $('#frm').serialize(),
		            beforeSend: "",
		            success: function(data){
		            	var jsonObj 			= null;
		            	var status				= null;
		            	var invoiceId			= null;
		            	var errMsg				= null;
		            	
		            	try{
		            		jsonObj = JSON.parse(data);
		            		status	= jsonObj.status;
		            		
		            		if(status=="SUCCESS"){
		            			invoiceId		= jsonObj.invoiceId;
		            			
		            			alert("บันทึกเรียบร้อย");
		            			window.location.replace(gv_url + "?service=servlet.EntrySaleDetailServlet&pageAction=edit&invoiceId=" + invoiceId);
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
		    	alert("btnSave :: " + e);
		    }
		    
		});
	
		$('#btnNext').click(function(){
		    
			var lv_invoiceId = null;
			
		    try{
		    	lv_invoiceId = gp_trim($("#invoiceId").val());
		    	
		    	if(lv_invoiceId==""){
		    		alert("บันทึกข้อมูลก่อนดูรายการถัดไป");
		    		return;
		    	}
		    	
				$.ajax({
					async:false,
		            type: "POST",
		            url: gv_url,
		            data: gv_service + "&pageAction=getNextInvoice&invoiceId=" + lv_invoiceId,
		            beforeSend: "",
		            success: function(data){
		            	var jsonObj 			= null;
		            	var status				= null;
		            	var nextInvoiceId		= null;
		            	var errMsg				= null;
		            	
		            	try{
		            		jsonObj = JSON.parse(data);
		            		status	= jsonObj.status;
		            		
		            		if(status=="SUCCESS"){
		            			nextInvoiceId		= jsonObj.invoiceId;
		            			
		            			if(nextInvoiceId==""){
		            				window.location.replace(gv_url + "?service=servlet.EntrySaleDetailServlet&pageAction=new");
		            			}else{
		            				window.location.replace(gv_url + "?service=servlet.EntrySaleDetailServlet&pageAction=edit&invoiceId=" + nextInvoiceId);
		            			}
		            		}else{
		            			errMsg = jsonObj.errMsg;
		            			
		            			alert(errMsg);
		            		}
		            	}catch(e){
		            		alert("in btnNext :: " + e);
		            	}
		            }
		        });
		    	
		    }catch(e){
		    	alert("btnNext :: " + e);
		    }
		    
		});
		
	$('#btnPrev').click(function(){
		    
			var lv_invoiceId = null;
			
		    try{
		    	lv_invoiceId = gp_trim($("#invoiceId").val());
		    	
		    	if(lv_invoiceId==""){
		    		alert("บันทึกข้อมูลก่อนดูรายการก่อนหน้า");
		    		return;
		    	}
		    	
				$.ajax({
					async:false,
		            type: "POST",
		            url: gv_url,
		            data: gv_service + "&pageAction=getPreviousInvoice&invoiceId=" + lv_invoiceId,
		            beforeSend: "",
		            success: function(data){
		            	var jsonObj 			= null;
		            	var status				= null;
		            	var nextInvoiceId		= null;
		            	var errMsg				= null;
		            	
		            	try{
		            		jsonObj = JSON.parse(data);
		            		status	= jsonObj.status;
		            		//alert(status);
		            		if(status=="SUCCESS"){
		            			nextInvoiceId		= jsonObj.invoiceId;
		            			//alert(nextInvoiceId);
		            			if(nextInvoiceId==""){
		            				window.location.replace(gv_url + "?service=servlet.EntrySaleDetailServlet&pageAction=new");
		            			}else{
		            				window.location.replace(gv_url + "?service=servlet.EntrySaleDetailServlet&pageAction=edit&invoiceId=" + nextInvoiceId);
		            			}
		            		}else{
		            			errMsg = jsonObj.errMsg;
		            			
		            			alert(errMsg);
		            		}
		            	}catch(e){
		            		alert("in btnPrev :: " + e);
		            	}
		            }
		        });
		    	
		    }catch(e){
		    	alert("btnPrev :: " + e);
		    }
		    
		});
		
	});
	
	function lp_checkRecordAddDate(){
		
		var lo_recordAddDate 	= null;
		
		try{
			lo_recordAddDate 	= document.getElementById("recordAddDate");
			
			//สำหรับเช็ค Format วันที่ต้องเป็น dd/mm/yyyy(พ.ศ.) เท่านั้น
			if(!gp_checkDate(lo_recordAddDate))return;
			
		}catch(e){
			alert("lp_checkRecordAddDate :: " + e);
		}
	}
	
	function lp_validate(){
		var la_idName               = new Array("custName", "custSurname", "idNumber", "houseNumber", "provinceName", "districtName", "subdistrictName", "brandName", "model", "chassisDisp", "engineNoDisp", "size", "color", "totalAmount", "vatAmount", "recordAddDate");
	    var la_msg               	= new Array("ชื่อ"	  , "นามสกุล"	 , "เลขที่บัตรประชาชนหรือเลขผู้เสียภาษี", "บ้านเลขที่", "จังหวัด", "อำเภอ", "ตำบล", "ยี่ห้อ", "รุ่น", "เลขตัวถัง", "เลขเครื่องยนต์", "ซีซี", "สี", "รวมสุทธิ", "ภาษี", "วันที่บันทึก");
	    var lo_flagAddSales			= null;
	    var lo_commAmount			= null;
	    var lv_return				= true;
	    
		try{
			
			lo_flagAddSales 	= document.getElementById("flagAddSales");
			lo_commAmount 		= document.getElementById("commAmount");
			
			for(var i=0;i<la_idName.length;i++){
	            lo_obj          = eval('document.getElementById("' + la_idName[i] + '")');
	            
	            if(la_idName[i]=="totalAmount" || la_idName[i]=="vatAmount"){
	            	if(gp_trim(lo_obj.value)=="0.00"){
		            	alert("กรุณาระบุ " + la_msg[i]);
		                return false;
		            }
	            }
	            //สามารถกรอก 0 ได้ตามคำบอกคุณนุ้ย 15/01/2015
	            /*else if(la_idName[i]=="size"){
	            	if(gp_trim(lo_obj.value)=="0"){
		            	alert("กรุณาระบุ " + la_msg[i]);
		                return false;
		            }
	            }*/
	            else{
		            if(gp_trim(lo_obj.value)==""){
		            	alert("กรุณาระบุ " + la_msg[i]);
		                return false;
		            }
	            }
	        }
			
			
			if(lo_flagAddSales.checked==true && lo_commAmount.value=="0.00"){
				alert("กรุณาระบุจำนวนส่งเสริมการขาย");
				return false;
			}
			
			$.ajax({
				async:false,
	            type: "POST",
	            url: gv_url,
	            data: gv_service + "&pageAction=validate&" + $('#frm').serialize(),
	            beforeSend: "",
	            success: function(data){
	            	var jsonObj 			= null;
	            	var status				= null;
	            	var errMsg				= null;
	            	var errType				= null;
	            	
	            	try{
	            		jsonObj = JSON.parse(data);
	            		status	= jsonObj.status;
	            		
	            		if(status=="SUCCESS"){
	            			
	            			$("#provinceId").val(jsonObj.provinceId);
	            			$("#districtId").val(jsonObj.districtId);
	            			$("#subdistrictId").val(jsonObj.subdistrictId);
	            			$("#motorcyclesCode").val(jsonObj.motorcyclesCode);
	            			
	            			lv_return = true;
	            		}else{
	            			errMsg 	= jsonObj.errMsg;
	            			errType = jsonObj.errType;
	            			
	            			if(errType=="E"){
	            				alert(errMsg);
	            				lv_return = false;
	            			}else{
	            				$("#provinceId").val(jsonObj.provinceId);
		            			$("#districtId").val(jsonObj.districtId);
		            			$("#subdistrictId").val(jsonObj.subdistrictId);
		            			$("#motorcyclesCode").val(jsonObj.motorcyclesCode);
	            				lv_return = confirm(errMsg);
	            			}
	            		}
	            	}catch(e){
	            		alert("in btnSave :: " + e);
	            		lv_return = false;
	            	}
	            }
	        });
			
			return lv_return;
			
		}catch(e){
			alert("lp_validate :: " + e);
			return false;
		}
		
		return true;
	}
	
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
	            			$("#postcode").val(jsonObj.postcode);
	            			$("#buildingName").val(jsonObj.buildingName);
	            			
	            		}else{
	            			$("#cusCode").val("");
	            			/*$("#custName").val("");
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
	            			$("#cusStatus").val("");*/
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
	            			$("#postcode").val(jsonObj.postcode);
	            			$("#buildingName").val(jsonObj.buildingName);
	            			
	            		}else{
	            			$("#cusCode").val("");
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
	
	function lp_getCustDtlByIdNumber(){
		
		var lv_idNumber 		= null;
		
		try{
			lv_idNumber 		= gp_trim($("#idNumber").val());
			if(lv_idNumber==""){
				return;
			}
			
			params 	= gv_service + "&pageAction=getCustDtlByIdNumber&idNumber=" + lv_idNumber;
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
	            			$("#postcode").val(jsonObj.postcode);
	            			$("#buildingName").val(jsonObj.buildingName);
	            		}else{
	            			$("#cusCode").val("");
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
				/*$("#brandName").val("");
				$("#chassis").val("");
    			$("#engineNo").val("");
    			$("#size").val("");*/
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
	            			
	            		}
	            		/*else{
	            			$("#brandName").val("");
	            			$("#chassis").val("");
	            			$("#engineNo").val("");
	            			$("#size").val("");
	            		}*/
	            	}catch(e){
	            		alert("in lp_getProdDtl :: " + e);
	            	}
	            }
	        });
			
		}catch(e){
			alert("lp_getProdDtl :: " + e);
		}
	}
	
	function lp_onBlurVatAmount(){
		
		var lo_vatAmount 		= null;
		
		try{
			lo_vatAmount 			= document.getElementById("vatAmount");
			
			if(gp_trim(lo_vatAmount.value)==""){
				lo_vatAmount.value = "0.00";
			}
			
			if(gp_format(lo_vatAmount, 2)==false){
				alert("กรุณาระบุตัวเลขเท่านั้น");
				lo_vatAmount.value = "0.00";
				return;
			}
			
			if(gp_replaceComma(lo_vatAmount.value).length > 15){
				alert("ระบุได้สูงสุด 14 ตัวอักษร");
				$('#vatAmount').focus().select();
				return;
			}
			
		}catch(e){
			alert("lp_onBlurVatAmount :: " + e);
		}
		
	}
	
	function lp_onBlurTotalAmount(){
		
		var lo_totalAmount 		= null;
		
		try{
			lo_totalAmount 			= document.getElementById("totalAmount");
			
			if(gp_trim(lo_totalAmount.value)==""){
				lo_totalAmount.value = "0.00";
			}
			
			if(gp_format(lo_totalAmount, 2)==false){
				alert("กรุณาระบุตัวเลขเท่านั้น");
				lo_totalAmount.value = "0.00";
				return;
			}
			
			if(gp_replaceComma(lo_totalAmount.value).length > 15){
				alert("ระบุได้สูงสุด 14 ตัวอักษร");
				$('#totalAmount').focus().select();
				return;
			}
			
			lp_calVatAmount();
			
		}catch(e){
			alert("lp_onBlurTotalAmount :: " + e);
		}
		
	}
	
	function lp_onBlurCommAmount(){
		
		var lo_commAmount 		= null;
		
		try{
			lo_commAmount 			= document.getElementById("commAmount");
			
			if(gp_trim(lo_commAmount.value)==""){
				lo_commAmount.value = "0.00";
			}
			
			if(gp_format(lo_commAmount, 2)==false){
				alert("กรุณาระบุตัวเลขเท่านั้น");
				lo_commAmount.value = "0.00";
				return;
			}
			
			if(gp_replaceComma(lo_commAmount.value).length > 15){
				alert("ระบุได้สูงสุด 14 ตัวอักษร");
				$('#commAmount').focus().select();
				return;
			}
			
		}catch(e){
			alert("lp_onBlurCommAmount :: " + e);
		}
		
	}
	
	function lp_onBlurCreditAmount(){
		
		var lo_creditAmount 		= null;
		
		try{
			lo_creditAmount 			= document.getElementById("creditAmount");
			
			if(gp_trim(lo_creditAmount.value)==""){
				lo_creditAmount.value = "0.00";
			}
			
			if(gp_format(lo_creditAmount, 2)==false){
				alert("กรุณาระบุตัวเลขเท่านั้น");
				lo_creditAmount.value = "0.00";
				return;
			}
			
			if(gp_replaceComma(lo_creditAmount.value).length > 15){
				alert("ระบุได้สูงสุด 14 ตัวอักษร");
				$('#creditAmount').focus().select();
				return;
			}
			
		}catch(e){
			alert("lp_onBlurCreditAmount :: " + e);
		}
		
	}
	
	function lp_size(){
		
		var lo_size 		= null;
		
		try{
			lo_size 			= document.getElementById("size");
			
			if(gp_trim(lo_size.value)==""){
				lo_size.value = "0";
			}
			
			if(gp_format(lo_size, 0)==false){
				alert("กรุณาระบุตัวเลขเท่านั้น");
				lo_size.value = "0";
				return;
			}
			
			if(gp_replaceComma(lo_size.value).length > 4){
				alert("ระบุได้สูงสุด 4 ตัวอักษร");
				$('#size').focus().select();
				return;
			}
			
		}catch(e){
			alert("lp_size :: " + e);
		}
		
	}
	
	/*function lp_calVatAmount(){
		
		var lo_vatAmount 	= null;
		var lo_totalAmount	= null;
		var lv_priceAmount	= "0.00";
		var lv_vatAmount 	= "0.00";
		var lv_totalAmount	= "0.00";
		var lv_vat			= null;
		
		try{
			lv_vat			= 100 + parseInt($("#vat").val());
			lo_vatAmount 	= document.getElementById("vatAmount");
			lo_totalAmount	= document.getElementById("totalAmount");
			lv_priceAmount	= gp_replaceComma(document.getElementById("priceAmount").value);
			
			lv_vatAmount = ((lv_priceAmount*7)/100).toString();
			
			lo_vatAmount.value = lv_vatAmount;
			gp_format(lo_vatAmount, 2);
			
			lv_totalAmount = (parseFloat(lv_priceAmount) + parseFloat(gp_replaceComma(lo_vatAmount.value))).toString();
			
			lo_totalAmount.value = lv_totalAmount;
			gp_format(lo_totalAmount, 2);
			
		}catch(e){
			alert("lp_calVatAmount :: " + e);
		}
		
	}*/
	
	function lp_calVatAmount(){
		
		var lo_vatAmount 	= null;
		var lo_totalAmount	= null;
		var lo_priceAmount	= null;
		var lv_priceAmount	= "0.00";
		var lv_vatAmount 	= "0.00";
		var lv_totalAmount	= "0.00";
		var lv_vat			= null;
		
		try{
			lv_vat			= 100 + parseInt($("#vat").val());
			lo_priceAmount	= document.getElementById("priceAmount");
			lo_vatAmount 	= document.getElementById("vatAmount");
			lo_totalAmount	= document.getElementById("totalAmount");
			//lv_priceAmount	= gp_replaceComma(document.getElementById("priceAmount").value);
			
			lv_totalAmount 	= parseFloat(gp_replaceComma(lo_totalAmount.value));
			lv_priceAmount	= lv_totalAmount * 100/(lv_vat);
			lv_vatAmount	= lv_totalAmount - lv_priceAmount;
			
			lo_priceAmount.value 	= lv_priceAmount;
			lo_vatAmount.value 		= lv_vatAmount;
			
			gp_format(lo_priceAmount, 2);
			gp_format(lo_vatAmount, 2);
			
		}catch(e){
			alert("lp_calVatAmount :: " + e);
		}
		
	}
	
	function lp_chkAddSales(){
		
		var lo_flagAddSales = null;
		var lo_commAmount	= null;
		
		try{
			lo_flagAddSales 	= document.getElementById("flagAddSales");
			lo_commAmount 		= document.getElementById("commAmount");
			
			if(lo_flagAddSales.checked==true){
				lo_commAmount.className 	= "";
				lo_commAmount.readOnly 		= false;
				//lo_commAmount.onkeypress	= function(){};
				//lo_commAmount.onkeydown		= function(){};
			}else{
				lo_commAmount.className 	= "input-disabled";
				lo_commAmount.readOnly 		= true;
				//lo_commAmount.onkeypress	= function(){return false;};
				//lo_commAmount.onkeydown		= function(){return false;};
				lo_commAmount.value 		= "0.00";
				
			}
			
			
		}catch(e){
			alert("lp_chkAddSales :: " + e);
		}
	}
	
	function lp_manageObligation(){
		
		var lv_invoiceId 		= null;
		var la_flagCredit		= null;
		var lo_creditAmount		= null;
		
		try{
			lv_invoiceId 		= gp_trim($("#invoiceId").val());
			lo_creditAmount		= document.getElementById("creditAmount");
			la_flagCredit		= document.getElementsByName("flagCredit");//ใบเพิ่มหนี้ : A, ใบลดหนี้ : C, ไม่มีเพิ่มเติม: N
			
			//Case new
			if(lv_invoiceId==""){
				for(var i=0;i<la_flagCredit.length;i++){
					la_flagCredit[i].disabled = true;
				}
				lo_creditAmount.className 	= "input-disabled";
				lo_creditAmount.readOnly 	= true;
				//lo_creditAmount.onkeypress	= function(){return false;};
				//lo_creditAmount.onkeydown	= function(){return false;};
				lo_creditAmount.value 		= "0.00";
			}else{
				for(var i=0;i<la_flagCredit.length;i++){
					la_flagCredit[i].disabled = false;
				}
				lo_creditAmount.className 	= "";
				lo_creditAmount.readOnly 	= false;
				//lo_creditAmount.onkeypress	= function(){};
				//lo_creditAmount.onkeydown	= function(){};
			}
			
		}catch(e){
			alert("lp_manageObligation :: " + e);
		}
	}
	
	function lp_controlCreditAmount(){
		
		var la_flagCredit		= null;
		var lo_creditAmount		= null;
		
		try{
			lo_creditAmount		= document.getElementById("creditAmount");
			la_flagCredit		= document.getElementsByName("flagCredit");//Flag สำหรับเก็บ A- ใบเพิ่มหนี้ , C- ใบลดหนี้, N- ไม่มีเพิ่มเติม
			
			//ใบเพิ่มหนี้ : A, ใบลดหนี้ : C
			if(la_flagCredit[0].checked==true || la_flagCredit[1].checked==true){
				lo_creditAmount.className 	= "";
				lo_creditAmount.readOnly 	= false;
				//lo_creditAmount.onkeypress	= function(){};
				//lo_creditAmount.onkeydown	= function(){};
			}else{
				lo_creditAmount.className 	= "input-disabled";
				lo_creditAmount.readOnly 	= true;
				//lo_creditAmount.onkeypress	= function(){return false;};
				//lo_creditAmount.onkeydown	= function(){return false;};
				lo_creditAmount.value 		= "0.00";
			}
			
		}catch(e){
			alert("lp_controlCreditAmount :: " + e);
		}
	}
	
	function lp_onBlurPostcode(){
		
		var lo_postcode 		= null;//replaceComma
		
		try{
			lo_postcode 			= document.getElementById("postcode");
			
			if(gp_number(lo_postcode)==false){
				alert("กรุณาระบุตัวเลขเท่านั้น");
				lo_postcode.value = "";
				return;
			}
			
			if(gp_trim(lo_postcode.value)!="" && gp_trim(lo_postcode.value).length < 5){
				alert("ระบุได้รหัสไปรษณ๊ย์ผิด");
				$('#postcode').focus().select();
				return;
			}
			
		}catch(e){
			alert("lp_onBlurPostcode :: " + e);
		}
		
	}
	
    window.onload = function () {
    	var lv_invoiceId 		= null;
    	var lv_userLevel		= null;
    	
    	try{
    		lv_invoiceId 		= gp_trim($("#invoiceId").val());
    		lv_userLevel 		= gp_trim($("#userLevel").val());
    		
    		if(lv_invoiceId!=""){
    			document.getElementById('flagAddSales').disabled = true;
    		}
    		
    		if(lv_invoiceId!="" && parseInt(lv_userLevel) < 9){
    			$("#frm :input").attr("disabled", true);
    			
    			$("#btnPrev").attr("disabled", false);
    			$("#btnPrint").attr("disabled", false);
    			$("#btnReset").attr("disabled", false);
    			$("#btnNext").attr("disabled", false);
    		}else{
    			lp_manageObligation();
    	    	lp_controlCreditAmount();
    		}
    		
    	}catch(e){
    		alert("onload :: " + e);
    	}
    }
    
</script>
	 
</head>
<body> 
<style>
	.trace,.trace *{outline:1px solid red}
	table tr td{ padding-top:8px !important;}
	.form-group .btn{ margin:0px 2px;}
</style>
<form class="form-horizontal" id="frm" action="<%=servURL%>/EnjoyGenericSrv">
	<input type="hidden" id="service" name="service" value="servlet.EntrySaleDetailServlet" />  
	<input type="hidden" id="pageActionPDF" name="pageActionPDF" /> 
	<input type="hidden" id="vat" name="vat" value="<%=entrySaleDetailForm.getVat() %>" /> 
	<input type="hidden" id="userLevel" name="userLevel" value="<%=entrySaleDetailForm.getUserLevel() %>" /> 
	<input type="hidden" id="provinceId" name="provinceId" value="" />
	<input type="hidden" id="districtId" name="districtId" value="" />
	<input type="hidden" id="subdistrictId" name="subdistrictId" value="" />
	<input type="hidden" id="motorcyclesCode" name="motorcyclesCode" value="" />
	<section class="vbox"> 
		<section>
			<section class="hbox stretch"> 
				<section id="content">
					<section class="vbox">
						<section class="scrollable padder">  
							<div class="alert alert-block alert-error fade in">
				            	<h4 class="alert-heading">บันทึกการขายรถ</h4>
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
																placeholder="DD/MM/YYYY"
																onblur="lp_checkRecordAddDate();"
																class="dateFormat"
															 /> 
														<i class="fa fa-fw fa-calendar" id="btnAddDate" style="cursor:pointer"></i>
													</td>
												</tr>
											</table> 
										</div>
									</section>
									<section class="panel panel-default ">
										<header class="panel-heading font-bold">ข้อมูลลูกค้า</header> 
										<div class="panel-body"> 
											<table border="0" width="100%" class='col-sm-12'>
												<tr>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">
														เลขผู้เสียภาษี<font color="red">*</font>:</label>
													</td>
													<td colspan="1">
														<input  type="text"
																class='col-sm-11 pull-left'
																id="idNumber" 
																name="idNumber"
																size="12"
																maxlength="13"
																onblur="lp_getCustDtlByIdNumber();"
																value="<%=customerBean.getIdNumber() %>"
														/>
													</td>
													<td colspan="4">
														<label class="col-sm-offset-1 col-sm-2 control-label" style="text-align:right">
															<input  type="radio" 
																	id="idType1" 
																	name="idType" 
																	value="1" 
																	<%if(customerBean.getIdType().equals("1")){%> checked="checked" <%} %> 
															/>
															บุคคลธรรมดา
														</label>
														<label class="col-sm-2 control-label" style="text-align:right">
															<input  type="radio" 
																	id="idType2" 
																	name="idType" 
																	value="2" 
																	<%if(customerBean.getIdType().equals("2")){%> checked="checked" <%} %> 
															/>
															นิติบุคคล
														</label>
													</td>
												</tr>
												<tr>
													<!-- 
													<td width="13%">
														<label class="col-sm-2 control-label" style="text-align:right">รหัสลูกค้า <font color="red">*</font>:</label>
													</td>
													<td width="20%">
														<input  type="text" 
																size="15"
																id="cusCode" 
																name="cusCode"
																onblur="lp_getCustDtl();"
																value=""
														/>
													</td>
													 -->
													<td width="13%">
														<label class="col-sm-2 control-label pull-right" style="text-align:right">ชื่อ<font color="red">*</font>:</label>
													</td>
													<td width="20%">
														<input  type="text" 
																	class='col-sm-11 pull-left'
																	size="15"
																	id="custName" 
																	name="custName"
																	onblur="lp_getCustDtlByName();"
																	value="<%=customerBean.getCustName() %>"
															/>
													</td>
													<td width="13%">
														<label class="col-sm-2 control-label pull-right" style="text-align:right">นามสกุล<font color="red">*</font>:</label>
													</td>
													<td width="20%" colspan="3">
														<input  type="text" 
																	class='col-sm-11 pull-left'
																	size="15"
																	id="custSurname" 
																	name="custSurname"
																	onblur="lp_getCustDtlByName();"
																	value="<%=customerBean.getCustSurname() %>"
															/>
														<input  type="hidden" 
																id="cusCode" 
																name="cusCode"
																value="<%=customerBean.getCusCode() %>"
														/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">อาคาร:</label>
													</td>
													<td colspan="5" >
														<input  type="text" 
																class='pull-left'
																style="width: 97%"
																size="15"
																id="buildingName" 
																name="buildingName"
																value="<%=customerBean.getBuildingName()%>"
														/>
													</td>													
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">บ้านเลขที่ <font color="red">*</font>:</label>
													</td>
													<td>
														<input  type="text"
																class='col-sm-11 pull-left'
																id="houseNumber" 
																name="houseNumber"
																size="5"
																value="<%=customerBean.getHouseNumber() %>"
														/>
													</td>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">หมู่ที่:</label>
													</td>
													<td colspan="3" align="left">
														<input  type="text"
																	class='col-sm-11 pull-left'
																	id="mooNumber" 
																	name="mooNumber"
																	size="5"
																	value="<%=customerBean.getMooNumber() %>"
															/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label pull-rigth" style="text-align:right">ตรอกซอย:</label>
													</td>
													<td>
														<input  type="text" 
																class='col-sm-11 pull-left'
																size="15"
																id="soiName" 
																name="soiName"
																value="<%=customerBean.getSoiName() %>"
														/>
													</td>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">ถนน:</label>
													</td>
													<td colspan="3">
														<input  type="text" 
																class='col-sm-11 pull-left'
																size="15"
																id="streetName" 
																name="streetName"
																value="<%=customerBean.getStreetName() %>"
														/>
													</td>
													
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">จังหวัด<font color="red">*</font>:</label>
													</td>
													<td>
														<input  type="text" 
																class='col-sm-11 pull-left'
																size="15"
																id="provinceName" 
																name="provinceName"
																placeholder="จังหวัด"
																value="<%=customerBean.getProvinceName() %>"
														/>
													</td>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">อำเภอ/เขต <font color="red">*</font>:</label>
													</td>
													<td>
														<input  type="text"
																class='col-sm-11 pull-left'
																id="districtName" 
																name="districtName"
																size="15"
																placeholder="อำเภอ"
																value="<%=customerBean.getDistrictName() %>"
														/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">ตำบล/แขวง <font color="red">*</font>:</label>
													</td>
													<td>
														<input  type="text"
																	class='col-sm-11 pull-left'
																	id="subdistrictName" 
																	name="subdistrictName"
																	size="15"
																	placeholder="ตำบล"
																	value="<%=customerBean.getSubdistrictName() %>"
															/>
													</td>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">รหัสไปรษณ๊ย์:</label>
													</td>
													<td colspan="5">
														<input  type="text" 
																class='col-sm-11 pull-left'
																size="7"
																id="postcode" 
																name="postcode"
																maxlength="5"
																placeholder="รหัสไปรษณ๊ย์"
																onblur="lp_onBlurPostcode();"
																value="<%=customerBean.getPostcode() %>"
														/>
													</td>
												</tr>
											</table>
										</div>
										<header class="panel-heading font-bold">ข้อมูลสินค้า</header> 
										<div class="panel-body"> 
											<table border="0" width="100%">
												<tr>
													<td width="10%">
														<label class="col-sm-2 control-label pull-right" style="text-align:right">ยี่ห้อ <font color="red">*</font>:</label>
													</td>
													<td width="40%">
														<input  type="text" 
																class='col-sm-12 pull-left'
																size="15"
																id="brandName" 
																name="brandName"
																value="<%=productBean.getBrandName() %>"
														/>
													</td>
													<td width="10%">
														<label class="col-sm-2 control-label pull-right" style="text-align:right">รุ่น<font color="red">*</font>:</label>
													</td>
													<td width="40%" align="left">
														<input  type="text" 
																class='col-sm-12 pull-left'
																size="15"
																id="model" 
																name="model"
																onblur="lp_getProdDtl();"
																value="<%=productBean.getModel() %>"
														/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label pull-rightd" style="text-align:right">เลขตัวถัง <font color="red">*</font>:</label>
													</td>
													<td>
														<input  type="text" 
																class='col-sm-7 pull-left'
																id="chassis" 
																name="chassis"
																value="<%=productBean.getChassis() %>"
																size="8"
																class="input-disabled" 
					                                       		readonly="readonly"		
					                                       		tabindex="-1"													
														/>
														<input  type="text" 
																class='col-sm-5 pull-left'
																size="3"
																id="chassisDisp" 
																name="chassisDisp"
																value="<%=productBean.getChassisDisp() %>"
																maxlength="10"
														/>
													</td>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">เลขเครื่องยนต์<font color="red">*</font>:</label>
													</td>
													<td align="left">
														<input  type="text" 
																class='col-sm-7 pull-left'
																id="engineNo" 
																name="engineNo"
																value="<%=productBean.getEngineNo() %>"
																size="8"
																class="input-disabled" 
					                                       		readonly="readonly"		
					                                       		tabindex="-1"
														/>
														<input  type="text" 
																class='col-sm-5 pull-left'
																size="3"
																id="engineNoDisp" 
																name="engineNoDisp"
																value="<%=productBean.getEngineNoDisp() %>"
																maxlength="10"
														/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">ซีซี <font color="red">*</font>:</label>
													</td>
													<td>
														<input  type="text" 
																class='col-sm-12 pull-left'
																size="15"
																id="size" 
																name="size"
																onblur="lp_size();"
																value="<%=productBean.getSize() %>"
														/>
													</td>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">สี <font color="red">*</font>:</label>
													</td>
													<td>
														<input  type="text" 
																class='col-sm-12 pull-left'
																size="15"
																id="color" 
																name="color"
																value="<%=entrySaleDetailForm.getColor() %>"
																maxlength="50"
														/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">
															รวมสุทธิ: <font color="red">*</font>
														</label>
													</td>
													<td colspan="3">
														<input  type="text" 
																class='col-sm-4 pull-left'
																size="15"
																id="totalAmount" 
																name="totalAmount"
																onblur="lp_onBlurTotalAmount();"
																value="<%=entrySaleDetailForm.getTotalAmount() %>"
														/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">
															<script>document.write("ภาษี <%=entrySaleDetailForm.getVat()%>%:");</script>
															 <font color="red">*</font>
														</label>
													</td>
													<td colspan="3" align="left">
														<input  type="text" 
																class='col-sm-4 pull-left'
																size="15"
																id="vatAmount" 
																name="vatAmount"
																onblur="lp_onBlurVatAmount();"
																value="<%=entrySaleDetailForm.getVatAmount() %>"
														/>
													</td>
												</tr>
												<tr>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">จำนวนเงินที่ขาย:</label>
													</td>
													<td>
														<input  type="text" 
																class='col-sm-12 pull-left'
																size="15"
																id="priceAmount" 
																name="priceAmount"
								                                class="input-disabled" 
								                                readonly="readonly" 
																value="<%=entrySaleDetailForm.getPriceAmount() %>"
														/>
													</td>
													<td>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">หมายเหต:</label>
													</td>
													<td align="left">
														<input  type="text" 
																class='col-sm-12 pull-left'
																size="15"
																id="remark" 
																name="remark"
																maxlength="100"
																value="<%=entrySaleDetailForm.getRemark() %>"
														/>
													</td>
												</tr>
												<tr>
													<td colspan="4" align="left" style="white-space:nowrap;">
														<label class="col-sm-2 control-label" style="text-align:right">
															<input  type="checkbox" 
																	id="flagAddSales" 
																	name="flagAddSales" 
																	onclick="lp_chkAddSales()"
																	<%if(entrySaleDetailForm.getFlagAddSales().equalsIgnoreCase("Y")){%> checked="checked" <%} %> 
																	value="Y" />
															มีการส่งเสริมการขาย
														</label>
														<input  type="text" 
																size="15"
																id="commAmount" 
																name="commAmount"
						                                        class="input-disabled" 
						                                        readonly="readonly" 
						                                        onblur="lp_onBlurCommAmount();"
																value="<%=entrySaleDetailForm.getCommAmount() %>"
														/>
														<label class="col-sm-2 control-label pull-right" style="text-align:right">
															หมายเหตุของส่งเสริมการขาย
														</label>
													</td>
												</tr>
												<tr>
													<td colspan="4" align="left">
														<label class="col-sm-2 control-label col-sm-offset-3" style="text-align:right;width:100px;">
															<input  type="radio" 
																	id="flagCredit1" 
																	name="flagCredit" 
																	onclick="lp_controlCreditAmount();"
																	<%if(entrySaleDetailForm.getFlagCredit().equalsIgnoreCase(EntrySaleDetailForm.FLAG_A)){%> checked="checked" <%} %> 
																	value="<%=EntrySaleDetailForm.FLAG_A %>" />
															ใบเพิ่มหนี้ 
														</label>
														<label class="col-sm-2 control-label" style="text-align:right;width:100px;">
															<input  type="radio" 
																	id="flagCredit2" 
																	name="flagCredit" 
																	onclick="lp_controlCreditAmount();"
																	<%if(entrySaleDetailForm.getFlagCredit().equalsIgnoreCase(EntrySaleDetailForm.FLAG_C)){%> checked="checked" <%} %> 
																	value="<%=EntrySaleDetailForm.FLAG_C %>" />
															ใบลดหนี้ 
														</label>
														<label class="col-sm-2 control-label" style="text-align:right;width:120px;">
															<input  type="radio" 
																	id="flagCredit3" 
																	name="flagCredit" 
																	onclick="lp_controlCreditAmount();"
																	<%if(entrySaleDetailForm.getFlagCredit().equalsIgnoreCase(EntrySaleDetailForm.FLAG_N)){%> checked="checked" <%} %> 
																	value="<%=EntrySaleDetailForm.FLAG_N %>" />
															ไม่มีเพิ่มเติม
														</label>
														<input  type="text" 
																size="15"
																id="creditAmount" 
																name="creditAmount"
						                                        class="input-disabled" 
						                                        readonly="readonly" 
						                                        onblur="lp_onBlurCreditAmount();"
																value="<%=entrySaleDetailForm.getCreditAmount() %>"
														/>
													</td>
												</tr>
												<tr>
													<td colspan="4" align="left">
														<label class="col-sm-2 control-label col-sm-offset-2" style="text-align:right">
															<b>พิมพ์แบบ</b>
														</label>
														<label class="col-sm-2 control-label" style="text-align:right">
															<input  type="radio" 
																	id="printType1" 
																	name="printType" 
																	<%if(entrySaleDetailForm.getPrintType().equalsIgnoreCase("1")){%> checked="checked" <%} %> 
																	value="1" />
															มีโครง
														</label>
														<label class="col-sm-2 control-label" style="text-align:right">
															<input  type="radio" 
																	id="printType2" 
																	name="printType" 
																	<%if(entrySaleDetailForm.getPrintType().equalsIgnoreCase("2")){%> checked="checked" <%} %> 
																	value="2" />
															ไม่มีโครง
														</label>
													</td>
												</tr>
											</table>
											<br/>
											<div class="form-group" align="center">	 
												<input type="button" class="btn " id="btnPrev" name="btnPrev" value="รายการก่อนหน้า" />  
												<input type="button" class="btn btn-success" id="btnSave" name="btnSave" value="บันทึก" />
												<input type="button" class="btn btn-primary" id="btnPrint" name="btnPrint" value="พิมพ์" />
												<input type="button" class="btn btn-danger" id="btnReset" name="btnReset" value="เริ่มใหม่" />
												<input type="button" class="btn " id="btnNext" name="btnNext" value="รายการถัดไป" />
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
