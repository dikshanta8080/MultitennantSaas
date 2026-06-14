package com.acharya.dikshanta.multitennant.MultiTennant.controller;

import com.acharya.dikshanta.multitennant.MultiTennant.common.dto.request.ApiResponse;
import com.acharya.dikshanta.multitennant.MultiTennant.common.dto.request.PaginationRequest;
import com.acharya.dikshanta.multitennant.MultiTennant.common.dto.response.PagedResponse;
import com.acharya.dikshanta.multitennant.MultiTennant.dto.request.CategoryRequest;
import com.acharya.dikshanta.multitennant.MultiTennant.dto.response.CategoryResponse;
import com.acharya.dikshanta.multitennant.MultiTennant.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> saveCategory(@RequestBody @Valid CategoryRequest request) {
        CategoryResponse category = categoryService.createCategory(request);
        return ResponseEntity.ok().body(ApiResponse.success("Category Added", category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @Valid @RequestBody CategoryRequest request,
            @PathVariable UUID id) {
        CategoryResponse categoryResponse = categoryService.updateCategory(id, request);
        return ResponseEntity.ok().body(ApiResponse.success("Category Updated", categoryResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(204).body(ApiResponse.success("Category deleted", "Category deleted successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<CategoryResponse>>> getAllCategories(
            @ModelAttribute PaginationRequest request,
            @RequestParam(name = "name", required = false) String name) {
        PagedResponse<CategoryResponse> allCategories = categoryService.findAllCategories(request.toPageable(), name);
        return ResponseEntity.ok().body(ApiResponse.success("Category fetched successfully", allCategories));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getById(@PathVariable UUID id) {
        CategoryResponse category = categoryService.findById(id);
        return ResponseEntity.ok().body(ApiResponse.success("Category Fetched", category));
    }
}
