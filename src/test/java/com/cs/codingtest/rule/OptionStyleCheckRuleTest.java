package com.cs.codingtest.rule;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.constants.OptionsStyleEnum;
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
public class OptionStyleCheckRuleTest {

    @TestConfiguration
    static class OptionStyleCheckRuleTestContextConfiguration {

        @Bean
        public OptionStyleCheckRule optionStyleCheckRule() {
            return new OptionStyleCheckRule();
        }
    }

    @Autowired
    private OptionStyleCheckRule optionStyleCheckRule;

    @Test
    public void whenOptionStyleisMissing_thenNullResponse() {
        Trade trade = new Trade();
        String responseString = optionStyleCheckRule.validate(trade);
        Assert.assertNull(responseString);
    }

    @Test
    public void whenOptionStyleIsValid_thenSuccessResponse() {
        Trade trade = new Trade();
        trade.setStyle(OptionsStyleEnum.AMERICAN.name());
        String responseString = optionStyleCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.SUCCESS,responseString);
    }

    @Test
    public void whenCPIsInValid_thenNonExistOptionStyleValidationResponse() {
        Trade trade = new Trade();
        trade.setStyle("NON_VALID_OPTION_STYLE");
        String responseString = optionStyleCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.NON_EXIST_OPTIONS_STYLE_VALIDATION,responseString);
    }
}
