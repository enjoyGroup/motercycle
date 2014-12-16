package th.go.motorcycles.web.enjoy.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import th.go.motorcycles.app.enjoy.bean.SummarySaleDetailBean;
import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.dao.SummarySaleDetailDao;
import th.go.motorcycles.app.enjoy.form.SummarySaleDetailForm;
import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;
import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;
import th.go.motorcycles.web.enjoy.utils.MotorUtil;

 public class SummarySaleDetailServlet extends EnjoyStandardSvc {
	 
   static final long serialVersionUID = 1L;
   private static final LogWrapper 	logger 		= LogWrapper.getLogger(SummarySaleDetailServlet.class);
   
   private static final String 		FORM_NAME 	= "summarySaleDetailForm";
   
   //Transaction
   private static final String 		SEARCH 		= "search";
   
   private MotorUtil               		motorUtil                   = null;
   private SummarySaleDetailForm        form                        = null;
   private HttpServletRequest          	request                     = null;
   private HttpServletResponse         	response                    = null;
   private HttpSession                 	session                     = null;
   private UserDetailsBean             	userDetailsBean             = null;
   private SummarySaleDetailDao			dao							= null;

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response)throws Exception {
	   logger.info("[SummarySaleDetailServlet][execute][Begin]");
		
	   String pageAction = null;
		
		try{
			pageAction 				= MotorUtil.nullToStr(request.getParameter("pageAction"));
			
			logger.info("[SummarySaleDetailServlet][execute] pageAction :: " + pageAction);
			
			this.motorUtil 			= new MotorUtil(request, response);
			this.request            = request;
			this.response           = response;
			this.session            = request.getSession(false);
			this.userDetailsBean    = (UserDetailsBean)session.getAttribute("userDetailsBean");
			this.form               = (SummarySaleDetailForm)session.getAttribute(FORM_NAME);
			this.dao				= new SummarySaleDetailDao();
			
			if(this.form == null || pageAction.equals("new")) this.form = new SummarySaleDetailForm();
			
			if(pageAction.equals("") || pageAction.equals("new")){
				request.setAttribute("target", Constants.PAGE_URL + "/SummarySaleDetailScn.jsp");
			}else if(pageAction.equals(SEARCH)){
				this.lp_search();
//				request.setAttribute("target", Constants.PAGE_URL + "/SummarySaleDetailScn.jsp");
			}
			
			session.setAttribute(FORM_NAME, this.form);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[SummarySaleDetailServlet][execute] " + e.getMessage());
		}finally{
			logger.info("[SummarySaleDetailServlet][execute][End]");
		}
   }
   
   private void lp_search(){
	   logger.info("[SummarySaleDetailServlet][lp_search][Begin]");
	   
	   String							invoiceId			= null;
       String							invoiceDateFrom		= null;
       String							invoiceDateTo		= null;
       String							brandName			= null;
       String							model				= null;
       String							cusName				= null;
       List<SummarySaleDetailBean> 		list 				= new ArrayList<SummarySaleDetailBean>();
       
	   try{
		   invoiceId					= EnjoyUtils.nullToStr(this.request.getParameter("invoiceId"));
		   invoiceDateFrom				= EnjoyUtils.nullToStr(this.request.getParameter("invoiceDateFrom"));
		   invoiceDateTo				= EnjoyUtils.nullToStr(this.request.getParameter("invoiceDateTo"));
		   brandName					= EnjoyUtils.nullToStr(this.request.getParameter("brandName"));
		   model						= EnjoyUtils.nullToStr(this.request.getParameter("model"));
		   cusName						= EnjoyUtils.nullToStr(this.request.getParameter("cusName"));
		   
		   logger.info("[SummarySaleDetailServlet][execute] invoiceId 			:: " + invoiceId);
		   logger.info("[SummarySaleDetailServlet][execute] invoiceDateFrom 	:: " + invoiceDateFrom);
		   logger.info("[SummarySaleDetailServlet][execute] invoiceDateTo 		:: " + invoiceDateTo);
		   logger.info("[SummarySaleDetailServlet][execute] brandName 			:: " + brandName);
		   logger.info("[SummarySaleDetailServlet][execute] model 				:: " + model);
		   logger.info("[SummarySaleDetailServlet][execute] cusName 			:: " + cusName);
		   
		   this.form.setInvoiceId(invoiceId);
		   this.form.setInvoiceDateFrom(invoiceDateFrom);
		   this.form.setInvoiceDateTo(invoiceDateTo);
		   this.form.setBrandName(brandName);
		   this.form.setModel(model);
		   this.form.setCusName(cusName);
		   
		   list = this.dao.searchSaleDetails(this.form);
		   
		   this.form.setDataList(list);
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[SummarySaleDetailServlet][lp_search] " + e.getMessage());
	   }finally{
		   logger.info("[SummarySaleDetailServlet][lp_search][End]");
	   }
   }
   
   
   
   
}
 
 
 
 
 
 
 
 
 
 