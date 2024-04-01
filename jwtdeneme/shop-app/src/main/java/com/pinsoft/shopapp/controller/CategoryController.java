package com.pinsoft.shopapp.controller;

import com.pinsoft.shopapp.dto.GetAllCategories;
import com.pinsoft.shopapp.entity.Category;
import com.pinsoft.shopapp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {this.categoryService = categoryService;}

	@Operation(tags = "List Categories", description = "Get All Categories", responses = {
			@ApiResponse(description = "Success", responseCode = "200"

			), @ApiResponse(description = "Data Not Found", responseCode = "404"

	)})

	@GetMapping("/getAll")
	public List<GetAllCategories> getAll() {
		return categoryService.getAll();
	}

	@Operation(tags = "Select Category", description = "Get Category", responses = {
			@ApiResponse(description = "Success", responseCode = "200"

			), @ApiResponse(description = "Data Not Found", responseCode = "404"

	)})

	@GetMapping("/categories/{name}")
	public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
		return categoryService.getCategoryByName(name)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/categories")
	public ResponseEntity<Category> addCategory(@RequestParam String name) {
		Category newCategory = categoryService.addCategory(name);
		return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
	}

	@PutMapping("/categories")
	public ResponseEntity<Category> updateCategory(@RequestParam String categoryName) {
		return categoryService.updateCategory(categoryName)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}


	@DeleteMapping("/categories/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}







}
