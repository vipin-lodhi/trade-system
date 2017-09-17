package com.cs.codingtest.rule;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.request.Trade;
import org.springframework.stereotype.Component;

import java.util.Currency;


/**
 * Created by vipinlodhi on 17-09-2017.
 */
@Component
public class PremiumCurrencyISOCheckRule implements BusinessRule {

    @Override
    public String validate(Trade trade) {
        if(trade!=null && trade.getPremiumCcy()!=null) {
            try {
                Currency.getInstance(trade.getPremiumCcy());
            } catch (IllegalArgumentException e) {
                return "PremiumCCY" + ApplicationConstant.NON_VALID_ISO_VALIDATION;
            }
            return ApplicationConstant.SUCCESS;
        }
        return null;
    }
}
