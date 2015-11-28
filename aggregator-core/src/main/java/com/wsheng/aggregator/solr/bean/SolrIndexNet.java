/**
 * 
 */
package com.wsheng.aggregator.solr.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**<p>SolrIndexNet</p> persist bean standards for the Index pending to operate
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
@Entity
@Table(name="index_nets")
@IdClass(SolrIndexNet.SolrIndexNetsKey.class) // IndexNets Composite id
public class SolrIndexNet implements Serializable {

	private static final long serialVersionUID = -6673744631725001106L;

	/**
	 * The business id, could be user id, event id, circle id etc.
	 */
	@Id
	private String bid; 
	
	/**
	 * The identify of the table name, it should be consistency with bid,
	 * typically, its the table name
	 * 
	 */
	@Id
	private String tid;
	
	/**
	 * The status for the row to index, 
	 * the value could be 'pending', 'success', 'failed'
	 */
	@Column(name="O_STATUS")
	private String status = SolrIndexStatus.pending.name(); // default status is pending
	
	/**
	 * The indexed operating times to current row
	 */
	@Column(name="O_TIMES")
	private int indexTimes = 1; // default the first time
	
	/**
	 * The priority for current row to be indexed.
	 */
	@Column
	private int priority = SolrIndexPriority.Urgent.code; // default is the P1 Priority
	
	/**
	 * the row latest updated time
	 */
	@Column(name="LAST_UPDATE_TIME")
	private Timestamp lastUpdate;
	
	/**
	 * Comments for current operation
	 */
	@Column
	private String comments;
	
	public enum SolrIndexStatus {
		pending,success,failed;
	}
	
	public enum SolrIndexPriority {
		
		Critical(0), // P0 : call Solr API to index immediately
		Urgent(1), // P1: may be indexed after 1 minute
		Normal(2), // P2: may be indexed after 3 minutes
		Low(3); // P3: may be indexed after 5 minutes
		
		private int code;
		
		private SolrIndexPriority(int code) {
			this.code = code;
		}

		public int code() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}
		
		
	}
	
	public SolrIndexNet() {
		
	}
	
	public SolrIndexNet(String bid, String tid) {
		this.bid = bid;
		this.tid = tid;
	}
	
	public SolrIndexNet(String bid, String tid, String status, int indexTimes,
			int priority, Timestamp lastUpdate, String comments) {
		super();
		this.bid = bid;
		this.tid = tid;
		this.status = status;
		this.indexTimes = indexTimes;
		this.priority = priority;
		this.lastUpdate = lastUpdate;
		this.comments = comments;
	}

	/**
	 * The inner class must be static for 2 reasons
	 * 
	 * 1) if not static will throw exception like:
	 * org.hibernate.InstantiationException: No default constructor for entity:  : 
	 * 	com....SolrIndexNets$SolrIndexNetsKey
	 * 
	 * 2£©For a inner class, if there is no need to access the outer class, we can define it to static
	 * 
	 * @author Josh Wang(Sheng)
	 *
	 * @email  josh_wang23@hotmail.com
	 */
	public static class SolrIndexNetsKey implements Serializable {

		private static final long serialVersionUID = -6459311384978319986L;
		
		private String bid;
		private String tid;
		
		public SolrIndexNetsKey() {
			
		}
		
		public SolrIndexNetsKey(String bid, String tid) {
			this.bid = bid;
			this.tid= tid;
		}
		
		public String getBid() {
			return bid;
		}
		public void setBid(String bid) {
			this.bid = bid;
		}
		public String getTid() {
			return tid;
		}
		public void setTid(String tid) {
			this.tid = tid;
		}
		@Override
		public int hashCode() {
			return this.bid.hashCode() + this.tid.hashCode();
		}
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof SolrIndexNetsKey) {
				SolrIndexNetsKey key = (SolrIndexNetsKey)obj;
				if (this.tid == key.getTid() && this.bid.equals(key.getBid())) {
					return true;
				}
				
			}
			
			return false;
		}
		
		
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIndexTimes() {
		return indexTimes;
	}

	public void setIndexTimes(int indexTimes) {
		this.indexTimes = indexTimes;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
}
