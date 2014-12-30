package th.go.motorcycles.web.enjoy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import th.go.motorcycles.app.enjoy.bean.BrandBean;
import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.dao.BrandDao;
import th.go.motorcycles.app.enjoy.form.BrandForm;
import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;
import th.go.motorcycles.web.enjoy.utils.MotorUtil;

public class BrandServlet extends EnjoyStandardSvc {
	 
	static final long serialVersionUID = 1L;
	private static final LogWrapper logger = LogWrapper.getLogger(BrandServlet.class);
	
    private static final String FORM_NAME = "brandForm";
    
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
         System.out.println("[BrandServlet][execute][Begin]");
		
         String pageAction = null; 
 		
 		try{
 			 pageAction 				= MotorUtil.nullToStr(request.getParameter("pageAction"));
 			 this.easUtil 				= new MotorUtil(request, response);
 			 this.request            	= request;
             this.response           	= response;
             this.session            	= request.getSession(false);
             this.userBean           	= (UserDetailsBean)session.getAttribute("userBean");
             this.form               	= (BrandForm)session.getAttribute(FORM_NAME);
             this.dao					= new BrandDao();
 			
             System.out.println("[BrandServlet][execute][Begin] : " + pageAction );
             
 			if(this.form == null || pageAction.equals("new")) this.form = new BrandForm();
 			
 			if(pageAction.equals("")){
 				this.onLoad();
 				request.setAttribute("target", Constants.PAGE_URL +"/BrandDetailScn.jsp");
 			}else if(pageAction.equals("searchAll")){
 				this.onSearchAll();
 			}else if(pageAction.equals("saveUpdData")){
 				this.saveUpdRecord();
 			}
 			
 			session.setAttribute(FORM_NAME, this.form);
 			
 		}catch(Exception e){
 			e.printStackTrace();
 		}finally{
 			System.out.println("[BrandServlet][execute][End]");
 		}
	}
	
// ****** On Load Page *********
	private void onLoad() throws Exception{ 
		System.out.println("[BrandServlet][onLoad][Begin]"); 
		onSearchAll();
	}

// ****** Search All Data *********
	private void onSearchAll() throws Exception{ 
		System.out.println("[BrandServlet][onSearchAll][Begin]");
		List<BrandBean> listBrand 	= null; 
		boolean	     dataRet		= false;
		
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
			listBrand    = null; 
			dataRet			= false;
			System.out.println("[BrandServlet][onSearchAll][End]");
		}
	}

// ****** Insert Update Data *********
	private void saveUpdRecord() throws Exception{
		System.out.println("[BrandServlet][saveUpdRecord][Begin]");
		
		String				brandCode		= null;
		String				brandName		= null;
		String[]			getBrandCode	= null;
		String[]			getBrandName	= null;
		String[]			getBrandStatus	= null;
		BrandBean 			brandBean 		= null;
		List<BrandBean>		list			= null;	
		boolean				dataRet			= false;
		JSONObject 			obj 			= new JSONObject();
		
		try{		
// ***** Print Parameter **************			 
//			System.out.println(" Data of Request of ActionStart");
//			Enumeration enum1 = request.getParameterNames();
//			String key, value;
//			while (enum1.hasMoreElements()){
//				key   = (String) enum1.nextElement();
//				value = request.getParameter(key);
//				System.out.println(" key = " + key + ", value = "+value);
//			}
			
			getBrandCode		= this.request.getParameterValues("hidBrandCode");
			getBrandName		= this.request.getParameterValues("brandName");
			getBrandStatus		= this.request.getParameterValues("hidBrandStatus");
				
			System.out.println("[BrandServlet][brandName . length == > ]" + getBrandName.length);
			
			for(int i=0; i < getBrandName.length; i++){
				System.out.println( " brandName["+ i + "] == > " + getBrandName[i]);
			   	System.out.println( " brandStatus == > " + getBrandStatus[i] );
			   	
			   	brandBean	= new BrandBean();   	

			   	if( getBrandStatus[i].equals("U")){
			   		brandCode	= getBrandCode[i];
			   		brandName 	= getBrandName[i]; 
			   		
			   		brandBean	= new BrandBean(); 
			   		brandBean.setBrandCode(brandCode); 
			   		brandBean.setBrandName(brandName); 
			   		
			   		dataRet = this.dao.updateBrand(brandBean);
			   		
			   		if( dataRet == true ){
						   System.out.println(" Update Brand : SUCCESS");
					}
			   	}else if( getBrandStatus[i].equals("I") ){
			   		brandName 	= getBrandName[i];
			   		
			   		brandBean	= new BrandBean(); 
			   		brandBean.setBrandName(brandName); 
			   		
			   		dataRet = this.dao.insertBrand(brandBean);
			   		
			   		if( dataRet == true ){
						   System.out.println(" Insert Brand : SUCCESS");
					}
			   	}
			}
			obj.put("status", 	"SUCCESS");
		}catch(Exception e){
			obj.put("status", 			"ERROR");
			obj.put("errMsg", 			"เกิดข้อผิดพลาดในการบันทึกข้อมูล");
			e.printStackTrace();
		}finally{
			brandBean 		= null;
			brandCode		= null;
			brandName		= null;
			getBrandCode	= null;
			getBrandName	= null;
			getBrandStatus	= null;
			dataRet			= false;
			System.out.println("[BrandServlet][saveUpdRecord][End]");
		}
	}
	
}
