package ru.otus.java.pro.mt.core.transfers.configs;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.otus.java.pro.mt.core.transfers.configs.properties.LimitsIntegrationProperties;

import java.time.Duration;

@Component
public class RestClientFactory {
    public RestClient createRestClient(String url, Duration readTimeout, Duration connectTimeout) {

        return RestClient.builder()
                .requestFactory(httpRequestFactory(readTimeout, connectTimeout))
                .baseUrl(url)
                .build();
    }

    private HttpComponentsClientHttpRequestFactory httpRequestFactory(Duration readTimeout, Duration connectTimeout) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(readTimeout);
        factory.setConnectTimeout(connectTimeout);
        return factory;
    }
}
