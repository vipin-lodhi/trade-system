package com.cs.codingtest.rule;

import com.cs.codingtest.request.Trade;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
public interface BusinessRule {
     String validate(Trade trade);
}
