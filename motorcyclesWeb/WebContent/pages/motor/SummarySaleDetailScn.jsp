<%@ include file="/pages/include/enjoyInclude.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="th.go.motorcycles.app.enjoy.form.SummarySaleDetailForm, th.go.motorcycles.app.enjoy.bean.SummarySaleDetailBean, th.go.motorcycles.app.enjoy.bean.ComboBean"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="summarySaleDetailForm" class="th.go.motorcycles.app.enjoy.form.SummarySaleDetailForm" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
	.input-disabled{
	    background-color:#EBEBE4;
	    border:1px solid #ABADB3;
	    color:rgb(84, 84, 84);
	}
	
	.rowSelect:hover{
	    background-color:FFCCFF;
	    /*opacity: .2; */
	    cursor: pointer;
	}
	
	#tableResult th:hover{
	    opacity: .2;
	    cursor: pointer;
	}
	
	
	
</style> 
<script>
	
	var gv_service 		= null;
	var gv_url 			= '<%=servURL%>/EnjoyGenericSrv';
	
	$(document).ready(function(){
		
		var d 		= new Date();
	    var toDay 	= d.getDate() + '/' + (d.getMonth() + 1) + '/' + (d.getFullYear() + 543);
	    gv_service 	= "service=" + $('#service').val();
		
		$('#btnSearch').click(function(){
		    var lv_invoiceDateFrom			= null;
		    var lv_invoiceDateTo			= null;
		    
		    try{
		    	lv_invoiceDateFrom 	= gp_trim($("#invoiceDateFrom").val());
				lv_invoiceDateTo 	= gp_trim($("#invoiceDateTo").val());
				
				if(lv_invoiceDateFrom=="" && lv_invoiceDateTo!=""){
					alert("กรุณาระบุวันที่ขายให้ครบ");
					$("#invoiceDateFrom").focus();
					return;
				}
				
				if(lv_invoiceDateTo=="" && lv_invoiceDateFrom!=""){
					alert("กรุณาระบุวันที่ขายให้ครบ");
					$("#invoiceDateTo").focus();
					return;
				}
				
				if(gp_toDate(lv_invoiceDateFrom) > gp_toDate(lv_invoiceDateTo)){
					alert("วันที่เริ่มต้นต้องน้อยกว่าวันที่สิ้นสุด");
					return;
				}
				
		    	
				document.getElementById("pageAction").value 	= "search";
		    	params 	= $('#frm').serialize();
				$.ajax({
					async:false,
		            type: "POST",
		            url: gv_url,
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
		    	if (document.getElementById("tableResult").rows.length <= 1)	{
		    		alert("กรุณาค้นหาข้อมูลก่อนทำการพิมพ์รายงาน");
		    	} else {
					document.getElementById("pageAction").value 	= "pdf";
					document.getElementById("frm").target 			= "_blank";
				    document.getElementById("frm").submit();
		    	}
		    }catch(e){
		    	alert("btnPrint :: " + e);
		    }
		    
		});
		
		//แบบสามารถเลือกเดือนได้อย่างเดียว
		/*$(".dateFormat").datepicker({ dateFormat: 'dd/mm/yy', isBuddhist: true, defaultDate: toDay, dayNames: ['อาทิตย์', 'จันทร์', 'อังคาร', 'พุธ', 'พฤหัสบดี', 'ศุกร์', 'เสาร์'],
            dayNamesMin: ['อา.','จ.','อ.','พ.','พฤ.','ศ.','ส.'],
            monthNames: ['มกราคม','กุมภาพันธ์','มีนาคม','เมษายน','พฤษภาคม','มิถุนายน','กรกฎาคม','สิงหาคม','กันยายน','ตุลาคม','พฤศจิกายน','ธันวาคม'],
            monthNamesShort: ['ม.ค.','ก.พ.','มี.ค.','เม.ย.','พ.ค.','มิ.ย.','ก.ค.','ส.ค.','ก.ย.','ต.ค.','พ.ย.','ธ.ค.']});*/
		
        //แบบสามารถเลือก เดือนกับปีได้
		$(".dateFormat").datepicker({ changeMonth: true, changeYear: true,dateFormat: 'dd/mm/yy', isBuddhist: true, defaultDate: toDay,dayNames: ['อาทิตย์','จันทร์','อังคาร','พุธ','พฤหัสบดี','ศุกร์','เสาร์'],
            dayNamesMin: ['อา.','จ.','อ.','พ.','พฤ.','ศ.','ส.'],
            monthNames: ['มกราคม','กุมภาพันธ์','มีนาคม','เมษายน','พฤษภาคม','มิถุนายน','กรกฎาคม','สิงหาคม','กันยายน','ตุลาคม','พฤศจิกายน','ธันวาคม'],
            monthNamesShort: ['ม.ค.','ก.พ.','มี.ค.','เม.ย.','พ.ค.','มิ.ย.','ก.ค.','ส.ค.','ก.ย.','ต.ค.','พ.ย.','ธ.ค.']});
		
		$('#trigger-DateFrom').on('click',function(){
			$('#invoiceDateFrom').focus();
		});
		
		$('#trigger-DateTo').on('click',function(){

			$('#invoiceDateTo').focus();
		});
		
		$("#tableResult").tablesorter(); 
		
		$( "#tableResult th" ).resizable();
		
		$( "#cusName" ).autocomplete({
			 source: function(request, response) {
	            $.ajax({
	            	async:false,
		            type: "POST",
	                url: gv_url,
	                dataType: "json",
	                data: gv_service + "&pageAction=getnameSname"+"&cusName=" + gp_trim(request.term),
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
	
	function lp_sendEditPage(av_val, av_index){
		
		var la_chassisDisp 	= null;
		var la_engineNoDisp = null;
		var la_flagAddSales = null;
		
		try{
			
			la_chassisDisp 		= document.getElementsByName("chassisDisp");
			la_engineNoDisp 	= document.getElementsByName("engineNoDisp");
			la_flagAddSales 	= document.getElementsByName("flagAddSales");
			
			if(la_flagAddSales[av_index].value=="N" || 
					(la_flagAddSales[av_index].value=="Y" && (la_chassisDisp[av_index].value!="" && la_engineNoDisp[av_index].value!=""))){
				window.location.replace(gv_url + "?service=servlet.EntrySaleDetailServlet&pageAction=edit&invoiceId=" + av_val);
			}else{
				alert("ไม่อนุญาตให้แก้ไขใบส่งเสิรมการขายโดยตรง");
			}
			
			//window.location.replace(gv_url + "?service=servlet.EntrySaleDetailServlet&pageAction=edit&invoiceId=" + av_val);
		}catch(e){
			alert("lp_sendEditPage :: " + e);
		}
	}
	
	//*********************************************************************************//
	//    รายละเอียดเกี่ยวกับการกดปุ่ม First/Previous/Next/Last บนหน้าจอ				       //												
	//*********************************************************************************//
	function lp_first_page()
	{
		if ($("#selPage").val() != "1")
		{
			$("#selPage").val("1");
			lp_selPage();
		}	
	}
	
	function lp_previous_page()
	{
		if ($("#selPage").val() != "1")
		{
			var lv_selPage = parseFloat($("#selPage").val()) - 1;
			$("#selPage").val(lv_selPage);
			lp_selPage();
		}	
	}
	
	function lp_next_page()
	{
		if ($("#selPage").val() != $("#txtMaxresult").val())
		{
			var lv_selPage = parseFloat($("#selPage").val()) + 1;
			$("#selPage").val(lv_selPage);
			lp_selPage();
		}	
	}
	
	function lp_last_page()
	{
		if ($("#selPage").val() != $("#txtMaxresult").val())
		{
			$("#selPage").val($("#txtMaxresult").val());
			lp_selPage();
		}	
	}

	function lp_selPage(){
		
		var lv_selPage = null;
		
		try{
			lv_selPage = $("#selPage").val();
	    	params 	= "service=servlet.SummarySaleDetailServlet&pageAction=getPage&pageNum=" + lv_selPage;
			$.ajax({
				async:false,
	            type: "POST",
	            url: gv_url,
	            data: params,
	            beforeSend: "",
	            success: function(data){
	            	window.location.replace('/motorcyclesWeb/pages/motor/SummarySaleDetailScn.jsp');
	            }
	        });
			
		}catch(e){
			alert("lp_selPage :: " + e);
		}
	}
	//*********************************************************************************//
	
	function lp_checkFormatdate(){
		
		var lo_invoiceDateFrom 	= null;
		var lo_invoiceDateTo 	= null;
		
		try{
			lo_invoiceDateFrom 	= document.getElementById("invoiceDateFrom");
			lo_invoiceDateTo 	= document.getElementById("invoiceDateTo");
			
			//สำหรับเช็ค Format วันที่ต้องเป็น dd/mm/yyyy(พ.ศ.) เท่านั้น
			if(!gp_checkDate(lo_invoiceDateFrom))return;
			if(!gp_checkDate(lo_invoiceDateTo))return;
			
		}catch(e){
			alert("lp_checkFormatdate :: " + e);
		}
	}
	
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
														<col align="left" width="15%" />
														<col align="left" />
														<col align="left" width="15%" />
														<col align="left" />
													</colgroup>
													<tr>
														<td><label class="control-label" style="text-align:right">เลขที่ใบกำกับ : </label></td>
														<td class="no-padd-left">
															<input type="text" id="invoiceId" name="invoiceId" value="<%=summarySaleDetailForm.getInvoiceId()%>" />
														</td>
														<td><label class="control-label" style="text-align:right">วันที่ขาย : </label></td>
														<td class="no-padd-left">
															<input type="text" class="dateFormat" style="width:100px;" id="invoiceDateFrom" name="invoiceDateFrom" placeholder="DD/MM/YYYY" value="<%=summarySaleDetailForm.getInvoiceDateFrom()%>" onblur="lp_checkFormatdate();" />
															<i class="fa fa-fw fa-calendar" id='trigger-DateFrom' style='cursor:pointer'></i>
															&nbsp;-&nbsp;
															<input type="text" class="dateFormat" style="width:100px;" id="invoiceDateTo" name="invoiceDateTo" placeholder="DD/MM/YYYY" value="<%=summarySaleDetailForm.getInvoiceDateTo()%>" onblur="lp_checkFormatdate();" />
															<i class="fa fa-fw fa-calendar" id='trigger-DateTo' style='cursor:pointer'></i>
														</td>
													</tr>
													<tr>
														<td><label class="control-label" style="text-align:right">ยี่ห้อ : </label></td>
														<td class="no-padd-left">
															<input type="text" id="brandName" name="brandName" value="<%=summarySaleDetailForm.getBrandName()%>" />
														</td>
														<td><label class="control-label" style="text-align:right">รุ่น : </label></td>
														<td class="no-padd-left">
															<input type="text" id="model" name="model" value="<%=summarySaleDetailForm.getModel()%>" />
														</td>
													</tr>
													<tr>
														<td><label class="control-label" style="text-align:right">ชื่อลูกค้า : </label></td>
														<td class="no-padd-left">
															<input type="text" id="cusName" name="cusName" value="<%=summarySaleDetailForm.getCusName()%>" size="40" />
														</td>
														<td><label class="control-label" style="text-align:right">สาขา : </label></td>
														<td class="no-padd-left">
															<select id="company" name="company">
																<%
																	List<ComboBean> companyComboList = summarySaleDetailForm.getCompanyComboList();
																	ComboBean 		comboBean 		= null;
																	
																	for(int i=0;i<companyComboList.size();i++){
																		comboBean = companyComboList.get(i);
																%>
																	<option value="<%=comboBean.getCode()%>" <%if(summarySaleDetailForm.getCompany().equals(comboBean.getCode())){ %> selected <%} %>><%=comboBean.getDescription()%></option>
																	<%}%>
															</select>
														</td>
													</tr>
													<tr>
														<td colspan="4" align="center">
															<input type="button" class="btn btn-primary" id="btnSearch" name="btnSearch" value="ค้นหา" />
														</td>
													</tr>
												</table>
											</div>
											<header class="panel-heading font-bold">ข้อมูลสรุปการขาย</header>
											<div class="panel-body">
												<table border="0" cellpadding="0" cellspacing="5" class="table span12" style="width:95%;" >
													<tr align="right">
														<td>
															<span style="top: -3px;">จำนวน&nbsp;</span>
															<input type="text" id="i_txt_nvt_totalresult" name="i_txt_nvt_totalresult" style="top:0px;left:0px;width: 50px;"  readonly="readonly" value="<%=summarySaleDetailForm.getTotalRecord()%>" >
															<span style="top: -3px;">&nbsp;รายการ&nbsp;&nbsp;</span>
															<img id="i_img_nvt_first" name="i_img_nvt_first" src="/motorcyclesWeb/images/first.gif" style="cursor:hand;top:1px;" title="First" onclick="lp_first_page();">
															<img id="i_img_nvt_prev"  name="i_img_nvt_prev"  src="/motorcyclesWeb/images/prv.gif"   style="cursor:hand;top:1px;" title="Previous" onclick="lp_previous_page();">
															<input type="text" id="selPage" name="selPage" style="top:0px;left:0px;width: 30px;text-align: right;" maxlength="3" readonly="readonly" value="<%=summarySaleDetailForm.getPageNum()%>">
										
															<span class="c_field_label" style="top:-5px;">/</span>
															<span id="i_txt_nvt_total" class="c_field_label" style="top:-5px;" name="i_txt_nvt_total"><%=summarySaleDetailForm.getTotalPage()%></span>
															<input type="hidden" id="txtMaxresult" name="txtMaxresult" value="<%=summarySaleDetailForm.getTotalPage()%>" >
															<img id="i_img_nvt_next"  name="i_img_nvt_next" src="/motorcyclesWeb/images/next.gif" style="cursor:hand;top:1px;" title="Next" onclick="lp_next_page();">
															<img id="i_img_nvt_last"  name="i_img_nvt_last" src="/motorcyclesWeb/images/last.gif" style="cursor:hand;top:1px;" title="Last" onclick="lp_last_page();">
														</td>
													</tr>
												</table>
												<table id="tableResult" border="1" class="table span12" style="width:95%;" >
													<thead> 
														<tr bgcolor="#473636"  class="text_white" style="white-space: nowrap;">
															<th style="text-align: center;">ลำดับ</th>
															<th style="text-align: center;">เลขกำกับภาษี</th>
															<th style="text-align: center;">ชื่อลูกค้า</th>
															<th style="text-align: center;">รายละเอียดรถ</th>
															<th style="text-align: center;">วันที่บันทึก</th>
															<th style="text-align: center;">ราคาขายสุทธิ</th>
															<th style="text-align: center;">อ้างอิงจากใบกำกับภาษี</th>
															<th style="text-align: center;">หมายเหต</th>
														</tr>
													</thead>
													<tbody>
													<%
															List<SummarySaleDetailBean> dataList 	= summarySaleDetailForm.getDataList();
			    											SummarySaleDetailBean 		bean 		= null;
			    											int							seq			= 1;
															
															for(int i=0;i<dataList.size();i++){
																bean = dataList.get(i);
															
														%>
														<tr class="rowSelect" onclick="lp_sendEditPage('<%=bean.getInvoiceId()%>', <%=i%>)">
															<td style="text-align:center"><%=seq%></td>
															<td><%=bean.getInvoiceId()%></td>
															<td><%=bean.getCusName()%></td>
															<td><%=bean.getMotorcyclesdetails()%></td>
															<td><%=bean.getRecordAddDate()%></td>
															<td class="money-text"><%=bean.getTotalAmount()%></td>
															<td><%=bean.getMasterInvoiceId()%></td>
															<td>
																<%=bean.getRemark()%>
																<input type="hidden" name="chassisDisp" value="<%=bean.getChassisDisp()%>" />
																<input type="hidden" name="engineNoDisp" value="<%=bean.getEngineNoDisp()%>" />
																<input type="hidden" name="flagAddSales" value="<%=bean.getFlagAddSales()%>" />
															</td>
														</tr>
														<% seq++;} %>
													</tbody>
												</table>
												<br/>
												<table border="0" cellpadding="0" cellspacing="5" class="table span12" style="width:95%;" >
													<tr align="center">
														<td>
															<input type="button" class="btn btn-primary" id="btnPrint" name="btnPrint" value="พิมพ์" />&nbsp;
															<input type="reset" class="btn btn-primary" id="btnReset" name="btnReset" value="เริ่มใหม่" />
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