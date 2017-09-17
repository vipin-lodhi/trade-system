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
public class CurrencyPairISOCheckRuleTest {

    @TestConfiguration
    static class CurrencyPairISOCheckRuleTestContextConfiguration {

        @Bean
        public CurrencyPairISOCheckRule currencyPairISOCheckRule() {
            return new CurrencyPairISOCheckRule();
        }
    }

    @Autowired
    private CurrencyPairISOCheckRule currencyPairISOCheckRule;

    @Test
    public void whenCurrencyPairIsMissing_thenNonCcyPairValidationResponse() {
        Trade trade = new Trade();
        String responseString = currencyPairISOCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.NON_CCY_PAIR_VALIDATION,responseString);
    }

    @Test
    public void whenCurrencyPairIsValid_thenSuccessResponse() {
        Trade trade = new Trade();
        trade.setCcyPair("EURUSD");
        String responseString = currencyPairISOCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.SUCCESS,responseString);
    }

    @Test
    public void whenCurrencyPairsIsInValid_thenNonValidISOValidationResponse() {
        Trade trade = new Trade();
        trade.setCcyPair("EURCNH");
        String responseString = currencyPairISOCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.NON_VALID_ISO_VALIDATION,responseString);
    }
}
