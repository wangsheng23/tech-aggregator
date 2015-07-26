/**
 * 
 */
package com.wsheng.aggregator.timezone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 * 
 */
public class TimeZone1 {
	
	public static void main(String[] args) {
		Date date = new Date(1391174450000L); // 2014-1-31 21:20:50  
	    String dateStr = "2014-1-31 21:20:50 ";  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));  
	    try {  
	        Date dateTmp = dateFormat.parse(dateStr);  
	        System.out.println(dateTmp);  
	    } catch (ParseException e) {  
	        e.printStackTrace();  
	    }  
	    String dateStrTmp = dateFormat.format(date);  
	    System.out.println(dateStrTmp);  
	}
	

}
