<%@page import="th.go.motorcycles.app.enjoy.form.BrandForm"%>
<%@ include file="/pages/include/enjoyInclude.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<title> Welcome </title>
<%@ page import="th.go.motorcycles.app.enjoy.bean.BrandBean"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="brandForm" class="th.go.motorcycles.app.enjoy.form.BrandForm" scope="session"/>
<%@ include file="../menu/inc_theme.jsp"%>

<script>
	$(document).ready(function(){
		
		$('#btnSearch').click(function(){ 
		    var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.BrandSearchServlet'; 
		    var pageAction			= "searchData";
		    var lv_params			= ""; 
		    var brandName            = gp_sanitizeURLString($('#brandName').val());  
		        
			try{
				brandName = brandName.trim(); 
				
				lv_params 	= "service=" + $('#service').val()  
				            + "&brandCode=" + gp_sanitizeURLString($('#brandCode').val()) 
				            + "&brandName=" + gp_sanitizeURLString($('#brandName').val())  
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
		    var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.BrandSearchServlet'; 
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
		       		  $('#brandCode').val(""); 
		       		  $('#brandName').val(""); 
		            }
		        });
			}catch(err){
				alert("btnCancel :: " + err);
			}
			
		}); 
	 	
	});
	
	function lp_del_row_table(ao_obj){  
		var lv_index			= 0;
		var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.BrandSearchServlet';  
	    var pageAction			= "delRecord";
	    var lv_params			= ""; 
		var lo_tabResultDtl		= document.getElementById("tb_result");
        var brandCode				= "";
 
		try{
			if(confirm("Please confirm to delete this record !!")){ 
				//lv_index	= gp_rowTableIndex(ao_obj);  
				//brandCode     = lo_tabResultDtl.rows[lv_index].cells[0].firstChild.value;
				brandCode     = ao_obj; 
				lv_params 	= "service=" + $('#service').val() 
				+ "&brandCode=" +brandCode  
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
						<form class="form-horizontal"  id="from_search" action="<%=servURL%>/EnjoyGenericSrv?service=servlet.BrandSearchServlet">
						<input type="hidden" id="service" name="service" value="servlet.BrandSearchServlet" />
						<input type="hidden" id="brandStatus" name="brandStatus" />
					 
							<div class="alert alert-block alert-error fade in">
				            	<h4 class="alert-heading">รายละเอียดยี่ห้อรถจักรยานยนต์</h4>
				          	</div>
							<div class="row"> 
								    <div class="col-sm-12">
									<section class="panel panel-default">
										<header class="panel-heading font-bold">ข้อมูลยี่ห้อรถจักรยานยนต์</header>
										<div class="panel-body" id="div_result">
											<table id="tb_result" border="1" class="table span12" style="overflow-y:auto;width:500px;">
								               <tr bgcolor="#473636"  class="text_white" height="26px;">
													<th  style="text-align: center;" width="30px;" ><B>ลำดับ</B></th>
													<th  style="text-align: left;"   width="400px;"><B>ยี่ห้อรถจักรยานยนต์</B></th>
													<th  style="text-align: center;" width="50px;" ><B>add/delete</B></th>
												</tr> 
											 
											     <%
													List<BrandBean>  list	=   brandForm.getListBrand();
													BrandBean 		bean	=   null;
													int rowNumber			=   0;
													 
													if(list.size()>0){
														for(int i=0;i<list.size();i++){
															bean = list.get(i);
															rowNumber = i+1; 
														
														%>
														 <tr onclick="lp_onclick_row(this);" >
															<td width="30px;" align="center"><input type="hidden" name="hidBrandCode" id="hidBrandCode"  value="<%=bean.getBrandCode()%>"/><B><%=rowNumber%></B></td>
															<td width="400px;" align="left" ><%=bean.getBrandName()%></td>
															<td width="50px" align="center">
															   <button id="btn_delete" name="btn_delete"  class="btn btn-warning btn-mini fa fa-times" style="width:25px;" onclick="lp_del_row_table(<%=bean.getBrandCode()%>);"></button>
															</td>
														</tr> 
														<% } } %> 
												<tr>
													<td align="center" style="visibility:hidden;">
													</td>
													<td align="left" style="visibility:hidden;">
													</td>
													<td width="50px" align="center">
													   <button id="btn_add" name="btn_add"  class="btn btn-warning btn-mini fa fa-plus-square" style="width:25px;" onclick="lp_add_row_table();"></button>
													</td>
												</tr> 
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
