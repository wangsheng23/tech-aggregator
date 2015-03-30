package com.wsheng.aggregator.webservice;

import javax.net.ssl.X509TrustManager;

/**
 * This code used to call https webservice, its only need to init once in the application
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 *
 */
public class EasyTrustManager implements X509TrustManager{
	public void checkClientTrusted(
            java.security.cert.X509Certificate[] arg0, String arg1)
            throws java.security.cert.CertificateException {
        // TODO Auto-generated method stub
    }
    public void checkServerTrusted(
            java.security.cert.X509Certificate[] arg0, String arg1)
            throws java.security.cert.CertificateException {
        // TODO Auto-generated method stub
    }
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        // TODO Auto-generated method stub
        return null;
    }
}
