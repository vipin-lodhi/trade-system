package com.cs.codingtest.rule;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.request.Trade;
import org.springframework.stereotype.Component;

import java.util.Currency;


/**
 * Created by vipinlodhi on 17-09-2017.
 */
@Component
public class CurrencyPairISOCheckRule implements BusinessRule {

    @Override
    public String validate(Trade trade) {
        if(trade!=null
                && trade.getCcyPair() !=null
                && !trade.getCcyPair().isEmpty()
                && trade.getCcyPair().length()==6) {
            try {
                String firstCurrency = trade.getCcyPair().substring(0, 3);
                String secondCurrency = trade.getCcyPair().substring(3, trade.getCcyPair().length());
                Currency.getInstance(firstCurrency);
                Currency.getInstance(secondCurrency);

            } catch (IllegalArgumentException e) {
                return ApplicationConstant.NON_VALID_ISO_VALIDATION;
            }
        } else {
            return ApplicationConstant.NON_CCY_PAIR_VALIDATION;
        }
        return ApplicationConstant.SUCCESS;
    }
}
