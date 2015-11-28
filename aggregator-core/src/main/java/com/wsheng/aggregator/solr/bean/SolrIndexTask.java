/**
 * 
 */
package com.wsheng.aggregator.solr.bean;

import java.util.List;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wsheng.aggregator.solr.bean.SolrIndexNet.SolrIndexStatus;
import com.wsheng.aggregator.solr.handler.SolrCircleIndexHandler;
import com.wsheng.aggregator.solr.handler.SolrMessageIndexHandler;

/**
 * SolrIndexTask is an asynchronized task managed by <p>SolrIndexManager</p>.
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
@Component
@Scope("prototype") // required, Spring return single instance default
public class SolrIndexTask implements Callable<SolrIndexResult> {
	
	private 	List<SolrIndexNet> 			indexNets;
	
	@Resource
	private  	SolrMessageIndexHandler 	messageIndexHandler;
	
	@Resource 	
	private 	SolrCircleIndexHandler 		circleIndexHandler;
	
	// initialize all of the Handlers here
	
	private static boolean init;
	
	
	@Override
	public SolrIndexResult call() throws Exception {
		
		if (!init) {
			init();
			init = true;
		} 
		
		SolrIndexResult result = new SolrIndexResult();
		try {
			for (SolrIndexNet indexNet : indexNets) {
				// if its not the messageIndexHandler's dish, will give it to next handler.
				messageIndexHandler.process(indexNet); 
			}
			
			result.setResult(SolrIndexStatus.success.name());
			result.setMessage("Current task for index success to handle " + indexNets.size() + " index items");
		} catch (Exception e) {
			result.setResult(SolrIndexStatus.failed.name());
			result.setMessage("Current task for index failed to handle " + indexNets.size() + " index items");

		}
		
		return result;
	}
	
	private void init() {
		messageIndexHandler.setNextHandler(circleIndexHandler);
	//	circleIndexHandler.setNextHandler(messageIndexHandler);
	}

	public List<SolrIndexNet> getIndexNets() {
		return indexNets;
	}

	public void setIndexNets(List<SolrIndexNet> indexNets) {
		this.indexNets = indexNets;
	}
	

}
