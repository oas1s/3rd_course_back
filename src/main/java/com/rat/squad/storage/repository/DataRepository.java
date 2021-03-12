package com.rat.squad.storage.repository;

import com.rat.squad.storage.entity.RawData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DataRepository extends JpaRepository<RawData, Long> {
    Optional<RawData> getFirstByIdAndDeletedFalse(Long id);

    @Modifying
    @Query("update RawData r set r.deleted = true where r.id = :id")
    void softDeleteById(Long id);
}