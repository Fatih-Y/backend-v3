package com.pinsoft.shopapp.controller;

import com.pinsoft.shopapp.dto.GetAllCategories;
import com.pinsoft.shopapp.entity.Category;
import com.pinsoft.shopapp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/{name}")
	public Category getCategoryByName(@PathVariable String name) {

		return categoryService.getCategoryByName(name);
	}
}
