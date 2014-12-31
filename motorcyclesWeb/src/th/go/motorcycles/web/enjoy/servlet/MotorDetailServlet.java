package th.go.motorcycles.web.enjoy.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import th.go.motorcycles.app.enjoy.bean.MotorDetailBean;
import th.go.motorcycles.app.enjoy.bean.ProductBean;
import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.dao.MotorDetailDao;
import th.go.motorcycles.app.enjoy.form.MotorDetailForm; 
import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;
import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;
import th.go.motorcycles.web.enjoy.utils.MotorUtil;

public class MotorDetailServlet extends EnjoyStandardSvc {
	 
	static final long serialVersionUID = 1L;
	private static final LogWrapper logger = LogWrapper.getLogger(MotorDetailServlet.class);
	
    private static final String FORM_NAME = "motorDetailForm";
    
    private MotorUtil               	motorUtil                   = null;
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
 			 this.motorUtil			= new MotorUtil(request, response);
 			 this.request           = request;
             this.response          = response;
             this.session           = request.getSession(false);
             this.userBean          = (UserDetailsBean)session.getAttribute("userBean");
             this.form              = (MotorDetailForm)session.getAttribute(FORM_NAME);
             this.dao				= new MotorDetailDao();
 			
 			if(pageAction.equals("new")) this.form = new MotorDetailForm();
 			
 			if( pageAction.equals("") || pageAction.equals("new") ){
 				this.onLoad();
 				request.setAttribute("target", Constants.PAGE_URL +"/MotorDetailScn.jsp");
 			}else if(pageAction.equals("searchData")){
 				this.onSearch();
 			}else if(pageAction.equals("getBrandName")){
 				this.getBrandName();
 			}else if(pageAction.equals("getModel")){
 				this.getModel();
 			}else if(pageAction.equals("getCompany")){
 				this.getCompany();
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
		String       	companySearch	= null; 
		boolean	     	dataRet			= false;
		try{
			bean     = new MotorDetailBean(); 
			brandSearch  		= this.request.getParameter("brandSearch"); 
			companySearch     	= this.request.getParameter("companySearch"); 

			bean.setBrandSearch(brandSearch); 
			bean.setCompanySearch(companySearch);
			
			listMotorDetail = (List <MotorDetailBean>) this.dao.findModelDetail(bean); 
			this.form.setListMotorDetail(listMotorDetail); 
			
			if(listMotorDetail.size()>0){
				dataRet	= true;
			}
			
			if(dataRet==true){
				this.motorUtil.writeMSG("OK"); 
			}else{
				this.motorUtil.writeMSG("No record!!");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			listMotorDetail       	= null;
			bean               		= null; 
			brandSearch           	= null;
			companySearch          	= null; 
			dataRet			   		= false;
			System.out.println("[MotorDetailServlet][onSearch][End]");
		}
	}
	
	private void getBrandName(){
		System.out.println("[MotorDemoSvc][getBrandName][Begin]");
		String							brandName				= null;
		List<String> 					list 					= new ArrayList<String>();
		String[]						strArray				= null;
		MotorDetailBean 				motorDetailBean			= null;
	       
	   try{
		   brandName					= EnjoyUtils.nullToStr(this.request.getParameter("brandName"));
		   motorDetailBean				= this.form.getMotorDetailBean();
		   
		   logger.info("[lp_getBrandName] brandName 			:: " + brandName);
		   
		   motorDetailBean.setBrandName(brandName);
		   
		   list 		= this.dao.brandNameList(brandName);
		   strArray 	= new String[list.size()];
		   strArray 	= list.toArray(strArray); 
		   
		   this.motorUtil.writeJsonMSG((String[]) strArray);
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[lp_getBrandName] " + e.getMessage());
	   }finally{
		   logger.info("[lp_getBrandName][End]");
	   }
	   System.out.println("[MotorDemoSvc][getBrandName][End]");
	}
	 
	private void getModel(){
		   logger.info("[getModel][Begin]");
		   
		   String							brandName				= null;
		   String							model					= null;
	       List<String> 					list 					= new ArrayList<String>();
	       String[]							strArray				= null;
	       MotorDetailBean 					motorDetailBean			= null;
	       
		   try{
			   brandName					= EnjoyUtils.nullToStr(this.request.getParameter("brandName"));
			   model						= EnjoyUtils.nullToStr(this.request.getParameter("model"));
			   motorDetailBean				= this.form.getMotorDetailBean();
			   
			   logger.info("[getModel] brandName 		:: " + brandName);
			   logger.info("[getModel] model 			:: " + model);
			   
			   motorDetailBean.setBrandName(brandName);
			   motorDetailBean.setModel(model);
			   
			   list 		= this.dao.modelList(brandName, model);
			   strArray 	= new String[list.size()];
			   strArray 	= list.toArray(strArray); 
			   
			   this.motorUtil.writeJsonMSG((String[]) strArray);
			   
		   }catch(Exception e){
			   e.printStackTrace();
			   logger.info("[getModel] " + e.getMessage());
		   }finally{
			   logger.info("[getModel][End]");
		   }
	   }
	private void getCompany(){
		System.out.println("[MotorDemoSvc][getCompany][Begin]");
		String							branchName				= null;
		List<String> 					list 					= new ArrayList<String>();
		String[]						strArray				= null;
		MotorDetailBean 				motorDetailBean			= null;
	       
	   try{
		   branchName					= EnjoyUtils.nullToStr(this.request.getParameter("branchName"));
		   motorDetailBean				= this.form.getMotorDetailBean();
		   
		   logger.info("[getCompany] branchName 			:: " + branchName);
		   
		   motorDetailBean.setBrandName(branchName);
		   
		   list 		= this.dao.branchNameList(branchName);
		   strArray 	= new String[list.size()];
		   strArray 	= list.toArray(strArray); 
		   
		   this.motorUtil.writeJsonMSG((String[]) strArray);
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[getCompany] " + e.getMessage());
	   }finally{
		   logger.info("[getCompany][End]");
	   }
	   System.out.println("[MotorDemoSvc][getCompany][End]");
	}
}
