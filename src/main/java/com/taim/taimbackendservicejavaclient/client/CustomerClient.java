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
    private final UriComponentsBuilder uriComponentsBuilder;

    @Autowired
    public CustomerClient(RestTemplate restTemplate,
                          UriComponentsBuilder uriComponentsBuilder) {
        this.restTemplate = restTemplate;
        this.uriComponentsBuilder = uriComponentsBuilder;
    }

    public List<CustomerDTO> getAllCustomers() {
        String uri = uriComponentsBuilder.path("/customer")
                .queryParam("action", "getAll")
                .toUriString();

        CustomerDTO[] results = this.restTemplate.getForObject(uri, CustomerDTO[].class);

        return Arrays.asList(results);
    }

    public CustomerDTO saveCustomer(CreateCustomerDTO createCustomerDTO) {
        String uri = uriComponentsBuilder.path("/customer")
                .queryParam("action", "new")
                .toUriString();

        CustomerDTO result = this.restTemplate.postForObject(uri, createCustomerDTO, CustomerDTO.class);

        return result;
    }
}
