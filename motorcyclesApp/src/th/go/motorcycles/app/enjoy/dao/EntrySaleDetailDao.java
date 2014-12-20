package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.bean.ProductBean;
import th.go.motorcycles.app.enjoy.form.EntrySaleDetailForm;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class EntrySaleDetailDao {	
	
	private EnjoyConectDbs db = null;
	
	final static String CUSTOMER = "customer";
	
	public EntrySaleDetailDao(){
		db = new EnjoyConectDbs();
	}
	
	public CustomerBean getCustomerDetail(String cusCode, CustomerBean customerBean){
		System.out.println("[EntrySaleDetailDao][getCustomerDetail][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;
		AddressDao			addressDao			= null;
		String				provinceCode		= null;
		String				districtCode		= null;
		String				subdistrictCode		= null;
		String				provinceName		= null;
		String				districtName		= null;
		String				subdistrictName		= null;
		
		try{
			addressDao 	= new AddressDao();
			sql 		= "SELECT * FROM " + CUSTOMER +" where  cusStatus = '"+"A"+"' and cusCode = '"+cusCode+"'";
			
			System.out.println("[EntrySaleDetailDao][getCustomerDetail] sql :: " + sql); 
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){ 
		    	customerBean.setCusCode(EnjoyUtils.nullToStr(rs.getString("cusCode")));
		    	customerBean.setCustName(EnjoyUtils.nullToStr(rs.getString("cusName"))); 
		    	customerBean.setCustSurname(EnjoyUtils.nullToStr(rs.getString("cusSurname"))); 
				customerBean.setHouseNumber(EnjoyUtils.nullToStr(rs.getString("houseNumber")));
				customerBean.setMooNumber(EnjoyUtils.nullToStr(rs.getString("mooNumber")));
				customerBean.setSoiName(EnjoyUtils.nullToStr(rs.getString("SoiName")));
				customerBean.setStreetName(EnjoyUtils.nullToStr(rs.getString("streetName")));
				
				provinceCode 		= EnjoyUtils.nullToStr(rs.getString("provinceCode"));
				districtCode 		= EnjoyUtils.nullToStr(rs.getString("districtCode"));
				subdistrictCode 	= EnjoyUtils.nullToStr(rs.getString("subdistrictCode"));
				
				provinceName		= addressDao.getProvinceName(provinceCode);
				districtName		= addressDao.getDistrictName(districtCode);
				subdistrictName		= addressDao.getSubdistrictName(subdistrictCode);
				
				customerBean.setProvinceCode(provinceCode);
				customerBean.setDistrictCode(districtCode);
				customerBean.setSubdistrictCode(subdistrictCode);
				
				customerBean.setProvinceName(provinceName);
				customerBean.setDistrictName(districtName);
				customerBean.setSubdistrictName(subdistrictName);
				
				customerBean.setIdType(EnjoyUtils.nullToStr(rs.getString("idType")));
				customerBean.setIdNumber(EnjoyUtils.nullToStr(rs.getString("idNumber")));
				customerBean.setCusStatus(EnjoyUtils.nullToStr(rs.getString("cusStatus")));
		    }	 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][getCustomerDetail][End]");
		}
		
		return customerBean;
	}
	
	public CustomerBean getCustomerDetail(String cusName, String cusSurname, CustomerBean customerBean){
		System.out.println("[EntrySaleDetailDao][getCustomerDetail][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;
		AddressDao			addressDao			= null;
		String				provinceCode		= null;
		String				districtCode		= null;
		String				subdistrictCode		= null;
		String				provinceName		= null;
		String				districtName		= null;
		String				subdistrictName		= null;
		
		try{
			addressDao 	= new AddressDao();
			sql 		= "SELECT * FROM " + CUSTOMER +" where  cusStatus = '"+"A"+"' and cusName = '"+cusName+"' and cusSurname = '"+cusSurname+"'";
			
			System.out.println("[EntrySaleDetailDao][getCustomerDetail] sql :: " + sql); 
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){ 
		    	customerBean.setCusCode(EnjoyUtils.nullToStr(rs.getString("cusCode")));
		    	customerBean.setCustName(EnjoyUtils.nullToStr(rs.getString("cusName"))); 
		    	customerBean.setCustSurname(EnjoyUtils.nullToStr(rs.getString("cusSurname"))); 
				customerBean.setHouseNumber(EnjoyUtils.nullToStr(rs.getString("houseNumber")));
				customerBean.setMooNumber(EnjoyUtils.nullToStr(rs.getString("mooNumber")));
				customerBean.setSoiName(EnjoyUtils.nullToStr(rs.getString("SoiName")));
				customerBean.setStreetName(EnjoyUtils.nullToStr(rs.getString("streetName")));
				
				provinceCode 		= EnjoyUtils.nullToStr(rs.getString("provinceCode"));
				districtCode 		= EnjoyUtils.nullToStr(rs.getString("districtCode"));
				subdistrictCode 	= EnjoyUtils.nullToStr(rs.getString("subdistrictCode"));
				
				provinceName		= addressDao.getProvinceName(provinceCode);
				districtName		= addressDao.getDistrictName(districtCode);
				subdistrictName		= addressDao.getSubdistrictName(subdistrictCode);
				
				customerBean.setProvinceCode(provinceCode);
				customerBean.setDistrictCode(districtCode);
				customerBean.setSubdistrictCode(subdistrictCode);
				
				customerBean.setProvinceName(provinceName);
				customerBean.setDistrictName(districtName);
				customerBean.setSubdistrictName(subdistrictName);
				
				customerBean.setIdType(EnjoyUtils.nullToStr(rs.getString("idType")));
				customerBean.setIdNumber(EnjoyUtils.nullToStr(rs.getString("idNumber")));
				customerBean.setCusStatus(EnjoyUtils.nullToStr(rs.getString("cusStatus")));
		    }	 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][getCustomerDetail][End]");
		}
		
		return customerBean;
	}
	
	public void setSaleDetail(String invoiceId, EntrySaleDetailForm form){
		System.out.println("[EntrySaleDetailDao][setSaleDetail][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;
		ProductBean			productBean			= null;
		CustomerBean 		customerBean 		= null;
		String				cusCode				= null;
		String				priceAmount			= null;
		String				vatAmount			= null;
		String				commAmount			= null;
		
		try{
			customerBean 	= new CustomerBean();
			productBean		= new ProductBean();
			
			sql 		= "select  i.invoiceId invoiceId" 
								+ " , c.cusCode cusCode"
								+ " , b.brandName brandName" 
								+ " , m.model model "
								+ " , i.chassisDisp chassis"
								+ " , i.EngineNoDisp engineNo"
								+ " , i.size size"
								+ " , i.priceAmount priceAmount" 
								+ " , i.vatAmount vatAmount" 
								+ " , i.commAmount commAmount" 
								+ " , i.remark remark"
								+ " , i.flagAddSales flagAddSales"
								+ " ,STR_TO_DATE(i.invoiceDate, '%Y%m%d') invoiceDate" 
							 + " from  invoicedetails i, customer c, motorcyclesdetails m, branddetails b" 
							   + " where c.cusCode          = i.cusCode"  
							     + " and m.motorcyclesCode  = i.motorcyclesCode"  
							     + " and b.brandCode	    = m.brandCode"
							     + " and i.invoiceId        = '"+invoiceId+"'";
			
			System.out.println("[EntrySaleDetailDao][setSaleDetail] sql :: " + sql); 
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){ 
		    	
		    	form.setInvoiceId(invoiceId);
		    	form.setRecordAddDate(EnjoyUtils.nullToStr(rs.getString("invoiceDate")));
		    	
		    	cusCode 	= EnjoyUtils.nullToStr(rs.getString("cusCode"));
		    	
		    	productBean.setBrandName(EnjoyUtils.nullToStr(rs.getString("brandName")));
		    	productBean.setModel(EnjoyUtils.nullToStr(rs.getString("model")));
		    	productBean.setChassis(EnjoyUtils.nullToStr(rs.getString("chassis")));
		    	productBean.setEngineNo(EnjoyUtils.nullToStr(rs.getString("engineNo")));
		    	productBean.setSize(EnjoyUtils.nullToStr(rs.getString("size")));
		    	
		    	form.setProductBean(productBean);
		    	
		    	priceAmount = EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("priceAmount")), 2);
		    	vatAmount 	= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("vatAmount")), 2);
		    	commAmount 	= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("commAmount")), 2);
		    	
		    	form.setPriceAmount(priceAmount);
		    	form.setVatAmount(vatAmount);
		    	form.setCommAmount(commAmount);
		    	form.setRemark(EnjoyUtils.nullToStr(rs.getString("remark")));
		    	form.setFlagAddSales(EnjoyUtils.nullToStr(rs.getString("flagAddSales")));
		    	
		    }	 
		    
		    if(!cusCode.equals("")){
	    		this.getCustomerDetail(cusCode, customerBean);
	    		form.setCustomerBean(customerBean);
	    	}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][setSaleDetail][End]");
		}
	}
	
	public List<String> cusCodeList(String cusCode){
		System.out.println("[EntrySaleDetailDao][cusCodeList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		
		try{
			sql 		= " select cusCode from customer where cusCode like ('"+cusCode+"%') order by cusCode asc limit 10 ";
			
			System.out.println("[EntrySaleDetailDao][cusCodeList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("cusCode")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][cusCodeList][End]");
		}
		
		return list;
	}
	
	public List<String> custNameList(String custName){
		System.out.println("[EntrySaleDetailDao][custNameList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		
		try{
			sql 		= " select cusName from customer where cusName like ('"+custName+"%') order by cusName asc limit 10 ";
			
			System.out.println("[EntrySaleDetailDao][custNameList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("cusName")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][custNameList][End]");
		}
		
		return list;
	}
	
	public List<String> custSurnameList(String custName, String custSurname){
		System.out.println("[EntrySaleDetailDao][custSurnameList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		
		try{
			
			sql 		= "select cusCode from customer where cusName = '"+custName+"'";
			
		    if(custName.equals("")){
		    	sql 		= " select cusSurname from customer where cusSurname like ('"+custSurname+"%') order by cusSurname asc limit 10 ";
		    }else{
		    	sql 		= " select cusSurname from customer where cusName = '"+custName+"' and cusSurname like ('"+custSurname+"%') order by cusSurname asc limit 10 ";
		    }
			System.out.println("[EntrySaleDetailDao][custSurnameList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("cusSurname")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][custSurnameList][End]");
		}
		
		return list;
	}
	
	public List<String> brandNameList(String brandName){
		System.out.println("[EntrySaleDetailDao][brandNameList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		
		try{
			sql 		= " select brandName from branddetails where brandName like ('"+brandName+"%') order by brandName asc limit 10 ";
			
			System.out.println("[EntrySaleDetailDao][brandNameList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("brandName")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][brandNameList][End]");
		}
		
		return list;
	}
	
	public List<String> modelList(String brandName, String model){
		System.out.println("[EntrySaleDetailDao][modelList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
        String							brandCode			= null;
		
		try{
			
			if(!brandName.equals("")){
				sql 		= " select brandCode from branddetails where brandName = '" + brandName + "'";
				
				System.out.println("[EntrySaleDetailDao][modelList] brandCode sql :: " + sql);
				
			    rs 			= this.db.executeQuery(sql);
			    while(rs.next())brandCode = EnjoyUtils.nullToStr(rs.getString("brandCode"));
			}
			
			if(brandCode == null){
				sql 		= " select model from motorcyclesdetails where model like ('"+model+"%') order by model asc limit 10 ";
			}else{
				sql 		= " select model from motorcyclesdetails where brandCode = '"+brandCode+"' and model like ('"+model+"%') order by model asc limit 10 ";
			}
			
			System.out.println("[EntrySaleDetailDao][modelList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("model")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][modelList][End]");
		}
		
		return list;
	}
	
	public ProductBean productDetail(String brandName, String model, ProductBean productBean){
		System.out.println("[EntrySaleDetailDao][productDetail][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
		String							brandCode			= null;
		
		try{
			sql 		= " select brandCode from branddetails where brandName = '" + brandName + "'";
			
			System.out.println("[EntrySaleDetailDao][productDetail] sql branddetails :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    while(rs.next())brandCode = EnjoyUtils.nullToStr(rs.getString("brandCode"));
		    
		    if(brandCode!=null && !brandCode.equals("")){
		    	sql 		= " select m.model, m.chassis, m.engineNo, m.size, b.brandName"
    					+ " from motorcyclesdetails m, branddetails b"
    					+ " where m.model = '"+model+"'"
    						+ " and m.brandCode = '"+brandCode+"'"
    						+ " and b.brandCode = m.brandCode";
		    }else{
		    	sql 		= " select m.model, m.chassis, m.engineNo, m.size, b.brandName"
		    					+ " from motorcyclesdetails m, branddetails b"
		    					+ " where m.model = '"+model+"'"
		    						+ " and b.brandCode = m.brandCode";
		    }
		    
		    System.out.println("[EntrySaleDetailDao][productDetail] sql branddetails :: " + sql);
			
			rs 			= this.db.executeQuery(sql);
			while(rs.next()){
				
				System.out.println("[EntrySaleDetailDao][productDetail] brandName 	:: " + EnjoyUtils.nullToStr(rs.getString("brandName")));
				System.out.println("[EntrySaleDetailDao][productDetail] model 		:: " + EnjoyUtils.nullToStr(rs.getString("model")));
				System.out.println("[EntrySaleDetailDao][productDetail] chassis 	:: " + EnjoyUtils.nullToStr(rs.getString("chassis")));
				System.out.println("[EntrySaleDetailDao][productDetail] engineNo 	:: " + EnjoyUtils.nullToStr(rs.getString("engineNo")));
				System.out.println("[EntrySaleDetailDao][productDetail] size 		:: " + EnjoyUtils.nullToStr(rs.getString("size")));
				
				productBean.setBrandName(EnjoyUtils.nullToStr(rs.getString("brandName")));
				productBean.setModel(EnjoyUtils.nullToStr(rs.getString("model")));
				productBean.setChassis(EnjoyUtils.nullToStr(rs.getString("chassis")));
				productBean.setEngineNo(EnjoyUtils.nullToStr(rs.getString("engineNo")));
				productBean.setSize(EnjoyUtils.nullToStr(rs.getString("size")));
			}
		    
		    
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][productDetail][End]");
		}
		
		return productBean;
	}
	
}
