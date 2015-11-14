/**
 * 
 */
package com.wsheng.aggregator.solr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;

import com.wsheng.aggregator.solr.bean.SolrCore;
import com.wsheng.aggregator.solr.bean.SolrParam;
import com.wsheng.aggregator.solr.query.SolrClientBuilder;
import com.wsheng.aggregator.solr.query.SolrQueryField;
import com.wsheng.aggregator.solr.query.SolrQueryField.SolrQueryFieldOperator;
import com.wsheng.aggregator.solr.query.SolrQueryPagination;
import com.wsheng.aggregator.util.CommonUtils;
import com.wsheng.aggregator.util.ExceptionUtils;
import com.wsheng.aggregator.util.LoggerUtils;
import com.wsheng.aggregator.util.SolrQueryUtils;


/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrActionProxy {
	
	private SolrClient solrClient;
	
	protected static Logger logger = Logger.getLogger(SolrActionProxy.class);
	
	public SolrActionProxy(SolrCore core) {
		solrClient = SolrClientBuilder.getSolrClinetInstance(core);
		
	}
	
	public SolrActionProxy(String baseURL) {
		solrClient = new HttpSolrClient(baseURL);
	}

	/**
	 * Query by given solr query language without pagination
	 * 
	 * @param query
	 * @param core The target core for current action
	 * @return
	 */
	public QueryResponse query(String queryStr) {
		ModifiableSolrParams params = new SolrQuery(queryStr);
		QueryResponse response = null;
		try {
			// the default return values is 10, we have to set it to a big enough value
			params.set(SolrParam.rows.name(), Integer.MAX_VALUE);
			response = solrClient.query(params);
		} catch (SolrServerException | IOException e) {
			LoggerUtils.error(logger, "Query failed for the query: " + queryStr + "	" + ExceptionUtils.getStackTraceMsg(e));
		}
		return response;
	}
	
	/**
	 * Query by given solr query language with pagination
	 * 
	 * @param query
	 * @param core The target core for current action
	 * @return
	 */
	public List<QueryResponse> query(String queryStr, int pageSize) {
		SolrQuery solrQuery = new SolrQuery(queryStr);
		List<QueryResponse> responses = paginationQuery(solrQuery, pageSize);
		return responses;
	}
	
	/**
	 * Query by given Solr Parameters without pagination
	 * 
	 * @param params	using *:* to query all of the documents
	 * @param core The target core for current action
	 * @return
	 */
	public QueryResponse query(ModifiableSolrParams params) {

		QueryResponse response = null;
		try {
			params.set(SolrParam.rows.name(), Integer.MAX_VALUE);
			response = solrClient.query(params);
		} catch (SolrServerException | IOException e) {
			LoggerUtils.error(logger, "Query docs by  ModifiableSolrParams failed "  + ExceptionUtils.getStackTraceMsg(e));
		}
		return response;
	}
	
	/**
	 * Query by SolrQuery Object without pagination
	 * 
	 * @param params	SolrQuery
	 * @param core The target core for current action
	 * @return
	 */
	public QueryResponse query(SolrQuery params) {
		QueryResponse response = null;
		try {
			params.set(SolrParam.rows.name(), Integer.MAX_VALUE);
			response = solrClient.query(params);
			return response;
		} catch (SolrServerException | IOException e) {
			LoggerUtils.error(logger, "Query docs by SolrQuery failed "  + ExceptionUtils.getStackTraceMsg(e));
		}
		return response;
	}

	/**
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");  
	 * String time = "lastTime:["+sdf.format(new Date())+" TO "+sdf.format(new Date())+"]";
	 * 自定义高级查询Field, 可指定Field是否进行排序，是否高亮显示。
	 * 使用自定义分页查询来包装查询的Fields
	 * 
	 * @param queryFields
	 * @param pageSize
	 * @param fieldsOperator - the Operator between Fields: like Fields(name) A and Fields B(manu)
	 * @return
	 */
	public List<QueryResponse> query(List<SolrQueryField<?>> queryFields, int pageSize, SolrQueryFieldOperator fieldsOperator) {
		// the sort filed should be saved in the order map because of the sort function. can't be HashMap here
		Map<String, ORDER> orderFields		= new LinkedHashMap<String, ORDER>();
		
		List<String> facetFields 	   		= new ArrayList<>();
		List<String> highlightFields 		= new ArrayList<>();
		
		String queryStr = SolrQueryUtils.buildQuery(queryFields, fieldsOperator);
		
		for (SolrQueryField<?> field : queryFields) {
			if (field.isSort())
				orderFields.put(field.getFieldName(), field.getSortType());
			if (field.isHighlight())
				highlightFields.add(field.getFieldName());
			if (field.isFacet()) 
				facetFields.add(field.getFieldName());
		}
	
		SolrQuery query = new SolrQuery(queryStr);
		for (Map.Entry<String, ORDER> sortField : orderFields.entrySet()) {
			query.addSort(sortField.getKey(), sortField.getValue());
		}
		// Add Facet Field
		if (facetFields.size() > 0) {
			query.setFacet(true).setFacetMinCount(1).setFacetLimit(100);
			for (String facetFieldName : facetFields) {
				query.addFacetField(facetFieldName);
			}
		}
		
		if (highlightFields.size() > 0) {
			query.setHighlight(true);
			query.setHighlightSimplePre("<font color=\'red\'>"); // html渲染
	        query.setHighlightSimplePost("</font>"); 
	        query.setHighlightFragsize(200); // 设置每个分片的最大长度
	        
	        for (String highLightFieldName : highlightFields) {
	        	query.addHighlightField(highLightFieldName);
	        	// query.setParam("hl.fl", highLightFieldName);
	        }
		}
		
		logQuery(query);
	
		return paginationQuery(query, pageSize);
		
	}
	
	

	/**
	 * Add Doc, if the doc exists, update it.
	 * 
	 * @param doc
	 * @param core The target core for current action
	 */
	public UpdateResponse addDoc(SolrInputDocument doc) {
		UpdateResponse response = null;
		
		try {
			response = solrClient.add(doc);
			solrClient.commit(); // required
			
		} catch (SolrServerException | IOException e) {
			LoggerUtils.error(logger, "Add doc failed "  + ExceptionUtils.getStackTraceMsg(e));
		} 
		
		return response;
	}

	/**
	 * Add Docs
	 * 
	 * @param docs	
	 * @param core The target core for current action
	 */
	public UpdateResponse addDocs(Collection<SolrInputDocument> docs) {
		UpdateResponse response = null;
		try {
			response = solrClient.add(docs);
			solrClient.commit();
		} catch (SolrServerException | IOException e) {
			LoggerUtils.error(logger, "Add docs failed "  + ExceptionUtils.getStackTraceMsg(e));
		} 
		
		return response;
	}


	/**
	 * Delete the doc by doc id immediately or not
	 * 
	 * @param id
	 * @param core The target core for current action
	 */
	public void removeDoc(String id, boolean needCommit) {
		try {
			solrClient.deleteById(id);
			if (needCommit)
				solrClient.commit();
		} catch (SolrServerException | IOException e) {
			LoggerUtils.error(logger, "Remove doc failed "  + ExceptionUtils.getStackTraceMsg(e));
		} 
	}
	
	/**
	 * Delete docs by doc id immediately or not
	 * 
	 * @param ids	
	 * @param needCommit
	 * @param core The target core for current action
	 */
	public void removeDocs(List<String> ids, boolean needCommit) {
		try {
			solrClient.deleteById(ids);
			if (needCommit)
				solrClient.commit();
		} catch (SolrServerException | IOException e) {
			LoggerUtils.error(logger, "Remove docs failed "  + ExceptionUtils.getStackTraceMsg(e));
		}
	}

	/**
	 * Delete docs by query immediately or not
	 * 
	 * @param query
	 * @param needCommit
	 * @param core The target core for current action
	 */
	public void removeDocs(String query, boolean needCommit) {
		try {
			solrClient.deleteByQuery(query);
			if (needCommit)
				solrClient.commit();
		} catch (SolrServerException | IOException e) {
			LoggerUtils.error(logger, "Remove docs failed : "  + ExceptionUtils.getStackTraceMsg(e));
		}
	}
	
	public void releaseResource() {
		if (solrClient != null) {
			try {
				solrClient.close();
			} catch (IOException e) {
				LoggerUtils.error(logger, "Close Solr Client failed : "  + ExceptionUtils.getStackTraceMsg(e));
			}
		}
	}
	
	private List<QueryResponse> paginationQuery(SolrQuery query, int pageSize) {
		List<QueryResponse> responses = new ArrayList<>();
		
		SolrQueryPagination page = new SolrQueryPagination(pageSize);
		
		boolean quit = false;
		boolean needScroll = true;
		do {
			int start = page.getStart();
			
			// Pagination or not
			if (start != -1 && pageSize != -1) {
				query.setStart(start);
				query.setRows(pageSize);
			} else {// In Solr, the default return page size is 10
				// return all of the docs
				query.setRows(Integer.MAX_VALUE);// 2147483647 = 2<sup>31</sup>-1
				quit = true;
			}
			
			LoggerUtils.info(logger, " Current Pagination Query is : " + CommonUtils.decode(query.toString()));

			try {
				QueryResponse response = solrClient.query(query);
				responses.add(response);
				
				// Each Solr query will return the total number always, so only need to query 1 time.
				if (response != null && response.getResults() != null && page.getTotalCount() == 0) {
					page.setTotalCount(response.getResults().getNumFound()); 
				}
			} catch (SolrServerException | IOException e) {
				LoggerUtils.error(logger, "Query docs by QueryFields failed "  + ExceptionUtils.getStackTraceMsg(e));
			}
			
		} while (page.hasNext(needScroll) && !quit);
		
		return responses;
	}
	
	private void logQuery(SolrQuery query) {
		if (query != null) {
			try {
				LoggerUtils.info(logger, "Current Query : " + URLDecoder.decode(query.toString(), "UTF-8"));
				System.out.println(" Current Query : " + URLDecoder.decode(query.toString(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				LoggerUtils.error(logger, "Query conents is so bad : "  + ExceptionUtils.getStackTraceMsg(e));
			}	
		}
	}
	
}
