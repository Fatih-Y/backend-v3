package com.pinsoft.shopapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductsDetails {
    private int id;
    private String name;
    private double price;
    private String explanation;
    private String categoryName;
    private String base64Image;
}
