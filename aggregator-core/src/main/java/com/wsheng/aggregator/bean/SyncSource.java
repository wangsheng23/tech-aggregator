/**
 * 
 */
package com.wsheng.aggregator.bean;

import java.io.Serializable;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 * 
 */
public class SyncSource implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Target target;
	
	public SyncSource() {
		
	}
	
	public SyncSource(Target target) {
		this.target = target;
	}
	
	/**
	 * <P>Note: the name must match with the key in cms-auth.properties</P>
	 * 
	 * cms-auth.properties
	 * 
	 * #type 	| user name		| token
	 * lbsync=_LBSYNC_READWRITE:ebay@2013
	 * 
	 * We only have LB sync currently
	 */
	public enum Target {

		lbsync;
		
		
	}
	


	public Target getTarget() {
		return target;
	}


	public void setTarget(Target target) {
		this.target = target;
	}


}
