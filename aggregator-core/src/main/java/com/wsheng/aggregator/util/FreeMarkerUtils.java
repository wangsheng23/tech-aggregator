package com.wsheng.aggregator.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

/**
 * @author Josh Wang(Sheng)
 *
 */
public class FreeMarkerUtils {
	
	private static Configuration cfg = new Configuration(new Version("2.3"));
	
	private static Logger logger = Logger.getLogger(FreeMarkerUtils.class);
	
	public static String getDBMetricsMailContent(Map<String, Object> contents) {
		
		// set the location of the template files
		try {
			// this valid in unit test, but not valid in tomcat
//			String projectPath = System.getProperty("user.dir");
//			String templatePath = projectPath + "\\src\\main\\resources\\template";
			// LoggerUtil.info(logger, "Current email template is: " + templatePath);
			
			String classPath = FreeMarkerUtils.class.getClassLoader().getResource("/").getPath();
			String templatePath = classPath + "template/";
			
			/*  This one is valid both in tomcat and junit test */
//			cfg = new Configuration(new Version("2.3"));
//			URL websrc = FreeMarkerUtils.class.getClassLoader().getResource("/");
//			String classPathPrefix = "com/ebay/spi/templates/";
//			if (websrc == null) {
//				templatePath = System.getProperty("user.dir") + "/src/" + classPathPrefix;
//			} else {
//				templatePath = websrc.getPath() + classPathPrefix;
//			}
			
			
			cfg.setDirectoryForTemplateLoading(new File(templatePath));
			
			
			
			
			
			
			// Create template Object
			Template template = cfg.getTemplate("mail_db_metrics.ftl");
			
			// Generate String
			return getContent(template, contents);
		} catch (IOException e) {
			
			LoggerUtils.error(logger, e.getMessage());
			return "Get Mail Template Exception !!!		" + e.getMessage();
		} catch (Exception e) {
			LoggerUtils.error(logger, e.getMessage());
			return "Generate Mail Content Exception !!!		" + e.getMessage();
		}
	}
	
	private static String getContent(Template template, Map<String, Object> contents) throws Exception {
		StringWriter out = new StringWriter();
		template.process(contents, out);
		return out.toString();
	}
	
	
	
}
