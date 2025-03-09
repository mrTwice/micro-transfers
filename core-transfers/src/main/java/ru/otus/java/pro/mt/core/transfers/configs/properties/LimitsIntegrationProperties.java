package ru.otus.java.pro.mt.core.transfers.configs.properties;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "integrations.services.limits")
@Data
@NoArgsConstructor
public class LimitsIntegrationProperties {
        private String url;
        private Duration readTimeout;
        private Duration connectTimeout;
}
