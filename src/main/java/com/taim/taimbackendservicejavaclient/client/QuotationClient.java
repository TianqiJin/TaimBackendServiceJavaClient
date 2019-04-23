package com.taim.taimbackendservicejavaclient.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class QuotationClient {

    private final RestTemplate restTemplate;
    private final UriComponentsBuilder uriComponentsBuilder;

    @Autowired
    public QuotationClient(RestTemplate restTemplate,
                           UriComponentsBuilder uriComponentsBuilder) {
        this.restTemplate = restTemplate;
        this.uriComponentsBuilder = uriComponentsBuilder;
    }


}
