package com.wsheng.aggregator.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/**
 * @author Josh Wang(Sheng)
 * @email  josh_wang23@hotmail.com
 */
public class DateUtils {

    public static final String 	MIDDLE_LINE_TIMESTAMP 			= "yyyy-MM-dd HH:mm:ss";
    public static final String  MIDDLE_LINE_TIME_ZONE_TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"; // Z : zone T: customized character, meaningless
    
    
    private static final long 	ONE_DAY 			  = 24 * 3600 * 1000L;
    private static long 		current_time;

    /**
     * Get the current time
     *
     * @return current_time
     */
    public static long getCurrentTime() {
        current_time = Calendar.getInstance().getTimeInMillis();
        return current_time;
    }

    /**
     * This method uesd to handle the special case which cross months. ie.
     * <p/>
     * Getting the data from CMS in the time(2013/8/31 23:55:00), and the request may
     * be completed in 2013/09/01, and we will get the data from CMS using 2013/09/01 as the endtime,
     * in this case, we will get data from 2013/08/01 to 2013/08/31 and get some data from
     * 2013/09/01 to 2013/09/30.
     *
     * @return current_time
     * @TODO: We need to get the exact current time before getting the data from CMS and pass the time to remain requests(get other data from CMS)
     * as a parameter to simulate the request time of all the requests are the same.
     */
    public static long getSavedTime() {
        return current_time;
    }

    /**
     * Get the string format of the time specified
     *
     * @param time
     * @return string format of the time
     */
    public static String longToString(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        String year = calendar.get(Calendar.YEAR) + "";

        String month = (calendar.get(Calendar.MONTH) + 1) + "";
        if (month.length() == 1) {
            month = "0" + month;
        }

        String date = calendar.get(Calendar.DATE) + "";
        if (date.length() == 1) {
            date = "0" + date;
        }

        return year + "/" + month + "/" + date;
    }

    /**
     * Get the month's start time specified by the given time
     *
     * @param time
     * @return the start time
     */
    public static long getStartOfMonth(long time) {
        time = (time / ONE_DAY) * ONE_DAY;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        calendar.set(Calendar.DATE, 1);

        return calendar.getTimeInMillis();
    }

    /**
     * Get next month's start time specified by the given time
     *
     * @param time
     * @return the start time
     */
    public static long getStartOfNextMonth(long time) {
        time = (time / ONE_DAY) * ONE_DAY;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        int month = calendar.get(Calendar.MONTH);
        month++;
        if (month == 13) {
            month = 1;
        }

        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);

        return calendar.getTimeInMillis();
    }

    /**
     * Get the start time of the month which is n months before the given time
     * n equals 0 means the start time of current month
     * n equals negative number means the start time of following months
     *
     * @param time
     * @param n
     * @return start time of the month specified by n
     */
    public static long getStartOfPreviousMonth(long time, int n) {
        time = (time / ONE_DAY) * ONE_DAY;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        month -= n;
        if (month <= 0) {
            year = year + month / 12 - 1;
            month = month % 12 + 12;
        } else if (month > 12) {
            year = year + (month - 1) / 12;
            month = (month - 1) % 12 + 1;
        }

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);

        return calendar.getTimeInMillis();
    }
    
    /**
     * Get previous six month base on current time
     * @return
     */
    public static Date getPreviousSixMonth() {
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MONTH, -6);
    	return calendar.getTime();
    }
    
    public static String compareTime(Calendar start, Calendar end) {
        if (end.get(Calendar.YEAR) > start.get(Calendar.YEAR))
            return "" + (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) + " years";
        if (end.get(Calendar.MONTH) > start.get(Calendar.MONTH))
            return "" + (end.get(Calendar.MONTH) - start.get(Calendar.MONTH)) + " months";
        if (end.get(Calendar.DATE) > start.get(Calendar.DATE))
            return "" + (end.get(Calendar.DATE) - start.get(Calendar.DATE)) + " days";
        if (end.get(Calendar.HOUR) > start.get(Calendar.HOUR))
            return "" + (end.get(Calendar.HOUR) - start.get(Calendar.HOUR)) + " hours";
        if (end.get(Calendar.MINUTE) > start.get(Calendar.MINUTE))
            return "" + (end.get(Calendar.MINUTE) - start.get(Calendar.MINUTE)) + " minutes";
        if (end.get(Calendar.SECOND) > start.get(Calendar.SECOND))
            return "" + (end.get(Calendar.SECOND) - start.get(Calendar.SECOND)) + " seconds";
        return "In miliseconds";
    }
    
    
    public static Date str2Date(String str, String format) {
    	DateFormat dFormat = new SimpleDateFormat(format);
    	Date date = null;
    	try {
    		if (StringUtils.isNotEmpty(str))
    			date = dFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	return date;
    }
    
    /**
     * This used to parse some string like : Mon Mar 16 19:28:03 CST 2015 
     * to
     * 2015-03-17 09:28:03
     * 
     * @param str
     * @param format
     */
    public static String str2Date2Str(String str, String format) {
    	DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    	String result = "2015-01-01 00:00:00";
		try {
			Date date = dateFormat.parse(str);
			dateFormat = new SimpleDateFormat(format);
			result = dateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return result;
    }
    
    public static String getCurrentTimeStr() {
    	Calendar calendar = Calendar.getInstance();
		String currentTimeStr = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" 
				+ calendar.get(Calendar.DAY_OF_MONTH) + "-"+ calendar.get(Calendar.HOUR_OF_DAY) +"-"
				+ calendar.get(Calendar.MINUTE) + "-" + calendar.get(Calendar.SECOND);
		return currentTimeStr;
    }
    
    public static boolean beforeCurrentTime(String timeStamp) {
    	Calendar calendar = Calendar.getInstance(); // 获取当前时间
    	Date date = str2Date(timeStamp, MIDDLE_LINE_TIMESTAMP);
    	return calendar.getTimeInMillis() > date.getTime();
    }
}
