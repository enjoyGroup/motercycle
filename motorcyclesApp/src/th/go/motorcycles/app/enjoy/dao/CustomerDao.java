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
		System.out.println("[CustomerDao][findCustomer][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;
		CustomerBean		customerBean		= null;
		List<CustomerBean>	list				= null;		
		
		try{
			if(bean.getCusCode()==null){
				sql 	= "SELECT * FROM " + TABLE + " order by cusCode";
			}else{
				sql 	= "SELECT * FROM " + TABLE +" where  cusCode = '"+bean.getCusCode()+"'";
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
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[CustomerDao][findCustomer][End]");
		}
		
		return list;
	}
	
	public List<CustomerBean> findCustomerAll(){
        System.out.println("[CustomerDao][findCustomerAll][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;
		CustomerBean		customerBean		= null;
		List<CustomerBean>	list				= null;		
		
		try{
			sql 		= "SELECT * FROM " + TABLE + " order by cusCode";
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
		String				custCode			= null;
		
		try{
			custCode	= EnjoyUtils.nullToStr(bean.getCusCode()); 
			
//			sql 		= "update " + TABLE + " set proName = '"+proName+"', proPrice='"+proPrice+"' where  proId = "+proId;
			
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
		
		try{
			cusCode    = EnjoyUtils.nullToStr(bean.getCusCode());
			
			sql 		= "delete from " + TABLE + " where cusCode = " + cusCode;
			
			System.out.println("[CustomerDao][deleteCustomer] sql :: " + sql);
			
			lv_ret 			= this.db.execute(sql);
			
			System.out.println("[CustomerDao][deleteCustomer] lv_ret :: " + lv_ret);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[CustomerDao][deleteCustomer][End]");
		}
		return lv_ret;
	}
}
