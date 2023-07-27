package com.abhijeetraut.indusmall.entity;

/**
 * Created By Abhijeet Raut on || Date : 19-07-2023 ||  Time : 02:52 pm.
 */
public class TransactionDetails {
    private String orderId;
    private String currency;
    private Integer amount;
    private String key;

    public TransactionDetails() {
    }

    public TransactionDetails(String orderId, String currency, Integer amount, String key) {
        this.orderId = orderId;
        this.currency = currency;
        this.amount = amount;
        this.key = key;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
