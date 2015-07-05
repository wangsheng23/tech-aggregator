package com.wsheng.aggregator.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Josh Wang(Sheng)
 * @email  josh_wang23@hotmail.com
 */
public class ExceptionUtils {
	
	/**
	 * Log the exception stack trace
	 * 
	 * @param e
	 * @return
	 */
	public static String getStackTraceMsg(Exception e) {
		StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	
}
