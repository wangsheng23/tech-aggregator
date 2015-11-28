/**
 * 
 */
package com.wsheng.aggregator.solr;

import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.wsheng.aggregator.bean.QueryParam;
import com.wsheng.aggregator.dao.IMBaseDao;
import com.wsheng.aggregator.dao.ISolrIndexDao;
import com.wsheng.aggregator.db.DBConstants;
import com.wsheng.aggregator.solr.bean.SolrIndexTable;
import com.wsheng.aggregator.util.LoggerUtils;



/**
 * IndexNetSyncManager used to sync data to index nets, index nets will be operated by <code>SolrIndexManager</code>
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
@Component("syncManager")
public class IndexNetSyncManager {
	private static Logger 		logger 			= Logger.getLogger(IndexNetSyncManager.class);
	
	private static boolean 		started 		= false;
	
	/** Start the timer after the searcher started 10 seconds*/
	private static final int 	START_TIME 		= 10000;
	
	/** every 1 minute*/
	private static final int 	SYNC_FREQUENCY 	= 60000;
	
	@Resource
	private IMBaseDao mBaseDao;
	
	@Resource
	ISolrIndexDao solrIndexProxy;
	
	public void startSync() {
		if (!started) {
			LoggerUtils.info(logger, "Starting Sync Manager");
			
			new Timer().scheduleAtFixedRate(new TimerTask() {
				
				@Override
				public void run() {
					// 1. sync circle
					// 1.1 Get last sync time from index_nets,in order to guarntee idempotent, here we will get the last hours data before the lasy sync time
					Timestamp circleTimestamp = getLastSyncTime(SolrIndexTable.circle);
					String circleStartTime = circleTimestamp == null ? "" : circleTimestamp.toString();
					
					// 1.2 Sync Circle to index net
					// syncCircle(circleStartTime, "");
					
					// 2. sync space message
					
					// 3. sync message
					
				}
			}, START_TIME, SYNC_FREQUENCY);
			
			started = true;
		}
	}
	
	// 如果违反主键唯一约束异常，而且状态不是Pending，则update pending， 时间， index time. priority
	/*public List<SpaceMessageVO> syncSpaceMessage(String startTime, String endTime) {
		// if the record exist in indexNets, it should be a updated record.
		
		// 1. query the specified Space message of the give time range
		QueryParam param = initQueryParam(startTime, endTime);
		
		List<SpaceMessageVO> messages = mBaseDao.listByQuery(DBConstants.INDEX_QUERY_SPACE_MESSAGE_BY_TIMERANGE, param);
		if (messages != null && !messages.isEmpty()) {
			// 2. Insert these new records to indexNest for the coming index(This should be taken by IndexManager)
			Collection<IndexNet> indexNets = new ArrayList<>();
			for (SpaceMessageVO message : messages) {
					IndexNet indexNet = new IndexNet(String.valueOf(message.getMid()), Table.space_message.name(), Status.pending.name(), 
							1, Priority.Urgent.code(), new Timestamp(System.currentTimeMillis()), "From Sync Manager-Sync Space Message");
					indexNets.add(indexNet);
				}
			indexProxy.expectIndexs(indexNets);
			
		}
		
		return messages;
	}*/
	
	
	/*public List<Circle> syncCircle(String startTime, String endTime) {
		// if the record exist in indexNets, it should be a updated record.
		
		// 1. query the specified Circle of the given time range
		QueryParam param = initQueryParam(startTime, endTime);
		
		List<Circle> circles = mBaseDao.listByQuery(DBConstants.INDEX_QUERY_CIRCLE_BY_TIMERANGE, param);
		if (circles != null && !circles.isEmpty()) {
			// 2. Insert these new records to indexNets for the coming index(This should be taken by IndexManager)
			Collection<SolrIndexNet> indexNets = new ArrayList<>();
			for (Circle circle : circles) {
				SolrIndexNet indexNet = new SolrIndexNet(String.valueOf(circle.getCid()), SolrIndexTable.circle.name(), SolrIndexStatus.pending.name(), 
						1, SolrIndexPriority.Urgent.code(), new Timestamp(System.currentTimeMillis()), "From Sync Manager-Sync Circle");
				
				// indexProxy._merge(indexNet);
				indexNets.add(indexNet);
			}
			
			solrIndexProxy.expectIndexs(indexNets);
			
		}
		
		return circles;
	}*/
	
	private QueryParam initQueryParam(String startTime, String endTime) {
		Timestamp startTimestamp = StringUtils.isEmpty(startTime)? Timestamp.valueOf(DBConstants.PARAM_DEFAULT_START_TIME) : Timestamp.valueOf(startTime);
		Timestamp endTimestamp = StringUtils.isEmpty(endTime)? new Timestamp(System.currentTimeMillis()) : Timestamp.valueOf(endTime);

		QueryParam param = new QueryParam();
		param.addParam(DBConstants.PARAM_START_UPDATE_TIME, startTimestamp);
		param.addParam(DBConstants.PARAM_END_UPDATE_TIME, endTimestamp);
		
		return param;
	}

	private Timestamp getLastSyncTime(SolrIndexTable table) {
		return solrIndexProxy.getLastSyncTime(table.name());
	}
}
