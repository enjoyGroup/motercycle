<%@page import="th.go.motorcycles.app.enjoy.bean.BrandBean"%>
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

<script>
	$(document).ready(function(){
		
		$('#btnSearch').click(function(){ 
		    var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.BrandServlet'; 
		    var pageAction			= "searchData";
		    var lv_params			= ""; 
		    var fullname            = gp_sanitizeURLString($('#fullName').val());  
		        
			try{
				fullname = fullname.trim(); 
				
				lv_params 	= "service=" + $('#service').val()  
				            + "&brandCode=" + gp_sanitizeURLString($('#brandtCode').val()) 
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
		
		$('#btnSave').click(function(){
			var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.BrandServlet'; 
			var pageAction			= "saveUpdData";
			var lv_params			= ""; 
	        var lo_obj;
		    
	        if(!lp_validate()){
	    		return;
	    	}
	        
		    try{
		    	lv_params 	= "&pageAction=" + pageAction + "&" + $('#frm').serialize();
				$.ajax({
					async:false,
		            type: "POST",
		            url: url,
		            data: lv_params,
		            beforeSend: "",
		            success: function(data){
		            	alert("บันทึกรายการเรียบร้อย  ");
		            }
		        });
		    	
		    }catch(e){
		    	alert("btnSubmit :: " + e);
		    }
		    
		});
		$('#btnCancel').click(function(){ 
		    var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.BrandServlet'; 
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
		       		  $('#fullName').val(""); 
		            }
		        });
			}catch(err){
				alert("btnCancel :: " + err);
			}
			
		}); 	
	});
	
	function lp_add_row_table(){
		var lo_table 	= null;
		var lv_length 	= null;
		var row 		= null;
		var cell1 		= null;
		var cell2 		= null;
		var cell3 		= null;	
		
		try{
			lo_table 	= document.getElementById("tb_result");
			lv_length 	= lo_table.rows.length - 1;
			row 		= lo_table.insertRow(lv_length);
			cell1 		= row.insertCell(0);
			cell2 		= row.insertCell(1);
			cell3 		= row.insertCell(2);
			
			cell1.align	= "center";
			cell1.innerHTML = "<b>" + lv_length + "<b>";
			cell2.innerHTML = "<td width='400px;' align='left' ><input type='text' name='brandName' id='brandName'style='width: 300px;' onclick/></td>";
			cell3.innerHTML = "<td width='50px' align='center'><input type='hidden' name='hidBrandStatus' id='hidBrandStatus'  value='I'></td>";	
		}catch(e){
			alert("lp_add_row_table :: " + e);
		}
	}

	function lp_validate(){
		var lo_obj          = eval('document.getElementsByName("brandName")');
		try{ 
			for(var i=0;i<lo_obj.length;i++){
				if( lo_obj[i].value == "" ){
					alert("กรุณาระบุข้อมูลให้ครบถ้วนก่อนทำการบันทึก");
		            return false;
				}
			} 
		}catch(e){
			alert("lp_validate :: " + e);
			return false;
		}
		return true;
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
						<form class="form-horizontal"  id="frm" action="<%=servURL%>/EnjoyGenericSrv?service=servlet.BrandServlet">
						<input type="hidden" id="service" name="service" value="servlet.BrandServlet" />
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
													<td  style="text-align: center;" width="30px;" ><B>ลำดับ</B></th>
													<td  style="text-align: left;"   width="400px;"><B>ยี่ห้อรถจักรยานยนต์</B></th>
													<td  style="text-align: center;" width="20px;" ><B>add</B></th>
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
														 <tr>
															<td width="30px;" align="center"><input type="hidden" name="hidBrandCode" id="hidBrandCode"  value="<%=bean.getBrandCode()%>"/><B><%=rowNumber%></B></td>
															<td width="400px;" align="left" ><input type="text" name="brandName" id="brandName" value="<%=bean.getBrandtName()%>" style="width: 300px;"/></td>
															<td width="50px" align="center"><input type="hidden" name="hidBrandStatus" id="hidBrandStatus"  value="<%=bean.getBrandStatus()%>"/></td>
														</tr> 
														<% } 
													} %> 
													<tr>
														<td align="center" style="visibility:hidden;"></td>
														<td align="left" style="visibility:hidden;"></td>
														<td align="center">
														  <a id="btn_add" href="#" class="btn btn-warning btn-mini fa fa-plus-square" style="width:25px;" onclick="lp_add_row_table();"></a>
														</td>
													</tr>
											</table> 
										</div>
										<div class="form-group" align="center">	 
											<button class="btn btn-primary" id="btnSave">บันทึก</button>   
											<button class="btn btn-primary" id="btnCancel" onclick="lp_reset_page();">เริ่มใหม่</button> 
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
