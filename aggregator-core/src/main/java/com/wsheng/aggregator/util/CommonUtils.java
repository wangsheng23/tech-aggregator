/**
 * 
 */
package com.wsheng.aggregator.util;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;



/**
 * @author Josh Wang(Sheng)
 * @email swang6@email.com
 */
public class CommonUtils {
	private final static String UTF8_CODE = "UTF-8";
	
	private static Logger logger = Logger.getLogger(CommonUtils.class);
	
    public static String getLocalHostName() {
        try {
            return InetAddress.getLocalHost().getCanonicalHostName();
        }
        catch (UnknownHostException e) {
            return "localhost";
        }
    }
    
    public static String convertToHostName(String ip) {
        InetAddress ina = null;
        try {
            ina = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            return null;
        }
        return ina.getHostName();
    }
    
    public static String convertToIP(String name) {
        InetAddress ina = null;
        try {
            ina = InetAddress.getByName(name);
        } catch (UnknownHostException e) {
            return null;
        }
        return ina.getHostAddress();
    }
    
    public static MediaType converDataType(String dataType){
    
        if (dataType.equals("json")) {
           return MediaType.APPLICATION_JSON; 
        }else {
           return MediaType.APPLICATION_XML;
        }
        
    }
    
    public static String getSimpleMac(String mac) {
        if (mac.length() > 17) {
            mac = mac.substring(0, 17);
        }
        return mac.toUpperCase().replaceAll(":", "");
    }


    public static boolean isRunningJunitTest() {
        return "true".equalsIgnoreCase(System.getProperty("junit.test"));
    }

    public static String upperFirstLetter(String str) {
        String first = str.substring(0, 1).toUpperCase();
        String rest = str.substring(1, str.length());
        return first + rest;
    }

    public static List<String> split(String input, String separator) {
        if (StringUtils.isNotEmpty(input) && StringUtils.isNotEmpty(separator)) {
            String[] outputArray = input.split(separator);
            return Arrays.asList(outputArray);
        } 
        
        return null;

    }
    
    public static boolean str2Boolean(String str) {
    	boolean result = false;
    	if (StringUtils.isNotEmpty(str)) {
    		if (!str.toLowerCase().equals("false")) {
    			result = true;
    		} 
    	}
    	return result;
    }
    
	public static String encode(String str) {
		try {
			return URLEncoder.encode(str, UTF8_CODE);
		} catch (UnsupportedEncodingException e) {
			logger.error("Encode failed on: " + str);
			return str;
		}
	}
	
	public static String decode(String str) {
		try {
			return URLDecoder.decode(str, UTF8_CODE);
		} catch (UnsupportedEncodingException e) {
			logger.error("Decode failed on: " + str);
			return str;
		}
	}
   
}
