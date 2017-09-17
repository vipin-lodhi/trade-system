package com.cs.codingtest.rule;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.request.Trade;
import com.cs.codingtest.response.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


/**
 * Created by vipinlodhi on 17-09-2017.
 */
@Component
public class SpotOrForwardTypeCheckRule implements BusinessRule {

    @Override
    public String validate(Trade trade) {

        if(trade !=null && trade.getRate() !=null) {
            String firstCurrency,secondCurrency =null;
            if(trade.getCcyPair() !=null
                    && !trade.getCcyPair().isEmpty()
                    && trade.getCcyPair().length()==6) {
                try {
                    firstCurrency = trade.getCcyPair().substring(0, 3);
                    secondCurrency = trade.getCcyPair().substring(3, trade.getCcyPair().length());
                    java.util.Currency.getInstance(firstCurrency);
                    java.util.Currency.getInstance(secondCurrency);

                } catch (IllegalArgumentException e) {
                    return "CurrencyPair" + ApplicationConstant.NON_VALID_ISO_VALIDATION;
                }
            } else {
                return ApplicationConstant.NON_CCY_PAIR_VALIDATION;
            }
            if(firstCurrency !=null && secondCurrency!=null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                RestTemplate restTemplate = new RestTemplate();
                Currency c = restTemplate.
                        getForObject("http://api.fixer.io/" + dateFormat.
                                        format(trade.getValueDate()) + "?base=" + firstCurrency,
                                Currency.class);

                DecimalFormat f = new DecimalFormat("##.00");
                if (trade.getRate().equals(new Double(f.format(c.getRates().get(secondCurrency))))){
                    return ApplicationConstant.SUCCESS;
                } else {
                    return ApplicationConstant.NON_VALUE_DATE_PROD_TYPE_VALIDATION;
                }
            } else {
                return ApplicationConstant.NON_CCY_PAIR_VALIDATION;
            }
        }
        return null;
    }
}
