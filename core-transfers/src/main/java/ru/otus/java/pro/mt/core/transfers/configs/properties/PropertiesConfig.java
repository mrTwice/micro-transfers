package ru.otus.java.pro.mt.core.transfers.configs.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        TransfersProperties.class,
        KafkaTopicsProperties.class
})
public class PropertiesConfig {
}
