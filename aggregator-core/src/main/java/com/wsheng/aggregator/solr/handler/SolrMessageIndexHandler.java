/**
 * 
 */
package com.wsheng.aggregator.solr.handler;

import org.springframework.stereotype.Component;

import com.wsheng.aggregator.solr.bean.SolrIndexNet;
import com.wsheng.aggregator.solr.bean.SolrIndexResult;
import com.wsheng.aggregator.solr.bean.SolrIndexTable;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
@Component("messageIndexHandler")
public class SolrMessageIndexHandler extends SolrIndexActionHandler<SolrIndexNet> {

	@Override
	protected SolrIndexTable getTarget() {
		return SolrIndexTable.message;
	}

	@Override
	protected SolrIndexResult response(SolrIndexNet indexNet) {
		// TODO Auto-generated method stub
		return null;
	}


}
