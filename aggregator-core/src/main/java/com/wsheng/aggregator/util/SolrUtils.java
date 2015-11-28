package com.wsheng.aggregator.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.wsheng.aggregator.solr.query.SolrQueryField.SolrQueryFieldType;


/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrUtils {

	/**
	 * Get the target value from the specified Solr filed
	 */
	public static Object getValue(SolrDocument doc, String fieldName) {
		// In new solr version, this will return something like [value]
//		Collection<String> fieldNames = doc.getFieldNames();
//		for (String field : fieldNames) {
//			if (fieldName.equalsIgnoreCase(field)) {
//				
//				return doc.getFieldValue(fieldName);
//			}
//		}
//		
		// but this will return value directly, no []
		Map<String, Object> fieldMap = doc.getFieldValueMap();
		return fieldMap.get(fieldName);
	}
	
	/**
	 * Get the target values from the specified Solr filed
	 */
	public static Set<Object> getValues(SolrDocumentList docs, String fieldName) {
		if (docs != null && docs.size() > 0) {
			Set<Object> values = new HashSet<Object>();
			
			for (SolrDocument doc : docs) {
				Collection<String> fieldNames = doc.getFieldNames();
				
				for (String field : fieldNames) {
					if (fieldName.equalsIgnoreCase(field)) {
						// values.add(doc.getFieldValue(fieldName));
						values.add(doc.getFieldValueMap().get(fieldName));
						break;
					}
				}
			}
			
			return values;
		}
		
		return null;
		
	}
	
	/**
	 * Get the Facet fields from the response
	 * 
	 * @param response
	 * @return
	 */
	public static List<FacetField> getFacetField(QueryResponse response) {
		return response.getFacetFields();
	}
	
	/**
	 * Get the highlight fields
	 * first key: doc id
	 * second key : the highlighted field name
	 * 
	 * @param response
	 * @return
	 */
	public static Map<String, Map<String, List<String>>> getHighlighFields(QueryResponse response) {
		return response.getHighlighting();
	}
	
	/*public static String handleSolrReq(String url) throws Exception {
      
        String result = HttpUtils.getResource(url, MediaType.APPLICATION_JSON_TYPE);
              
        return result;
    }*/
	
	/**
	 * Set complex properties
	 * 
	 * @param bean
	 * @param values
	 */
	public static void placeComplexProps(Object bean, Map<String, Object> values) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
			
			for (PropertyDescriptor descriptor : descriptors) {
				String fieldName = descriptor.getName();
				if (values.containsKey(fieldName)) {
					Method setter = descriptor.getWriteMethod();
					Object value = values.get(fieldName);
					if (value != null) {
						if (descriptor.getPropertyType().isEnum()) {
							if ("sortType".equals(descriptor.getDisplayName()))  {
								value = ORDER.valueOf(value.toString());
							} 
							
							if ("fieldType".equals(descriptor.getDisplayName())) {
								value = SolrQueryFieldType.valueOf(value.toString());
							}
						}
						setter.invoke(bean, value);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String handleSolrReq(String url) throws Exception {
	      
        String result = HttpUtils.getResource(url, MediaType.APPLICATION_JSON_TYPE);
              
        return result;
    }
	
}
