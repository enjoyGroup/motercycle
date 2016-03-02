package th.go.motorcycles.web.enjoy.servlet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
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
import th.go.motorcycles.app.enjoy.pdf.ViewPdfMainForm;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;
import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;
import th.go.motorcycles.web.enjoy.utils.MotorUtil;

 public class SummarySaleDetailServlet extends EnjoyStandardSvc {
	 
   static final long serialVersionUID = 1L;
   private static final LogWrapper 	logger 		= LogWrapper.getLogger(SummarySaleDetailServlet.class);
   
   private static final String 		FORM_NAME 	= "summarySaleDetailForm";
   
   //Transaction
   private static final String 		SEARCH 			= "search";
   private static final String 		VIEWPDF 		= "pdf";
   private static final String 		GET_PAGE 		= "getPage";
   private static final String 		GET_NAME_SNAME 	= "getnameSname";
   
   private MotorUtil               		motorUtil                   = null;
   private SummarySaleDetailForm        form                        = null;
   private HttpServletRequest          	request                     = null;
   private HttpServletResponse         	response                    = null;
   private HttpSession                 	session                     = null;
   private UserDetailsBean				userBean 					= null;
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
			this.form               = (SummarySaleDetailForm)session.getAttribute(FORM_NAME);
			this.dao				= new SummarySaleDetailDao();
			this.userBean			= (UserDetailsBean) this.session.getAttribute("userBean");
			
			if(this.form == null || pageAction.equals("new")) this.form = new SummarySaleDetailForm();
			
			logger.info("[execute] userLevel :: " + this.userBean.getUserLevel());
//			this.form.setUserLevel(this.userBean.getUserLevel());
			
			if(pageAction.equals("") || pageAction.equals("new")){
				this.form.setCompany(this.userBean.getFormatInvoie());
				this.lp_setCombo();
				request.setAttribute("target", Constants.PAGE_URL + "/SummarySaleDetailScn.jsp");
			}else if(pageAction.equals(SEARCH)){
				this.lp_search();
//				request.setAttribute("target", Constants.PAGE_URL + "/SummarySaleDetailScn.jsp");lp_getnameSurname
			}else if(pageAction.equals(VIEWPDF)){
				this.lp_viewpdf();
			}else if(pageAction.equals(GET_PAGE)){
				this.lp_getPage();
			}else if(pageAction.equals(GET_NAME_SNAME)){
				this.lp_getnameSurname();
			}
			
			session.setAttribute(FORM_NAME, this.form);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[SummarySaleDetailServlet][execute] " + e.getMessage());
		}finally{
			logger.info("[SummarySaleDetailServlet][execute][End]");
		}
   }
   
   private void lp_setCombo(){
	   logger.info("[lp_setCombo][Begin]");
	   
	   try{
		   this.form.setCompanyComboList(this.dao.companyList());
		   this.form.setTotalRecord("0");
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[SummarySaleDetailServlet][lp_setCombo] " + e.getMessage());
	   }finally{
		   logger.info("[lp_setCombo][End]");
	   }
   }
   
   private void lp_search(){
	   logger.info("[lp_search][Begin]");
	   
	   String							invoiceId			= null;
       String							invoiceDateFrom		= null;
       String							invoiceDateTo		= null;
       String							brandName			= null;
       String							model				= null;
       String							cusName				= null;
       String							company				= null;
       List<SummarySaleDetailBean> 		list 				= new ArrayList<SummarySaleDetailBean>();
       
	   try{
		   invoiceId					= EnjoyUtils.nullToStr(this.request.getParameter("invoiceId"));
		   invoiceDateFrom				= EnjoyUtils.nullToStr(this.request.getParameter("invoiceDateFrom"));
		   invoiceDateTo				= EnjoyUtils.nullToStr(this.request.getParameter("invoiceDateTo"));
		   brandName					= EnjoyUtils.nullToStr(this.request.getParameter("brandName"));
		   model						= EnjoyUtils.nullToStr(this.request.getParameter("model"));
		   cusName						= EnjoyUtils.nullToStr(this.request.getParameter("cusName"));
		   company						= EnjoyUtils.nullToStr(this.request.getParameter("company"));
		   
		   logger.info("[execute] invoiceId 			:: " + invoiceId);
		   logger.info("[execute] invoiceDateFrom 		:: " + invoiceDateFrom);
		   logger.info("[execute] invoiceDateTo 		:: " + invoiceDateTo);
		   logger.info("[execute] brandName 			:: " + brandName);
		   logger.info("[execute] model 				:: " + model);
		   logger.info("[execute] cusName 				:: " + cusName);
		   logger.info("[execute] company	 			:: " + company);
		   
		   this.form.setInvoiceId(invoiceId);
		   this.form.setInvoiceDateFrom(invoiceDateFrom);
		   this.form.setInvoiceDateTo(invoiceDateTo);
		   this.form.setBrandName(brandName);
		   this.form.setModel(model);
		   this.form.setCusName(cusName);
		   this.form.setCompany(company);
		   
		   this.dao.searchSaleDetails(this.form);
		   this.form.setPageNum(1);
		   
		   list = this.form.getHashTable().get(this.form.getPageNum());
		   
		   this.form.setDataList(list);
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[lp_search] " + e.getMessage());
	   }finally{
		   logger.info("[lp_search][End]");
	   }
   }
   
   private void lp_getPage(){
	   logger.info("[lp_getPage][Begin]");
	   
	   int								pageNum				= 1;
	   List<SummarySaleDetailBean> 		list 				= new ArrayList<SummarySaleDetailBean>();
	   
	   try{
		   pageNum					= Integer.parseInt(this.request.getParameter("pageNum"));
		   
		   this.form.setPageNum(pageNum);
		   
		   list = this.form.getHashTable().get(pageNum);
		   this.form.setDataList(list);
		   
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
		   logger.info("[lp_getPage][End]");
	   }
	   
   }
   
   private void lp_viewpdf(){
	   logger.info("[SummarySaleDetailServlet][lp_viewpdf][Begin]");
	   
	   String							invoiceId			= null;
       String							invoiceDateFrom		= null;
       String							invoiceDateTo		= null;
       String							brandName			= null;
       String							model				= null;
       String							cusName				= null;
       ViewPdfMainForm					viewPdfMainForm		= null;
	   DataOutput 						output 				= null;
	   ByteArrayOutputStream			buffer				= null;
	   byte[] 							bytes				= null;
	   UserDetailsBean 					userBean			= null;
       String							company				= null;
	   try{
		   invoiceId					= EnjoyUtils.nullToStr(this.request.getParameter("invoiceId"));
		   invoiceDateFrom				= EnjoyUtils.nullToStr(this.request.getParameter("invoiceDateFrom"));
		   invoiceDateTo				= EnjoyUtils.nullToStr(this.request.getParameter("invoiceDateTo"));
		   brandName					= EnjoyUtils.nullToStr(this.request.getParameter("brandName"));
		   model						= EnjoyUtils.nullToStr(this.request.getParameter("model"));
		   cusName						= EnjoyUtils.nullToStr(this.request.getParameter("cusName"));
		   company						= EnjoyUtils.nullToStr(this.request.getParameter("company"));
		   
		   logger.info("[SummarySaleDetailServlet][execute] invoiceId 			:: " + invoiceId);
		   logger.info("[SummarySaleDetailServlet][execute] invoiceDateFrom 	:: " + invoiceDateFrom);
		   logger.info("[SummarySaleDetailServlet][execute] invoiceDateTo 		:: " + invoiceDateTo);
		   logger.info("[SummarySaleDetailServlet][execute] brandName 			:: " + brandName);
		   logger.info("[SummarySaleDetailServlet][execute] model 				:: " + model);
		   logger.info("[SummarySaleDetailServlet][execute] cusName 			:: " + cusName);
		   logger.info("[SummarySaleDetailServlet][execute] company	 			:: " + company);
		   
		   userBean = (UserDetailsBean) request.getSession().getAttribute("userBean");
		   
		   viewPdfMainForm	= new ViewPdfMainForm();
		   buffer = viewPdfMainForm.writeSummarySalePDFFormDB("SummarySalePdfForm", invoiceId, invoiceDateFrom, 
				   											 invoiceDateTo, brandName, model, cusName, userBean, company );
		   response.setContentType( "application/pdf" );
		   output 	= new DataOutputStream( this.response.getOutputStream() );
		   bytes 	= buffer.toByteArray();
			
		   this.response.setContentLength(bytes.length);
		   for( int i = 0; i < bytes.length; i++ ) { 
			   output.writeByte( bytes[i] ); 
		   }		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[SummarySaleDetailServlet][lp_viewpdf] " + e.getMessage());
	   }finally{
		   logger.info("[SummarySaleDetailServlet][lp_viewpdf][End]");
	   }
   }
   
   private void lp_getnameSurname(){
	   logger.info("[lp_getnameSurname][Begin]");
	   
	   String							cusName					= null;
       List<String> 					list 					= new ArrayList<String>();
       String[]							strArray				= null;
       
	   try{
		   cusName					= EnjoyUtils.nullToStr(this.request.getParameter("cusName"));
		   
		   logger.info("[lp_getnameSurname] cusName 			:: " + cusName);
		   
		   this.form.setCusName(cusName);
		   
		   list 		= this.dao.nameSurnameList(cusName);
		   strArray 	= new String[list.size()];
		   strArray 	= list.toArray(strArray); 
		   
		   this.motorUtil.writeJsonMSG((String[]) strArray);
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[lp_getnameSurname] " + e.getMessage());
	   }finally{
		   logger.info("[lp_getnameSurname][End]");
	   }
   }
   
}
 
 
 
 
 
 
 
 
 
 
 