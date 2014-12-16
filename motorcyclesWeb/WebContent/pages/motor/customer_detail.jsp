<%@page import="th.go.motorcycles.app.enjoy.form.CustomerForm"%>
<%@ include file="/pages/include/enjoyLoginInclude.jsp"%>
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
			
			var lo_divResult		= document.getElementById("div_result");
			var lo_tabResultDtl		= document.getElementById("tb_result");
			var	lv_rows				= lo_tabResultDtl.rows.length;
			var newNodeTr 	        = null;
		    var newNodeTd1 			= null;
		    var newNodeTd2 			= null;
		    var newNodeTd3 			= null;
		    var newNodeTd4 			= null;
		    var newNodeTd5 			= null;
		    var cusCode 			= "";
		    var la_data 			= null;
		    var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.CustomerSearchServlet'; 
		    var pageAction			= "searchData";
		    var lv_params			= "";
			
			try{  
				lv_params 	= "service=" + $('#service').val()  
				            + "&cusCode=" + gp_sanitizeURLString($('#custCode').val())
							+ "&pageAction=" + pageAction;
 
				$.ajax({
		            type: "POST",
		            async: false,
		            url: url,
		            data: lv_params,
		            beforeSend: "",
		            success: function(data){
		            	if(data.indexOf('OK') > -1){ 
						}else{
							//alert(data);
						}
		            }
		        });
			}catch(err){
				alert("btnSearch :: " + err);
			}
			
		});
	});
	
	function lp_del_row_table(ao_obj){ 
		var la_hidProId 		= null;
		var lv_index			= 0;
		var url					= '<%=servURL%>/EnjoyGenericSrv?service=servlet.CustomerSearchServlet';  
	    var pageAction			= "delRecord";
	    var lv_params			= "";
	    var lo_divResult		= document.getElementById("div_result");
		var lo_tabResultDtl		= document.getElementById("tb_result");
		
		try{
			if(confirm("Please confirm to delete this record !!")){
				la_hidProId = document.getElementsByName("hidCusCode");
				lv_index	= gp_rowTableIndex(ao_obj);
 
				lv_params 	= "service=" + $('#service').val() 
				+ "&cusCode=" + la_hidProId[lv_index].value 
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
	
	
	
	function lp_reset_page(){
		 $('#cusCode').val(""); 
		 $('#customer').val("");
	}
	
	function lp_onBlurPrice(ao_obj){
		gp_format(ao_obj, 2);
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
													    <label class="col-sm-1 control-label" style="text-align:right">รหัสลูกค้า :</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="custCode" name="custCode"> 
														</div>
														<label class="col-sm-1 control-label" style="text-align:right">ชื่อ-นามสกุล:</label>
														<div class="col-md-2"> 
															<input type="text" class="form-control" id="customer" name="customer">
														</div> 
													 	<button class="btn btn-primary" id="btnSearch">ค้นหา</button> 
												        <button class="btn btn-primary" id="btnCancel" onclick="lp_reset_page()">เริ่มใหม่</button>  
												</div> 
											</div>
											</div>
										</div>
									</section>
									<section class="panel panel-default">
										<header class="panel-heading font-bold">ข้อมูลลูกค้า</header>
										
										<table border="0" align="center"  class="table span12"  style=" width:400px;" >
											<tr>
												<td>  
												<div class="panel-body" id="div_result">
										 
													<table id="tb_result" border="1" class="table span12" style="height: 200px;overflow-y:auto;width:500px;">
										               <tr bgcolor="#473636"  class="text_white" >
															<th  style="text-align: center;" width="30px;" ><B>ลำดับ</B></th>
															<th  style="text-align: left;"   width="20px;"><B>ชื่อ-นามสกุล</B></th>
															<th  style="text-align: left;"   width="100px;"><B>ที่อยู่</B></th> 
															<th  style="text-align: center;" width="50px;" ><B>add/delete</B></th>
														</tr> 
													     
													    <%
															List<CustomerBean>  list        =   customerForm.getListCustomer();
															CustomerBean 		bean 		=   null;
															int rowNumber;
															String address ;
															for(int i=0;i<list.size();i++){
																bean = list.get(i);
																rowNumber = i+1;
																address  = "";
															    if(bean.getHouseNumber()!=""||bean.getHouseNumber()!=null){
															    	address += bean.getHouseNumber();
															    }
															    if(bean.getMooNumber()!=""||bean.getMooNumber()!=null){
															    	address += " หมู่  " +bean.getMooNumber();
															    }
															    if(bean.getSoiName()!=""||bean.getSoiName()!=null){
															    	address += " ซอย   " +bean.getSoiName();
															    }
															    if(bean.getStreetName()!=""||bean.getStreetName()!=null){
															    	address += " ถนน   " +bean.getStreetName();
															    }
															    if(bean.getSubdistrictCode()!=""||bean.getSubdistrictCode()!=null){
															    	address +=  " ตำบล   " +bean.getSubdistrictCode();
															    }
															    if(bean.getDistrictCode()!=""||bean.getDistrictCode()!=null){
															    	address +=  " อำเภอ   " +bean.getDistrictCode();
															    }
															    if(bean.getProvinceCode()!=""||bean.getProvinceCode()!=null){
															    	address +=  " จังหวัด   " +bean.getProvinceCode();
															    }
														%>
														 <tr>
															<td width="30px;" align="center"><input type="hidden" name="hidCusCode" id="hidCusCode"  value="<%=bean.getCusCode()%>"/><B><%=rowNumber%></B></td>
															<td width="20px;" align="left"><%=bean.getCustName()%> <%=bean.getCustSurname()%></td>
															<td width="300px;" align="left"><%=address%></td>
															<td width="50px" align="center">
															   <button id="btn_delete" name="btn_delete"  class="btn btn-warning btn-mini fa fa-times" style="width:25px;" onclick="lp_del_row_table(this);"></button>
															</td>
														</tr>
														<% } %>
														 
														 <tr>
															<td align="center" style="visibility:hidden;">
															   <label><B>1</B></label>
															</td>
															<td align="left" style="visibility:hidden;">
															   <input type="text" name="xxx"  id="xxx" size="15">
															</td>
															<td align="left" style="visibility:hidden;">
															   <input type="text" name="xxx"  id="xxx" size="100">
															</td> 
															<td align="center">
															  <button id="btn_add" name="btn_add"  class="btn btn-warning btn-mini fa fa-plus-square"  style="width:25px;" onclick="lp_add_row_table()"></button>
															</td>
														</tr> 
													</table> 
													</div>
												</td>
											</tr>
										</table> 
								    </section>
								</div>
							</div> 
							
							</form>
						</section>
					</section>
				</section>
				<a href="#" class="hide nav-off-screen-block"
					data-toggle="class:nav-off-screen" data-target="#nav"></a>
			</section>

		</section>
	</section>
</body>
</html>
