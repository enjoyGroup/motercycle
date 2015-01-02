package th.go.motorcycles.web.enjoy.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import th.go.motorcycles.app.enjoy.bean.AddressBean;
import th.go.motorcycles.app.enjoy.bean.BrandBean;
import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.bean.MotorDetailBean;
import th.go.motorcycles.app.enjoy.bean.ProductBean;
import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.dao.MotorDetailDao;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.app.enjoy.form.MotorDetailForm; 
import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.app.enjoy.utils.EnjoyConectDbs;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;
import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;
import th.go.motorcycles.web.enjoy.utils.MotorUtil;

public class MotorDetailServlet<E> extends EnjoyStandardSvc {
	 
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
 			
 			 
 			if( pageAction.equals("")){
 				System.out.println("[MotorDetailServlet][onLoad][Begin]"); 		
 				request.setAttribute("target", Constants.PAGE_URL +"/MotorDetailScn.jsp");
 			}else if(pageAction.equals("new")){
 				this.form = new MotorDetailForm();
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
 				this.delRecord();
 			}else if(pageAction.equals("saveUpdData")){
 				this.saveUpdData();
 			}
 			
 			session.setAttribute(FORM_NAME, this.form);
 			
 		}catch(Exception e){
 			e.printStackTrace();
 		}finally{
 			System.out.println("[MotorDetailServlet][execute][End]");
 		}
	}
 


	private void onSearch() throws Exception{ 
		System.out.println("[MotorDetailServlet][onSearch][Begin]");
		List<MotorDetailBean> listMotorDetail = null;
		MotorDetailBean bean			= null; 
		MotorDetailBean beanResult		= null; 
		String       	brandSearch		= null;
		String       	companySearch	= null; 
		String       	brandCode		= null;
		String       	companyId   	= null; 
		boolean	     	dataRet			= false;
		try{
			bean     = new MotorDetailBean(); 
			brandSearch  		= this.request.getParameter("brandSearch"); 
			companySearch     	= this.request.getParameter("companySearch"); 

			bean.setBrandSearch(brandSearch); 
			bean.setCompanySearch(companySearch);
			
			listMotorDetail = (List <MotorDetailBean>) this.dao.findModelDetail(bean,this.form); 
			
			if(listMotorDetail.size()>0){
				
				this.form.setListMotorDetail(listMotorDetail);   
				brandCode = listMotorDetail.get(0).getBrandCode();
				companyId = listMotorDetail.get(0).getCompanyId();
				
				beanResult = new MotorDetailBean();
				beanResult.setBrandSearch(brandSearch);
				beanResult.setCompanySearch(companySearch);
				beanResult.setBrandCode(brandCode);
				beanResult.setCompanyId(companyId);
				this.form.setMotorDetailBean(beanResult);
			 
				dataRet	= true;
			}
			
			if(dataRet==true){
				this.motorUtil.writeMSG("OK:"+brandSearch+":"+companySearch); 
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
	
	private void saveUpdData() throws Exception{
		System.out.println("[MotorDetailServlet][saveUpdRecord][Begin]");
		
		String				   motorCode	    = null;
		String				   motorDelete	    = null;
		String				   brandCode	    = null; 
		String				   brandName	    = null; 
		String				   model 	        = null; 
		String				   chassis	        = null; 
		String				   engineNo	        = null; 
		String				   size	            = null; 
		String				   companyId	    = null; 
		String				   companyName	    = null; 
		String[]			   getMotorCode	    = null;
		String[]			   getBrandCode	    = null;
		String[]			   getBrandName	    = null;
		String[]			   getModel 	    = null;
		String[]			   getChassis	    = null; 
		String[]			   getEngineNo	    = null; 
		String[]			   getSize	        = null;
		String[]			   getCompanyId     = null;
		String[]			   getCompanyName	= null;
		String[]			   getMotorStatus	= null;
		String[]               motorCodes       = null;
		MotorDetailBean 	   bean 		    = null;
		BrandBean 	           brandBean 		= null;
		MotorDetailBean 	   companyBean 		= null;
		List<MotorDetailBean>  list			    = null;	
		boolean				   dataRet		    = false;
		JSONObject 			   obj 			    = new JSONObject();
		
		try{	
			/*
			Enumeration<E> enum1 = (Enumeration<E>)request.getParameterNames();
			String key, value;
			while (enum1.hasMoreElements()){
				key   = (String) enum1.nextElement();
				value = request.getParameter(key);
				System.out.println(" key = " + key + ", value = "+value);
			}
			*/
		
			getMotorCode		= this.request.getParameterValues("hidMotorcyclesCode");  
			getBrandCode		= this.request.getParameterValues("hidBrandCode"); 
			getBrandName		= this.request.getParameterValues("brandName"); 
			getModel 		    = this.request.getParameterValues("model"); 
			getChassis		    = this.request.getParameterValues("chassis"); 
			getEngineNo		    = this.request.getParameterValues("engineNo"); 
			getSize		        = this.request.getParameterValues("size"); 
			getCompanyId		= this.request.getParameterValues("hidCompanyId"); 
			getCompanyName		= this.request.getParameterValues("companyName");  
			getMotorStatus		= this.request.getParameterValues("hidMotorStartus");

			System.out.println("[MotorDetailServlet][MoterCode . length : ]" + getMotorCode.length);
			
			for(int i=0; i < getMotorCode.length; i++){ 
			   	System.out.println( " MotorDetailServlet == > " + getMotorCode[i] );
			   	motorCode	    = EnjoyUtils.nullToStr(getMotorCode[i]);
				brandCode	    = EnjoyUtils.nullToStr(getBrandCode[i]); 
				brandName	    = EnjoyUtils.nullToStr(getBrandName[i]); 
				model 	        = EnjoyUtils.nullToStrUpperCase(getModel[i]); 
				chassis	        = EnjoyUtils.nullToStrUpperCase(getChassis[i]); 
				engineNo	    = EnjoyUtils.nullToStrUpperCase(getEngineNo[i]); 
				size	        = EnjoyUtils.nullToStr(getSize[i]); 
				companyId	    = EnjoyUtils.nullToStr(getCompanyId[i]); 
				companyName     = EnjoyUtils.nullToStr(getCompanyName[i]);
			   	bean	= new MotorDetailBean();   	
			   	bean.setMotorcyclesCode(motorCode); 
		   		bean.setBrandCode(brandCode);
		   		bean.setBrandName(brandName.toUpperCase());
		   		bean.setModel(model);
		   		bean.setChassis(chassis);
		   		bean.setEngineNo(engineNo);
		   		bean.setSize(size);
		   		bean.setCompanyId(companyId);
		   		bean.setCompanyName(companyName.toUpperCase());  
			 
			   logger.info("[lp_save] brandName  :: " + brandName);
			   logger.info("[lp_save] companyName 	:: " + companyName); 
			   
			   brandBean 		= this.dao.validateBrandName(brandName);
			   
			   if(brandBean.getErrMsg().equals("")){ 
				   logger.info("[lp_save] BrandCode :: " + brandBean.getBrandCode()); 
			   }else{
				   throw new EnjoyException(brandBean.getErrMsg());
			   }
			   
			   companyBean 		= this.dao.validateCompanyName(companyName);
			   
			   if(companyBean.getErrMsg().equals("")){ 
				   logger.info("[lp_save] CompanyId :: " + companyBean.getCompanyId()); 
			   }else{
				   throw new EnjoyException(brandBean.getErrMsg());
			   }
			   
			   
			   if(brandBean.getErrMsg().equals("")){ 
				   	if(getMotorStatus[i].equals("U")){//update
				   		System.out.println( " MotorDetailServlet update  : " + bean.toString() );  
				   		bean.setBrandCode(brandBean.getBrandCode());
				   	  //bean.setCompanyId(companyBean.getCompanyId());
				   		
				   		dataRet = this.dao.updateMotorcycles(bean);
				   		
				   		if( dataRet == true ){
							   System.out.println(" update Brand : SUCCESS");
						}
				    
				   	}else if(getMotorStatus[i].equals("N") ){//Add new
				   		System.out.println( " MotorDetailServlet insert  : " + bean.toString() );   
				   		bean.setBrandCode(brandBean.getBrandCode());
				   		//bean.setCompanyId(companyBean.getCompanyId());
				   		
				   		dataRet = this.dao.insertMotorcycles(bean,this.form);
				   		
				   		if( dataRet == true ){
				   			System.out.println(" Insert Brand : SUCCESS");
						}
				   	}
			     }
			}
			   
			
           /*motorCodes = this.request.getParameterValues("motorArrDelCode");
			System.out.println("motorCodeArr :: "+ motorCodes[0]); 

			if(motorCodes.length>0){
				for(int i=0;i<motorCodes.length;i++){
					motorDelete = motorCodes[i];
					System.out.println( " MotorDetailServlet delete  : " + motorDelete ); 
			   		 
			   		dataRet = this.dao.deleteMotorcycles(motorDelete);
			   		
			   		if( dataRet == true ){
						   System.out.println(" delete Brand : SUCCESS");
					}
				}
			}*/
			
			obj.put("status", 	"SUCCESS");
			
		}catch(EnjoyException e){
			obj.put("status", 			"ERROR");
			obj.put("errMsg", 			e.getMessage());
		}catch(Exception e){
			obj.put("status", 			"ERROR");
			obj.put("errMsg", 			"เกิดข้อผิดพลาดในการบันทึกข้อมูล");
			e.printStackTrace();
		}finally{ 
			brandName		= null;
			getMotorCode	= null; 
			getMotorStatus	= null;
			dataRet			= false;
			System.out.println("[MotorDetailServlet][saveUpdRecord][End]");
		}
	}
	
	private void delRecord() throws Exception{
		System.out.println("[MotorDetailServlet][delRecord][Begin]");
		
		MotorDetailBean bean 	        = null;
		String			motorCode		= null;
		boolean			dataRet			= false; 
		String       	brandSearch		= null;
		String       	companySearch	= null; 
		
		try{
			bean 	            = new MotorDetailBean();
			motorCode	 		= EnjoyUtils.nullToStr(this.request.getParameter("motorCode"));
			brandSearch  		= EnjoyUtils.nullToStr(this.request.getParameter("brandSearch")); 
			companySearch     	= EnjoyUtils.nullToStr(this.request.getParameter("companySearch")); 
			
			bean.setMotorcyclesCode(motorCode);
			bean.setBranchName(brandSearch);
			bean.setCompanyName(companySearch);
			
			if(bean.getMotorcyclesCode()!="" || bean.getMotorcyclesCode()!=null){
				dataRet = this.dao.deleteMotorcycles(bean.getMotorcyclesCode());
			}
			 
			if(dataRet==true){
				this.motorUtil.writeMSG("OK");
				this.onSearchAfterDelete(bean);
			}else{
				this.motorUtil.writeMSG("MotorDetailServlet Delete failed !!");
			}
			 
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			bean           	    = null;
			motorCode			= null;
			dataRet			    = false;
			System.out.println("[MotorDetailServlet][delRecord][End]");
		}
	}
	
	private void onSearchAfterDelete(MotorDetailBean bean) throws Exception{ 
		System.out.println("[MotorDetailServlet][onSearchAfterDelete][Begin]");
		List<MotorDetailBean> listMotorDetail = null; 
		MotorDetailBean beanResult		= null; 
		String       	brandCode		= null;
		String       	companyId   	= null; 
		boolean	     	dataRet			= false;
		try{
			 
			listMotorDetail = (List <MotorDetailBean>) this.dao.findModelDetail(bean,this.form); 
			
			if(listMotorDetail.size()>0){
				
				this.form.setListMotorDetail(listMotorDetail);   
				brandCode = listMotorDetail.get(0).getBrandCode();
				companyId = listMotorDetail.get(0).getCompanyId();
				
				beanResult = new MotorDetailBean();
				beanResult.setBrandSearch(bean.getBranchName());
				beanResult.setCompanySearch(bean.getCompanyName());
				beanResult.setBrandCode(brandCode);
				beanResult.setCompanyId(companyId);
				this.form.setMotorDetailBean(beanResult);
			 
				dataRet	= true;
			}
			
			if(dataRet==true){
				this.motorUtil.writeMSG("OK:"+bean.getBranchName()+":"+bean.getCompanyName()); 
			}else{
				this.motorUtil.writeMSG("No record!!");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			listMotorDetail       	= null;
			bean               		= null; 
			dataRet			   		= false;
			System.out.println("[MotorDetailServlet][onSearchAfterDelete][End]");
		}
	}
	
}
