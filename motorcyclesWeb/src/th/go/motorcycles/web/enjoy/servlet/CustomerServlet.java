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
import th.go.motorcycles.app.enjoy.dao.EntrySaleDetailDao;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.app.enjoy.form.CustomerForm; 
import th.go.motorcycles.app.enjoy.form.EntrySaleDetailForm;
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
    private CustomerDao				    dao							= null;
    private AddressDao					addressDao					= null; 
    private UserDetailsBean				userBean 					= null;
    
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
 			 
 			if(pageAction.equals("")){
 				System.out.println("[CustomerInsertServlet][onLoad]");   
 				request.setAttribute("target", Constants.PAGE_URL +"/customer_insert.jsp");
 			}else if(pageAction.equals("new")|| pageAction.equals("reset")){ 
 				this.form = new CustomerForm();
 				request.setAttribute("target", Constants.PAGE_URL +"/customer_insert.jsp"); 
 			}else if(pageAction.equals("addRecord")){
 				this.addRecord();
 			}else if(pageAction.equals("updateRecord")){ 
 				this.updateRecord();  
 			}else if(pageAction.equals("findData")){ 
 				this.findData(); 
 				request.setAttribute("target", Constants.PAGE_URL +"/customer_insert.jsp");
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
		 String         postcode        = null;
		 AddressBean	addressBean		= null;
		 JSONObject 	obj 			= new JSONObject();
		 
		try{
			   province					= EnjoyUtils.nullToStr(this.request.getParameter("provinceName"));
			   district					= EnjoyUtils.nullToStr(this.request.getParameter("districtName"));
			   subdistrict				= EnjoyUtils.nullToStr(this.request.getParameter("subdistrictName")); 
			 
			   logger.info("[lp_save] province 			:: " + province);
			   logger.info("[lp_save] district 			:: " + district);
			   logger.info("[lp_save] subdistrict 		:: " + subdistrict);
			   
			   addressBean 		= this.dao.validateAddress(province, district, subdistrict);
			   
			   if(addressBean.getErrMsg().equals("")){ 
				   logger.info("[lp_save] provinceId 			:: " + addressBean.getProvinceId());
				   logger.info("[lp_save] districtId 			:: " + addressBean.getDistrictId());
				   logger.info("[lp_save] subdistrictId 		:: " + addressBean.getSubdistrictId());
				   
			   }else{
				   throw new EnjoyException(addressBean.getErrMsg());
			   }
			   
			   if(addressBean.getErrMsg().equals("")){ 
				  
				   
				    customerBean 	= new CustomerBean();
					custName 		= EnjoyUtils.nullToStr(this.request.getParameter("custName")); 
					custSurname 	= EnjoyUtils.nullToStr(this.request.getParameter("custSurname"));
					houseNumber 	= EnjoyUtils.nullToStr(this.request.getParameter("houseNumber"));
					mooNumber 	    = EnjoyUtils.nullToStr(this.request.getParameter("mooNumber"));
					soiName 	    = EnjoyUtils.nullToStr(this.request.getParameter("soiName"));
					streetName 	    = EnjoyUtils.nullToStr(this.request.getParameter("streetName")); 
					idType 	        = EnjoyUtils.nullToStr(this.request.getParameter("idType"));
					idNumber 	    = EnjoyUtils.nullToStr(this.request.getParameter("idNumber")); 
					postcode        = EnjoyUtils.nullToStr(this.request.getParameter("postcode"));
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
					customerBean.setProvinceName(province);
					customerBean.setDistrictName(district);
					customerBean.setSubdistrictName(subdistrict);
					customerBean.setIdType(idType);
					customerBean.setIdNumber(idNumber);
					customerBean.setCusStatus(cusStatus);
					customerBean.setCusCode(cusCode);
					customerBean.setPostcode(postcode);
					
					cusCode = this.dao.insertCustomer(customerBean);  
					customerBean.setCusCode(cusCode);
					this.form.setCustomerBean(customerBean);
					
					obj.put("status", 	"SUCCESS");
					obj.put("cusCode", 	cusCode);
					obj.put("mode", 	"READONLY");
					logger.info("customerBean:"+customerBean.getCusCode());
				   
			   }  
			   
		   }catch(EnjoyException e){
			   obj.put("status", 			"ERROR");
			   obj.put("errMsg", 			e.getMessage());
		   }catch(Exception e){
			   obj.put("status", 			"ERROR");
			   obj.put("errMsg", 			"เกิดข้อผิดพลาดในการบันทึกข้อมูล");
			   e.printStackTrace();
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
			System.out.println("[CustomerInsertServlet][addRecord][End]");
			this.motorUtil.writeMSG(obj.toString());
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
		 String			province	    = null;
		 String			district		= null;
		 String			subdistrict		= null;
		 String         postcode        = null;
		 AddressBean	addressBean		= null;
		 JSONObject 	obj				= new JSONObject();
		
		try{
			customerBean 	= new CustomerBean();
			cusCode         = EnjoyUtils.nullToStr(this.request.getParameter("cusCode"));
			custName 		= EnjoyUtils.nullToStr(this.request.getParameter("custName")); 
			custSurname 	= EnjoyUtils.nullToStr(this.request.getParameter("custSurname"));
			houseNumber 	= EnjoyUtils.nullToStr(this.request.getParameter("houseNumber"));
			mooNumber 	    = EnjoyUtils.nullToStr(this.request.getParameter("mooNumber"));
			soiName 	    = EnjoyUtils.nullToStr(this.request.getParameter("soiName"));
			streetName 	    = EnjoyUtils.nullToStr(this.request.getParameter("streetName"));
			subdistrictCode = EnjoyUtils.nullToStr(this.request.getParameter("subdistrictCode"));
			districtCode 	= EnjoyUtils.nullToStr(this.request.getParameter("districtCode"));
			provinceCode 	= EnjoyUtils.nullToStr(this.request.getParameter("provinceCode"));
			idType 	        = EnjoyUtils.nullToStr(this.request.getParameter("idType"));
			idNumber 	    = EnjoyUtils.nullToStr(this.request.getParameter("idNumber")); 
			cusStatus 	    = EnjoyUtils.nullToStr(this.request.getParameter("cusStatus"));  
			postcode        = EnjoyUtils.nullToStr(this.request.getParameter("postcode"));
			
			   province					= EnjoyUtils.nullToStr(this.request.getParameter("provinceName"));
			   district					= EnjoyUtils.nullToStr(this.request.getParameter("districtName"));
			   subdistrict				= EnjoyUtils.nullToStr(this.request.getParameter("subdistrictName")); 
			  
			   logger.info("[lp_save] province 			:: " + province);
			   logger.info("[lp_save] district 			:: " + district);
			   logger.info("[lp_save] subdistrict 		:: " + subdistrict);
			   
			   addressBean 		= this.dao.validateAddress(province, district, subdistrict);
			   
			   if(addressBean.getErrMsg().equals("")){ 
				   logger.info("[lp_save] provinceId 			:: " + addressBean.getProvinceId());
				   logger.info("[lp_save] districtId 			:: " + addressBean.getDistrictId());
				   logger.info("[lp_save] subdistrictId 		:: " + addressBean.getSubdistrictId());
			   }else{
				   throw new EnjoyException(addressBean.getErrMsg());
			   }
			    
				customerBean.setCusCode(cusCode);
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
				customerBean.setPostcode(postcode);
	 
			    dataRet	= this.dao.updateCustomer(customerBean);
			    this.dao.findCustomerByCusCode(customerBean, this.form);
			    
			    if(dataRet==true){ 
			    	obj.put("status", 	"SUCCESS");
					obj.put("cusCode", 	 cusCode);  
				} 
			    
		   }catch(EnjoyException e){
			   obj.put("status", 			"ERROR");
			   obj.put("errMsg", 			e.getMessage());
		   }catch(Exception e){
			   obj.put("status", 			"ERROR");
			   obj.put("errMsg", 			"เกิดข้อผิดพลาดในการแก้ไขข้อมูล");
			   e.printStackTrace();
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
			this.motorUtil.writeMSG(obj.toString());
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
			customerBean = (CustomerBean)this.dao.findCustomerByCusCode(bean,this.form);
			System.out.println("[CustomerInsertServlet][findData][Begin]:"+customerBean.getCusCode()); 
			
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
