package com.rat.squad.storage.controller;

import com.rat.squad.storage.dto.CategoryDto;
import com.rat.squad.storage.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public List<CategoryDto> getCategoryList() {
        return categoryService.getCategories();
    }
}
