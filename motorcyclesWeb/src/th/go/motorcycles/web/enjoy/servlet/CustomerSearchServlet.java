package th.go.motorcycles.web.enjoy.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;
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
         System.out.println("[CustomerSearchServlet][execute][Begin]");
		
         String pageAction = null; 
 		
 		try{
 			 pageAction 				= MotorUtil.nullToStr(request.getParameter("pageAction")); 
 			 this.easUtil 			= new MotorUtil(request, response);
 			 this.request            = request;
             this.response           = response;
             this.session            = request.getSession(false);
             this.userBean           = (UserDetailsBean)session.getAttribute("userBean");
             this.form               = (CustomerForm)session.getAttribute(FORM_NAME);
             this.dao				= new CustomerDao();
 			
 			if(pageAction.equals("new")) this.form = new CustomerForm();
 			
 			if(pageAction.equals("")){
 				System.out.println("[CustomerSearchServlet][onLoad][Begin]");   
 				request.setAttribute("target", Constants.PAGE_URL +"/customer_detail.jsp");
 			}else if(pageAction.equals("searchData")){
 				this.onSearch();
 			}else if(pageAction.equals("delRecord")){
 				this.delRecord();
 			} 
 			
 			session.setAttribute(FORM_NAME, this.form);
 			
 		}catch(Exception e){
 			e.printStackTrace();
 		}finally{
 			System.out.println("[CustomerSearchServlet][execute][End]");
 		}
	}
  
	 
	private void onSearch() throws Exception{ 
		System.out.println("[CustomerSearchServlet][onSearch][Begin]");
		List<CustomerBean> listCustomer = null;
		CustomerBean bean               = null; 
		String       idNumber           = null;
		String       name               = null; 
		boolean	     dataRet			= false;
		try{
			bean     = new CustomerBean(); 
			idNumber = EnjoyUtils.nullToStr(this.request.getParameter("idNumber")); 
			name     = EnjoyUtils.nullToStr(this.request.getParameter("fullName")); 

			bean.setIdNumber(idNumber); 
			bean.setCustFullname(name); 
			
			listCustomer = (List <CustomerBean>) this.dao.findCustomer(bean); 
			this.form.setListCustomer(listCustomer); 
			
			if(listCustomer.size()>0){
				dataRet	= true;
			}
			
			if(dataRet==true){
				this.easUtil.writeMSG("OK"); 
			}else{
				this.easUtil.writeMSG("No record!!");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			listCustomer       = null;
			bean               = null; 
			idNumber           = null;
			name               = null; 
			dataRet			   = false;
			System.out.println("[CustomerSearchServlet][onSearch][End]");
		}
	}
	 
	private void delRecord() throws Exception{
		System.out.println("[CustomerSearchServlet][delRecord][Begin]");
		
		CustomerBean 	customerBean 	= null;
		String			cusCode			= null;
		boolean			dataRet			= false;
		
		try{
			customerBean 	= new CustomerBean();
			cusCode	 		= EnjoyUtils.nullToStr(this.request.getParameter("cusCode"));
			
			customerBean.setCusCode(cusCode);
			customerBean.setCusStatus("I");
			
			if(customerBean.getCusCode()!="" || customerBean.getCusCode()!=null){
				dataRet = this.dao.deleteCustomer(customerBean);
			}
			 
			if(dataRet==true){
				this.easUtil.writeMSG("OK");
				this.onSearchAll(); 
			}else{
				this.easUtil.writeMSG("CustomerSearchServlet Delete failed !!");
			}
			 
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			customerBean 	= null;
			cusCode			= null;
			dataRet			= false;
			System.out.println("[CustomerSearchServlet][delRecord][End]");
		}
	}
	
	
	private void onSearchAll() throws Exception{ 
		System.out.println("[CustomerSearchServlet][onSearchAll][Begin]");
		List<CustomerBean> listCustomer = null; 
		boolean	     dataRet			= false;
		try{ 
			
			listCustomer = (List <CustomerBean>) this.dao.findAllCustomer(); 
			this.form.setListCustomer(listCustomer); 
			
			if(listCustomer.size()>0){
				dataRet	= true;
			}
			
			if(dataRet==true){
				this.easUtil.writeMSG("OK"); 
			}else{
				this.easUtil.writeMSG("No record!!");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			listCustomer    = null; 
			dataRet			= false;
			System.out.println("[CustomerSearchServlet][onSearchAll][End]");
		}
	}
	
	
}
