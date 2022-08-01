package com.mvpt.model.dto;

import com.mvpt.model.Category;
import com.mvpt.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CategoryDTO {

    @NotBlank(message = "ID is required")
    @Pattern(regexp = "^[0-9]+$", message = "Category ID only digit")
    private String id;

    private String name;

    private String code;

    public CategoryDTO(Long id, String name, String code) {
        this.id = String.valueOf(id);
        this.name = name;
        this.code = code;
    }

    public Category toCategory() {
        return new Category()
                .setId(Long.valueOf(id))
                .setName(name)
                .setCode(code);
    }
}
