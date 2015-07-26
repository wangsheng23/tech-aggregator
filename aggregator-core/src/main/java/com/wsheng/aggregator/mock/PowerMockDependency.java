/**
 * 
 */
package com.wsheng.aggregator.mock;

/**
 * 
 * just like nucleonutils
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class PowerMockDependency {
	
	public final boolean isAlive() {
		System.out.println(" Hehe... I am mocking a final method");
		return false;
	}
	
	public static boolean isExist() {
		System.out.println(" Hehe... I am mocking a static method");
		return false;
	}

}
