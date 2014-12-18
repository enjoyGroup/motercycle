package th.go.motorcycles.web.enjoy.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
  



import org.json.simple.JSONObject;

import th.go.motorcycles.app.enjoy.bean.AddressBean;
import th.go.motorcycles.app.enjoy.bean.CustomerBean; 
import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.dao.AddressDao;
import th.go.motorcycles.app.enjoy.dao.CustomerDao; 
import th.go.motorcycles.app.enjoy.form.CustomerForm; 
import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;
import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;
import th.go.motorcycles.web.enjoy.utils.MotorUtil; 

public class CustomerServlet extends EnjoyStandardSvc {
	 
	static final long serialVersionUID = 1L;
	private static final LogWrapper logger = LogWrapper.getLogger(CustomerServlet.class);
	
    private static final String FORM_NAME = "customerForm";
    //Transaction For address
    private static final String 		PROVINCE 		= "p";
    private static final String 		DISTRICT 		= "d";
    private static final String 		SUBDISTRICT 	= "s";
    
    
    private MotorUtil               	motorUtil                   = null;
    private CustomerForm           	    form                        = null;
    private HttpServletRequest          request                     = null;
    private HttpServletResponse         response                    = null;
    private HttpSession                 session                     = null;
    private UserDetailsBean             userBean                    = null;
    private CustomerDao				    dao							= null;
    private AddressDao					addressDao					= null;
    
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         System.out.println("[CustomerInsertServlet][execute][Begin]");
		
         String pageAction = null;
 		
