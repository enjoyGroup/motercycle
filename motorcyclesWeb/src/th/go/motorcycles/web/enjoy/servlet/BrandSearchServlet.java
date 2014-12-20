package th.go.motorcycles.web.enjoy.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;

public class BrandSearchServlet extends EnjoyStandardSvc {
	 
	static final long serialVersionUID = 1L;
	private static final LogWrapper logger = LogWrapper.getLogger(BrandSearchServlet.class);
	
    private static final String FORM_NAME = "brandForm";
    
//    private MotorUtil               	easUtil                     = null;
//    private BrandForm           	    form                        = null;
//    private HttpServletRequest          request                     = null;
//    private HttpServletResponse         response                    = null;
//    private HttpSession                 session                     = null;
//    private UserDetailsBean             userBean                    = null;
//    private BrandDao				    dao							= null;
    
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
//		doProcess(request, response);
	}
	
//	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         System.out.println("[BrandInsertServlet][execute][Begin]");
//		
//         String pageAction = null; 
// 		
// 		try{
// 			 pageAction 				= MotorUtil.nullToStr(request.getParameter("pageAction"));
// 			 System.out.println("pageAction : "+pageAction);
// 			 this.easUtil 			= new MotorUtil(request, response);
// 			 this.request            = request;
//             this.response           = response;
//             this.session            = request.getSession(false);
//             this.userBean           = (UserDetailsBean)session.getAttribute("userBean");
//             this.form               = (BrandForm)session.getAttribute(FORM_NAME);
//             this.dao				= new BrandDao();
// 			
// 			if(pageAction.equals("new")) this.form = new BrandForm();
// 			
// 			if(pageAction.equals("")){
//// 				this.onLoad();
//// 				request.setAttribute("target", Constants.PAGE_URL +"/customer_detail.jsp");
// 				this.onSearchAll();
// 			}else if(pageAction.equals("searchData")){
// 				this.onSearch();
// 			}else if(pageAction.equals("delRecord")){
// 				this.delRecord();
// 			} 
// 			
// 			session.setAttribute(FORM_NAME, this.form);
// 			
// 		}catch(Exception e){
// 			e.printStackTrace();
// 		}finally{
// 			System.out.println("[BrandInsertServlet][execute][End]");
// 		}
//	}
// 
//	private void onLoad() throws Exception{ 
//		System.out.println("[BrandInsertServlet][onLoad][Begin]"); 
//		
//	}
//	 
//	private void onSearch() throws Exception{ 
//		System.out.println("[BrandInsertServlet][onSearch][Begin]");
//		List<BrandBean> listBrand = null;
//		BrandBean bean               = null; 
//		String       brandCode            = null;
//		String       name               = null; 
//		boolean	     dataRet			= false;
////		try{
////			bean     = new BrandBean(); 
////			brandCode  = this.request.getParameter("brandCode"); 
////			name     = this.request.getParameter("fullName"); 
////
////			bean.setBrandCode(brandCode); 
////			bean.setCustFullname(name); 
////			
////			listBrand = (List <BrandBean>) this.dao.findBrand(bean); 
////			this.form.setListBrand(listBrand); 
////			
////			if(listBrand.size()>0){
////				dataRet	= true;
////			}
////			
////			if(dataRet==true){
////				this.easUtil.writeMSG("OK"); 
////			}else{
////				this.easUtil.writeMSG("No record!!");
////			}
////			
////		}catch(Exception e){
////			e.printStackTrace();
////			throw new Exception(e.getMessage());
////		}finally{
////			listBrand       = null;
////			bean               = null; 
////			brandCode            = null;
////			name               = null; 
////			dataRet			   = false;
////			System.out.println("[BrandInsertServlet][onSearch][End]");
////		}
//	}
//	 
//	private void delRecord() throws Exception{
//		System.out.println("[MotorDemoSvc][delRecord][Begin]");
//		
//		BrandBean 	brandBean 	= null;
//		String			brandCode			= null;
//		boolean			dataRet			= false;
//		
////		try{
////			brandBean 	= new BrandBean();
////			brandCode	 		= this.request.getParameter("brandCode");
////			
////			brandBean.setBrandCode(brandCode);
////			brandBean.setCusStatus("I");
////			
////			if(brandBean.getBrandCode()!="" || brandBean.getBrandCode()!=null){
////				dataRet = this.dao.deleteBrand(brandBean);
////			}
////			 
////			if(dataRet==true){
////				this.easUtil.writeMSG("OK");
////				this.onSearchAll(); 
////			}else{
////				this.easUtil.writeMSG("Delete failed !!");
////			}
////			 
////			
////		}catch(Exception e){
////			e.printStackTrace();
////			throw new Exception(e.getMessage());
////		}finally{
////			brandBean 	= null;
////			brandCode			= null;
////			dataRet			= false;
////			System.out.println("[MotorDemoSvc][delRecord][End]");
////		}
//	}
//	
//	
//	private void onSearchAll() throws Exception{ 
//		System.out.println("[BrandInsertServlet][onSearchAll][Begin]");
//		List<BrandBean> listBrand = null; 
//		boolean	     dataRet			= false;
//		try{ 
//			
//			listBrand = (List <BrandBean>) this.dao.findAllBrand(); 
//			this.form.setListBrand(listBrand); 
//			
//			if(listBrand.size()>0){
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
//			listBrand    = null; 
//			dataRet			= false;
//			System.out.println("[BrandInsertServlet][onSearchAll][End]");
//		}
//	}
	
	
}