/**
 * 
 */
package com.wsheng.aggregator.solr.bean;

/**
 * IndexResult standards execution results of IndexManager
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrIndexResult {

	private String  result;
	
	private String message;
	
	public SolrIndexResult() {
		
	}

	public SolrIndexResult(String result, String message) {
		this.result = result;
		this.message = message;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
