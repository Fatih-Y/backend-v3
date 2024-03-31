package com.pinsoft.shopapp.service;

import com.pinsoft.shopapp.dto.AddProduct;
import com.pinsoft.shopapp.dto.GetAllProducts;
import com.pinsoft.shopapp.dto.GetProductsDetails;
import com.pinsoft.shopapp.dto.ProductSearch;
import com.pinsoft.shopapp.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ProductService {
    List<GetAllProducts> getAll();
    List<GetProductsDetails> findAll();
    List<Product> getProductByName(String name);
    void addProduct(AddProduct addProduct);

    ResponseEntity deleteProduct(int id);
    List<ProductSearch> searchProducts(String name);
}
