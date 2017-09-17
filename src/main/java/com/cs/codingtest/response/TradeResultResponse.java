package com.cs.codingtest.response;

import java.util.List;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
public class TradeResultResponse {
    private int tradeProcessed;
    private int tradePassed;
    private int tradeFailed;
    private List<ValidationResponse> validationResponses;

    public int getTradeProcessed() {
        return tradeProcessed;
    }

    public void setTradeProcessed(int tradeProcessed) {
        this.tradeProcessed = tradeProcessed;
    }

    public int getTradePassed() {
        return tradePassed;
    }

    public void setTradePassed(int tradePassed) {
        this.tradePassed = tradePassed;
    }

    public int getTradeFailed() {
        return tradeFailed;
    }

    public void setTradeFailed(int tradeFailed) {
        this.tradeFailed = tradeFailed;
    }

    public List<ValidationResponse> getValidationResponses() {
        return validationResponses;
    }

    public void setValidationResponses(List<ValidationResponse> validationResponses) {
        this.validationResponses = validationResponses;
    }
}
