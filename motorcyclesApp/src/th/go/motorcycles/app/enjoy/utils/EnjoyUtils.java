package th.go.motorcycles.app.enjoy.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EnjoyUtils {
	
	public static String nullToStr(String str){
        return (str==null?"":str.trim());
    }
    
    public static String dateToString(Date dDate, String stFormat){
        String stDate = "";
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(stFormat,Locale.US);
            stDate = sdf.format(dDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
           
        return stDate;
    }
    
    public static String dateFormat (String av_date, String av_currFormat, String av_toFormat){
        System.out.println("[FormatUtil][dateFormat][Begin]");
        
        SimpleDateFormat    dt              = null;
        Date                date            = null;
        SimpleDateFormat    dt1             = null;
        String              dateFormat      = null;
        
        try{
            if(av_date==null || av_date.equals("")){
                dateFormat = "";
            }else{
                dt      = new SimpleDateFormat(av_currFormat); 
                date    = dt.parse(av_date); 
                dt1     = new SimpleDateFormat(av_toFormat,Locale.US);
                
                dateFormat = dt1.format(date);
            }
        }catch(Exception e){
                e.printStackTrace();
        }finally{
            System.out.println("[FormatUtil][dateFormat][End]");
        }
        
        return dateFormat;
    }
    
    public static String  sanitizeURLString(String av_val){
        
        System.out.println("[FormatUtil][sanitizeURLString][Begin]");
        
        String[]    la_symbol   = {"%" , "<" , ">" , "#" , "{" , "}" , "|" , "\\" , "^" , "~" , "[" , "]" , "`" , ";" , "/" , "?" , ":" , "@" , "=" , "&" , "$"};
        String[]    la_replace  = {"%25", "%3C", "%3E", "%23", "%7B", "%7D", "%7C", "%5C", "%5E", "%7E", "%5B", "%5D", "%60", "%3B", "%2F", "%3F", "%3A", "%40", "%3D", "%26", "%24"};
        String      lv_return   = "";
        String      lv_char     = "";
        
        try{
            loop1:for(int i=0;i<av_val.length();i++){
                lv_char = av_val.substring(i, (i+1));
                loop2:for(int j=0;j<la_symbol.length;j++){
                    if(lv_char.indexOf(la_symbol[j]) > -1){
                        lv_char = lv_char.replaceAll(la_symbol[j], la_replace[j]);
                        break loop2;
                    }
                }
                lv_return = lv_return + lv_char;
            }
            
            System.out.println("[FormatUtil][sanitizeURLString] lv_return :: " + lv_return);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            System.out.println("[FormatUtil][sanitizeURLString][End]");
        }
        
        return lv_return;
    }
    
	public static String convertFloatToDisplay(String stFloat,int point){

		if (!stFloat.equals("")){
			String strFormat = "##,##0";
			if (point > 0) { strFormat = strFormat + "."; }
			for(int i=0;i<point;i++){
				strFormat = strFormat + "0";	
			}		
			DecimalFormat df	= new DecimalFormat(strFormat);			
			stFloat 			= df.format(Double.parseDouble(stFloat));	
		}

		return stFloat;
	}

	/**
	 * @param stData
	 * @return String
	 * convert Thai format
	 */
	public static String convertDataThai(String stData){
		String lv_strData 	 = nullToStr(stData);
		StringBuffer strTemp = new StringBuffer(lv_strData);		
		int maxLength 		 = lv_strData.length();
		int code;

		for( int i = 0; i < maxLength; i++){ 
			code = (int) strTemp.charAt(i); 
			if ( ( 0xA1 <= code ) && ( code <= 0xFB ) ){ 
				strTemp.setCharAt( i, (char) ( code + 0xD60 ) ); 
			} 
		} 
		return strTemp.toString(); 
	}
	/*
	public static String getCustNext(String code) {
    	StringBuffer codeReturn = new StringBuffer("CUS000");
    	String lv_strData 	 = nullToStr(code); 	
		int maxLength 		 = lv_strData.length();
		System.out.println("maxLength:"+maxLength);
		if(maxLength>=7){
			String lastRef       = lv_strData.substring(3);
	    	int codeInt  = Integer.parseInt(lastRef);
	    	codeInt++;
	    	System.out.println("codeInt:"+codeInt);
	    	codeReturn = codeReturn.append(String.valueOf(codeInt)); 
	    	System.out.println("codeReturn:"+codeReturn);
		}
        return codeReturn.toString();
    }*/
	
    public static String getCustNext(String code) {
    	String returnValue      = "";
    	String lv_strData 	    = nullToStr(code); 
    	int codeInt  = Integer.parseInt(lv_strData);
    	codeInt++;
    	System.out.println("codeInt:"+codeInt);
    	returnValue             = String.valueOf(codeInt); 
        return returnValue;
    }
}