 		try{
 			 pageAction 			 = MotorUtil.nullToStr(request.getParameter("pageAction")); 
 			 this.motorUtil 		 = new MotorUtil(request, response);
 			 this.request            = request;
             this.response           = response;
             this.session            = request.getSession(false);
             this.userBean           = (UserDetailsBean)session.getAttribute("userBean");
             this.form               = (CustomerForm)session.getAttribute(FORM_NAME);
             this.dao				 = new CustomerDao();
             this.addressDao         = new AddressDao();
 			
 			if(this.form == null || pageAction.equals("new")) this.form = new CustomerForm();
 			
 			if(pageAction.equals("") || pageAction.equals("new")){
 				this.onLoad();
 				request.setAttribute("target", Constants.PAGE_URL +"/customer_insert.jsp");
 			}else if(pageAction.equals("addRecord")){
 				this.addRecord();
 			}else if(pageAction.equals("updateRecord")){ 
 				this.updateRecord(); 
 			}else if(pageAction.equals("findData")){ 
 				this.findData(); 
 			}else if(pageAction.equals(PROVINCE)){
				this.lp_province();
			}else if(pageAction.equals(DISTRICT)){
				this.lp_district();
			}else if(pageAction.equals(SUBDISTRICT)){
				this.lp_subdistrict();
			}
 			
 			
 			session.setAttribute(FORM_NAME, this.form);
 			
 		}catch(Exception e){
 			e.printStackTrace();
 		}finally{
 			System.out.println("[CustomerInsertServlet][execute][End]");
 		}
	}
 
	private void onLoad() throws Exception{ 
		System.out.println("[CustomerInsertServlet][onLoad][Begin]");  
	}
	 
	
	private void addRecord() throws Exception{
		System.out.println("[CustomerInsertServlet][addRecord][Begin]");
		
		 CustomerBean 	customerBean 	= null;
		 String			cusCode		    = null; 
		 String 		custName    	= null; 
		 String 		custSurname 	= null; 
		 String 		houseNumber 	= null; 
		 String 		mooNumber   	= null; 
		 String 		soiName  	    = null;   
         String 		streetName  	= null;    
		 String 		idType 			= null; 
		 String 		idNumber		= null; 
		 String 		cusStatus		= null;
		 boolean	    dataRet			= false; 
		 String			province	    = null;
		 String			district		= null;
		 String			subdistrict		= null;
		 AddressBean	addressBean		= null;
		 JSONObject 	obj 			= null;
		 
		try{
			   province					= EnjoyUtils.nullToStr(this.request.getParameter("provinceName"));
			   district					= EnjoyUtils.nullToStr(this.request.getParameter("districtName"));
			   subdistrict				= EnjoyUtils.nullToStr(this.request.getParameter("subdistrictName")); 
			   obj 						= new JSONObject();
			   logger.info("[lp_save] province 			:: " + province);
			   logger.info("[lp_save] district 			:: " + district);
			   logger.info("[lp_save] subdistrict 		:: " + subdistrict);
			   
			   addressBean 		= this.dao.validateAddress(province, district, subdistrict);
			   
			   if(addressBean.getErrMsg().equals("")){
//				   obj.put("status","SUCCESS");
//				   obj.put("provinceCode",addressBean.getProvinceId());
//				   obj.put("districtCode",addressBean.getDistrictId());
//				   obj.put("subdistrictCode",addressBean.getSubdistrictId());
				   
				   
				   logger.info("[lp_save] provinceId 			:: " + addressBean.getProvinceId());
				   logger.info("[lp_save] districtId 			:: " + addressBean.getDistrictId());
				   logger.info("[lp_save] subdistrictId 		:: " + addressBean.getSubdistrictId());
				   
				    customerBean 	= new CustomerBean();
					custName 		= this.request.getParameter("custName"); 
					custSurname 	= this.request.getParameter("custSurname");
					houseNumber 	= this.request.getParameter("houseNumber");
					mooNumber 	    = this.request.getParameter("mooNumber");
					soiName 	    = this.request.getParameter("soiName");
					streetName 	    = this.request.getParameter("streetName"); 
					idType 	        = this.request.getParameter("idType");
					idNumber 	    = this.request.getParameter("idNumber"); 
					cusStatus 	    = "A";  
					
					customerBean.setCustName(custName); 
					customerBean.setCustSurname(custSurname);
					customerBean.setHouseNumber(houseNumber);
					customerBean.setMooNumber(mooNumber);
					customerBean.setSoiName(soiName);
					customerBean.setStreetName(streetName);
					customerBean.setSubdistrictCode(addressBean.getSubdistrictId());
					customerBean.setDistrictCode(addressBean.getDistrictId());
					customerBean.setProvinceCode(addressBean.getProvinceId());
					customerBean.setIdType(idType);
					customerBean.setIdNumber(idNumber);
					customerBean.setCusStatus(cusStatus);
			
				    cusCode = this.dao.insertCustomer(customerBean); 
					logger.info("customerBean:"+customerBean.getCusCode());
					
//				   if(!cusCode.equals(null)){
				   if(cusCode!=null){
					   //obj.put("cusCode",cusCode);
						this.motorUtil.writeMSG("OK:" + cusCode); 
				   }else{
						this.motorUtil.writeMSG("Insert fail !!");
				   }
				 
			   }else{
				   //obj.put("status", "ERROR");
				   //obj.put("errMsg", addressBean.getErrMsg());
			   } 
			   
		}catch(Exception e){
			//obj.put("status", 			"ERROR");
			//obj.put("errMsg", 			e.getMessage());
			e.printStackTrace();
			logger.info("[lp_save] " + e.getMessage());
			throw new Exception(e.getMessage());
		}finally{
			customerBean 	= null;
			cusCode		    = null; 
			custName    	= null; 
			custSurname 	= null; 
			houseNumber 	= null; 
			mooNumber   	= null; 
			soiName  	    = null;   
	        streetName  	= null;   
			idType 			= null; 
			idNumber		= null; 
			cusStatus		= null;
			province	    = null;
			district		= null;
			subdistrict		= null;
			addressBean		= null;
			obj 			= null;
			System.out.println("[CustomerInsertServlet][addRecord][End]");
		}
	}
	

	private void updateRecord() throws Exception{
		System.out.println("[CustomerInsertServlet][updateRecord][Begin]");
		
		 CustomerBean 	customerBean 	= null;
		 String			cusCode		    = null; 
		 String 		custName    	= null; 
		 String 		custSurname 	= null; 
		 String 		houseNumber 	= null; 
		 String 		mooNumber   	= null; 
		 String 		soiName  	    = null;   
         String 		streetName  	= null;   
		 String 		subdistrictCode = null;   
		 String 		districtCode 	= null;   
		 String 		provinceCode 	= null; 
		 String 		idType 			= null; 
		 String 		idNumber		= null; 
		 String 		cusStatus		= null;
		 boolean	    dataRet			= false; 
		
		try{
			customerBean 	= new CustomerBean();
			cusCode         = this.request.getParameter("cusCode");
			custName 		= this.request.getParameter("custName"); 
			custSurname 	= this.request.getParameter("custSurname");
			houseNumber 	= this.request.getParameter("houseNumber");
			mooNumber 	    = this.request.getParameter("mooNumber");
			soiName 	    = this.request.getParameter("soiName");
			streetName 	    = this.request.getParameter("streetName");
			subdistrictCode = this.request.getParameter("subdistrictCode");
			districtCode 	= this.request.getParameter("districtCode");
			provinceCode 	= this.request.getParameter("provinceCode");
			idType 	        = this.request.getParameter("idType");
			idNumber 	    = this.request.getParameter("idNumber"); 
			cusStatus 	    = this.request.getParameter("cusStatus");  
			
			customerBean.setCusCode(cusCode);
			customerBean.setCustName(custName); 
			customerBean.setCustSurname(custSurname);
			customerBean.setHouseNumber(houseNumber);
			customerBean.setMooNumber(mooNumber);
			customerBean.setSoiName(soiName);
			customerBean.setStreetName(streetName);
			customerBean.setSubdistrictCode(subdistrictCode);
			customerBean.setDistrictCode(districtCode);
			customerBean.setProvinceCode(provinceCode);
			customerBean.setIdType(idType);
			customerBean.setIdNumber(idNumber);
			customerBean.setCusStatus(cusStatus);
 
		    dataRet	= this.dao.updateCustomer(customerBean);
		    if(dataRet==true){
				this.motorUtil.writeMSG("OK:" + customerBean.getCusCode());
				//this.onLoad();
			}else{
				this.motorUtil.writeMSG("updateRecord failed !!");
			}
			 
			 
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			customerBean 	= null; 
			cusCode		    = null; 
			custName    	= null; 
			custSurname 	= null; 
			houseNumber 	= null; 
			mooNumber   	= null; 
			soiName  	    = null;   
	        streetName  	= null;   
			subdistrictCode = null;   
			districtCode 	= null;   
			provinceCode 	= null; 
			idType 			= null; 
			idNumber		= null; 
			cusStatus		= null;
			System.out.println("[CustomerInsertServlet][updateRecord][End]");
		}
	}
	
	
	private CustomerBean findData() throws Exception {
		System.out.println("[CustomerInsertServlet][findData][Begin]");
		CustomerBean customerBean       = null;
		CustomerBean bean               = null; 
		String       cusCode            = null;
		
		
		try{
			bean = new CustomerBean(); 
			cusCode  = this.request.getParameter("cusCode");   
			bean.setCusCode(cusCode);
			
			customerBean = new CustomerBean();
			customerBean = (CustomerBean)this.dao.findCustomerByCusCode(bean); 
			System.out.println("[CustomerInsertServlet][findData][Begin]:"+customerBean.getCusCode());
			this.form.setCustomerBean(customerBean);
			  
			if(customerBean.getCusCode()!=null){ 
				
				this.motorUtil.writeMSG("OK:" +   customerBean.getCusCode()+":"+
											    customerBean.getCusStatus()+":"+
												customerBean.getCustName()+":"+
												customerBean.getCustSurname()+":"+
												customerBean.getHouseNumber()+":"+
												customerBean.getMooNumber()+":"+
												customerBean.getSoiName()+":"+
												customerBean.getStreetName()+":"+
												customerBean.getSubdistrictCode()+":"+
												customerBean.getSubdistrictName()+":"+ 
												customerBean.getDistrictCode()+":"+
												customerBean.getDistrictName()+":"+
												customerBean.getProvinceCode()+":"+
												customerBean.getProvinceName()+":"+
												customerBean.getIdNumber()+":"+
												customerBean.getIdType()); 
			}else{
				this.motorUtil.writeMSG("Insert fail !!");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			customerBean       = null;
		    bean               = null; 
	        cusCode            = null;
			System.out.println("[CustomerInsertServlet][findData][End]");
		}
		return customerBean;
	}
 
	   
	   private void lp_province(){
		   logger.info("[lp_search][Begin]");
		   
		   String							province			= null;
	       List<String> 					list 				= new ArrayList<String>();
	       String[]							strArray			= null;
	       
		   try{
			   province					= EnjoyUtils.nullToStr(this.request.getParameter("province"));
			   
			   logger.info("[lp_province] province 	:: " + province);
			   
			   this.form.getCustomerBean().setProvinceName(province);
			   
			   list 		= this.addressDao.provinceList(province);
			   strArray 	= new String[list.size()];
			   strArray 	= list.toArray(strArray); 
			   
			   this.motorUtil.writeJsonMSG((String[]) strArray);
			   
		   }catch(Exception e){
			   e.printStackTrace();
			   logger.info("[lp_province] " + e.getMessage());
		   }finally{
			   logger.info("[lp_province][End]");
		   }
	   }
	   
	   private void lp_district(){
		   logger.info("[lp_district][Begin]");
		   
		   String							province			= null;
		   String							district			= null;
	       List<String> 					list 				= new ArrayList<String>();
	       String[]							strArray			= null;
	       
		   try{
			   province					= EnjoyUtils.nullToStr(this.request.getParameter("province"));
			   district					= EnjoyUtils.nullToStr(this.request.getParameter("district"));
			   
			   this.form.getCustomerBean().setProvinceName(province);
			   this.form.getCustomerBean().setDistrictName(district);
			   
			   logger.info("[lp_district] province 			:: " + province);
			   logger.info("[lp_district] district 			:: " + district);
			   
			   list 		= this.addressDao.districtList(province, district);
			   strArray 	= new String[list.size()];
			   strArray 	= list.toArray(strArray); 
			   
			   this.motorUtil.writeJsonMSG((String[]) strArray);
			   
		   }catch(Exception e){
			   e.printStackTrace();
			   logger.info("[lp_district] " + e.getMessage());
		   }finally{
			   logger.info("[lp_district][End]");
		   }
	   }
	   
	   private void lp_subdistrict(){
		   logger.info("[subdistrict][Begin]");
		   
		   String							province			= null;
		   String							district			= null;
		   String							subdistrict			= null;
	       List<String> 					list 				= new ArrayList<String>();
	       String[]							strArray			= null;
	       
		   try{
			   province					= EnjoyUtils.nullToStr(this.request.getParameter("province"));
			   district					= EnjoyUtils.nullToStr(this.request.getParameter("district"));
			   subdistrict				= EnjoyUtils.nullToStr(this.request.getParameter("subdistrict"));
			   
			   this.form.getCustomerBean().setProvinceName(province);
			   this.form.getCustomerBean().setDistrictName(district);
			   this.form.getCustomerBean().setSubdistrictName(subdistrict);
			   
			   logger.info("[lp_subdistrict] province 			:: " + province);
			   logger.info("[lp_subdistrict] district 			:: " + district);
			   logger.info("[lp_subdistrict] subdistrict 		:: " + subdistrict);
			   
			   list 		= this.addressDao.subdistrictList(province, district, subdistrict);
			   strArray 	= new String[list.size()];
			   strArray 	= list.toArray(strArray); 
			   
			   this.motorUtil.writeJsonMSG((String[]) strArray);
			   
		   }catch(Exception e){
			   e.printStackTrace();
			   logger.info("[lp_subdistrict] " + e.getMessage());
		   }finally{
			   logger.info("[lp_subdistrict][End]");
		   }
	   }
}
