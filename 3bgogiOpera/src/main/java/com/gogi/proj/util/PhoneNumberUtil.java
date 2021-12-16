package com.gogi.proj.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberUtil {

	public String phoneNumberHyphenAdd(String num, String mask) {

	    String formatNum = "";
	    
	    if(num == null ) return formatNum;
	    
	    if (num.equals("")) return formatNum;

	    num = num.replaceAll("-","").replaceAll(" ", "");

	    if (num.length() == 11) {
	        if (mask.equals("Y")) {
	            formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
	        }else{
	            formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
	        }
	    }else if(num.length()==8){
	        formatNum = num.replaceAll("(\\d{4})(\\d{4})", "$1-$2");
	    }else{
	        if(num.indexOf("02")==0){
	            if(mask.equals("Y")){
	                formatNum = num.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-****-$3");
	            }else{
	                formatNum = num.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-$2-$3");
	            }
	        }else{
	            if(mask.equals("Y")){
	                formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
	            }else{
	                formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
	            }
	        }
	    }
	    return formatNum;
	}

}
