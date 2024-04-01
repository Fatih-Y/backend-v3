package com.pinsoft.shopapp.service;

import org.springframework.http.ResponseEntity;
import com.pinsoft.shopapp.entity.Order;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(int id);
    Order addOrder(Order newOrder);
    Optional<Order> updateOrder(Order order);

    void deleteOrder(int id);
}
