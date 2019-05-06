package com.taim.taimbackendservicejavaclient.client;

import com.taim.taimbackendservicemodel.CustomerClassDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomerClassClient {

    private final RestTemplate restTemplate;
    private final String rootBackendServiceUrl;

    @Autowired
    public CustomerClassClient(RestTemplate restTemplate, String rootBackendServiceUrl) {
        this.restTemplate = restTemplate;
        this.rootBackendServiceUrl = rootBackendServiceUrl;
    }

    public List<CustomerClassDTO> getAllCustomerClasses() {
        String uri = UriComponentsBuilder.fromHttpUrl(rootBackendServiceUrl).path("/customerclasses")
                .queryParam("action", "getAll")
                .toUriString();
        CustomerClassDTO[] results = this.restTemplate.getForObject(uri, CustomerClassDTO[].class);

        return Arrays.asList(results);
    }

    public CustomerClassDTO getCustomerClassByName(String customerClassName) {
        String uri = UriComponentsBuilder.fromHttpUrl(rootBackendServiceUrl).path("/customerclasses")
                .queryParam("action", "getByFilter")
                .queryParam("name", customerClassName)
                .toUriString();
        return this.restTemplate.getForObject(uri + customerClassName,
                CustomerClassDTO.class);
    }
}
