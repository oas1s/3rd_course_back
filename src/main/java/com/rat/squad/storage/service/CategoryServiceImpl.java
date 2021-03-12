package com.rat.squad.storage.service;

import com.rat.squad.storage.controller.CategoryController;
import com.rat.squad.storage.dto.CategoryDto;
import com.rat.squad.storage.entity.Category;
import com.rat.squad.storage.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream().map(CategoryServiceImpl::convertToCategoryDto).collect(Collectors.toList());
    }

    private  static  CategoryDto convertToCategoryDto(Category category) {
        return CategoryDto.builder().id(category.getId()).title(category.getTitle()).build();
    }
}
