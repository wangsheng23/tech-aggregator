package com.wsheng.aggregator.util;

import org.apache.commons.lang3.StringUtils;

import com.wsheng.aggregator.enums.NetworkAddressType;



/**
 * 
 * @author Josh Wang(Sheng)
 * @email swang6@ebay.com
 */
public class IPAddressUtil {
	
	
	@SuppressWarnings("restriction")
	public static String getIpVersion(String host) {
		if (StringUtils.isNotEmpty(host)) {
			if (sun.net.util.IPAddressUtil.isIPv4LiteralAddress(host)) {
				return NetworkAddressType.IPv4.name();
			} else if (sun.net.util.IPAddressUtil.isIPv6LiteralAddress(host)) {
				return NetworkAddressType.IPv6.name();
			}
		}
		
		return NetworkAddressType.None.name();
	}
	
	public static boolean isIpAddress (String host) {
		
		if (NetworkAddressType.None.name().equals(getIpVersion(host))) {
			return false;
		}
		
		return true;
	}
}
