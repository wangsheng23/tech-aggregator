/**
 * 
 */
package com.wsheng.aggregator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.wsheng.aggregator.constants.MailConstants;
import com.wsheng.aggregator.enums.EmailTargetEnum;
import com.wsheng.aggregator.util.EmailUtils;
import com.wsheng.aggregator.util.FreeMarkerUtils;

/**
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 *
 */
public class TestEmailUtil {

	@Test
	public void send() throws Exception {
		Map<String, Object> contents = new HashMap<String, Object>();
		contents.put(MailConstants.MESSAGE, "There are some errors!!!");
		contents.put(MailConstants.GATHERED_VALUE, 15);
		double metricValue = 13.5;
		double bufferPercent = 0.95;
		double thresholdValue = metricValue + bufferPercent;
		contents.put(MailConstants.THRESHOLD_TYPE, "sum");
		contents.put(MailConstants.METRIC_VALUE, metricValue);
		contents.put(MailConstants.METRIC_BUFFER_OR_PERCENT, bufferPercent);
		
		contents.put(MailConstants.THRESHOLD_VALUE, thresholdValue);
		contents.put(MailConstants.HANDLED_VALUE, 28);
		contents.put(MailConstants.SOLUTION, "Reduce or recalculate the value");
		contents.put(MailConstants.CONDITION, "Greater Than");
		
		List<String> signatures = new ArrayList<String>();
		signatures.add("This mail is sent from db in a box tool");
		signatures.add("it's still in test");
		signatures.add("access: http://dbinabox.vip.ybei.com/ for the tool");
		
		contents.put(MailConstants.SIGNATURES, signatures);
		contents.put(MailConstants.HAS_SIGNATURE, true);
		// contents.put(MailConstants.HAS_SIGNATURE, false);
		
		String title = "Test-DBMetrics";
		// String receivers = "josh_wang23@hotmail.com^_^wangsheng23@126.com";
		String receivers = "josh_wang23@hotmail.com";
		String sender = "josh_wang23@hotmail.com";
		
		String contentStr = FreeMarkerUtils.getDBMetricsMailContent(contents);
//		int i = 0;
//		while(i <= 50) {
			EmailUtils.sendMail(EmailTargetEnum.DBMetrics_Check, title, contentStr, receivers, sender);
	//		Thread.sleep(100000);
//			i++;
//		}
		
	}
}
