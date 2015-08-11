package com.wsheng.aggregator.thread.performance.visualvm;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class PermGenTest {
	
	private static List<Object> logObjectList = new ArrayList<Object>();
	
	public static void main(String[] args) throws Exception {
		permLeak();
	}
	
	private static void permLeak() throws Exception {
		for (int i = 0; i < 1000; i++) {
			URL[] urls = getUrls();
			URLClassLoader urlClassLoader = new URLClassLoader(urls, null);
			// 从指定的jar包中load LogFactory并构造一个LogFactory的对象
			Class<?> logfClass = Class.forName("org.apache.commons.logging.LogFactory", true, urlClassLoader);
			Method getLog = logfClass.getMethod("getLog", String.class);
			Object result = getLog.invoke(logfClass, "PermGenTest"); // String.class指定了getLog传入String参数
			logObjectList.add(result);
			
			System.out.println(i + ": " + result);
		}
	}
	
	private static URL[] getUrls() throws MalformedURLException {
		File libDir = new File("C:/Users/wangsheng/.m2/repository/commons-logging/commons-logging/1.1.1");
		File[] subFiles = libDir.listFiles();
		int count = subFiles.length;
		URL[] urls = new URL[count];
		for (int i = 0; i < count; i++) {
			urls[i] = subFiles[i].toURI().toURL();
		}
		
		return urls;
	}

}
 