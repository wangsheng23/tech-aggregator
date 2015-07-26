/**
 * 
 */
package com.wsheng.aggregator.basic;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class ObjPass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ObjPass pass = new ObjPass();
		Person person = pass.new Person();
		person.age = 10;
		
		System.out.println("Before pass : main 方法中的age = " + person.age);
		pass.passTest(person);
		
		System.out.println("After pass : main 方法中的age = " + person.age);
		

	}
	
	public void passTest (Person person) {
		person.age = 20;
		System.out.println("passTest中的age = " + person.age);
	}
	
	class Person {
		public int age = 0;
	}

}
