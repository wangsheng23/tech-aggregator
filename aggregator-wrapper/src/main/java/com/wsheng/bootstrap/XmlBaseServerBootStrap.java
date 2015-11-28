/**
 * 
 */
package com.wsheng.bootstrap;

import server.XmlWebServer;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class XmlBaseServerBootStrap {
	
	public static void main(String[] args) throws Exception {
		XmlWebServer server = new XmlWebServer(8080);
		server.start();
	}

}
