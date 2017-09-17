package com.cs.codingtest.response;

import com.cs.codingtest.request.Trade;

import java.util.Set;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
public class ValidationResponse {
    private Set<String> validationErrors;
    private Trade linkedTrade;

    public Set<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Set<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Trade getLinkedTrade() {
        return linkedTrade;
    }

    public void setLinkedTrade(Trade linkedTrade) {
        this.linkedTrade = linkedTrade;
    }
}
