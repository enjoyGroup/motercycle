package th.go.motorcycles.web.enjoy.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import th.go.motorcycles.app.enjoy.bean.BrandBean;
import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.dao.BrandDao;
import th.go.motorcycles.app.enjoy.form.BrandForm; 
import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;
import th.go.motorcycles.web.enjoy.utils.MotorUtil;

public class BrandSearchServlet extends EnjoyStandardSvc {
	 
	static final long serialVersionUID = 1L;
	private static final LogWrapper logger = LogWrapper.getLogger(BrandSearchServlet.class);
	
    private static final String FORM_NAME = "BrandForm";
    
    private MotorUtil               	easUtil                     = null;
    private BrandForm           	    form                        = null;
    private HttpServletRequest          request                     = null;
    private HttpServletResponse         response                    = null;
    private HttpSession                 session                     = null;
    private UserDetailsBean             userBean                    = null;
    private BrandDao				    dao							= null;
    
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         System.out.println("[BrandSearchServlet][execute][Begin]");
		
         String pageAction = null; 
 		
 		try{
 			 pageAction 				= MotorUtil.nullToStr(request.getParameter("pageAction"));
 			 System.out.println("pageAction : "+pageAction);
 			 this.easUtil 				= new MotorUtil(request, response);
 			 this.request            	= request;
             this.response           	= response;
             this.session            	= request.getSession(false);
             this.userBean           	= (UserDetailsBean)session.getAttribute("userBean");
             this.form               	= (BrandForm)session.getAttribute(FORM_NAME);
             this.dao					= new BrandDao();
 			
 			if(pageAction.equals("new")) this.form = new BrandForm();
 			
 			if(pageAction.equals("")){
 				this.onLoad();
 				request.setAttribute("target", Constants.PAGE_URL +"/BrandDetailScn.jsp");
 			}else if(pageAction.equals("searchData")){
 				this.onSearch();
 			}else if(pageAction.equals("delRecord")){
 				this.delRecord();
 			} 
 			
 			session.setAttribute(FORM_NAME, this.form);
 			
 		}catch(Exception e){
 			e.printStackTrace();
 		}finally{
 			System.out.println("[BrandSearchServlet][execute][End]");
 		}
	}
 
	private void onLoad() throws Exception{ 
		System.out.println("[BrandSearchServlet][onSearchAll][Begin]");
		List<BrandBean> listBrand 	= null; 
		boolean	     dataRet		= false;
		try{ 
			
			listBrand = (List <BrandBean>) this.dao.findAllBrand(); 
			
			System.out.println("[BrandSearchServlet][listBrand] : " + listBrand.size());
			
			this.form.setListBrand(listBrand); 
			
			if(listBrand.size()>0){
				dataRet	= true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			listBrand    	= null; 
			dataRet			= false;
			System.out.println("[BrandSearchServlet][onLoad][End]");
		}
	}
	 
	private void onSearch() throws Exception{ 
		System.out.println("[BrandSearchServlet][onSearch][Begin]");
		List<BrandBean> 	listBrand = null;
		BrandBean 			bean               	= null; 
		String       		brandCode           = null;
		String       		brandName           = null; 
		boolean	     		dataRet				= false;
		try{
			bean     	= new BrandBean(); 
			brandCode  	= this.request.getParameter("brandCode"); 
			brandName   = this.request.getParameter("brandName"); 

			bean.setBrandCode(brandCode); 
			bean.setBrandName(brandName); 
			
			listBrand = (List <BrandBean>) this.dao.findBrandByBrandCode(bean);
			this.form.setListBrand(listBrand); 
			
			if(listBrand.size()>0){
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
			listBrand       = null;
			bean            = null; 
			brandCode       = null;
			brandName       = null; 
			dataRet			= false;
			System.out.println("[BrandSearchServlet][onSearch][End]");
		}
	}
	 
	private void delRecord() throws Exception{
		System.out.println("[BrandSearchServlet][delRecord][Begin]");
		
		BrandBean 	brandBean 	= null;
		String			brandCode			= null;
		boolean			dataRet			= false;
		
		try{
			brandBean 	= new BrandBean();
			brandCode	 		= this.request.getParameter("brandCode");
			
			brandBean.setBrandCode(brandCode);
			brandBean.setBrandStatus("I");
			
			if(brandBean.getBrandCode()!="" || brandBean.getBrandCode()!=null){
				dataRet = this.dao.deleteBrand(brandBean);
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
			brandBean 	= null;
			brandCode			= null;
			dataRet			= false;
			System.out.println("[MotorDemoSvc][delRecord][End]");
		}
	}
	
	
	private void onSearchAll() throws Exception{ 
		System.out.println("[BrandSearchServlet][onSearchAll][Begin]");
		List<BrandBean> listBrand = null; 
		boolean	     dataRet			= false;
		try{ 
			
			listBrand = (List <BrandBean>) this.dao.findAllBrand(); 
			this.form.setListBrand(listBrand); 
			
			if(listBrand.size()>0){
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
			listBrand   = null; 
			dataRet		= false;
			System.out.println("[BrandSearchServlet][onSearchAll][End]");
		}
	}
	
	
}
