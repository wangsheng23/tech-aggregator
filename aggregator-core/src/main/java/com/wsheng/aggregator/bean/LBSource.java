/**
 * 
 */
package com.wsheng.aggregator.bean;


/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class LBSource extends SyncSource {

	private static final long serialVersionUID = 139382269429816841L;

	public enum LBSourceType {
		all, // all means parse the whole LB data content, includes below items.
		array, // means parse the String[] content
		text,
		lbmember_hash,
		lbvirtualip_hash,
		lb_monitor_hash,
		lbpool_hash,
		bind_lbv_hash,
		bind_monitor_hash,
		bind_csv_hash;
	}
}
