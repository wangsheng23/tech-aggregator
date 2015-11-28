/**
 * 
 */
package com.wsheng.aggregator.dao;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import com.wsheng.aggregator.solr.bean.SolrIndexNet;
import com.wsheng.aggregator.solr.bean.SolrIndexNet.SolrIndexNetsKey;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public interface ISolrIndexDao extends IHBaseDao<SolrIndexNet, SolrIndexNetsKey> {

	/**
	 * Expecting indexed later
	 * 
	 * @param index
	 */
	public void expectIndex(SolrIndexNet indexNet);
	
	/**
	 * Expecting index for the given index later
	 * @param indexs
	 */
	public void expectIndexs(Collection<SolrIndexNet> indexNets);
	
	/**
	 * Get all of the IndexNets
	 * 
	 * @return
	 */
	public List<SolrIndexNet> getIndexNets();
	
	/**
	 * Get the Index Net by primary key
	 * @param bid
	 * @param tid
	 * @return
	 */
	public SolrIndexNet getIndexNet(String bid, String tid);
	
	/**
	 * Get all of the Pending Index Nets (Index Nets with pending status)
	 * 
	 * @param priority - if priority equals -1 then return all of the PendingIndexNets
	 * 
	 * @return
	 */
	public List<SolrIndexNet> getPendingIndexNets(int priority);
	
	/**
	 * Get Required Index Nets (Index Nets with pending, failed status)
	 * 
	 * @param priority - if priority equals -1 then return all of the Required Index Nets.
	 * 
	 * @return
	 */
	public List<SolrIndexNet> getRequiredIndexNets(int priority);
	
	/**
	 * Get the last sync time for the given table
	 * @param tid
	 * @return
	 */
	public Timestamp getLastSyncTime(String tid);
	
	/**
	 * Delete the deprecated Index Nets.
	 * 1. Failed more than 3 times.
	 * 2. LastUpdateTime more than 6 months.
	 */
	public void deleteDeprecatedIndexNets();
	
	/**
	 * Update index nets
	 * @param index
	 */
	public void updateIndexNet(SolrIndexNet indexNet);
	
	/**
	 * Delete the index nets
	 * @param index
	 */
	public void deleteIndexNet(SolrIndexNet indexNet);
	
}
