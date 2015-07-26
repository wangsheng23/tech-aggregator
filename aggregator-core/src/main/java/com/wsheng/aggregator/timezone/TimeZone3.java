/**
 * 
 */
package com.wsheng.aggregator.timezone;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 * 
 */
public class TimeZone3 {
	
    public static void main(String[] args) {  
    	Date date = new Date(1391174450000L); // 2014-1-31 21:20:50  
        System.out.println(date);  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));  
        // 或者可以 Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));  
        calendar.setTime(date);  
        Calendar calendar2 = Calendar.getInstance();  
        calendar2.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));  
        System.out.println(calendar2.getTime());  
    }  

}
