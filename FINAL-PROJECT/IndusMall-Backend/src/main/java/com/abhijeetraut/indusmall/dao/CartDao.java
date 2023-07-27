package com.abhijeetraut.indusmall.dao;

import com.abhijeetraut.indusmall.entity.Cart;
import com.abhijeetraut.indusmall.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Abhijeet Raut on || Date : 18-07-2023 ||  Time : 08:39 pm.
 */
@Repository
public interface CartDao extends CrudRepository<Cart,Integer> {
    public List<Cart> findByUser(User user);
}
