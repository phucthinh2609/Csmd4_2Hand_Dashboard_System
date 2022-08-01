package com.mvpt.service.category;

import com.mvpt.model.Category;
import com.mvpt.model.dto.CategoryDTO;
import com.mvpt.model.dto.ProductDTO;
import com.mvpt.service.IGeneralService;

import java.util.List;

public interface CategoryService extends IGeneralService<Category> {
    List<CategoryDTO> getAllCategoryDTO();

    Category findByName(String name);


}
