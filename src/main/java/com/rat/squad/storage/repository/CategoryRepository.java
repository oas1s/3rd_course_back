package com.rat.squad.storage.repository;

import com.rat.squad.storage.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
    Optional<Category> findFirstByTitle(String title);
    void deleteCategoryByTitle(String title);
}
