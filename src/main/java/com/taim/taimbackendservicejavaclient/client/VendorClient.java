package com.taim.taimbackendservicejavaclient.client;

import com.taim.taimbackendservicemodel.CreateVendorDTO;
import com.taim.taimbackendservicemodel.VendorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
public class VendorClient {

    private final RestTemplate restTemplate;
    private final String rootBackendServiceUrl;

    @Autowired
    public VendorClient(RestTemplate restTemplate, String rootBackendServiceUrl) {
        this.restTemplate = restTemplate;
        this.rootBackendServiceUrl = rootBackendServiceUrl;
    }

    public List<VendorDTO> getAllVendors() {
        String uri = UriComponentsBuilder.fromHttpUrl(rootBackendServiceUrl).path("/vendors")
                .queryParam("action", "getAll")
                .toUriString();
        VendorDTO[] results = restTemplate.getForObject(uri, VendorDTO[].class);

        return Arrays.asList(results);
    }

    public VendorDTO saveVendor(CreateVendorDTO createVendorDTO) {
        String uri = UriComponentsBuilder.fromHttpUrl(rootBackendServiceUrl).path("/vendors")
                .queryParam("action", "new")
                .toUriString();
        VendorDTO result = this.restTemplate.postForObject(uri, createVendorDTO, VendorDTO.class);

        return result;
    }
}
