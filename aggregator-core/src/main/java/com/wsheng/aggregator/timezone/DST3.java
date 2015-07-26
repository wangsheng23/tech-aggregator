/**
 * 
 */
package com.wsheng.aggregator.timezone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 测试看看中国和美国洛杉矶在1970年以来到从现在开始的5年后的时间里，什么时候
 * 使用夏令时，什么时候没有使用夏令时
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 * 
 */
public class DST3 {
	
	
	private static void testDayTime(TimeZone timeZone) {
		System.out.println("Time Zone is : " + timeZone.getDisplayName() + " : " + timeZone.getID());  
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		Calendar start = Calendar.getInstance(timeZone);  
        start.setTime(new Date(0)); // UTC 1970-01-01
        System.out.println("start=" + df.format(start.getTime()));  // will print: start=1970-01-01 08:00:00
		
        Calendar end = Calendar.getInstance(timeZone);
        end.add(Calendar.YEAR, 5);
        System.out.println("end=" + df.format(end.getTime()));
        
        
        boolean find = false;
        for (long i = start.getTimeInMillis(); i < end.getTimeInMillis(); i = start.getTimeInMillis()) {
        	start.add(Calendar.DATE, 1); // add one day
        	
        	if ((start.getTimeInMillis() - i) % (24 * 3600 * 1000L) != 0) { // 是否能被24整除
        		find = true;
        		
        		 System.out.println("from " + df.format(new Date(i)) + " to " + df.format(start.getTime()) + " has "  
                         + (start.getTimeInMillis() - i) + "ms" + "[" + (start.getTimeInMillis() - i) / (3600 * 1000L)  
                         + "hours]");
        	}
        	
        }
        
        if (!find) {  
            System.out.println("Every day is ok.");  
        } 
	}
	
	public static void main(String[] args) {
		TimeZone timeZone = TimeZone.getDefault();  
		DST3.testDayTime(timeZone);
		
		System.out.println(" -------------------- ");
		timeZone = TimeZone.getTimeZone("GMT");  
		DST3.testDayTime(timeZone);
		System.out.println(" -------------------- ");
		
		timeZone = TimeZone.getTimeZone("America/Los_Angeles");  
		DST3.testDayTime(timeZone);  
	}

}
