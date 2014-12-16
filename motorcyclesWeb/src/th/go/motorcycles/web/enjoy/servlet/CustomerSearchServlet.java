package th.go.motorcycles.web.enjoy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.dao.CustomerDao;
import th.go.motorcycles.app.enjoy.form.CustomerForm;
import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;
import th.go.motorcycles.web.enjoy.utils.MotorUtil;

public class CustomerSearchServlet extends EnjoyStandardSvc {
	 
	static final long serialVersionUID = 1L;
	private static final LogWrapper logger = LogWrapper.getLogger(CustomerSearchServlet.class);
	
    private static final String FORM_NAME = "customerForm";
    
    private MotorUtil               	easUtil                     = null;
    private CustomerForm           	    form                        = null;
    private HttpServletRequest          request                     = null;
    private HttpServletResponse         response                    = null;
    private HttpSession                 session                     = null;
    private UserDetailsBean             userBean                    = null;
    private CustomerDao				    dao							= null;
    
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
 			pageAction 				= MotorUtil.nullToStr(request.getParameter("pageAction"));
 			System.out.println("pageAction : "+pageAction);
 			 this.easUtil 			= new MotorUtil(request, response);
 			 this.request            = request;
             this.response           = response;
             this.session            = request.getSession(false);
             this.userBean           = (UserDetailsBean)session.getAttribute("userBean");
             this.form               = (CustomerForm)session.getAttribute(FORM_NAME);
             this.dao				= new CustomerDao();
 			
 			if(this.form == null) this.form = new CustomerForm();
 			
 			if(pageAction.equals("") || pageAction.equals("new")){
 				this.onLoad();
 				request.setAttribute("target", Constants.PAGE_URL +"/customer_detail.jsp");
 			}else if(pageAction.equals("searchData")){
 				this.onSearch();
 			}else if(pageAction.equals("updateData")){
 				this.updateRecord(); 
 			}else if(pageAction.equals("delRecord")){
 				this.delRecord();
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
		List<CustomerBean> listCustomer = null; 
		
		try{ 
			listCustomer = (List <CustomerBean>) this.dao.findCustomerAll(); 
			this.form.setListCustomer(listCustomer); 
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			System.out.println("[CustomerInsertServlet][onLoad][End]");
		}
	}
	

	private void onSearch() throws Exception{ 
		System.out.println("[CustomerInsertServlet][onLoad][Begin]");
		List<CustomerBean> listCustomer = null;
		CustomerBean bean               = null;
		String       custName           = null;
		String       cusCode            = null;
		
		try{
			bean = new CustomerBean(); 
			cusCode  = this.request.getParameter("cusCode");  
			bean.setCusCode(cusCode);
			
			listCustomer = (List <CustomerBean>) this.dao.findCustomer(bean); 
			this.form.setListCustomer(listCustomer); 
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			System.out.println("[CustomerInsertServlet][onLoad][End]");
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
         String 		streetName  	= null;   
		 String 		subdistrictCode = null;   
		 String 		districtCode 	= null;   
		 String 		provinceCode 	= null; 
		 String 		idType 			= null; 
		 String 		idNumber		= null; 
		 String 		cusStatus		= null; 
		 boolean		dataRet			= false;
		try{
			customerBean 	= new CustomerBean();
			cusCode 		= this.request.getParameter("cusCode");
			custName 		= this.request.getParameter("custName");
			custSurname 	= this.request.getParameter("custSurname");
			houseNumber 	= this.request.getParameter("houseNumber");
			mooNumber 	    = this.request.getParameter("mooNumber");
			streetName 	    = this.request.getParameter("streetName");
			subdistrictCode = this.request.getParameter("subdistrictCode");
			districtCode 	= this.request.getParameter("districtCode");
			provinceCode 	= this.request.getParameter("provinceCode");
			idType 	        = this.request.getParameter("idType");
			idNumber 	    = this.request.getParameter("idNumber"); 
			cusStatus 	    = this.request.getParameter("cusStatus"); 
			
			customerBean.setCustName(custName); 
			customerBean.setCustSurname(custSurname);
			customerBean.setHouseNumber(houseNumber);
			customerBean.setMooNumber(mooNumber);
			customerBean.setStreetName(streetName);
			customerBean.setSubdistrictCode(subdistrictCode);
			customerBean.setDistrictCode(districtCode);
			customerBean.setProvinceCode(provinceCode);
			customerBean.setIdType(idType);
			customerBean.setIdNumber(idNumber);
			customerBean.setCusStatus(cusStatus);
			
			dataRet = this.dao.updateCustomer(customerBean);
			
			if(dataRet==true){
				this.easUtil.writeMSG("OK");
			}else{
				this.easUtil.writeMSG("updateRecord failed !!");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			System.out.println("[CustomerInsertServlet][updateRecord][End]");
		}
	}

	
	private void delRecord() throws Exception{
		System.out.println("[MotorDemoSvc][delRecord][Begin]");
		
		CustomerBean 	customerBean 	= null;
		String			cusCode			= null;
		boolean			dataRet			= false;
		
		try{
			customerBean 	= new CustomerBean();
			cusCode	 		= this.request.getParameter("cusCode");
			
			customerBean.setCusCode(cusCode);
			
			if(customerBean.getCusCode()!="" || customerBean.getCusCode()!=null){
				dataRet = this.dao.deleteCustomer(customerBean);
			}
			 
			if(dataRet==true){
				this.easUtil.writeMSG("OK");
			}else{
				this.easUtil.writeMSG("Delent failed !!");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			System.out.println("[MotorDemoSvc][delRecord][End]");
		}
	}
	
}
