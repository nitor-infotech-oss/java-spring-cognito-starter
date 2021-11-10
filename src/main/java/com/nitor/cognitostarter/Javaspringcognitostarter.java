package com.nitor.cognitostarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nitor.cognitostarter.security.JwtConfiguration;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static com.nimbusds.jose.JWSAlgorithm.RS256;
@SpringBootApplication
public class Javaspringcognitostarter {
	@Autowired
	private JwtConfiguration jwtConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(Javaspringcognitostarter.class, args);
	}

	  @Bean
	    public ConfigurableJWTProcessor configurableJWTProcessor() throws MalformedURLException {
	        ResourceRetriever resourceRetriever =
	                new DefaultResourceRetriever(jwtConfiguration.getConnectionTimeout(),
	                        jwtConfiguration.getReadTimeout());
	        URL jwkSetURL = new URL(jwtConfiguration.getJwkUrl());
	        JWKSource keySource = new RemoteJWKSet(jwkSetURL, resourceRetriever);
	        ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
	        JWSKeySelector keySelector = new JWSVerificationKeySelector(RS256, keySource);
	        jwtProcessor.setJWSKeySelector(keySelector);
	        return jwtProcessor;
	    }
	  
	  private ApiKey apiKey() {
	        return new ApiKey("JWT", "Authorization", "header");
	    }

	    private SecurityContext securityContext() {
	        return SecurityContext.builder().securityReferences(defaultAuth()).build();
	    }

	    private List<SecurityReference> defaultAuth() {
	        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	    }

}