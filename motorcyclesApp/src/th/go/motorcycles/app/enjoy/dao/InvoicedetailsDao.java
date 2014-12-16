package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class InvoicedetailsDao {
	private EnjoyConectDbs db = null;	
	public InvoicedetailsDao(){
		db = new EnjoyConectDbs();
	}
	
	public JSONObject SummarySalePDF(String userId, String pass){
		System.out.println("[InvoicedetailsDao][SummarySalePDF][Begin]");
		String 				sql			 	= null;
		ResultSet 			rs 				= null;
		JSONObject 			jsonObjectMain  = null;
		JSONObject 			jsonObjectDetail= null;
		JSONArray 			listJSONArray 	= null;
		
		try{
			sql 		= " Select t1.invoiceId, CONCAT(t2.cusName, ' ', t2.cusName) as cusNameDisp, ";
			sql 		= sql + " CONCAT(t4.brandName, 'รุ่น ', t3.model, ' เลขตัวถัง  ', t1.chassisDisp, 'เลขเครื่องยนต์  ', t1.EngineNoDisp) as motorcyclesDisp, ";
			sql 		= sql + " t1.priceAmount, t1.vatAmount, t1.priceAmount + t1.vatAmount as totalAmount, t1.remark"; 
			sql 		= sql + " FROM invoicedetails t1 , customer t2 , motorcyclesdetails t3 , branddetails t4 ";
			sql 		= sql + " WHERE t1.cusCode = t2.cusCode ";
			sql 		= sql + " AND t1.motorcyclesCode = t3.motorcyclesCode ";
			sql 		= sql + " AND t3.brandCode = t4.brandCode	";		

			System.out.println("[InvoicedetailsDao][SummarySalePDF] sql :: " + sql);
		    rs 				= this.db.executeQuery(sql);
		    jsonObjectMain 	= new JSONObject();
		    listJSONArray 	= new JSONArray();
		    
		    while(rs.next()){
		    	jsonObjectDetail 	= new JSONObject();
		    	jsonObjectDetail.put("invoiceId",       rs.getString("invoiceId"));
		    	jsonObjectDetail.put("cusNameDisp",     rs.getString("cusNameDisp"));
		    	if (rs.getString("motorcyclesDisp") != null) {
		    		jsonObjectDetail.put("motorcyclesDisp", rs.getString("motorcyclesDisp"));
			    	jsonObjectDetail.put("remark",          rs.getString("remark"));
		    	} else {
		    		jsonObjectDetail.put("motorcyclesDisp", rs.getString("remark"));
			    	jsonObjectDetail.put("remark",          " - ");
		    	}
		    	jsonObjectDetail.put("priceAmount",     EnjoyUtils.convertFloatToDisplay(rs.getString("priceAmount"),2));
		    	jsonObjectDetail.put("vatAmount",       EnjoyUtils.convertFloatToDisplay(rs.getString("vatAmount"),2));
		    	jsonObjectDetail.put("totalAmount",     EnjoyUtils.convertFloatToDisplay(rs.getString("totalAmount"),2));
		    	listJSONArray.add(jsonObjectDetail);
		    }
		    jsonObjectMain.put("invoicelist", listJSONArray);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[InvoicedetailsDao][SummarySalePDF][End]");
		}
		
		return jsonObjectMain;
	}
	
	public JSONObject InvoiceSalePDF(String invoiceId){
		System.out.println("[InvoicedetailsDao][InvoiceSalePDF][Begin]");
		String 				sql			 	= null;
		ResultSet 			rs 				= null;
//		JSONObject 			jsonObjectMain  = null;
		JSONObject 			jsonObjectDetail= null;
//		JSONArray 			listJSONArray 	= null;
		
		try{
			sql 		= " Select t1.invoiceId, t3.brandName, t2.model, t1.chassisDisp, t1.EngineNoDisp, t1.size, ";
			sql 		= sql + " t1.priceAmount, t1.vatAmount, t1.priceAmount + t1.vatAmount as totalAmount "; 
			sql 		= sql + " FROM invoicedetails t1 , motorcyclesdetails t2, branddetails t3 ";
			sql 		= sql + " WHERE t1.motorcyclesCode = t2.motorcyclesCode ";
			sql 		= sql + " AND t2.brandCode = t3.brandCode ";		
			sql 		= sql + " AND t1.invoiceId = '" + invoiceId + "'";		

			System.out.println("[InvoicedetailsDao][SummarySalePDF] sql :: " + sql);
		    rs 				= this.db.executeQuery(sql);
//		    jsonObjectMain 	= new JSONObject();
//		    listJSONArray 	= new JSONArray();
		    
		    while(rs.next()){
		    	jsonObjectDetail 	= new JSONObject();
		    	jsonObjectDetail.put("invoiceId",       rs.getString("invoiceId"));
	    		jsonObjectDetail.put("brandName", 		rs.getString("brandName"));
		    	jsonObjectDetail.put("model",           rs.getString("model"));
		    	jsonObjectDetail.put("chassisDisp",     rs.getString("chassisDisp"));
		    	jsonObjectDetail.put("EngineNoDisp",    rs.getString("EngineNoDisp"));
		    	jsonObjectDetail.put("Size",    		EnjoyUtils.convertFloatToDisplay(rs.getString("Size"),0));
		    	jsonObjectDetail.put("priceAmount",     EnjoyUtils.convertFloatToDisplay(rs.getString("priceAmount"),2));
		    	jsonObjectDetail.put("vatAmount",       EnjoyUtils.convertFloatToDisplay(rs.getString("vatAmount"),2));
		    	jsonObjectDetail.put("totalAmount",     EnjoyUtils.convertFloatToDisplay(rs.getString("totalAmount"),2));
//		    	listJSONArray.add(jsonObjectDetail);
		    }
//		    jsonObjectMain.put("invoicelist", listJSONArray);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[InvoicedetailsDao][InvoiceSalePDF][End]");
		}
		
		return jsonObjectDetail;
	}
}
