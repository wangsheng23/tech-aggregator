/**
 * 
 */
package com.wsheng.aggregator.timezone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 1986年5月4号的0点不见了
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 * 
 */
public class DST1 {
	
	public static final DateFormat Y2MD_HMS = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) throws Exception {
		Date d = DST1.Y2MD_HMS.parse("1986-5-4 0:0:0");
		System.out.println(d); 
	}

}
