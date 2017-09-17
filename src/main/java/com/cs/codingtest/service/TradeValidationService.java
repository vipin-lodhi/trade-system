package com.cs.codingtest.service;

import com.cs.codingtest.constants.OptionsStyleEnum;
import com.cs.codingtest.constants.ProductTypeEnum;
import com.cs.codingtest.request.Trade;
import com.cs.codingtest.rule.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vipinlodhi on 17-09-2017.
 */

@Service
public class TradeValidationService {

    private List<BusinessRule> rules;
    private List<String> validationErrors;

    @Autowired
    private ValueDateCheckRule valueDateCheckRule;

    @Autowired
    private CounterPartyCheckRule counterPartyCheckRule;

    @Autowired
    private CurrencyValueDateCheckRule currencyValueDateCheckRule;

    @Autowired
    private  CurrencyPairISOCheckRule currencyPairISOCheckRule;

    @Autowired
    private PayCurrencyISOCheckRule payCurrencyISOCheckRule;

    @Autowired
    private PremiumCurrencyISOCheckRule premiumCurrencyISOCheckRule;

    @Autowired
    private SpotOrForwardTypeCheckRule spotOrForwardTypeCheckRule;

    @Autowired
    private OptionStyleCheckRule optionStyleCheckRule;

    @Autowired
    private AmericanOptionStyleCheckRule americanOptionStyleCheckRule;

    @Autowired
    private DeliveryDateCheckRule deliveryDateCheckRule;

    @Autowired
    private ProductTypeCheckRule productTypeCheckRule;

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public ResponseEntity<String> validateTrade(Trade trade) {
        if(trade !=null) {
            init(trade);
            for(BusinessRule rule:this.rules) {
                addValidationError(rule.validate(trade));
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
    }

    private void init(Trade trade) {
        addValidationRule(trade);
        setValidationErrors(new ArrayList<String>());
    }

    private void addValidationRule(Trade trade) {
       rules = new ArrayList<BusinessRule>();
       rules.add(valueDateCheckRule);
       rules.add(counterPartyCheckRule);
       rules.add(currencyValueDateCheckRule);
       rules.add(currencyPairISOCheckRule);
       if(ProductTypeEnum.isSpotOrForward(trade.getType())) {
           rules.add(spotOrForwardTypeCheckRule);
       } else if(ProductTypeEnum.isOption(trade.getType())) {
           rules.add(payCurrencyISOCheckRule);
           rules.add(premiumCurrencyISOCheckRule);
           rules.add(optionStyleCheckRule);
           if(trade.getStyle().equals(OptionsStyleEnum.AMERICAN.name())) {
               rules.add(americanOptionStyleCheckRule);
           }
           rules.add(deliveryDateCheckRule);
        }
        rules.add(productTypeCheckRule);
    }

    public void addValidationError(String validationError) {
        if(validationError !=null
                && !validationError.isEmpty()) {
            getValidationErrors().add(validationError);
        }
    }
}
