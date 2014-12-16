package th.go.motorcycles.web.enjoy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import th.go.motorcycles.app.enjoy.bean.UserDetailsBean;
import th.go.motorcycles.app.enjoy.dao.UserDetailsDao;
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
        	
        	logger.info("[EnjoyLoginSvc][execute] userId 	:: " + userId);
        	logger.info("[EnjoyLoginSvc][execute] passWord 	:: " + passWord);
        	
        	userBean = userDao.userSelect(userId, passWord);
        	
        	if(userBean==null){
        		easUtil.writeMSG("รหัสผ่านไม่ถูกต้อง");
        	}else{
        		session.setAttribute("userBean", userBean);
        		easUtil.writeMSG("OK");
        	}
        	
        }catch(Exception e){
        	e.printStackTrace();
        	easUtil.writeMSG(e.getMessage());
        }finally{
        	logger.info("[EnjoyLoginSvc][execute][End]");
        }
	}
}