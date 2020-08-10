package com.learningManagement.UserMangement.config;


import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {
	
	ApiCredentials apiCredentials;
	
	public RestTemplateConfig(ApiCredentials apiCredentials) {
		this.apiCredentials	=	 apiCredentials;
	}
	
	@Bean
	public String getApi() {
		return apiCredentials.getResourUrl();
	}

	/**@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate	=	 new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		System.out.println("Rest Template");
		return restTemplate;
	}
	**/
	public HttpHeaders getHttpHeaders() {
		
		
		String authorizationCredentials	=	apiCredentials.getUser()+":"+apiCredentials.getPass();
		byte[] authorizationCredentialsBytes	=	authorizationCredentials.getBytes();
		byte[] base64authorizationCredentialsBytes	=	 Base64.encodeBase64(authorizationCredentialsBytes);
		String base64authorizationCredentials	= new String(base64authorizationCredentialsBytes);	
	
		HttpHeaders httpHeaders	=	 new HttpHeaders();
		httpHeaders.add("Authorization", base64authorizationCredentials);
		httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.add("Accept",  MediaType.APPLICATION_JSON_VALUE);
		
		return httpHeaders;
	
	}
}
