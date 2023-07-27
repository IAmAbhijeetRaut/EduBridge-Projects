package com.abhijeetraut.indusmall.controller;

import com.abhijeetraut.indusmall.entity.Cart;
import com.abhijeetraut.indusmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By Abhijeet Raut on || Date : 18-07-2023 ||  Time : 08:39 pm.
 */
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/addToCart/{productId}"})
    public Cart addToCart(@PathVariable(name="productId") Integer productId){
        return cartService.addToCart(productId);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/getCartDetails"})
    public List<Cart> getCartDetails(){
        return cartService.getCartDetails();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping({"/deleteCartItem/{cartId}"})
    public void deleteCartItem(@PathVariable(name="cartId") Integer cartId){
        cartService.deleteCartItem(cartId);
    }
}
