package com.abhijeetraut.indusmall.service;

import com.abhijeetraut.indusmall.configuration.JwtRequestFilter;
import com.abhijeetraut.indusmall.dao.CartDao;
import com.abhijeetraut.indusmall.dao.ProductDao;
import com.abhijeetraut.indusmall.dao.UserDao;
import com.abhijeetraut.indusmall.entity.Cart;
import com.abhijeetraut.indusmall.entity.Product;
import com.abhijeetraut.indusmall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Abhijeet Raut on || Date : 18-07-2023 ||  Time : 08:39 pm.
 */
@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public Cart addToCart(Integer productId){
        Product product = productDao.findById(productId).get();
        String username = JwtRequestFilter.CURRENT_USER;
        User user = null;
        if(username != null){
             user = userDao.findById(username).get();
        }

        List<Cart> cartList = cartDao.findByUser(user);
        List<Cart> filteredList =  cartList.stream().filter(x->x.getProduct().getProductId() == productId).collect(Collectors.toList());
        if(filteredList.size() > 0){
            return null;
        }

        if(product != null && user!=null ){
            Cart cart = new Cart(product, user);
            return cartDao.save(cart);
        }
        return null;
    }

    public List<Cart> getCartDetails(){
        String username = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(username).get();
        return cartDao.findByUser(user);
    }

    public void deleteCartItem(Integer cartId){
        cartDao.deleteById(cartId);
    }
}
