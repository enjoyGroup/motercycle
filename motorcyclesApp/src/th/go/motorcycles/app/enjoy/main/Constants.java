package th.go.motorcycles.app.enjoy.main;

public class Constants {

	public  static final String CONFIG_FILE         = "ConfigFile";
	
	private static final String EJ_WEB_ROOT 		= "/motorcyclesWeb";
	private static final String ERR_PAGE			= "/enjoyErrorPage.jsp";
	private static final String LOGIN_FAIL			= "/loginFailScn.jsp";
	
	public static final String MAIN_PACKAGE 		= "th.go.motorcycles.web.enjoy.";
	public static final String SERV_URL 			= EJ_WEB_ROOT;
	public static final String PAGE_URL 			= EJ_WEB_ROOT+"/pages/motor";
	public static final String JS_URL 				= EJ_WEB_ROOT+"/js";
	public static final String IMG_URL 				= EJ_WEB_ROOT+"/images";
	public static final String CSS_URL 				= EJ_WEB_ROOT+"/css";
	public static final String ERR_PAGE_URL			= EJ_WEB_ROOT+"/pages/error" + ERR_PAGE;
	public static final String LOGIN_FAIL_URL		= EJ_WEB_ROOT+"/pages/error" + LOGIN_FAIL;
	
	/*JSON status*/
	public static final String STATUS 				= "status";
	public static final String ERR_MSG 				= "errMsg";
	public static final String SUCCESS 				= "SUCCESS";
	public static final String ERROR 				= "ERROR";
	public static final String ERR_TYPE 			= "errType";
	
	/*Error Type*/
	public static final String ERR_ERROR 			= "E";
	public static final String ERR_WARNING 			= "W";
}
