/**
 * 
 */
package com.wsheng.aggregator.solr.vo;

/**
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrFileImageSearchVO extends SolrSearchVO {

	/**
	 * Currently, only support searching on file/image name, description and comments
	 */
	private String text;
	
	/**
	 * The user id of uploading the file/image
	 */
	private String uploader;
	
	/**
	 * The circle id of the file/image
	 */
	private String cid;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
}
