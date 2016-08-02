package com.wsheng.aggregator.util;

import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;



/**
 * <p>
 * HttpUtil mainly used to get the Objects By the given WebService Requests URL
 * </p>
 *
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class HttpUtils {

    private static final String MIME_JSON = MediaType.APPLICATION_JSON;
    private static final Logger logger = LogManager.getLogger(HttpUtils.class);

    private static Client client;

    // private static CloseableHttpClient apacheClient;

    static {

        // ---- jersey handle Object ----
        /*
         * To resolve the exception:
		 *  A message body writer for Java type,
		 *  class com.ybei.montage.pm.model.NucleonRule,
		 *  and MIME media type, application/json, was not found
		 */
        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        // defaultClientConfig.getFeatures().put(FEATURE_POJO_MAPPING)
        // defaultClientConfig.getClasses().add(GsonJsonProvider.class);
        client = Client.create(defaultClientConfig);
        client.setConnectTimeout(3000);
        client.setReadTimeout(3000);
        // client.addFilter(new LoggingFilter(System.out));

        // apacheClient = HttpClients.createDefault();
    }

    public static String getJsonString(String url) throws Exception {

        Client client = Client.create();

        WebResource webResource = client.resource(url);

        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        String output = response.getEntity(String.class);

        return output;
    }

    /**
     * Call restful Get request to get the vo as JSON format
     *
     * @return
     * @throws Exception
     */
    public static String getResource(String resourceUrl, MediaType mediaType) throws Exception {

        return getResource(resourceUrl, null, mediaType);
    }

    public static String getResource(String resourceUrl, Map<String, String> params) throws Exception {

        return getResource(resourceUrl, params, MediaType.APPLICATION_JSON_TYPE);
    }

    /**
     * Call restful Get request to get the vo as JSON format
     *
     * @return
     * @throws Exception
     */
    public static String getResource(String resourceUrl, Map<String, String> params, MediaType mediaType) throws Exception {

        // the request web resources
        WebResource webResource = findWebResource(resourceUrl, params);

        ClientResponse response = webResource.accept(mediaType).get(ClientResponse.class);

        String output = response.getEntity(String.class);

        return output;
    }

    /**
     * Put a request Entity to the web resource
     *
     * @param resourceUrl
     * @throws Exception
     */
    public static void put2Resource(String resourceUrl, Object requestEntity) throws Exception {
        put2Resource(resourceUrl, null, requestEntity);
    }

    /**
     * Put a request Entity to the web resource
     *
     * @param resourceUrl
     * @param params
     * @throws Exception
     */
    public static void put2Resource(String resourceUrl, Map<String, String> params, Object requestEntity) throws Exception {
        WebResource webResource = findWebResource(resourceUrl, params);

        webResource.type(MIME_JSON).accept(MIME_JSON).put(ClientResponse.class, requestEntity);

    }


    /**
     * Post an entity to the web resource
     *
     * @param resourceUrl
     * @param requestEntity
     * @throws Exception
     */
    public static ClientResponse post2Resource(String resourceUrl, Object requestEntity) throws Exception {
        return post2Resource(resourceUrl, null, requestEntity);
    }

    /**
     * Post an entity to the web resource
     *
     * @param resourceUrl
     * @param params
     * @param requestEntity
     * @throws Exception
     */
    public static ClientResponse post2Resource(String resourceUrl, Map<String, String> params, Object requestEntity) throws Exception {
        WebResource webResource = findWebResource(resourceUrl, params);

        ClientResponse response = webResource.type(MIME_JSON).accept(MIME_JSON).post(ClientResponse.class, requestEntity);

        return response;
    }

    /**
     * Post object
     * Required: Add annotation to the request request entity, @XmlRootElement
     *
     * @param requestEntity
     * @throws Exception
     */
    public static void executeReq(String requestUrl, Object requestEntity) throws Exception {
        WebResource webResource = findWebResource(requestUrl, null);

        // Method1: 
        // webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, requestEntity);
        // post2Resource(resourceUrl, requestEntity);

        // Method2: Post the entity directly
        webResource.entity(requestEntity).post();
    }

    /**
     * Post Object and customize the request and response type
     * 
     * @param resourceUrl
     * @param params
     * @param requestEntity
     * @param sendType
     * @param acceptType
     * @return
     * @throws Exception
     */
    public static String post2Resource(String resourceUrl, Map<String, String> params,
                                       Object requestEntity, MediaType sendType, MediaType acceptType)
            throws Exception {
        WebResource webResource = findWebResource(resourceUrl, params);

        ClientResponse response = webResource.type(sendType).accept(acceptType).post(ClientResponse.class, requestEntity);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed: HTTP error code:" + response.getStatus());
        }
        return response.getEntity(String.class);
    }



    private static WebResource findWebResource(String resourceUrl, Map<String, String> params) {
        // the request web resources
        WebResource webResource = client.resource(resourceUrl);

        if (params != null && params.size() > 0) { // build the parameters
            MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
            for (Map.Entry<String, String> p : params.entrySet()) {
                queryParams.add(p.getKey(), p.getValue());
            }
            webResource = webResource.queryParams(queryParams);
        }

        logger.info("Request URI: " + webResource.getURI());
        return webResource;
    }
}
