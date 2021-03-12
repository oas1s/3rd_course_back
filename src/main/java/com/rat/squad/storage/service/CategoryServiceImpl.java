package com.rat.squad.storage.service;

import com.rat.squad.storage.controller.CategoryController;
import com.rat.squad.storage.dto.CategoryDto;
import com.rat.squad.storage.dto.DataDto;
import com.rat.squad.storage.entity.Category;
import com.rat.squad.storage.entity.RawData;
import com.rat.squad.storage.repository.CategoryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final DataService dataService;

    @Override
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream().map(CategoryServiceImpl::convertToCategoryDto).collect(Collectors.toList());
    }

    @Override
    public void getByCategory(Long id, HttpServletResponse response) throws IOException, SQLException {
        Optional<RawData> first = categoryRepository.getOne(id).getRawData().stream().findFirst();
        if (first.isPresent()) {
            dataService.get(first.get().getId(), response);
        } else {
            throw new EntityNotFoundException("RawData entity not found by category");
        }
    }

    private static CategoryDto convertToCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .dataList(convertRawDataToDataDtoList(category.getRawData()))
                .build();
    }

    private static List<DataDto> convertRawDataToDataDtoList(List<RawData> rawDataList) {
        return rawDataList.stream()
                .filter(d -> !d.isDeleted())
                .map(d -> DataDto.builder()
                        .id(d.getId())
                        .actualName(d.getActualName())
                        .mimeType(d.getMimeType())
                        .size(d.getSize())
                        .build())
                .collect(Collectors.toList());
    }
}
