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
public class CurrencyValueDateCheckRule implements BusinessRule {

    @Override
    public String validate(Trade trade) {
        if(trade !=null && trade.getValueDate() !=null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            RestTemplate restTemplate = new RestTemplate();
            Currency c = restTemplate.getForObject("http://api.fixer.io/"+dateFormat.format(trade.getValueDate()),
                    Currency.class);
            if(dateFormat.format(c.getDate()).equals(dateFormat.format(trade.getValueDate()))) {
                return ApplicationConstant.SUCCESS;
            }
            else {
                return ApplicationConstant.NON_WORKING_DAY_VALIDATION;
            }
        }
        return null;
    }
}
