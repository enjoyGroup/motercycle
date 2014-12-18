package th.go.motorcycles.web.enjoy.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import th.go.motorcycles.app.enjoy.bean.SummarySaleDetailBean;
import th.go.motorcycles.app.enjoy.dao.EntrySaleDetailDao;
import th.go.motorcycles.app.enjoy.dao.SummarySaleDetailDao;
import th.go.motorcycles.app.enjoy.form.EntrySaleDetailForm;
import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;
import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;
import th.go.motorcycles.web.enjoy.utils.MotorUtil;

 public class EntrySaleDetailServlet extends EnjoyStandardSvc {
	 
   static final long serialVersionUID = 1L;
   private static final LogWrapper 	logger 		= LogWrapper.getLogger(EntrySaleDetailServlet.class);
   
   private static final String 		FORM_NAME 	= "entrySaleDetailForm";
   
   //Transaction
   private static final String 		NEW 		= "new";
   private static final String 		EDIT 		= "edit";
   
   private MotorUtil               		motorUtil                   = null;
   private EntrySaleDetailForm	        form                        = null;
   private HttpServletRequest          	request                     = null;
   private HttpServletResponse         	response                    = null;
   private HttpSession                 	session                     = null;
   private EntrySaleDetailDao			dao							= null;

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response)throws Exception {
	   logger.info("[execute][Begin]");
		
	   String pageAction = null;
		
		try{
			pageAction 				= MotorUtil.nullToStr(request.getParameter("pageAction"));
			
			logger.info("[execute] pageAction :: " + pageAction);
			
			this.motorUtil 			= new MotorUtil(request, response);
			this.request            = request;
			this.response           = response;
			this.session            = request.getSession(false);
			this.form               = (EntrySaleDetailForm)session.getAttribute(FORM_NAME);
			this.dao				= new EntrySaleDetailDao();
			
			if(this.form == null || pageAction.equals(NEW)) this.form = new EntrySaleDetailForm();
			
			if(pageAction.equals("") || pageAction.equals(NEW)){
				request.setAttribute("target", Constants.PAGE_URL + "/EntrySaleDetailScn.jsp");
			}else if(pageAction.equals(EDIT)){
				this.lp_getData();
				request.setAttribute("target", Constants.PAGE_URL + "/EntrySaleDetailScn.jsp");
			}
			
			session.setAttribute(FORM_NAME, this.form);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[execute] " + e.getMessage());
		}finally{
			logger.info("[execute][End]");
		}
   }
   
   private void lp_getData(){
	   logger.info("[lp_getData][Begin]");
	   
	   String							invoiceId			= null;
       
	   try{
		   invoiceId					= EnjoyUtils.nullToStr(this.request.getParameter("invoiceId"));
		   
		   logger.info("[lp_getData] invoiceId 			:: " + invoiceId);
		   
		   this.dao.setSaleDetail(invoiceId, this.form);
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[lp_getData] " + e.getMessage());
	   }finally{
		   logger.info("[lp_getData][End]");
	   }
   }
   
   
   
   
}
 
 
 
 
 
 
 
 
 
 