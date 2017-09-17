package com.cs.codingtest.service;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.constants.ProductTypeEnum;
import com.cs.codingtest.request.Trade;
import com.cs.codingtest.rule.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by vipinlodhi on 17-09-2017.
*/
@RunWith(SpringRunner.class)
public class TradeValidationServiceTest {

    @TestConfiguration
    static class TradeValidationServiceTestContextConfiguration {

        @Bean
        public TradeValidationService tradeValidationService() {
            return new TradeValidationService();
        }
    }

    @Autowired
    private TradeValidationService tradeValidationService;

    @MockBean
    private ValueDateCheckRule valueDateCheckRule;

    @MockBean
    private CounterPartyCheckRule counterPartyCheckRule;

    @MockBean
    private CurrencyValueDateCheckRule currencyValueDateCheckRule;

    @MockBean
    private CurrencyPairISOCheckRule currencyPairISOCheckRule;

    @MockBean
    private PayCurrencyISOCheckRule payCurrencyISOCheckRule;

    @MockBean
    private PremiumCurrencyISOCheckRule premiumCurrencyISOCheckRule;

    @MockBean
    private SpotOrForwardTypeCheckRule spotOrForwardTypeCheckRule;

    @MockBean
    private OptionStyleCheckRule optionStyleCheckRule;

    @MockBean
    private AmericanOptionStyleCheckRule americanOptionStyleCheckRule;

    @MockBean
    private DeliveryDateCheckRule deliveryDateCheckRule;

    @MockBean
    private ProductTypeCheckRule productTypeCheckRule;

    @Test
    public void whenAllDataIsValid_thenTradeIsValidated() {
        Trade trade = new Trade();
        trade.setCustomer("PLUTO1");
        trade.setValueDate(new Date(2016,11,30));
        trade.setTradeDate(new Date(2016,11,28));
        trade.setType(ProductTypeEnum.Spot.name());
        trade.setCcyPair("EURUSD");
        trade.setRate(1.12);

        Mockito.when(counterPartyCheckRule.validate(trade)).thenReturn(ApplicationConstant.SUCCESS);
        Mockito.when(valueDateCheckRule.validate(trade)).thenReturn(ApplicationConstant.SUCCESS);
        Mockito.when(currencyPairISOCheckRule.validate(trade)).thenReturn(ApplicationConstant.SUCCESS);
        Mockito.when(currencyValueDateCheckRule.validate(trade)).thenReturn(ApplicationConstant.SUCCESS);
        Mockito.when(spotOrForwardTypeCheckRule.validate(trade)).thenReturn(ApplicationConstant.SUCCESS);

        Object[] expectedArray = {ApplicationConstant.SUCCESS,ApplicationConstant.SUCCESS,ApplicationConstant.SUCCESS
        ,ApplicationConstant.SUCCESS,ApplicationConstant.SUCCESS};

        ResponseEntity<String> responseEntity = tradeValidationService.validateTrade(trade);

        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assert.assertEquals(5,tradeValidationService.getValidationErrors().size());
        Assert.assertArrayEquals(expectedArray,
                tradeValidationService.getValidationErrors().toArray());
    }

    @Test
    public void whenCPAndNonISOCurrencyIsInValid_thenTradeValidationISFailedWithErrors() {
        Trade trade = new Trade();
        trade.setCustomer("PLUTO9");
        trade.setValueDate(new Date(2016,11,30));
        trade.setTradeDate(new Date(2016,11,28));
        trade.setType(ProductTypeEnum.Spot.name());
        trade.setCcyPair("EURABC");
        trade.setRate(1.12);

        Mockito.when(valueDateCheckRule.validate(trade)).thenReturn(ApplicationConstant.SUCCESS);
        Mockito.when(counterPartyCheckRule.validate(trade)).thenReturn(ApplicationConstant.NON_CP_VALIDATION);
        Mockito.when(currencyValueDateCheckRule.validate(trade)).thenReturn(ApplicationConstant.SUCCESS);
        Mockito.when(currencyPairISOCheckRule.validate(trade)).thenReturn(ApplicationConstant.NON_VALID_ISO_VALIDATION);
        Mockito.when(spotOrForwardTypeCheckRule.validate(trade)).thenReturn(ApplicationConstant.SUCCESS);

        Object[] expectedArray = {ApplicationConstant.SUCCESS,ApplicationConstant.NON_CP_VALIDATION,ApplicationConstant.SUCCESS
                ,ApplicationConstant.NON_VALID_ISO_VALIDATION,ApplicationConstant.SUCCESS};

        ResponseEntity<String> responseEntity = tradeValidationService.validateTrade(trade);

        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assert.assertEquals(5,tradeValidationService.getValidationErrors().size());
        tradeValidationService.getValidationErrors();
        Assert.assertArrayEquals(expectedArray,tradeValidationService.getValidationErrors().toArray());
    }


}










