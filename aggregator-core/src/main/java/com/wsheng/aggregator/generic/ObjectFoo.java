package com.wsheng.aggregator.generic;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class ObjectFoo {

	private Object x;
	
	public ObjectFoo(Object x) {
		this.x = x;
	}

	public Object getX() {
		return x;
	}

	public void setX(Object x) {
		this.x = x;
	}

	public static void main(String[] args) {
		ObjectFoo strFoo = new ObjectFoo(new StringFoo("Hello Generics!"));
		ObjectFoo doubleFoo = new ObjectFoo(new DoubleFoo(0d));
		ObjectFoo objectFoo = new ObjectFoo(new Object());
		
		System.out.println("strFoo.getX=" + (StringFoo) strFoo.getX());
        System.out.println("douFoo.getX=" + (DoubleFoo) doubleFoo.getX());
        System.out.println("objFoo.getX=" + objectFoo.getX());
	}
}
