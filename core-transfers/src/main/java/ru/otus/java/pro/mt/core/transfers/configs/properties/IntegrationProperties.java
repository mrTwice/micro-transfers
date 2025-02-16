package ru.otus.java.pro.mt.core.transfers.configs.properties;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "integrations")
@Getter
@Setter
public class IntegrationProperties {
    private Map<String, ServiceConfig> services;

    @Getter
    @Setter
    public static class ServiceConfig {
        private String url;
        private Duration readTimeout;
        private Duration connectTimeout;
    }
}
