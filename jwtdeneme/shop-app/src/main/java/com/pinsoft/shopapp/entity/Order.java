package com.pinsoft.shopapp.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name= "OrderEntity")
@Table(name="order", schema = "public")
@Data
public class Order {
    @Id
    @SequenceGenerator(name = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    private String name;
    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

}
