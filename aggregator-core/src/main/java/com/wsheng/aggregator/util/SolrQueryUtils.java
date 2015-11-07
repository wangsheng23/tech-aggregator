
package com.wsheng.aggregator.util;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.wsheng.aggregator.solr.query.SolrQueryField;
import com.wsheng.aggregator.solr.query.SolrQueryField.SolrQueryFieldOperator;
import com.wsheng.aggregator.solr.query.SolrQueryField.SolrQueryFieldType;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrQueryUtils {
	
	private static Logger logger = Logger.getLogger(SolrQueryUtils.class);
	
	/**
	 * Building query for multiple fields, like for field "name" and field "description"
	 * sb.append("(name:王胜  AND name:Josh) OR (description:王胜  AND description:Josh)")
	 * 
	 * @param queryFields
	 * @param operator
	 * @return
	 */
	public static String buildQuery(List<SolrQueryField<?>> queryFields, SolrQueryFieldOperator operator) {
		LoggerUtils.info(logger, "Current Query Fields : " + queryFields.toString());
		StringBuffer multipleQueryBuilder = new StringBuffer();
		for (int i = 0; i < queryFields.size() -1; i++) {
			multipleQueryBuilder.append("(").append(buildQuery(queryFields.get(i))).append(")");
			multipleQueryBuilder.append(" ").append(operator.name()).append(" ");
		}
		
		multipleQueryBuilder.append("(").append(buildQuery(queryFields.get(queryFields.size() - 1))).append(")");
		
		LoggerUtils.info(logger, "Current Building Query : " + multipleQueryBuilder.toString());
		
		return multipleQueryBuilder.toString();
	}
	
	/**
	 * Building query for single field, like for field "name"
	 * sb.append("name:王胜  AND name:Josh")
	 * @param queryField
	 * @return
	 */
	public static <T extends Serializable> String buildQuery(SolrQueryField<T> queryField) {
		if (queryField != null && queryField.getFieldValues() != null) {
			StringBuffer queryBuilder = new StringBuffer();
			SolrQueryFieldType fieldType = queryField.getFieldType();
			switch (fieldType) {
			case Common:
				buildCommonQuery(queryField, queryBuilder);
				break;
			case Num_Range:
				buildNumRangeQuery(queryField, queryBuilder);
				break;
			case Date_Range:
				buildDateRangeQuery(queryField, queryBuilder);
				break;

			default:
				buildCommonQuery(queryField, queryBuilder);
				break;
			}
			
			return queryBuilder.toString();
		}
		
		return null;
	}
	
	// sb.append("name:王胜  AND name:Josh");
	private static void buildCommonQuery(SolrQueryField<?> queryField, StringBuffer queryBuilder) {
		List<?> fieldValues = queryField.getFieldValues();
		if (fieldValues.size() >= 2) {
			for (int i = 0; i < fieldValues.size() - 1; i++) {
				queryBuilder.append(queryField.getFieldName() + ":" + fieldValues.get(i));
				queryBuilder.append(" ").append(queryField.getFieldOperator().name()).append(" ");
			}
			
			// append the last field value
			queryBuilder.append(queryField.getFieldName() + ":" + fieldValues.get(fieldValues.size()-1));
		} else {
			queryBuilder.append(queryField.getFieldName() + ":" + fieldValues.get(0));
		}
		
	}
	
	/*
	 * 1. <30: fq=name:[* TO 30}
	 * or fq=name:[0 TO 30}
	 * 
	 * 2. >= 30
	 * fq=name:[30 TO *}
	 */
	private static void buildNumRangeQuery(SolrQueryField<?> queryField, StringBuffer queryBuilder) {
		List<?> fieldValues = queryField.getFieldValues();
		if (fieldValues.size() == 2) {
			queryBuilder.append(queryField.getFieldName()).append(SolrQueryField.DELIMETER_COLON);
			Object value1 = fieldValues.get(0), 
					value2 = fieldValues.get(1);
			if (value1 != null) {
				if (!isNumRange(value1)) {
					throw new RuntimeException("Not supported type, only support Integer, Float, Double now!");
				}
			} else {
				value1 = "*";
			}
			
			if (value2 != null) {
				if (!isNumRange(value2)) {
					throw new RuntimeException("Not supported type, only support Integer, Float, Double now!");
				}
			} else {
				value2 = "*";
			}
			
			queryBuilder.append("[").append(value1).append(" ").append(SolrQueryField.DELIMETER_TO).
			append(" ").append(value2).append("]");
		} else {
			throw new RuntimeException("For Number Range Query, There are should be 2 values, Please check your query values!");
		}
	}
	
	/*
	 * fq=ptime:[2013-01-01T00:00:00Z TO * ]  
	 */
	private static void buildDateRangeQuery(SolrQueryField<?> queryField, StringBuffer queryBuilder) {
		List<?> fieldValues = queryField.getFieldValues();
		if (fieldValues.size() == 2) {
			String date1 = fieldValues.get(0).toString(), 
					date2 = fieldValues.get(1).toString();
			
			date1 = date1 == null ? "*" : date1;
			date2 = date2 == null ? "*" : date2;
			
			queryBuilder.append("[").append(date1).append(" ").append(SolrQueryField.DELIMETER_TO).
				append(" ").append(date2).append("]");
			
		} else {
			throw new RuntimeException("For Date Range Query, There are should be 2 values, Please check your query values!");
		}
	}
	
	private static boolean isNumRange(Object value) {
		return Integer.class.isInstance(value) || Float.class.isInstance(value) || Double.class.isInstance(value);
	}
	

}
