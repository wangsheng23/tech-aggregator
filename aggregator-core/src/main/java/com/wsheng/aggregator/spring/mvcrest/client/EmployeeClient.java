package com.wsheng.aggregator.spring.mvcrest.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.wsheng.aggregator.util.CommonsUtil;


public class EmployeeClient {

	private static final String preUrl = "http://localhost:8080/spring-mvc-rest/rest/";

	/**
	 * Add an item.
	 * 
	 * @param map
	 * @param url
	 * @param dataType
	 * @return
	 */
	public static String postJson(Map<String, String> map, String url, String dataType) {
		url = preUrl + url;
		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(CommonsUtil.converDataType(dataType));
		requestHeaders.setAccept(mediaTypes);
		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(map, requestHeaders);
		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
	}

	/**
	 * Update An item.
	 * 
	 * @param map
	 * @param url
	 * @param dataType
	 * @return
	 */
	public static Boolean putJson(Map<String, String> map, String url, String dataType) {
		url = preUrl + url;
		HttpHeaders headers = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(CommonsUtil.converDataType(dataType));
		headers.setAccept(acceptableMediaTypes);

		HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(map, headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
		return true;
	}

	public static String getjson(String url, String id, String dataType) {
		url = preUrl + url + id;
		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(CommonsUtil.converDataType(dataType));
		requestHeaders.setAccept(mediaTypes);
		HttpEntity<String> requestEntity = new HttpEntity<String>(requestHeaders);
		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();
		// Make the HTTP GET request to the Basic Auth protected URL
		return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class).getBody();
	}

	public static Boolean deleteJson(String url, String id) {
		url = preUrl + url + id;
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(url, HttpMethod.DELETE, null, Boolean.class).getBody();
	}

}
