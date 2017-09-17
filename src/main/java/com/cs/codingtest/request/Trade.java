package com.cs.codingtest.request;

import java.util.Date;

/**
 * Created by vipinlodhi on 17-09-2017.
 */
public class Trade {


    private String customer;
    private String ccyPair;
    private String type;
    private String direction;
    private Date tradeDate;
    private Double amount1;
    private Double amount2;
    private Double rate;
    private Date valueDate;
    private String legalEntity;
    private String trader;

    private String style;
    private String strategy;
    private Date deliveryDate;
    private Date expiryDate;
    private String payCcy;
    private Double premium;
    private String premiumCcy;
    private Date premiumDate;
    private String premiumType;
    private Date excerciseStartDate;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCcyPair() {
        return ccyPair;
    }

    public void setCcyPair(String ccyPair) {
        this.ccyPair = ccyPair;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Double getAmount1() {
        return amount1;
    }

    public void setAmount1(Double amount1) {
        this.amount1 = amount1;
    }

    public Double getAmount2() {
        return amount2;
    }

    public void setAmount2(Double amount2) {
        this.amount2 = amount2;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public String getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(String legalEntity) {
        this.legalEntity = legalEntity;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getPayCcy() {
        return payCcy;
    }

    public void setPayCcy(String payCcy) {
        this.payCcy = payCcy;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public String getPremiumCcy() {
        return premiumCcy;
    }

    public void setPremiumCcy(String premiumCcy) {
        this.premiumCcy = premiumCcy;
    }

    public Date getPremiumDate() {
        return premiumDate;
    }

    public void setPremiumDate(Date premiumDate) {
        this.premiumDate = premiumDate;
    }

    public String getPremiumType() {
        return premiumType;
    }

    public void setPremiumType(String premiumType) {
        this.premiumType = premiumType;
    }

    public Date getExcerciseStartDate() {
        return excerciseStartDate;
    }

    public void setExcerciseStartDate(Date excerciseStartDate) {
        this.excerciseStartDate = excerciseStartDate;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "customer='" + customer + '\'' +
                ", ccyPair='" + ccyPair + '\'' +
                ", type='" + type + '\'' +
                ", direction='" + direction + '\'' +
                ", tradeDate=" + tradeDate +
                ", amount1=" + amount1 +
                ", amount2=" + amount2 +
                ", rate=" + rate +
                ", valueDate=" + valueDate +
                ", legalEntity='" + legalEntity + '\'' +
                ", trader='" + trader + '\'' +
                ", style='" + style + '\'' +
                ", strategy='" + strategy + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", expiryDate=" + expiryDate +
                ", payCcy='" + payCcy + '\'' +
                ", premium=" + premium +
                ", premiumCcy='" + premiumCcy + '\'' +
                ", premiumDate=" + premiumDate +
                ", premiumType='" + premiumType + '\'' +
                ", excerciseStartDate=" + excerciseStartDate +
                '}';
    }
}
