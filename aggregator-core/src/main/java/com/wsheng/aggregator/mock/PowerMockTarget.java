/**
 * 
 */
package com.wsheng.aggregator.mock;

import java.io.File;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class PowerMockTarget {
	
	public boolean callArgumentInstance(File file) {
		return file.exists();
	}
	
	public boolean callInternalInstance(String path) {
		File file = new File(path);
		return file.exists();
	}
	
	public boolean callFinalMethod(PowerMockDependency refer) {
		return refer.isAlive();
	}
	
	public boolean callStaticMethod() {
		return PowerMockDependency.isExist();
	}
	
	public boolean callPrivateMethod() {
		return isExists();
	}
	
	private boolean isExists() {
		return false;
	}
	
	public boolean callSystemFinalMethod(String str) {
		return str.isEmpty();
	}
	
	public String callSystemStaticMethod(String str) {
		return System.getProperty(str);
	}
	
}
