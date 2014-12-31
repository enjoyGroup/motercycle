package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.bean.EntrySaleDetailBean;
import th.go.motorcycles.app.enjoy.bean.ProductBean;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.app.enjoy.form.EntrySaleDetailForm;
import th.go.motorcycles.app.enjoy.main.ConfigFile;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class EntrySaleDetailDao {	
	
	private EnjoyConectDbs db = null;
	
	final static String CUSTOMER 		= "customer";
	final static String FILL_ZERO 		= ConfigFile.getPADING_INVOICE();
	
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
		
		try{
			addressDao 	= new AddressDao();
			sql 		= "SELECT * FROM " + CUSTOMER +" where  cusStatus = '"+"A"+"' and idNumber = '"+idNumber+"'";
			
			System.out.println("[EntrySaleDetailDao][getCustomerDetailByIdNumber] sql :: " + sql); 
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
		String				creditAmount		= null;
		String 				size				= null;
		
		try{
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
								+ " , i.creditAmount creditAmount"
								+ " , i.priceAmount priceAmount" 
								+ " , i.totalAmount totalAmount" 
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
		    	form.setRecordAddDate(EnjoyUtils.dateFormat(rs.getString("invoiceDate"), "yyyy-MM-dd", "dd/MM/yyyy"));
		    	
		    	cusCode 	= EnjoyUtils.nullToStr(rs.getString("cusCode"));
		    	
		    	productBean.setBrandName(EnjoyUtils.nullToStr(rs.getString("brandName")));
		    	productBean.setModel(EnjoyUtils.nullToStr(rs.getString("model")));
		    	productBean.setChassis(EnjoyUtils.nullToStr(rs.getString("chassis")));
		    	productBean.setEngineNo(EnjoyUtils.nullToStr(rs.getString("engineNo")));
		    	productBean.setChassisDisp(EnjoyUtils.nullToStr(rs.getString("chassisDisp")));
		    	productBean.setEngineNoDisp(EnjoyUtils.nullToStr(rs.getString("EngineNoDisp")));
//		    	productBean.setSize(EnjoyUtils.nullToStr(rs.getString("size")));
		    	size 			= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("size")), 0);
		    	productBean.setSize(size);
		    	
		    	form.setProductBean(productBean);
		    	
//		    	priceAmount = rs.getDouble("priceAmount");
//		    	vatAmount 	= rs.getDouble("vatAmount");
//		    	totalAmount	= priceAmount + vatAmount;
		    	
//		    	if(vatAmount > 0){
//		    		form.setVatFlag("Y");
//		    	}
		    	
		    	totalAmount 	= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("totalAmount")), 2);
		    	vatAmount 		= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("vatAmount")), 2);
		    	priceAmount		= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("priceAmount")), 2);
		    	commAmount 		= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("commAmount")), 2);
		    	creditAmount 	= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("creditAmount")), 2);
		    	
		    	form.setPriceAmount(priceAmount);
		    	form.setVatAmount(vatAmount);
		    	form.setCommAmount(commAmount);
		    	form.setTotalAmount(totalAmount);
		    	form.setCreditAmount(creditAmount);
		    	form.setRemark(EnjoyUtils.nullToStr(rs.getString("remark")));
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
			System.out.println("[EntrySaleDetailDao][setSaleDetail][End]");
		}
	}
	
	public List<String> cusCodeList(String cusCode){
		System.out.println("[EntrySaleDetailDao][cusCodeList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		
		try{
			sql 		= " select cusCode from customer where cusCode like ('"+cusCode+"%') and cusStatus = 'A' order by cusCode asc limit 10 ";
			
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
	
	public List<String> idNumberList(String idNumber){
		System.out.println("[EntrySaleDetailDao][idNumberList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		
		try{
			sql 		= " select idNumber from customer where idNumber like ('"+idNumber+"%') and cusStatus = 'A' order by idNumber asc limit 10 ";
			
			System.out.println("[EntrySaleDetailDao][idNumberList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("idNumber")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][idNumberList][End]");
		}
		
		return list;
	}
	
	public List<String> custNameList(String custName){
		System.out.println("[EntrySaleDetailDao][custNameList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		
		try{
			sql 		= " select cusName from customer where cusName like ('"+custName+"%') and cusStatus = 'A' group by cusName order by cusName asc limit 10 ";
			
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
		    	sql 		= " select cusSurname from customer where cusSurname like ('"+custSurname+"%') and cusStatus = 'A' group by cusSurname order by cusSurname asc limit 10 ";
		    }else{
		    	sql 		= " select cusSurname from customer where cusName = '"+custName+"' and cusSurname like ('"+custSurname+"%') group by cusSurname order by cusSurname asc limit 10 ";
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
				sql 		= " select model from motorcyclesdetails where model like ('"+model+"%') and motorcyclesStatus = 'A' order by model asc limit 10 ";
			}else{
				sql 		= " select model from motorcyclesdetails where brandCode = '"+brandCode+"' and model like ('"+model+"%') and motorcyclesStatus = 'A' order by model asc limit 10 ";
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
		String							size				= null;
		
		try{
			sql 		= " select brandCode from branddetails where brandName = '" + brandName + "'";
			
			System.out.println("[EntrySaleDetailDao][productDetail] sql branddetails :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
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
				
				size 			= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("size")), 0);
		    	productBean.setSize(size);
			}
		    
		    
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][productDetail][End]");
		}
		
		return productBean;
	}
	
	public EntrySaleDetailBean getMotorcyclesCode(String brandName, String model){
		System.out.println("[EntrySaleDetailDao][getMotorcyclesCode][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
		String							brandCode			= null;
		String							motorcyclesCode		= null;
		EntrySaleDetailBean				bean				= new EntrySaleDetailBean();
		String							errMsg				= null;
		
		try{
			sql 		= " select brandCode from branddetails where brandName = '" + brandName + "'";
			
			System.out.println("[EntrySaleDetailDao][getMotorcyclesCode] sql branddetails :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    while(rs.next())brandCode = EnjoyUtils.nullToStr(rs.getString("brandCode"));
		    
		    sql 		= " select m.motorcyclesCode"
							+ " from motorcyclesdetails m, branddetails b"
							+ " where m.model = '"+model+"'"
								+ " and m.motorcyclesStatus = 'A'"
								+ " and m.brandCode = '"+brandCode+"'"
								+ " and b.brandCode = m.brandCode";
		    
		    System.out.println("[EntrySaleDetailDao][getMotorcyclesCode] sql branddetails :: " + sql);
			
			rs 			= this.db.executeQuery(sql);
			while(rs.next()){
				motorcyclesCode = EnjoyUtils.nullToStr(rs.getString("motorcyclesCode"));
				System.out.println("[EntrySaleDetailDao][getMotorcyclesCode] motorcyclesCode 	:: " + motorcyclesCode);
				
				bean.setMotorcyclesCode(motorcyclesCode);
			}
			
			if(motorcyclesCode==null || motorcyclesCode.equals("")){
				throw new EnjoyException("กรอกยี่ห้อ หรือ รุ่น ผิด");
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
		
		try{
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
													+ " ,creditAmount)"
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
													+ " ,'"+creditAmount+"')";
			
			System.out.println("[EntrySaleDetailDao][insertInvoiceDetail] sql :: " + sql);
			
			this.db.execute(sql);
			
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
			System.out.println("[EntrySaleDetailDao][insertInvoiceDetail][End]");
		}
		
		return bean;
	}
	
	public void saveInvoiceAddSales(EntrySaleDetailForm form, String invoiceId) throws Exception{
		System.out.println("[EntrySaleDetailDao][saveInvoiceAddSales][Begin]");
		
		String 				sql			 		= null;
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
		
		try{
			formatInvoie		= form.getFormatInvoie();
			invoiceAddSalesId	= this.genInvoiceId(formatInvoie);
//			priceAmount 		= form.getPriceAmount();
//			vatAmount 			= form.getVatAmount();
//			totalAmount			= form.getTotalAmount();
			priceAmount 		= form.getCommAmount();
			vatAmount 			= "0.00";					// เนื่องจากเป็นใบส่งเสริมการขาย
			totalAmount			= form.getCommAmount();	
			commAmount 			= "0.00";					// เนื่องจากเป็นใบส่งเสริมการขาย
			flagAddSales 		= form.getFlagAddSales();
			userUniqueId 		= form.getUserUniqueId();
			flagCredit 			= "N";						// เนื่องจากเป็นใบส่งเสริมการขาย
			creditAmount 		= "0.00";					// เนื่องจากเป็นใบส่งเสริมการขาย
			remark		 		= "";						// เนื่องจากเป็นใบส่งเสริมการขาย
			recordAddDate		= EnjoyUtils.dateFormat(form.getRecordAddDate(), "dd/MM/yyyy", "yyyyMMdd");
			
			sql 		= "insert into invoicedetails ( invoiceId"
													+ " ,invoiceDate"
													+ " ,priceAmount"	
													+ " ,vatAmount"
													+ " ,totalAmount"
													+ " ,flagAddSales"	
													+ " ,commAmount"
													+ " ,userUniqueId"
													+ " ,invoiceIdAddSales"
													+ " ,flagCredit"
													+ " ,creditAmount"
													+ " ,remark)"
										+ " values(		'"+invoiceAddSalesId+"'"
													+ " ,'"+recordAddDate+"'"
													+ " ,'"+priceAmount+"'"
													+ " ,'"+vatAmount+"'"
													+ " ,'"+totalAmount+"'"
													+ " ,'"+flagAddSales+"'"
													+ " ,'"+commAmount+"'"
													+ " ,'"+userUniqueId+"'"
													+ " ,'"+invoiceIdAddSales+"'"
													+ " ,'"+flagCredit+"'"
													+ " ,'"+creditAmount+"'"
													+ " ,'"+remark+"')";
								
			System.out.println("[EntrySaleDetailDao][saveInvoiceAddSales] AddSales sql :: " + sql);
			
			this.db.execute(sql);
			
			sql 		= "update invoicedetails set invoiceIdAddSales = '"+invoiceAddSalesId+"' where invoiceId = '"+invoiceId+"'";
			
			System.out.println("[EntrySaleDetailDao][saveInvoiceAddSales] update sql :: " + sql);
			
			this.db.execute(sql);
			
		}catch(Exception e){
			throw e;
		}finally{
			System.out.println("[EntrySaleDetailDao][saveInvoiceAddSales][End]");
		}
		
	}
	
	public String genInvoiceId(String formatInvoie) throws Exception{
		
		System.out.println("[EntrySaleDetailDao][genInvoiceId][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;
		String				invoiceId			= null;
		String				newId				= null;
		int					firstInvoice		= Integer.parseInt(ConfigFile.getBEGIN_INVOICE(formatInvoie));
		try{
			sql 			= "SELECT (MAX(SUBSTRING_INDEX(SUBSTRING_INDEX(invoiceId, '/', 2), '/', -1)) + 1) AS newId FROM invoicedetails where invoiceId like('"+formatInvoie+"%')";
			
			System.out.println("[EntrySaleDetailDao][genInvoiceId] sql :: " + sql);
			
			rs 			= this.db.executeQuery(sql);
			while(rs.next()){
				newId = String.format(FILL_ZERO, rs.getInt("newId"));
			}
			
			System.out.println("[EntrySaleDetailDao][genInvoiceId] newId :: " + newId);
			if(newId==null){
//				newId = String.format(FILL_ZERO, 1);
				newId = String.format(FILL_ZERO, firstInvoice);
			} else if ((Integer.parseInt(newId) == 0)) { // แปลว่าไม่พบรายการ
				newId = String.format(FILL_ZERO, firstInvoice);
			}
			
			invoiceId			= formatInvoie + "/" + newId;
			
			System.out.println("[EntrySaleDetailDao][genInvoiceId] invoiceId :: " + invoiceId);
		}catch(Exception e){
			throw e;
		}finally{
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
		String				invoiceIdAddSales	= null;
		String				flagCredit			= null;
		String				creditAmount		= null;
		
		try{
			productBean 		= form.getProductBean();
			customerBean		= form.getCustomerBean();
			
			invoiceId			= form.getInvoiceId();
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
			
			if(flagAddSales.equals("Y")){
				invoiceIdAddSales = invoiceId;
			}
			
			sql 		= "update invoicedetails set invoiceDate 			= '" + EnjoyUtils.currDateThai() + "'"
													+ ", cusCode 			= '" + cusCode + "'"
													+ ", motorcyclesCode 	= '" + motorcyclesCode + "'"
													+ ", chassisDisp 		= '" + chassisDisp + "'"
													+ ", EngineNoDisp 		= '" + EngineNoDisp + "'"
													+ ", size 				= '" + size + "'"
													+ ", priceAmount 		= '" + priceAmount + "'"
													+ ", vatAmount 			= '" + vatAmount + "'"
													+ ", remark 			= '" + remark + "'"
													+ ", flagAddSales 		= '" + flagAddSales + "'"
													+ ", commAmount 		= '" + commAmount + "'"
													+ ", userUniqueId 		= '" + userUniqueId + "'"
													+ ", invoiceIdAddSales 	= '" + invoiceIdAddSales + "'"
													+ ", flagCredit 		= '" + flagCredit + "'"
													+ ", creditAmount 		= '" + creditAmount + "'"
										+ " where invoiceId = '" + invoiceId + "'";
			
			System.out.println("[EntrySaleDetailDao][updateInvoiceDetail] sql :: " + sql);
			
			this.db.execute(sql);
			
			bean.setInvoiceId(invoiceId);
			
		}catch(Exception e){
			bean.setErrMsg("เกิดข้อผิดพลาดในการอัพเดทข้อมูล");
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][updateInvoiceDetail][End]");
		}
		
		return bean;
	}
	
	public EntrySaleDetailBean getNextInvoiceId(String invoiceId){
		System.out.println("[EntrySaleDetailDao][getNextInvoiceId][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
		String							nextInvoiceId		= null;
		EntrySaleDetailBean				bean				= new EntrySaleDetailBean();
		String							errMsg				= null;
		String[]						nextIdArray			= null;
		String							nextId				= null;
		
		try{
			nextIdArray 	= invoiceId.split("/");
			nextId 			= String.format(FILL_ZERO, Integer.parseInt(nextIdArray[1]) + 1);
			nextInvoiceId	= nextIdArray[0] + "/" + nextId;
			
			System.out.println("[EntrySaleDetailDao][getNextInvoiceId] nextInvoiceId :: " + nextInvoiceId);
			
			sql 		= "select invoiceId from invoicedetails where invoiceId = '"+nextInvoiceId+"'";
			
			System.out.println("[EntrySaleDetailDao][getNextInvoiceId] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    while(rs.next()){
		    	nextInvoiceId = EnjoyUtils.nullToStr(rs.getString("invoiceId"));
		    	bean.setInvoiceId(nextInvoiceId);
		    }
		    
		}catch(Exception e){
			errMsg = "เกิดข้อผิดพลาดในการดึง invoiceId";
			bean.setErrMsg(errMsg);
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][getNextInvoiceId][End]");
		}
		
		return bean;
	}
	
	public EntrySaleDetailBean getPreviousInvoiceId(String invoiceId){
		System.out.println("[EntrySaleDetailDao][getPreviousInvoiceId][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
		String							prevInvoiceId		= null;
		EntrySaleDetailBean				bean				= new EntrySaleDetailBean();
		String							errMsg				= null;
		String[]						prevIdArray			= null;
		String							prevId				= null;
		
		try{
			
			prevIdArray 	= invoiceId.split("/");
			prevId 			= String.format(FILL_ZERO, Integer.parseInt(prevIdArray[1]) - 1);
			prevInvoiceId	= prevIdArray[0] + "/" + prevId;
			
			System.out.println("[EntrySaleDetailDao][getPreviousInvoiceId] prevInvoiceId :: " + prevInvoiceId);
			
			sql 		= "select invoiceId from invoicedetails where invoiceId = '"+prevInvoiceId+"'";
			
			System.out.println("[EntrySaleDetailDao][getPreviousInvoiceId] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    while(rs.next()){
		    	prevInvoiceId = EnjoyUtils.nullToStr(rs.getString("invoiceId"));
		    	bean.setInvoiceId(prevInvoiceId);
		    }
		    
		}catch(Exception e){
			errMsg = "เกิดข้อผิดพลาดในการดึง invoiceId";
			bean.setErrMsg(errMsg);
			e.printStackTrace();
		}finally{
			System.out.println("[EntrySaleDetailDao][getPreviousInvoiceId][End]");
		}
		
		return bean;
	}
	
}
















