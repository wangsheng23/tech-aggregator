/**
 * 
 */
package com.wsheng.aggregator.solr.vo;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrMessageSearchVO extends SolrSearchVO {
	
	/**
	 * The user id of sending the message 
	 */
	private String sender;
	
	/**
	 * The circle id of the message
	 */
	private String cid;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}
