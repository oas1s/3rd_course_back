package com.rat.squad.storage.service;

import com.rat.squad.storage.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategories();
}
