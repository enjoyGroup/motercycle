package th.go.motorcycles.app.enjoy.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class ConfigFile {
	private static final String MAX_YEAR   = "conf.value.maxyear";
	private static ConfigFile configFile;
	private static Properties properties ;
	

	public ConfigFile(String fileName) throws Exception {
		try {
			properties = new Properties(); 
			properties.load(new FileInputStream(fileName)); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception("FileNotFoundException");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("IOException");
		}
	} 	
	public static void init(String fileName) throws Exception{
		if (configFile == null) {
//logger.info("init() Begin.");
			configFile = new ConfigFile(fileName);
//logger.info("init() End.");
		}
	}
	public static Properties getProperties(){
		return properties;
	}
	
	public static String getText( String arg ){
		String result = ConfigFile.getProperties().getProperty( arg );		
	    result = EnjoyUtils.convertDataThai(result);
		return result;
	}
	
	public static String getMAX_YEAR() {
		return getText(MAX_YEAR);
	}
}
