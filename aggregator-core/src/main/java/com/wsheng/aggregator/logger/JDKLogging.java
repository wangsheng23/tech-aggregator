/**
 * 
 */
package com.wsheng.aggregator.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


/**
 * 默认使用的logging.properties是位于
 * jdk\jre\lib\logging.properties
 * 
 * 当然也可以使用自定义log.properties.
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class JDKLogging {

	private static Logger logger = Logger.getLogger("sreappe2e");

	public static void test() {
		
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("C:/logs/appe2e.log");
			fileHandler.setLevel(Level.INFO);
			fileHandler.setFormatter(new Formatter() { // the default format is xml.
				@Override
				public String format(LogRecord record) {
					return record.getLevel() + ":" + record.getMessage()+"\n"; 
				}
			});
			
	        logger.addHandler(fileHandler); 
		} catch (SecurityException e) {
			logger.info(e.getMessage());
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
        
		logger.info(" *************** Start in SSO2FAAuthenticater ");
	}

	
}
