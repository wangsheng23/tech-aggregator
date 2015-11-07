package com.wsheng.aggregator.solr.query;

import java.util.List;

import com.wsheng.aggregator.solr.bean.SolrConstants;

/**
 * 1. SolrQueryPagination includes the page related  parameters and the queried results for the iterating
 * 2. SolrQueryPagination could be inherited to add more different query conditions 
 * 3. Its different from DB query pagination because of Solr query result contains the total
 * documents, there is no need to query the total number just doing in normal DB.
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrQueryPagination {
	
	private int currentPageNum = 1; 
	
	private int pageSize = SolrConstants.PAGE_SIZE;
	
	private long totalCount;
	
	/** Query Parameters*/
	private List<SolrQueryField<?>> queryFields;
	
	public SolrQueryPagination() {
		
	}
	
	public SolrQueryPagination(List<SolrQueryField<?>> queryFields, int pageSize) {
    	this.pageSize = pageSize;
    	this.queryFields = queryFields;
    }
	
	public SolrQueryPagination(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**Get total pages*/
	public long getTotalPages() {
		long totalPages = totalCount / pageSize;
		
		if (totalCount % pageSize != 0)
			totalPages = totalPages + 1;
		
		return totalPages;
	}
	
	/** Get the started number of the query*/
	public int getStart() {
		return (currentPageNum - 1) * pageSize;
	}
	
	/** Has previous page or not */
    public boolean hasPrevious() {
        return currentPageNum == 1 ? false : true;
    }
    
    /** Has the last page or not */
    public boolean hasNext(boolean needScroll) { // scroll to next page if needed(like in do while case)
    	boolean hasNext = getTotalPages() != 0 && getTotalPages() != currentPageNum ? true : false;
    	
    	if (hasNext && needScroll)
    		nextPage();
    	
    	return hasNext;
    }
    
    /** Navigation to next page*/
    public void nextPage() {
    	currentPageNum ++;
    }

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<SolrQueryField<?>> getQueryFields() {
		return queryFields;
	}

	public void setQueryFields(List<SolrQueryField<?>> queryFields) {
		this.queryFields = queryFields;
	}
}
