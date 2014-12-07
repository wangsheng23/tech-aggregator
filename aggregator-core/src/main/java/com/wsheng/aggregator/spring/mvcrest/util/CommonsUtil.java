/**
 * 
 */
package com.wsheng.aggregator.spring.mvcrest.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.MediaType;



/**
 * @author Josh Wang(Sheng)
 * @email swang6@email.com
 */
public class CommonsUtil {
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
   
}
