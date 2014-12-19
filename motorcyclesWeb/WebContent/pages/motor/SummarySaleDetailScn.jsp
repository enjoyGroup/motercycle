<%@ include file="/pages/include/enjoyInclude.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="th.go.motorcycles.app.enjoy.form.SummarySaleDetailForm, th.go.motorcycles.app.enjoy.bean.SummarySaleDetailBean"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="summarySaleDetailForm" class="th.go.motorcycles.app.enjoy.form.SummarySaleDetailForm" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script>
	$(document).ready(function(){
		
		$('#btnSearch').click(function(){
		    var lo_pageAction			= null;
		    var lo_frm					= null;
		    var url 					= '<%=servURL%>/EnjoyGenericSrv';
		    
		    try{
		    	/*lo_pageAction 	= document.getElementById("pageAction");
		    	lo_frm 			= document.getElementById("frm");
		    	
		    	lo_pageAction.value = "search";
		    	lo_frm.submit();*/
		    	
				document.getElementById("pageAction").value 	= "search";
		    	params 	= $('#frm').serialize();// + "&pageAction=search";
				$.ajax({
					async:false,
		            type: "POST",
		            url: url,
		            data: params,
		            beforeSend: "",
		            success: function(data){
		            	window.location.replace('/motorcyclesWeb/pages/motor/SummarySaleDetailScn.jsp');
		            }
		        });
		    	
		    }catch(e){
		    	alert("btnSearch :: " + e);
		    }
		    
		});
	
		$('#btnReset').click(function(){
		    var lo_pageAction			= null;
		    var lo_frm					= null;
		    var url 					= '<%=servURL%>/EnjoyGenericSrv';
		    
		    try{
		    	
				document.getElementById("pageAction").value 	= "new";
		    	params 	= $('#frm').serialize();// + "&pageAction=new";
				$.ajax({
					async:false,
		            type: "POST",
		            url: url,
		            data: params,
		            beforeSend: "",
		            success: function(data){
		            	window.location.replace('/motorcyclesWeb/pages/motor/SummarySaleDetailScn.jsp');
		            }
		        });
		    	
		    }catch(e){
		    	alert("btnReset :: " + e);
		    }
		    
		});
		
		$('#btnPrint').click(function(){
		    try{
		    	if ((document.getElementById("invoiceId").value		  == "") &&
	    			(document.getElementById("invoiceDateFrom").value == "") &&
	    			(document.getElementById("invoiceDateTo").value   == "") &&
	    			(document.getElementById("brandName").value 	  == "") &&
	    			(document.getElementById("model").value 		  == "") &&
	    			(document.getElementById("cusName").value 		  == ""))	{
		    		alert("กรุณาระบุเงื่อนไขอย่างน้อย 1 อย่างก่อนทำการพิมพ์รายงาน");
		    	} else {
					document.getElementById("pageAction").value 	= "pdf";
					document.getElementById("frm").target 			= "_blank";
				    document.getElementById("frm").submit();
		    	}
		    }catch(e){
		    	alert("btnPrint :: " + e);
		    }
		    
		});
		
		$('#trigger-DateFrom').on('click',function(){
			$('#invoiceDateFrom').focus();
		})
		
		$('#trigger-DateTo').on('click',function(){

			$('#invoiceDateTo').focus();
		})
		
	});
	
