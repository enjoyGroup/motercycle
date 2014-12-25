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
	private EnjoyConectDbs db = null;	
	public InvoicedetailsDao(){
		db = new EnjoyConectDbs();
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
		
		try{
			sql = " select t.* from (select  i.invoiceId invoiceId"
					+ " , CONCAT(c.cusName, ' ', c.cusSurname) cusName"
					+ " , CONCAT(b.brandName, ' รุ่น ' , m.model, ' สี ' , i.color ) motorcyclesdetails"
					+ " , i.chassisDisp chassisDisp"
					+ " , i.EngineNoDisp EngineNoDisp"
					+ " , b.brandName brandName"
					+ " , m.model model"
					+ " , i.priceAmount priceAmount"
					+ " , i.vatAmount vatAmount"
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
			sql = sql + where;
			
			System.out.println("[InvoicedetailsDao][SummarySalePDF] sql :: " + sql);
		    rs 				= this.db.executeQuery(sql);
		    jsonObjectMain 	= new JSONObject();
		    listJSONArray 	= new JSONArray();
		    
		    while(rs.next()){
		    	jsonObjectDetail 	= new JSONObject();
		    	jsonObjectDetail.put("invoiceId",       rs.getString("invoiceId"));
		    	jsonObjectDetail.put("cusNameDisp",     rs.getString("cusName"));
		    	if (rs.getString("motorcyclesdetails") != null) {
		    		jsonObjectDetail.put("motorcyclesDisp", rs.getString("motorcyclesdetails"));
		    		jsonObjectDetail.put("chassisDisp", 	rs.getString("chassisDisp"));
		    		jsonObjectDetail.put("EngineNoDisp", 	rs.getString("EngineNoDisp"));
			    	jsonObjectDetail.put("remark",          EnjoyUtils.nullToStr(rs.getString("remark")) + " " +
			    										    EnjoyUtils.nullToStr(rs.getString("invoiceIdAddSales")));
		    	} else {
		    		jsonObjectDetail.put("motorcyclesDisp", rs.getString("remark"));
		    		jsonObjectDetail.put("chassisDisp", 	" ");
		    		jsonObjectDetail.put("EngineNoDisp", 	" ");
			    	jsonObjectDetail.put("remark",          " - ");
		    	}
		    	jsonObjectDetail.put("priceAmount",     EnjoyUtils.convertFloatToDisplay(rs.getString("priceAmount"),2));
		    	jsonObjectDetail.put("vatAmount",       EnjoyUtils.convertFloatToDisplay(rs.getString("vatAmount"),2));
		    	totleAmount   = Double.parseDouble(rs.getString("priceAmount")) + Double.parseDouble(rs.getString("vatAmount"));
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
		try{
			sql = " select t.* from (select  i.invoiceId invoiceId"
					+ " , CONCAT(c.cusName, ' ', c.cusSurname) cusName"
					+ " , CONCAT(c.houseNumber, ' หมู่ที่ ' , c.mooNumber, ' ถนน ' , c.streetName, ' ตำบล ' , i.EngineNoDisp, ' อำเภอ ' , i.EngineNoDisp, ' จังหวัด ' , i.EngineNoDisp ) cusAddress"
					+ " , b.brandName brandName"
					+ " , m.model model"
					+ " , i.chassisDisp chassisDisp"
					+ " , i.engineNoDisp engineNoDisp"
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
		    rs 				= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	jsonObjectDetail 	= new JSONObject();
		    	jsonObjectDetail.put("invoiceId",       rs.getString("invoiceId"));
		    	jsonObjectDetail.put("invoiceDate",     EnjoyUtils.displayDateThai(rs.getString("invoiceDate")));
		    	cusCode 			= rs.getString("cusCode");
			    flagAddSales 		= rs.getString("flagAddSales");
			    invoiceIdAddSales 	= rs.getString("invoiceIdAddSales");
		    	jsonObjectDetail.put("cusNameDisp",     rs.getString("cusName"));
		    	jsonObjectDetail.put("idNumber",     	rs.getString("idNumber"));
	    		jsonObjectDetail.put("brandName", 		rs.getString("brandName"));
		    	jsonObjectDetail.put("model",           rs.getString("model"));
		    	jsonObjectDetail.put("chassisDisp",     rs.getString("chassisDisp"));
		    	jsonObjectDetail.put("engineNoDisp",    rs.getString("engineNoDisp"));
		    	jsonObjectDetail.put("size",    		EnjoyUtils.convertFloatToDisplay(rs.getString("size"),0));
		    	jsonObjectDetail.put("priceAmount",     EnjoyUtils.convertFloatToDisplay(rs.getString("priceAmount"),2));
		    	jsonObjectDetail.put("vatAmount",       EnjoyUtils.convertFloatToDisplay(rs.getString("vatAmount")  ,2));
		    	jsonObjectDetail.put("totalAmount",     EnjoyUtils.convertFloatToDisplay(rs.getString("totalAmount"),2));
		    	jsonObjectDetail.put("totalAmountThai", EnjoyUtils.displayAmountThai(rs.getString("totalAmount")));
		    	jsonObjectDetail.put("remark",     		EnjoyUtils.nullToStr(rs.getString("remark")));
		    	jsonObjectDetail.put("CompanyName",     userBean.getCompanyName());
		    	jsonObjectDetail.put("tel",     		userBean.getTel());
		    	jsonObjectDetail.put("CompanyAddress",  userBean.getCompanyAddress());	
		    	jsonObjectDetail.put("branchName",  	userBean.getBranchName());	
		    	jsonObjectDetail.put("tin",  			userBean.getTin());	
		    	jsonObjectDetail.put("invoiceIdAddSales", rs.getString("invoiceIdAddSales"));		    
			    jsonObjectDetail.put("flagAddSales",    rs.getString("flagAddSales"));		    
			    jsonObjectDetail.put("commAmount",      EnjoyUtils.convertFloatToDisplay(rs.getString("commAmount"),2));		    
			    jsonObjectDetail.put("flagCredit",  	rs.getString("flagCredit"));		    
			    jsonObjectDetail.put("creditAmount",  	EnjoyUtils.convertFloatToDisplay(rs.getString("creditAmount"),2));		    
			    jsonObjectDetail.put("creditAmountThai",EnjoyUtils.displayAmountThai(rs.getString("creditAmount")));	
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
			System.out.println("[InvoicedetailsDao][InvoiceSalePDF][End]");
		}
		
		return jsonObjectDetail;
	}

	private String findCustomerById(String cusCode){
		System.out.println("[InvoicedetailsDao][findCustomerById][Begin] :" + cusCode);
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null; 
		StringBuilder       address             = null;           
		
		try{ 
			sql = "SELECT * FROM customer a  LEFT JOIN  subdistrict s ON a.subdistrictCode=s.subdistrictId LEFT JOIN district d "
				+ "ON a.districtCode=d.districtId LEFT JOIN province p ON a.provinceCode=p.provinceId where  cusStatus = 'A'"			
				+ " and cusCode = '" + cusCode + "'";		   
			System.out.println("[CustomerDao][findCustomer] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    while(rs.next()){
				address  =  new StringBuilder();
				address.append(EnjoyUtils.nullToStr(rs.getString("houseNumber"))); 
				address.append(" หมู่ที่  ").append(EnjoyUtils.nullToStr(rs.getString("mooNumber")));
				address.append(" ถนน   ").append(EnjoyUtils.nullToStr(rs.getString("streetName"))); 
				address.append(" ตำบล  ").append(EnjoyUtils.nullToStr(rs.getString("subdistrictName")));
				address.append(" อำเภอ  ").append(EnjoyUtils.nullToStr(rs.getString("districtName"))); 
				address.append(" จังหวัด  ").append(EnjoyUtils.nullToStr(rs.getString("provinceName")));  
		    	
		    } 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[InvoicedetailsDao][findCustomerById][End]");
		}		
		return address.toString();
	}
	
	public void InvoiceAddSalesPDF(String 	  invoiceIdAddSales, 
								   JSONObject jsonObjectDetail){
		System.out.println("[InvoicedetailsDao][InvoiceAddSalesPDF][Begin]");
		String 				sql			 	= null;
		ResultSet 			rs 				= null;
		try{
			sql = " select  i.invoiceId invoiceId"
					+ " , i.priceAmount priceAmount"
					+ " , i.vatAmount vatAmount"
					+ " , i.totalAmount totalAmount"
					+ " , i.remark remark"
					+ " from  invoicedetails i "
					+ " where i.invoiceId 		= '" + invoiceIdAddSales + "'";

			System.out.println("[InvoicedetailsDao][InvoiceAddSalesPDF] sql :: " + sql);
		    rs 				= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	jsonObjectDetail.put("addSalesPriceAmount",     EnjoyUtils.convertFloatToDisplay(rs.getString("priceAmount"),2));
		    	jsonObjectDetail.put("addSalesVatAmount",       EnjoyUtils.convertFloatToDisplay(rs.getString("vatAmount"),2));
		    	jsonObjectDetail.put("addSalesTotalAmount",     EnjoyUtils.convertFloatToDisplay(rs.getString("totalAmount"),2));
		    	jsonObjectDetail.put("addSalesTotalAmountThai", EnjoyUtils.displayAmountThai(rs.getString("totalAmount")));
		    	jsonObjectDetail.put("addSalesRemark", 			rs.getString("remark"));
		    }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[InvoicedetailsDao][InvoiceAddSalesPDF][End]");
		}
	}
}
