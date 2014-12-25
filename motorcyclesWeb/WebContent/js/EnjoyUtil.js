function isBlank(objValue){
    if(objValue != null && objValue != undefined && objValue != ''){
        return false;
    }else{
        return true;
    }
}

//Example gp_replaceComma(78,500.00);
function gp_replaceComma(av_val){
	
	var lv_ret = av_val;
	
	if(av_val!=null && av_val!=""){
		lv_ret = av_val.replace(/,/g,"");
	}

	return lv_ret;
}

function gp_format(av_obj, decimals){
	return gp_number_format(av_obj, decimals, '.', ',');
}

//Example gp_number_format(av_obj, 2, ".", ",");
function gp_number_format (av_obj, decimals, dec_point, thousands_sep) {
    
    var exponent    = "";
    var numberstr   = null;
    var eindex      = null;
    var temp        = null;
    var sign        = null;
    var integer     = null;
    var fractional  = null;
    var number      = av_obj.value.replace(/,/g,"");
    var msgVal      = "";

    if(gp_trim(number)==""){
        return true;
    }
    
    numberstr   = number.toString ();
    eindex      = numberstr.indexOf ("e");
    
    if (eindex > -1) {
        exponent = numberstr.substring (eindex);
        number = parseFloat (numberstr.substring (0, eindex));
    }
       
    if (decimals != null) {
        temp    = Math.pow (10, decimals);
        number  = Math.round (number * temp) / temp;
    }
    
    sign        = number < 0 ? "-" : "";
    integer     = (number > 0 ? Math.floor (number) : Math.abs (Math.ceil (number))).toString ();
    
    fractional  = number.toString ().substring (integer.length + sign.length);
    dec_point   = dec_point != null ? dec_point : ".";
    fractional  = decimals != null && decimals > 0 || fractional.length > 1 ? (dec_point + fractional.substring (1)) : "";
    
    if (decimals != null && decimals > 0) {
        for (i = fractional.length - 1, z = decimals; i < z; ++i) {
            fractional += "0";
        }
    }
    
    thousands_sep = (thousands_sep != dec_point || fractional.length == 0) ? thousands_sep : null;
    if (thousands_sep != null && thousands_sep != "") {
        for (i = integer.length - 3; i > 0; i -= 3){
            integer = integer.substring (0 , i) + thousands_sep + integer.substring (i);
        }
    }
    
    msgVal = sign + integer + fractional + exponent;
    if(msgVal.indexOf("NaN") > -1){
        return false;
    }
    
    av_obj.value = msgVal;
    return true;
    
    //av_obj.value = sign + integer + fractional + exponent;
    //return sign + integer + fractional + exponent;
}

function gp_trim(str) {
    return str.replace(/^\s+|\s+$/g,"");
}

function gp_validNumberKey(e){
//    var keyFormat = /^\d/g;
    var keyFormat = /[^\d\.]/g;
    var charVal = String.fromCharCode(e.keyCode);
    
    return !keyFormat.test(charVal);
}

function gp_validNumberKeyMinus(e){
//    var keyFormat = /^\d/g;
    var keyFormat = /[^\d\.\-]/g;
    var charVal = String.fromCharCode(e.keyCode);
    
    return !keyFormat.test(charVal);
}

function gp_clearMsg(ao_obj){
    ao_obj.value = "";
}

function gp_sanitizeURLString(av_val){
    var la_symbol   = ["%" , "<" , ">" , "#" , "{" , "}" , "|" , "\\" , "^" , "~" , "[" , "]" , "`" , ";" , "/" , "?" , ":" , "@" , "=" , "&" , "$"];
    var la_replace  = ["%25", "%3C", "%3E", "%23", "%7B", "%7D", "%7C", "%5C", "%5E", "%7E", "%5B", "%5D", "%60", "%3B", "%2F", "%3F", "%3A", "%40", "%3D", "%26", "%24"];
    var lv_return   = "";
    var lv_char		= null;

    loop1:for(var i=0;i<av_val.length;i++){
        lv_char = av_val.substr(i, 1);
        loop2:for(var j=0;j<la_symbol.length;j++){
            if(lv_char.indexOf(la_symbol[j]) > -1){
                lv_char = lv_char.split(la_symbol[j]).join(la_replace[j]);
                break loop2;
            }
        }
        lv_return = lv_return + lv_char;
    }
    
    return lv_return;
}

