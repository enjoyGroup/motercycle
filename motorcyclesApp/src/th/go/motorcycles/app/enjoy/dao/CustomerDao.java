package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet; 
import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.AddressBean;
import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs; 
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class CustomerDao {	
	final static String TABLE = "customer";
	private EnjoyConectDbs db = null;	
	
	public CustomerDao(){
		db = new EnjoyConectDbs();
	}
	
	public List<CustomerBean> findCustomer(CustomerBean bean){
		System.out.println("[CustomerDao][findCustomer][Begin] :"+bean.getCusCode());
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;
		ResultSet           rSubdistict         = null;
		ResultSet           rDistict            = null;
		ResultSet           rProvince           = null; 
		CustomerBean		customerBean		= null;
		List<CustomerBean>	list				= null;	
		StringBuilder       address             = null;           
		
		try{ 
			System.out.println(bean.getCusCode());
			System.out.println(bean.getCustFullname());

			sql = "SELECT * FROM " + TABLE +" where  cusStatus = 'A'";
			
			if(bean.getCusCode()=="" && (bean.getCustFullname()=="")){
				sql += " order by cusCode";
			}else{
				if(bean.getCusCode()!=""){
					sql += " and cusCode = '"+bean.getCusCode()+"'";
				}
				if(bean.getCustFullname()!=""){
					sql += " and (SELECT CONCAT(cusName, ' ', cusSurname) as fullName FROM customer) = '"+bean.getCustFullname()+"'";
				}
				 
			} 	 
			   
			list		= new ArrayList<CustomerBean>();
			
			System.out.println("[CustomerDao][findCustomer] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
                customerBean	= new CustomerBean();  
		    	customerBean.setCusCode(EnjoyUtils.nullToStr(rs.getString("cusCode")));
		    	customerBean.setCustName(EnjoyUtils.nullToStr(rs.getString("cusName"))); 
		    	customerBean.setCustSurname(EnjoyUtils.nullToStr(rs.getString("cusSurname"))); 
				customerBean.setHouseNumber(EnjoyUtils.nullToStr(rs.getString("houseNumber")));
				customerBean.setMooNumber(EnjoyUtils.nullToStr(rs.getString("mooNumber")));
				customerBean.setSoiName(EnjoyUtils.nullToStr(rs.getString("SoiName")));
				customerBean.setStreetName(EnjoyUtils.nullToStr(rs.getString("streetName")));
				customerBean.setSubdistrictCode(EnjoyUtils.nullToStr(rs.getString("subdistrictCode")));
				customerBean.setDistrictCode(EnjoyUtils.nullToStr(rs.getString("districtCode")));
				customerBean.setProvinceCode(EnjoyUtils.nullToStr(rs.getString("provinceCode")));
				customerBean.setIdType(EnjoyUtils.nullToStr(rs.getString("idType")));
				customerBean.setIdNumber(EnjoyUtils.nullToStr(rs.getString("idNumber")));
				customerBean.setCusStatus(EnjoyUtils.nullToStr(rs.getString("cusStatus")));
				
				
		    	list.add(customerBean);
		    }
		    
		    for(CustomerBean cusBean : list){ 
				sql = "select subdistrictName from subdistrict where subdistrictId <> 000000 and subdistrictId ='" +EnjoyUtils.nullToStr(cusBean.getSubdistrictCode())+"'";
				System.out.println("[CustomerDao][validateAddress] subdistrict sql :: " + sql);
				rSubdistict	= this.db.executeQuery(sql);
				while(rSubdistict.next()){
					cusBean.setSubdistrictName(EnjoyUtils.nullToStr(rSubdistict.getString("subdistrictName")));
				} 
				 
				sql = "select districtName from district where districtId <> 0000 and districtId ='" +EnjoyUtils.nullToStr(cusBean.getDistrictCode())+"'";
				System.out.println("[CustomerDao][validateAddress] district sql :: " + sql);
				rDistict	= this.db.executeQuery(sql);
				while(rDistict.next()){
					cusBean.setDistrictName(EnjoyUtils.nullToStr(rDistict.getString("districtName")));
				} 
				
				sql = "select provinceName from province where provinceId <> 00 and provinceId ='" +EnjoyUtils.nullToStr(cusBean.getProvinceCode())+"'";
				System.out.println("[CustomerDao][validateAddress] province sql :: " + sql);
				rProvince	= this.db.executeQuery(sql);
				while(rProvince.next()){
					cusBean.setProvinceName(EnjoyUtils.nullToStr(rProvince.getString("provinceName")));
				} 
				
				address  =  new StringBuilder();
				address.append(bean.getHouseNumber());
				address.append(" หมู่บ้าน  ").append(EnjoyUtils.nullToStr(cusBean.getMooNumber()));
				address.append(" ซอย   ").append(EnjoyUtils.nullToStr(cusBean.getSoiName())); 
				address.append(" ถนน   ").append(EnjoyUtils.nullToStr(cusBean.getStreetName())); 
				address.append(" ตำบล  ").append(EnjoyUtils.nullToStr(cusBean.getSubdistrictName()));
				address.append(" อำเภอ  ").append(EnjoyUtils.nullToStr(cusBean.getDistrictName())); 
				address.append(" จังหวัด  ").append(EnjoyUtils.nullToStr(cusBean.getProvinceName()));  
				cusBean.setAddress(address.toString());
			    System.out.println(customerBean.getAddress());
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[CustomerDao][findCustomer][End]");
		}
		
		return list;
	}
	
	public CustomerBean findCustomerByCusCode(CustomerBean bean){
		System.out.println("[CustomerDao][findCustomerByCusCode][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;
		ResultSet           rSubdistict         = null;
		ResultSet           rDistict            = null;
		ResultSet           rProvince           = null; 
		CustomerBean		customerBean		= null; 	
		
		try{
			if(bean.getCusCode()!=null && bean.getCusCode()!=""){ 
				sql = "SELECT * FROM " + TABLE +" where  cusStatus = '"+"A"+"' and cusCode = '"+bean.getCusCode()+"'";
			}
			
			customerBean	= new CustomerBean();   
			System.out.println("[CustomerDao][findCustomerByCusCode] sql :: " + sql); 
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){ 
		    	customerBean.setCusCode(EnjoyUtils.nullToStr(rs.getString("cusCode")));
		    	customerBean.setCustName(EnjoyUtils.nullToStr(rs.getString("cusName"))); 
		    	customerBean.setCustSurname(EnjoyUtils.nullToStr(rs.getString("cusSurname"))); 
				customerBean.setHouseNumber(EnjoyUtils.nullToStr(rs.getString("houseNumber")));
				customerBean.setMooNumber(EnjoyUtils.nullToStr(rs.getString("mooNumber")));
				customerBean.setSoiName(EnjoyUtils.nullToStr(rs.getString("SoiName")));
				customerBean.setStreetName(EnjoyUtils.nullToStr(rs.getString("streetName")));
				customerBean.setSubdistrictCode(EnjoyUtils.nullToStr(rs.getString("subdistrictCode")));
				customerBean.setDistrictCode(EnjoyUtils.nullToStr(rs.getString("districtCode")));
				customerBean.setProvinceCode(EnjoyUtils.nullToStr(rs.getString("provinceCode")));
				customerBean.setIdType(EnjoyUtils.nullToStr(rs.getString("idType")));
				customerBean.setIdNumber(EnjoyUtils.nullToStr(rs.getString("idNumber")));
				customerBean.setCusStatus(EnjoyUtils.nullToStr(rs.getString("cusStatus")));
		    }	 
			
		    sql = "select subdistrictName from subdistrict where subdistrictId <> 000000 and subdistrictId ='" +EnjoyUtils.nullToStr(customerBean.getSubdistrictCode())+"'";
			System.out.println("[CustomerDao][validateAddress] subdistrict sql :: " + sql);
			rSubdistict	= this.db.executeQuery(sql);
			while(rSubdistict.next()){
				 customerBean.setSubdistrictName(EnjoyUtils.nullToStr(rSubdistict.getString("subdistrictName")));
			} 
			 
			sql = "select districtName from district where districtId <> 0000 and districtId ='" +EnjoyUtils.nullToStr(customerBean.getDistrictCode())+"'";
			System.out.println("[CustomerDao][validateAddress] district sql :: " + sql);
			rDistict	= this.db.executeQuery(sql);
			while(rDistict.next()){
				 customerBean.setDistrictName(EnjoyUtils.nullToStr(rDistict.getString("districtName")));
			} 
			
			sql = "select provinceName from province where provinceId <> 00 and provinceId ='" +EnjoyUtils.nullToStr(customerBean.getProvinceCode())+"'";
			System.out.println("[CustomerDao][validateAddress] province sql :: " + sql);
			rProvince	= this.db.executeQuery(sql);
			while(rProvince.next()){
				 customerBean.setProvinceName(EnjoyUtils.nullToStr(rProvince.getString("provinceName")));
			} 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[CustomerDao][findCustomerByCusCode][End]");
		}
		
		return customerBean;
	}
	
	 
	public String insertCustomer(CustomerBean customerBean){
		System.out.println("[CustomerDao][insertCustomer][Begin]");
		
		String 		sql			 	= null;
		ResultSet 	rs 				= null;  
		String      cusCode         = null; 
		
		try{ 
			 sql 		= "SELECT cusCode as lastId FROM customer ORDER BY cusCode DESC LIMIT 1";
			 rs 		= this.db.executeQuery(sql);
			 System.out.println("[CustomerDao][insertCustomer] sql :: " + sql);
			  while (rs.next()) {
				  cusCode	= EnjoyUtils.nullToStr(rs.getString("lastId"));
			  } 
			 
			 cusCode = EnjoyUtils.getCustNext(cusCode);
		     System.out.println("[CustomerDao][insertCustomer] cusCode : " + cusCode);
		    
			sql = "insert into " + TABLE + " (cusCode,cusName, cusSurname, houseNumber, mooNumber,SoiName, streetName, subdistrictCode, districtCode, provinceCode, idType, idNumber, cusStatus)"
				 + " values ('"+ cusCode + "', '" + customerBean.getCustName() + "', '" + customerBean.getCustSurname() + "', '" + 
				 customerBean.getHouseNumber() + "', '" + customerBean.getMooNumber() + "', '" + customerBean.getSoiName() + "', '" + 
				 customerBean.getStreetName()+ "', '" + customerBean.getSubdistrictCode() + "', '" +
				 customerBean.getDistrictCode() + "', '" + customerBean.getProvinceCode() + "', '" +
				 customerBean.getIdType() + "', '" + customerBean.getIdNumber() + "', '" +
				 customerBean.getCusStatus() +"') ";
			
			System.out.println("[CustomerDao][insertCustomer] sql :: " + sql);
			
            this.db.execute(sql); 
			
		    System.out.println("[CustomerDao][insertCustomer] cusCode : " + cusCode);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[CustomerDao][insertCustomer][End]");
		}
		
		return cusCode;
	}
	
	public boolean  updateCustomer(CustomerBean bean){
		System.out.println("[CustomerDao][updateCustomer][Begin]");
		
		String 				sql			 		= null;
		boolean				lv_ret				= false; 
		
		try{ 
			sql 	= "update " + TABLE + " set cusStatus = '"+bean.getCusStatus()+"', cusName='"+bean.getCustName()+
					"', cusSurname='"+bean.getCustSurname()+"', houseNumber='"+bean.getHouseNumber()+"', mooNumber='"+bean.getMooNumber()+
					"', SoiName='"+bean.getSoiName()+"', streetName='"+bean.getStreetName()+"', subdistrictCode='"+bean.getSubdistrictCode()+
					"', districtCode='"+bean.getDistrictCode()+"', provinceCode='"+bean.getProvinceCode()+"', idType='"+bean.getIdType()+
					"', idNumber='"+bean.getIdNumber()+"' where  cusCode = '"+bean.getCusCode()+"'";
			
			System.out.println("[CustomerDao][updateCustomer] sql :: " + sql);
			
			lv_ret 			= this.db.execute(sql);
			
			System.out.println("[CustomerDao][updateCustomer] lv_ret :: " + lv_ret);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[CustomerDao][updateCustomer][End]");
		}
		return lv_ret;
	}
	
	public boolean deleteCustomer(CustomerBean bean){
		System.out.println("[CustomerDao][deleteCustomer][Begin]");
		
		String 				sql			 		= null;
		boolean				lv_ret				= false;
		String				cusCode		        = null;
		String				cusStatus	        = null;
		
		try{
			cusCode    = EnjoyUtils.nullToStr(bean.getCusCode());
			cusStatus    = EnjoyUtils.nullToStr(bean.getCusStatus());
			
			sql 		= "update " + TABLE + " set cusStatus = '"+ cusStatus +"'  where cusCode = '" + cusCode +"'";
		 
			System.out.println("[CustomerDao][deleteCustomer] sql :: " + sql);
			
			lv_ret 			= this.db.execute(sql);
			
			System.out.println("[CustomerDao][deleteCustomer] lv_ret :: " + lv_ret);
			findCustomer(new CustomerBean());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[CustomerDao][deleteCustomer][End]");
		}
		return lv_ret;
	}
	
	public AddressBean validateAddress(String province, String district, String subdistrict){
		System.out.println("[CustomerDao][validateAddress][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        String							provinceId			= null;
        String							districtId			= null;
        String							subdistrictId		= null;
        String							errMsg				= null;
        AddressBean						addressBean			= new AddressBean();
		
		try{
			/*Begin check province section*/
			sql 		= "select provinceId from province where provinceId <> 00 and provinceName = '"+province+"'";
			
			System.out.println("[CustomerDao][validateAddress] province sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    while(rs.next())provinceId = rs.getString("provinceId").trim();
		    if(provinceId==null)throw new EnjoyException("ระบุจังหวัดผิด");
		    /*End check province section*/
		    
		    /*Begin check district section*/
			sql 		= "select districtId from district where districtId <> 0000 and provinceId = "+provinceId+" and districtName = '"+district+"'";
			
			System.out.println("[CustomerDao][validateAddress] district sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    while(rs.next())districtId = rs.getString("districtId").trim();
		    if(districtId==null)throw new EnjoyException("ระบุอำเภอผิด");
		    /*End check district section*/
		    
		    /*Begin check subDistrict section*/
			sql 		= "select subdistrictId from subdistrict where subdistrictId <> 000000 and provinceId = "+provinceId+" and districtId = "+districtId+" and subdistrictName = '"+subdistrict+"'";
			
			System.out.println("[CustomerDao][validateAddress] subdistrict sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    while(rs.next())subdistrictId = rs.getString("subdistrictId").trim();
		    if(subdistrictId==null)throw new EnjoyException("ระบุตำบลผิด");
		    /*End check subDistrict section*/
		    
		    System.out.println("[CustomerDao][validateAddress] " + provinceId + ", " + districtId + ", " + subdistrictId);
		    
		    addressBean.setProvinceId(provinceId);
		    addressBean.setDistrictId(districtId);
		    addressBean.setSubdistrictId(subdistrictId);
		    
		}catch(EnjoyException e){
			errMsg = e.getMessage();
			addressBean.setErrMsg(errMsg);
			e.printStackTrace();
		}catch(Exception e){
			errMsg = e.getMessage();
			addressBean.setErrMsg(errMsg);
			e.printStackTrace();
		}finally{
			System.out.println("[CustomerDao][validateAddress][End]");
		}
		return addressBean;
	}
}
