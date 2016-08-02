/**
 * 
 */
package com.wsheng.aggregator.timezone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ybei.com
 * 
 */
public class DST2 {
	
	public static void main(String[] args) throws Exception {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");  
		String sTime = "1991-04-07 00:00:00";  
		Date time = sdf.parse(sTime);  
		Calendar cd = Calendar.getInstance();  
		cd.setTime(time);  
		cd.add(Calendar.DATE, 7);  
		time = cd.getTime();  
		System.out.println(sdf.format(time)); 
	}

}
