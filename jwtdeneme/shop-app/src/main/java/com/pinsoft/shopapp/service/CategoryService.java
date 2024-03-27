package com.pinsoft.shopapp.service;

import com.pinsoft.shopapp.dto.GetAllCategories;
import com.pinsoft.shopapp.entity.Category;
import java.util.List;

public interface CategoryService {
    List<GetAllCategories> getAll();
    Category getCategoryByName(String name);
}
