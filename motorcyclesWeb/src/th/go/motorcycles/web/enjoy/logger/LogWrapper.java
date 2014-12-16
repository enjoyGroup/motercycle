package th.go.motorcycles.web.enjoy.logger;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogWrapper {
	private Logger fLogger;
	private LogWrapper(Logger myLogger){
		this.fLogger = myLogger;
	}

	public static void init(){
		BasicConfigurator.configure();
	}

	public static void init(String configPath){
		PropertyConfigurator.configureAndWatch(configPath,300000);
	}
	
	public static LogWrapper getLogger(Class clazz) {
		String fullClass = clazz.getName();
		return getLogger(fullClass);
	}

	public static LogWrapper getLogger(String fullClass) {
		Logger myLogger = Logger.getLogger(fullClass);
		return (new LogWrapper(myLogger));
	}

	public void debug(Object obj) {
		fLogger.debug(obj);
	}

	public void info(Object obj) {
		  fLogger.info(obj);
	}

	public void warn(Object obj) {
		  fLogger.warn(obj);
	}

	public void error(Object obj) {
		fLogger.error(obj);
	}

	public void error(Object obj, Throwable throwable ){
		fLogger.error(obj,throwable);
	}
	
	public void fatal(Object obj) {
		fLogger.fatal(obj);
	}

	public boolean isDebugEnable(){
		return fLogger.isDebugEnabled();
	}
	
	public void trace(Object obj){
		fLogger.trace(obj);
	}
	
	public void trace(Object obj, Throwable throwable ){
		fLogger.trace(obj,throwable);
	}
	
/*	
	private static String getClassData(String fullClass,String mode){
	int index   = fullClass.lastIndexOf(".");
		if(mode.equals("PACKAGE")){
			return fullClass.substring(0,index);
		}else if(mode.equals("SHORT")){
			return fullClass.substring(index+1);
		}else{
			System.out.println("Invalid mode for getClassData()");
			return  null;
		}
	}

	private static String getPackageName(String fullClass){
		return getClassData(fullClass,"PACKAGE");
	}

	private static String getShortClassName(String fullClass) {
		return getClassData(fullClass,"SHORT");
	}
*/
}
