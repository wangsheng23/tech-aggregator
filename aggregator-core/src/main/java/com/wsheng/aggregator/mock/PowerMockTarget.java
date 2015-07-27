/**
 * 
 */
package com.wsheng.aggregator.mock;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

import com.mysql.jdbc.StringUtils;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class PowerMockTarget {
	
	public boolean callArgumentInstance(File file) { // pass an Object parameter
		return file.exists();
	}
	
	public boolean callInternalInstance(String path) { // new an Object within a method
		File file = new File(path);
		return file.exists();
	}
	
	public boolean callFinalMethod(PowerMockDependency refer) { // a final method
		return refer.isAlive();
	}
	
	public boolean callStaticMethod() { // a static method
		return PowerMockDependency.isExist();
	}
	
	public boolean callPrivateMethod() { // a private method
		return isExists();
	}
	
	private boolean isExists() {
		return false;
	}
	
	public FileDescriptor callSystemFinalMethod(FileOutputStream fos) { // System final method
		try {
			return fos.getFD();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String callSystemStaticMethod(String str) { // System static method
		return System.getProperty(str);
	}
	
	public static boolean identify(String test) { // self static method
		return StringUtils.isNullOrEmpty(test) ? false : true;
				
	}
	
}
