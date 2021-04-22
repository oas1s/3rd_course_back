package com.rat.squad.storage.controller;

import com.rat.squad.storage.dto.CategoryDto;
import com.rat.squad.storage.dto.ControllerResult;
import com.rat.squad.storage.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller for categories
 * Implemented methods: get all categories, get certain category by id
 * Handled response statuses: 404 Not found
 */
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public List<CategoryDto> getCategoryList() {
        return categoryService.getCategories();
    }

    @GetMapping("/category/{id}")
    public void handleFileDownload(@PathVariable("id") Long id, HttpServletResponse response) throws IOException, SQLException {
        categoryService.getByCategory(id, response);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public ControllerResult handleNotFoundError(Exception e) {
        return ControllerResult.failResult(e.getMessage());

    }
}
