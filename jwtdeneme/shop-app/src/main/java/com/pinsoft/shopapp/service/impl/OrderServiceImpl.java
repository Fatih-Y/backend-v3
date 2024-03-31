package com.pinsoft.shopapp.service.impl;

import com.pinsoft.shopapp.repository.OrderRepository;
import com.pinsoft.shopapp.entity.Order;
import com.pinsoft.shopapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }
    @Override
    public Optional<Order> updateOrder(Order order) {
        return orderRepository.findById(order.getId())
                .map(existingOrder -> {
                    existingOrder.setName(order.getName());
                    existingOrder.setPrice(order.getPrice());
                    existingOrder.setQuantity(order.getQuantity());
                    return orderRepository.save(existingOrder);
                });
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }






}
