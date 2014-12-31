<%@ include file="/pages/include/enjoyInclude.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page import="th.go.motorcycles.app.enjoy.form.MotorDetailForm, th.go.motorcycles.app.enjoy.bean.MotorDetailBean"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="motorDetailForm" class="th.go.motorcycles.app.enjoy.form.MotorDetailForm" scope="session"/>  

<% 
	MotorDetailBean 	motorDetailBean 	= motorDetailForm.getMotorDetailBean();
%>
<!DOCTYPE html>
<html>

<head>
<title>ข้อมูลรถ</title>
<script language="JavaScript" type="text/JavaScript">
	var gv_service 		= null;
	var gv_url 			= '<%=servURL%>/EnjoyGenericSrv?service=servlet.MotorDetailServlet'; 
	
	$(document).ready(function(){
		
		gv_service = "service=" + $('#service').val();
		
		$('#btnSearch').click(function(){ 
		    var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.MotorDetailServlet'; 
		    var pageAction			= "searchData";
		    var lv_params			= ""; 
		    var brandSearch       	= gp_sanitizeURLString($('#brandSearch').val());  
		    var companySearch       = gp_sanitizeURLString($('#companySearch').val());  
			
		    if( brandSearch == "" ){
		    	alert("กรุณาระบุยี่ห้อก่อนทำการค้นหา");
		    	return false;
		    }
			try{
				brandSearch = brandSearch.trim(); 
				companySearch = companySearch.trim(); 
				
				lv_params 	= "service=" + $('#service').val()  
				            + "&brandSearch=" + gp_sanitizeURLString($('#brandSearch').val()) 
				            + "&companySearch=" + gp_sanitizeURLString($('#companySearch').val())  
							+ "&pageAction=" + pageAction;

				$.ajax({
		            type: "POST",
		            async: false,
		            url: url,
		            data: lv_params,
		            beforeSend: "",
		            success: function(data){
		            	if(data.indexOf('OK') > -1){ 
		            	}else{}
		            }
		        });
			}catch(err){
				alert("btnSearch :: " + err);
			}
			
		});
		
		$('#btnCancel').click(function(){ 
		    var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.MotorDetailServlet'; 
		    var pageAction			= "new";
		    var lv_params			= "";
			
			try{  
				lv_params 	= "service=" + $('#service').val()   
							+ "&pageAction=" + pageAction;
 
				$.ajax({
		            type: "POST",
		            async: false,
		            url: url,
		            data: lv_params,
		            beforeSend: "",
		            success: function(data){  
		       		  $('#brandSearch').val(""); 
		       		  $('#companSearch').val(""); 
		            }
		        });
			}catch(err){
				alert("btnCancel :: " + err);
			}
			
		}); 
		
		$( "#brandSearch" ).autocomplete({
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
		
		$( "#modelSearch" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: gv_url,
	                dataType: "json",
	                data: gv_service + "&pageAction=getModel&brandName="+gp_trim($("#brandSearch").val())+"&model=" + gp_trim(request.term),//request,
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
		
		$( "#companySearch" ).autocomplete({
			source: function(request, response) {
		           $.ajax({
		           	async:false,
			            type: "POST",
		               url: gv_url,
		               dataType: "json",
		               data: gv_service + "&pageAction=getCompany&branchName=" + gp_trim(request.term),//request,
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
	});
	
	function lp_del_row_table(ao_obj){  
		var lv_index			= 0;
		var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.MotorDetailServlet';  
	    var pageAction			= "delRecord";
	    var lv_params			= ""; 
		var lo_tabResultDtl		= document.getElementById("tb_result");
        var cusCode				= "";
 
		try{
			if(confirm("Please confirm to delete this record !!")){ 
				//lv_index	= gp_rowTableIndex(ao_obj);  
				//cusCode     = lo_tabResultDtl.rows[lv_index].cells[0].firstChild.value;
				cusCode     = ao_obj; 
				lv_params 	= "service=" + $('#service').val() 
				+ "&cusCode=" +cusCode  
				+ "&pageAction=" + pageAction;
	
				$.ajax({
				    type: "POST",
				    async: false,
				    url: url,
				    data: lv_params,
				    beforeSend: "",
				    success: function(data){
				    	if(data.indexOf('OK') > -1){
				    		lo_tabResultDtl.deleteRow(lv_index); 
				    		var length = lo_tabResultDtl.rows.length-1;
				    		//alert(lo_tabResultDtl.rows.length);
				    		for(var i=0;i<length;i++){ 
								rowNumber = i+1;
								lo_tabResultDtl.rows[i+1].cells[0].innerHTML=rowNumber;
				    		}
				    		
						}else{
							alert(data);
						}
				    }
				});
			}
		}catch(err){
			alert("lp_del_row_table :: " + err);
		}
	}

</script>
</head>
<body> 
	<section class="vbox"> 
		<section>
			<section class="hbox stretch"> 
				<section id="content">
					<section class="vbox">
						<section class="scrollable padder">
						<form class="form-horizontal"  id="from_search" action="<%=servURL%>/EnjoyGenericSrv?service=servlet.MotorDetailServlet">
						<input type="hidden" id="service" name="service" value="servlet.MotorDetailServlet" />
						<input type="hidden" id="cusStatus" name="cusStatus" />
					 
							<div class="alert alert-block alert-error fade in">
				            	<h4 class="alert-heading">ข้อมูลรถ</h4>
				          	</div>
							<div class="row"> 
								    <div class="col-sm-12">
										<section class="panel panel-default trace">
											<header class="panel-heading font-bold">เงื่อนไขการค้นหา</header>
											<div class="panel-body">
												 <div class="form-group"> 
													<div class="col-sm-12">
														<div class="row"> 
														    <label class="col-sm-10 control-label" style="text-align:right">ยี่ห้อ :</label>
															<div class="col-md-2"> 
																<input type="text" id="brandSearch" name="brandSearch"  value="<%=motorDetailBean.getBrandSearch() %>"/> 
															</div>
															<label class="col-sm-1 control-label" style="text-align:right">บริษัทที่เก็บ :</label>
															<div class="col-md-2">  
																<input type="text" id="companySearch" name="companySearch" value="<%=motorDetailBean.getCompanySearch() %>">
															</div> 
														 	<button class="btn btn-primary" id="btnSearch" >ค้นหา</button> 
													        <button class="btn btn-primary" id="btnCancel" >เริ่มใหม่</button>  
													</div> 
												</div>
											</div>
										</div>
									</section>
									<section class="panel panel-default">
										<header class="panel-heading font-bold">ข้อมูลรุ่นรถจักรยานยนต์</header>
										<div class="panel-body" id="div_result">
											<table id="tb_result" border="1" class="table span12" style="overflow-y:auto;width:450px;">
								               <tr bgcolor="#473636"  class="text_white" height="26px;">
													<th  style="text-align: center;" width="30px;" ><B>ลำดับ</B></th>
													<th  style="text-align: left;"   width="100px;"><B>ยี่ห้อ</B></th>
													<th  style="text-align: left;"   width="100px;"><B>รุ่น</B></th> 
													<th  style="text-align: left;"   width="50px;"><B>เลขตัวถัง</B></th>
													<th  style="text-align: left;"   width="50px;"><B>เลขเครื่องยนต์</B></th>  
													<th  style="text-align: left;"   width="50px;"><B>ซีซี</B></th>
													<th  style="text-align: left;"   width="50px;"><B>บริษัทที่เก็บ</B></th>
													<th  style="text-align: center;" width="50px;" ><B>add/delete</B></th>
												</tr> 
											 
											     <%
													List<MotorDetailBean>  	list        =   motorDetailForm.getListMotorDetail();
											     	MotorDetailBean 		bean 		=   null;
													int rowNumber                   	=   0;
													 
													if(list.size()>0){
														for(int i=0;i<list.size();i++){
															bean = list.get(i);
															rowNumber = i+1; 
														
														%>
														 <tr onclick="lp_onclick_row(this);" >
															<td width="15px;" align="center"><input type="hidden" name="hidMotorcyclesCode" id="hidMotorcyclesCode"  value="<%=bean.getMotorcyclesCode()%>"/><B><%=rowNumber%></B></td>
															<td width="100px;" align="left" ><input type="hidden" name="hidBrandCode" id="hidBrandCode"  value="<%=bean.getBrandCode()%>"/><input type="text" name="brandName" id="brandName" value="<%=bean.getBrandName()%>" style="width: 100px;" /></td>
															<td width="100px;" align="left"><input type="text" name="model" id="model" value="<%=bean.getModel()%> " style="width: 100px;"/></td>
															<td width="50px;" align="left"><input type="text" name="chassis" id="chassis" value="<%=bean.getChassis()%> " style="width: 100px;"/></td>
															<td width="50px;" align="left"><input type="text" name="engineNo" id="engineNo" value="<%=bean.getEngineNo()%> " style="width: 100px;"/></td>
															<td width="50px;" align="left"><input type="text" name="size" id="size" value="<%=bean.getSize()%> " style="width: 100px;"/></td>
															<td width="50px;" align="left"><input type="hidden" name="hidCompanyId" id="hidCompanyId" value="<%=bean.getCompanyId()%> " style="width: 100px;"/><input type="text" name="companyName" id="companyName" value="<%=bean.getCompanyName()%> " style="width: 100px;"/></td>
															<td width="50px" align="center">
															   <button id="btn_delete" name="btn_delete"  class="btn btn-warning btn-mini fa fa-times" style="width:25px;" onclick="lp_del_row_table(<%=bean.getMotorcyclesCode()%>);"></button>
															</td>
														</tr> 
														<% } }else{ %>
														  <tr height="26px;"><td colspan="4"><b>ไม่พบข้อมูลที่ระบุ</b></td></tr>
														<% } 
												%>  
											</table> 
										</div> 
								    </section>
								</div>
							</div> 
							
							</form>
						</section>
					</section>
				</section> 
			</section> 
		</section>
	</section>
</body>
</html>
