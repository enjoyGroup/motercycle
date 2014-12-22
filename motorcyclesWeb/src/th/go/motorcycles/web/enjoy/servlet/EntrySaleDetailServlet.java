package th.go.motorcycles.web.enjoy.servlet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import th.go.motorcycles.app.enjoy.bean.AddressBean;
import th.go.motorcycles.app.enjoy.bean.CustomerBean;
import th.go.motorcycles.app.enjoy.bean.EntrySaleDetailBean;
import th.go.motorcycles.app.enjoy.bean.ProductBean;
import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.dao.AddressDao;
import th.go.motorcycles.app.enjoy.dao.CustomerDao;
import th.go.motorcycles.app.enjoy.dao.EntrySaleDetailDao;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.app.enjoy.form.EntrySaleDetailForm;
import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.app.enjoy.pdf.ViewPdfMainForm;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;
import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;
import th.go.motorcycles.web.enjoy.utils.MotorUtil;

 public class EntrySaleDetailServlet extends EnjoyStandardSvc {
	 
   static final long serialVersionUID = 1L;
   private static final LogWrapper 	logger 		= LogWrapper.getLogger(EntrySaleDetailServlet.class);
   
   private static final String 		FORM_NAME 	= "entrySaleDetailForm";
   
   //Transaction
   private static final String 		NEW 					= "new";
   private static final String 		EDIT 					= "edit";
   private static final String 		SAVE 					= "save";
   private static final String 		RESET 					= "reset";
   private static final String 		VIEWPDF 			    = "pdf";
   private static final String 		PROVINCE 				= "p";
   private static final String 		DISTRICT 				= "d";
   private static final String 		SUBDISTRICT 			= "s";
   private static final String 		GET_CUST_CODE 			= "getCustCode";
   private static final String 		GET_CUST_NAME 			= "getCustName";
   private static final String 		GET_CUST_SNAME 			= "getCustSurName";
   private static final String 		GET_CUST_DTL 			= "getCustDtl";
   private static final String 		GET_CUST_DTL_BY_NAME 	= "getCustDtlByName";
   private static final String 		GET_PROD_DTL 			= "getProdDtl";
   private static final String 		GET_BRAND_NAME 			= "getBrandName";
   private static final String 		GET_MODEL	 			= "getModel";
   private static final String 		GET_NEXT_INVOICE	 	= "getNextInvoice";
   private static final String 		GET_PREVIOUS_INVOICE	= "getPreviousInvoice";
   
   private MotorUtil               		motorUtil                   = null;
   private EntrySaleDetailForm	        form                        = null;
   private HttpServletRequest          	request                     = null;
   private HttpServletResponse         	response                    = null;
   private HttpSession                 	session                     = null;
   private EntrySaleDetailDao			dao							= null;
   private AddressDao					addressDao					= null;
   private CustomerDao					customerDao					= null;
   private UserDetailsBean				userBean 					= null;

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response)throws Exception {
	   logger.info("[execute][Begin]");
		
	   String pageAction 	= null;
	   String pageActionPDF = null;
		
		try{
			pageAction 				= MotorUtil.nullToStr(request.getParameter("pageAction"));
			pageActionPDF			= MotorUtil.nullToStr(request.getParameter("pageActionPDF"));
			
			logger.info("[execute] pageAction :: " + pageAction);
			
			this.motorUtil 			= new MotorUtil(request, response);
			this.request            = request;
			this.response           = response;
			this.session            = request.getSession(false);
			this.form               = (EntrySaleDetailForm)session.getAttribute(FORM_NAME);
			this.dao				= new EntrySaleDetailDao();
			this.addressDao			= new AddressDao();
			this.customerDao		= new CustomerDao();
			this.userBean			= (UserDetailsBean) this.session.getAttribute("userBean");
			
			if(this.form == null || pageAction.equals(NEW) || pageAction.equals(RESET)) this.form = new EntrySaleDetailForm();
			
			if(pageAction.equals("") || pageAction.equals(NEW)){
System.out.println("pageActionPDF ==> " + pageActionPDF);
				if (pageActionPDF.equals(VIEWPDF)) {

					this.lp_viewpdf();
				} else {
//					this.form.getCustomerBean().setIdType("1");
//					this.form.setInvoiceId("5700000001");
					request.setAttribute("target", Constants.PAGE_URL + "/EntrySaleDetailScn.jsp");
				}
			}else if(pageAction.equals(EDIT)){
				this.lp_getData();
				request.setAttribute("target", Constants.PAGE_URL + "/EntrySaleDetailScn.jsp");
			}else if(pageAction.equals(PROVINCE)){
				this.lp_province();
			}else if(pageAction.equals(DISTRICT)){
				this.lp_district();
			}else if(pageAction.equals(SUBDISTRICT)){
				this.lp_subdistrict();
			}else if(pageAction.equals(GET_CUST_CODE)){
				this.lp_getCustCode();
			}else if(pageAction.equals(GET_CUST_NAME)){
				this.lp_getCustName();
			}else if(pageAction.equals(GET_CUST_SNAME)){
				this.lp_getCustSurName();
			}else if(pageAction.equals(GET_CUST_DTL)){
				this.lp_getCustDtl();
			}else if(pageAction.equals(GET_CUST_DTL_BY_NAME)){
				this.lp_getCustDtlByName();
			}else if(pageAction.equals(GET_BRAND_NAME)){
				this.lp_getBrandName();
			}else if(pageAction.equals(GET_MODEL)){
				this.lp_getModel();
			}else if(pageAction.equals(GET_PROD_DTL)){
				this.lp_getProdDtl();
			}else if(pageAction.equals(SAVE)){
				this.lp_saveData();
			}else if(pageAction.equals(GET_NEXT_INVOICE)){
				this.lp_getNextInvoice();
			}else if(pageAction.equals(GET_PREVIOUS_INVOICE)){
				this.lp_getPreviousInvoice();
			}
			
			session.setAttribute(FORM_NAME, this.form);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[execute] " + e.getMessage());
		}finally{
			logger.info("[execute][End]");
		}
   }
   
   private void lp_saveData(){
	   logger.info("[lp_saveData][Begin]");
	   
	   CustomerBean 		customerBean 				= null;
	   ProductBean			productBean 				= null;
	   EntrySaleDetailBean 	entrySaleDetailBean 		= null;
	   String				invoiceId 					= null;
	   String 				priceAmount 				= null;
	   String 				vatAmount 					= null;
	   String 				remark 						= null;
	   String 				commAmount 					= null;
	   String 				flagAddSales 				= null;
	   String				cusCode 					= null;
	   String				motorcyclesCode 			= null;
	   String				userUniqueId 				= null;
	   String				invoiceMode 				= null;
	   String				provinceId					= null;
	   String				districtId					= null;
	   String				subdistrictId				= null;
	   String				provinceName				= null;
	   String				districtName				= null;
	   String				subdistrictName				= null;
	   String 				brandName					= null;
	   String 				model						= null;
	   JSONObject 			obj 						= new JSONObject();
	   AddressBean			addressBean					= null;
	   EntrySaleDetailForm	form						= null;
	   String 				chassis						= null;
	   String 				engineNo					= null;
	   String 				size						= null;
	   
	   try{
		   customerBean 	= new CustomerBean();
		   productBean 		= new ProductBean();
		   form				= new EntrySaleDetailForm();
		   invoiceId		= EnjoyUtils.nullToStr(this.request.getParameter("invoiceId"));
		   priceAmount		= EnjoyUtils.replaceComma(this.request.getParameter("priceAmount"));
		   vatAmount		= EnjoyUtils.replaceComma(this.request.getParameter("vatAmount"));
		   remark			= EnjoyUtils.nullToStr(this.request.getParameter("remark"));
		   commAmount		= EnjoyUtils.replaceComma(this.request.getParameter("commAmount"));
		   flagAddSales		= EnjoyUtils.chkBoxtoDb(this.request.getParameter("flagAddSales"));
		   cusCode			= EnjoyUtils.nullToStr(this.request.getParameter("cusCode"));
		   userUniqueId		= this.userBean.getUserUniqueId();
		   invoiceMode		= EnjoyUtils.nullToStr(this.request.getParameter("invoiceMode"));
		   provinceName		= EnjoyUtils.nullToStr(this.request.getParameter("provinceName"));
		   districtName		= EnjoyUtils.nullToStr(this.request.getParameter("districtName"));
		   subdistrictName	= EnjoyUtils.nullToStr(this.request.getParameter("subdistrictName"));
		   brandName		= EnjoyUtils.nullToStr(this.request.getParameter("brandName"));
		   model			= EnjoyUtils.nullToStr(this.request.getParameter("model"));
		   chassis			= EnjoyUtils.nullToStr(this.request.getParameter("chassis"));
		   engineNo			= EnjoyUtils.nullToStr(this.request.getParameter("engineNo"));
		   size				= EnjoyUtils.nullToStr(this.request.getParameter("size"));
		   
		   logger.info("[lp_saveData] invoiceId 			:: " + invoiceId);
		   logger.info("[lp_saveData] priceAmount 			:: " + priceAmount);
		   logger.info("[lp_saveData] vatAmount 			:: " + vatAmount);
		   logger.info("[lp_saveData] remark 				:: " + remark);
		   logger.info("[lp_saveData] commAmount 			:: " + commAmount);
		   logger.info("[lp_saveData] flagAddSales 			:: " + flagAddSales);
		   logger.info("[lp_saveData] cusCode 				:: " + cusCode);
		   logger.info("[lp_saveData] userUniqueId 			:: " + userUniqueId);
		   logger.info("[lp_saveData] invoiceMode 			:: " + invoiceMode);
		   logger.info("[lp_saveData] provinceName 			:: " + provinceName);
		   logger.info("[lp_saveData] districtName 			:: " + districtName);
		   logger.info("[lp_saveData] subdistrictName 		:: " + subdistrictName);
		   logger.info("[lp_saveData] brandName 			:: " + brandName);
		   logger.info("[lp_saveData] model 				:: " + model);
		   logger.info("[lp_saveData] chassis 				:: " + chassis);
		   logger.info("[lp_saveData] engineNo 				:: " + engineNo);
		   logger.info("[lp_saveData] size 					:: " + size);
		   logger.info("[lp_saveData] idType 				:: " + this.request.getParameter("idType"));
		   logger.info("[lp_saveData] idNumber 				:: " + EnjoyUtils.nullToStr(this.request.getParameter("idNumber")));
		   
		   form.setInvoiceId(invoiceId);
		   form.setPriceAmount(priceAmount);
		   form.setVatAmount(vatAmount);
		   form.setRemark(remark);
		   form.setCommAmount(commAmount);
		   form.setFlagAddSales(flagAddSales);
		   form.setUserUniqueId(userUniqueId);
		   
		   addressBean 		= this.addressDao.validateAddress(provinceName, districtName, subdistrictName);
		   
		   if(addressBean.getErrMsg().equals("")){
			   
			   provinceId 		= addressBean.getProvinceId();
			   districtId 		= addressBean.getDistrictId();
			   subdistrictId 	= addressBean.getSubdistrictId();
			   
			   logger.info("[lp_saveData] provinceId 			:: " + provinceId);
			   logger.info("[lp_saveData] districtId 			:: " + districtId);
			   logger.info("[lp_saveData] subdistrictId 		:: " + subdistrictId);
			   
		   }else{
			   throw new EnjoyException(addressBean.getErrMsg());
		   }
		   
		   if(cusCode.equals("")){
			   customerBean.setCustName(EnjoyUtils.nullToStr(this.request.getParameter("custName"))); 
			   customerBean.setCustSurname(EnjoyUtils.nullToStr(this.request.getParameter("custSurname")));
			   customerBean.setHouseNumber(EnjoyUtils.nullToStr(this.request.getParameter("houseNumber")));
			   customerBean.setMooNumber(EnjoyUtils.nullToStr(this.request.getParameter("mooNumber")));
			   customerBean.setSoiName(EnjoyUtils.nullToStr(this.request.getParameter("soiName")));  
			   customerBean.setStreetName(EnjoyUtils.nullToStr(this.request.getParameter("streetName")));
			   
			   customerBean.setProvinceCode(provinceId);
			   customerBean.setDistrictCode(districtId); 
			   customerBean.setSubdistrictCode(subdistrictId);
			   
			   customerBean.setIdType(EnjoyUtils.nullToStr(this.request.getParameter("idType"))); 
			   customerBean.setIdNumber(EnjoyUtils.nullToStr(this.request.getParameter("idNumber")));
			   customerBean.setCusStatus("A");
			   
			   cusCode = this.customerDao.insertCustomer(customerBean);
			   customerBean.setCusCode(cusCode);
		   }else{
			   customerBean.setCusCode(cusCode);
			   customerBean.setCustName(EnjoyUtils.nullToStr(this.request.getParameter("custName"))); 
			   customerBean.setCustSurname(EnjoyUtils.nullToStr(this.request.getParameter("custSurname")));
			   customerBean.setHouseNumber(EnjoyUtils.nullToStr(this.request.getParameter("houseNumber")));
			   customerBean.setMooNumber(EnjoyUtils.nullToStr(this.request.getParameter("mooNumber")));
			   customerBean.setSoiName(EnjoyUtils.nullToStr(this.request.getParameter("soiName")));  
			   customerBean.setStreetName(EnjoyUtils.nullToStr(this.request.getParameter("streetName")));
			   
			   customerBean.setProvinceCode(provinceId);
			   customerBean.setDistrictCode(districtId); 
			   customerBean.setSubdistrictCode(subdistrictId);
			   
			   customerBean.setIdType(EnjoyUtils.nullToStr(this.request.getParameter("idType"))); 
			   customerBean.setIdNumber(EnjoyUtils.nullToStr(this.request.getParameter("idNumber")));
			   
			   this.customerDao.updateCustomer(customerBean);
		   }
		   
		   form.setCustomerBean(customerBean);
		   
		   entrySaleDetailBean = this.dao.getMotorcyclesCode(brandName, model);
		   if(!entrySaleDetailBean.getErrMsg().equals("")){
			   throw new EnjoyException(entrySaleDetailBean.getErrMsg());
		   }else{
			   motorcyclesCode = entrySaleDetailBean.getMotorcyclesCode();
		   }
		   
		   form.setMotorcyclesCode(motorcyclesCode);
		   
		   productBean.setChassis(chassis);
		   productBean.setEngineNo(engineNo);
		   productBean.setSize(size);
		   form.setProductBean(productBean);
		   
		   if(invoiceId.equals("")){
			   entrySaleDetailBean = this.dao.insertInvoiceDetail(form);
		   }else{
			   entrySaleDetailBean = this.dao.updateInvoiceDetail(form);
		   }
		   
		   if(!entrySaleDetailBean.getErrMsg().equals("")){
			   throw new EnjoyException(entrySaleDetailBean.getErrMsg());
		   }else{
			   obj.put("status", 			"SUCCESS");
			   obj.put("invoiceId", 		entrySaleDetailBean.getInvoiceId());
		   }
		   
		   
	   }
	   catch(EnjoyException e){
		   obj.put("status", 			"ERROR");
		   obj.put("errMsg", 			e.getMessage());
		}
	   catch(Exception e){
			obj.put("status", 			"ERROR");
			obj.put("errMsg", 			"เกิดข้อผิดพลาดในการบันทึกข้อมูล");
		   e.printStackTrace();
	   }finally{
		   this.motorUtil.writeMSG(obj.toString());
		   logger.info("[lp_saveData][End]");
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
   
   private void lp_getCustDtl(){
	   logger.info("[lp_getCustDtl][Begin]");
	   
	   String							cusCode					= null;
       CustomerBean 					customerBeanDb			= null;
       JSONObject 						obj 					= null;
       
	   try{
		   cusCode					= EnjoyUtils.nullToStr(this.request.getParameter("cusCode"));
		   customerBeanDb			= new CustomerBean();
		   obj 						= new JSONObject();
		   
		   logger.info("[lp_getCustDtl] cusCode 			:: " + cusCode);
		   
		   this.dao.getCustomerDetail(cusCode, customerBeanDb);
		   
		   if(!customerBeanDb.getCusCode().equals("")){
			   obj.put("status", 			"SUCCESS");
			   obj.put("cusCode", 			customerBeanDb.getCusCode());
			   obj.put("custName", 			customerBeanDb.getCustName());
			   obj.put("custSurname", 		customerBeanDb.getCustSurname());
			   obj.put("houseNumber", 		customerBeanDb.getHouseNumber());
			   obj.put("mooNumber", 		customerBeanDb.getMooNumber());
			   obj.put("soiName", 			customerBeanDb.getSoiName());
			   obj.put("streetName", 		customerBeanDb.getStreetName());
			   obj.put("subdistrictName", 	customerBeanDb.getSubdistrictName());
			   obj.put("districtName", 		customerBeanDb.getDistrictName());
			   obj.put("provinceName", 		customerBeanDb.getProvinceName());
			   obj.put("idType", 			customerBeanDb.getIdType());
			   obj.put("idNumber", 			customerBeanDb.getIdNumber());
			   obj.put("cusStatus", 		customerBeanDb.getCusStatus());
			   
		   }else{
			   obj.put("status", 			"ERROR");
		   }
		   
		   this.form.setCustomerBean(customerBeanDb);
		   
	   }catch(Exception e){
		   obj.put("status", 			"ERROR");
		   e.printStackTrace();
		   logger.info("[lp_getCustDtl] " + e.getMessage());
	   }finally{
		   this.motorUtil.writeMSG(obj.toString());
		   logger.info("[lp_getCustDtl][End]");
	   }
   }
   
   private void lp_getCustDtlByName(){
	   logger.info("[lp_getCustDtlByName][Begin]");
	   
	   String							custName				= null;
	   String							custSurname				= null;
       CustomerBean 					customerBeanDb			= null;
       JSONObject 						obj 					= null;
       
	   try{
		   custName					= EnjoyUtils.nullToStr(this.request.getParameter("custName"));
		   custSurname				= EnjoyUtils.nullToStr(this.request.getParameter("custSurname"));
		   customerBeanDb			= new CustomerBean();
		   obj 						= new JSONObject();
		   
		   logger.info("[lp_getCustDtlByName] custName 			:: " + custName);
		   logger.info("[lp_getCustDtlByName] custSurname 		:: " + custSurname);
		   
		   this.dao.getCustomerDetail(custName, custSurname, customerBeanDb);
		   
		   if(!customerBeanDb.getCusCode().equals("")){
			   obj.put("status", 			"SUCCESS");
			   obj.put("cusCode", 			customerBeanDb.getCusCode());
			   obj.put("custName", 			customerBeanDb.getCustName());
			   obj.put("custSurname", 		customerBeanDb.getCustSurname());
			   obj.put("houseNumber", 		customerBeanDb.getHouseNumber());
			   obj.put("mooNumber", 		customerBeanDb.getMooNumber());
			   obj.put("soiName", 			customerBeanDb.getSoiName());
			   obj.put("streetName", 		customerBeanDb.getStreetName());
			   obj.put("subdistrictName", 	customerBeanDb.getSubdistrictName());
			   obj.put("districtName", 		customerBeanDb.getDistrictName());
			   obj.put("provinceName", 		customerBeanDb.getProvinceName());
			   obj.put("idType", 			customerBeanDb.getIdType());
			   obj.put("idNumber", 			customerBeanDb.getIdNumber());
			   obj.put("cusStatus", 		customerBeanDb.getCusStatus());
			   
		   }else{
			   obj.put("status", 			"ERROR");
		   }
		   
		   this.form.setCustomerBean(customerBeanDb);
		   
	   }catch(Exception e){
		   obj.put("status", 			"ERROR");
		   e.printStackTrace();
		   logger.info("[lp_getCustDtlByName] " + e.getMessage());
	   }finally{
		   this.motorUtil.writeMSG(obj.toString());
		   logger.info("[lp_getCustDtlByName][End]");
	   }
   }
   
   private void lp_getProdDtl(){
	   logger.info("[lp_getProdDtl][Begin]");
	   
	   String							brandName				= null;
	   String							model					= null;
	   ProductBean 						productBeanDb			= null;
       JSONObject 						obj 					= null;
       
	   try{
		   brandName					= EnjoyUtils.nullToStr(this.request.getParameter("brandName"));
		   model						= EnjoyUtils.nullToStr(this.request.getParameter("model"));
		   productBeanDb				= new ProductBean();
		   obj 							= new JSONObject();
		   
		   logger.info("[lp_getProdDtl] brandName 		:: " + brandName);
		   logger.info("[lp_getProdDtl] model 			:: " + model);
		   
		   this.dao.productDetail(brandName, model, productBeanDb);
		   
		   logger.info("[lp_getProdDtl] productBeanDb.getModel() 			:: " + productBeanDb.getModel());
		   if(!productBeanDb.getModel().equals("")){
			   obj.put("status", 			"SUCCESS");
			   obj.put("brandName", 		productBeanDb.getBrandName());
			   obj.put("model", 			productBeanDb.getModel());
			   obj.put("chassis", 			productBeanDb.getChassis());
			   obj.put("engineNo", 			productBeanDb.getEngineNo());
			   obj.put("size", 				productBeanDb.getSize());
			   
		   }else{
			   obj.put("status", 			"ERROR");
		   }
		   
		   this.form.setProductBean(productBeanDb);
		   
	   }catch(Exception e){
		   obj.put("status", 			"ERROR");
		   e.printStackTrace();
		   logger.info("[lp_getProdDtl] " + e.getMessage());
	   }finally{
		   this.motorUtil.writeMSG(obj.toString());
		   logger.info("[lp_getProdDtl][End]");
	   }
   }
   
   private void lp_getCustCode(){
	   logger.info("[lp_getCustCode][Begin]");
	   
	   String							cusCode					= null;
       List<String> 					list 					= new ArrayList<String>();
       String[]							strArray				= null;
       CustomerBean 					customerBean			= null;
       
	   try{
		   cusCode					= EnjoyUtils.nullToStr(this.request.getParameter("cusCode"));
		   customerBean				= this.form.getCustomerBean();
		   
		   logger.info("[lp_getCustCode] cusCode 			:: " + cusCode);
		   
		   customerBean.setCusCode(cusCode);
		   
		   list 		= this.dao.cusCodeList(cusCode);
		   strArray 	= new String[list.size()];
		   strArray 	= list.toArray(strArray); 
		   
		   this.motorUtil.writeJsonMSG((String[]) strArray);
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[lp_getCustCode] " + e.getMessage());
	   }finally{
		   logger.info("[lp_getCustCode][End]");
	   }
   }
   
   private void lp_getCustName(){
	   logger.info("[lp_getCustName][Begin]");
	   
	   String							custName				= null;
       List<String> 					list 					= new ArrayList<String>();
       String[]							strArray				= null;
       CustomerBean 					customerBean			= null;
       
	   try{
		   custName					= EnjoyUtils.nullToStr(this.request.getParameter("custName"));
		   customerBean				= this.form.getCustomerBean();
		   
		   logger.info("[lp_getCustName] custName 			:: " + custName);
		   
		   customerBean.setCustName(custName);
		   
		   list 		= this.dao.custNameList(custName);
		   strArray 	= new String[list.size()];
		   strArray 	= list.toArray(strArray); 
		   
		   this.motorUtil.writeJsonMSG((String[]) strArray);
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[lp_getCustName] " + e.getMessage());
	   }finally{
		   logger.info("[lp_getCustName][End]");
	   }
   }
   
   private void lp_getCustSurName(){
	   logger.info("[lp_getCustSurName][Begin]");
	   
	   String							custName				= null;
	   String							custSurname				= null;
       List<String> 					list 					= new ArrayList<String>();
       String[]							strArray				= null;
       CustomerBean 					customerBean			= null;
       
	   try{
		   custName					= EnjoyUtils.nullToStr(this.request.getParameter("custName"));
		   custSurname				= EnjoyUtils.nullToStr(this.request.getParameter("custSurname"));
		   customerBean				= this.form.getCustomerBean();
		   
		   logger.info("[lp_getCustSurName] custName 		:: " + custName);
		   logger.info("[lp_getCustSurName] custSurname 	:: " + custSurname);
		   
		   customerBean.setCustSurname(custSurname);
		   
		   list 		= this.dao.custSurnameList(custName, custSurname);
		   strArray 	= new String[list.size()];
		   strArray 	= list.toArray(strArray); 
		   
		   this.motorUtil.writeJsonMSG((String[]) strArray);
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[lp_getCustSurName] " + e.getMessage());
	   }finally{
		   logger.info("[lp_getCustSurName][End]");
	   }
   }
   
   private void lp_getBrandName(){
	   logger.info("[lp_getBrandName][Begin]");
	   
	   String							brandName				= null;
       List<String> 					list 					= new ArrayList<String>();
       String[]							strArray				= null;
       ProductBean 						productBean				= null;
       
	   try{
		   brandName					= EnjoyUtils.nullToStr(this.request.getParameter("brandName"));
		   productBean					= this.form.getProductBean();
		   
		   logger.info("[lp_getBrandName] brandName 			:: " + brandName);
		   
		   productBean.setBrandName(brandName);
		   
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
   }
   
   private void lp_getModel(){
	   logger.info("[lp_getModel][Begin]");
	   
	   String							brandName				= null;
	   String							model					= null;
       List<String> 					list 					= new ArrayList<String>();
       String[]							strArray				= null;
       ProductBean 						productBean				= null;
       
	   try{
		   brandName					= EnjoyUtils.nullToStr(this.request.getParameter("brandName"));
		   model						= EnjoyUtils.nullToStr(this.request.getParameter("model"));
		   productBean					= this.form.getProductBean();
		   
		   logger.info("[lp_getModel] brandName 		:: " + brandName);
		   logger.info("[lp_getModel] model 			:: " + model);
		   
		   productBean.setBrandName(brandName);
		   productBean.setModel(model);
		   
		   list 		= this.dao.modelList(brandName, model);
		   strArray 	= new String[list.size()];
		   strArray 	= list.toArray(strArray); 
		   
		   this.motorUtil.writeJsonMSG((String[]) strArray);
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[lp_getModel] " + e.getMessage());
	   }finally{
		   logger.info("[lp_getModel][End]");
	   }
   }
   
   private void lp_province(){
	   logger.info("[lp_search][Begin]");
	   
	   String							provinceName			= null;
       List<String> 					list 					= new ArrayList<String>();
       String[]							strArray				= null;
       CustomerBean 					customerBean			= null;
       
	   try{
		   provinceName				= EnjoyUtils.nullToStr(this.request.getParameter("provinceName"));
		   customerBean				= this.form.getCustomerBean();
		   
		   logger.info("[lp_province] provinceName 			:: " + provinceName);
		   
		   customerBean.setProvinceName(provinceName);
		   
		   list 		= this.addressDao.provinceList(provinceName);
		   strArray 	= new String[list.size()];
		   strArray 	= list.toArray(strArray); 
		   
		   this.motorUtil.writeJsonMSG((String[]) strArray);
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[lp_province] " + e.getMessage());
	   }finally{
		   logger.info("[lp_province][End]");
	   }
   }
   
   private void lp_district(){
	   logger.info("[lp_district][Begin]");
	   
	   String							provinceName			= null;
	   String							districtName			= null;
       List<String> 					list 					= new ArrayList<String>();
       String[]							strArray				= null;
       CustomerBean 					customerBean			= null;
       
	   try{
		   provinceName					= EnjoyUtils.nullToStr(this.request.getParameter("provinceName"));
		   districtName					= EnjoyUtils.nullToStr(this.request.getParameter("districtName"));
		   customerBean					= this.form.getCustomerBean();
		   
		   customerBean.setProvinceName(provinceName);
		   customerBean.setDistrictName(districtName);
		   
		   logger.info("[lp_district] provinceName 			:: " + provinceName);
		   logger.info("[lp_district] districtName 			:: " + districtName);
		   
		   list 		= this.addressDao.districtList(provinceName, districtName);
		   strArray 	= new String[list.size()];
		   strArray 	= list.toArray(strArray); 
		   
		   this.motorUtil.writeJsonMSG((String[]) strArray);
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[lp_district] " + e.getMessage());
	   }finally{
		   logger.info("[lp_district][End]");
	   }
   }
   
   private void lp_subdistrict(){
	   logger.info("[subdistrict][Begin]");
	   
	   String							provinceName			= null;
	   String							districtName			= null;
	   String							subdistrictName			= null;
       List<String> 					list 					= new ArrayList<String>();
       String[]							strArray				= null;
       CustomerBean 					customerBean			= null;
       
	   try{
		   provinceName					= EnjoyUtils.nullToStr(this.request.getParameter("provinceName"));
		   districtName					= EnjoyUtils.nullToStr(this.request.getParameter("districtName"));
		   subdistrictName				= EnjoyUtils.nullToStr(this.request.getParameter("subdistrictName"));
		   customerBean					= this.form.getCustomerBean();
		   
		   customerBean.setProvinceName(provinceName);
		   customerBean.setDistrictName(districtName);
		   customerBean.setSubdistrictName(subdistrictName);
		   
		   logger.info("[lp_district] provinceName 			:: " + provinceName);
		   logger.info("[lp_district] districtName 			:: " + districtName);
		   logger.info("[lp_subdistrict] subdistrict 		:: " + subdistrictName);
		   
		   list 		= this.addressDao.subdistrictList(provinceName, districtName, subdistrictName);
		   strArray 	= new String[list.size()];
		   strArray 	= list.toArray(strArray); 
		   
		   this.motorUtil.writeJsonMSG((String[]) strArray);
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[lp_subdistrict] " + e.getMessage());
	   }finally{
		   logger.info("[lp_subdistrict][End]");
	   }
   }
   
   private void lp_viewpdf(){
	   logger.info("[EntrySaleDetailServlet][lp_viewpdf][Begin]");
	   
	   String							invoiceId			= null;
       ViewPdfMainForm					viewPdfMainForm		= null;
	   DataOutput 						output 				= null;
	   ByteArrayOutputStream			buffer				= null;
	   byte[] 							bytes				= null;
	   UserDetailsBean 					userBean			= null;
	   try{
		   invoiceId					= EnjoyUtils.nullToStr(this.request.getParameter("invoiceId"));
		   
		   logger.info("[SummarySaleDetailServlet][execute] invoiceId 			:: " + invoiceId);
		   
		   userBean = (UserDetailsBean) request.getSession().getAttribute("userBean");
		   
		   viewPdfMainForm	= new ViewPdfMainForm();
		   buffer = viewPdfMainForm.writeSlipPdfFormPDFFormDB("SlipPdfForm", invoiceId, userBean );
		   response.setContentType( "application/pdf" );
		   output 	= new DataOutputStream( this.response.getOutputStream() );
		   bytes 	= buffer.toByteArray();
			
		   this.response.setContentLength(bytes.length);
		   for( int i = 0; i < bytes.length; i++ ) { 
			   output.writeByte( bytes[i] ); 
		   }		   
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("[EntrySaleDetailServlet][lp_viewpdf] " + e.getMessage());
	   }finally{
		   logger.info("[EntrySaleDetailServlet][lp_viewpdf][End]");
	   }
   }
   
   private void lp_getNextInvoice(){
	   logger.info("[lp_getNextInvoice][Begin]");
	   
	   JSONObject 			obj 						= new JSONObject();
	   String				invoiceId					= null;
	   EntrySaleDetailBean	entrySaleDetailBean			= null;
	   
	   try{
		   invoiceId = EnjoyUtils.nullToStr(this.request.getParameter("invoiceId"));
		   
		   logger.info("[lp_getNextInvoice] invoiceId :: " + invoiceId);
		   
		   entrySaleDetailBean = this.dao.getNextInvoiceId(invoiceId);
		   
		   if(!entrySaleDetailBean.getErrMsg().equals("")){
			   throw new EnjoyException(entrySaleDetailBean.getErrMsg());
		   }
		   
		   obj.put("status", 			"SUCCESS");
		   obj.put("invoiceId", 		entrySaleDetailBean.getInvoiceId());
		   
	   }catch(EnjoyException e){
		   obj.put("status", 			"ERROR");
		   obj.put("errMsg", 			e.getMessage());
		   e.printStackTrace();
	   }catch(Exception e){
		   obj.put("status", 			"ERROR");
		   obj.put("errMsg", 			"เกิดข้อผิดพลาดในการดึง invoiceId");
		   e.printStackTrace();
	   }finally{
		   this.motorUtil.writeMSG(obj.toString());
		   logger.info("[lp_getNextInvoice][End]");
	   }
   }
   
   private void lp_getPreviousInvoice(){
	   logger.info("[lp_getPreviousInvoice][Begin]");
	   
	   JSONObject 			obj 						= new JSONObject();
	   String				invoiceId					= null;
	   EntrySaleDetailBean	entrySaleDetailBean			= null;
	   
	   try{
		   invoiceId = EnjoyUtils.nullToStr(this.request.getParameter("invoiceId"));
		   
		   logger.info("[lp_getPreviousInvoice] invoiceId :: " + invoiceId);
		   
		   entrySaleDetailBean = this.dao.getPreviousInvoiceId(invoiceId);
		   
		   if(!entrySaleDetailBean.getErrMsg().equals("")){
			   throw new EnjoyException(entrySaleDetailBean.getErrMsg());
		   }
		   
		   obj.put("status", 			"SUCCESS");
		   obj.put("invoiceId", 		entrySaleDetailBean.getInvoiceId());
		   
	   }catch(EnjoyException e){
		   obj.put("status", 			"ERROR");
		   obj.put("errMsg", 			e.getMessage());
		   e.printStackTrace();
	   }catch(Exception e){
		   obj.put("status", 			"ERROR");
		   obj.put("errMsg", 			"เกิดข้อผิดพลาดในการดึง invoiceId");
		   e.printStackTrace();
	   }finally{
		   this.motorUtil.writeMSG(obj.toString());
		   logger.info("[lp_getPreviousInvoice][End]");
	   }
   }
   
}
 
 
 
 
 
 
 
 
 
 