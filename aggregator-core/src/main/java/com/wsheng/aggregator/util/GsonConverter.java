/**
 * 
 */
package com.wsheng.aggregator.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wsheng.aggregator.bean.LBMemberSource;
import com.wsheng.aggregator.bean.LBSource.LBSourceType;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class GsonConverter {
	
	public static Map<LBSourceType, Type> tokenTypes = new HashMap<>();
	
	private static List<LBSourceType> sourceTypes = new ArrayList<>();
	
	private static Gson gson = new Gson();
	
	// private Type genericType;
	
	static {
		// 1. Object: return key(String), value(Object:String actually)
		sourceTypes.add(LBSourceType.all);
		
		// 2. LBMemberSource: return key(String), value(LBMemberSource)
		sourceTypes.add(LBSourceType.lbmember_hash);
		
		// 3. Lbvirtualip_hash
		sourceTypes.add(LBSourceType.lbvirtualip_hash);
		
		initTypeTokens();
	}
	
	private static void initTypeTokens() {
		for (LBSourceType sourceType : sourceTypes) {
			Type tokenType = buildTokenType(sourceType);
			tokenTypes.put(sourceType, tokenType);
		}
	}
	
	/*public GsonConverter() {
		Type typeClass = getClass().getGenericSuperclass();
		genericType = ((ParameterizedType) typeClass).getActualTypeArguments()[0];
	}*/
	
	public static Map<String, String> json2Map(String jsonString) {
		Type type = new TypeToken<Map<String, String>>() {}.getType();
		Gson gson = new Gson();
		
		return gson.fromJson(jsonString, type);
	}
	
	public static Map<String, List<String>> json2Maps(String jsonString) {
		return  convert(LBSourceType.array, jsonString);
	}
	
	public static Map<String, Map<String, LBMemberSource>> json2LBMemberSourceMap(String jsonString) {
		return convert(LBSourceType.lbmember_hash, jsonString);
	}
	
	public static Map<String, Object> json2ObjMap(String jsonString) {
		return convert(LBSourceType.all, jsonString);
	}
	
	public static Map<String, String> json2TextMap(String jsonString) {
		return convert(LBSourceType.text, jsonString);
	}
	
	private static <T> T convert(LBSourceType type, String jsonString) {
		Type tokenType = buildTokenType(type);
		return gson.fromJson(jsonString, tokenType);
	}
	
	private static Type buildTokenType(LBSourceType type) {
		Type tokenType = null;
		switch (type) {
		case all:
			tokenType = new TypeToken<Object>() {
			}.getType();
			break;
		case lbmember_hash:
			tokenType = new TypeToken<Map<String, Map<String, LBMemberSource>>>() {}.getType();
			break;
		case array:
			tokenType = new TypeToken<List<String>>() {
			}.getType();
		case text:
			tokenType = new TypeToken<String>() {
			}.getType();
		default:
			break;
		}
		
		return tokenType;
	}
	
	/*private Type buildTypeToken(Type genericType) {
		Type typeToken = null;
		if (genericType.toString().endsWith(LBMemberSource.class.getSimpleName())) {
			typeToken = new TypeToken<Map<String, Map<String, LBMemberSource>>>() {
				private static final long serialVersionUID = 1L;}.getType();
		} else if (genericType.toString().endsWith(List.class.getSimpleName())) {
			typeToken = new TypeToken<List<String>>() {
				private static final long serialVersionUID = -2103792877225414843L;
			}.getType();
		} else {
			typeToken = new TypeToken<Object>() {
				private static final long serialVersionUID = -7435280462719909275L;
			}.getType();
		}
		
		return typeToken;
	}*/
	
}
