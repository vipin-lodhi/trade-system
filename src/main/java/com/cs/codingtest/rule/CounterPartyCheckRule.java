package com.cs.codingtest.rule;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.constants.CounterPartyEnum;
import com.cs.codingtest.request.Trade;
import org.springframework.stereotype.Component;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
@Component
public class CounterPartyCheckRule implements BusinessRule{
    @Override
    public String validate(Trade trade) {
        if(trade !=null && trade.getCustomer() !=null && !trade.getCustomer().isEmpty()) {
            if (CounterPartyEnum.contains(trade.getCustomer())) {
                return ApplicationConstant.SUCCESS;
            } else {
                return ApplicationConstant.NON_CP_VALIDATION;
            }
        }
        return null;
    }
}
