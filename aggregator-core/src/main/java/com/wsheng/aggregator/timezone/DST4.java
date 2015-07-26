/**
 * 
 */
package com.wsheng.aggregator.timezone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 为什么一天会变成23、25小时
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 * 
 */
public class DST4 {
	
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	public static void main(String[] args) {
		// 特殊时间点  
        long abc = 1414918799000l;  
        printDate(abc);  
        abc = 1414918800000l;  
        printDate(abc);  
	}
	
	private static void printDate(long now) {  
        // 中国时间  
        // TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));  
        // System.out.println(new Date(now).toString());  
        // System.out.println(new Date(now).toLocaleString());  
        // 美国时间  
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));  
        Date date = new Date(now);
        System.out.println(date);  
        System.out.println(df.format(date));  
        System.out.println("============================================================");  
	}  

}
