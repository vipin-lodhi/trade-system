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
public class CurrencyValueDateCheckRuleTest {

    @TestConfiguration
    static class CurrencyDateCheckRuleContextConfiguration {

        @Bean
        public CurrencyValueDateCheckRule currencyDateCheckRule() {
            return new CurrencyValueDateCheckRule();
        }
    }

    @Autowired
    private CurrencyValueDateCheckRule currencyValueDateCheckRule;

    @Test
    public void whenCurrencyDateIsMissing_thenNullResponse() {
        Trade trade = new Trade();
        String responseString = currencyValueDateCheckRule.validate(trade);
        Assert.assertNull(responseString);
    }

    @Test
    public void whenCurrencyDateIsValid_thenSuccessResponse() {
        Trade trade = new Trade();
        trade.setValueDate(new GregorianCalendar(2016, Calendar.DECEMBER,
                29,5,30,00).getTime());
        String responseString = currencyValueDateCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(responseString,ApplicationConstant.SUCCESS);
    }

    @Test
    public void whenCurrencyDateIsNonWorkingDay_thenNonWorkingDAyValidationResponse() {
        Trade trade = new Trade();
        trade.setValueDate(new GregorianCalendar(2016, Calendar.DECEMBER,31).getTime());
        String responseString = currencyValueDateCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.NON_WORKING_DAY_VALIDATION,responseString);
    }
}
