package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
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
	
	public List<SummarySaleDetailBean> searchSaleDetails(SummarySaleDetailForm form){
		System.out.println("[SummarySaleDetail][searchSaleDetails][Begin]");
		
		String 							sql			 		= null;
		String 							where			 	= "";
		ResultSet 						rs 					= null;
		SummarySaleDetailBean			bean				= null;
        String							invoiceId			= null;
        String							invoiceDateFrom		= null;
        String							invoiceDateTo		= null;
        String							brandName			= null;
        String							model				= null;
        String							cusName				= null;
        List<SummarySaleDetailBean> 	list 				= new ArrayList<SummarySaleDetailBean>();
		
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
			
			System.out.println("[SummarySaleDetail][searchSaleDetails] sql :: " + sql + where);
			
		    rs 			= this.db.executeQuery(sql + where);
		    
		    while(rs.next()){
		    	bean	= new SummarySaleDetailBean();
		    	
		    	bean.setInvoiceId			(EnjoyUtils.nullToStr(rs.getString("invoiceId")));
		    	bean.setCusName				(EnjoyUtils.nullToStr(rs.getString("cusName")));
		    	bean.setMotorcyclesdetails	(EnjoyUtils.nullToStr(rs.getString("motorcyclesdetails")));
		    	bean.setPriceAmount			(EnjoyUtils.nullToStr(rs.getString("priceAmount")));
		    	bean.setVatAmount			(EnjoyUtils.nullToStr(rs.getString("vatAmount")));
		    	bean.setCommAmount			(EnjoyUtils.nullToStr(rs.getString("commAmount")));
		    	bean.setRemark				(EnjoyUtils.nullToStr(rs.getString("remark")));
		    	
		    	list.add(bean);
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[SummarySaleDetail][searchSaleDetails][End]");
		}
		
		return list;
	}
}
