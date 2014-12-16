package th.go.motorcycles.web.enjoy.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import th.go.motorcycles.app.enjoy.bean.AddressBean;
import th.go.motorcycles.app.enjoy.dao.AddressDao;
import th.go.motorcycles.app.enjoy.form.AddressDemoForm;
import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;
import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;
import th.go.motorcycles.web.enjoy.utils.MotorUtil;

 public class AddressDemoServlet extends EnjoyStandardSvc {
	 
   static final long serialVersionUID = 1L;
   private static final LogWrapper 	logger 		= LogWrapper.getLogger(AddressDemoServlet.class);
   
   private static final String 		FORM_NAME 	= "addressDemoForm";
   
   //Transaction
   private static final String 		PROVINCE 		= "p";
   private static final String 		DISTRICT 		= "d";
   private static final String 		SUBDISTRICT 	= "s";
   private static final String 		SAVE		 	= "save";
   
   private MotorUtil               		motorUtil                   = null;
   private AddressDemoForm		        form                        = null;
   private HttpServletRequest          	request                     = null;
   private HttpServletResponse         	response                    = null;
   private HttpSession                 	session                     = null;
   private AddressDao					dao							= null;

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
			this.form               = (AddressDemoForm)session.getAttribute(FORM_NAME);
			this.dao				= new AddressDao();
			
			if(this.form == null || pageAction.equals("new")) this.form = new AddressDemoForm();
			
			if(pageAction.equals("") || pageAction.equals("new")){
				request.setAttribute("target", Constants.PAGE_URL + "/AddressDemoScn.jsp");
			}else if(pageAction.equals(PROVINCE)){
				this.lp_province();
			}else if(pageAction.equals(DISTRICT)){
				this.lp_district();
			}else if(pageAction.equals(SUBDISTRICT)){
				this.lp_subdistrict();
			}else if(pageAction.equals(SAVE)){
				this.lp_save();
			}
			
			session.setAttribute(FORM_NAME, this.form);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[execute] " + e.getMessage());
		}finally{
			logger.info("[execute][End]");
		}
   }
   
   private void lp_save(){
	   logger.info("[lp_save][Begin]");
	   
	   String							province			= null;
	   String							district			= null;
	   String							subdistrict			= null;
	   AddressBean						addressBean			= null;
	   JSONObject 						obj 				= null;
       
	   try{
		   province					= EnjoyUtils.nullToStr(this.request.getParameter("province"));
		   district					= EnjoyUtils.nullToStr(this.request.getParameter("district"));
		   subdistrict				= EnjoyUtils.nullToStr(this.request.getParameter("subdistrict"));
		   obj 						= new JSONObject();
		   
		   logger.info("[lp_save] province 			:: " + province);
		   logger.info("[lp_save] district 			:: " + district);
		   logger.info("[lp_save] subdistrict 		:: " + subdistrict);
		   
		   this.form.setProvince(province);
		   this.form.setDistrict(district);
		   this.form.setSubdistrict(subdistrict);
		   
		   addressBean 		= this.dao.validateAddress(province, district, subdistrict);
		   
		   if(addressBean.getErrMsg().equals("")){
			   obj.put("status", 			"SUCCESS");
			   obj.put("provinceId", 		addressBean.getProvinceId());
			   obj.put("districtId", 		addressBean.getDistrictId());
			   obj.put("subdistrictId", 	addressBean.getSubdistrictId());
			   
			   logger.info("[lp_save] provinceId 			:: " + addressBean.getProvinceId());
			   logger.info("[lp_save] districtId 			:: " + addressBean.getDistrictId());
			   logger.info("[lp_save] subdistrictId 		:: " + addressBean.getSubdistrictId());
			   
		   }else{
			   obj.put("status", 			"ERROR");
			   obj.put("errMsg", 			addressBean.getErrMsg());
		   }
		   
	   }catch(Exception e){
		   obj.put("status", 			"ERROR");
		   obj.put("errMsg", 			e.getMessage());
		   e.printStackTrace();
		   logger.info("[lp_save] " + e.getMessage());
	   }finally{
		   this.motorUtil.writeMSG(obj.toString());
		   logger.info("[lp_save][End]");
	   }
   }
   
   private void lp_province(){
	   logger.info("[lp_search][Begin]");
	   
	   String							province			= null;
       List<String> 					list 				= new ArrayList<String>();
       String[]							strArray			= null;
       
	   try{
		   province					= EnjoyUtils.nullToStr(this.request.getParameter("province"));
		   
		   logger.info("[lp_province] province 			:: " + province);
		   
		   this.form.setProvince(province);
		   
		   list 		= this.dao.provinceList(province);
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
	   
	   String							province			= null;
	   String							district			= null;
       List<String> 					list 				= new ArrayList<String>();
       String[]							strArray			= null;
       
	   try{
		   province					= EnjoyUtils.nullToStr(this.request.getParameter("province"));
		   district					= EnjoyUtils.nullToStr(this.request.getParameter("district"));
		   
		   this.form.setProvince(province);
		   this.form.setDistrict(district);
		   
		   logger.info("[lp_district] province 			:: " + province);
		   logger.info("[lp_district] district 			:: " + district);
		   
		   list 		= this.dao.districtList(province, district);
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
	   
	   String							province			= null;
	   String							district			= null;
	   String							subdistrict			= null;
       List<String> 					list 				= new ArrayList<String>();
       String[]							strArray			= null;
       
	   try{
		   province					= EnjoyUtils.nullToStr(this.request.getParameter("province"));
		   district					= EnjoyUtils.nullToStr(this.request.getParameter("district"));
		   subdistrict				= EnjoyUtils.nullToStr(this.request.getParameter("subdistrict"));
		   
		   this.form.setProvince(province);
		   this.form.setDistrict(district);
		   this.form.setSubdistrict(subdistrict);
		   
		   logger.info("[lp_subdistrict] province 			:: " + province);
		   logger.info("[lp_subdistrict] district 			:: " + district);
		   logger.info("[lp_subdistrict] subdistrict 		:: " + subdistrict);
		   
		   list 		= this.dao.subdistrictList(province, district, subdistrict);
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
}
 
 
 
 
 
 
 
 
 
 