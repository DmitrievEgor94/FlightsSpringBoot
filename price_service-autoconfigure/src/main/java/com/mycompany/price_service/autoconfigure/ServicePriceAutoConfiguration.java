package com.mycompany.price_service.autoconfigure;

import com.mycompany.price_service.PriceServiceImpl;
import com.mycompany.price_service.PriceServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.mycompany.price_service.PriceServiceConfigParams.SERVICE_CURRENCY;
import static com.mycompany.price_service.PriceServiceConfigParams.SERVICE_SUBJECT;


@Configuration
@ConditionalOnClass(PriceServiceImpl.class)
@EnableConfigurationProperties(ServiceProperties.class)
public class ServicePriceAutoConfiguration {

    @Autowired
    private ServiceProperties serviceProperties;

    @Bean
    @ConditionalOnMissingBean
    public PriceServiceConfig priceServiceConfig() {
        String serviceSubject = serviceProperties.getSubject();
        String serviceCurrency = serviceProperties.getCurrency() == null ? "$" : serviceProperties.getCurrency();

        PriceServiceConfig priceServiceConfig = new PriceServiceConfig();

        priceServiceConfig.put(SERVICE_SUBJECT, serviceSubject);
        priceServiceConfig.put(SERVICE_CURRENCY, serviceCurrency);

        return priceServiceConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public PriceServiceImpl priceService(PriceServiceConfig priceServiceConfig) {
        return new PriceServiceImpl(priceServiceConfig);
    }
}
