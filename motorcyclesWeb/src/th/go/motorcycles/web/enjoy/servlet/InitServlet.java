package th.go.motorcycles.web.enjoy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.go.motorcycles.app.enjoy.main.ConfigFile;
import th.go.motorcycles.app.enjoy.main.Constants;
import th.go.motorcycles.web.enjoy.logger.LogWrapper;

/**
 * Servlet implementation class for Servlet: InitServlet
 *
 */
 public class InitServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public InitServlet() {
		super();
	}   	
	
	public void init() throws ServletException {
//System.out.println("InitServlet Begin().");
		String realPath  = getServletContext().getRealPath("");  
		try{
			String log4jFile = realPath + getServletContext().getInitParameter("Log4JFile");
			initLog4J(log4jFile); 
			ConfigFile.init(realPath + getServletContext().getInitParameter(Constants.CONFIG_FILE));
		} catch(Exception e){
			e.printStackTrace();
			throw new ServletException("Can not Initial Servlet [" + e.toString() + "]");
		}
//System.out.println("InitServlet End().");
	}
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   
	
	/**
	 * Log4J Initialization
	 *
	 */
	private static void initLog4J(String log4jFile){ 
		try{
			System.out.println("Initial Log4J Begin.");
			LogWrapper.init(log4jFile );
		}catch(Exception e){
			System.err.println("Inital Log4J failed.");
			e.printStackTrace();
		}finally{
			System.out.println("Initial Log4J End.");
		}
	}
}