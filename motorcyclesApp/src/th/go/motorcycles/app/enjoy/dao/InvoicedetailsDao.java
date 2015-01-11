package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class InvoicedetailsDao {
//	private EnjoyConectDbs db = null;	
	public InvoicedetailsDao(){
//		db = new EnjoyConectDbs();
	}
	
	public JSONObject SummarySalePDF(String	invoiceId, 
									 String invoiceDateFrom,
									 String	invoiceDateTo,
									 String	brandName,
									 String	model,
									 String	cusName,
									 UserDetailsBean userBean){
		System.out.println("[InvoicedetailsDao][SummarySalePDF][Begin]");
		String 				sql			 	= null;
		ResultSet 			rs 				= null;
		JSONObject 			jsonObjectMain  = null;
		JSONObject 			jsonObjectDetail= null;
		JSONArray 			listJSONArray 	= null;
		String 				where			= "";
		double				totleAmount     = 0;
		double				summaryAmount   = 0;
		EnjoyConectDbs 		db 				= null;
		try{
			db  = new EnjoyConectDbs();
			sql = " select t.* from (select  i.invoiceId invoiceId"
					+ " , CONCAT(c.cusName, ' ', c.cusSurname) cusName"
					+ " , CONCAT(b.brandName, ' รุ่น ' , m.model, ' สี ' , i.color ) motorcyclesdetails"
					+ " , CONCAT(m.chassis, i.chassisDisp ) chassisDisp"
					+ " , CONCAT(m.engineNo,'-' ,i.EngineNoDisp ) EngineNoDisp"
					+ " , b.brandName brandName"
					+ " , m.model model"
					+ " , i.priceAmount priceAmount"
					+ " , i.vatAmount vatAmount"
					+ " , i.totalAmount totalAmount"
					+ " , i.commAmount commAmount"
					+ " ,STR_TO_DATE(i.invoiceDate, '%Y%m%d') invoiceDate"
					+ " , i.remark remark"
					+ " , i.invoiceIdAddSales invoiceIdAddSales"
					+ " from  invoicedetails i, customer c, motorcyclesdetails m, branddetails b"
					+ " where c.cusCode         = i.cusCode"
					+ "  and m.motorcyclesCode  = i.motorcyclesCode"
					+ "  and b.brandCode	    = m.brandCode) t where 1=1 ";

			if(!invoiceId.equals("")){
				where += " and t.invoiceId = '" + invoiceId + "'";
			}
			
			if(!cusName.equals("")){
				where += " and t.cusName like ('" + cusName + "%')";
			}
			
			if(!brandName.equals("")){
				where += " and t.brandName like ('" + brandName + "%')";
			}
			
			if(!model.equals("")){
				where += " and t.model like ('" + model + "%')";
			}
			
			if(!invoiceDateFrom.equals("")){
				where += " and invoiceDate BETWEEN STR_TO_DATE('" + invoiceDateFrom + "', '%Y%m%d') AND STR_TO_DATE('" + invoiceDateTo + "', '%Y%m%d')";
			}
			sql = sql + where + " ORDER BY t.invoiceId ";
			
			System.out.println("[InvoicedetailsDao][SummarySalePDF] sql :: " + sql);
		    rs 				= db.executeQuery(sql);
		    jsonObjectMain 	= new JSONObject();
		    listJSONArray 	= new JSONArray();
		    
		    while(rs.next()){
		    	jsonObjectDetail 	= new JSONObject();
		    	jsonObjectDetail.put("invoiceId",       	EnjoyUtils.nullToStr(rs.getString("invoiceId")));
		    	jsonObjectDetail.put("cusNameDisp",     	EnjoyUtils.nullToStr(rs.getString("cusName")));
		    	if (rs.getString("motorcyclesdetails") != null) {
		    		jsonObjectDetail.put("motorcyclesDisp", EnjoyUtils.nullToStr(rs.getString("motorcyclesdetails")));
		    		jsonObjectDetail.put("chassisDisp", 	EnjoyUtils.nullToStr(rs.getString("chassisDisp")));
		    		jsonObjectDetail.put("EngineNoDisp", 	EnjoyUtils.nullToStr(rs.getString("EngineNoDisp")));
			    	jsonObjectDetail.put("remark",          "มีรายละเอียดส่งเสริมการขายที่" + " " +
			    										    EnjoyUtils.nullToStr(rs.getString("invoiceIdAddSales")));
		    	} else {
		    		jsonObjectDetail.put("motorcyclesDisp", EnjoyUtils.nullToStr(rs.getString("remark")));
		    		jsonObjectDetail.put("chassisDisp", 	" ");
		    		jsonObjectDetail.put("EngineNoDisp", 	" ");
			    	jsonObjectDetail.put("remark",          " - ");
		    	}
		    	jsonObjectDetail.put("priceAmount",     EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("priceAmount"),"0"),2));
		    	jsonObjectDetail.put("vatAmount",       EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("vatAmount"),"0"),2));
		    	totleAmount   = Double.parseDouble(EnjoyUtils.nullToStr(rs.getString("totalAmount"),"0"));
		    	summaryAmount = summaryAmount + totleAmount;
		    	jsonObjectDetail.put("totalAmount",     EnjoyUtils.convertFloatToDisplay(String.valueOf(totleAmount),2));
		    	listJSONArray.add(jsonObjectDetail);
		    }
		    jsonObjectMain.put("invoicelist", 		listJSONArray);
		    jsonObjectMain.put("CompanyName",    	userBean.getCompanyName());
		    jsonObjectMain.put("CompanyAddress", 	userBean.getCompanyAddress());		    
		    jsonObjectMain.put("SummaryAmount",  	EnjoyUtils.convertFloatToDisplay(String.valueOf(summaryAmount),2));		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[InvoicedetailsDao][SummarySalePDF][End]");
		}
		
		return jsonObjectMain;
	}
	
	public JSONObject InvoiceSalePDF(String 		 invoiceId,
			 						 UserDetailsBean userBean){
		System.out.println("[InvoicedetailsDao][InvoiceSalePDF][Begin]");
		String 				sql			 	= null;
		ResultSet 			rs 				= null;
		JSONObject 			jsonObjectDetail= null;
		String				cusCode		    = "";
		String				flagAddSales	= "";
		String				invoiceIdAddSales = "";
		EnjoyConectDbs 		db 				= null;
		try{
			db  = new EnjoyConectDbs();
			sql = " select t.* from (select  i.invoiceId invoiceId"
					+ " , CONCAT(c.cusName, ' ', c.cusSurname) cusName"
					+ " , CONCAT(c.houseNumber, ' หมู่ที่ ' , c.mooNumber, ' ถนน ' , c.streetName, ' ตำบล ' , i.EngineNoDisp, ' อำเภอ ' , i.EngineNoDisp, ' จังหวัด ' , i.EngineNoDisp ) cusAddress"
					+ " , b.brandName brandName"
					+ " , m.model model"
					+ " , CONCAT(m.chassis, i.chassisDisp ) chassisDisp"
					+ " , CONCAT(m.engineNo,i.EngineNoDisp ) EngineNoDisp"
					+ " , i.color color"
					+ " , i.size size"
					+ " , i.priceAmount priceAmount"
					+ " , i.vatAmount vatAmount"
					+ " , i.totalAmount totalAmount"
					+ " , i.commAmount commAmount"
					+ " , i.invoiceDate invoiceDate"
					+ " , i.remark remark"
					+ " , i.cusCode cusCode"
					+ " , c.idNumber idNumber"
					+ " , i.flagAddSales flagAddSales"
					+ " , i.invoiceIdAddSales invoiceIdAddSales"
					+ " , i.flagCredit flagCredit"
					+ " , i.creditAmount creditAmount"
					+ " from  invoicedetails i, customer c, motorcyclesdetails m, branddetails b"
					+ " where c.cusCode         = i.cusCode"
					+ "  and m.motorcyclesCode  = i.motorcyclesCode"
					+ "  and b.brandCode	    = m.brandCode) t where 1=1 "
					+ "  and t.invoiceId 		= '" + invoiceId + "'";

			System.out.println("[InvoicedetailsDao][InvoiceSalePDF] sql :: " + sql);
		    rs 				= db.executeQuery(sql);
		    
		    while(rs.next()){
		    	jsonObjectDetail 	= new JSONObject();
		    	jsonObjectDetail.put("invoiceId",       EnjoyUtils.nullToStr(rs.getString("invoiceId")));
		    	jsonObjectDetail.put("invoiceDate",     EnjoyUtils.displayDateThai(rs.getString("invoiceDate")));
		    	cusCode 			= rs.getString("cusCode");
			    flagAddSales 		= rs.getString("flagAddSales");
			    invoiceIdAddSales 	= rs.getString("invoiceIdAddSales");
		    	jsonObjectDetail.put("cusNameDisp",     EnjoyUtils.nullToStr(rs.getString("cusName")));
		    	jsonObjectDetail.put("idNumber",     	EnjoyUtils.nullToStr(rs.getString("idNumber")));
	    		jsonObjectDetail.put("brandName", 		EnjoyUtils.nullToStr(rs.getString("brandName")));
		    	jsonObjectDetail.put("model",           EnjoyUtils.nullToStr(rs.getString("model")));
		    	jsonObjectDetail.put("chassisDisp",     EnjoyUtils.nullToStr(rs.getString("chassisDisp")));
		    	jsonObjectDetail.put("engineNoDisp",    EnjoyUtils.nullToStr(rs.getString("engineNoDisp")));
		    	jsonObjectDetail.put("color",    		EnjoyUtils.nullToStr(rs.getString("color")));
		    	jsonObjectDetail.put("size",    		EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("size"),"0"),0));
		    	jsonObjectDetail.put("priceAmount",     EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("priceAmount"),"0"),2));
		    	jsonObjectDetail.put("vatAmount",       EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("vatAmount"),"0")  ,2));
		    	jsonObjectDetail.put("totalAmount",     EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("totalAmount"),"0"),2));
		    	jsonObjectDetail.put("totalAmountThai", EnjoyUtils.displayAmountThai(EnjoyUtils.nullToStr(rs.getString("totalAmount"),"0")));
		    	jsonObjectDetail.put("remark",     		EnjoyUtils.nullToStr(rs.getString("remark")));
		    	jsonObjectDetail.put("CompanyName",     userBean.getCompanyName());
		    	jsonObjectDetail.put("tel",     		userBean.getTel());
		    	jsonObjectDetail.put("CompanyAddress",  userBean.getCompanyAddress());	
		    	jsonObjectDetail.put("branchName",  	userBean.getBranchName());	
		    	jsonObjectDetail.put("tin",  			userBean.getTin());	
		    	jsonObjectDetail.put("invoiceIdAddSales", EnjoyUtils.nullToStr(rs.getString("invoiceIdAddSales")));		    
			    jsonObjectDetail.put("flagAddSales",    rs.getString("flagAddSales"));		    
			    jsonObjectDetail.put("commAmount",      EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("commAmount"),"0"),2));		    
			    jsonObjectDetail.put("flagCredit",  	rs.getString("flagCredit"));		    
			    jsonObjectDetail.put("creditAmount",  	EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("creditAmount"),"0"),2));		    
			    jsonObjectDetail.put("creditAmountThai",EnjoyUtils.displayAmountThai(EnjoyUtils.nullToStr(rs.getString("creditAmount"),"0")));	
		    }
		    if (! cusCode.equals("")) { 
		    	jsonObjectDetail.put("cusAddress",  findCustomerById(cusCode));
		    }	
		    if (flagAddSales.equals("Y")) { 
		    	InvoiceAddSalesPDF(invoiceIdAddSales , jsonObjectDetail);
		    }	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[InvoicedetailsDao][InvoiceSalePDF][End]");
		}
		
		return jsonObjectDetail;
	}

	private String findCustomerById(String cusCode){
		System.out.println("[InvoicedetailsDao][findCustomerById][Begin] :" + cusCode);
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null; 
		StringBuilder       address             = null;           
		EnjoyConectDbs 		db 					= null;
		
		try{ 
			db  = new EnjoyConectDbs();
			sql = "SELECT * FROM customer a  LEFT JOIN  subdistrict s ON a.subdistrictCode=s.subdistrictId LEFT JOIN district d "
				+ "ON a.districtCode=d.districtId LEFT JOIN province p ON a.provinceCode=p.provinceId where  cusStatus = 'A'"			
				+ " and cusCode = '" + cusCode + "'";		   
			System.out.println("[CustomerDao][findCustomer] sql :: " + sql);
			
		    rs 			= db.executeQuery(sql);
		    while(rs.next()){
				address  =  new StringBuilder();
				address.append(EnjoyUtils.nullToStr(rs.getString("houseNumber"))); 
				if (! EnjoyUtils.nullToStr(rs.getString("buildingName")).equals("")) {
					address.append(" อาคาร  ").append(EnjoyUtils.nullToStr(rs.getString("buildingName")));
				}	
				if (! EnjoyUtils.nullToStr(rs.getString("mooNumber")).equals("")) {
					address.append(" หมู่ที่  ").append(EnjoyUtils.nullToStr(rs.getString("mooNumber")));
				}	
				if (! EnjoyUtils.nullToStr(rs.getString("streetName")).equals("")) {
					address.append(" ถนน  ").append(EnjoyUtils.nullToStr(rs.getString("streetName")));
				}	
//				address.append(" ถนน   ").append(EnjoyUtils.nullToStr(rs.getString("streetName"))); 
				address.append(" ตำบล/แขวง  ").append(EnjoyUtils.nullToStr(rs.getString("subdistrictName")));
				address.append(" อำเภอ/เขต  ").append(EnjoyUtils.nullToStr(rs.getString("districtName"))); 
				address.append(" จังหวัด  ").append(EnjoyUtils.nullToStr(rs.getString("provinceName")));  
		    	
		    } 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[InvoicedetailsDao][findCustomerById][End]");
		}		
		return address.toString();
	}
	
	public void InvoiceAddSalesPDF(String 	  invoiceIdAddSales, 
								   JSONObject jsonObjectDetail){
		System.out.println("[InvoicedetailsDao][InvoiceAddSalesPDF][Begin]");
		String 				sql			 	= null;
		ResultSet 			rs 				= null;
		EnjoyConectDbs 		db 				= null;
		try{
			db  = new EnjoyConectDbs();
			sql = " select  i.invoiceId invoiceId"
					+ " , i.priceAmount priceAmount"
					+ " , i.vatAmount vatAmount"
					+ " , i.totalAmount totalAmount"
					+ " , i.remark remark"
					+ " from  invoicedetails i "
					+ " where i.invoiceId 		= '" + invoiceIdAddSales + "'";

			System.out.println("[InvoicedetailsDao][InvoiceAddSalesPDF] sql :: " + sql);
		    rs 				= db.executeQuery(sql);
		    
		    while(rs.next()){
		    	jsonObjectDetail.put("addSalesPriceAmount",     EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("priceAmount"),"0"),2));
		    	jsonObjectDetail.put("addSalesVatAmount",       EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("vatAmount"),"0"),2));
		    	jsonObjectDetail.put("addSalesTotalAmount",     EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("totalAmount"),"0"),2));
		    	jsonObjectDetail.put("addSalesTotalAmountThai", EnjoyUtils.displayAmountThai(EnjoyUtils.nullToStr(rs.getString("totalAmount"),"0")));
		    	jsonObjectDetail.put("addSalesRemark", 			EnjoyUtils.nullToStr(rs.getString("remark")));
		    }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[InvoicedetailsDao][InvoiceAddSalesPDF][End]");
		}
	}
}
