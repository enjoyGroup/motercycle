package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.bean.EntrySaleDetailBean;
import th.go.motorcycles.app.enjoy.bean.ProductBean;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.app.enjoy.form.EntrySaleDetailForm;
import th.go.motorcycles.app.enjoy.main.ConfigFile;
import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class EntrySaleDetailDao {	
	
//	private EnjoyConectDbs db = null;
	
	final static String CUSTOMER 		= "customer";
	final static String FILL_ZERO 		= ConfigFile.getPADING_INVOICE();
	
	public EntrySaleDetailDao(){
//		db = new EnjoyConectDbs();
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
		EnjoyConectDbs 		db 					= null;
		try{
			db    		= new EnjoyConectDbs();
			addressDao 	= new AddressDao();
			sql 		= "SELECT * FROM " + CUSTOMER +" where  cusStatus = '"+"A"+"' and cusCode = '"+cusCode+"'";
			
			System.out.println("[EntrySaleDetailDao][getCustomerDetail] sql :: " + sql); 
		    rs 			= db.executeQuery(sql);
		    
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
				
				customerBean.setPostcode(EnjoyUtils.nullToStr(rs.getString("postcode")));
				customerBean.setBuildingName(EnjoyUtils.nullToStr(rs.getString("buildingName")));
			 
		    }	 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
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
		EnjoyConectDbs 		db 					= null;
		
		try{
			db    		= new EnjoyConectDbs();
			addressDao 	= new AddressDao();
			sql 		= "SELECT * FROM " + CUSTOMER +" where  cusStatus = '"+"A"+"' and cusName = '"+cusName+"' and cusSurname = '"+cusSurname+"'";
			
			System.out.println("[EntrySaleDetailDao][getCustomerDetail] sql :: " + sql); 
		    rs 			= db.executeQuery(sql);
		    
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
				
				customerBean.setPostcode(EnjoyUtils.nullToStr(rs.getString("postcode")));
				customerBean.setBuildingName(EnjoyUtils.nullToStr(rs.getString("buildingName")));
		    }	 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[EntrySaleDetailDao][getCustomerDetail][End]");
		}
		
		return customerBean;
	}
	
	public CustomerBean getCustomerDetailByIdNumber(String idNumber, CustomerBean customerBean){
		System.out.println("[EntrySaleDetailDao][getCustomerDetailByIdNumber][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;
		AddressDao			addressDao			= null;
		String				provinceCode		= null;
		String				districtCode		= null;
		String				subdistrictCode		= null;
		String				provinceName		= null;
		String				districtName		= null;
		String				subdistrictName		= null;
		EnjoyConectDbs 		db 					= null;
		
		try{
			db    		= new EnjoyConectDbs();
			addressDao 	= new AddressDao();
			sql 		= "SELECT * FROM " + CUSTOMER +" where  cusStatus = '"+"A"+"' and idNumber = '"+idNumber+"'";
			
			System.out.println("[EntrySaleDetailDao][getCustomerDetailByIdNumber] sql :: " + sql); 
		    rs 			= db.executeQuery(sql);
		    
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
				
				customerBean.setPostcode(EnjoyUtils.nullToStr(rs.getString("postcode")));
				customerBean.setBuildingName(EnjoyUtils.nullToStr(rs.getString("buildingName")));
		    }	 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[EntrySaleDetailDao][getCustomerDetailByIdNumber][End]");
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
		String				totalAmount			= null;
		String				commAmount			= null;
		String				commTotalAmount		= null;
		String				commVatAmount		= null;
		String				creditTotalAmount	= null;
		String				creditVatAmount		= null;
		String				creditAmount		= null;
//		String 				size				= null;
		EnjoyConectDbs 		db 					= null;
		
		try{
			db    			= new EnjoyConectDbs();
			customerBean 	= new CustomerBean();
			productBean		= new ProductBean();
			
			sql 		= "select  i.invoiceId invoiceId" 
								+ " , c.cusCode cusCode"
								+ " , b.brandName brandName" 
								+ " , m.model model "
								+ " , m.chassis chassis"
								+ " , m.engineNo engineNo"
								+ " , i.chassisDisp chassisDisp"
								+ " , i.EngineNoDisp EngineNoDisp"
								+ " , i.size size"
								+ " , i.color color"
								+ " , i.invoiceIdAddSales invoiceIdAddSales"
								+ " , i.flagCredit flagCredit"
								+ " , i.creditTotalAmount creditTotalAmount"
								+ " , i.creditVatAmount creditVatAmount"
								+ " , i.creditAmount creditAmount"
								+ " , i.priceAmount priceAmount" 
								+ " , i.totalAmount totalAmount" 
								+ " , i.vatAmount vatAmount" 
								+ " , i.commTotalAmount commTotalAmount" 
								+ " , i.commVatAmount commVatAmount" 
								+ " , i.commAmount commAmount" 
								+ " , i.remarkAddSales remarkAddSales" 
								+ " , i.remark remark"
								+ " , i.flagAddSales flagAddSales"
//								+ " ,STR_TO_DATE(i.invoiceDate, '%Y%m%d') invoiceDate" 
								+ " , i.invoiceDate invoiceDate" 
							 + " from  invoicedetails i, customer c, motorcyclesdetails m, branddetails b" 
							   + " where c.cusCode          = i.cusCode"  
							     + " and m.motorcyclesCode  = i.motorcyclesCode"  
							     + " and b.brandCode	    = m.brandCode"
							     + " and i.invoiceId        = '"+invoiceId+"'";
			
			System.out.println("[EntrySaleDetailDao][setSaleDetail] sql :: " + sql); 
		    rs 			= db.executeQuery(sql);
		    
		    while(rs.next()){ 
		    	
		    	form.setInvoiceId(invoiceId);
		    	form.setRecordAddDate(EnjoyUtils.dateFormat(rs.getString("invoiceDate"), "yyyyMMdd", "dd/MM/yyyy"));
		    	
		    	cusCode 	= EnjoyUtils.nullToStr(rs.getString("cusCode"));
		    	
		    	productBean.setBrandName(EnjoyUtils.nullToStr(rs.getString("brandName")));
		    	productBean.setModel(EnjoyUtils.nullToStr(rs.getString("model")));
		    	productBean.setChassis(EnjoyUtils.nullToStr(rs.getString("chassis")));
		    	productBean.setEngineNo(EnjoyUtils.nullToStr(rs.getString("engineNo")));
		    	productBean.setChassisDisp(EnjoyUtils.nullToStr(rs.getString("chassisDisp")));
		    	productBean.setEngineNoDisp(EnjoyUtils.nullToStr(rs.getString("EngineNoDisp")));
//		    	productBean.setSize(EnjoyUtils.nullToStr(rs.getString("size")));
//		    	size 			= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("size")), 0);
//		    	productBean.setSize(size);
		    	if(EnjoyUtils.nullToStr(rs.getString("size")).equals("-")){
		    		productBean.setSize(EnjoyUtils.nullToStr(rs.getString("size")));
				}else{
					productBean.setSize(EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("size")), 0));
				}
		    	form.setProductBean(productBean);
		    	
//		    	priceAmount = rs.getDouble("priceAmount");
//		    	vatAmount 	= rs.getDouble("vatAmount");
//		    	totalAmount	= priceAmount + vatAmount;
		    	
//		    	if(vatAmount > 0){
//		    		form.setVatFlag("Y");
//		    	}
		    	
		    	totalAmount 		= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("totalAmount")), 2);
		    	vatAmount 			= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("vatAmount")), 2);
		    	priceAmount			= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("priceAmount")), 2);
		    	commTotalAmount 	= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("commTotalAmount")), 2);
		    	commVatAmount 		= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("commVatAmount")), 2);
		    	commAmount 			= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("commAmount")), 2);
		    	creditTotalAmount 	= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("creditTotalAmount")), 2);
		    	creditVatAmount 	= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("creditVatAmount")), 2);
		    	creditAmount 		= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("creditAmount")), 2);
		    	
		    	form.setPriceAmount(priceAmount);
		    	form.setVatAmount(vatAmount);
		    	form.setTotalAmount(totalAmount);
		    	
		    	form.setCommTotalAmount(commTotalAmount);
		    	form.setCommVatAmount(commVatAmount);
		    	form.setCommAmount(commAmount);
		    	
		    	form.setCreditTotalAmount(creditTotalAmount);
		    	form.setCreditVatAmount(creditVatAmount);
		    	form.setCreditAmount(creditAmount);
		    	
		    	form.setRemark(EnjoyUtils.nullToStr(rs.getString("remark")));
		    	form.setRemarkAddSales(EnjoyUtils.nullToStr(rs.getString("remarkAddSales")));
		    	form.setFlagAddSales(EnjoyUtils.nullToStr(rs.getString("flagAddSales")));
		    	form.setColor(EnjoyUtils.nullToStr(rs.getString("color")));
		    	form.setInvoiceIdAddSales(EnjoyUtils.nullToStr(rs.getString("invoiceIdAddSales")));
		    	form.setFlagCredit(EnjoyUtils.nullToStr(rs.getString("flagCredit")));
		    	
		    }	 
		    
		    if(cusCode!=null&&!cusCode.equals("")){
	    		this.getCustomerDetail(cusCode, customerBean);
	    		form.setCustomerBean(customerBean);
	    	}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[EntrySaleDetailDao][setSaleDetail][End]");
		}
	}
	
	public List<String> cusCodeList(String cusCode){
		System.out.println("[EntrySaleDetailDao][cusCodeList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		EnjoyConectDbs 					db 					= null;
		
		try{
			db    		= new EnjoyConectDbs();
			sql 		= " select cusCode from customer where cusCode like ('"+cusCode+"%') and cusStatus = 'A' order by cusCode asc limit 10 ";
			
			System.out.println("[EntrySaleDetailDao][cusCodeList] sql :: " + sql);
			
		    rs 			= db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("cusCode")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[EntrySaleDetailDao][cusCodeList][End]");
		}
		
		return list;
	}
	
	public List<String> idNumberList(String idNumber){
		System.out.println("[EntrySaleDetailDao][idNumberList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		EnjoyConectDbs 					db 					= null;
		
		try{
			db    		= new EnjoyConectDbs();
			sql 		= " select idNumber from customer where idNumber like ('"+idNumber+"%') and cusStatus = 'A' order by idNumber asc limit 10 ";
			
			System.out.println("[EntrySaleDetailDao][idNumberList] sql :: " + sql);
			
		    rs 			= db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("idNumber")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[EntrySaleDetailDao][idNumberList][End]");
		}
		
		return list;
	}
	
	public List<String> custNameList(String custName){
		System.out.println("[EntrySaleDetailDao][custNameList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		EnjoyConectDbs 					db 					= null;
		
		try{
			db    		= new EnjoyConectDbs();
			sql 		= " select cusName from customer where cusName like ('"+custName+"%') and cusStatus = 'A' group by cusName order by cusName asc limit 10 ";
			
			System.out.println("[EntrySaleDetailDao][custNameList] sql :: " + sql);
			
		    rs 			= db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("cusName")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[EntrySaleDetailDao][custNameList][End]");
		}
		
		return list;
	}
	
	public List<String> custSurnameList(String custName, String custSurname){
		System.out.println("[EntrySaleDetailDao][custSurnameList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		EnjoyConectDbs 					db 					= null;
		
		try{
			db    		= new EnjoyConectDbs();
			sql 		= "select cusCode from customer where cusName = '"+custName+"'";
			
		    if(custName.equals("")){
		    	sql 		= " select cusSurname from customer where cusSurname like ('"+custSurname+"%') and cusStatus = 'A' group by cusSurname order by cusSurname asc limit 10 ";
		    }else{
		    	sql 		= " select cusSurname from customer where cusName = '"+custName+"' and cusSurname like ('"+custSurname+"%') group by cusSurname order by cusSurname asc limit 10 ";
		    }
			System.out.println("[EntrySaleDetailDao][custSurnameList] sql :: " + sql);
			
		    rs 			= db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("cusSurname")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[EntrySaleDetailDao][custSurnameList][End]");
		}
		
		return list;
	}
	
	public List<String> brandNameList(String brandName){
		System.out.println("[EntrySaleDetailDao][brandNameList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		EnjoyConectDbs 					db 					= null;
		
		try{
			db    		= new EnjoyConectDbs();
			sql 		= " select brandName from branddetails where brandName like ('"+brandName+"%') order by brandName asc limit 10 ";
			
			System.out.println("[EntrySaleDetailDao][brandNameList] sql :: " + sql);
			
		    rs 			= db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("brandName")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
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
		EnjoyConectDbs 					db 					= null;
		
		try{
			db    			= new EnjoyConectDbs();
			if(!brandName.equals("")){
				sql 		= " select brandCode from branddetails where brandName = '" + brandName + "'";
				
				System.out.println("[EntrySaleDetailDao][modelList] brandCode sql :: " + sql);
				
			    rs 			= db.executeQuery(sql);
			    while(rs.next())brandCode = EnjoyUtils.nullToStr(rs.getString("brandCode"));
			}
			
			if(brandCode == null){
				sql 		= " select model from motorcyclesdetails where model like ('"+model+"%') and motorcyclesStatus = 'A' order by model asc limit 10 ";
			}else{
				sql 		= " select model from motorcyclesdetails where brandCode = '"+brandCode+"' and model like ('"+model+"%') and motorcyclesStatus = 'A' order by model asc limit 10 ";
			}
			
			System.out.println("[EntrySaleDetailDao][modelList] sql :: " + sql);
			
		    rs 			= db.executeQuery(sql);
		    
		    while(rs.next()){		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("model")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[EntrySaleDetailDao][modelList][End]");
		}
		
		return list;
	}
	
	public ProductBean productDetail(String brandName, String model, ProductBean productBean){
		System.out.println("[EntrySaleDetailDao][productDetail][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
		String							brandCode			= null;
		String							size				= null;
		EnjoyConectDbs 					db 					= null;
		
		try{
			db    		= new EnjoyConectDbs();
			sql 		= " select brandCode from branddetails where brandName = '" + brandName + "'";
			
			System.out.println("[EntrySaleDetailDao][productDetail] sql branddetails :: " + sql);
			
		    rs 			= db.executeQuery(sql);
		    while(rs.next())brandCode = EnjoyUtils.nullToStr(rs.getString("brandCode"));
		    
		    if(brandCode!=null && !brandCode.equals("")){
		    	sql 		= " select m.model, m.chassis, m.engineNo, m.size, b.brandName"
    					+ " from motorcyclesdetails m, branddetails b"
    					+ " where m.model = '"+model+"'"
    						+ " and m.motorcyclesStatus = 'A'"
    						+ " and m.brandCode = '"+brandCode+"'"
    						+ " and b.brandCode = m.brandCode";
		    }else{
		    	sql 		= " select m.model, m.chassis, m.engineNo, m.size, b.brandName"
		    					+ " from motorcyclesdetails m, branddetails b"
		    					+ " where m.model = '"+model+"'"
		    						+ " and m.motorcyclesStatus = 'A'"
		    						+ " and b.brandCode = m.brandCode";
		    }
		    
		    System.out.println("[EntrySaleDetailDao][productDetail] sql branddetails :: " + sql);
			
			rs 			= db.executeQuery(sql);
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
				
//				size 			= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("size")), 0);
//		    	productBean.setSize(size);
		    	
		    	if(EnjoyUtils.nullToStr(rs.getString("size")).equals("-")){
		    		productBean.setSize(EnjoyUtils.nullToStr(rs.getString("size")));
				}else{
					productBean.setSize(EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("size")), 0));
				}
				
			}
		    
		    
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[EntrySaleDetailDao][productDetail][End]");
		}
		
		return productBean;
	}
	
	public EntrySaleDetailBean getMotorcyclesCode(String brandName, String model, String chassisDisp, String engineNoDisp, String invoiceId){
		System.out.println("[EntrySaleDetailDao][getMotorcyclesCode][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
		String							brandCode			= null;
		String							motorcyclesCode		= null;
		EntrySaleDetailBean				bean				= new EntrySaleDetailBean();
		String							errMsg				= null;
		EnjoyConectDbs 					db 					= null;
		int								v_cou				= 0;
		String 							v_where			 	= "";
		String 							v_msg			 	= "";
		
		try{
			db    		= new EnjoyConectDbs();
			sql 		= " select brandCode from branddetails where brandName = '" + brandName + "'";
			
			System.out.println("[EntrySaleDetailDao][getMotorcyclesCode] sql branddetails :: " + sql);
			
		    rs 			= db.executeQuery(sql);
		    while(rs.next())brandCode = EnjoyUtils.nullToStr(rs.getString("brandCode"));
		    
		    sql 		= " select m.motorcyclesCode"
							+ " from motorcyclesdetails m, branddetails b"
							+ " where m.model = '"+model+"'"
								+ " and m.motorcyclesStatus = 'A'"
								+ " and m.brandCode = '"+brandCode+"'"
								+ " and b.brandCode = m.brandCode";
		    
		    System.out.println("[EntrySaleDetailDao][getMotorcyclesCode] sql branddetails :: " + sql);
			
			rs 			= db.executeQuery(sql);
			while(rs.next()){
				motorcyclesCode = EnjoyUtils.nullToStr(rs.getString("motorcyclesCode"));
				System.out.println("[EntrySaleDetailDao][getMotorcyclesCode] motorcyclesCode 	:: " + motorcyclesCode);
				
				bean.setMotorcyclesCode(motorcyclesCode);
			}
			
			if(motorcyclesCode==null || motorcyclesCode.equals("")){
				throw new EnjoyException("กรอกยี่ห้อ หรือ รุ่น ผิด");
			}
			
			if(!invoiceId.equals("")){
				v_where = " and invoiceId <> '"+invoiceId+"'";
		    }
		    
			/*Begin count chassisDisp*/
			sql 		= " select count(*) cou from invoicedetails where motorcyclesCode = '" + motorcyclesCode + "' and chassisDisp = '" + chassisDisp + "'" + v_where;
			
			System.out.println("[EntrySaleDetailDao][getMotorcyclesCode] sql count chassisDisp :: " + sql);
			rs 			= db.executeQuery(sql);
			while(rs.next()){
				v_cou = rs.getInt("cou");
			}
			if(v_cou > 0){
				v_msg = "เลขตัวถัง";
//				throw new EnjoyException("เลขตัวถังซ้ำกรุณาตรวจสอบ");
			}
			/*End count chassisDisp*/
			
			/*Begin count engineNoDisp*/
			sql 		= " select count(*) cou from invoicedetails where motorcyclesCode = '" + motorcyclesCode + "' and engineNoDisp = '" + engineNoDisp + "'" + v_where;
			
			System.out.println("[EntrySaleDetailDao][getMotorcyclesCode] sql count engineNoDisp :: " + sql);
			rs 			= db.executeQuery(sql);
			v_cou		= 0;
			while(rs.next()){
				v_cou = rs.getInt("cou");
			}
			if(v_cou > 0){
				if(v_msg.equals("")){
					v_msg = "เลขเครื่องยนต์";
				}else{
					v_msg = v_msg + "และเลขเครื่องยนต์";
				}
//				throw new EnjoyException("เลขเครื่องยนต์ซ้ำกรุณาตรวจสอบ");
			}
			/*End count engineNoDisp*/
			
			if(!v_msg.equals("")){
				bean.setErrType(Constants.ERR_WARNING);
				throw new EnjoyException(v_msg + "ซ้ำจะดำเนินการต่อมั้ย");
			}
			
		}catch(EnjoyException e){
			errMsg = e.getMessage();
			bean.setErrMsg(errMsg);
			e.printStackTrace();
		}catch(Exception e){
			errMsg = "เกิดข้อผิดพลาดในการดึง motorcyclesCode";
			bean.setErrMsg(errMsg);
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[EntrySaleDetailDao][productDetail][End]");
		}
		
		return bean;
	}
	
	public EntrySaleDetailBean insertInvoiceDetail(EntrySaleDetailForm form){
		System.out.println("[EntrySaleDetailDao][insertInvoiceDetail][Begin]");
		
		String 				sql			 		= null;
		String 				errMsg 				= null;
		EntrySaleDetailBean bean				= new EntrySaleDetailBean();
		String				invoiceId			= null;
		String 				priceAmount 		= null;
		String 				vatAmount 			= null;
		String 				remark 				= null;
		String 				commAmount 			= null;
		String 				flagAddSales 		= null;
		String				cusCode 			= null;
		String				motorcyclesCode 	= null;
		String				userUniqueId 		= null;
		String				chassisDisp 		= null;
		String				EngineNoDisp 		= null;
		String				size		 		= null;
		ProductBean			productBean			= null;
		CustomerBean		customerBean		= null;
//		String				invoiceIdAddSales	= "";
		String				flagCredit			= null;
		String				creditAmount		= null;
		String				totalAmount			= null;
		String				color				= null;
		String				recordAddDate		= null;
		String				formatInvoie		= null;
		String 				creditVatAmount		= null;
	    String 				creditTotalAmount	= null;
	    String 				remarkAddSales		= null;
	    String 				commVatAmount		= null;
	    String 				commTotalAmount		= null;
		EnjoyConectDbs 		db 					= null;
		
		try{
			db    				= new EnjoyConectDbs();
			productBean 		= form.getProductBean();
			customerBean		= form.getCustomerBean();
			formatInvoie		= form.getFormatInvoie();
			invoiceId			= this.genInvoiceId(formatInvoie);
			priceAmount 		= form.getPriceAmount();
			vatAmount 			= form.getVatAmount();
			remark	 			= form.getRemark();
			commAmount 			= form.getCommAmount();
			flagAddSales 		= form.getFlagAddSales();
			cusCode 			= customerBean.getCusCode();
			motorcyclesCode 	= form.getMotorcyclesCode();
			userUniqueId 		= form.getUserUniqueId();
			chassisDisp 		= productBean.getChassisDisp();
			EngineNoDisp 		= productBean.getEngineNoDisp();
			size 				= productBean.getSize();
			flagCredit 			= form.getFlagCredit();
			creditAmount 		= form.getCreditAmount();
			totalAmount			= form.getTotalAmount();
			color				= form.getColor();
			recordAddDate		= EnjoyUtils.dateFormat(form.getRecordAddDate(), "dd/MM/yyyy", "yyyyMMdd");
			creditVatAmount		= form.getCreditVatAmount();
			creditTotalAmount	= form.getCreditTotalAmount();
			remarkAddSales		= form.getRemarkAddSales();
			commVatAmount		= form.getCommVatAmount();
			commTotalAmount		= form.getCommTotalAmount();
			
			sql 		= "insert into invoicedetails ( invoiceId"
													+ " ,invoiceDate"	
													+ " ,cusCode"
													+ " ,motorcyclesCode"	
													+ " ,chassisDisp"
													+ " ,EngineNoDisp"	
													+ " ,size"
													+ " ,color"
													+ " ,priceAmount"	
													+ " ,vatAmount"
													+ " ,totalAmount"
													+ " ,remark"
													+ " ,flagAddSales"	
													+ " ,commAmount"
													+ " ,userUniqueId"
//													+ " ,invoiceIdAddSales"
													+ " ,flagCredit"
													+ " ,creditAmount"
													+ " ,remarkAddSales"
													+ " ,commVatAmount"
													+ " ,commTotalAmount"
													+ " ,creditVatAmount"
													+ " ,creditTotalAmount)"
										+ " values(		'"+invoiceId+"'"
													+ " ,'"+recordAddDate+"'"
													+ " ,'"+cusCode+"'"
													+ " ,'"+motorcyclesCode+"'"
													+ " ,'"+chassisDisp+"'"
													+ " ,'"+EngineNoDisp+"'"
													+ " ,'"+size+"'"
													+ " ,'"+color+"'"
													+ " ,'"+priceAmount+"'"
													+ " ,'"+vatAmount+"'"
													+ " ,'"+totalAmount+"'"
													+ " ,'"+remark+"'"
													+ " ,'"+flagAddSales+"'"
													+ " ,'"+commAmount+"'"
													+ " ,'"+userUniqueId+"'"
//													+ " ,'"+invoiceIdAddSales+"'"
													+ " ,'"+flagCredit+"'"
													+ " ,'"+creditAmount+"'"
													+ " ,'"+remarkAddSales+"'"
													+ " ,'"+commVatAmount+"'"
													+ " ,'"+commTotalAmount+"'"
													+ " ,'"+creditVatAmount+"'"
													+ " ,'"+creditTotalAmount+"')";
			
			System.out.println("[EntrySaleDetailDao][insertInvoiceDetail] sql :: " + sql);
			
			db.execute(sql);
			
			if(flagAddSales.equals("Y")){
				this.saveInvoiceAddSales(form, invoiceId);
			}
			
			bean.setInvoiceId(invoiceId);
			
		}catch(EnjoyException e){
			errMsg = e.getMessage();
			bean.setErrMsg(errMsg);
			e.printStackTrace();
		}catch(Exception e){
			bean.setErrMsg("เกิดข้อผิดพลาดในการบันทึก");
			e.printStackTrace();
		}finally{
			db.setDisconnection();
			System.out.println("[EntrySaleDetailDao][insertInvoiceDetail][End]");
		}
		
		return bean;
	}
	
	public void saveInvoiceAddSales(EntrySaleDetailForm form, String invoiceId) throws Exception{
		System.out.println("[EntrySaleDetailDao][saveInvoiceAddSales][Begin]");
		
		String 				sql			 		= null;
		CustomerBean		customerBean		= null;
		String				cusCode 			= null;
		String				motorcyclesCode 	= null;
		String 				priceAmount 		= null;
		String 				vatAmount 			= null;
		String 				commAmount 			= null;
		String 				flagAddSales 		= null;
		String				userUniqueId 		= null;
		String				invoiceIdAddSales	= "";
		String				flagCredit			= null;
		String				creditAmount		= null;
		String				totalAmount			= null;
		String				recordAddDate		= null;
		String				formatInvoie		= null;
		String 				invoiceAddSalesId	= null;
		String 				remark				= null;
		String 				remarkAddSales		= null;
		String 				commVatAmount		= null;
	    String 				commTotalAmount		= null;
	    String 				creditVatAmount		= null;
	    String 				creditTotalAmount	= null;
		EnjoyConectDbs 		db 					= null;
		
		try{
			db    				= new EnjoyConectDbs();
			formatInvoie		= form.getFormatInvoie();
			customerBean		= form.getCustomerBean();
			invoiceAddSalesId	= this.genInvoiceId(formatInvoie);
			cusCode 			= customerBean.getCusCode();
			motorcyclesCode 	= form.getMotorcyclesCode();
//			priceAmount 		= form.getPriceAmount();
//			vatAmount 			= form.getVatAmount();
//			totalAmount			= form.getTotalAmount();
			priceAmount 		= form.getCommAmount();		// เนื่องจากเป็นใบส่งเสริมการขาย
			vatAmount 			= form.getCommVatAmount();	// เนื่องจากเป็นใบส่งเสริมการขาย
			totalAmount			= form.getCommTotalAmount();// เนื่องจากเป็นใบส่งเสริมการขาย	
			commTotalAmount		= "0.00";					// เนื่องจากเป็นใบส่งเสริมการขาย
			commVatAmount		= "0.00";					// เนื่องจากเป็นใบส่งเสริมการขาย
			commAmount 			= "0.00";					// เนื่องจากเป็นใบส่งเสริมการขาย
			flagAddSales 		= form.getFlagAddSales();
			userUniqueId 		= form.getUserUniqueId();
			flagCredit 			= "N";						// เนื่องจากเป็นใบส่งเสริมการขาย
			creditTotalAmount 	= "0.00";					// เนื่องจากเป็นใบส่งเสริมการขาย
			creditVatAmount 	= "0.00";					// เนื่องจากเป็นใบส่งเสริมการขาย
			creditAmount 		= "0.00";					// เนื่องจากเป็นใบส่งเสริมการขาย
			remark		 		= "เป็นใบส่งเสริมการขาย";			// เนื่องจากเป็นใบส่งเสริมการขาย
			recordAddDate		= EnjoyUtils.dateFormat(form.getRecordAddDate(), "dd/MM/yyyy", "yyyyMMdd");
			remarkAddSales		= form.getRemarkAddSales();
			
			sql 		= "insert into invoicedetails ( invoiceId"
													+ " ,invoiceDate"
													+ " ,cusCode"
													+ " ,motorcyclesCode"	
													+ " ,priceAmount"	
													+ " ,vatAmount"
													+ " ,totalAmount"
													+ " ,flagAddSales"	
													+ " ,commTotalAmount"
													+ " ,commVatAmount"
													+ " ,commAmount"
													+ " ,userUniqueId"
													+ " ,invoiceIdAddSales"
													+ " ,flagCredit"
													+ " ,creditTotalAmount"
													+ " ,creditVatAmount"
													+ " ,creditAmount"
													+ " ,remark"
													+ " ,remarkAddSales"
													+ " ,masterInvoiceId)"
										+ " values(		'"+invoiceAddSalesId+"'"
													+ " ,'"+recordAddDate+"'"
													+ " ,'"+cusCode+"'"
													+ " ,'"+motorcyclesCode+"'"
													+ " ,'"+priceAmount+"'"
													+ " ,'"+vatAmount+"'"
													+ " ,'"+totalAmount+"'"
													+ " ,'"+flagAddSales+"'"
													+ " ,'"+commTotalAmount+"'"
													+ " ,'"+commVatAmount+"'"
													+ " ,'"+commAmount+"'"
													+ " ,'"+userUniqueId+"'"
													+ " ,'"+invoiceIdAddSales+"'"
													+ " ,'"+flagCredit+"'"
													+ " ,'"+creditTotalAmount+"'"
													+ " ,'"+creditVatAmount+"'"
													+ " ,'"+creditAmount+"'"
													+ " ,'"+remark+"'"
													+ " ,'"+remarkAddSales+"'"
													+ " ,'"+invoiceId+"')";
								
			System.out.println("[EntrySaleDetailDao][saveInvoiceAddSales] AddSales sql :: " + sql);
			
			db.execute(sql);
			
			sql 		= "update invoicedetails set invoiceIdAddSales = '"+invoiceAddSalesId+"' where invoiceId = '"+invoiceId+"'";
			
			System.out.println("[EntrySaleDetailDao][saveInvoiceAddSales] update sql :: " + sql);
			
			db.execute(sql);
			
		}catch(Exception e){
			throw e;
		}finally{
			db.setDisconnection();
			System.out.println("[EntrySaleDetailDao][saveInvoiceAddSales][End]");
		}
		
	}
	
	public String genInvoiceId(String formatInvoie) throws Exception{
		
		System.out.println("[EntrySaleDetailDao][genInvoiceId][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;
		String				invoiceId			= null;
		String				newId				= null;
//		int					firstInvoice		= Integer.parseInt(ConfigFile.getBEGIN_INVOICE(formatInvoie));
		EnjoyConectDbs 		db 					= null;
		Date	  			date				= new Date();
		String				year				= null;
		
		try{
			year			= EnjoyUtils.dateToStringThai(date).substring(8,10);
			formatInvoie	= formatInvoie + year;
			db    			= new EnjoyConectDbs();
			sql 			= "SELECT (MAX(SUBSTRING_INDEX(SUBSTRING_INDEX(invoiceId, '/', 2), '/', -1)) + 1) AS newId FROM invoicedetails where invoiceId like('"+formatInvoie+"%')";
			
			System.out.println("[EntrySaleDetailDao][genInvoiceId] sql :: " + sql);
			
			rs 			= db.executeQuery(sql);
			while(rs.next()){
				newId = String.format(FILL_ZERO, rs.getInt("newId"));
			}
			
			System.out.println("[EntrySaleDetailDao][genInvoiceId] newId :: " + newId);
			if(newId==null || (Integer.parseInt(newId) == 0)){
				newId = String.format(FILL_ZERO, 1);
//				newId = String.format(FILL_ZERO, firstInvoice);
			} 
//			else if ((Integer.parseInt(newId) == 0)) { // แปลว่าไม่พบรายการ
//				newId = String.format(FILL_ZERO, firstInvoice);
//			}
			
			invoiceId			= formatInvoie + "/" + newId;
			
			System.out.println("[EntrySaleDetailDao][genInvoiceId] invoiceId :: " + invoiceId);
		}catch(Exception e){
			throw e;
		}finally{
			db.setDisconnection(rs);
			System.out.println("[EntrySaleDetailDao][genInvoiceId][End]");
		}
		
		return invoiceId;
	}
	
	public EntrySaleDetailBean updateInvoiceDetail(EntrySaleDetailForm form){
		System.out.println("[EntrySaleDetailDao][updateInvoiceDetail][Begin]");
		
		String 				sql			 		= null;
		EntrySaleDetailBean bean				= new EntrySaleDetailBean();
		String				invoiceId			= null;
		String 				priceAmount 		= null;
		String 				vatAmount 			= null;
		String 				remark 				= null;
//		String 				commAmount 			= null;
//		String 				flagAddSales 		= null;
		String				cusCode 			= null;
		String				motorcyclesCode 	= null;
		String				userUniqueId 		= null;
		String				chassisDisp 		= null;
		String				EngineNoDisp 		= null;
		String				size		 		= null;
		ProductBean			productBean			= null;
		CustomerBean		customerBean		= null;
//		String				invoiceIdAddSales	= null;
		String				flagCredit			= null;
		String				creditAmount		= null;
		EnjoyConectDbs 		db 					= null;
		String				recordAddDate		= null;
		String				totalAmount			= null;
		String				color				= null;
		String 				creditVatAmount		= null;
	    String 				creditTotalAmount	= null;
//	    String 				remarkAddSales		= null;
		
		try{
			db    				= new EnjoyConectDbs();
			productBean 		= form.getProductBean();
			customerBean		= form.getCustomerBean();
			
			invoiceId			= form.getInvoiceId();
			priceAmount 		= form.getPriceAmount();
			vatAmount 			= form.getVatAmount();
			remark	 			= form.getRemark();
//			commAmount 			= form.getCommAmount();
//			flagAddSales 		= form.getFlagAddSales();
			cusCode 			= customerBean.getCusCode();
			motorcyclesCode 	= form.getMotorcyclesCode();
			userUniqueId 		= form.getUserUniqueId();
			chassisDisp 		= productBean.getChassisDisp();
			EngineNoDisp 		= productBean.getEngineNoDisp();
			size 				= productBean.getSize();
			totalAmount			= form.getTotalAmount();
			color				= form.getColor();
			
			flagCredit 			= form.getFlagCredit();
			creditAmount 		= form.getCreditAmount();
			recordAddDate		= EnjoyUtils.dateFormat(form.getRecordAddDate(), "dd/MM/yyyy", "yyyyMMdd");
			creditVatAmount		= form.getCreditVatAmount();
			creditTotalAmount	= form.getCreditTotalAmount();
//			remarkAddSales		= form.getRemarkAddSales();
			
//			if(flagAddSales.equals("Y")){
//				invoiceIdAddSales = invoiceId;
//			}
			
			sql 		= "update invoicedetails set invoiceDate 			= '" + recordAddDate + "'"
													+ ", cusCode 			= '" + cusCode + "'"
													+ ", motorcyclesCode 	= '" + motorcyclesCode + "'"
													+ ", chassisDisp 		= '" + chassisDisp + "'"
													+ ", EngineNoDisp 		= '" + EngineNoDisp + "'"
													+ ", size 				= '" + size + "'"
													+ ", color 				= '" + color + "'"
													+ ", priceAmount 		= '" + priceAmount + "'"
													+ ", vatAmount 			= '" + vatAmount + "'"
													+ ", totalAmount 		= '" + totalAmount + "'"
													+ ", remark 			= '" + remark + "'"
//													+ ", flagAddSales 		= '" + flagAddSales + "'"
//													+ ", commAmount 		= '" + commAmount + "'"
													+ ", userUniqueId 		= '" + userUniqueId + "'"
//													+ ", invoiceIdAddSales 	= '" + invoiceIdAddSales + "'"
													+ ", flagCredit 		= '" + flagCredit + "'"
													+ ", creditAmount 		= '" + creditAmount + "'"
//													+ ", remarkAddSales 	= '" + remarkAddSales + "'"
													+ ", creditVatAmount 	= '" + creditVatAmount + "'"
													+ ", creditTotalAmount 	= '" + creditTotalAmount + "'"
										+ " where invoiceId = '" + invoiceId + "'";
			
			System.out.println("[EntrySaleDetailDao][updateInvoiceDetail] sql :: " + sql);
			
			db.execute(sql);
			
			this.manageInvoiceAddSales(form);
			
			bean.setInvoiceId(invoiceId);
			
		}catch(Exception e){
			bean.setErrMsg("เกิดข้อผิดพลาดในการอัพเดทข้อมูล");
			e.printStackTrace();
		}finally{
			db.setDisconnection();
			System.out.println("[EntrySaleDetailDao][updateInvoiceDetail][End]");
		}
		
		return bean;
	}
	
	public void manageInvoiceAddSales(EntrySaleDetailForm form) throws Exception{
		System.out.println("[EntrySaleDetailDao][manageInvoiceAddSales][Begin]");
		
		String 				sql			 		= null;
		String				invoiceId			= null;
		String 				commTotalAmount		= null;
		String 				commVatAmount		= null;
		String 				commAmount 			= null;
		String 				flagAddSales 		= null;
		String 				flagAddSalesDb 		= null;
		String				invoiceIdAddSales	= "";
		EnjoyConectDbs 		db 					= null;
		ResultSet 			rs 					= null;
		String 				remarkAddSales		= null;
		
		try{
			db    				= new EnjoyConectDbs();
			flagAddSales 		= form.getFlagAddSales();
			invoiceId			= form.getInvoiceId();
			commTotalAmount 	= form.getCommTotalAmount();
			commVatAmount 		= form.getCommVatAmount();
			commAmount 			= form.getCommAmount();
			flagAddSales 		= form.getFlagAddSales();
			remarkAddSales		= form.getRemarkAddSales();
			
			
			sql 		= "SELECT flagAddSales, invoiceIdAddSales"
						   + " FROM invoicedetails" 
						   + " where invoiceId = '" + invoiceId + "'";
	
			System.out.println("[EntrySaleDetailDao][manageInvoiceAddSales] sql :: " + sql);
			
			rs 			= db.executeQuery(sql);
			while(rs.next()){
				flagAddSalesDb 		= EnjoyUtils.nullToStr(rs.getString("flagAddSales"));
				invoiceIdAddSales 	= EnjoyUtils.nullToStr(rs.getString("invoiceIdAddSales"));
			}
			
			if(flagAddSalesDb!=null && !flagAddSales.equals(flagAddSalesDb)){
				
				if(flagAddSales.equals("N")){
					//รายการหลัก
					sql 		= "update invoicedetails set flagAddSales 		= 'N'"
														+ ", commTotalAmount 	= '0.00'"
														+ ", commVatAmount 		= '0.00'"
														+ ", commAmount 		= '0.00'"
														+ ", invoiceIdAddSales 	= ''"
														+ ", remarkAddSales 	= ''"
											+ " where invoiceId = '" + invoiceId + "'";
					db.execute(sql);
					
					//รายการส่งเสริมการขาย
					sql 		= "update invoicedetails set flagAddSales 		= 'N'"
														+ ", invoiceIdAddSales 	= ''"
														+ ", masterInvoiceId 	= ''"
														+ ", remark			 	= ''"
														+ ", remarkAddSales 	= ''"
											+ " where invoiceId = '" + invoiceIdAddSales + "'";
					db.execute(sql);
				}else{
					//รายการหลัก
					sql 		= "update invoicedetails set flagAddSales 		= '"+flagAddSales+"'"
														+ ", commTotalAmount 	= '"+commTotalAmount+"'"
														+ ", commVatAmount 		= '"+commVatAmount+"'"
														+ ", commAmount 		= '"+commAmount+"'"
														+ ", remarkAddSales 	= '"+remarkAddSales+"'"
											+ " where invoiceId = '" + invoiceId + "'";
					db.execute(sql);
					
					//บันทึกส่งเสรืมการขายใบใหม่
					this.saveInvoiceAddSales(form, invoiceId);
				}
				
			}else{
				if(flagAddSales.equals("Y")){
					//รายการหลัก
					sql 		= "update invoicedetails set commTotalAmount 	= '"+commTotalAmount+"'"
														+ ", commVatAmount 		= '"+commVatAmount+"'"
														+ ", commAmount 		= '"+commAmount+"'"
														+ ", remarkAddSales 	= '"+remarkAddSales+"'"
											+ " where invoiceId = '" + invoiceId + "'";
					db.execute(sql);
					
					//รายการส่งเสริมการขาย
					sql 		= "update invoicedetails set totalAmount 		= '"+commTotalAmount+"'"
														+ ", vatAmount 			= '"+commVatAmount+"'"
														+ ", priceAmount 		= '"+commAmount+"'"
														+ ", remarkAddSales 	= '"+remarkAddSales+"'"
											+ " where invoiceId = '" + invoiceIdAddSales + "'";
					db.execute(sql);
				}
			}
			
			
		}catch(Exception e){
			throw e;
		}finally{
			db.setDisconnection();
			System.out.println("[EntrySaleDetailDao][manageInvoiceAddSales][End]");
		}
	}
	
	public EntrySaleDetailBean getNextInvoiceId(String invoiceId){
		System.out.println("[EntrySaleDetailDao][getNextInvoiceId][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
		String							nextInvoiceId		= null;
		EntrySaleDetailBean				bean				= new EntrySaleDetailBean();
		String							errMsg				= null;
		String[]						idArray				= null;
		int 							currentId			= 0;
		String							formatInvoie		= null;
		EnjoyConectDbs 					db 					= null;
		
		try{
			db    			= new EnjoyConectDbs();
			idArray 		= invoiceId.split("/");
			formatInvoie	= idArray[0];
			currentId 		= Integer.parseInt(idArray[1]);
			
			sql 		= "SELECT invoiceId"
							   + " FROM invoicedetails" 
							   + " where invoiceId like('"+formatInvoie+"%')"
							   + "  and ((chassisDisp is not null) or (flagAddSales = 'N'))"
							   + " GROUP BY invoiceId"
							   + " HAVING MIN(SUBSTRING_INDEX(SUBSTRING_INDEX(invoiceId, '/', 2), '/', -1)) > " + currentId
							   + " order by invoiceId asc limit 1";
			
			System.out.println("[EntrySaleDetailDao][getNextInvoiceId] sql :: " + sql);
			
		    rs 			= db.executeQuery(sql);
		    while(rs.next()){
		    	nextInvoiceId = EnjoyUtils.nullToStr(rs.getString("invoiceId"));
		    	bean.setInvoiceId(nextInvoiceId);
		    }
		    
		}catch(Exception e){
			errMsg = "เกิดข้อผิดพลาดในการดึง invoiceId";
			bean.setErrMsg(errMsg);
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[EntrySaleDetailDao][getNextInvoiceId][End]");
		}
		
		return bean;
	}
	
	public EntrySaleDetailBean getPreviousInvoiceId(String invoiceId){
		System.out.println("[EntrySaleDetailDao][getPreviousInvoiceId][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
		EntrySaleDetailBean				bean				= new EntrySaleDetailBean();
		String							errMsg				= null;
		String[]						idArray				= null;
		int 							currentId			= 0;
		String							formatInvoie		= null;
		String							prevInvoiceId		= null;
		EnjoyConectDbs 					db 					= null;
		
		try{
			db    			= new EnjoyConectDbs();
			idArray 		= invoiceId.split("/");
			formatInvoie	= idArray[0];
			currentId 		= Integer.parseInt(idArray[1]);
			
			sql 		= "SELECT invoiceId"
							   + " FROM invoicedetails" 
							   + " where invoiceId like('"+formatInvoie+"%')"
							   + "  and ((chassisDisp is not null) or (flagAddSales = 'N'))"
							   + " GROUP BY invoiceId"
							   + " HAVING MAX(SUBSTRING_INDEX(SUBSTRING_INDEX(invoiceId, '/', 2), '/', -1)) < " + currentId
							   + " order by invoiceId desc limit 1";
			
			System.out.println("[EntrySaleDetailDao][getPreviousInvoiceId] sql :: " + sql);
			
		    rs 			= db.executeQuery(sql);
		    while(rs.next()){
		    	prevInvoiceId = EnjoyUtils.nullToStr(rs.getString("invoiceId"));
		    	bean.setInvoiceId(prevInvoiceId);
		    }
		    
		}catch(Exception e){
			errMsg = "เกิดข้อผิดพลาดในการดึง invoiceId";
			bean.setErrMsg(errMsg);
			e.printStackTrace();
		}finally{
			db.setDisconnection(rs);
			System.out.println("[EntrySaleDetailDao][getPreviousInvoiceId][End]");
		}
		
		return bean;
	}
	
}