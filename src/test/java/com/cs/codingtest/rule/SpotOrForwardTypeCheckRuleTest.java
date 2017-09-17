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

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
@RunWith(SpringRunner.class)
public class SpotOrForwardTypeCheckRuleTest {

    @TestConfiguration
    static class SpotOrForwardTypeCheckRuleTestContextConfiguration {

        @Bean
        public SpotOrForwardTypeCheckRule spotOrForwardTypeCheckRule() {
            return new SpotOrForwardTypeCheckRule();
        }
    }

    @Autowired
    private SpotOrForwardTypeCheckRule spotOrForwardTypeCheckRule;

    @Test
    public void whenCurrencyPairIsMissing_thenNonCcyPairValidationResponse() {
        Trade trade = new Trade();
        String responseString = spotOrForwardTypeCheckRule.validate(trade);
        Assert.assertNull(responseString);
    }

    @Test
    public void whenCurrencyRateIsValid_thenSuccessResponse() {
        Trade trade = new Trade();
        trade.setCcyPair("EURUSD");
        trade.setRate(1.05);
        trade.setValueDate(new GregorianCalendar(2016, Calendar.DECEMBER,30).getTime());
        String responseString = spotOrForwardTypeCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.SUCCESS,responseString);
    }

    @Test
    public void whenCurrencyRateIsInValid_thenNonValueDateProdValidationResponse() {
        Trade trade = new Trade();
        trade.setCcyPair("EURUSD");
        trade.setRate(1.12);
        trade.setValueDate(new GregorianCalendar(2016, Calendar.DECEMBER,30).getTime());
        String responseString = spotOrForwardTypeCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.NON_VALUE_DATE_PROD_TYPE_VALIDATION,responseString);
    }
}
