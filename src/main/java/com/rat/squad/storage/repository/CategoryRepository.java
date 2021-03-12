package com.rat.squad.storage.repository;

import com.rat.squad.storage.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
