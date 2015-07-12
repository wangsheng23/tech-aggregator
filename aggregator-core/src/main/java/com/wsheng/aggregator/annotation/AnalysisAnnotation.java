/**
 * 
 */
package com.wsheng.aggregator.annotation;

import java.lang.reflect.Method;

/**
 * 
 *在运行时分析处理annotation类型的信息
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class AnalysisAnnotation {
	
	public static void main(String[] args) {
		try {
			
			// 通过运行时反射API获得annotation信息
			Class<?> rtClass = Class.forName("com.wsheng.aggregator.annotation.Utility");
			Method[] methods = rtClass.getMethods();
			
			boolean descriptionExist = rtClass.isAnnotationPresent(Description.class);
			if (descriptionExist) {
				Description description = (Description)rtClass.getAnnotation(Description.class);
				System.out.println("Utility's Description --- > " + description.value());
				
				for (Method method : methods) {
					if (method.isAnnotationPresent(Author.class)) {
						Author author = (Author)method.getAnnotation(Author.class);
						System.out.println("Utility's Author ---> " + author.name() + " from " + author.group());
					}
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
