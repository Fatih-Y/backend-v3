package com.pinsoft.shopapp.service;

import com.pinsoft.shopapp.dto.productDTO.AddProduct;
import com.pinsoft.shopapp.dto.productDTO.GetAllProducts;
import com.pinsoft.shopapp.dto.productDTO.GetProductsDetails;
import com.pinsoft.shopapp.dto.productDTO.ProductSearch;
import com.pinsoft.shopapp.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<GetAllProducts> getAll();
    List<GetProductsDetails> findAll();
    List<Product> getProductByName(String name);
    void addProduct(AddProduct addProduct);

    ResponseEntity deleteProduct(int id);
    List<ProductSearch> searchProducts(String name);
}
