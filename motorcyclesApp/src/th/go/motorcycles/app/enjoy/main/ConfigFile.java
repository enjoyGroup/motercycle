package th.go.motorcycles.app.enjoy.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import th.go.motorcycles.app.enjoy.utils.EnjoyUtils;

public class ConfigFile {
	private static final String VAT_RATE   		 = "conf.vatRate";
	private static final String PADING_INVOICE   = "pading.invoice";
	private static final String BEGIN_INVOICE    = "begin.invoice.";
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
			configFile = new ConfigFile(fileName);
		}
	}
	public static Properties getProperties(){
		return properties;
	}
	
	public static String getText( String arg ){
		String result = ConfigFile.getProperties().getProperty( arg );		
	    result = EnjoyUtils.convertDataThai(result);
//System.out.println("result  :: " + result);	
		return result;
	}
	
	public static String getVAT_RATE() {
		return getText(VAT_RATE);
	}
	
	public static String getPADING_INVOICE() {
		return getText(PADING_INVOICE);
	}

	public static String getBEGIN_INVOICE(String typeInvoice) {
		return getText(BEGIN_INVOICE + typeInvoice);
	}
}
