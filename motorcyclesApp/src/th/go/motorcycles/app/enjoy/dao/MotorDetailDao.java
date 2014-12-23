package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet; 
import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.MotorDetailBean;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs; 
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class MotorDetailDao {	 
	private EnjoyConectDbs db = null;	
	
	public MotorDetailDao(){
		db = new EnjoyConectDbs();
	}
	
	public List<MotorDetailBean> findModelDetail(MotorDetailBean bean){
		String 					sql			 		= null;
		ResultSet 				rs 					= null; 
		MotorDetailBean			motorDetailBean		= null;
		List<MotorDetailBean>	list				= null;	         
		
		try{ 
			System.out.println(bean.getBrandSearch());
			System.out.println(bean.getModelSearch());

			sql = "select * from motorcyclesdetails m left join branddetails b on m.brandCode=b.brandCode"
					+ " WHERE  motorcyclesStatus = 'A'";
			
			if( bean.getBrandSearch() == "" && ( bean.getModelSearch() == "" )){
				sql += " order by motorcyclesCode";
			}else{
				if(bean.getBrandSearch()!=""){
					sql += " and b.brandName = '" + bean.getBrandSearch() + "'";
				}
				if(bean.getModelSearch()!=""){
					sql += " and m.model = '" + bean.getModelSearch() + "'";
				}
				 
			} 	 
			   
			list		= new ArrayList<MotorDetailBean>();
			
			System.out.println("[MotorDetailDao][findModelDetail] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
                motorDetailBean	= new MotorDetailBean();  
		    	motorDetailBean.setMotorcyclesCode(EnjoyUtils.nullToStr(rs.getString("motorcyclesCode")));
		    	motorDetailBean.setBrandCode(EnjoyUtils.nullToStr(rs.getString("brandCode"))); 
		    	motorDetailBean.setBrandName(EnjoyUtils.nullToStr(rs.getString("brandName")));  
				motorDetailBean.setModel(EnjoyUtils.nullToStr(rs.getString("model")));
				motorDetailBean.setChassis(EnjoyUtils.nullToStr(rs.getString("chassis")));
				motorDetailBean.setEngineNo(EnjoyUtils.nullToStr(rs.getString("engineNo")));
				motorDetailBean.setSize(EnjoyUtils.nullToStr(rs.getString("size")));
				motorDetailBean.setCompanyId(EnjoyUtils.nullToStr(rs.getString("companyId")));
			//	motorDetailBean.setCompanyName(EnjoyUtils.nullToStr(rs.getString("companyName")));
				motorDetailBean.setMotorcyclesStatus(EnjoyUtils.nullToStr(rs.getString("motorcyclesStatus")));
		    	list.add(motorDetailBean);	
		    } 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("[MotorDetailDao][findModelDetail][End]");
		}
		
		return list;
	}
//	
//	public List<CustomerBean> findAllCustomer(){
//		System.out.println("[CustomerDao][findAllCustomer][Begin]");
//		
//		String 				sql			 		= null;
//		ResultSet 			rs 					= null;  
//		CustomerBean		customerBean		= null;
//		List<CustomerBean>	list				= null;	
//		StringBuilder       address             = null;           
//		
//		try{  
//			sql = "SELECT * FROM customer a  LEFT JOIN  subdistrict s ON a.subdistrictCode=s.subdistrictId LEFT JOIN district d "
//				 + "ON a.districtCode=d.districtId LEFT JOIN province p ON a.provinceCode=p.provinceId where  cusStatus = 'A'";
//			 
//			list		= new ArrayList<CustomerBean>();
//			
//			System.out.println("[CustomerDao][findAllCustomer] sql :: " + sql);
//			
//		    rs 			= this.db.executeQuery(sql);
//		    
//		    while(rs.next()){
//                customerBean	= new CustomerBean();  
//		    	customerBean.setCusCode(EnjoyUtils.nullToStr(rs.getString("cusCode")));
//		    	customerBean.setCustName(EnjoyUtils.nullToStr(rs.getString("cusName"))); 
//		    	customerBean.setCustSurname(EnjoyUtils.nullToStr(rs.getString("cusSurname")));  
//				customerBean.setSubdistrictCode(EnjoyUtils.nullToStr(rs.getString("subdistrictCode")));
//				customerBean.setDistrictCode(EnjoyUtils.nullToStr(rs.getString("districtCode")));
//				customerBean.setProvinceCode(EnjoyUtils.nullToStr(rs.getString("provinceCode")));
//				customerBean.setIdType(EnjoyUtils.nullToStr(rs.getString("idType")));
//				customerBean.setIdNumber(EnjoyUtils.nullToStr(rs.getString("idNumber")));
//				customerBean.setCusStatus(EnjoyUtils.nullToStr(rs.getString("cusStatus")));
//
//				address  =  new StringBuilder();
//				address.append(EnjoyUtils.nullToStr(rs.getString("houseNumber"))); 
//				address.append(" เธซเธกเธนเน�เธ�เน�เธฒเธ�  ").append(EnjoyUtils.nullToStr(rs.getString("mooNumber")));
//				address.append(" เธ�เธญเธข   ").append(EnjoyUtils.nullToStr(rs.getString("SoiName"))); 
//				address.append(" เธ–เธ�เธ�   ").append(EnjoyUtils.nullToStr(rs.getString("streetName"))); 
//				address.append(" เธ•เธณเธ�เธฅ  ").append(EnjoyUtils.nullToStr(rs.getString("subdistrictName")));
//				address.append(" เธญเธณเน€เธ เธญ  ").append(EnjoyUtils.nullToStr(rs.getString("districtName"))); 
//				address.append(" เธ�เธฑเธ�เธซเธงเธฑเธ”  ").append(EnjoyUtils.nullToStr(rs.getString("provinceName")));  
//				customerBean.setAddress(address.toString());
//			    System.out.println(customerBean.getAddress());
//		    	list.add(customerBean);
//		    	
//		    } 
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			System.out.println("[CustomerDao][findAllCustomer][End]");
//		}
//		
//		return list;
//	}	
//	
//	public CustomerBean findCustomerByCusCode(CustomerBean bean){
//		System.out.println("[CustomerDao][findCustomerByCusCode][Begin]");
//		
//		String 				sql			 		= null;
//		ResultSet 			rs 					= null;  
//		CustomerBean		customerBean		= null; 	
//		
//		try{
//			if(bean.getCusCode()!=null && bean.getCusCode()!=""){ 
//				sql = "SELECT * FROM customer a  LEFT JOIN  subdistrict s ON a.subdistrictCode=s.subdistrictId LEFT JOIN district d "
//						+ "ON a.districtCode=d.districtId LEFT JOIN province p ON a.provinceCode=p.provinceId where  cusStatus = 'A' and cusCode = '"
//						+ bean.getCusCode()+"'";
//			}
//			
//			customerBean	= new CustomerBean();   
//			System.out.println("[CustomerDao][findCustomerByCusCode] sql :: " + sql); 
//		    rs 			= this.db.executeQuery(sql);
//		    
//		    while(rs.next()){ 
//		    	customerBean.setCusCode(EnjoyUtils.nullToStr(rs.getString("cusCode")));
//		    	customerBean.setCustName(EnjoyUtils.nullToStr(rs.getString("cusName"))); 
//		    	customerBean.setCustSurname(EnjoyUtils.nullToStr(rs.getString("cusSurname"))); 
//				customerBean.setHouseNumber(EnjoyUtils.nullToStr(rs.getString("houseNumber")));
//				customerBean.setMooNumber(EnjoyUtils.nullToStr(rs.getString("mooNumber")));
//				customerBean.setSoiName(EnjoyUtils.nullToStr(rs.getString("SoiName")));
//				customerBean.setStreetName(EnjoyUtils.nullToStr(rs.getString("streetName"))); 
//				customerBean.setIdType(EnjoyUtils.nullToStr(rs.getString("idType")));
//				customerBean.setIdNumber(EnjoyUtils.nullToStr(rs.getString("idNumber")));
//				customerBean.setCusStatus(EnjoyUtils.nullToStr(rs.getString("cusStatus")));
//				customerBean.setSubdistrictName(EnjoyUtils.nullToStr(rs.getString("subdistrictName")));
//				customerBean.setDistrictName(EnjoyUtils.nullToStr(rs.getString("districtName")));
//				customerBean.setProvinceName(EnjoyUtils.nullToStr(rs.getString("provinceName")));
//		    } 
//		   
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			System.out.println("[CustomerDao][findCustomerByCusCode][End]");
//		}
//		
//		return customerBean;
//	}
	
	 
//	public String insertCustomer(CustomerBean customerBean){
//		System.out.println("[CustomerDao][insertCustomer][Begin]");
//		
//		String 		sql			 	= null;
//		ResultSet 	rs 				= null;  
//		String      cusCode         = null; 
//		
//		try{ 
//			 sql 		= "SELECT cusCode as lastId FROM customer ORDER BY cusCode DESC LIMIT 1";
//			 rs 		= this.db.executeQuery(sql);
//			 System.out.println("[CustomerDao][insertCustomer] sql :: " + sql);
//			  while (rs.next()) {
//				  cusCode	= EnjoyUtils.nullToStr(rs.getString("lastId"));
//			  } 
//			 
//			 cusCode = EnjoyUtils.getCustNext(cusCode);
//		     System.out.println("[CustomerDao][insertCustomer] cusCode : " + cusCode);
//		    
//			sql = "insert into customer (cusCode,cusName, cusSurname, houseNumber, mooNumber,SoiName, streetName, subdistrictCode, districtCode, provinceCode, idType, idNumber, cusStatus)"
//				 + " values ('"+ cusCode + "', '" + customerBean.getCustName() + "', '" + customerBean.getCustSurname() + "', '" + 
//				 customerBean.getHouseNumber() + "', '" + customerBean.getMooNumber() + "', '" + customerBean.getSoiName() + "', '" + 
//				 customerBean.getStreetName()+ "', '" + customerBean.getSubdistrictCode() + "', '" +
//				 customerBean.getDistrictCode() + "', '" + customerBean.getProvinceCode() + "', '" +
//				 customerBean.getIdType() + "', '" + customerBean.getIdNumber() + "', '" +
//				 customerBean.getCusStatus() +"') ";
//			
//			System.out.println("[CustomerDao][insertCustomer] sql :: " + sql);
//			
//            this.db.execute(sql); 
//			
//		    System.out.println("[CustomerDao][insertCustomer] cusCode : " + cusCode);
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			System.out.println("[CustomerDao][insertCustomer][End]");
//		}
//		
//		return cusCode;
//	}
	
//	public boolean  updateCustomer(CustomerBean bean){
//		System.out.println("[CustomerDao][updateCustomer][Begin]");
//		
//		String 				sql			 		= null;
//		boolean				lv_ret				= false; 
//		
//		try{ 
//			sql 	= "update  customer set cusStatus = '"+bean.getCusStatus()+"', cusName='"+bean.getCustName()+
//					"', cusSurname='"+bean.getCustSurname()+"', houseNumber='"+bean.getHouseNumber()+
//					"', mooNumber='"+bean.getMooNumber()+"', SoiName='"+bean.getSoiName()+
//					"', streetName='"+bean.getStreetName()+"', subdistrictCode='"+bean.getSubdistrictCode()+
//					"', districtCode='"+bean.getDistrictCode()+"', provinceCode='"+bean.getProvinceCode()+
//					"', idType='"+bean.getIdType()+"', idNumber='"+bean.getIdNumber()+"' where  cusCode = '"+bean.getCusCode()+"'";
//			
//			System.out.println("[CustomerDao][updateCustomer] sql :: " + sql);
//			
//			lv_ret 			= this.db.execute(sql);
//			
//			System.out.println("[CustomerDao][updateCustomer] lv_ret :: " + lv_ret);
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			System.out.println("[CustomerDao][updateCustomer][End]");
//		}
//		return lv_ret;
//	}
//	
//	public boolean deleteCustomer(CustomerBean bean){
//		System.out.println("[CustomerDao][deleteCustomer][Begin]");
//		
//		String 				sql			 		= null;
//		boolean				lv_ret				= false;
//		String				cusCode		        = null;
//		String				cusStatus	        = null;
//		
//		try{
//			cusCode    = EnjoyUtils.nullToStr(bean.getCusCode());
//			cusStatus    = EnjoyUtils.nullToStr(bean.getCusStatus());
//			
//			sql 		= "update customer set cusStatus = '"+ cusStatus +"'  where cusCode = '" + cusCode +"'";
//		 
//			System.out.println("[CustomerDao][deleteCustomer] sql :: " + sql);
//			
//			lv_ret 			= this.db.execute(sql);
//			
//			System.out.println("[CustomerDao][deleteCustomer] lv_ret :: " + lv_ret);
//			findCustomer(new CustomerBean());
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			System.out.println("[CustomerDao][deleteCustomer][End]");
//		}
//		return lv_ret;
//	}
	
}
