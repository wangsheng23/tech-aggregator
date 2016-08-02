/**
 * 
 */
package com.wsheng.aggregator.timezone;

import java.util.Date;
import java.util.TimeZone;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ybei.com
 * 
 */
public class TimeZone4 {
	
    public static void main(String[] args) {  
    	Date date = new Date(1391174450000L); // 2014-1-31 21:20:50    
        System.out.println(date);  
        date = changeTimeZone(date, TimeZone.getTimeZone("Asia/Shanghai"), TimeZone.getTimeZone("GMT"));  
        System.out.println(date);  
    }  
      
    /** 
     * 获取更改时区后的日期 
     * @param date 日期 
     * @param oldZone 旧时区对象 
     * @param newZone 新时区对象 
     * @return 日期 
     */  
    public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {  
        Date dateTmp = null;  
        if (date != null) {  
            int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();  
            dateTmp = new Date(date.getTime() - timeOffset);  
        }  
        return dateTmp;  
    }  

}
