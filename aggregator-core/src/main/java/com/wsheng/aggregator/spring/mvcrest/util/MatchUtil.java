package com.wsheng.aggregator.spring.mvcrest.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class MatchUtil {
	public static boolean eq(String s1, String s2) {
		if (StringUtils.isEmpty(s1)) { 
			if (StringUtils.isEmpty(s2)) { 
				return true;
			} else {
				return false;
			}
		}
		
		if (StringUtils.isNotEmpty(s2)) {
			return s1.trim().equalsIgnoreCase(s2.trim());
		} else {
			return s1.trim().equalsIgnoreCase(s2);
		}
		
		 
	}
	
	public static boolean eqDefined(String s1, String s2, String[][] defined) {
		if (eq(s1, s2)) {
			return true;
		}
		for (String[] def : defined) {
			if (s1.equals(def[0]) && s2.equals(def[1])) {
				return true;
			}
		}
		return false;
	}

}
