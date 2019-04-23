package com.taim.taimbackendservicejavaclient.client;

import com.taim.taimbackendservicemodel.CreateProductDTO;
import com.taim.taimbackendservicemodel.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductClient {

    private final RestTemplate restTemplate;
    private final UriComponentsBuilder uriComponentsBuilder;


    @Autowired
    public ProductClient(RestTemplate restTemplate,
                         UriComponentsBuilder uriComponentsBuilder) {
        this.restTemplate = restTemplate;
        this.uriComponentsBuilder = uriComponentsBuilder;
    }

    public List<ProductDTO> getAllProducts() {
        String uri = uriComponentsBuilder.path("/products")
                .queryParam("action", "getAll")
                .toUriString();
        ProductDTO[] results = restTemplate.getForObject(uri, ProductDTO[].class);

        return Arrays.asList(results);
    }

    public ProductDTO getProductById(Long id) {
        String uri = uriComponentsBuilder.path("/products")
                .queryParam("action", "getById")
                .queryParam("id", id)
                .toUriString();
        ProductDTO result = restTemplate.getForObject(uri, ProductDTO.class);

        return result;
    }

    public ProductDTO createNewProduct(CreateProductDTO createProductDTO) {
        String uri = uriComponentsBuilder.path("/products")
                .queryParam("action", "new")
                .toUriString();
        ProductDTO result = restTemplate.postForObject(uri, createProductDTO, ProductDTO.class);

        return result;
    }

}
