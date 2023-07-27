package com.abhijeetraut.indusmall.service;

import com.abhijeetraut.indusmall.configuration.JwtRequestFilter;
import com.abhijeetraut.indusmall.dao.CartDao;
import com.abhijeetraut.indusmall.dao.ProductDao;
import com.abhijeetraut.indusmall.dao.UserDao;
import com.abhijeetraut.indusmall.entity.Cart;
import com.abhijeetraut.indusmall.entity.Product;
import com.abhijeetraut.indusmall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Abhijeet Raut on || Date : 19-06-2023 ||  Time : 01:00 pm.
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    public Product addNewProduct(Product product){
        return productDao.save(product);

    }

    public List<Product> getAllProducts(int pageNo, String searchKey){
        Pageable pageable = PageRequest.of(pageNo,8);
        if(searchKey.equals("")){
            return (List<Product>) productDao.findAll(pageable);
        }else{
            return (List<Product>) productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
        }

    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId ){
        if(isSingleProductCheckout && productId !=0 ){
            //Buy Single Product
            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;
        }else{
            String username = JwtRequestFilter.CURRENT_USER;
            User user =  userDao.findById(username).get();
            List<Cart> carts = cartDao.findByUser(user);
            return carts.stream().map(x->x.getProduct()).collect(Collectors.toList());
        }

    }

    public void deleteProductDetails(Integer Id){
        productDao.deleteById(Id);
    }

    public Product getProductDetailsById(Integer productId){
        return productDao.findById(productId).get();
    }
}
