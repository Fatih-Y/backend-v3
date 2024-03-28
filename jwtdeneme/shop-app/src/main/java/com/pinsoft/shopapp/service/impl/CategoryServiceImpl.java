package com.pinsoft.shopapp.service.impl;

import com.pinsoft.shopapp.dto.GetAllCategories;
import com.pinsoft.shopapp.entity.Category;
import com.pinsoft.shopapp.mapper.ModelMapperService;
import com.pinsoft.shopapp.repository.CategoryRepository;
import com.pinsoft.shopapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapperService modelMapperService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapperService modelMapperService) {
        this.categoryRepository = categoryRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<GetAllCategories> getAll() {

        List<Category> categories = categoryRepository.findAll();
        List<GetAllCategories> getresponse = categories.stream()
                .map(category -> this.modelMapperService.forResponse()
                        .map(category, GetAllCategories.class)).collect(Collectors.toList());
        return getresponse;
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
}
