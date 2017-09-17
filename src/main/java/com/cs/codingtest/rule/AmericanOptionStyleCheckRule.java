package com.cs.codingtest.rule;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.constants.CounterPartyEnum;
import com.cs.codingtest.request.Trade;
import org.springframework.stereotype.Component;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
@Component
public class AmericanOptionStyleCheckRule implements BusinessRule{
    @Override
    public String validate(Trade trade) {
        if(trade != null
                && trade.getExcerciseStartDate() != null
                && trade.getTradeDate() != null
                && trade.getExpiryDate() != null) {
            if (trade.getExcerciseStartDate().after(trade.getTradeDate())
                    && trade.getExcerciseStartDate().before(trade.getExpiryDate())) {
                return ApplicationConstant.SUCCESS;
            } else {
                return ApplicationConstant.NON_EX_DATE_VALIDATION;
            }
        }
        return null;
    }
}
