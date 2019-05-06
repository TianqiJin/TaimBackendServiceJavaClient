package com.taim.taimbackendservicejavaclient.client;

import com.taim.taimbackendservicemodel.CreateQuotationDTO;
import com.taim.taimbackendservicemodel.QuotationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class QuotationClient {

    private final RestTemplate restTemplate;
    private final String rootBackendServiceUrl;


    @Autowired
    public QuotationClient(RestTemplate restTemplate, String rootBackendServiceUrl) {
        this.restTemplate = restTemplate;
        this.rootBackendServiceUrl = rootBackendServiceUrl;
    }

    public QuotationDTO saveQuotation(CreateQuotationDTO createQuotationDTO) {
        String uri = UriComponentsBuilder.fromHttpUrl(rootBackendServiceUrl).path("/quotations")
                .queryParam("action", "save")
                .toUriString();

        QuotationDTO result = restTemplate.postForObject(uri, createQuotationDTO, QuotationDTO.class);

        return result;
    }

}
