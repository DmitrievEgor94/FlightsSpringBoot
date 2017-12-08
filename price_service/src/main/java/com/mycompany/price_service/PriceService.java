package com.mycompany.price_service;

import java.math.BigDecimal;
import java.util.List;

public interface PriceService {
    String getAveragePrice(List<BigDecimal> prices);
}
