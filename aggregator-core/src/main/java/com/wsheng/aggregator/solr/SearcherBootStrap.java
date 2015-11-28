/**
 * 
 */
package com.wsheng.aggregator.solr;


import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.wsheng.aggregator.util.ExceptionUtils;
import com.wsheng.aggregator.util.LoggerUtils;



/**
 * How to start and index
 * 
 * 1. Using command "mvn clean package -DskipTests"  or "mvn assembly:assembly -DskipTests" to build the runnable jar
 * 
 * 2. Using command "jar -jar searcher.jar" to start searcher
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
@Component
public class SearcherBootStrap  {

	private static Logger logger = Logger.getLogger(SearcherBootStrap.class);
	public static AbstractXmlApplicationContext ctx;
	
	public static void main(String[] args) {
		startManagers();
	}
	
	public static void startManagers () {
		try {
			ctx = new ClassPathXmlApplicationContext(
					new String[]{"classpath:activity-common.xml", "classpath:activity-hibernate.xml", "classpath:activity-mybatis.xml"});
			SolrIndexManager indexManager = ctx.getBean(SolrIndexManager.class);
		//	SyncManager syncManager = ctx.getBean(SyncManager.class);
		//	WeightManager weightManager = ctx.getBean(WeightManager.class);
			
		//	syncManager.startSync();
			indexManager.startIndex();
		//	weightManager.startCalculate();
			
		} catch (Exception e) {
			LoggerUtils.error(logger, "Unable to start the system. Reason: " + ExceptionUtils.getStackTraceMsg(e));
			LoggerUtils.info(logger, "Terminating the process...");
			System.exit(1);
		}
	}
	
}
