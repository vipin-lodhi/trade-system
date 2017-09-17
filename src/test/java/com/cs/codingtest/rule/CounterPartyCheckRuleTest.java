package com.cs.codingtest.rule;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.constants.CounterPartyEnum;
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
public class CounterPartyCheckRuleTest {

    @TestConfiguration
    static class CounterPartyCheckRuleTestContextConfiguration {

        @Bean
        public CounterPartyCheckRule counterPartyCheckRule() {
            return new CounterPartyCheckRule();
        }
    }

    @Autowired
    private CounterPartyCheckRule counterPartyCheckRule;

    @Test
    public void whenCPIsMissing_thenNullResponse() {
        Trade trade = new Trade();
        String responseString = counterPartyCheckRule.validate(trade);
        Assert.assertNull(responseString);
    }

    @Test
    public void whenCPIsValid_thenSuccessResponse() {
        Trade trade = new Trade();
        trade.setCustomer(CounterPartyEnum.PLUTO1.name());
        String responseString = counterPartyCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.SUCCESS,responseString);
    }

    @Test
    public void whenCPIsInValid_thenNonValidationResponse() {
        Trade trade = new Trade();
        trade.setCustomer("NON_VALID_CP");
        String responseString = counterPartyCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.NON_CP_VALIDATION,responseString);
    }
}
