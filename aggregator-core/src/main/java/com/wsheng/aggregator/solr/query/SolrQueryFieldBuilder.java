/**
 * 
 */
package com.wsheng.aggregator.solr.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wsheng.aggregator.solr.bean.SolrBizField;
import com.wsheng.aggregator.solr.query.SolrQueryField.SolrQueryFieldOperator;
import com.wsheng.aggregator.solr.query.SolrQueryField.SolrQueryFieldType;
import com.wsheng.aggregator.solr.vo.SolrFileImageSearchVO;
import com.wsheng.aggregator.solr.vo.SolrMessageSearchVO;


/**
 * SolrQueryFieldBuilder used to build the user search requests to SolrQueryField
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrQueryFieldBuilder {

	/**
	 * For Message, the search conditions are:
	 * 
	 * 1) Message sender
	 * 
	 * 2) Time range
	 * 
	 * 3) Exact time : Start Time equals End Time
	 * 
	 * @param vo
	 * @return
	 */
	public List<SolrQueryField<String>> buildMsgQueryFields(SolrMessageSearchVO vo) {
		List<SolrQueryField<String>> fields = new ArrayList<>();
		
		if (vo != null) {
			// 1. want to search the message of the specified sender
			if (vo.getSender() != null) {
				SolrQueryField<String> senderField = new SolrQueryField<String>();
				senderField.setFieldName(SolrBizField.uid.name());
				senderField.setFieldValues(buildMultipleValues(vo.getSender()));
				senderField.setFieldOperator(SolrQueryFieldOperator.OR);
				senderField.setFieldType(SolrQueryFieldType.Common);
				
				fields.add(senderField);
			}
			
			// 2. want to search the message of the specified circle
			if (vo.getCid() != null) {
				SolrQueryField<String> circleField = new SolrQueryField<String>();
				circleField.setFieldName(SolrBizField.cid.name());
				circleField.setFieldValues(buildMultipleValues(vo.getCid()));
				circleField.setFieldOperator(SolrQueryFieldOperator.OR);
				circleField.setFieldType(SolrQueryFieldType.Common);
				
				fields.add(circleField);
			}
			
			// 3. want to search the message on the specified time, will search on create time or update time
			if (vo.getTime() != null) { // 可能不需要，需要确认？
				SolrQueryField<String> createTimeField = new SolrQueryField<String>();
				createTimeField.setFieldName(SolrBizField.create_time.name());
				createTimeField.setFieldValues(buildMultipleValues(vo.getTime()));
				createTimeField.setFieldOperator(SolrQueryFieldOperator.OR);
				createTimeField.setFieldType(SolrQueryFieldType.Common);
				
				SolrQueryField<String> updateTimeField = new SolrQueryField<String>();
				updateTimeField.setFieldName(SolrBizField.last_update_time.name());
				updateTimeField.setFieldValues(buildMultipleValues(vo.getTime()));
				updateTimeField.setFieldOperator(SolrQueryFieldOperator.OR);
				updateTimeField.setFieldType(SolrQueryFieldType.Common);
				
				fields.add(createTimeField);
				fields.add(updateTimeField);
			} else if (vo.getStartTime() != null) {
				SolrQueryField<String> startTimeField = new SolrQueryField<String>();
				startTimeField.setFieldName(SolrBizField.start_time.name());
				startTimeField.setFieldValues(buildMultipleValues(vo.getStartTime()));
				startTimeField.setFieldOperator(SolrQueryFieldOperator.OR);
				startTimeField.setFieldType(SolrQueryFieldType.Date_Range);
				
				fields.add(startTimeField);
			} else if (vo.getEndTime() != null) {
				SolrQueryField<String> endTimeField = new SolrQueryField<String>();
				endTimeField.setFieldName(SolrBizField.end_time.name());
				endTimeField.setFieldValues(buildMultipleValues(vo.getStartTime()));
				endTimeField.setFieldOperator(SolrQueryFieldOperator.OR);
				endTimeField.setFieldType(SolrQueryFieldType.Date_Range);
				
				fields.add(endTimeField);
			}
		}
		
		return fields;
	}
	
	/**
	 * For File/Image, the search conditions are:
	 * 
	 * 1) the upload user of the File or Image
	 * 
	 * 2) Time range
	 * 
	 * 3) Exact time : Start Time equals End Time
	 * 
	 * @param vo
	 * @return
	 */
	public List<SolrQueryField<String>> buildFIQueryFields(SolrFileImageSearchVO vo) {
		List<SolrQueryField<String>> fields = new ArrayList<>();
		
		if (vo != null) {
			// 1. want to search the File / Image of the specified upload user
			if (vo.getUploader() != null) {
				SolrQueryField<String> uploaderField = new SolrQueryField<String>();
				uploaderField.setFieldName(SolrBizField.uid.name());
				uploaderField.setFieldValues(buildMultipleValues(vo.getUploader()));
				uploaderField.setFieldOperator(SolrQueryFieldOperator.OR);
				uploaderField.setFieldType(SolrQueryFieldType.Common);
				
				fields.add(uploaderField);
			}
			
			// 2. want to search the File / Image of the specified circle
			if (vo.getCid() != null) {
				SolrQueryField<String> circleField = new SolrQueryField<String>();
				circleField.setFieldName(SolrBizField.cid.name());
				circleField.setFieldValues(buildMultipleValues(vo.getCid()));
				circleField.setFieldOperator(SolrQueryFieldOperator.OR);
				circleField.setFieldType(SolrQueryFieldType.Common);
				
				fields.add(circleField);
			}
			
			// 3. want to search the message on the specified time, will search on create time or update time
			if (vo.getTime() != null) { // 可能不需要，需要确认？
				SolrQueryField<String> createTimeField = new SolrQueryField<String>();
				createTimeField.setFieldName(SolrBizField.create_time.name());
				createTimeField.setFieldValues(buildMultipleValues(vo.getTime()));
				createTimeField.setFieldOperator(SolrQueryFieldOperator.OR);
				createTimeField.setFieldType(SolrQueryFieldType.Common);
				
				SolrQueryField<String> updateTimeField = new SolrQueryField<String>();
				updateTimeField.setFieldName(SolrBizField.last_update_time.name());
				updateTimeField.setFieldValues(buildMultipleValues(vo.getTime()));
				updateTimeField.setFieldOperator(SolrQueryFieldOperator.OR);
				updateTimeField.setFieldType(SolrQueryFieldType.Common);
				
				fields.add(createTimeField);
				fields.add(updateTimeField);
			} else if (vo.getStartTime() != null) {
				SolrQueryField<String> startTimeField = new SolrQueryField<String>();
				startTimeField.setFieldName(SolrBizField.start_time.name());
				startTimeField.setFieldValues(buildMultipleValues(vo.getStartTime()));
				startTimeField.setFieldOperator(SolrQueryFieldOperator.OR);
				startTimeField.setFieldType(SolrQueryFieldType.Date_Range);
				
				fields.add(startTimeField);
			} else if (vo.getEndTime() != null) {
				SolrQueryField<String> endTimeField = new SolrQueryField<String>();
				endTimeField.setFieldName(SolrBizField.end_time.name());
				endTimeField.setFieldValues(buildMultipleValues(vo.getStartTime()));
				endTimeField.setFieldOperator(SolrQueryFieldOperator.OR);
				endTimeField.setFieldType(SolrQueryFieldType.Date_Range);
				
				fields.add(endTimeField);
			}	
		}
		
		return fields;
	}
	
	// the default separator is space
	private List<String> buildMultipleValues(String value) {
		String[] values = value.split("\\s+"); // \s* 0 or more space, \s+ one or more space
		return Arrays.asList(values);
	}
}
