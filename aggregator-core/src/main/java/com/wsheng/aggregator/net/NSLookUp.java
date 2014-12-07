/**
 * 
 */
package com.wsheng.aggregator.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 * 
 */
public class NSLookUp {
	
	
	public static String forwardLookUp(String ip) {
		try {
			InetAddress address = InetAddress.getByName(ip);
			
			return address.getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String reverseLookUp(String ip) {
		try {
			InetAddress address = InetAddress.getByName(ip);
			
			return address.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(forwardLookUp("10.89.68.39"));
		System.out.println(reverseLookUp("slcdb2167.slc.ebay.com"));
	}

}
