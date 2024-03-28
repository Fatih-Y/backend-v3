package com.pinsoft.shopapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.util.List;


@Entity(name = "user")
@Table(schema = "public")
@Data
public class User {


    @Id
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @JsonBackReference
    private Role role;

    private String password;

    @Getter
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Order> orders;
}

