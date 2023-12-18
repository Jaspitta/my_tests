package com.example.demo.utils;

import com.google.gson.Gson;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This class can be used each time it is necessary to invoke a REST web
 * service.
 *
 * @author michele.vigilante
 * @version 1.0
 */
public class RESTClient<T, V> {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(
            RESTClient.class.getName());
    /**
     * Input object to be sent.
     */
    private final T input;
    /**
     * Map of HTTP headers to be sent.
     */
    private final Map<String, String> headers;

    /**
     * Map of query string parameters to be passed.
     */
    private final Map<String, String> queryString;

    /**
     * Type of response object.
     */
    private final Class<V> outputType;

    private final ParameterizedTypeReference<V> outputTypeRef;
    /**
     * upper limit for log string
     */
    int bufferlimit = 2048;
    /**
     * Endpoint of REST service.
     */
    private String url;
    private RestTemplate client;
    private CloseableHttpClient httpClient;

    /**
     * Class constructor for RESTClient class.
     *
     * @param input       Input object to be sent as body
     * @param url         Endpoint of REST service
     * @param headers     HTTP request headers
     * @param queryString Query string parameters
     * @param outputType  Class of returned entity
     */
    public RESTClient(T input, String url,
                      Map<String, String> headers,
                      Map<String, String> queryString, Class<V> outputType) {
        this.input = input;
        this.url = url;
        this.headers = headers;
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        httpRequestFactory.setConnectionRequestTimeout(10000);
//        httpRequestFactory.setConnectTimeout(10000);
//        httpRequestFactory.setReadTimeout(10000);
        this.client = new RestTemplate(httpRequestFactory);

        for (HttpMessageConverter<?> myConverter : this.client.getMessageConverters()) {
            if (myConverter instanceof MappingJackson2HttpMessageConverter) {
                List<MediaType> myMediaTypes = new ArrayList<MediaType>();
                myMediaTypes.addAll(myConverter.getSupportedMediaTypes());
                myMediaTypes.add(MediaType.parseMediaType("text/javascript; charset=utf-8"));
                ((MappingJackson2HttpMessageConverter) myConverter).setSupportedMediaTypes(myMediaTypes);
            }
        }
        this.outputType = outputType;
        this.outputTypeRef = null;
        this.queryString = queryString;
    }

    public RESTClient(T input, String url,
                      Map<String, String> headers,
                      Map<String, String> queryString, ParameterizedTypeReference<V> outputTypeParametrized) {
        this.input = input;
        this.url = url;
        this.headers = headers;
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(240000);
        httpRequestFactory.setConnectTimeout(240000);
        httpRequestFactory.setReadTimeout(240000);
        this.client = new RestTemplate(httpRequestFactory);
        for (HttpMessageConverter<?> myConverter : this.client.getMessageConverters()) {
            if (myConverter instanceof MappingJackson2HttpMessageConverter) {
                List<MediaType> myMediaTypes = new ArrayList<MediaType>();
                myMediaTypes.addAll(myConverter.getSupportedMediaTypes());
                myMediaTypes.add(MediaType.parseMediaType("text/javascript; charset=utf-8"));
                ((MappingJackson2HttpMessageConverter) myConverter).setSupportedMediaTypes(myMediaTypes);
            }
        }
        this.outputType = null;
        this.outputTypeRef = outputTypeParametrized;
        this.queryString = queryString;
    }

    public RESTClient(T input, String url,
                      Map<String, String> headers,
                      Map<String, String> queryString, ParameterizedTypeReference<V> outputTypeParametrized, int timeout) {
        this.input = input;
        this.url = url;
        this.headers = headers;
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(timeout);
        httpRequestFactory.setConnectTimeout(timeout);
        httpRequestFactory.setReadTimeout(timeout);
        this.client = new RestTemplate(httpRequestFactory);
        for (HttpMessageConverter<?> myConverter : this.client.getMessageConverters()) {
            if (myConverter instanceof MappingJackson2HttpMessageConverter) {
                List<MediaType> myMediaTypes = new ArrayList<MediaType>();
                myMediaTypes.addAll(myConverter.getSupportedMediaTypes());
                myMediaTypes.add(MediaType.parseMediaType("text/javascript; charset=utf-8"));
                ((MappingJackson2HttpMessageConverter) myConverter).setSupportedMediaTypes(myMediaTypes);
            }
        }
        this.outputType = null;
        this.outputTypeRef = outputTypeParametrized;
        this.queryString = queryString;
    }