function gp_rowTableIndex(ao_obj){
    var lv_index            = 0;
    var lv_tagName          = "";
    var lo_obj              = ao_obj;
    
    lv_tagName  = lo_obj.tagName.toUpperCase();
    while (lv_tagName != "TR") {
        lo_obj      = lo_obj.parentNode;
        lv_tagName  = lo_obj.tagName.toUpperCase();
    }
    
    lv_index = lo_obj.rowIndex;
    
    return lv_index;
}

function gp_rowTableObj(ao_obj){
    var lv_tagName          = "";
    var lo_obj              = ao_obj;
    
    lv_tagName  = lo_obj.tagName.toUpperCase();
    while (lv_tagName != "TR") {
        lo_obj      = lo_obj.parentNode;
        lv_tagName  = lo_obj.tagName.toUpperCase();
    }
    
    return lo_obj;
}

//av_val ==> dd/MM/yyyy
function gp_toDate(av_val){
    var dateArray   = null;;
    var d           = null;
    
    try{
        dateArray   = av_val.split("/");
        d           = new Date(dateArray[2], dateArray[1], dateArray[0], 0, 0, 0, 0);
    }catch(e){
        d = null;
    }
    
    return d;
}

function gp_progressBarOn(){
    scroll(0,0);
    var outerPane = document.getElementById('FreezePane');
    var processBar = document.getElementById('processBar');
    if (outerPane) outerPane.className = 'FreezePaneOn';
    if(processBar) processBar.className = 'processBarOn';
}

function gp_progressBarOff(){
    scroll(0,0);
    var outerPane = document.getElementById('FreezePane');
    var processBar = document.getElementById('processBar');
    if (outerPane) outerPane.className = 'FreezePaneOff';
    if(processBar) processBar.className = 'processBarOff';
}

function gp_checkDate(ao_obj){
    var allowBlank 	= true;
    var minYear 	= 2000;
    //var maxYear 	= (new Date()).getFullYear();
    var errorMsg 	= "";
    var field 		= gp_trim(ao_obj.value);

    // regular expression to match required date format
    re = /^(\d{1,2})\/(\d{1,2})\/(\d{4})$/;
	
    try{
	    if(field != '') {
			
	    	if(field.length==8){
	    		field 			= field.substring(0, 2) + "/" + field.substring(2, 4) + "/" + field.substring(4, 8);
	    		ao_obj.value 	= field;
	    	}
	    	
	      if(regs = field.match(re)) {
	        if(regs[1] < 1 || regs[1] > 31) {
	          errorMsg = "กรอกวันที่ผิด: " + regs[1];
	        } else if(regs[2] < 1 || regs[2] > 12) {
	          errorMsg = "กรอกเดือนผิด: " + regs[2];
	        } else if(regs[3] < minYear) {
	          errorMsg = "กรอกปีผิด: " + regs[3] + " ต้องเป็น พ.ศ. เท่านั้น";
	        }
	      } else {
	        errorMsg = "กรอกรูปแบบวันที่ผิด";
	      }
	    }

	    if(errorMsg != "") {
	      alert(errorMsg);
	      ao_obj.value = "";
	      ao_obj.focus();
	      return false;
	    }
    }catch(e){
    	alert("checkDate :: " + e);
    	return false;
    }

    return true;
  }

function showCalendar() {
    
    /*
    	p_month : 0-11 for Jan-Dec; 12 for All Months.
    	p_year	: 4-digit year
    	p_format: Date format (mm/dd/yyyy, dd/mm/yy, ...)
    	p_item	: Return Item.
    */

    p_item = arguments[0];

    if (arguments[1] == null || arguments[1] == ""){
        p_month = new String(gNow.getMonth());
    }else{
        p_month = arguments[1];
    }
    
    if (arguments[2] == "" || arguments[2] == null){
        p_year = new String(gNow.getFullYear().toString());
    }else{
    	p_year = arguments[2];
    }
	
    if(arguments[3] == "1"){
    	p_format = "DD/MM/YYYY";
    }else if(arguments[3] == "2"){
    	p_format = "MM/DD/YYYY";
    }else{
        p_format="YYYY/MM/DD";
    }
    
    //vWinCal = window.open("", "calendar", "width=150,height=180,status=no,resizable=no,toolbar=no,top=250,left=450");
    // Set position for click as easier [surachai].
    p_top = arguments[4];
    p_left = arguments[5];
    
    vWinCal = window.open("", "calendar", "width=230,height=170,status=no,resizable=no,toolbar=no,top="+(p_top+70)+",left="+p_left+",screenY=400");
    vWinCal.opener = self;
    ggWinCal = vWinCal;
    vWinCal.focus();
    build(p_item, p_month, p_year, p_format);
}