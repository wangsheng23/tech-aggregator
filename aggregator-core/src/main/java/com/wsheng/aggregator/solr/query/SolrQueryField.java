/**
 * 
 */
package com.wsheng.aggregator.solr.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery.ORDER;


/**
 * Constructing the target field when searching from Solr
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrQueryField<T extends Serializable> {
	
	/** The name of the searched field*/
	private String fieldName;
	
	/** The value of the searched field*/
	private List<T> fieldValues;
	
	/** Facet search or not, Default is false
	 * (Shard search, Vertical search, Navigation Search)
	 */
	private boolean facet;
	
	/** Highlight the field or not, Default is false
	 *  1. The field won't highlight by default
	 *  2. WS: float£¬integer filed can't be highlighted
	 *  3. The store attributed must be "true" if the field needed to be highlighted
	 */
	private boolean highlight;
	
	/** The filed is the sort field or not, Default is false*/
	private boolean sort;
	
	/** The sort type is asc by default*/
	private ORDER sortType = ORDER.asc;
	
	/** The field type is Common by default*/
	private SolrQueryFieldType fieldType = SolrQueryFieldType.Common;
	
	/** The field operator is OR by default*/
	private SolrQueryFieldOperator fieldOperator = SolrQueryFieldOperator.OR; 
	
	public final static String 			DELIMETER_COLON 	= ":";
	public final static String		 	DELIMETER_TO 		= "TO";
	public final static char 			DELIMETER_COMMA 	= ',';
	
	public SolrQueryField() {
		
	}
	
	public SolrQueryField(String fieldName, List<T> fieldValues, boolean facet, boolean highlight, boolean sort, ORDER sortType, 
			SolrQueryFieldType fieldType, SolrQueryFieldOperator fieldOperator) {
		super();
		this.fieldName 		= fieldName;
		this.fieldValues 	= fieldValues;
		this.facet 			= facet;
		this.highlight 		= highlight;   
		this.sort 			= sort;
		this.sortType 		= sortType;
		this.fieldType 		= fieldType;
		this.fieldOperator 	= fieldOperator;
	}
	
	public SolrQueryField(String fieldName, T value, boolean facet, boolean highlight, boolean sort, ORDER sortType, 
			SolrQueryFieldType fieldType, SolrQueryFieldOperator fieldOperator) {
		super();
		this.fieldName 		= fieldName;
		this.facet 			= facet;
		
		List<T> values 		= new ArrayList<T>();
		values.add(value);
		this.fieldValues 	= values;
		
		this.highlight 		= highlight;
		this.sort 			= sort;
		this.sortType 		= sortType;
		this.fieldType 		= fieldType;
		this.fieldOperator 	= fieldOperator;
	}
	
	public enum SolrQueryFieldType {
		Common, // String, Integer, Double, Long, Float etc.
		Num_Range,// Integer Range, Float Range, Double Range
		Date_Range;
	}
	
	public enum SolrQueryFieldOperator {
		AND, OR, NOT, MINUS, PLUS;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public List<T> getFieldValues() {
		return fieldValues;
	}

	public void setFieldValues(List<T> fieldValues) {
		this.fieldValues = fieldValues;
	}

	public boolean isFacet() {
		return facet;
	}

	public void setFacet(boolean facet) {
		this.facet = facet;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	public boolean isSort() {
		return sort;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
	}

	public ORDER getSortType() {
		return sortType;
	}

	public void setSortType(ORDER sortType) {
		this.sortType = sortType;
	}

	public SolrQueryFieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(SolrQueryFieldType fieldType) {
		this.fieldType = fieldType;
	}

	public SolrQueryFieldOperator getFieldOperator() {
		return fieldOperator;
	}

	public void setFieldOperator(SolrQueryFieldOperator fieldOperator) {
		this.fieldOperator = fieldOperator;
	}

}
