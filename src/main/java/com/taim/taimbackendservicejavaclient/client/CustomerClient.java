package com.taim.taimbackendservicejavaclient.client;

import com.taim.taimbackendservicemodel.CreateCustomerDTO;
import com.taim.taimbackendservicemodel.CustomerClassDTO;
import com.taim.taimbackendservicemodel.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomerClient {

    private final RestTemplate restTemplate;
    private final String rootBackendServiceUrl;

    @Autowired
    public CustomerClient(RestTemplate restTemplate,
                          String rootBackendServiceUrl) {
        this.restTemplate = restTemplate;
        this.rootBackendServiceUrl = rootBackendServiceUrl;
    }

    public List<CustomerDTO> getAllCustomers() {
        String uri = UriComponentsBuilder.fromHttpUrl(rootBackendServiceUrl).path("/customers")
                .queryParam("action", "getAll")
                .toUriString();

        CustomerDTO[] results = this.restTemplate.getForObject(uri, CustomerDTO[].class);

        return Arrays.asList(results);
    }

    public CustomerDTO saveCustomer(CreateCustomerDTO createCustomerDTO) {
        String uri = UriComponentsBuilder.fromHttpUrl(rootBackendServiceUrl).path("/customers")
                .queryParam("action", "new")
                .toUriString();

        CustomerDTO result = this.restTemplate.postForObject(uri, createCustomerDTO, CustomerDTO.class);

        return result;
    }

    public CustomerDTO getCustomerByCustomerId(Long customerId) {
        String uri = UriComponentsBuilder.fromHttpUrl(rootBackendServiceUrl).path("/customers")
                .queryParam("action", "getById")
                .queryParam("id", customerId)
                .toUriString();

        return this.restTemplate.getForObject(uri, CustomerDTO.class);
    }
}
