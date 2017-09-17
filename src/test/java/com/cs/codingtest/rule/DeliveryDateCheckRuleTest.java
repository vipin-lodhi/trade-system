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
public class DeliveryDateCheckRuleTest {

    @TestConfiguration
    static class DeliveryDateCheckRuleTestRuleContextConfiguration {

        @Bean
        public DeliveryDateCheckRule deliveryDateCheckRule() {
            return new DeliveryDateCheckRule();
        }
    }

    @Autowired
    private DeliveryDateCheckRule deliveryDateCheckRule;

    @Test
    public void whenAnyOneOfDateIsMissing_thenNullResponse() {
        Trade trade = new Trade();
        String responseString = deliveryDateCheckRule.validate(trade);
        Assert.assertNull(responseString);
    }

    @Test
    public void whenAllDeliverOrTradeOrPremiumDateIsValid_thenSuccessResponse() {
        Trade trade = new Trade();
        trade.setDeliveryDate(new GregorianCalendar(2017, Calendar.JANUARY, 03).getTime());
        trade.setExpiryDate(new GregorianCalendar(2017, Calendar.JANUARY, 02).getTime());
        trade.setPremiumDate(new GregorianCalendar(2016, Calendar.JANUARY, 01).getTime());
        String responseString = deliveryDateCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.SUCCESS,responseString);
    }

    @Test
    public void whenAnyOneOfDeliverOrTradeOrPremiumDateIsInValid_thenNonDelDateValidationResponse() {
        Trade trade = new Trade();
        trade.setDeliveryDate(new GregorianCalendar(2016, Calendar.DECEMBER, 29).getTime());
        trade.setExpiryDate(new GregorianCalendar(2016, Calendar.DECEMBER, 28).getTime());
        trade.setPremiumDate(new GregorianCalendar(2017, Calendar.JANUARY, 02).getTime());
        String responseString = deliveryDateCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.NON_DEL_DATE_VALIDATION,responseString);
    }
}
