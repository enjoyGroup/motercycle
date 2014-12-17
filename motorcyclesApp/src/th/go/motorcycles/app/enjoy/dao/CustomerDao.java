package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet; 
import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.CustomerBean;
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
		CustomerBean		customerBean		= null;
		List<CustomerBean>	list				= null;	
		StringBuilder       address             = null;           
		
		try{ 
			System.out.println(bean.getCusCode());
			System.out.println(bean.getCustFullname());

			sql = "SELECT * FROM " + TABLE +" where  cusStatus = 'A'";
			
			if(!bean.getCusCode().equals("")  && (!bean.getCustFullname().equals(""))){
				sql += " order by cusCode";
			}else{
				if(!bean.getCusCode().equals("")){
					sql += " and cusCode = '"+bean.getCusCode()+"'";
				}
				if(!bean.getCustFullname().equals("")){
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
				
				address  =  new StringBuilder();
				address.append(bean.getHouseNumber());
				address.append(" À¡ŸË  ").append(EnjoyUtils.nullToStr(rs.getString("houseNumber")));
				address.append(" ´Õ¬   ").append(EnjoyUtils.nullToStr(rs.getString("SoiName"))); 
				address.append(" ∂ππ   ").append(EnjoyUtils.nullToStr(rs.getString("streetName"))); 
				address.append(" µ”∫≈   ").append(EnjoyUtils.nullToStr(rs.getString("subdistrictCode")));
				address.append(" Õ”‡¿Õ   ").append(EnjoyUtils.nullToStr(rs.getString("districtCode"))); 
				address.append(" ®—ßÀ«—¥   ").append(EnjoyUtils.nullToStr(rs.getString("provinceCode")));  
			    customerBean.setAddress(address.toString());
			    System.out.println(customerBean.getAddress());
		    	list.add(customerBean);
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
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[CustomerDao][findCustomerByCusCode][End]");
		}
		
		return customerBean;
	}
	
	public List<CustomerBean> findCustomerAll(){
        System.out.println("[CustomerDao][findCustomerAll][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;
		CustomerBean		customerBean		= null;
		List<CustomerBean>	list				= null;		
		
		try{
			sql = "SELECT * FROM " + TABLE +" where  cusStatus = 'A' order by cusCode";
			list		= new ArrayList<CustomerBean>();
			
			System.out.println("[CustomerDao][findCustomerAll] sql :: " + sql);
			
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
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[CustomerDao][findCustomerAll][End]");
		}
		
		return list;
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
			findCustomerAll();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[CustomerDao][deleteCustomer][End]");
		}
		return lv_ret;
	}
}
