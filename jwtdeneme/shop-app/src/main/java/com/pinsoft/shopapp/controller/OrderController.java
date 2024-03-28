package com.pinsoft.shopapp.controller;

import com.pinsoft.shopapp.entity.Order;
import com.pinsoft.shopapp.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(tags = "List Orders", description = "Get All Orders", responses = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Data Not Found", responseCode = "404")
    })
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @Operation(tags = "Select Order", description = "Select an order to see details", responses = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Data Not Found", responseCode = "404")
    })
    @GetMapping("{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id){
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
    // get order details ekle. findall kullananı. daha iyiyse üsttekini sil



}
