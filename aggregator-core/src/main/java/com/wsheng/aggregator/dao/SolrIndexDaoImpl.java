/**
 * 
 */
package com.wsheng.aggregator.dao;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.wsheng.aggregator.solr.bean.SolrIndexNet;
import com.wsheng.aggregator.solr.bean.SolrIndexNet.SolrIndexNetsKey;
import com.wsheng.aggregator.solr.bean.SolrIndexNet.SolrIndexStatus;
import com.wsheng.aggregator.util.DateUtils;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
@Repository("solrIndexProxy")
@Transactional(rollbackOn=Exception.class)
public class SolrIndexDaoImpl extends HBaseDaoImpl<SolrIndexNet, SolrIndexNetsKey> implements ISolrIndexDao {

	@Override
	public void expectIndex(SolrIndexNet index) {
		this._saveOrUpdate(index);
	}
	
	@Override
	public void expectIndexs(Collection<SolrIndexNet> indexs) {
		for (SolrIndexNet index : indexs) {
			expectIndex(index);
		}
	}

	@Override
	public List<SolrIndexNet> getIndexNets() {
		return this._listAll();
	}

	@Override
	public SolrIndexNet getIndexNet(String bid, String tid) {
		SolrIndexNetsKey key = new SolrIndexNetsKey();
		key.setBid(bid);
		key.setTid(tid);
		
		return this._get(key);
	}

	@Override
	public List<SolrIndexNet> getPendingIndexNets(int priority) {
		if (priority == -1) {
			// query all of the pending Index Nets
			return this._filter("from IndexNet where status = 'pending'", "");
		} else {
			return this._filter("from IndexNet where status = 'pending'  and priority = ?", priority);
		}
	}

	@Override
	public List<SolrIndexNet> getRequiredIndexNets(int priority) {
		if (priority == -1) {
			// query all of the required Index Nets
			return this._filter("from IndexNet where status = 'pending' or (status = 'failed' and indexTimes <= 3)", "");
		} else { // Hibernate knows and has precedence over or, so hibernate will remove the bracket, for us, bracket just used for tell us there are two parts.
			// return this._filter("from IndexNet where status = 'pending' or (status = 'failed' and indexTimes <= 3) and priority = ? ", priority);
			return this._filter("from IndexNet where (status = 'pending' or (status = 'failed' and indexTimes <= 3)) and priority = ? ", priority);
		}
		
	}

	@Override
	public Timestamp getLastSyncTime(String tid) {// select * from u_table where rowid=(select max(rowid) from u_table) --- Hql in subquery
		SolrIndexNet net =  this._get("from IndexNet iNet where iNet.lastUpdate = (select max(iNet.lastUpdate) from iNet where iNet.tid = ? order by iNet.lastUpdate desc)", tid);
		return net == null ? null : net.getLastUpdate();
	}

	@Override
	public void deleteDeprecatedIndexNets() {
		/*
		 * for the last parameter, we can't use "curdate(), interval 6 month", because this is a String type, will be 
		 * set to a normal parameter, not a timestamp parameter:
		 * query.setParameter(i, parameters[i]);
		 * 
		 * the expected should be
		 * 
		 * query.setTimestamp(i, (Date) parameters[i])
		 * 
		 */
//		this._delete(SolrIndexNet.class, "(status = ? or (status = ? and indexTimes >= ?)) and lastUpdate < ?", 
//				Status.success.name(), Status.failed.name(), 3, "curdate(), interval 6 month");
		
		this._delete(SolrIndexNet.class, "(status = ? or (status = ? and indexTimes >= ?)) and lastUpdate < ?", 
				SolrIndexStatus.success.name(), SolrIndexStatus.failed.name(), 3, DateUtils.getPreviousSixMonth());
		
	}

	@Override
	public void updateIndexNet(SolrIndexNet indexNet) {
		this._update(indexNet);
	}

	@Override
	public void deleteIndexNet(SolrIndexNet indexNet) {
		SolrIndexNetsKey key = new SolrIndexNetsKey(indexNet.getBid(), indexNet.getTid());
		this._delete(key);
		
	}

}
