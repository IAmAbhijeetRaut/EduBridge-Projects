package com.abhijeetraut.indusmall.entity;

/**
 * Created By Abhijeet Raut on || Date : 18-07-2023 ||  Time : 07:12 am.
 */
public class OrderProductQuantity {
    private Integer productId;
    private Integer quantity;

    public OrderProductQuantity(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        quantity = quantity;
    }
}
