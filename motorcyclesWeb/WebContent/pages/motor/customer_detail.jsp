<%@page import="th.go.motorcycles.app.enjoy.form.CustomerForm"%>
<%@ page import="th.go.motorcycles.app.enjoy.bean.CustomerBean"%>
<%@ include file="/pages/include/enjoyInclude.jsp"%> 
<%@ page import="java.util.*"%>  
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<jsp:useBean id="customerForm" class="th.go.motorcycles.app.enjoy.form.CustomerForm" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title> Welcome </title>  
<style> 
	
	.rowSelect:hover{
	    background-color:FFCCFF;
	    /*opacity: .2; */
	    cursor: pointer;
	} 
	
</style> 
<script>
    var gv_url 			= '<%=servURL%>/EnjoyGenericSrv';
    var gv_service 		= null;
    
	$(document).ready(function(){
		gv_service = "service=" + $('#service').val();
		
		 $( "#idNumber" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: gv_url,
	                dataType: "json",
	                data: gv_service + "&pageAction=getIdNumber&idNumber=" + gp_trim(request.term),//request,
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
		
		$( "#fullName" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: gv_url,
	                dataType: "json",
	                data: gv_service + "&pageAction=getCustFullName&custFullName=" + gp_trim(request.term),//request,
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
		 
		$('#btnSearch').click(function(){ 
		    var url					= '<%=servURL%>/EnjoyGenericSrv'; 
		    var pageAction			= "searchData";
		    var lv_params			= ""; 
		    var fullname            = gp_sanitizeURLString($('#fullName').val());  
		        
			try{
				fullname = fullname.trim(); 
				
				lv_params 	= "service=" + $('#service').val()  
				            + "&idNumber=" + gp_sanitizeURLString($('#idNumber').val()) 
				            + "&fullName=" + gp_sanitizeURLString($('#fullName').val())  
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
		    var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.CustomerSearchServlet'; 
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
		       		  $('#cusCode').val(""); 
		       		  $('#fullName').val(""); 
		            }
		        });
			}catch(err){
				alert("btnCancel :: " + err);
			}
			
		}); 
	 	
	});
	
	function lp_del_row_table(ao_obj){  
		var lv_index			= 0;
		var url					= '<%=servURL%>/EnjoyGenericSrv';  
	    var pageAction			= "delRecord";
	    var lv_params			= ""; 
		var lo_tabResultDtl		= document.getElementById("tb_result");
        var cusCode				= "";
 
		try{
			if(confirm("ต้องการจะลบข้อมูล ?")){  
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
	 
	
	function lp_onclick_row(av_obj){
		try{    
			window.location.replace(gv_url + "?service=servlet.CustomerServlet&pageAction=findData&cusCode=" + av_obj);
		}catch(err){
			alert("btnSearch :: " + err);
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
						<form class="form-horizontal"  id="from_search" action="<%=servURL%>/EnjoyGenericSrv">
						<input type="hidden" id="service" name="service" value="servlet.CustomerSearchServlet" />
						<input type="hidden" id="cusStatus" name="cusStatus" />
					 
							<div class="alert alert-block alert-error fade in">
				            	<h4 class="alert-heading">ค้นหารายละเอียดลูกค้า</h4>
				          	</div>
							<div class="row"> 
								    <div class="col-sm-12">
									<section class="panel panel-default">
										<header class="panel-heading font-bold">เงื่อนไขการค้นหา</header>
										<div class="panel-body">
											 <div class="form-group"> 
												<div class="col-sm-12">
													<div class="row"> 
													    <label class="col-sm-10 control-label" style="text-align:right">เลขผู้เสียภาษี :</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="idNumber" name="idNumber"> 
														</div>
														<label class="col-sm-1 control-label" style="text-align:right">ชื่อ-นามสกุล:</label>
														<div class="col-md-2">  
															<input type="text" class="form-control" id="fullName" name="fullName">
														</div> 
													 	<button class="btn btn-primary" id="btnSearch" >ค้นหา</button> 
												        <button class="btn btn-primary" id="btnCancel" >เริ่มใหม่</button>  
												</div> 
											</div>
											</div>
										</div>
									</section>
									<section class="panel panel-default">
										<header class="panel-heading font-bold">ข้อมูลลูกค้า</header>
										<div class="panel-body" id="div_result">
											<table id="tb_result" border="1" class="table span12" style="overflow-y:auto;width:900px;">
								               <tr bgcolor="#473636"  class="text_white" height="26px;">
													<th  style="text-align: center;" width="30px;" ><B>ลำดับ</B></th>
													<th  style="text-align: left;"   width="20px;"><B>ชื่อ-นามสกุล</B></th>
													<th  style="text-align: left;"   width="100px;"><B>ที่อยู่</B></th> 
													<th  style="text-align: center;" width="50px;" ><B>delete</B></th>
												</tr> 
											 
											     <%
													List<CustomerBean>  list        =   customerForm.getListCustomer();
													CustomerBean 		bean 		=   null;
													int rowNumber                   =   0;
													 
													if(list.size()>0){
														for(int i=0;i<list.size();i++){
															bean = list.get(i);
															rowNumber = i+1; 
														
														%>
														 <tr class="rowSelect"  onclick="lp_onclick_row(<%=bean.getCusCode()%>);" >
															<td width="30px;" align="center"><input type="hidden" name="hidCusCode" id="hidCusCode"  value="<%=bean.getCusCode()%>"/><B><%=rowNumber%></B></td>
															<td width="100px;" align="left" ><%=bean.getCustName()%> <%=bean.getCustSurname()%></td>
															<td width="300px;" align="left"><%=bean.getAddress()%></td>
															<td width="50px" align="center">
															   <button id="btn_delete" name="btn_delete"  class="btn btn-warning btn-mini fa fa-times" style="width:25px;" onclick="lp_del_row_table(<%=bean.getCusCode()%>);"></button>
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
