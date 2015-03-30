package com.wsheng.aggregator.spring.mvcrest.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.wsheng.aggregator.util.CommonsUtil;


public class MetaclassClient<T> {

	private static final String preUrl = "http://localhost:8080/spring-mvc-rest/rest/";

	/**
	 * Add an item to the url with class type T.
	 * @param item
	 * @param url
	 * @param dataType
	 * @return
	 */
	public String postJson(T item, String url, String dataType) {
		url = preUrl + url;
		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(CommonsUtil.converDataType(dataType));
		requestHeaders.setAccept(mediaTypes);
		HttpEntity<T> requestEntity = new HttpEntity<T>(item, requestHeaders);

		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
	}

	/**
	 * Update an item to the url with class type T.
	 * @param item
	 * @param url
	 * @return
	 */
	public String putJson(T item, String url, String dataType) {
		url = preUrl + url;
		HttpHeaders headers = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(CommonsUtil.converDataType(dataType));
		headers.setAccept(acceptableMediaTypes);

		HttpEntity<T> entity = new HttpEntity<T>(item, headers);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(url, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	/**
	 * Get an item from the url with class type T.
	 * @param url
	 * @param id
	 * @param dataType
	 * @param c
	 * @return
	 */
	public T getjson(String url, String id, String dataType, Class<T> c) {
		url = preUrl + url + id;
		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(CommonsUtil.converDataType(dataType));
		requestHeaders.setAccept(mediaTypes);
		HttpEntity<T> requestEntity = new HttpEntity<T>(requestHeaders);

		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();
		// Make the HTTP GET request to the Basic Auth protected URL
		return restTemplate.exchange(url, HttpMethod.GET, requestEntity, c).getBody();
	}

	/**
	 * Delete an item with id from the url.
	 * @param url
	 * @param id
	 * @return
	 */
	public static String deleteJson(String url, String id) {
		url = preUrl + url + id;
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(url, HttpMethod.DELETE, null, String.class).getBody();
	}
}
