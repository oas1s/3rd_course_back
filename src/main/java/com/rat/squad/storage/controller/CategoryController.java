package com.rat.squad.storage.controller;

import com.rat.squad.storage.dto.CategoryDto;
import com.rat.squad.storage.entity.Category;
import com.rat.squad.storage.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/category")
    public List<CategoryDto> getCategoryList() {
        return categoryRepository.findAll().stream().map(CategoryController::convertToCategoryDto).collect(Collectors.toList());
    }

    private  static  CategoryDto convertToCategoryDto(Category category) {
        return CategoryDto.builder().id(category.getId()).title(category.getTitle()).build();
    }
}
