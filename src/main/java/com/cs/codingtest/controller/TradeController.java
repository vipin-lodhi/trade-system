package com.cs.codingtest.controller;

import com.cs.codingtest.constants.ApplicationConstant;
import com.cs.codingtest.request.Trade;
import com.cs.codingtest.response.TradeResultResponse;
import com.cs.codingtest.response.ValidationResponse;
import com.cs.codingtest.service.TradeValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by vipinlodhi on 17-09-2017.
 */

@RestController
public class TradeController {

    private static final Logger LOGGER = Logger.getLogger(TradeController.class.getName());

    @Autowired
    private TradeValidationService tradeValidationService;

    @RequestMapping(value = "/api/tradeValidation", method = RequestMethod.POST,
            produces="application/json", consumes="application/json")
    public TradeResultResponse tradeValidation(@RequestBody List<Trade> tradeList) {

        LOGGER.entering(TradeController.class.getName(),"tradeValidation","with tradeList size : "+tradeList.size());

        List<ValidationResponse> validationResponseList = new ArrayList();
        List<String> errors;
        for(Trade trade:tradeList) {
            tradeValidationService.validateTrade(trade);
            errors = tradeValidationService.getValidationErrors();
            ValidationResponse validationResponse = getValidationResponse(errors,trade);
            if(validationResponse !=null) {
                validationResponseList.add(validationResponse);
            }
        }
        LOGGER.exiting(TradeController.class.getName(),"tradeValidation","with validationResponseList size : "+validationResponseList.size());
        return getTradeResultResponse(validationResponseList,tradeList);
    }

    private ValidationResponse getValidationResponse( List<String> errors,Trade trade) {
        ValidationResponse validationResponse = null;
        if (errors != null
                && !errors.isEmpty()) {
            Set<String> set = new HashSet(errors);
            set.remove(ApplicationConstant.SUCCESS);
            if (!set.isEmpty()) {
                validationResponse = new ValidationResponse();
                validationResponse.setValidationErrors(set);
                validationResponse.setLinkedTrade(trade);
            }
        }
        return validationResponse;
    }

    private TradeResultResponse getTradeResultResponse(List<ValidationResponse> tradeValidation,List<Trade> tradeList) {
        TradeResultResponse tradeResultResponse = new TradeResultResponse();
        tradeResultResponse.setTradeProcessed(tradeList.size());
        tradeResultResponse.setTradeFailed(tradeValidation.size());
        tradeResultResponse.setTradePassed(tradeList.size()-tradeValidation.size());
        tradeResultResponse.setValidationResponses(tradeValidation);
        return tradeResultResponse;
    }
}
