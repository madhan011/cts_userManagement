package com.learningManagement.UserMangement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletConfigAware;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;


@Configuration
@EnableSwagger2
public class SwaggerConfig implements ServletConfigAware {

	private ServletContext servletContext;
	
	@Value("${swagger.base-path}")
	private String swaggerBasePath;
	
    @Bean
    public Docket dynamicdocs() {
        return new Docket(DocumentationType.SWAGGER_2).
        		pathProvider(new RelativePathProvider(servletContext){
        	@Override
        	public String getApplicationBasePath() {return SwaggerConfig.this.swaggerBasePath;}
        }).useDefaultResponseMessages(false).select().paths(PathSelectors.any())
        	.apis(RequestHandlerSelectors.basePackage("com.learningManagement.UserMangement")).build().apiInfo(SwaggerStaticAPiInfo());
        
    }

	private ApiInfo SwaggerStaticAPiInfo() {
		return new ApiInfoBuilder().description("Test").title("USER MANAGEMENT").version("0.0.1").build();
	}

	@Override
	public void setServletConfig(ServletConfig servletConfig) {
		// TODO Auto-generated method stub
		
	}
}
