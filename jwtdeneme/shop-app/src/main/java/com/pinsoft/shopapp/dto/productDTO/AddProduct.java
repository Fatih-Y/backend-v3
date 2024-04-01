package com.pinsoft.shopapp.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProduct {
    private String name;
    private double price;
    private String explanation;
    private String categoryName;
    private MultipartFile file;
}