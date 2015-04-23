/**
 * 
 */
package com.wsheng.aggregator.collection;

import java.util.Stack;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 * 
 */
public class TestStack {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
		
		System.out.println(" ================== 1 ================");
		// iterator in collection method
		for (Integer x : stack) {
			System.out.print(x + " "); // not first in last out
		}
		System.out.println("size: " + stack.size()); 
		
		System.out.println(" ================== 2 ================");
		
		// iterator in stack Popup method 
//		while (stack.peek() != null) { // the method throws exception from time to time
//			System.out.print(stack.pop() + " ");
//		}
		
		// the correct iterator for Stack should be
		while (!stack.empty()) {
			System.out.print(stack.pop() + " ");
		}
		
		System.out.println();
		
		System.out.println("current size: " + stack.size()); 
				
	}
}
