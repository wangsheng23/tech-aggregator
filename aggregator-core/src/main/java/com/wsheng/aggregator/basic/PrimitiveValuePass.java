/**
 * 
 */
package com.wsheng.aggregator.basic;

/**
 * 
 * 基本类型的传递
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 * 
 */
public class PrimitiveValuePass {
	
	public static void main(String[] args) {
		boolean test = true;
		
		System.out.println(" ======= Test Pass1 ======= ");
		System.out.println("Before pass : test =  " + test);
		pass1(test);
		System.out.println("After pass : test = " + test);
		
		
		System.out.println(" ======= Test Pass2 ======= ");
		Boolean test2 = true;
		System.out.println("Before pass : test =  " + test);
		pass2(test2);
		System.out.println("After pass : test = " + test);
		
	}
	
	// Primitive value pass
	public static void pass1(boolean test) {
		test = !test;
		System.out.println("in pass : test = " + test);
	}
	
	// 会自动拆箱为基本类型
	public static void pass2(Boolean test) {
		test = !test;
		System.out.println("in pass : test = " + test);
	}
	
	

}
