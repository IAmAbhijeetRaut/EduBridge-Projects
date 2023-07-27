package com.abhijeetraut.indusmall.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created By Abhijeet Raut on || Date : 19-06-2023 ||  Time : 12:44 pm.
 */

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer productId;
    private String productName;
    @Column(length = 5000)
    private String productDescription;
    private Double productDiscountedPrice;
    private Double productActualPrice;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_images", joinColumns = {
            @JoinColumn(name="product_id")
    },
    inverseJoinColumns = {
            @JoinColumn(name="image_id")
    }
    )
    private Set<ImageModel> productImages;

    public Product() {
    }

    public Product(Integer productId, String productName, String productDescription, Double productDiscountedPrice, Double productActualPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productDiscountedPrice = productDiscountedPrice;
        this.productActualPrice = productActualPrice;
    }

    public Set<ImageModel> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ImageModel> productImages) {
        this.productImages = productImages;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductDiscountedPrice() {
        return productDiscountedPrice;
    }

    public void setProductDiscountedPrice(Double productDiscountedPrice) {
        this.productDiscountedPrice = productDiscountedPrice;
    }

    public Double getProductActualPrice() {
        return productActualPrice;
    }

    public void setProductActualPrice(Double productActualPrice) {
        this.productActualPrice = productActualPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productDiscountedPrice=" + productDiscountedPrice +
                ", productActualPrice=" + productActualPrice +
                '}';
    }
}
