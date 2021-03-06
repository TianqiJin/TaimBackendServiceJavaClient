package com.taim.taimbackendservicejavaclient.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:config.properties")
public class ClientConfiguration {

    private final Environment environment;

    @Autowired
    public ClientConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplateBuilder()
                .rootUri(environment.getProperty("taimbackendservice.url"))
                .errorHandler(new RestTemplateErrorHandler())
                .build();
    }

    @Bean
    public String getRootBackendServiceUrl() {
        return environment.getProperty("taimbackendservice.url");
    }

}