</script>
</head>
<body>
	<form id="frm" action="<%=servURL%>/EnjoyGenericSrv">
		<input type="hidden" id="service" name="service" value="servlet.SummarySaleDetailServlet" />
		<input type="hidden" id="pageAction" name="pageAction" value="" />
		
		<section class="vbox">
			<section>
				<section class="hbox stretch">
					<section id="content">
						<section class="vbox">
							<section class="scrollable padder">
								<div class="alert alert-block alert-error fade in">
					            	<h4 class="alert-heading">สรุปการขายรถ</h4>
					          	</div>
					          	<div class="row">
									<div class="col-sm-12">
										<section class="panel panel-default">
											<header class="panel-heading font-bold">ค้นหาสรุปการขาย</header>
											<div class="panel-body">
												<table border="0" cellpadding="0" cellspacing="5" class="table span12" >
													<colgroup>
														<col align="left" width="20%" />
														<col align="left" />
														<col align="left" width="20%" />
														<col align="left" />
													</colgroup>
													<tr>
														<td>เลขที่ใบกำกับ : </td>
														<td>
															<input type="text" id="invoiceId" name="invoiceId" value="<%=summarySaleDetailForm.getInvoiceId()%>" />
														</td>
														<td>วันที่ขาย : </td>
														<td>
															<input type="text" style="width:100px;" class="input-sm input-s datepicker-input form-control" data-date-format="dd/mm/yyyy" id="invoiceDateFrom" name="invoiceDateFrom" value="<%=summarySaleDetailForm.getInvoiceDateFrom()%>" />
															<i class="fa fa-fw fa-calendar" id='trigger-DateFrom' style='cursor:pointer'></i>
															&nbsp;-&nbsp;
															<input type="text" style="width:100px;" class="input-sm input-s datepicker-input form-control" data-date-format="dd/mm/yyyy" id="invoiceDateTo" name="invoiceDateTo" value="<%=summarySaleDetailForm.getInvoiceDateTo()%>" />
															<i class="fa fa-fw fa-calendar" id='trigger-DateTo' style='cursor:pointer'></i>
														</td>
													</tr>
													<tr>
														<td>ยี่ห้อ : </td>
														<td>
															<input type="text" id="brandName" name="brandName" value="<%=summarySaleDetailForm.getBrandName()%>" />
														</td>
														<td>รุ่น : </td>
														<td>
															<input type="text" id="model" name="model" value="<%=summarySaleDetailForm.getModel()%>" />
														</td>
													</tr>
													<tr>
														<td>ชื่อลูกค้า : </td>
														<td colspan="4">
															<input type="text" id="cusName" name="cusName" value="<%=summarySaleDetailForm.getCusName()%>" size="40" />
															<input type="button" class="btn btn-danger" id="btnSearch" name="btnSearch" value="ค้นหา" />
														</td>
													</tr>
												</table>
											</div>
											<header class="panel-heading font-bold">ข้อมูลสรุปการขาย</header>
											<div class="panel-body">
												<table border="1" class="table span12" style="width:95%;POSITION: relative;  TOP: expression(offsetParent.scrollTop);" >
													<tr bgcolor="#473636"  class="text_white">
														<td width="50px">ลำดับ</td>
														<td width="100px">เลขกำกับภาษี</td>
														<td width="150px">ชื่อลูกค้า</td>
														<td width="200px">รายละเอียดรถ</td>
														<td width="100px">ราคาขาย</td>
														<td width="100px">ราคาภาษี</td>
														<td width="100px">ราคาขายสุทธิ</td>
														<td width="100px">หมายเหต</td>
													</tr>
													<%
															List<SummarySaleDetailBean> dataList 	= summarySaleDetailForm.getDataList();
			    											SummarySaleDetailBean 		bean 		= null;
			    											int							seq			= 1;
															
															for(int i=0;i<dataList.size();i++){
																bean = dataList.get(i);
															
														%>
														<tr>
															<td width="50px"><%=seq%></td>
															<td width="100px"><%=bean.getInvoiceId()%></td>
															<td width="150px"><%=bean.getCusName()%></td>
															<td width="200px"><%=bean.getMotorcyclesdetails()%></td>
															<td width="100px"><%=bean.getPriceAmount()%></td>
															<td width="100px"><%=bean.getVatAmount()%></td>
															<td width="100px"><%=bean.getCommAmount()%></td>
															<td width="100px"><%=bean.getRemark()%></td>
														</tr>
														<% seq++;} %>
												</table>
												<br/>
												<table border="0" cellpadding="0" cellspacing="5" class="table span12" style="width:95%;" >
													<tr align="center">
														<td>
															<input type="button" class="btn btn-danger" id="btnPrint" name="btnPrint" value="พิมพ์" />&nbsp;
															<input type="reset" class="btn btn-danger" id="btnReset" name="btnReset" value="เริ่มใหม่" />
														</td>
													</tr>
												</table>
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