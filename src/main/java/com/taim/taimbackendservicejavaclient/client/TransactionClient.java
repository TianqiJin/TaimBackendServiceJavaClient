package com.taim.taimbackendservicejavaclient.client;

import com.taim.taimbackendservicemodel.CreateQuotationDTO;
import com.taim.taimbackendservicemodel.QuotationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class TransactionClient {

    private final RestTemplate restTemplate;
    private final String rootBackendServiceUrl;


    @Autowired
    public TransactionClient(RestTemplate restTemplate, String rootBackendServiceUrl) {
        this.restTemplate = restTemplate;
        this.rootBackendServiceUrl = rootBackendServiceUrl;
    }

    public QuotationDTO saveQuotation(CreateQuotationDTO createQuotationDTO) {
        String uri = UriComponentsBuilder.fromHttpUrl(rootBackendServiceUrl).path("/quotations")
                .queryParam("action", "save")
                .toUriString();

        return restTemplate.postForObject(uri, createQuotationDTO, QuotationDTO.class);
    }

    public Map<String, List<BigDecimal>> getAllowedTaxRates() {
        String uri = UriComponentsBuilder.fromHttpUrl(rootBackendServiceUrl).path("/transactions")
                .queryParam("action", "getAllowedTaxRates")
                .toUriString();

        return restTemplate.getForObject(uri, Map.class);
    }

    public List<QuotationDTO> getQuotationListByCustomerId(Long customerId) {
        String uri = UriComponentsBuilder.fromHttpUrl(rootBackendServiceUrl).path("/quotations")
                .queryParam("action", "getByCustomerId")
                .queryParam("customerId", customerId)
                .toUriString();

        return Arrays.asList(restTemplate.getForObject(uri, QuotationDTO[].class));
    }

    public List<QuotationDTO> getAllQuotations() {
        String uri = UriComponentsBuilder.fromHttpUrl(rootBackendServiceUrl).path("/quotations")
                .queryParam("action", "getAll")
                .toUriString();

        return Arrays.asList(restTemplate.getForObject(uri, QuotationDTO[].class));
    }

    public QuotationDTO getQuotationByQuotationId(String quotationId) {
        String uri = UriComponentsBuilder.fromHttpUrl(rootBackendServiceUrl).path("/quotations")
                .queryParam("action", "getByQuotationId")
                .queryParam("quotationId", quotationId)
                .toUriString();

        return restTemplate.getForObject(uri, QuotationDTO.class);
    }

}
