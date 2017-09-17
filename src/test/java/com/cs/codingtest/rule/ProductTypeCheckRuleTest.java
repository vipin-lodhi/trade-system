package com.cs.codingtest.rule;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.constants.ProductTypeEnum;
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
public class ProductTypeCheckRuleTest {

    @TestConfiguration
    static class ProductTypeCheckRuleTestContextConfiguration {

        @Bean
        public ProductTypeCheckRule productTypeCheckRule() {
            return new ProductTypeCheckRule();
        }
    }

    @Autowired
    private ProductTypeCheckRule productTypeCheckRule;

    @Test
    public void whenPrdTypeisMissing_thenNullResponse() {
        Trade trade = new Trade();
        String responseString = productTypeCheckRule.validate(trade);
        Assert.assertNull(responseString);
    }

    @Test
    public void whenPrdTypeIsValid_thenSuccessResponse() {
        Trade trade = new Trade();
        trade.setType(ProductTypeEnum.Spot.name());
        String responseString = productTypeCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.SUCCESS,responseString);
    }

    @Test
    public void whenPrdTypeIsInValid_thenNonExistPrdValidationResponse() {
        Trade trade = new Trade();
        trade.setType("NON_PRD_TYPE");
        String responseString = productTypeCheckRule.validate(trade);
        Assert.assertNotNull(responseString);
        Assert.assertEquals(ApplicationConstant.NON_EXIST_PRODUCT_TYPE_STYLE_VALIDATION,responseString);
    }

}
