/**
 * 
 */
package com.wsheng.aggregator.catchexcept;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class CatchFinally {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("8888888888888" + test());

	}
	
	
	private static String test() {
		String test = "";
		FileReader fReader = null;
		try {
			fReader = new FileReader(new File("/home/test"));
		} catch (FileNotFoundException e) {
			System.out.println(" ---- catch---- ");
			return "catchcatch";
		} finally {
			try {
				System.out.println(" before close---");
				fReader.close();
				System.out.println(" after close---");
			} catch (IOException e) {
				System.out.println(" exception when closing---");
			}
			System.out.println( " ---- finally ---- ");
		}
		
		return test;
	}

}
