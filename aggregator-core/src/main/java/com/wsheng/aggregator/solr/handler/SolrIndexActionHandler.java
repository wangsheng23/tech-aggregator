/**
 * 
 */
package com.wsheng.aggregator.solr.handler;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.wsheng.aggregator.dao.ISolrIndexDao;
import com.wsheng.aggregator.solr.bean.SolrIndexNet;
import com.wsheng.aggregator.solr.bean.SolrIndexResult;
import com.wsheng.aggregator.solr.bean.SolrIndexTable;
import com.wsheng.aggregator.solr.bean.SolrIndexNet.SolrIndexStatus;



/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public abstract class SolrIndexActionHandler<T extends Serializable> {
	
	protected static Logger logger = Logger.getLogger(SolrIndexActionHandler.class);
	
	private SolrIndexActionHandler<?> nextHandler;

	protected abstract SolrIndexTable getTarget();
	
	protected abstract SolrIndexResult response(SolrIndexNet indexNet);
	
	protected @Resource ISolrIndexDao solrIndexProxy;
	
	public final SolrIndexResult process(SolrIndexNet indexNet) {
		SolrIndexResult result = null;
		
		if (getTarget().name().equals(indexNet.getTid())) {
			result = this.response(indexNet);
		} else if (this.nextHandler != null) {
			result = this.nextHandler.response(indexNet);
		} else {
			// no available handlers
			logger.info("No Avaiable Handlers for this Solr Index Req: " + indexNet.getTid());
		}
		
		if (SolrIndexStatus.success.name().equals(result.getResult())) {
			// Update the Index status to success also
			indexNet.setStatus(SolrIndexStatus.success.name());
		} else {
			indexNet.setIndexTimes(indexNet.getIndexTimes() + 1);
			indexNet.setStatus(SolrIndexStatus.failed.name());
		}
		
		solrIndexProxy.updateIndexNet(indexNet);
		
		return result;
		
	}

	public SolrIndexActionHandler<?> getNextHandler() {
		return nextHandler;
	}

	public void setNextHandler(SolrIndexActionHandler<?> nextHandler) {
		this.nextHandler = nextHandler;
	}
	
	
}
