/**
 * 
 */
package com.wsheng.aggregator.enums;

/**
 * The Enumeration used to decide the mail details(including mail title,
 * mail content, mail receiver)
 * @author swang6
 *
 */
public enum EmailTargetEnum {
	
	DBMetrics_Check(1, "Check Metrics");
	
	private int targetCode;
	
	private String target;
	
	private EmailTargetEnum(int targetCode, String target) {
		this.targetCode = targetCode;
		this.target = target;
	}

	public int getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(int targetCode) {
		this.targetCode = targetCode;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	
}
