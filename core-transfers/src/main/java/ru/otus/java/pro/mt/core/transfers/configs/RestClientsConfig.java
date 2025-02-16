package ru.otus.java.pro.mt.core.transfers.configs;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestClient;
import ru.otus.java.pro.mt.core.transfers.configs.properties.IntegrationProperties;

@Configuration
public class RestClientsConfig implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void postProcessBeanDefinitionRegistry( BeanDefinitionRegistry registry) throws BeansException {
        IntegrationProperties integrationProperties = Binder.get(environment)
                .bind("integrations", IntegrationProperties.class)
                .orElseThrow(() -> new IllegalStateException("Failed to bind IntegrationProperties"));

        if (integrationProperties.getServices() == null) {
            throw new IllegalStateException("IntegrationProperties services map is null. Check your configuration.");
        }

        RestClientFactory restClientFactory = new RestClientFactory();

        integrationProperties.getServices().forEach((serviceName, config) -> {
            RestClient restClient = restClientFactory.createRestClient(config);
            BeanDefinition beanDefinition = BeanDefinitionBuilder
                    .genericBeanDefinition(RestClient.class, () -> restClient)
                    .setLazyInit(false)
                    .getBeanDefinition();

            registry.registerBeanDefinition(serviceName, beanDefinition);
        });
    }
}
