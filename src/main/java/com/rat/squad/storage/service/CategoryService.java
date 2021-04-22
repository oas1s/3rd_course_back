package com.rat.squad.storage.service;

import com.rat.squad.storage.dto.CategoryDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * CategoryService Interface
 * DECORATOR pattern applied
 * Methods: get all categories, get certain category by id
 */
public interface CategoryService {
    List<CategoryDto> getCategories();
    void getByCategory(Long id, HttpServletResponse response);
}
