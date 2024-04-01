package com.pinsoft.shopapp.service.impl;

import com.pinsoft.shopapp.dto.productDTO.AddProduct;
import com.pinsoft.shopapp.dto.productDTO.GetAllProducts;
import com.pinsoft.shopapp.dto.productDTO.GetProductsDetails;
import com.pinsoft.shopapp.dto.productDTO.ProductSearch;
import com.pinsoft.shopapp.entity.Category;
import com.pinsoft.shopapp.entity.Product;
import com.pinsoft.shopapp.mapper.ModelMapperService;
import com.pinsoft.shopapp.repository.CategoryRepository;
import com.pinsoft.shopapp.repository.ProductRepository;
import com.pinsoft.shopapp.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ModelMapperService modelMapperService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapperService modelMapperService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapperService = modelMapperService;
    }

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
    public List<GetProductsDetails> findAll() {  //ürüne tıklanıldığında açılan ürün detayları sayfası için
        List<Product> products = productRepository.findAll();
        List<GetProductsDetails> getProductsDetails = products.stream()
                .map(product -> this.modelMapperService.forResponse()
                        .map(product, GetProductsDetails.class)).collect(Collectors.toList());
        return getProductsDetails;
    }

    @Override
    public void addProduct(AddProduct addProduct) {
        Product product = new Product();
        String fileName = StringUtils.cleanPath(addProduct.getFile().getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("Geçersiz dosya");
        }
        try {
            product.setBase64image(Base64.getEncoder().encodeToString(addProduct.getFile().getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setName(addProduct.getName());
        product.setPrice(addProduct.getPrice());
        product.setExplanation(addProduct.getExplanation());
        Category category = categoryRepository.findByName(addProduct.getCategoryName())
                .orElseThrow(() -> new EntityNotFoundException("Böyle bir kategori yok: " + addProduct.getCategoryName()));
        product.setCategory(category);
        productRepository.save(product);
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

