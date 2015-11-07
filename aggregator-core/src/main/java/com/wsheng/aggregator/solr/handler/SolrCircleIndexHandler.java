/**
 * 
 */
package com.wsheng.aggregator.solr.handler;

import org.springframework.stereotype.Component;

import com.wsheng.aggregator.solr.bean.SolrIndexNet;
import com.wsheng.aggregator.solr.bean.SolrIndexResult;
import com.wsheng.aggregator.solr.bean.SolrIndexTable;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
@Component("circleIndexHandler")
public class SolrCircleIndexHandler extends SolrIndexActionHandler<SolrIndexNet> {
	
	// @Resource private ICircleService circleService;
	
	// private static SolrActionProxy solrActionProxy = new SolrActionProxy(SolrCore.Circle);;
	
	@Override
	protected SolrIndexTable getTarget() {
		return SolrIndexTable.circle;
	}
	
	@Override
	protected SolrIndexResult response(SolrIndexNet SolrIndexNet) {
		SolrIndexResult result = new SolrIndexResult();
		
		/*String cid = SolrIndexNet.getBid();
		
		CircleUserSummaryVO circle;
		try {
			circle = circleService.getCircle4Index(Long.valueOf(cid));
			if (circle != null) {
				// name, description, type, owner, member_count, member_limit, permission, last_reference_mid,  last_update_time, create_time;
				SolrInputDocument circleDocument = new SolrInputDocument();
				
				if (BaseStatus.Expired.getCode() == circle.getStatus()) {
					// the circle already be dismissed, should delete the index
					solrActionProxy.removeDoc(String.valueOf(circle.getCid()), true);
				} else {// the new document will replace the old one totally.
					circleDocument.addField(Field.id.name(), circle.getCid());
					circleDocument.addField(Field.name.name(), circle.getCircleName());
					circleDocument.addField(Field.description.name(), circle.getCircleDescription());
					circleDocument.addField(Field.type.name(), circle.getCircleType().getCode());// Should be Public Circle(4) and Public Activity(7) currently
					circleDocument.addField(Field.owner.name(), circle.getCircleOwner());
					circleDocument.addField(Field.member_count.name(), circle.getCircleMemberCount());
					circleDocument.addField(Field.member_limit.name(), circle.getCircleMemberLimit());
					circleDocument.addField(Field.permission.name(), circle.getCirclePermission());
					circleDocument.addField(Field.create_time.name(), circle.getCircleCreateTime());
					circleDocument.addField(Field.last_update_time.name(), circle.getLastUpdateTime());
					circleDocument.addField(Field.logo.name(), circle.getCircleLogo());
					circleDocument.addField(Field.weight.name(), circle.getWeight());
					circleDocument.addField(Field.status.name(), circle.getStatus());
					circleDocument.addField(Field.e_bound_circle.name(), circle.getCircleEBoundCircle());
					circleDocument.addField(Field.e_addr.name(), circle.getCircleEAddr());
					circleDocument.addField(Field.e_desc.name(), circle.getCircleEDesc());
					
					// 因为schema中指定的是date类型，所以此次必须转化为date，如果是String，索引创建不会成功
					circleDocument.addField(Field.e_start_time.name(), DateUtils.str2Date(circle.getCircleEStartTime(), DateUtils.MIDDLE_LINE_TIMESTAMP));
					circleDocument.addField(Field.e_end_time.name(), DateUtils.str2Date(circle.getCircleEEndTime(), DateUtils.MIDDLE_LINE_TIMESTAMP));
					
					solrActionProxy.addDoc(circleDocument);
				}
				result.setResult(Status.success.name());
				LoggerUtils.info(logger, " Add/Updte Index success for circle: " + cid + " name : " + circle.getCircleName());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTraceMsg(e));
			result.setResult(Status.failed.name());
			result.setMessage(e.getMessage());
		}*/
		
		return result;
	}

}
