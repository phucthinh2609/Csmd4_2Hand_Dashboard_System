package com.mvpt.model;

import com.mvpt.model.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
@Accessors(chain = true)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;

    @OneToMany(targetEntity = Product.class, mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Product> products;

    public CategoryDTO toCategoryDTO() {
        return new CategoryDTO()
                .setId(String.valueOf(id))
                .setName(name)
                .setCode(code);
    }
}
