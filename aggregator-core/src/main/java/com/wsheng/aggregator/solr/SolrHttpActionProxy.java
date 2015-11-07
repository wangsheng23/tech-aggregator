/**
 * 
 */
package com.wsheng.aggregator.solr;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wsheng.aggregator.constants.Constants;
import com.wsheng.aggregator.solr.bean.SolrCore;
import com.wsheng.aggregator.solr.query.SolrClientBuilder;
import com.wsheng.aggregator.util.ExceptionUtils;
import com.wsheng.aggregator.util.SolrUtils;

/**
 * SolrHttpActionService Used to execute these actions requested by http request,
 * such as full index, delta index, show the execution status, abort the ongoing action etc.
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
@Service("solrHttpActionProxy")
public class SolrHttpActionProxy {
	
	private static Logger logger = Logger.getLogger(SolrHttpActionProxy.class);

	private  String CONFIGURATION_URL;
	
	private  String FULL_IMPORT_URL_DIANDI;
	
	private  String DELTA_IMPORT_URL_ITEM;

	private  String RELOAD_CONFIG;
	
	private  String EXECUTION_STATUS;
	
	private  String ABORT;
	
	public SolrHttpActionProxy() {
		
	}
	
	public SolrHttpActionProxy(SolrCore core) {
		String solrActionUrl = SolrClientBuilder.getSolrActionUrl(core);
		CONFIGURATION_URL = solrActionUrl + Constants.SEPARATOR_FORWARD_SLASH + "dataimport";
		
		FULL_IMPORT_URL_DIANDI = CONFIGURATION_URL + Constants.SEPARATOR_QUESTION_MARK + "command=full-import";
		
		DELTA_IMPORT_URL_ITEM = CONFIGURATION_URL + Constants.SEPARATOR_QUESTION_MARK  + "command=delta-import";
		
		RELOAD_CONFIG = CONFIGURATION_URL + Constants.SEPARATOR_QUESTION_MARK + "command=reload-config";
		
		EXECUTION_STATUS = CONFIGURATION_URL + Constants.SEPARATOR_QUESTION_MARK + "command=status";
		
		ABORT = CONFIGURATION_URL + Constants.SEPARATOR_QUESTION_MARK + "command=abort";
	}
	
	/**
	 * Verify the Solr Configuration valid or not
	 * @return
	 * @throws Exception
	 */
	public String verify() throws Exception {
		return SolrUtils.handleSolrReq(CONFIGURATION_URL);
	}
	
	/**
	 * Full Import for this configuration in data-config.xml
	 * 
	 * FUll Import将会删掉之前的索引并全部重新做索引
	 * 
	 * <dataConfig>
		<dataSource name="solrDB" type="JdbcDataSource" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/solr" user="root" password="tools2013"/>
		<document>
		    <entity dataSource="solrDB" name="solr_item"  query="select * from solr_item">
		        <field column="ID" name="id"/>
		        <field column="NAME" name="name"/>
		        <field column="MANU" name="manu"/>
		        <field column="PRICE" name="price"/>
		        <field column="POPULRITY" name="popularity"/>
		        <field column="INCLUDES" name="includes"/>
		    </entity>
		</document>
		</dataConfig>
	 * @throws Exception 
	 * 
	 */
	public void fullImport()  {
	
		String result;
		try {
			result = SolrUtils.handleSolrReq(FULL_IMPORT_URL_DIANDI);
			logger.info("Full Import Ended : /n" + result);
		} catch (Exception e) {
			logger.error("Full Import failed!!!" + ExceptionUtils.getStackTraceMsg(e));
		}
		
	}
	
	/**
	 * Implement this in better solution, not using solr defalut solution
	 * @throws Exception
	 */
	@Deprecated
	public void deltaImport() {
		String result;
		try {
			result = SolrUtils.handleSolrReq(DELTA_IMPORT_URL_ITEM);
			logger.info("Delta Import Ended : /n" + result);
		} catch (Exception e) {
			logger.error("Delat Import Failed: /n " + ExceptionUtils.getStackTraceMsg(e));
		}
	}
	
	/** If the configuration file has been changed and you wish to reload it without restarting Solr */
	public void reload() {
		try {
			String result = SolrUtils.handleSolrReq(RELOAD_CONFIG);
			logger.info("Reload Solr Ended: /n " + result);
		} catch (Exception e) {
			logger.error("Reload Configuration Failed: /n" + ExceptionUtils.getStackTraceMsg(e));
		}
	}
	
	/** It returns statistics on the number of documents created, deleted, queries run, rows fetched, status, and so on*/
	public void getExecutionState() {
		try {
			String result = SolrUtils.handleSolrReq(EXECUTION_STATUS);
			logger.info("Get Solr Execution Status: /n " + result);
		} catch (Exception e) {
			logger.error("Get Solr Execution Status Failed:/n " + ExceptionUtils.getStackTraceMsg(e));
		}
	}
	
	/** Aborts an ongoing operation */
	public void abort() {
		try {
			String result = SolrUtils.handleSolrReq(ABORT);
			logger.info("Abort Solr ongoing actions:/n" + result);
		} catch (Exception e) {
			logger.error("Abort Solr ongoing actions Failed: /n");
		}
		
	}
}
