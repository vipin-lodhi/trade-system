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
public class ValueDateCheckRuleTest {

    @TestConfiguration
    static class ValueDateCheckRuleTestRuleContextConfiguration {

        @Bean
        public ValueDateCheckRule valueDateCheckRule() {
            return new ValueDateCheckRule();
        }
    }

    @Autowired
    private ValueDateCheckRule valueDateCheckRule;

    @Test
    public void whenAnyOneOfDateIsMissing_thenNullResponse() {
        Trade trade = new Trade();
        String responseString = valueDateCheckRule.validate(trade);
        Assert.assertNull(responseString);
    }

    @Test
    public void whenAllValueAndTradeIsValid_thenSuccessResponse() {
        Trade trade = new Trade();
        trade.setValueDate(new GregorianCalendar(2016, Calendar.JANUARY, 02).getTime());
        trade.setTradeDate(new GregorianCalendar(2016, Calendar.JANUARY, 01).getTime());
        String responseString = valueDateCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.SUCCESS,responseString);
    }

    @Test
    public void whenValueAndTradeIsInValid_thenNonValueDateValidationResponse() {
        Trade trade = new Trade();
        trade.setValueDate(new GregorianCalendar(2016, Calendar.JANUARY, 01).getTime());
        trade.setTradeDate(new GregorianCalendar(2016, Calendar.JANUARY, 02).getTime());
        String responseString = valueDateCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.NON_VALUE_DATE_VALIDATION,responseString);
    }
}
