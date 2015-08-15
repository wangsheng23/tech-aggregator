package com.wsheng.aggregator.generic;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class GenericMethod {
	
	public <T> void print(T x) {
		System.out.println(x.getClass().getName());
	}
	
	public static void main(String[] args) {
		GenericMethod method = new GenericMethod();
		method.print(" ");
		method.print(10);
		method.print('a');
		method.print(method);
	}

}
