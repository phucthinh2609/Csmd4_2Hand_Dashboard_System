package com.mvpt.repository;

import com.mvpt.model.Category;
import com.mvpt.model.Role;
import com.mvpt.model.dto.CategoryDTO;
import com.mvpt.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT NEW com.mvpt.model.dto.CategoryDTO (" +
            "c.id, " +
            "c.name, " +
            "c.code) " +
            "FROM Category c ")
    List<CategoryDTO> getAllCategoryDTO();

    Category findByName(String name);
}
