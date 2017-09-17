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
public class AmericanOptionStyleCheckRuleTest {

    @TestConfiguration
    static class AmericanOptionStyleCheckRuleContextConfiguration {

        @Bean
        public AmericanOptionStyleCheckRule americanOptionStyleCheckRule() {
            return new AmericanOptionStyleCheckRule();
        }
    }

    @Autowired
    private AmericanOptionStyleCheckRule americanOptionStyleCheckRule;

    @Test
    public void whenAnyOneOfExerciseOrTradeOrExpiryDateIsMissing_thenNullResponse() {
        Trade trade = new Trade();
        String responseString = americanOptionStyleCheckRule.validate(trade);
        Assert.assertNull(responseString);
    }

    @Test
    public void whenAllExerciseOrTradeOrExpiryDateIsValid_thenSuccessResponse() {
        Trade trade = new Trade();
        trade.setExcerciseStartDate(new GregorianCalendar(2016, Calendar.DECEMBER, 29).getTime());
        trade.setTradeDate(new GregorianCalendar(2016, Calendar.DECEMBER, 28).getTime());
        trade.setExpiryDate(new GregorianCalendar(2017, Calendar.JANUARY, 02).getTime());
        String responseString = americanOptionStyleCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(responseString,ApplicationConstant.SUCCESS);
    }

    @Test
    public void whenAllTradeIsAfterExerciseDate_thenNonValidExDateValidationResponse() {
        Trade trade = new Trade();
        trade.setExcerciseStartDate(new GregorianCalendar(2016, Calendar.DECEMBER, 29).getTime());
        trade.setTradeDate(new GregorianCalendar(2016, Calendar.DECEMBER, 31).getTime());
        trade.setExpiryDate(new GregorianCalendar(2017, Calendar.JANUARY, 02).getTime());
        String responseString = americanOptionStyleCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.NON_EX_DATE_VALIDATION,responseString);
    }
}
