package com.pinsoft.shopapp.service;

import com.pinsoft.shopapp.dto.GetAllCategories;
import com.pinsoft.shopapp.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<GetAllCategories> getAll();
    Optional<Category> getCategoryByName(String name);
    Category addCategory(String name);
    Optional<Category> updateCategory(String name);
    void deleteCategory(int id);
}
