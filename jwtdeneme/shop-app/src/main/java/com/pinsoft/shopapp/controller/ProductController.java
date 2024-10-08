package com.pinsoft.shopapp.controller;


import com.pinsoft.shopapp.dto.productDTO.AddProduct;
import com.pinsoft.shopapp.dto.productDTO.GetAllProducts;
import com.pinsoft.shopapp.dto.productDTO.GetProductsDetails;
import com.pinsoft.shopapp.dto.productDTO.ProductSearch;
import com.pinsoft.shopapp.entity.Product;

import com.pinsoft.shopapp.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {


	private final ProductService productService;
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@Operation(tags = "List Products", description = "Get All Products", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "Data Not Found", responseCode = "404")
		})

	@GetMapping("/getAllProducts")
	public List<GetAllProducts> getAllProducts() {
		return productService.getAll();
	}


	@Operation(tags = "Select Product", description = "Get Product", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "Data Not Found", responseCode = "404")
	})

	@GetMapping("/product/{name}")
	public List<Product> getProductByName(@PathVariable String name) {
		return productService.getProductByName(name);

	}
//	@PreAuthorize("hasRole('ROLE_ADMIN')")

//	@PreAuthorize("hasAuthority('admin')") olmadı bakılacak...

	@GetMapping("/getProductDetails")
	public List<GetProductsDetails> findAll() {
		return productService.findAll();
	}
	@PostMapping(value = "/addProduct", consumes = {"multipart/form-data"})
	public ResponseEntity<String> addProduct(@ModelAttribute AddProduct addProduct) {
		productService.addProduct(addProduct);
		return ResponseEntity.ok("Ürün ekleme başarılı");
	}
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity deleteProduct(@PathVariable int id) {
		return productService.deleteProduct(id);
	}

	@GetMapping("/search")
	public ResponseEntity<List<ProductSearch>> searchProducts(@RequestParam String name) {
		List<ProductSearch> searchResults = productService.searchProducts(name);
		return new ResponseEntity<>(searchResults, HttpStatus.OK);
	}




}
