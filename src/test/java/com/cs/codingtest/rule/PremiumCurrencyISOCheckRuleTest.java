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
public class PremiumCurrencyISOCheckRuleTest {

    @TestConfiguration
    static class PremiumCurrencyISOCheckRuleTestContextConfiguration {

        @Bean
        public PremiumCurrencyISOCheckRule premiumCurrencyISOCheckRule() {
            return new PremiumCurrencyISOCheckRule();
        }
    }

    @Autowired
    private PremiumCurrencyISOCheckRule premiumCurrencyISOCheckRule;

    @Test
    public void whenPremiumCcyIsMissing_thenNullResponse() {
        Trade trade = new Trade();
        String responseString = premiumCurrencyISOCheckRule.validate(trade);
        Assert.assertNull(responseString);
    }

    @Test
    public void whenPremiumCCyIsValid_thenSuccessResponse() {
        Trade trade = new Trade();
        trade.setPremiumCcy("USD");
        String responseString = premiumCurrencyISOCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.SUCCESS,responseString);
    }

    @Test
    public void whenPremiumCcyIsInValid_thenNonValidISOValidationResponse() {
        Trade trade = new Trade();
        trade.setPremiumCcy("CNH");
        String responseString = premiumCurrencyISOCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals("PremiumCCY"+ApplicationConstant.NON_VALID_ISO_VALIDATION,responseString);
    }
}
