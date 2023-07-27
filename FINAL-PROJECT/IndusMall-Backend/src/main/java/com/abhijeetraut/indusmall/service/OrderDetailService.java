package com.abhijeetraut.indusmall.service;
import com.abhijeetraut.indusmall.configuration.JwtRequestFilter;
import com.abhijeetraut.indusmall.dao.CartDao;
import com.abhijeetraut.indusmall.dao.OrderDetailDao;
import com.abhijeetraut.indusmall.dao.ProductDao;
import com.abhijeetraut.indusmall.dao.UserDao;
import com.abhijeetraut.indusmall.entity.*;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Abhijeet Raut on || Date : 18-07-2023 ||  Time : 06:58 am.
 */
@Service
public class OrderDetailService {

    private static final String ORDER_PLACED = "Placed";
    private static final String KEY = "rzp_test_20C729b9i4bogb";
    private static final String KEY_SECRET = "2kALNOdrw0yHoKM9EVAfowpk";
    private static final String CURRENCY = "INR";

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CartDao cartDao;

    public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout){
        List<OrderProductQuantity> productQuantityList =  orderInput.getOrderProductQuantityList();
        for(OrderProductQuantity o: productQuantityList){
            Product product = productDao.findById(o.getProductId()).get();
            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(currentUser).get();
            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getProductDiscountedPrice() * o.getQuantity(),
                    product,
                    user,
                    orderInput.getTransactionId()
            );

            if(!isSingleProductCheckout){
                List<Cart> carts = cartDao.findByUser(user);
                carts.stream().forEach(x->cartDao.deleteById(x.getCartId()));
            }

            orderDetailDao.save(orderDetail);
        }
    }

    public List<OrderDetail> getOrderDetails(){
        String currentUser = JwtRequestFilter.CURRENT_USER ;
        User user = userDao.findById(currentUser).get();
        return orderDetailDao.findByUser(user);
    }

    public List<OrderDetail> getAllOrderDetails(String status){
        List<OrderDetail> orderDetails = new ArrayList<>();
        if(status.equals("All")){
            orderDetailDao.findAll().forEach(x-> orderDetails.add(x));
        }
        else{
            orderDetailDao.findByOrderStatus(status).forEach(x-> orderDetails.add(x));
        }
        return orderDetails;
    }

    public void markOrderAsDelivered(Integer orderId){
        OrderDetail orderDetail = orderDetailDao.findById(orderId).get();
        if(orderDetail != null){
            orderDetail.setOrderStatus("Delivered");
            orderDetailDao.save(orderDetail);
        }
    }

    public TransactionDetails createTransaction(Double amount){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount", (amount * 100));
            jsonObject.put("currency", CURRENCY);

            RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);
            Order order = razorpayClient.orders.create(jsonObject);
            System.out.println(order);
            return prepareTransactionDetails(order);

        } catch (RazorpayException e) {
            e.getMessage();
        }
        return null;
    }

    private TransactionDetails prepareTransactionDetails(Order order){
        String orderId = order.get("id");
        String currency = order.get("currency");
        Integer amount = order.get("amount");

        TransactionDetails transactionDetails = new TransactionDetails(orderId, currency, amount, KEY);
        return transactionDetails;
    }
}
