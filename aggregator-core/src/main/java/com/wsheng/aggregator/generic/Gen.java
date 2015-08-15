package com.wsheng.aggregator.generic;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class Gen<T> {

	// 定义泛型成员变量
	private T t;
	
	public Gen(T t) {
		this.t = t;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
	public void showType() {
		System.out.println("T的实际类型是： " + t.getClass().getName());
	}
	
	public static void main(String[] args) {
		// 定义一个泛型类Gen的一个Integer的版本
		Gen<Integer> intObj = new Gen<Integer>(0);
		intObj.showType();
		
		int i = intObj.getT();
		System.out.println(" value = " + i);
		System.out.println(" ====================== ");
		
		//定义泛型类Gen的一个String的版本
		Gen<String>strObj = new Gen<String>("Hello Gen!");
		strObj.showType();
		String s = strObj.getT();
		System.out.println(" value = " + s);
	}
	
}
 