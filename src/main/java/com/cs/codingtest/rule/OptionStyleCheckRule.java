package com.cs.codingtest.rule;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.constants.OptionsStyleEnum;
import com.cs.codingtest.request.Trade;
import org.springframework.stereotype.Component;

/**
 * Created by vipinlodhi on 17-09-2017.
 */

@Component
public class OptionStyleCheckRule implements BusinessRule {
    @Override
    public String validate(Trade trade) {
        if(trade !=null && trade.getStyle() !=null && !trade.getStyle().isEmpty()) {
            if (OptionsStyleEnum.contains(trade.getStyle())) {
                return ApplicationConstant.SUCCESS;
            } else {
                return ApplicationConstant.NON_EXIST_OPTIONS_STYLE_VALIDATION;
            }
        }
        return null;
    }
}
