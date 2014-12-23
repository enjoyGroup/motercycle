<%@page import="th.go.motorcycles.app.enjoy.form.CustomerForm"%>
<%@ include file="/pages/include/enjoyInclude.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<title> Welcome </title>
<%@ page import="th.go.motorcycles.app.enjoy.bean.CustomerBean"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="customerForm" class="th.go.motorcycles.app.enjoy.form.CustomerForm" scope="session"/>
<%@ include file="../menu/inc_theme.jsp"%>

<script>
	$(document).ready(function(){
		
		$('#btnSearch').click(function(){ 
		    var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.CustomerSearchServlet'; 
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
		var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.CustomerSearchServlet';  
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
	 
	
	function lp_onclick_row(av_obj){
		//alert(av_obj.rowIndex);
		var table 				= document.getElementById("tb_result");
		var curRow 				= table.rows[av_obj.rowIndex];
		var cell 				= curRow.getElementsByTagName("td")[0]; 
		var cusCode 			= cell.firstChild.value; 
	    var pageAction			= "updateData"; 
	    
		try{   
			window.open("/motorcyclesWeb/pages/motor/customer_insert.jsp?pageAction="+ pageAction +"&cusCode="+ cusCode , "frame_main" );  
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
						<form class="form-horizontal"  id="from_search" action="<%=servURL%>/EnjoyGenericSrv?service=servlet.CustomerSearchServlet">
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
													    <label class="col-sm-10 control-label" style="text-align:right">เลขประจำตัวประชาชน :</label>
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
													<th  style="text-align: center;" width="50px;" ><B>add/delete</B></th>
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
														 <tr onclick="lp_onclick_row(this);" >
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
