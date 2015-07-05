/**
 * 
 */
package com.wsheng.aggregator.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class Ping {

	public static boolean pingable(String host) {
		int timeout = 5000; // 5s to timeout
		boolean status = false;
		try {
			status = InetAddress.getByName(host).isReachable(timeout);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public static void main(String[] args) {
		System.out.println(pingable("tmrslc.vip.ebay.com"));
		System.out.println(pingable("D-SHC-00802132"));
		System.out.println(pingable("10.249.65.147"));
	}
}
