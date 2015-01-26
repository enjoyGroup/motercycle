package th.go.motorcycles.web.enjoy.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.dao.UserDetailsDao;
import th.go.motorcycles.app.enjoy.exception.EnjoyException;
import th.go.motorcycles.web.enjoy.common.EnjoyStandardSvc;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;
import th.go.motorcycles.web.enjoy.utils.MotorUtil;

 public class LoginServlet extends EnjoyStandardSvc {
	 
   static final long serialVersionUID = 1L;
   private static final LogWrapper logger = LogWrapper.getLogger(LoginServlet.class);

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response)throws Exception {
	   doProcess(request, response);
   }

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String 				userId 		= null;
        String 				passWord 	= null;
        HttpSession 		session 	= request.getSession(true);
        UserDetailsBean		userBean 	= null;
        UserDetailsDao	 	userDao 	= null;
        MotorUtil           easUtil 	= null;
        try{
        	easUtil 	= new MotorUtil(request, response);
        	userId 		= MotorUtil.nullToStr(request.getParameter("userId"));
        	passWord 	= MotorUtil.nullToStr(request.getParameter("passWord"));
        	userDao		= new UserDetailsDao();
        	
        	this.checkExpiryDate();
        	
        	logger.info("[EnjoyLoginSvc][execute] userId 	:: " + userId);
        	logger.info("[EnjoyLoginSvc][execute] passWord 	:: " + passWord);
        	
        	userBean = userDao.userSelect(userId, passWord);

//        	logger.info("[EnjoyLoginSvc][execute] Vat Rate 	     :: " + ConfigFile.getVAT_RATE());
//        	logger.info("[EnjoyLoginSvc][execute] Pading Invoice :: " + ConfigFile.getPADING_INVOICE());
//        	logger.info("[EnjoyLoginSvc][execute] Begin Invoice  :: " + ConfigFile.getBEGIN_INVOICE(userBean.getFormatInvoie()));
        	
        	if(userBean==null){
        		easUtil.writeMSG("รหัสผ่านไม่ถูกต้อง");
        	}else{
        		session.setAttribute("userBean", userBean);
        		easUtil.writeMSG("OK:" + userBean.getCompanyId());
        	}
        	
        }catch(EnjoyException e){
        	e.printStackTrace();
        	logger.info(e.getMessage());
        	easUtil.writeMSG(e.getMessage());
		}catch(Exception e){
        	e.printStackTrace();
        	logger.info(e.getMessage());
        	easUtil.writeMSG(e.getMessage());
        }finally{
        	logger.info("[EnjoyLoginSvc][execute][End]");
        }
	}
	
	private void checkExpiryDate() throws EnjoyException, Exception{
		Date	  			currDate			= new Date();
		Date	  			expDate				= null;
		SimpleDateFormat    dt              	= null;
		Calendar 			currDateC 			= Calendar.getInstance(Locale.US);
		
		try{
			dt      	= new SimpleDateFormat("yyyyMMdd",Locale.US); 
			expDate     = dt.parse("20150130"); 
			
			currDateC.setTime(currDate);
			
			if(currDateC.getTime().after(expDate)){
				System.out.println("Expiry");
				throw new EnjoyException("เกิดข้อผิดพลาดในการเข้าสู่ระบบ");
			}
            
		}catch(EnjoyException e){
			throw e;
		}catch(Exception e){
			throw e;
		}
	}
	
	
}