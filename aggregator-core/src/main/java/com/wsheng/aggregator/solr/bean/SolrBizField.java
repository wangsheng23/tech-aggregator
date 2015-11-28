/**
 * 
 */
package com.wsheng.aggregator.solr.bean;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public enum SolrBizField {
	// ============== Common ===================
	id, last_update_time, create_time, start_time, end_time,
	
	
	// ============== SpaceMessage ===================
	mid, uid, cid, tid, circle_parentid, uname, content, status, flag, message_to, reference_mid, target_cids, sync_mid, content_type,

	// ==================== Circle ====================
	name, description, type, owner, member_count, member_limit, permission, last_reference_mid, logo, weight,
	e_bound_circle, e_desc, e_start_time, e_end_time, e_addr;
	
}
