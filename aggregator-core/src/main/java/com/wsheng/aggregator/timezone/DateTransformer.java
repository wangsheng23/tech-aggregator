package com.wsheng.aggregator.timezone;
import java.text.*;    
import java.util.*;    
 
/**
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ybei.com
 *
 */
public class DateTransformer  {  
    public static final String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";  
         
    public static String dateTransformBetweenTimeZone(Date sourceDate, DateFormat formatter,  
        TimeZone sourceTimeZone, TimeZone targetTimeZone) {  
        Long targetTime = sourceDate.getTime() - sourceTimeZone.getRawOffset() + targetTimeZone.getRawOffset();  
        return DateTransformer.getTime(new Date(targetTime), formatter);  
    }  
         
    public static String getTime(Date date, DateFormat formatter) {  
       return formatter.format(date);  
    }  
         
    public static void main(String[] args) {  
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);  
        Date date = Calendar.getInstance().getTime();  
        System.out.println(" date: " + date);
        
        TimeZone srcTimeZone = TimeZone.getTimeZone("EST");  
        TimeZone destTimeZone = TimeZone.getTimeZone("GMT+8");  
        System.out.println(DateTransformer.dateTransformBetweenTimeZone(date, formatter, srcTimeZone, destTimeZone));  
    }  
} 