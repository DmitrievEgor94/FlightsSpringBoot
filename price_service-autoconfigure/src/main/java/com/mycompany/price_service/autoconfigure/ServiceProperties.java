package com.mycompany.price_service.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mycompany.service")
public class ServiceProperties {

    private String subject;
    private String currency;

    String getSubject() {
        return subject;
    }

    String getCurrency() {
        return currency;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
