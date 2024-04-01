package com.pinsoft.shopapp.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProducts {
    private int id;
    private String name;
    private double price;
    private String base64Image;
}
