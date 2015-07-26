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
public class TimeZone2 {
	
    public static void main(String[] args) {  
    	Date date = new Date(1391174450000L); // 2014-1-31 21:20:50  
        System.out.println(date);  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));  
        // 或者可以 Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));  
        calendar.setTime(date);  
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));  
    }  

}
