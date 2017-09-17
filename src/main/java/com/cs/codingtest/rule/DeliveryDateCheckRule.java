package com.cs.codingtest.rule;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.request.Trade;
import org.springframework.stereotype.Component;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
@Component
public class DeliveryDateCheckRule implements BusinessRule{
    @Override
    public String validate(Trade trade) {
        if(trade != null
                && trade.getDeliveryDate() != null
                && trade.getPremiumDate() != null
                && trade.getExpiryDate() != null) {
            if (trade.getExpiryDate().before(trade.getDeliveryDate())
                    && trade.getPremiumDate().before(trade.getDeliveryDate())) {
                return ApplicationConstant.SUCCESS;
            } else {
                return ApplicationConstant.NON_DEL_DATE_VALIDATION;
            }
        }
        return null;
    }
}
