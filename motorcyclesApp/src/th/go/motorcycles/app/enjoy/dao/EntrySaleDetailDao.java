package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.bean.EntrySaleDetailBean;
import th.go.motorcycles.app.enjoy.bean.ProductBean;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
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
		Double				priceAmount			= null;
		Double				vatAmount			= null;
		Double				totalAmount			= null;
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
		    	
		    	priceAmount = rs.getDouble("priceAmount");
		    	vatAmount 	= rs.getDouble("vatAmount");
		    	totalAmount	= priceAmount + vatAmount;
		    	
		    	if(vatAmount > 0){
		    		form.setVatFlag("Y");
		    	}
		    	
		    	commAmount 	= EnjoyUtils.convertFloatToDisplay(EnjoyUtils.nullToStr(rs.getString("commAmount")), 2);
		    	
		    	form.setPriceAmount(EnjoyUtils.convertFloatToDisplay(priceAmount.toString(), 2));
		    	form.setVatAmount(EnjoyUtils.convertFloatToDisplay(vatAmount.toString(), 2));
		    	form.setCommAmount(commAmount);
		    	form.setTotalAmount(EnjoyUtils.convertFloatToDisplay(totalAmount.toString(), 2));
		    	form.setRemark(EnjoyUtils.nullToStr(rs.getString("remark")));
		    	form.setFlagAddSales(EnjoyUtils.nullToStr(rs.getString("flagAddSales")));
		    	
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
			sql 		= " select cusName from customer where cusName like ('"+custName+"%') group by cusName order by cusName asc limit 10 ";
			
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
		    	sql 		= " select cusSurname from customer where cusSurname like ('"+custSurname+"%') group by cusSurname order by cusSurname asc limit 10 ";
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
		ResultSet 			rs 					= null;
		String 				errMsg 				= null;
		EntrySaleDetailBean bean				= new EntrySaleDetailBean();;
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
		
		try{
			productBean 	= form.getProductBean();
			customerBean	= form.getCustomerBean();
			sql 			= "select (max(invoiceId)+1) newInvoiceId from invoicedetails";
			
			System.out.println("[EntrySaleDetailDao][insertInvoiceDetail] sql :: " + sql);
			
			rs 			= this.db.executeQuery(sql);
			while(rs.next()) invoiceId = EnjoyUtils.nullToStr(rs.getString("newInvoiceId"));
			
			System.out.println("[EntrySaleDetailDao][insertInvoiceDetail] invoiceId :: " + invoiceId);
			if(invoiceId==null){
				throw new EnjoyException("เกิดข้อผิดพลาดในการ gen invoice id.");
			}
			
			priceAmount 		= form.getPriceAmount();
			vatAmount 			= form.getVatAmount();
			remark	 			= form.getRemark();
			commAmount 			= form.getCommAmount();
			flagAddSales 		= form.getFlagAddSales();
			cusCode 			= customerBean.getCusCode();
			motorcyclesCode 	= form.getMotorcyclesCode();
			userUniqueId 		= form.getUserUniqueId();
			chassisDisp 		= productBean.getChassis();
			EngineNoDisp 		= productBean.getEngineNo();
			size 				= productBean.getSize();
			
			
			sql 		= "insert into invoicedetails ( invoiceId"
													+ " ,invoiceDate"	
													+ " ,cusCode"
													+ " ,motorcyclesCode"	
													+ " ,chassisDisp"
													+ " ,EngineNoDisp"	
													+ " ,size"
													+ " ,priceAmount"	
													+ " ,vatAmount"
													+ " ,remark"
													+ " ,flagAddSales"	
													+ " ,commAmount"
													+ " ,userUniqueId)"
										+ " values("+invoiceId+""
													+ " ,'"+EnjoyUtils.currDateThai()+"'"
													+ " ,'"+cusCode+"'"
													+ " ,'"+motorcyclesCode+"'"
													+ " ,'"+chassisDisp+"'"
													+ " ,'"+EngineNoDisp+"'"
													+ " ,'"+size+"'"
													+ " ,'"+priceAmount+"'"
													+ " ,'"+vatAmount+"'"
													+ " ,'"+remark+"'"
													+ " ,'"+flagAddSales+"'"
													+ " ,'"+commAmount+"'"
													+ " ,'"+userUniqueId+"')";
			this.db.execute(sql);
			
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
			chassisDisp 		= productBean.getChassis();
			EngineNoDisp 		= productBean.getEngineNo();
			size 				= productBean.getSize();
			
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
		
		try{
			sql 		= "select invoiceId from invoicedetails where invoiceId = (select min(invoiceId) from invoicedetails where  invoiceId > "+invoiceId+")";
			
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
		String							nextInvoiceId		= null;
		EntrySaleDetailBean				bean				= new EntrySaleDetailBean();
		String							errMsg				= null;
		
		try{
			sql 		= "select invoiceId from invoicedetails where invoiceId = (select max(invoiceId) from invoicedetails where  invoiceId < "+invoiceId+")";
			
			System.out.println("[EntrySaleDetailDao][getPreviousInvoiceId] sql :: " + sql);
			
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
			System.out.println("[EntrySaleDetailDao][getPreviousInvoiceId][End]");
		}
		
		return bean;
	}
	
}
















