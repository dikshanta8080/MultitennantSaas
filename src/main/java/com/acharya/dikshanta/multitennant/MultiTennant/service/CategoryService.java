package com.acharya.dikshanta.multitennant.MultiTennant.service;

import com.acharya.dikshanta.multitennant.MultiTennant.common.dto.response.PagedResponse;
import com.acharya.dikshanta.multitennant.MultiTennant.dto.request.CategoryRequest;
import com.acharya.dikshanta.multitennant.MultiTennant.dto.response.CategoryResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);

    CategoryResponse updateCategory(UUID categoryId, CategoryRequest newCategory);

    void deleteCategory(UUID id);

    PagedResponse<CategoryResponse> findAllCategories(Pageable pageable, String name);

    CategoryResponse findById(UUID id);

}


