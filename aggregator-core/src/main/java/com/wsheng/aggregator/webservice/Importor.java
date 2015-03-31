package com.wsheng.aggregator.webservice;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class Importor {

	static {
		TrustManager easyTrustManager = new EasyTrustManager();
		SSLContext sslcontext;
		try {
			sslcontext = SSLContext.getInstance("TLS");
			sslcontext
			.init(null, new TrustManager[] { easyTrustManager }, null);
			SSLContext.setDefault(sslcontext);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
