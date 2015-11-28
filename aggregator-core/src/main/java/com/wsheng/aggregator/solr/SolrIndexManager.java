/**
 * 
 */
package com.wsheng.aggregator.solr;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


import com.wsheng.aggregator.dao.ISolrIndexDao;
import com.wsheng.aggregator.solr.bean.SolrIndexNet;
import com.wsheng.aggregator.solr.bean.SolrIndexNet.SolrIndexPriority;
import com.wsheng.aggregator.solr.bean.SolrIndexNet.SolrIndexStatus;
import com.wsheng.aggregator.solr.bean.SolrIndexResult;
import com.wsheng.aggregator.solr.bean.SolrIndexTask;
import com.wsheng.aggregator.util.ExceptionUtils;
import com.wsheng.aggregator.util.LoggerUtils;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
@Component("solrIndexManager")
public class SolrIndexManager {
	
	private static Logger logger = Logger.getLogger(SolrIndexManager.class);
	
	private static boolean started = false;
	
	@Resource ISolrIndexDao solrIndexProxy;
    
	/** Start the 3 timers after the searcher started  30 seconds*/
	private static final int START_TIME = 30000;
	
	private static ExecutorService executorServicePool = Executors.newFixedThreadPool(10);
	
	private static CompletionService<SolrIndexResult> indexCompletionService = new ExecutorCompletionService<SolrIndexResult>(executorServicePool);
			
	public void startIndex() {
		
		if (!started) {
			LoggerUtils.info(logger, "Starting the P1 Index Timer");
			new P1Timer().scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() { 
					// 1. query the expected index items with priority p1
					long startTime = System.currentTimeMillis();
					LoggerUtils.info(logger, "Starting to index -- P1");
					List<SolrIndexNet> indexNets = solrIndexProxy.getRequiredIndexNets(SolrIndexPriority.Urgent.code());
					
					try {
						LoggerUtils.info(logger, "Current having [ " + indexNets.size() + " ] P1 items to index");
						
						SolrIndexResult p1Result = executeTask(indexNets, indexCompletionService);
						LoggerUtils.info(logger, "Index Result: " + p1Result.getResult() + "  " + p1Result.getMessage());
						
						long endTime = System.currentTimeMillis();
						LoggerUtils.info(logger, "End to index -- P1" + " Cost " + (endTime - startTime)/60000 + " min to complete");
						
					} catch (InterruptedException | ExecutionException e) {
						LoggerUtils.error(logger, "Index Result: "   + ExceptionUtils.getStackTraceMsg(e));
					}
					
				}
			}, START_TIME, P1Timer.P1_INDEX_FREQUENCY);
			
		/*	LoggerUtils.info(logger, "Starting the P2 Index Timer");
			new P2Timer().scheduleAtFixedRate(new TimerTask() {
				
				@Override
				public void run() {
					// 2. query the expected index items with priority p2
					LoggerUtils.info(logger, "Staring to index -- P2");
					long startTime = System.currentTimeMillis();
					List<IndexNet> indexNets = indexProxy.getRequiredIndexNets(Priority.Normal.code());
					
					try {
						LoggerUtils.info(logger, "Current having " + indexNets.size() + " P2 items to index");
						
						IndexResult p2Result = executeTask(indexNets, indexCompletionService);
						LoggerUtils.info(logger, "Index Result: " + p2Result.getResult() + "  " + p2Result.getMessage());
						
						long endTime = System.currentTimeMillis();
						LoggerUtils.info(logger, "End to index -- P2" + " Cost " + (endTime - startTime)/60000 + " min to complete");
						
					} catch (InterruptedException | ExecutionException e) {
						LoggerUtils.error(logger, "Index Result: "   + ExceptionUtils.getStackTraceMsg(e));
					}				
					
				}
			}, START_TIME, P2Timer.P2_INDEX_FREQUENCY);
			
			LoggerUtils.info(logger, "Starting the P3 Index Timer");
			new P3Timer().scheduleAtFixedRate(new TimerTask() {
					
				@Override
				public void run() {
					// 3. query the expected index items with priority p3
					LoggerUtils.info(logger, "Staring to index -- P3");
					long startTime = System.currentTimeMillis();
					List<IndexNet> indexNets = indexProxy.getRequiredIndexNets(Priority.Low.code());
					
					try {
						LoggerUtils.info(logger, "Current having " + indexNets.size() + " P3 items to index");
						
						IndexResult p3Result = executeTask(indexNets, indexCompletionService);
						LoggerUtils.info(logger, "Index Result: " + p3Result.getResult() + "  " + p3Result.getMessage());
						
						long endTime = System.currentTimeMillis();
						LoggerUtils.info(logger, "End to index -- P3" + " Cost " + (endTime - startTime)/60000 + " min to complete");
						
					} catch (InterruptedException | ExecutionException e) {
						LoggerUtils.error(logger, "Index Result: "   + ExceptionUtils.getStackTraceMsg(e));
					}						
				}
			}, START_TIME, P3Timer.P3_INDEX_FREQUENCY);
			*/
			
