package com.taim.taimbackendservicejavaclient.client;

import com.taim.taimbackendservicemodel.StaffDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
public class StaffClient {

    private final RestTemplate restTemplate;
    private final UriComponentsBuilder uriComponentsBuilder;

    @Autowired
    public StaffClient(RestTemplate restTemplate,
                       UriComponentsBuilder uriComponentsBuilder) {
        this.restTemplate = restTemplate;
        this.uriComponentsBuilder = uriComponentsBuilder;
    }

    public List<StaffDTO> getAllStaffs() {
        String uri = uriComponentsBuilder.path("/staffs")
                .queryParam("action", "getAll")
                .toUriString();
        StaffDTO[] results = restTemplate.getForObject(uri, StaffDTO[].class);

        return Arrays.asList(results);
    }
}
