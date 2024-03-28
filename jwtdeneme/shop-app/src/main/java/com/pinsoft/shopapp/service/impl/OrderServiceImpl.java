package com.pinsoft.shopapp.service.impl;

import com.pinsoft.shopapp.repository.OrderRepository;
import com.pinsoft.shopapp.entity.Order;
import com.pinsoft.shopapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    @Override
    public Optional<Order> getOrderById(int id){
        return orderRepository.findById(id);
    }

}
