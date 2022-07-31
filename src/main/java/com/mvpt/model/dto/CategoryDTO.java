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

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Code is required")
    private String code;

    public Category toCategory() {
        return new Category()
                .setId(Long.valueOf(id))
                .setName(name)
                .setCode(code);
    }
}