    /**
     * Class constructor for RESTClient class.
     *
     * @param input       Input object to be sent as body
     * @param url         Endpoint of REST service
     * @param headers     HTTP request headers
     * @param queryString Query string parameters
     * @param outputType  Class of returned entity
     * @param proxyAddr   Address of proxy server
     * @param proxyPort   Port of proxy server
     */
    public RESTClient(T input, String url,
                      Map<String, String> headers,
                      Map<String, String> queryString, Class<V> outputType,
                      String proxyAddr, int proxyPort) {
        this.input = input;
        this.url = url;
        this.headers = headers;
        SimpleClientHttpRequestFactory fact = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddr, proxyPort));
        fact.setProxy(proxy);
        this.client = new RestTemplate(fact);
        for (HttpMessageConverter<?> myConverter : this.client.getMessageConverters()) {
            if (myConverter instanceof MappingJackson2HttpMessageConverter) {
                List<MediaType> myMediaTypes = new ArrayList<MediaType>();
                myMediaTypes.addAll(myConverter.getSupportedMediaTypes());
                myMediaTypes.add(MediaType.parseMediaType("text/javascript; charset=utf-8"));
                ((MappingJackson2HttpMessageConverter) myConverter).setSupportedMediaTypes(myMediaTypes);
            }
        }
        this.outputType = outputType;
        this.outputTypeRef = null;
        this.queryString = queryString;
    }


    /**
     * Class constructor for RESTClient class.
     *
     * @param input              Input object to be sent as body
     * @param url                Endpoint of REST service
     * @param headers            HTTP request headers
     * @param queryString        Query string parameters
     * @param outputType         Class of returned entity
     * @param useSSL             specify if MTLS must or not be enabled
     * @param keyStore           client certificate
     * @param keyStorePassword   client keystore password
     * @param trustStore         server root ca certificate
     * @param trustStorePassword server keystore password
     * @param keyPassword        client private key // Q^F^VwI@e8^n * FgRpbBwb7
     */
    public RESTClient(T input, String url,
                      Map<String, String> headers,
                      Map<String, String> queryString, Class<V> outputType, boolean useSSL,
                      Resource keyStore, String keyStorePassword, String keyPassword,
                      Resource trustStore, String trustStorePassword) {
        this.input = input;
        this.url = url;
        this.headers = headers;
        if (useSSL) {
            try {

                HttpClient clientHttp = HttpClients.custom()
                        .setSSLContext(SSLContextBuilder.create().setProtocol("TLSv1.2").setKeyStoreType("JKS")
                                .loadKeyMaterial(keyStore.getFile(), keyStorePassword.toCharArray(),
                                        keyPassword.toCharArray())
                                .loadTrustMaterial(trustStore.getFile(), trustStorePassword.toCharArray()).build())
                        .build();
                HttpComponentsClientHttpRequestFactory requestFactory
                        = new HttpComponentsClientHttpRequestFactory();
                requestFactory.setHttpClient(clientHttp);
                this.client = new RestTemplate(requestFactory);
            } catch (IOException | NoSuchAlgorithmException | KeyStoreException
                     | UnrecoverableKeyException | CertificateException
                     | KeyManagementException ex) {
                LOGGER.error(ex.getMessage());
                //Logger.getLogger(RESTClient.class.getName()).log(Level.SEVERE, null, ex);
                this.client = new RestTemplate();
            }
        } else {
            this.client = new RestTemplate();
        }

        for (HttpMessageConverter<?> myConverter : this.client.getMessageConverters()) {
            if (myConverter instanceof MappingJackson2HttpMessageConverter) {
                List<MediaType> myMediaTypes = new ArrayList<MediaType>();
                myMediaTypes.addAll(myConverter.getSupportedMediaTypes());
                myMediaTypes.add(MediaType.parseMediaType("text/javascript; charset=utf-8"));
                ((MappingJackson2HttpMessageConverter) myConverter).setSupportedMediaTypes(myMediaTypes);
            }
        }
        this.outputType = outputType;
        this.outputTypeRef = null;
        this.queryString = queryString;
    }

    /**
     * This is the private method internally called to implement all the HTTP
     * method requests.
     *
     * @param method HTTP method to be used
     * @return The response of service call (ResponseEntity)
     * @throws URISyntaxException thrown if the URL is not correctly formed
     */
    private ResponseEntity<V> execute(HttpMethod method) throws URISyntaxException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (queryString != null && !queryString.isEmpty()) {
            for (String key : queryString.keySet()) {
                builder = builder.queryParam(key, queryString.get(key));
            }
        }
        url = builder.toUriString();
        URI uri = new URI(builder.toUriString());
        HttpHeaders hdrs = new HttpHeaders();
        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                hdrs.add(key, headers.get(key));
            }
        }

        HttpEntity<T> requestEntity = new HttpEntity<>(input, hdrs);
        ResponseEntity<V> result = null;

        StringBuilder log = new StringBuilder();
        Date startTime = new Date();
        Date endTime = null;
        log.append("OUTCOMING REST CALL = ");
        log.append("Method: " + method.toString());
        log.append(". Url: " + url);
        log.append(". Headers request: " + hdrs);

        String requestBody = "empty";

        if (requestEntity.getBody() != null) {
            if (requestEntity.getBody().toString().length() > bufferlimit) {
                requestBody = requestEntity.getBody().toString().substring(0, bufferlimit);
            } else {
                requestBody = requestEntity.getBody().toString();
            }
        }

        try {
            if (outputType != null) {
                result = client.exchange(uri, method, requestEntity, outputType);
            } else if (outputTypeRef != null) {
                result = client.exchange(uri, method, requestEntity, outputTypeRef);
            }
            requestBody = new Gson().toJson(requestBody);
            endTime = new Date();
            log.append(". Execution time: " + (endTime.getTime() - startTime.getTime()) + " ms");
            log.append(". Body request: " + requestBody);
        } catch (Exception ex) {
            endTime = new Date();
            log.append(". Execution time: " + (endTime.getTime() - startTime.getTime()) + " ms");
            log.append(". Body request: " + requestBody);
            //log.append("###### OUTCOMING - Rest call END ######");
            LOGGER.info(log.toString());
            throw ex;
        }

        if (result != null) {
            log.append(". Headers response: " + result.getHeaders());
            if (result.toString().length() > bufferlimit) {
                log.append(". Result: " + result.toString().substring(0, bufferlimit));
            } else {
                log.append("- Result: " + result);
            }
        }
        //log.append("###### OUTCOMING - Rest call END ######");

        LOGGER.info(log.toString());
        return result;
    }

    /**
     * Execute a GET HTTP request.
     *
     * @return The response of service call (ResponseEntity)
     * @throws URISyntaxException thrown if the URL is not correctly formed.
     */
    public ResponseEntity<V> executeGET() throws URISyntaxException {
        return execute(HttpMethod.GET);
    }

    public ResponseEntity<V> executeGETWithRetry(Integer retryNumber, Long waitSeconds) throws URISyntaxException, InterruptedException {
        Exception exloop = null;
        if (retryNumber == null) {
            retryNumber = 3;
        }
        if (waitSeconds == null) {
            waitSeconds = 30L;
        }
        while (exloop == null && retryNumber > 0) {
            retryNumber--;
            try {
                exloop = null;
                return execute(HttpMethod.GET);
            } catch (Exception ex) {
                LOGGER.info("Call to {} with method {} failed with message: {}", url, HttpMethod.GET, ex.getMessage());
                if (ex instanceof HttpClientErrorException) {
                    HttpClientErrorException exHttp = (HttpClientErrorException) ex;
                    if (exHttp.getRawStatusCode() < 500 && exHttp.getRawStatusCode() != 401) {
                        throw ex;
                    }
                }
                LOGGER.info("Trying again...");
                Thread.sleep(waitSeconds * 1000);
                exloop = ex;
                return executeGETWithRetry(retryNumber, waitSeconds);
            }
        }
        return null;
    }

    /**
     * Execute a POST HTTP request.
     *
     * @return The response of service call (ResponseEntity)
     * @throws URISyntaxException thrown if the URL is not correctly formed.
     */
    public ResponseEntity<V> executePOST() throws URISyntaxException {
        return execute(HttpMethod.POST);
    }

    public ResponseEntity<V> executePOSTWithRetry(Integer retryNumber, Long waitSeconds) throws URISyntaxException, InterruptedException {
        Exception exloop = null;
        if (retryNumber == null) {
            retryNumber = 3;
        }
        if (waitSeconds == null) {
            waitSeconds = 30L;
        }
        while (exloop == null && retryNumber > 0) {
            retryNumber--;
            try {
                exloop = null;
                return execute(HttpMethod.POST);
            } catch (Exception ex) {
                LOGGER.info("Call to {} with method {} failed with message: {}", url, HttpMethod.POST, ex.getMessage());
                if (ex instanceof HttpClientErrorException) {
                    HttpClientErrorException exHttp = (HttpClientErrorException) ex;
                    if (exHttp.getRawStatusCode() < 500 && exHttp.getRawStatusCode() != 401) {
                        throw ex;
                    }
                }
                LOGGER.info("Trying again...");
                Thread.sleep(waitSeconds * 1000);
                exloop = ex;
                return executePOSTWithRetry(retryNumber, waitSeconds);
            }
        }
        return null;
    }

    /**
     * Execute a PUT HTTP request.
     *
     * @return The response of service call (ResponseEntity)
     * @throws URISyntaxException thrown if the URL is not correctly formed
     */
    public ResponseEntity<V> executePUT() throws URISyntaxException {
        return execute(HttpMethod.PUT);
    }

    public ResponseEntity<V> executePUTWithRetry(Integer retryNumber, Long waitSeconds) throws URISyntaxException, InterruptedException {
        Exception exloop = null;
        if (retryNumber == null) {
            retryNumber = 3;
        }
        if (waitSeconds == null) {
            waitSeconds = 30L;
        }
        while (exloop == null && retryNumber > 0) {
            retryNumber--;
            try {
                exloop = null;
                return execute(HttpMethod.PUT);
            } catch (Exception ex) {
                LOGGER.info("Call to {} with method {} failed with message: {}", url, HttpMethod.PUT, ex.getMessage());
                if (ex instanceof HttpClientErrorException) {
                    HttpClientErrorException exHttp = (HttpClientErrorException) ex;
                    if (exHttp.getRawStatusCode() < 500 && exHttp.getRawStatusCode() != 401) {
                        throw ex;
                    }
                }
                LOGGER.info("Trying again...");
                Thread.sleep(waitSeconds * 1000);
                exloop = ex;
                return executePUTWithRetry(retryNumber, waitSeconds);
            }
        }
        return null;
    }

    /**
     * Execute a PATCH HTTP request.
     *
     * @return The response of service call (ResponseEntity)
     * @throws URISyntaxException thrown if the URL is not correctly formed
     */
    public ResponseEntity<V> executePATCH() throws URISyntaxException {
    	/*HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    	requestFactory.setConnectTimeout(3000);
    	requestFactory.setReadTimeout(3000);
    	this.client.setRequestFactory(requestFactory);*/
        return execute(HttpMethod.PATCH);
    }


    public ResponseEntity<V> executePATCHWithRetry(Integer retryNumber, Long waitSeconds) throws URISyntaxException, InterruptedException {
    	/*HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    	requestFactory.setConnectTimeout(3000);
    	requestFactory.setReadTimeout(3000);
    	this.client.setRequestFactory(requestFactory);*/
        Exception exloop = null;
        if (retryNumber == null) {
            retryNumber = 3;
        }
        if (waitSeconds == null) {
            waitSeconds = 30L;
        }
        while (exloop == null && retryNumber > 0) {
            retryNumber--;
            try {
                exloop = null;
                return execute(HttpMethod.PATCH);
            } catch (Exception ex) {
                LOGGER.info("Call to {} with method {} failed with message: {}", url, HttpMethod.PATCH, ex.getMessage());
                if (ex instanceof HttpClientErrorException) {
                    HttpClientErrorException exHttp = (HttpClientErrorException) ex;
                    if (exHttp.getRawStatusCode() < 500 && exHttp.getRawStatusCode() != 401) {
                        throw ex;
                    }
                }
                LOGGER.info("Trying again...");
                Thread.sleep(waitSeconds * 1000);
                exloop = ex;
                return executePATCHWithRetry(retryNumber, waitSeconds);
            }
        }
        return null;
    }

    /**
     * Execute a DELETE HTTP request.
     *
     * @return The response of service call (ResponseEntity)
     * @throws URISyntaxException thrown if the URL is not correctly formed
     */
    public ResponseEntity<V> executeDELETE() throws URISyntaxException {
        return execute(HttpMethod.DELETE);
    }

    /**
     * Execute a POST HTTP request.
     *
     * @return The response of service call (ResponseEntity)
     * @throws URISyntaxException thrown if the URL is not correctly formed.
     */
    public ResponseEntity<V> executePOSTURLEncoded() throws URISyntaxException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (queryString != null && !queryString.isEmpty()) {
            for (String key : queryString.keySet()) {
                builder = builder.queryParam(key, queryString.get(key));
            }
        }
        url = builder.toUriString();
        URI uri = new URI(builder.toUriString());
        HttpHeaders hdrs = new HttpHeaders();
        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                hdrs.add(key, headers.get(key));
            }
        }
        Field[] declaredFields = input.getClass().getDeclaredFields();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        if (declaredFields != null) {
            for (Field field : declaredFields) {
                String fieldName = field.getName();
                String toUpperCase = fieldName.substring(0, 1).toUpperCase();
                toUpperCase = toUpperCase + fieldName.substring(1);
                map.add(field.getName(),
                        (String) input.getClass().getDeclaredMethod("get"
                                + toUpperCase).invoke(input));
            }
        }

        HttpEntity<MultiValueMap<String, String>> requestEntity
                = new HttpEntity<>(map, hdrs);
        ResponseEntity<V> result = client.exchange(uri, HttpMethod.POST,
                requestEntity, outputType);

        return result;
    }
}
