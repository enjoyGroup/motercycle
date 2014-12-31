package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.SummarySaleDetailBean;
import th.go.motorcycles.app.enjoy.form.SummarySaleDetailForm;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class SummarySaleDetailDao {	
	
	private EnjoyConectDbs db = null;
	
	public SummarySaleDetailDao(){
		db = new EnjoyConectDbs();
	}
	
	public void searchSaleDetails(SummarySaleDetailForm form){
		System.out.println("[SummarySaleDetail][searchSaleDetails][Begin]");
		
		String 											sql			 		= null;
		String 											where			 	= "";
		ResultSet 										rs 					= null;
		SummarySaleDetailBean							bean				= null;
        String											invoiceId			= null;
        String											invoiceDateFrom		= null;
        String											invoiceDateTo		= null;
        String											brandName			= null;
        String											model				= null;
        String											cusName				= null;
        List<SummarySaleDetailBean> 					list 				= new ArrayList<SummarySaleDetailBean>();
        HashMap<Integer, List<SummarySaleDetailBean>>	hashTable			= new HashMap<Integer, List<SummarySaleDetailBean>>();
        int												cou					= 0;
        int												pageNum				= 1;
        int												totalPage			= 0;
        String 											priceAmount 		= null;
        String 											vatAmount 			= null;
        String 											commAmount 			= null;
		
		try{
			invoiceId					= EnjoyUtils.nullToStr(form.getInvoiceId());
			invoiceDateFrom				= EnjoyUtils.nullToStr(form.getInvoiceDateFrom());
			invoiceDateTo				= EnjoyUtils.nullToStr(form.getInvoiceDateTo());
			brandName					= EnjoyUtils.nullToStr(form.getBrandName());
			model						= EnjoyUtils.nullToStr(form.getModel());
			cusName						= EnjoyUtils.nullToStr(form.getCusName());
			
			
			
			sql 		= " select t.* from (select  i.invoiceId invoiceId"
											+ " , CONCAT(c.cusName, ' ', c.cusSurname) cusName"
											+ " , CONCAT(b.brandName, ' รุ่น ' , m.model, ' เลขตัวถัง ' , i.chassisDisp, ' เลขเครื่องยนต์ ' , i.EngineNoDisp ) motorcyclesdetails"
											+ " , b.brandName brandName"
											+ " , m.model model"
											+ " , i.priceAmount priceAmount"
											+ " , i.vatAmount vatAmount"
											+ " , i.commAmount commAmount"
											+ " ,STR_TO_DATE(i.invoiceDate, '%Y%m%d') invoiceDate"
											+ " ,i.remark remark"
											+ " from  invoicedetails i, customer c, motorcyclesdetails m, branddetails b"
											+ " where c.cusCode         = i.cusCode"
											+ "  and m.motorcyclesCode  = i.motorcyclesCode"
											+ "  and i.chassisDisp is not null"
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
				
				invoiceDateFrom				= EnjoyUtils.dateFormat(invoiceDateFrom, "dd/MM/yyyy", "yyyyMMdd");
				invoiceDateTo				= EnjoyUtils.dateFormat(invoiceDateTo, "dd/MM/yyyy", "yyyyMMdd");
				
				System.out.println("[SummarySaleDetail][searchSaleDetails] invoiceDateFrom 	:: " + invoiceDateFrom);
				System.out.println("[SummarySaleDetail][searchSaleDetails] invoiceDateTo 	:: " + invoiceDateTo);
				
				where += " and invoiceDate BETWEEN STR_TO_DATE('" + invoiceDateFrom + "', '%Y%m%d') AND STR_TO_DATE('" + invoiceDateTo + "', '%Y%m%d')";
			}
			
			System.out.println("[SummarySaleDetail][searchSaleDetails] sql :: " + sql + where);
			
		    rs 			= this.db.executeQuery(sql + where);
		    
		    hashTable.put(pageNum, list);
		    while(rs.next()){
		    	bean	= new SummarySaleDetailBean();
		    	
		    	bean.setInvoiceId			(EnjoyUtils.nullToStr(rs.getString("invoiceId")));
		    	bean.setCusName				(EnjoyUtils.nullToStr(rs.getString("cusName")));
		    	bean.setMotorcyclesdetails	(EnjoyUtils.nullToStr(rs.getString("motorcyclesdetails")));
		    	
		    	priceAmount 		= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("priceAmount")), 2);
		        vatAmount 			= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("vatAmount")), 2);
		        commAmount 			= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("commAmount")), 2);
		    	
		    	bean.setPriceAmount			(priceAmount);
		    	bean.setVatAmount			(vatAmount);
		    	bean.setCommAmount			(commAmount);
		    	bean.setRemark				(EnjoyUtils.nullToStr(rs.getString("remark")));
		    	
		    	if(cou==10){
		    		cou 	= 0;
		    		list 	= new ArrayList<SummarySaleDetailBean>();
		    		pageNum++;
		    	}
		    	
		    	list.add(bean);
		    	hashTable.put(pageNum, list);
		    	cou++;
		    	
		    }
		    
		    totalPage = hashTable.size();
		    form.setTotalPage(totalPage);
		    form.setHashTable(hashTable);
		    
		    System.out.println("[SummarySaleDetail][searchSaleDetails] totalPage :: " + totalPage);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[SummarySaleDetail][searchSaleDetails][End]");
		}
		
//		return list;
	}
	
	public List<String> nameSurnameList(String cusName){
		System.out.println("[SummarySaleDetail][nameSurnameList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		
		try{
			sql 		= "select t.cusName from ( select CONCAT(cusName, ' ', cusSurname) cusName from customer) t where t.cusName like ('"+cusName+"%') order by t.cusName asc limit 10 ";
			
			System.out.println("[SummarySaleDetail][nameSurnameList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("cusName")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[SummarySaleDetail][nameSurnameList][End]");
		}
		
		return list;
	}
}
