/**
 * 
 */
package com.wsheng.aggregator.basic;

/**
 * String is special, str = str + "2" will create a new String 
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 * 
 */
public class StringValuePass {

	public static void passTest(String str) {
		System.out.println(" === before append: hash code: " + str.hashCode());
		str = " hello : " + str;
		System.out.println(" === After append: hash code: " + str.hashCode());
		System.out.println("in pass : str = " + str);
	}
	
	public static void main(String[] args) {
		System.out.println(" ======= Test Pass3 ======= ");
		String str = "wangsheng";
		System.out.println("Before pass : str =  " + str + " hash code: " + str.hashCode());
		passTest(str);
		System.out.println("After pass : str = " + str);
	}
}
