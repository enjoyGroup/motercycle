package th.go.motorcycles.app.enjoy.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;

public class EnjoyUtils {
	
	public static void main(String[] args) {
		Date	  			date				= new Date();
		currDateThai();
		System.out.println(dateToStringThai(date));

	}
	
	public static String nullToStr(String str){
        return (str==null?"":str.trim());
    }

	public static String nullToStr(String str, String strRep){
        return (str==null?strRep:str.trim());
    }
	
	public static String nullToStrUpperCase(String str){
        return (str==null?"":str.trim().toUpperCase());
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
    
    public static String dateToStringThai(Date dDate){
        String 	stDate 	= "";
        int 	year 	= 0;
        int 	month 	= 0;
        int 	day 	= 0;
        try{
//            SimpleDateFormat 	sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            Calendar 			c 	= Calendar.getInstance(Locale.US);
            c.setTime(dDate); 
            
            
            year 	= c.get(Calendar.YEAR);
			month 	= c.get(Calendar.MONTH) + 1;
			day 	= c.get(Calendar.DATE);
            
//            stDate = sdf.format(dDate);
            stDate = String.format("%02d/%02d/%04d", day, month, year+543);
        } catch (Exception e) {
            e.printStackTrace();
        }
           
        return stDate;
    }
    
    public static String currDateThai(){
        String 	stDate 	= "";
        Date 	date	= new Date();
        
        try{
            java.text.SimpleDateFormat df= new java.text.SimpleDateFormat();
            
            df.applyPattern("yyyyMMdd");
            stDate = df.format(date);
            System.out.println(stDate);
            
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
//                dt1     = new SimpleDateFormat(av_toFormat,Locale.US);// ค.ศ.
                dt1     = new SimpleDateFormat(av_toFormat);//พ.ศ.
                
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
    
    public static String displayAmountThai(String amt)
    {
        String CurText,Thai_CurText = "";
        char ch;
        String st_ch;
        int idx,Digit;

        String[] GetNum   = { "หนึ่ง","สอง","สาม","สี่","ห้า","หก","เจ็ด","แปด","เก้า","ยี่","เอ็ด" };
        String[] GetDigit = { "","สิบ","ร้อย","พัน","หมื่น","แสน","ล้าน"};

        CurText = amt;
        Digit = 0;
        for ( idx = CurText.length() ; idx >= 1 ;idx-- )
        {
           if ( Digit == 6 ){
              Digit = 0;
              Thai_CurText = "ล้าน" + Thai_CurText;
           }

           ch      = CurText.substring(idx-1,idx).toCharArray()[0];
           st_ch   = CurText.substring(idx-1,idx);
           switch (ch){
              case '.' :  Digit = 0;
                          Thai_CurText = "บาท" + Thai_CurText;
                          break;
              case ',' :  break;

              case '0' :  Digit = Digit + 1;
                          break;
              case '1' :  Digit = Digit + 1;
                      switch (Digit){
                          case 1 : if ( st_ch.equals(CurText.substring(0,idx))) {
                                       Thai_CurText = GetNum[0] + Thai_CurText;
                                   }else if (! CurText.substring(idx-1,idx).equals("0") ){
                                	   if(CurText.substring(idx-2,idx-1).equals("0")){
                                		   Thai_CurText = GetNum[0] + Thai_CurText;
                                	   }else{
                                		   Thai_CurText = GetNum[10] + Thai_CurText;
                                	   }
                                   }else {
                                       Thai_CurText = GetNum[0] + Thai_CurText;
                                   }
                                   break;
                          case 2 : Thai_CurText = GetDigit[Digit-1] + Thai_CurText;
                                   break;
                          case 3 : Thai_CurText = GetNum[0] + GetDigit[Digit-1] + Thai_CurText;
                                   break;
                          case 4 : Thai_CurText = GetNum[0] + GetDigit[Digit-1] + Thai_CurText;
                                   break;
                          case 5 : Thai_CurText = GetNum[0] + GetDigit[Digit-1] + Thai_CurText;
                                   break;
                          case 6 : Thai_CurText = GetNum[0] + GetDigit[Digit-1] + Thai_CurText;
                                   break;
                          }
                          break;
              case '2' :  Digit = Digit + 1;
                          if ( Digit == 2 ) {
                              Thai_CurText = GetNum[9] + GetDigit[Digit-1] + Thai_CurText;
                          }else{
                              Thai_CurText = GetNum[1] + GetDigit[Digit-1] + Thai_CurText;
                          }
                          break;
              default  :  Digit = Digit + 1;
                          Thai_CurText = GetNum[Integer.parseInt(st_ch)-1] + GetDigit[Digit-1] + Thai_CurText;
                          break;

           }
        }

        int pos_dot = CurText.indexOf(".");
        if (Integer.parseInt(CurText.substring(pos_dot+1,CurText.length())) == 0 ){
        	if(amt.equals("0.00")){
        		Thai_CurText = "ศูนย์" + Thai_CurText + "ถ้วน";
        	}else{
        		Thai_CurText = Thai_CurText + "ถ้วน";
        	}
        }else{
        	Thai_CurText = Thai_CurText + "สตางค์";
        }
        return Thai_CurText;
    }
    
	public static String displayDateThai(String dateThai) {
        Hashtable tblMonth = new Hashtable();
        String    day      = "";
        int       month    = 0;
        int       year     = 0;
        String	  dateDisplay = "";
        try {
        	if (!dateThai.equals(""))
			{	
	            tblMonth.put("1", "มกราคม");
	            tblMonth.put("2", "กุมภาพันธ์");
	            tblMonth.put("3", "มีนาคม");
	            tblMonth.put("4", "เมษายน");
	            tblMonth.put("5", "พฤษภาคม");
	            tblMonth.put("6", "มิถุนายน");
	            tblMonth.put("7", "กรกฏาคม");
	            tblMonth.put("8", "สิงหาคม");
	            tblMonth.put("9", "กันยายน");
	            tblMonth.put("10","ตุลาคม");
	            tblMonth.put("11","พฤศจิกายน");
	            tblMonth.put("12","ธันวาคม");
	
	            day    = dateThai.substring(6, 8);
				month  = Integer.parseInt(dateThai.substring(4, 6));
		        year   = Integer.parseInt(dateThai.substring(0, 4));
		        dateDisplay = day + " " + tblMonth.get(String.valueOf(month)).toString() +" พ.ศ. " + year;
			} else {
				dateDisplay = "";			
			}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            tblMonth = null;
        }
        return dateDisplay;
	}
	
	public static String replaceComma(String av_val) {
		
		String lv_ret = null;
		
        try {
        	if (av_val!=null&&!av_val.equals("")){	
        		lv_ret = av_val.replaceAll(",", "");
			} else {
				lv_ret = "";			
			}
        } catch (Exception e) {
        	e.printStackTrace();
        } 
        return lv_ret;
	}
	
	public static String chkBoxtoDb(String av_val) {
		
		String lv_ret = null;
		
        try {
        	if (av_val!=null&&!av_val.equals("")){	
        		lv_ret = av_val;
			} else {
				lv_ret = "N";			
			}
        } catch (Exception e) {
        	e.printStackTrace();
        } 
        return lv_ret;
	}
	
	public static Double pareseDouble(String av_val) {
		
		Double lv_ret = 0.00;
		
        try {
        	if (av_val!=null&&!av_val.equals("")){	
        		lv_ret = Double.parseDouble(av_val);
			} 
        } catch (Exception e) {
        	e.printStackTrace();
        } 
        return lv_ret;
	}
	
}

