<%@ include file="/pages/include/enjoyInclude.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page import="th.go.motorcycles.app.enjoy.form.MotorDetailForm, th.go.motorcycles.app.enjoy.bean.MotorDetailBean"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="motorDetailForm" class="th.go.motorcycles.app.enjoy.form.MotorDetailForm" scope="session"/>  

<% 
  List<MotorDetailBean> motorDetailBeans    = motorDetailForm.getListMotorDetail();	
  MotorDetailBean 	    motorDetailBean 	= motorDetailForm.getMotorDetailBean();
%>
<!DOCTYPE html>
<html>

<head>
<title>ข้อมูลรถ</title>
<script language="JavaScript" type="text/JavaScript">
	var gv_service 		    = null;
	var gv_url 			    = null;  
	var motorArrCode  	    = null;
	var motorArrDelCode  	= null;
	var j                   = null;
	
	$(document).ready(function(){
		
		gv_url 			    = '<%=servURL%>/EnjoyGenericSrv?service=servlet.MotorDetailServlet'; 
		gv_service          = "service=" + $('#service').val(); 
		motorArrCode  	    = new Array();
		motorArrDelCode  	= new Array();
		j                   = 0;
		
		$('#btnSearch').click(function(){  
		    var pageAction			= "searchData";
		    var lv_params			= ""; 
		    var brandSearch       	= gp_sanitizeURLString($('#brandSearch').val());  
		    var companySearch       = gp_sanitizeURLString($('#companySearch').val());  
			var brandName           = null;
			var companyName         = null;
			
		    if(brandSearch == ""){
		    	alert("กรุณาระบุยี่ห้อก่อนทำการค้นหา");
		    	return false;
		    }
		    
		   /*  if(companySearch == ""){
		    	alert("กรุณาระบุบริษัทที่เก็บก่อนทำการค้นหา");
		    	return false;
		    }
		     */
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
		            url: gv_url,
		            data: lv_params,
		            beforeSend: "",
		            success: function(data){   
		            }
		        });
			}catch(err){
				alert("btnSearch :: " + err);
			}
			
			
		});
		
		$('#btnReset').click(function(){ 
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
		
		//****************************** AUTO Complete for Table ******************************//
		$(".brandNameClass").live("focus",function(){
			$(this).autocomplete({
				 
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
			      }
			  });
			
		});
		
	
		$(".branchNameClass").live("focus",function(){
			$(this).autocomplete({
		
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
				      }
			     });
			
		   });
  
		//****************************** AUTO Complete for Table ******************************//
		 
		$('#btnSave').click(function(){ 
			var pageAction			= "saveUpdData";
			var lv_params			= "";  
			 
	    	if(!lp_validate()){
	    		return;
	    	}

		    try{
		    	lv_params 	= "&pageAction=" + pageAction + "&" + $('#frm').serialize();
		    	
				$.ajax({
					async:false,
		            type: "POST",
		            url: gv_url,
		            data: lv_params,
		            beforeSend: "",
		            success: function(data){
		            	alert("บันทึกรายการเรียบร้อย  ");
		            	// $('#frm :input').attr("readonly", true); 
		            	//$('#btnSave').attr("readonly", false); 
		            	$('.hidMotorStartus').val("U");
		            	$('.brandNameClass').attr("readonly", true); 
		            	$('.branchNameClass').attr("readonly", true);             	 		            	 
		            }
		        });
		    	
		    }catch(e){
		    	alert("btnSubmit :: " + e);
		    }
		    
		});
		
		$('#btnCancel').click(function(){  
		    var pageAction			= "new";
		    var lv_params			= "";
			
			try{  
				lv_params 	= "pageAction=" + pageAction;
 
				$.ajax({
		            type: "POST",
		            async: false,
		            url: gv_url,
		            data: lv_params,
		            beforeSend: "",
		            success: function(data){}
		        });
			}catch(err){
				alert("btnCancel :: " + err);
			}
			
		}); 
	 	
	    
		
	});
	
	
	function lp_add_row_table(){
		var lo_table 	 = null;
		var lv_length 	 = null;
		var row 		 = null;
		var cell1 		 = null;
		var cell2 		 = null;
		var cell3 		 = null;	
		var brandSearch       	= gp_sanitizeURLString($('#brandSearch').val());  
	    var companySearch       = gp_sanitizeURLString($('#companySearch').val());  
	    var brandCode       	= gp_sanitizeURLString($('#brandCode').val());  
	    var companyId           = gp_sanitizeURLString($('#companyId').val());  
	//alert("brandCode::"+brandCode);	
	//alert("companyId::"+companyId);	
		try{
			lo_table 	= document.getElementById("tb_result");
			lv_length 	= lo_table.rows.length - 1;
			row 		= lo_table.insertRow(lv_length);
			cell1 		= row.insertCell(0);
			cell2 		= row.insertCell(1);
			cell3 		= row.insertCell(2);
			cell4 		= row.insertCell(3);
			cell5 		= row.insertCell(4);
			cell6 		= row.insertCell(5);
			cell7 		= row.insertCell(6);
			cell8 		= row.insertCell(7); 
		 
			
			cell1.align	= "center"; 
			cell8.align	= "center"; 
			cell1.innerHTML = "<td width='15px;' align='center'><input type='hidden' name='hidMotorcyclesCode' id='hidMotorcyclesCode' value='" + lv_length + "'/>"+"<b>" + lv_length + "<b>";
			cell2.innerHTML = "<td width='100px;' align='left' ><input type='hidden' name='hidBrandCode' id='hidBrandCode'  /><input type='text' name='brandName' id='brandName' class='brandNameClass' value='" + brandSearch + "' style='width: 100px;' /></td>";
			cell3.innerHTML = "<td width='100px;' align='left'><input type='text' name='model' id='model'  style='width: 100px;' maxlength='10'/></td>";	
			cell4.innerHTML = "<td width='100px;' align='left'><input type='text' name='chassis' id='chassis'   style='width: 100px;' maxlength='10'/></td>";
			cell5.innerHTML = "<td width='100px;' align='left'><input type='text' name='engineNo' id='engineNo'  style='width: 100px;'  maxlength='10'/></td>";
			cell6.innerHTML = "<td width='50px;'  align='left'><input type='text' name='size' id='size'   style='width: 100px;' maxlength='4' onblur='lp_onBlurFormatNumber(this);'/></td>";
			cell7.innerHTML = "<td width='100px;' align='left'><input type='hidden' name='hidCompanyId' id='hidCompanyId'  style='width: 100px;'/>" +
			                  "<input type='text' name='branchName' id='branchName' class='branchNameClass' value='" +companySearch+ "'  style='width: 150px;' /></td>";
			cell8.innerHTML = "<td width='50px' align='center'><button id='btn_delete'  name='btn_delete'  class='btn btn-warning btn-mini fa fa-times' style='width:25px;' onclick='lp_del_row_table(this);' ></button><input type='hidden' name='hidMotorStartus' id='hidMotorStartus' class='hidMotorStartus' value='N'/></td>";
			   
			
		}catch(e){
			alert("lp_add_row_table :: " + e);
		}
	}  
	
	function lp_onBlurFormatNumber(ao_obj){
        var lo_size 		    = null;
        var lv_size               = null;
        var lv_index			= 0;    
 		
		try{ 
			lv_index	= gp_rowTableIndex(ao_obj);
			lv_index    = lv_index-1; 
			lo_size     = document.getElementsByName("size");
			lv_size     = lo_size[lv_index].value;
			
			if(gp_trim(lv_size)==""){
				lv_size = "0.00";
			}
			
			if(gp_format(lo_size[lv_index], 0)==false){
				alert("กรุณาระบุตัวเลขเท่านั้น");
				lv_size = "0.00";
				return;
			}
			 
			
		}catch(e){
			alert("lp_onBlurFormatNumber :: " + e);
		}
	}
	
	function lp_validate(){
		var la_idName               = new Array("brandName", "model", "chassis", "engineNo", "size","branchName");
	    var la_msg               	= new Array("ยี่ห้อ", "รุ่น", "เลขตัวถัง", "เลขเครื่องยนต์", "ซีซี","บริษัทที่เก็บ");
	    var lo_flagAddSales			= null;
	    var lo_commAmount			= null;
	    var lo_obj                  = null;
	    var lv_max_length			= 0;
		try{
			 
			for(var i=0;i<la_idName.length;i++){
	            //lo_obj          = eval('document.getElementById("' + la_idName[i] + '")');
	            lo_obj          	= document.getElementsByName(la_idName[i]);
	    		lv_max_length 		= lo_obj.length;
	    		for (j=0;j<lv_max_length;j++)
	    		{
		            if(gp_trim(lo_obj[j].value)==""){
		            	//alert("กรุณาระบุ " + la_msg[i]);   
		            	alert("กรุณาระบุข้อมูลให้ครบถ้วนก่อนทำการบันทึก");
		                return false;
		            }
	    		}
	        }
		}catch(e){
			alert("lp_validate :: " + e);
			return false;
		}
		
		return true;
	}
	
	function lp_del_row_table(ao_obj){  
		var lv_index			= 0;   
		var lo_tabResultDtl		= document.getElementById("tb_result");
		var motorCode           = null; 
		var motorDelCode        = null; 

		try{ 
			if(confirm("ต้องการลบรายการนี็?")){   
				lv_index	= gp_rowTableIndex(ao_obj);
				lv_index=lv_index-1;
				//alert("lv_index::"+lv_index); 
				motorCode    = document.getElementsByName("hidMotorcyclesCode");
				motorDelCode = motorCode[lv_index].value;
				//alert("motorDelCode::"+ motorDelCode);	
				
				if(motorDelCode!=""){
					//motorArrDelCode[j]=motorCode; 
					//j++;
				  
				   lv_params = "service=" + $('#service').val() 
				              + "&motorCode=" +motorDelCode 
				              + "&brandSearch=" + gp_sanitizeURLString($('#brandSearch').val()) 
					          + "&companySearch=" + gp_sanitizeURLString($('#companySearch').val())  
				              + "&pageAction=delRecord";

					$.ajax({
					    type: "POST",
					    async: false,
					    url: gv_url,
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
						<form class="form-horizontal"  id="frm" action="<%=servURL%>/EnjoyGenericSrv">
						<input type="hidden" id="service" name="service" value="servlet.MotorDetailServlet" />
						 
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
														    <label class="col-sm-10 control-label" style="text-align:right">ยี่ห้อ<font color="red">*</font>:</label>
															<div class="col-md-2"> 
																<input type="text" id="brandSearch" name="brandSearch"  value="<%=motorDetailBean.getBrandSearch()%>" <%if(!motorDetailBean.getBrandSearch().equalsIgnoreCase("")){%> disabled="disabled" <%} %> />
																<input type="hidden" name="brandCode" id="brandCode"  value="<%=motorDetailBean.getBrandCode()%>"/> 
															</div>
															<label class="col-sm-1 control-label" style="text-align:right">บริษัทที่เก็บ:</label>
															<div class="col-md-2">  
																<input type="text" id="companySearch" name="companySearch" value="<%=motorDetailBean.getCompanySearch()%>" <%if((!motorDetailBean.getBrandSearch().equalsIgnoreCase(""))||(!motorDetailBean.getCompanySearch().equalsIgnoreCase(""))){%> disabled="disabled" <%} %> >
																<input type="hidden" name="companyId" id="companyId"  value="<%=motorDetailBean.getCompanyId()%>"/>
															</div> 
														 
														  <!--   <input type="button" class="btn btn-primary" id="btnSearch" name="btnSearch" value="ค้นหา"/>  -->  
														  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														    <button class="btn btn-primary" id="btnSearch" name="btnSearch">ค้นหา</button>
											                <button class="btn btn-primary" id="btnCancel" name="btnCancel">เริ่มใหม่</button>
													</div> 
												</div>
											</div>
										</div>
									</section>
									<section class="panel panel-default">
										<header class="panel-heading font-bold">ข้อมูลรุ่นรถจักรยานยนต์</header>
										<div class="panel-body" id="div_result">
											<table id="tb_result" border="1" class="table span12" style="overflow-y:auto;width:950px;">
								               <tr bgcolor="#473636"  class="text_white" height="26px;">
													<th  style="text-align: center;" width="30px;" ><B>ลำดับ</B></th>
													<th  style="text-align: left;"   width="100px;"><B>ยี่ห้อ</B></th>
													<th  style="text-align: left;"   width="100px;"><B>รุ่น</B></th> 
													<th  style="text-align: left;"   width="100px;"><B>เลขตัวถัง</B></th>
													<th  style="text-align: left;"   width="100px;"><B>เลขเครื่องยนต์</B></th>  
													<th  style="text-align: left;"   width="50px;"><B>ซีซี</B></th>
													<th  style="text-align: left;"   width="100px;"><B>บริษัทที่เก็บ</B></th> 
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
														 <tr>
															<td width="15px;" align="center"><input type="hidden" name="hidMotorcyclesCode" id="hidMotorcyclesCode"  value="<%=bean.getMotorcyclesCode()%>"/><B><%=rowNumber%></B></td>
															<td width="100px;" align="left" ><input type="hidden" name="hidBrandCode" id="hidBrandCode"  value="<%=bean.getBrandCode()%>"/>
															    <input type="text" name="brandName" id="brandName" class="brandNameClass"  value="<%=bean.getBrandName()%>" style="width: 100px;" readonly="readonly"/>
															</td>
															<td width="100px;" align="left"><input type="text" name="model" id="model" value="<%=bean.getModel()%> " style="width: 100px;" maxlength="10"/></td>
															<td width="100px;" align="left"><input type="text" name="chassis" id="chassis" value="<%=bean.getChassis()%> " style="width: 100px;"  maxlength="10"/></td>
															<td width="100px;" align="left"><input type="text" name="engineNo" id="engineNo" value="<%=bean.getEngineNo()%> " style="width: 100px;"  maxlength="10"/></td>
															<td width="50px;" align="left"><input type="text" name="size" id="size" value="<%=bean.getSize()%> " style="width: 100px;" maxlength="4"  onblur="lp_onBlurFormatNumber(this);"/></td>
															<td width="100px;" align="left"><input type="hidden" name="hidCompanyId" id="hidCompanyId" value="<%=bean.getCompanyId()%> " style="width: 100px;"/>
															    <input type="text" name="branchName" id="branchName" class="branchNameClass" value="<%=bean.getBranchName()%> "  style="width: 150px;" readonly="readonly"  />
															</td>
															<td width="50px" align="center">
															   <button id="btn_delete" name="btn_delete"  class="btn btn-warning btn-mini fa fa-times" style="width:25px;" onclick="lp_del_row_table(this);" ></button>
															   <input type="hidden" name="hidMotorStartus" id="hidMotorStartus"  value="U"/> 
															</td> 
														</tr> 
														
													<% } }else{ %>
														   <tr>
															<td width="15px;" align="center"><input type="hidden" name="hidMotorcyclesCode" id="hidMotorcyclesCode"  /><B>1</B></td>
															<td width="100px;" align="left" ><input type="hidden" name="hidBrandCode" id="hidBrandCode" value="<%=motorDetailBean.getBrandCode()%>"/>
															    <input type="text" name="brandName" id="brandName" class="brandNameClass"  value="<%=motorDetailBean.getBrandName()%>" style="width: 100px;" />
															</td>
															<td width="100px;" align="left"><input type="text" name="model" id="model" style="width: 100px;"  maxlength="10"/></td>
															<td width="100px;" align="left"><input type="text" name="chassis" id="chassis"   style="width: 100px;"  maxlength="10"/></td>
															<td width="100px;" align="left"><input type="text" name="engineNo" id="engineNo"   style="width: 100px;" maxlength="10"/></td>
															<td width="50px;" align="left"><input type="text" name="size" id="size"   style="width: 100px;" maxlength="4" onblur="lp_onBlurFormatNumber(this);"/></td>
															<td width="100px;" align="left"><input type="hidden" name="hidCompanyId" id="hidCompanyId"  value="<%=motorDetailBean.getCompanyId()%>"  style="width: 100px;"/>
															   <input type="text" name="branchName" id="branchName" class="branchNameClass"   value="<%=motorDetailBean.getBranchName()%>" style="width: 150px;"/>
															</td>
															<td width="50px" align="center">
															   <button id="btn_delete" name="btn_delete"  class="btn btn-warning btn-mini fa fa-times" style="width:25px;" onclick="lp_del_row_table(this);" ></button>
															   <input type="hidden" name="hidMotorStartus" id="hidMotorStartus"  class='hidMotorStartus'  value="N"/> 
															</td> 
														</tr>  
													<% } 
													 
												%> 
												
												  <tr>
													<td style="visibility:hidden;"></td>
													<td style="visibility:hidden;"></td>
													<td style="visibility:hidden;"></td>
													<td style="visibility:hidden;"></td>
													<td style="visibility:hidden;"></td>
													<td style="visibility:hidden;"></td>
													<td style="visibility:hidden;"></td> 
													<td align="center">
													  <a id="btn_add" href="#" class="btn btn-warning btn-mini fa fa-plus-square" style="width:25px;" onclick="lp_add_row_table();"></a>
													</td>
												 </tr>  
										 
											</table> 
										</div> 
								    </section>
								</div>
							</div> 
							<div class="form-group" align="center">	  
								<input type="button" class="btn btn-primary" id="btnSave" name="btnSave" value="บันทึก" /> 
								<button class="btn btn-primary" id="btnReset" name="btnReset">เริ่มใหม่</button>
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
