package com.abhijeetraut.indusmall.entity;

import java.util.List;

/**
 * Created By Abhijeet Raut on || Date : 18-07-2023 ||  Time : 07:10 am.
 */
public class OrderInput {
    private String fullName;
    private String fullAddress;
    private String contactNumber;
    private String alternateContactNumber;
    private String transactionId;
    private List<OrderProductQuantity> orderProductQuantityList;

    public OrderInput() {
    }

    public OrderInput(String fullName, String fullAddress, String contactNumber, String alternateContactNumber, String transactionId, List<OrderProductQuantity> orderProductQuantityList) {
        this.fullName = fullName;
        this.fullAddress = fullAddress;
        this.contactNumber = contactNumber;
        this.alternateContactNumber = alternateContactNumber;
        this.transactionId = transactionId;
        this.orderProductQuantityList = orderProductQuantityList;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(String alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<OrderProductQuantity> getOrderProductQuantityList() {
        return orderProductQuantityList;
    }

    public void setOrderProductQuantityList(List<OrderProductQuantity> orderProductQuantityList) {
        this.orderProductQuantityList = orderProductQuantityList;
    }
}
