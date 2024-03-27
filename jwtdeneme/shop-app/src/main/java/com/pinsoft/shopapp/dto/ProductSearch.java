package com.pinsoft.shopapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearch {
    private int id;
    private String name;
    private Double price;
    private String explanation;
    private String categoryName;
}