			started = true;
		}
		
		
	}
	
	/*
	 * We just separate the task base on index number simply
	 */
	private SolrIndexResult executeTask(List<SolrIndexNet> indexNets, CompletionService<SolrIndexResult> completionService) throws InterruptedException, ExecutionException {
		SolrIndexResult result = new SolrIndexResult();
		SolrIndexStatus finalStatus = SolrIndexStatus.success;
		StringBuffer messageBuffer = new StringBuffer();
		String finalMessage;
		
		if (indexNets != null && indexNets.size() > 0) {
			int pendingCount = indexNets.size();
			if (pendingCount > 0 ) {
				List<Future<SolrIndexResult>> futures;
				
				int threadNum = 1;
				
				if (pendingCount <= 500) { // one thread
					threadNum = 1;
				} else if (pendingCount <= 1000) {// 3 threads
					threadNum = 3;
				} else if (pendingCount <= 5000) {// 5 threads
					threadNum = 5;
				} else {// 10 threads
					threadNum = 10;
				}
				
				futures = splitTask(indexNets, completionService, threadNum);
				
				for (int i = 0; i < threadNum; i++) {
					SolrIndexResult currentResult = futures.get(i).get();
					if (SolrIndexStatus.failed.name().equals(currentResult.getResult())) {
						finalStatus = SolrIndexStatus.failed;
						messageBuffer.append(currentResult.getMessage());
					}
				}
			}
		}
		
		finalMessage = StringUtils.isNotEmpty(messageBuffer.toString()) ? messageBuffer.toString() : "No Pending Index found currently";
		result = new SolrIndexResult(finalStatus.name(), finalMessage);
		logger.info("Get Result: " + result.getMessage());

		return result;
	}
	
	
	private List<Future<SolrIndexResult>> splitTask(List<SolrIndexNet> indexNets, CompletionService<SolrIndexResult> completionService, int threadNum) throws InterruptedException {
		for (int i = 0; i < threadNum; i++) {
			List<SolrIndexNet> subIndexNets = indexNets.subList(indexNets.size()*i/threadNum, indexNets.size()*(i+1)/threadNum);
			
			// ======================== no new in Spring, or the later Objects related will be injected with null. ====================================
			// IndexTask task = new IndexTask(indexProxy, subIndexNets);
			SolrIndexTask indexTask = SearcherBootStrap.ctx.getBean(SolrIndexTask.class);
			indexTask.setIndexNets(subIndexNets);
			// System.out.println(" hashcode: " + indexTask.hashCode());
			
			logger.info("Current Pending index size: " + " [ " +subIndexNets.size() + " ] ");
			completionService.submit(indexTask);
		}
		
		List<Future<SolrIndexResult>> resultsFutures = new ArrayList<>();
		
		for (int i = 0; i < threadNum; i++) {
			resultsFutures.add(completionService.take());
		}
		
		LoggerUtils.info(logger, "The total entity handled this time: " + resultsFutures.size());
		
		return resultsFutures;
		 
	}
	
	
	public class P1Timer extends Timer {
		// every one minute to delta  p1(priority=1) level index
		private static final int P1_INDEX_FREQUENCY = 60000;
	}
	
	
/*	public class P2Timer extends Timer {
		// every 3 minutes to delta p2(priority=2) level index
		private static final int P2_INDEX_FREQUENCY = 180000;
	}
	
	
	public class P3Timer extends Timer {
		// every 5 minutes to delta p3(priority=3) level index
		private static final int P3_INDEX_FREQUENCY	= 300000;
	}
*/

//	public IIndexDao getIndexProxy() {
//		return indexProxy;
//	}
//
//
//	public void setIndexProxy(IIndexDao indexProxy) {
//		this.indexProxy = indexProxy;
//	}

}
