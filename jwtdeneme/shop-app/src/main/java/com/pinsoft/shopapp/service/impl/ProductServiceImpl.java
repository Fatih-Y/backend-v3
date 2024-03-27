package com.pinsoft.shopapp.service.impl;

import com.pinsoft.shopapp.dto.DeleteUser;
import com.pinsoft.shopapp.dto.GetAllProducts;
import com.pinsoft.shopapp.dto.GetProductsDetails;
import com.pinsoft.shopapp.dto.ProductSearch;
import com.pinsoft.shopapp.entity.Category;
import com.pinsoft.shopapp.entity.Product;
import com.pinsoft.shopapp.entity.User;
import com.pinsoft.shopapp.mapper.ModelMapperService;
import com.pinsoft.shopapp.repository.CategoryRepository;
import com.pinsoft.shopapp.repository.ProductRepository;
import com.pinsoft.shopapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllProducts> getAll() { //ürün listelenirken sadece isim,kategori, resim, fiyat bilgileri
        List<Product> products = productRepository.findAll();
        List<GetAllProducts> getAllProducts = products.stream()
                .map(product -> this.modelMapperService.forResponse()
                        .map(product, GetAllProducts.class)).collect(Collectors.toList());
        return getAllProducts;
    }


    @Override
    public List<Product> getProductByName(String name){
        return productRepository.findByName(name);
    }

    @Override
    public void addProduct(MultipartFile file, String name, double price, String explanation, String categoryName) {
        Product product = new Product();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println(" geçersiz dosya");
        }
        try {
            product.setBase64image(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setName(name);
        product.setPrice(price);
        product.setExplanation(explanation);
        Category category = categoryRepository.findByName(categoryName);
        product.setCategory(category);

        productRepository.save(product);
    }
    @Override
    public List<GetProductsDetails> findAll() {  //ürüne tıklandığında açılan ürün detayları sayfası için
        List<Product> products = productRepository.findAll();
        List<GetProductsDetails> getProductsDetails = products.stream()
                .map(product -> this.modelMapperService.forResponse()
                        .map(product, GetProductsDetails.class)).collect(Collectors.toList());
        return getProductsDetails;
    }



    @Override
    public ResponseEntity deleteProduct(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {

            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Override
    public List<ProductSearch> searchProducts(String name) {
        List<Product> products = productRepository.findAll();
        List<ProductSearch> matchingProducts = products.stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()) ||
                        product.getCategory().getName().toLowerCase().contains(name.toLowerCase()))
                .map(product -> modelMapperService.forResponse().map(product, ProductSearch.class))
                .collect(Collectors.toList());
        return matchingProducts;
    }
}

