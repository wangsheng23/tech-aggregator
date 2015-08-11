/**
 * 
 */
package com.wsheng.aggregator.jmx;

import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class TestJMXClient {
	
	public static void main(String[] args) throws Exception {
		String jndiPath = "jmxrmi";
		String serverHost = "192.168.244.88";
		String serverPort = "7777";
		 // url=service:jmx:rmi:///jndi/rmi://192.168.8.7:8088/jmxrmi 
		
		String jmxurl = "service:jmx:rmi:///jndi/rmi://" + serverHost + ":"  
                 + serverPort + "/" + jndiPath;
		System.out.println("jmxurl:" + jmxurl); 
		Map<String, Object> enviMap = new HashMap<String, Object>(); 
		
		JMXServiceURL url = new JMXServiceURL(jmxurl);  
		
		JMXConnector connector = JMXConnectorFactory.connect(url, enviMap);
		MBeanServerConnection mbsc = connector.getMBeanServerConnection();  
        System.out.println("successful connected " + mbsc.getDefaultDomain());  
        connector.close();  
        System.out.println("close connect"); 
		 
	}

}
