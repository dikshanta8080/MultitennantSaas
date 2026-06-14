package com.acharya.dikshanta.multitennant.MultiTennant.mappers;

import com.acharya.dikshanta.multitennant.MultiTennant.dto.request.CategoryRequest;
import com.acharya.dikshanta.multitennant.MultiTennant.dto.response.CategoryResponse;
import com.acharya.dikshanta.multitennant.MultiTennant.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", constant = "false")
    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category entity);
}
