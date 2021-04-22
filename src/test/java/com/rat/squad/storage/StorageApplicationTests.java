package com.rat.squad.storage;

import com.rat.squad.storage.dto.CategoryDto;
import com.rat.squad.storage.dto.ControllerResult;
import com.rat.squad.storage.entity.Category;
import com.rat.squad.storage.entity.RawData;
import com.rat.squad.storage.repository.CategoryRepository;
import com.rat.squad.storage.repository.DataRepository;
import com.rat.squad.storage.service.CategoryServiceImpl;
import com.rat.squad.storage.service.DataServiceImpl;
import com.rat.squad.storage.service.LobHelper;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@EnableJpaRepositories
class StorageApplicationTests {

    @Test
    void contextLoads() {
    }

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private DataRepository dataRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private LobHelper lobHelper;

    @Autowired
    private DataServiceImpl dataService;

    /***
     * Testing that method getCategories() in CategoryServiceImpl
     * returns correct categoryDto
     */
    @Test
    public void testGetCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder().title("plain/text").rawData(new ArrayList<>()).id(1L).build());
        // mock sql request
        when(categoryRepository.findAll()).thenReturn(categories);

        // correct CategoryDtoList
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categoryDtos.add(CategoryDto.builder().title("plain/text").build());

        // assert method return correct list
        Assertions.assertEquals(categoryService.getCategories().get(0).getTitle(), categoryDtos.get(0).getTitle());
    }

    /***
     * Test writeBLobToOutputStream() method from LobHelper
     * Testing that data correct writes to outputstream
     */
    @SneakyThrows
    @Test
    public void writeToOutTest() {

        // test string, data in outputStream should be equal to that
        String testString = "test";

        // creating Blob on base testString
        Blob blob = createBlob(testString);

        // create outputStream, there is will be result
        OutputStream outputStream = new ByteArrayOutputStream();

        // method that we testing
        lobHelper.writeBLobToOutputStream(blob, outputStream);

        // result in outputStream should be equal to testString
        Assertions.assertEquals(testString, outputStream.toString());
    }

    /***
     * Test that when correct saving then correct ControllerResult
     */
    @SneakyThrows
    @Test
    public void saveCategoryTest() {

        // test raw data
        RawData rawData = RawData.builder()
                .actualName("testName")
                .data(createBlob("test"))
                .deleted(false)
                .mimeType("plain/text")
                .size(Integer.toUnsignedLong("test".getBytes().length))
                .id(1L)
                .build();


        // mock inner database functions
        when(categoryRepository.findFirstByTitle(rawData.getMimeType()))
                .thenReturn(Optional.of(getCategory(rawData.getMimeType())));

        when(categoryRepository.save(getCategory(rawData.getMimeType())))
                .thenReturn(getCategory(rawData.getMimeType()));

        when(dataRepository.save(rawData)).thenReturn(rawData);

        // Assert that file saved in db correct and returned correct result
        Assertions.assertEquals(ControllerResult.successResult("file saved").getMessage(),
                dataService.save(
                        new ByteArrayInputStream("test".getBytes()
                        ), "plain/test", Integer.toUnsignedLong("test".getBytes().length),
                        "testName").getMessage());
    }

    public Blob createBlob(String testString) {
        // create inputStream with containing testString
        InputStream inputStream = new ByteArrayInputStream(testString.getBytes());
        return lobHelper.createBlob(inputStream, testString.getBytes().length);
    }

    public Category getCategory(String title) {
        return Category.builder().title(title).build();
    }

}
