package com.wsheng.aggregator.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class CollectionGenFoo<T extends Collection<?>> {
	
	private T x;
	
	public CollectionGenFoo(T x) {
		this.x = x;
	}

	public T getX() {
		return x;
	}

	public void setX(T x) {
		this.x = x;
	}

	public static void main(String[] args) {
		/*CollectionGenFoo<ArrayList<String>> listFoo = null;
		listFoo = new CollectionGenFoo<ArrayList<String>>(new ArrayList<String>());*/
		
		CollectionGenFoo<Collection<String>> listFoo = new CollectionGenFoo<Collection<String>>(new ArrayList<String>());
		
		System.out.println("实例化成功！" + listFoo);
		
	}
}
