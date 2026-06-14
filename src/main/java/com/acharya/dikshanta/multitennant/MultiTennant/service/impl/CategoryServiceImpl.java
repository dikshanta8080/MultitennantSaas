package com.acharya.dikshanta.multitennant.MultiTennant.service.impl;

import com.acharya.dikshanta.multitennant.MultiTennant.common.dto.response.PagedResponse;
import com.acharya.dikshanta.multitennant.MultiTennant.common.exceptions.BusinessException;
import com.acharya.dikshanta.multitennant.MultiTennant.common.exceptions.ResourceNotFoundException;
import com.acharya.dikshanta.multitennant.MultiTennant.dto.request.CategoryRequest;
import com.acharya.dikshanta.multitennant.MultiTennant.dto.response.CategoryResponse;
import com.acharya.dikshanta.multitennant.MultiTennant.mappers.CategoryMapper;
import com.acharya.dikshanta.multitennant.MultiTennant.model.Category;
import com.acharya.dikshanta.multitennant.MultiTennant.repository.CategoryRepository;
import com.acharya.dikshanta.multitennant.MultiTennant.service.CategoryService;
import com.acharya.dikshanta.multitennant.MultiTennant.specifications.CategorySpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        checkCategoryExists(request.name());
        log.debug("Category Does not exists, creating category....");
        Category category = categoryMapper.toEntity(request);
        return categoryMapper.toResponse(categoryRepository.save(category));

    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(final UUID categoryId, CategoryRequest newCategory) {
        Category category = getCategory(categoryId);
        updateCategoryIfNotSame(category, newCategory);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategory(UUID id) {
        int rowsUpdated = categoryRepository.softDeleteById(id);
        if (rowsUpdated <= 0) {
            throw new ResourceNotFoundException("Category not found");
        }
    }


    @Override
    public PagedResponse<CategoryResponse> findAllCategories(Pageable pageable, String name) {
        Page<Category> categoryPage = categoryRepository.findAll(CategorySpecification.nameLike(name), pageable);
        Page<CategoryResponse> categoryResponsePage = categoryPage.map(categoryMapper::toResponse);
        return PagedResponse.toPagedResponse(categoryResponsePage);
    }

    @Override
    public CategoryResponse findById(UUID id) {
        return categoryMapper.toResponse(getCategory(id));
    }

    private void checkCategoryExists(final String name) {
        if (categoryRepository.existsByName(name)) {
            log.debug("Category not found");
            throw new BusinessException("Category Already Exists");
        }
    }

    private Category getCategory(UUID categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category Not found"));
    }

    private void updateCategoryIfNotSame(Category category, CategoryRequest request) {
        if (request.name() != null && !request.name().equalsIgnoreCase(category.getName())) {
            category.setName(request.name());
        }
        if (request.description() != null && request.description().equalsIgnoreCase(category.getDescription())) {
            category.setDescription(request.description());
        }
    }

}
