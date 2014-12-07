
package com.wsheng.aggregator.spring.mvcrest.util;

import org.apache.commons.logging.Log;

/**
 * 
 * @author Josh Wang(Sheng)
 * @email swang6@email.com
 */
public class LoggerUtil {

    
  //  private static final String  LOG4J_PATH = "/log4j.properties";

    private static final String LOG_FLAG_DEBUG = " ----- ";
    private static final String LOG_FLAG_INFO = " ***** ";
    private static final String LOG_FLAG_ERROR = " ##### ";
   
//    static {
//        PropertyConfigurator.configure(LOG4J_PATH);
//    }
    

    public static void logCurrentMethod(Log logger, Throwable t) {
        StackTraceElement ste = t.getStackTrace()[0];
        logger.info(ste.getMethodName());
    }
    
    public static void debugCurrentMethod(Log logger, Throwable t) {
        StackTraceElement ste = t.getStackTrace()[0];
        logger.debug(ste.getMethodName());
    }
    
    public static void debug(Log logger, String message) {
        logger.debug(LOG_FLAG_DEBUG + message + LOG_FLAG_DEBUG);
    }
    
    public static void info(Log logger, String message) {
        logger.info(LOG_FLAG_INFO + message + LOG_FLAG_INFO);
    }
    
    public static void error(Log logger, String message) {
        logger.error(LOG_FLAG_ERROR + message + LOG_FLAG_ERROR);
    }
    

}
