package com.mycompany.price_service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static com.mycompany.price_service.PriceServiceConfigParams.SERVICE_CURRENCY;
import static com.mycompany.price_service.PriceServiceConfigParams.SERVICE_SUBJECT;

public class PriceServiceImpl implements PriceService {

    private PriceServiceConfig priceServiceConfig;

    public PriceServiceImpl(PriceServiceConfig priceServiceConfig) {
        this.priceServiceConfig = priceServiceConfig;
    }

    public String getAveragePrice(List<BigDecimal> prices) {

        BigDecimal average = new BigDecimal(BigInteger.ZERO);

        for (BigDecimal number : prices) {
            average = average.add(number);
        }

        average = average.divide(BigDecimal.valueOf(prices.size()), 5, BigDecimal.ROUND_HALF_UP);

        return "Average price of " + priceServiceConfig.getProperty(SERVICE_SUBJECT) + " equals " + average
                + " " + priceServiceConfig.getProperty(SERVICE_CURRENCY);
    }
}
