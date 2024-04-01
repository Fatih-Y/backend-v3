package com.pinsoft.shopapp.repository;

import com.pinsoft.shopapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM OrderEntity o WHERE o.user.id = :userId")
    List<Order> findByUserId(int userId);


}
