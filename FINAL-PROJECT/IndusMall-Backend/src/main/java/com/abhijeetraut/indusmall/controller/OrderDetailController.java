package com.abhijeetraut.indusmall.controller;

import com.abhijeetraut.indusmall.entity.OrderDetail;
import com.abhijeetraut.indusmall.entity.OrderInput;
import com.abhijeetraut.indusmall.entity.TransactionDetails;
import com.abhijeetraut.indusmall.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By Abhijeet Raut on || Date : 18-07-2023 ||  Time : 07:01 am.
 */
@RestController
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping({"/placeOrder/{isSingleProductCheckout}"})
    public void placeOrder(@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout, @RequestBody OrderInput orderInput){
        orderDetailService.placeOrder(orderInput, isSingleProductCheckout);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/getOrderDetails"})
    public List<OrderDetail> getOrderDetails(){
            return orderDetailService.getOrderDetails();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/getAllOrderDetails/{status}"})
    public List<OrderDetail> getAllOrderDetails(@PathVariable(name="status") String status){
        return orderDetailService.getAllOrderDetails(status);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/markOrderAsDelivered/{orderId}"})
    public void markOrderAsDelivered(@PathVariable(name="orderId") Integer orderId){
        orderDetailService.markOrderAsDelivered(orderId);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/createTransaction/{amount}"})
    public TransactionDetails createTransaction(@PathVariable(name="amount") Double amount){
        return orderDetailService.createTransaction(amount);
    }
}
