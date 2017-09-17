package com.cs.codingtest.rule;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.request.Trade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
@RunWith(SpringRunner.class)
public class PayCurrencyISOCheckRuleTest {

    @TestConfiguration
    static class PayCurrencyISOCheckRuleTestContextConfiguration {

        @Bean
        public PayCurrencyISOCheckRule payCurrencyISOCheckRule() {
            return new PayCurrencyISOCheckRule();
        }
    }

    @Autowired
    private PayCurrencyISOCheckRule payCurrencyISOCheckRule;

    @Test
    public void whenPayCcyIsMissing_thenNullResponse() {
        Trade trade = new Trade();
        String responseString = payCurrencyISOCheckRule.validate(trade);
        Assert.assertNull(responseString);
    }

    @Test
    public void whenPayCCyIsValid_thenSuccessResponse() {
        Trade trade = new Trade();
        trade.setPayCcy("USD");
        String responseString = payCurrencyISOCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.SUCCESS,responseString);
    }

    @Test
    public void whenPayCcyIsInValid_thenNonValidISOValidationResponse() {
        Trade trade = new Trade();
        trade.setPayCcy("CNH");
        String responseString = payCurrencyISOCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals("payCcy"+ApplicationConstant.NON_VALID_ISO_VALIDATION,responseString);
    }
}
