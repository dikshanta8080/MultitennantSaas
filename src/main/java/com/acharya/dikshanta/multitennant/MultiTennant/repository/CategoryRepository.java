package com.acharya.dikshanta.multitennant.MultiTennant.repository;

import com.acharya.dikshanta.multitennant.MultiTennant.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>, JpaSpecificationExecutor<Category> {
    boolean existsByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Category c SET c.deleted = true WHERE c.id = :id")
    int softDeleteById(@Param("id") UUID id);


}
