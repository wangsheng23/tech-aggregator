/**
 * 
 */
package com.wsheng.aggregator.generic;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class GenericsFoo<T> {
	private T x;
	
	public GenericsFoo(T x) {
		this.x = x;
	}

	public T getX() {
		return x;
	}

	public void setX(T x) {
		this.x = x;
	}
	
	public static void main(String[] args) {
		GenericsFoo<String> strFoo = new GenericsFoo<String>("Hello Generics");
		GenericsFoo<Double> douFoo = new GenericsFoo<Double>(0d);
		GenericsFoo<Object> objFoo = new GenericsFoo<Object>(new Object());
		
		System.out.println("strFoo.getX=" + strFoo.getX());
        System.out.println("douFoo.getX=" + douFoo.getX());
        System.out.println("objFoo.getX=" + objFoo.getX());
	}
}


