/**
 * 
 */
package com.wsheng.aggregator.solr.vo;

import com.google.gson.annotations.Expose;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrSearchVO {
	
	/**
	 * @Expose is required, or can't received the parameter from rest call
	 * Default value is and
	 */
	@Expose
	private String connector = "and";
	
	@Expose
	private String startTime;
	
	@Expose
	private String endTime;
	
	@Expose
	private String time;

	public String getConnector() {
		return connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
