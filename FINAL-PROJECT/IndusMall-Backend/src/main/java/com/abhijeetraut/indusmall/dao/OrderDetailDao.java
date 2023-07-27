package com.abhijeetraut.indusmall.dao;

import com.abhijeetraut.indusmall.entity.OrderDetail;
import com.abhijeetraut.indusmall.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Abhijeet Raut on || Date : 18-07-2023 ||  Time : 07:00 am.
 */
@Repository
public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer> {
    public List<OrderDetail> findByUser(User user);
    public List<OrderDetail> findByOrderStatus(String status);
}
