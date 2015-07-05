/**
 * 
 */
package com.wsheng.aggregator.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * <code>ConfigurationUtils</code> 
 * used to get the properties from settings.properties, 
 * And settings.properties comes from local/pre-prod/qa/prod.properties
 * 
 * @author Josh Wang(Sheng)
 * @email  josh_wang23@hotmail.com
 */
public class ConfigurationUtils {

	private static Logger logger = Logger.getLogger(ConfigurationUtils.class);

	private static Properties props = new Properties();
	static {

		try {
			props.load(ConfigurationUtils.class.getClassLoader().getResourceAsStream("settings.properties"));
			LoggerUtils.info(logger, "Load Settings.Properties Successfully");

		} catch (IOException e) {
			LoggerUtils.info(logger, e.getMessage());
		}
	}
	
	
	public static String getCMSEndpoint() {
		return props.getProperty("CMSDB_ENDPOINT");
	}

	
	public static String getCMSRepo() {
		return props.getProperty("CMSDB_REPO");
	}
	
	public static String getCMSBranch() {
		return props.getProperty("CMSDB_BRANCH");
	}
	
	/**
	 * Get CMS Client prefix URL
	 * @return
	 */
	public static String getCMSPackage() {
		return props.getProperty("CMSDB_PACKAGE");
	}
	
}
