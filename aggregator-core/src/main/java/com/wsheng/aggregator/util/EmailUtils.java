/**
 * 
 */
package com.wsheng.aggregator.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.wsheng.aggregator.enums.EmailTargetEnum;



/**
 * It's used to send email notification by using commons email in APACHE organization
 * @author swang6
 *
 */
public class EmailUtils {
	
	private static Log logger = LogFactory.getLog(EmailUtils.class);
	
	public static void sendMail(EmailTargetEnum target, String title, 
			String contents, String receivers, String sender) throws EmailException {
		switch(target) {
		case DBMetrics_Check:
			sendDBMetricsMail(title, contents, receivers, sender);
			break;
		}
	}
	
	private static void sendDBMetricsMail(String title, String contents, 
			String receivers, String sender) throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setSSLOnConnect(false);
		String smtpHost = "atom.corp.ybei.com";
		// LoggerUtil.info(logger, "Current smtpHost is: " + smtpHost);
		
		email.setHostName(smtpHost);
		//email.setAuthentication(userName, password);
		for (String receiver : receivers.split(",")) {
			email.addTo(receiver); // separated by ,
		}
		email.setFrom(sender);
		email.setSubject(title);
		// String content = FreeMarkerUtils.getDBMetricsMailContent(contents);
		email.setMsg(contents);
		
		logger.info("Start to Send to receiver" + email.getToAddresses());
		email.send();
		
	}
}
