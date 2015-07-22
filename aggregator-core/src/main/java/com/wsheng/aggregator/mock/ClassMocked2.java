/**
 * 
 */
package com.wsheng.aggregator.mock;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class ClassMocked2 {

	public static int getDouble(int i) {
		System.out.println("================");
        return i*2;
	}
	
	public String getTripleString(int i) {
	    return multiply3(i) + "";
	}
	
	private int multiply3(int i) {
	    return i*3;
	}
}
