package com.taim.taimbackendservicejavaclient.configuration;

import com.taim.taimbackendservicejavaclient.exception.InternalServerErrorException;
import com.taim.taimbackendservicejavaclient.exception.ResourceNotFoundException;
import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode().is4xxClientError()
                || clientHttpResponse.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        String errorEntity = IOUtils.toString(clientHttpResponse.getBody(), Charset.forName("UTF-8"));

        switch (clientHttpResponse.getStatusCode()) {
            case NOT_FOUND:
                throw new ResourceNotFoundException(errorEntity);
            default:
                throw new InternalServerErrorException(errorEntity);
        }
    }
}
