package com.cs.codingtest.rule;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.request.Trade;
import com.cs.codingtest.response.Currency;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
@Component
public class ValueDateCheckRule implements BusinessRule {

    @Override
    public String validate(Trade trade) {
        if(trade !=null
                && trade.getValueDate() !=null
                && trade.getTradeDate() !=null) {
            if(trade.getValueDate().before(trade.getTradeDate())) {
                return ApplicationConstant.NON_VALUE_DATE_VALIDATION;
            }
            else {
                return ApplicationConstant.SUCCESS;
            }
        }
        return null;
    }
}
