package com.pinsoft.shopapp.service.impl;

import com.pinsoft.shopapp.entity.User;
import com.pinsoft.shopapp.repository.OrderRepository;
import com.pinsoft.shopapp.entity.Order;
import com.pinsoft.shopapp.repository.UserRepository;
import com.pinsoft.shopapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
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
    public List<Order> findByUserId(int userId){
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order addOrder(Order order) {
        User userId = userRepository.findById(order.getUser().getId())
                .orElseThrow(() -> new RuntimeException(order.getId() + " kullanıcısı bulunamadı"));
        order.setUser(userId);
        order.setName(order.getName());
        order.setPrice(order.getPrice());
        order.setQuantity(order.getQuantity());
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
