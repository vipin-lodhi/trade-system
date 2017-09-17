package com.cs.codingtest.rule;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.constants.OptionsStyleEnum;
import com.cs.codingtest.constants.ProductTypeEnum;
import com.cs.codingtest.request.Trade;
import org.springframework.stereotype.Component;

/**
 * Created by vipinlodhi on 17-09-2017.
 */

@Component
public class ProductTypeCheckRule implements BusinessRule {
    @Override
    public String validate(Trade trade) {
        if(trade !=null && trade.getType() !=null && !trade.getType().isEmpty()) {
            if (ProductTypeEnum.contains(trade.getType())) {
                return ApplicationConstant.SUCCESS;
            } else {
                return ApplicationConstant.NON_EXIST_PRODUCT_TYPE_STYLE_VALIDATION;
            }
        }
        return null;
    }
}
