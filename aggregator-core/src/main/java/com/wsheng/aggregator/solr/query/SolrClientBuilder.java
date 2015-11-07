/**
 * 
 */
package com.wsheng.aggregator.solr.query;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import com.wsheng.aggregator.solr.bean.SolrCore;
import com.wsheng.aggregator.util.ConfigurationUtils;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrClientBuilder { 
	
	private static SolrClient messageSearchClient;
	private static SolrClient fileImageSearchClient;
	
	private SolrClientBuilder() {}
	
	public static synchronized SolrClient getSolrClinetInstance(SolrCore core) {
		SolrClient targetSolrClient = null;
		switch (core) {
		case FileImage:
			if (fileImageSearchClient != null) {
				targetSolrClient = fileImageSearchClient;
			}
			break;

		case Message:
			if (messageSearchClient != null) {
				targetSolrClient = messageSearchClient;
			}
			break;
		default:
			break;
		}
		
		if (targetSolrClient == null) {
			targetSolrClient = new HttpSolrClient(getSolrActionUrl(core));
		}
		
		return targetSolrClient;
	}
	
	
	public static synchronized String getSolrActionUrl(SolrCore core) {
		String actionUrl = ConfigurationUtils.getSolrInstance() + core.name().toLowerCase();
		return actionUrl;
	}
	
	

}
