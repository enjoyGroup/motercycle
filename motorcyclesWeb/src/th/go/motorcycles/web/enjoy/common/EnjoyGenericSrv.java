package th.go.motorcycles.web.enjoy.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

/**
 * Servlet implementation class RrcGenericSrv
 */
public class EnjoyGenericSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnjoyGenericSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("[EnjoyGenericSrv][service][Begin]");
		
		String 				serviceName 			= null;
		Class 				cls 					= null;
		EnjoyStandardSvc 	enjoyStandardSvc 		= null;
		EnjoyStandardItf 	service 				= null;
		HttpSession 		session 				= null;
		String 				target 					= null;
        
        try {
        	request.setCharacterEncoding("UTF-8");
        	response.setCharacterEncoding("UTF-8");
        	
            session				= request.getSession(true);
            serviceName 		= request.getParameter("service");
            cls 				= Class.forName(Constants.MAIN_PACKAGE + serviceName);
            enjoyStandardSvc 	= (EnjoyStandardSvc)cls.newInstance();
            service 			= enjoyStandardSvc;
            
            System.out.println("[EnjoyGenericSrv][service]: service: " + serviceName);

            
            service.execute(request, response);
            target = (String)request.getAttribute("target");
            
            if(target != null){
                redirect(response, target);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            redirect(response, Constants.ERR_PAGE_URL);
        }finally{
        	System.out.println("[EnjoyGenericSrv][service][End]");
        }
	}
	
	protected void redirect(HttpServletResponse response, String target) {
        try {
            System.out.println("[EnjoyGenericSrv][redirect]: target: "+target);
            response.sendRedirect(target);
        } catch (Exception ioe) {
            System.out.println("[EnjoyGenericSrv][redirect]: IO exception thrown");
            ioe.printStackTrace();
        }
    }
    

}
