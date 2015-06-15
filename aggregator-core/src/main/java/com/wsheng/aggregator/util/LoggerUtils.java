package com.wsheng.aggregator.util;

import org.apache.log4j.Logger;

/**
 * used to highlight the important log with the log level if needed
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 *
 */
public class LoggerUtils {

	private static final String Logger_FLAG_DEBUG = " ----- ";
	private static final String Logger_FLAG_WARN = " ^^^^^ ";
	private static final String Logger_FLAG_INFO = " ***** ";
	private static final String Logger_FLAG_ERROR = " ##### ";
	private static final String Logger_FLAG_FATAL = " $$$$$ ";
	private static final String Logger_FLAG_HIGHLIGHT = " ********** ";

	public static void LoggerCurrentMethod(Logger logger, Throwable t) {
		StackTraceElement ste = t.getStackTrace()[0];
		logger.info(ste.getMethodName());
	}

	public static void debugCurrentMethod(Logger logger, Throwable t) {
		StackTraceElement ste = t.getStackTrace()[0];
		logger.debug(ste.getMethodName());
	}

	public static void debug(Logger logger, String message) {
		logger.debug(Logger_FLAG_DEBUG + message + Logger_FLAG_DEBUG);
	}

	public static void warn(Logger logger, String message) {
		logger.warn(Logger_FLAG_WARN + message + Logger_FLAG_WARN);
	}

	public static void info(Logger logger, String message) {
		logger.info(Logger_FLAG_INFO + message + Logger_FLAG_INFO);
	}

	public static void error(Logger Logger, String message) {
		Logger.error(Logger_FLAG_ERROR + message + Logger_FLAG_ERROR);
	}

	public static void fatal(Logger Logger, String message) {
		Logger.fatal(Logger_FLAG_FATAL + message + Logger_FLAG_FATAL);
	}

	public static void highlight(Logger Logger, String message) {
		Logger.info(Logger_FLAG_HIGHLIGHT + message + Logger_FLAG_HIGHLIGHT);
	}
	
}
