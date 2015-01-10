package th.go.motorcycles.app.enjoy.dao;

import java.sql.ResultSet; 
import java.util.ArrayList;
import java.util.List;

import th.go.motorcycles.app.enjoy.bean.AddressBean;
import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.app.enjoy.form.CustomerForm;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs; 
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class CustomerDao {	 
	private EnjoyConectDbs db = null;	
	
	public CustomerDao(){
//		db = new EnjoyConectDbs();
	}
	
	public List<CustomerBean> findCustomer(CustomerBean bean){
		System.out.println("[CustomerDao][findCustomer][Begin] :"+bean.getCusCode());
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null; 
		CustomerBean		customerBean		= null;
		List<CustomerBean>	list				= null;	
		StringBuilder       address             = null;           
		
		try{ 
			System.out.println(bean.getIdNumber());
			System.out.println(bean.getCustFullname());
			
			this.db    = new EnjoyConectDbs();
			sql = "SELECT * FROM customer a  LEFT JOIN  subdistrict s ON a.subdistrictCode=s.subdistrictId LEFT JOIN district d "
				+ "ON a.districtCode=d.districtId LEFT JOIN province p ON a.provinceCode=p.provinceId where  cusStatus = 'A'";
			
			if(bean.getIdNumber()=="" && (bean.getCustFullname()=="")){
				sql += " order by cusCode";
			}else{
				if(bean.getIdNumber()!=""){
					sql += " and idNumber = '"+bean.getIdNumber()+"'";
				}
				if(bean.getCustFullname()!=""){
					sql += " and (SELECT CONCAT(a.cusName, ' ', a.cusSurname) as fullName) like '"+bean.getCustFullname()+"%'";
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
				customerBean.setSubdistrictCode(EnjoyUtils.nullToStr(rs.getString("subdistrictCode")));
				customerBean.setDistrictCode(EnjoyUtils.nullToStr(rs.getString("districtCode")));
				customerBean.setProvinceCode(EnjoyUtils.nullToStr(rs.getString("provinceCode")));
				customerBean.setIdType(EnjoyUtils.nullToStr(rs.getString("idType")));
				customerBean.setIdNumber(EnjoyUtils.nullToStr(rs.getString("idNumber")));
				customerBean.setCusStatus(EnjoyUtils.nullToStr(rs.getString("cusStatus")));

				address  =  new StringBuilder();
				address.append(EnjoyUtils.nullToStr(rs.getString("houseNumber")));  
				
				if (! EnjoyUtils.nullToStr(rs.getString("mooNumber")).equals("")) {
					address.append(" หมู่ที่ ").append(EnjoyUtils.nullToStr(rs.getString("mooNumber")));
				}	
				if (! EnjoyUtils.nullToStr(rs.getString("SoiName")).equals("")) {
					address.append(" ซอย ").append(EnjoyUtils.nullToStr(rs.getString("SoiName")));
				}	
				if (! EnjoyUtils.nullToStr(rs.getString("streetName")).equals("")) {
					address.append(" ถนน ").append(EnjoyUtils.nullToStr(rs.getString("streetName")));
				}	
				address.append(" ตำบล/แขวง ").append(EnjoyUtils.nullToStr(rs.getString("subdistrictName")));
				address.append(" อำเภอ/เขต  ").append(EnjoyUtils.nullToStr(rs.getString("districtName"))); 
				address.append(" จังหวัด   ").append(EnjoyUtils.nullToStr(rs.getString("provinceName")));  
				address.append(" ").append(EnjoyUtils.nullToStr(rs.getString("postCode")));
				
				customerBean.setAddress(address.toString());
			    System.out.println(customerBean.getAddress());
		    	list.add(customerBean);
		    	
		    } 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection(rs);
			System.out.println("[CustomerDao][findCustomer][End]");
		}
		
		return list;
	}
	
	public List<CustomerBean> findAllCustomer(){
		System.out.println("[CustomerDao][findAllCustomer][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;  
		CustomerBean		customerBean		= null;
		List<CustomerBean>	list				= null;	
		StringBuilder       address             = null;           
		
		try{  
			this.db    = new EnjoyConectDbs();
			sql = "SELECT * FROM customer a  LEFT JOIN  subdistrict s ON a.subdistrictCode=s.subdistrictId LEFT JOIN district d "
				 + "ON a.districtCode=d.districtId LEFT JOIN province p ON a.provinceCode=p.provinceId where  cusStatus = 'A'";
			 
			list		= new ArrayList<CustomerBean>();
			
			System.out.println("[CustomerDao][findAllCustomer] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
                customerBean	= new CustomerBean();  
		    	customerBean.setCusCode(EnjoyUtils.nullToStr(rs.getString("cusCode")));
		    	customerBean.setCustName(EnjoyUtils.nullToStr(rs.getString("cusName"))); 
		    	customerBean.setCustSurname(EnjoyUtils.nullToStr(rs.getString("cusSurname")));  
				customerBean.setSubdistrictCode(EnjoyUtils.nullToStr(rs.getString("subdistrictCode")));
				customerBean.setDistrictCode(EnjoyUtils.nullToStr(rs.getString("districtCode")));
				customerBean.setProvinceCode(EnjoyUtils.nullToStr(rs.getString("provinceCode")));
				customerBean.setIdType(EnjoyUtils.nullToStr(rs.getString("idType")));
				customerBean.setIdNumber(EnjoyUtils.nullToStr(rs.getString("idNumber")));
				customerBean.setCusStatus(EnjoyUtils.nullToStr(rs.getString("cusStatus")));

				address  =  new StringBuilder();
				address.append(EnjoyUtils.nullToStr(rs.getString("houseNumber"))); 
				if (! EnjoyUtils.nullToStr(rs.getString("mooNumber")).equals("")) {
					address.append(" หมู่ที่ ").append(EnjoyUtils.nullToStr(rs.getString("mooNumber")));
				}	
				if (! EnjoyUtils.nullToStr(rs.getString("SoiName")).equals("")) {
					address.append(" ซอย ").append(EnjoyUtils.nullToStr(rs.getString("SoiName")));
				}	
				if (! EnjoyUtils.nullToStr(rs.getString("streetName")).equals("")) {
					address.append(" ถนน ").append(EnjoyUtils.nullToStr(rs.getString("streetName")));
				}	
				address.append(" ตำบล/แขวง ").append(EnjoyUtils.nullToStr(rs.getString("subdistrictName")));
				address.append(" อำเภอ/เขต  ").append(EnjoyUtils.nullToStr(rs.getString("districtName"))); 
				address.append(" จังหวัด  ").append(EnjoyUtils.nullToStr(rs.getString("provinceName")));  
				address.append(" ").append(EnjoyUtils.nullToStr(rs.getString("postCode")));
				
				customerBean.setAddress(address.toString());
			    System.out.println(customerBean.getAddress());
		    	list.add(customerBean);
		    	
		    } 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection(rs);
			System.out.println("[CustomerDao][findAllCustomer][End]");
		}
		
		return list;
	}	
	
	public CustomerBean findCustomerByCusCode(CustomerBean bean,CustomerForm cusForm){
		System.out.println("[CustomerDao][findCustomerByCusCode][Begin]");
		
		String 				sql			 		= null;
		ResultSet 			rs 					= null;  
		CustomerBean		customerBean		= null; 	
		
		try{
			this.db    = new EnjoyConectDbs();
			if(bean.getCusCode()!=null && bean.getCusCode()!=""){ 
				sql = "SELECT * FROM customer a  LEFT JOIN  subdistrict s ON a.subdistrictCode=s.subdistrictId LEFT JOIN district d "
						+ "ON a.districtCode=d.districtId LEFT JOIN province p ON a.provinceCode=p.provinceId where  cusStatus = 'A' and cusCode = '"
						+ bean.getCusCode()+"'";
			}
			
			customerBean	= new CustomerBean();   
			System.out.println("[CustomerDao][findCustomerByCusCode] sql :: " + sql); 
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){ 
		    	System.out.println("cuscode:"+EnjoyUtils.nullToStr(rs.getString("cusCode")));
		    	customerBean.setCusCode(EnjoyUtils.nullToStr(rs.getString("cusCode")));
		    	customerBean.setCustName(EnjoyUtils.nullToStr(rs.getString("cusName"))); 
		    	customerBean.setCustSurname(EnjoyUtils.nullToStr(rs.getString("cusSurname"))); 
				customerBean.setHouseNumber(EnjoyUtils.nullToStr(rs.getString("houseNumber")));
				customerBean.setMooNumber(EnjoyUtils.nullToStr(rs.getString("mooNumber")));
				customerBean.setSoiName(EnjoyUtils.nullToStr(rs.getString("SoiName")));
				customerBean.setStreetName(EnjoyUtils.nullToStr(rs.getString("streetName"))); 
				customerBean.setIdType(EnjoyUtils.nullToStr(rs.getString("idType")));
				customerBean.setIdNumber(EnjoyUtils.nullToStr(rs.getString("idNumber")));
				customerBean.setCusStatus(EnjoyUtils.nullToStr(rs.getString("cusStatus")));
				customerBean.setSubdistrictName(EnjoyUtils.nullToStr(rs.getString("subdistrictName")));
				customerBean.setDistrictName(EnjoyUtils.nullToStr(rs.getString("districtName")));
				customerBean.setProvinceName(EnjoyUtils.nullToStr(rs.getString("provinceName")));
				customerBean.setSubdistrictCode(EnjoyUtils.nullToStr(rs.getString("subdistrictId")));
				customerBean.setDistrictCode(EnjoyUtils.nullToStr(rs.getString("districtId")));
				customerBean.setProvinceCode(EnjoyUtils.nullToStr(rs.getString("provinceId")));
				customerBean.setPostcode(EnjoyUtils.nullToStr(rs.getString("postcode")));
				cusForm.setCustomerBean(customerBean);
		    } 
		    
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection(rs);
			System.out.println("[CustomerDao][findCustomerByCusCode][End]");
		}
		
		return customerBean;
	}
	
	 
	public String insertCustomer(CustomerBean customerBean){
		System.out.println("[CustomerDao][insertCustomer][Begin]");
		
		String 		sql			 	= null;
		ResultSet 	rs 				= null;  
		String      cusCode         = null;  
		CustomerBean bean           = null;
		try{ 
			 this.db    = new EnjoyConectDbs();
			 sql 		= "SELECT cusCode as lastId FROM customer ORDER BY cusCode DESC LIMIT 1";
			 rs 		= this.db.executeQuery(sql);
			 System.out.println("[CustomerDao][insertCustomer] sql :: " + sql);
			 while (rs.next()) {
				  cusCode	= EnjoyUtils.nullToStr(rs.getString("lastId"));
			 } 
			 System.out.println("[CustomerDao][insertCustomer] cusCode :: " + cusCode); 
			 
			 if(cusCode==null){
				cusCode = "100"; 
			 }
			 
			 cusCode = EnjoyUtils.getCustNext(cusCode);
		     System.out.println("[CustomerDao][insertCustomer] cusCode : " + cusCode);
		    
			sql = "insert into customer ( cusCode"
										+ ", cusName"
										+ ", cusSurname"
										+ ", houseNumber"
										+ ", mooNumber"
										+ ", SoiName"
										+ ", streetName"
										+ ", subdistrictCode"
										+ ", districtCode"
										+ ", provinceCode"
										+ ", idType"
										+ ", idNumber"
										+ ", cusStatus"
										+ ", postcode)"
							+ " values (	'"+ cusCode + "'"
										+ ", '" + customerBean.getCustName() 		+ "'"
										+ ", '" + customerBean.getCustSurname() 	+ "'"
										+ ", '" + customerBean.getHouseNumber() 	+ "'"
										+ ", '" + customerBean.getMooNumber() 		+ "'"
										+ ", '" + customerBean.getSoiName() 		+ "'"
										+ ", '" + customerBean.getStreetName()		+ "'"
										+ ", '" + customerBean.getSubdistrictCode() + "'"
										+ ", '" + customerBean.getDistrictCode() 	+ "'"
										+ ", '" + customerBean.getProvinceCode() 	+ "'"
										+ ", '" + customerBean.getIdType() 			+ "'"
										+ ", '" + customerBean.getIdNumber() 		+ "'"
										+ ", '" + customerBean.getCusStatus() 		+ "'"
										+ ", '"	+ customerBean.getPostcode()		+ "'"
									+ ") ";
			
			System.out.println("[CustomerDao][insertCustomer] sql :: " + sql);
			
            this.db.execute(sql);  
		    System.out.println("[CustomerDao][insertCustomer] cusCode : " + cusCode);
		    
		    /*if(cusCode!=null&&!cusCode.equals("")){
			    bean = new CustomerBean();
			    bean.setCusCode(cusCode);
			    form.setCustomerBean(bean);
		    }*/
		    
		}catch(Exception e){  
			e.printStackTrace();
		}finally{
			this.db.setDisconnection(rs);
			System.out.println("[CustomerDao][insertCustomer][End]");
		}
		
		return cusCode;
	}

	
	public boolean updateCustomer(CustomerBean bean){
		System.out.println("[CustomerDao][updateCustomer][Begin]::"+bean.getCusCode());
		
		String 				sql			 		= null;
		boolean				lv_ret				= false; 

		try{ 
			this.db = new EnjoyConectDbs();
			sql 	= "update  customer set "
									+ " cusStatus 			= '"+bean.getCusStatus()		+ "'"
									+ ", cusName			= '"+bean.getCustName() 		+ "'"
									+ ", cusSurname			= '"+bean.getCustSurname()		+ "'"
									+ ", houseNumber		= '"+bean.getHouseNumber()		+ "'"
									+ ", mooNumber			= '"+bean.getMooNumber()		+ "'"
									+ ", SoiName			= '"+bean.getSoiName()			+ "'"
									+ ", streetName			= '"+bean.getStreetName()		+ "'"
									+ ", subdistrictCode	= '"+bean.getSubdistrictCode()	+ "'"
									+ ", districtCode		= '"+bean.getDistrictCode()		+ "'"
									+ ", provinceCode		= '"+bean.getProvinceCode()		+ "'"
									+ ", idType				= '"+bean.getIdType()			+ "'"
									+ ", idNumber			= '"+bean.getIdNumber()			+ "'"
									+ ", postcode			= '"+bean.getPostcode()			+ "'"
							+ " where  cusCode = '"+bean.getCusCode()+"'";
			
			System.out.println("[CustomerDao][updateCustomer] sql :: " + sql);
			
			lv_ret 			= this.db.execute(sql);
			
			System.out.println("[CustomerDao][updateCustomer] lv_ret :: " + lv_ret); 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection();
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
			this.db    = new EnjoyConectDbs();
			cusCode    = EnjoyUtils.nullToStr(bean.getCusCode());
			cusStatus  = EnjoyUtils.nullToStr(bean.getCusStatus());
			
			sql 	   = "update customer set cusStatus = '"+ cusStatus +"'  where cusCode = '" + cusCode +"'";
		 
			System.out.println("[CustomerDao][deleteCustomer] sql :: " + sql);
			
			lv_ret 			= this.db.execute(sql);
			
			System.out.println("[CustomerDao][deleteCustomer] lv_ret :: " + lv_ret);
			findCustomer(new CustomerBean());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection();
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
			this.db     = new EnjoyConectDbs();
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
		    if(districtId==null)throw new EnjoyException("ระบุอำเภอ/เขตผิด”");
		    /*End check district section*/
		    
		    /*Begin check subDistrict section*/
			sql 		= "select subdistrictId from subdistrict where subdistrictId <> 000000 and provinceId = "+provinceId+" and districtId = "+districtId+" and subdistrictName = '"+subdistrict+"'";
			
			System.out.println("[CustomerDao][validateAddress] subdistrict sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    while(rs.next())subdistrictId = rs.getString("subdistrictId").trim();
		    if(subdistrictId==null)throw new EnjoyException("ระบุตำบล/แขวงผิด");
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
			this.db.setDisconnection(rs);
			System.out.println("[CustomerDao][validateAddress][End]");
		}
		return addressBean;
	}
	
	public List<String> idNumberList(String idNumber){
		System.out.println("[CustomerDao][idNumberList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		
		try{
			this.db     = new EnjoyConectDbs();
			sql 		= " select idNumber from customer where idNumber like ('"+idNumber+"%') and cusStatus = 'A' order by idNumber asc limit 10 ";
			
			System.out.println("[CustomerDao][idNumberList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){ 
		    	list.add(EnjoyUtils.nullToStr(rs.getString("idNumber")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection(rs);
			System.out.println("[CustomerDao][idNumberList][End]");
		}
		
		return list;
	}
	
	public List<String> custFullNameList(String custFullName){
		System.out.println("[CustomerDao][custFullNameList][Begin]");
		
		String 							sql			 		= null;
		ResultSet 						rs 					= null;
        List<String> 					list 				= new ArrayList<String>();
		
		try{
			this.db     = new EnjoyConectDbs();
			sql 		= "SELECT CONCAT(cusName, ' ', cusSurname) as fullName from customer where (SELECT CONCAT(cusName, ' ', cusSurname) as fullName) like '"+custFullName+"%' and cusStatus = 'A'  limit 10 ";
			
			System.out.println("[CustomerDao][custFullNameList] sql :: " + sql);
			
		    rs 			= this.db.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    	list.add(EnjoyUtils.nullToStr(rs.getString("fullName")));
		    }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.db.setDisconnection(rs);
			System.out.println("[CustomerDao][custFullNameList][End]");
		}
		
		return list;
	}
	 
}
