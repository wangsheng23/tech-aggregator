/**
 * 
 */
package com.wsheng.aggregator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;



/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class IOUtils {
	
	
	public static String file2String(String filePath) throws Exception {
		InputStream in = new FileInputStream(new File(filePath));
		
		try {
			return org.apache.commons.io.IOUtils.toString(in);
		} finally {
			org.apache.commons.io.IOUtils.closeQuietly(in);
		}
	}
	
	public static List<String> readLines(String filePath) throws Exception {
		InputStream in = new FileInputStream(new File(filePath));
		
		try {
			return org.apache.commons.io.IOUtils.readLines(in);
		} finally {
			org.apache.commons.io.IOUtils.closeQuietly(in);
		}
	}
	
	public static void main(String[] args) throws Exception {
		String filePath = "src/main/resources/file.txt";
		List<String> lines = readLines(filePath);
		for (String currentLine : lines) {
			if (currentLine.contains("==== AS:")) {
				System.out.println(currentLine);
			}
		}
		
	}

}
