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
 			
 			if(this.form == null || pageAction.equals("new")) this.form = new CustomerForm();
 			
 			if(pageAction.equals("")){
 				this.onLoad();
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
 			System.out.println("[CustomerInsertServlet][execute][End]");
 		}
	}
 
	private void onLoad() throws Exception{ 
		System.out.println("[CustomerInsertServlet][onLoad][Begin]"); 
		
	}
	 
	private void onSearch() throws Exception{ 
		System.out.println("[CustomerInsertServlet][onLoad][Begin]");
		List<CustomerBean> listCustomer = null;
		CustomerBean bean               = null; 
		String       cusCode            = null;
		String       name               = null; 
		boolean	     dataRet			= false;
		try{
			bean     = new CustomerBean(); 
			cusCode  = this.request.getParameter("cusCode"); 
			name     = this.request.getParameter("fullName"); 

			bean.setCusCode(cusCode); 
			bean.setCustFullname(name); 
			
			listCustomer = (List <CustomerBean>) this.dao.findCustomer(bean); 
			this.form.setListCustomer(listCustomer); 
			
			if(listCustomer.size()>0){
				dataRet	= true;
			}
			
			if(dataRet==true){
				this.easUtil.writeMSG("OK"); 
			}else{
				//this.easUtil.writeMSG("ไม่พบข้อมูลที่ระบุ!!");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			cusCode            = null;
			bean               = null;
			System.out.println("[CustomerInsertServlet][onLoad][End]");
		}
	}
	
	private void onSearchAll() throws Exception{ 
		System.out.println("[CustomerInsertServlet][onSearchAll][Begin]");
		List<CustomerBean> listCustomer = null;  
		boolean	     dataRet			= false;
		
		try{ 
			listCustomer = (List <CustomerBean>) this.dao.findCustomerAll(); 
			this.form.setListCustomer(listCustomer); 
			
			if(listCustomer.size()>0){
				dataRet	= true;
			}
			
			if(dataRet==true){
				this.easUtil.writeMSG("OK"); 
			}else{
				this.easUtil.writeMSG("search failed!!");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{ 
			System.out.println("[CustomerInsertServlet][onSearchAll][End]");
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
			customerBean.setCusStatus("I");
			
			if(customerBean.getCusCode()!="" || customerBean.getCusCode()!=null){
				dataRet = this.dao.deleteCustomer(customerBean);
			}
			 
			if(dataRet==true){
				this.easUtil.writeMSG("OK");
				this.onSearchAll(); 
			}else{
				this.easUtil.writeMSG("Delete failed !!");
			}
			 
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			System.out.println("[MotorDemoSvc][delRecord][End]");
		}
	}
	
	

	
	
}
