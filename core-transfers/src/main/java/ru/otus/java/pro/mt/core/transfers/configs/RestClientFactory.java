package ru.otus.java.pro.mt.core.transfers.configs;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.otus.java.pro.mt.core.transfers.configs.properties.IntegrationProperties;

@Component
public class RestClientFactory {
    public RestClient createRestClient(IntegrationProperties.ServiceConfig serviceConfig) {

        return RestClient.builder()
                .requestFactory(httpRequestFactory(serviceConfig))
                .baseUrl(serviceConfig.getUrl())
                .build();
    }

    public HttpComponentsClientHttpRequestFactory httpRequestFactory(IntegrationProperties.ServiceConfig serviceConfig) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(serviceConfig.getReadTimeout());
        factory.setConnectTimeout(serviceConfig.getConnectTimeout());
        return factory;
    }
}
