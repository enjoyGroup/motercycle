package th.go.motorcycles.web.enjoy.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import th.go.motorcycles.app.enjoy.bean.MotorDetailBean;
import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.dao.MotorDetailDao;
import th.go.motorcycles.app.enjoy.form.MotorDetailForm; 
import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;
import th.go.motorcycles.web.enjoy.utils.MotorUtil;

public class MotorDetailServlet extends EnjoyStandardSvc {
	 
	static final long serialVersionUID = 1L;
	private static final LogWrapper logger = LogWrapper.getLogger(MotorDetailServlet.class);
	
    private static final String FORM_NAME = "motorDetailForm";
    
    private MotorUtil               	easUtil                     = null;
    private MotorDetailForm           	form                        = null;
    private HttpServletRequest          request                     = null;
    private HttpServletResponse         response                    = null;
    private HttpSession                 session                     = null;
    private UserDetailsBean             userBean                    = null;
    private MotorDetailDao			    dao							= null;
    
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         System.out.println("[MotorDetailServlet][execute][Begin]");
		
         String pageAction = null; 
 		
 		try{
 			 pageAction 				= MotorUtil.nullToStr(request.getParameter("pageAction"));
 			 System.out.println("pageAction : " + pageAction);
 			 this.easUtil 			= new MotorUtil(request, response);
 			 this.request            = request;
             this.response           = response;
             this.session            = request.getSession(false);
             this.userBean           = (UserDetailsBean)session.getAttribute("userBean");
             this.form               = (MotorDetailForm)session.getAttribute(FORM_NAME);
             this.dao				= new MotorDetailDao();
 			
 			if(pageAction.equals("new")) this.form = new MotorDetailForm();
 			
 			if(pageAction.equals("")){
 				this.onLoad();
 				request.setAttribute("target", Constants.PAGE_URL +"/MotorDetailScn.jsp");
 			}else if(pageAction.equals("searchData")){
 				this.onSearch();
 			}else if(pageAction.equals("delRecord")){
 			//	this.delRecord();
 			} 
 			
 			session.setAttribute(FORM_NAME, this.form);
 			
 		}catch(Exception e){
 			e.printStackTrace();
 		}finally{
 			System.out.println("[MotorDetailServlet][execute][End]");
 		}
	}
 
	private void onLoad() throws Exception{ 
		System.out.println("[MotorDetailServlet][onLoad][Begin]"); 		
	}
	 
	private void onSearch() throws Exception{ 
		System.out.println("[MotorDetailServlet][onSearch][Begin]");
		List<MotorDetailBean> listMotorDetail = null;
		MotorDetailBean bean			= null; 
		String       	brandSearch		= null;
		String       	modelSearch		= null; 
		boolean	     	dataRet			= false;
		try{
			bean     = new MotorDetailBean(); 
			brandSearch  	= this.request.getParameter("brandSearch"); 
			modelSearch     = this.request.getParameter("modelSearch"); 

			bean.setBrandSearch(brandSearch); 
			bean.setModelSearch(modelSearch); 
			
			listMotorDetail = (List <MotorDetailBean>) this.dao.findModelDetail(bean); 
			this.form.setListMotorDetail(listMotorDetail); 
			
			if(listMotorDetail.size()>0){
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
			listMotorDetail       	= null;
			bean               		= null; 
			brandSearch           	= null;
			modelSearch            	= null; 
			dataRet			   		= false;
			System.out.println("[MotorDetailServlet][onSearch][End]");
		}
	}
	 
//	private void delRecord() throws Exception{
//		System.out.println("[MotorDemoSvc][delRecord][Begin]");
//		
//		MotorDetailBean 	motorDetailBean 	= null;
//		String			cusCode			= null;
//		boolean			dataRet			= false;
//		
//		try{
//			motorDetailBean 	= new MotorDetailBean();
//			cusCode	 		= this.request.getParameter("cusCode");
//			
//			motorDetailBean.setCusCode(cusCode);
//			motorDetailBean.setCusStatus("I");
//			
//			if(motorDetailBean.getCusCode()!="" || motorDetailBean.getCusCode()!=null){
//				dataRet = this.dao.deleteCustomer(motorDetailBean);
//			}
//			 
//			if(dataRet==true){
//				this.easUtil.writeMSG("OK");
//				this.onSearchAll(); 
//			}else{
//				this.easUtil.writeMSG("Delete failed !!");
//			}
//			 
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			throw new Exception(e.getMessage());
//		}finally{
//			motorDetailBean 	= null;
//			cusCode			= null;
//			dataRet			= false;
//			System.out.println("[MotorDemoSvc][delRecord][End]");
//		}
//	}
	
	
//	private void onSearchAll() throws Exception{ 
//		System.out.println("[MotorDetailServlet][onSearchAll][Begin]");
//		List<MotorDetailBean> listMotorDetail = null; 
//		boolean	     dataRet			= false;
//		try{ 
//			
//			listMotorDetail = (List <MotorDetailBean>) this.dao.findAllCustomer(); 
//			this.form.setListMotorDetail(listMotorDetail); 
//			
//			if(listMotorDetail.size()>0){
//				dataRet	= true;
//			}
//			
//			if(dataRet==true){
//				this.easUtil.writeMSG("OK"); 
//			}else{
//				this.easUtil.writeMSG("No record!!");
//			}
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			throw new Exception(e.getMessage());
//		}finally{
//			listMotorDetail    = null; 
//			dataRet			= false;
//			System.out.println("[MotorDetailServlet][onSearchAll][End]");
//		}
//	}
	
	
}
