package com.abhijeetraut.indusmall.entity;

import javax.persistence.*;

/**
 * Created By Abhijeet Raut on || Date : 18-07-2023 ||  Time : 08:35 pm.
 */
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;

    @OneToOne
    private Product product;

    @OneToOne
    private User user;

    public Cart() {
    }

    public Cart(Product product, User user) {
        this.product = product;
        this.user = user;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
