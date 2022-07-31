package com.mvpt.model.dto;

import com.mvpt.model.Category;
import com.mvpt.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductDTO {

    @NotBlank(message = "ID is required")
    private String id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Sku is required")
    private String sku;

    private String urlImage;

    private String description;

    private String price;

    private String quantity;

    private String sold;

    private String available;

    @Valid
    private CategoryDTO categoryDTO;

    public Product toProduct(Category category) {
        return new Product()
                .setId(Long.valueOf(id))
                .setTitle(title)
                .setSku(sku)
                .setUrlImage(urlImage)
                .setDescription(description)
                .setPrice(new BigDecimal(Long.parseLong(price)))
                .setQuantity(Integer.parseInt(quantity))
                .setSold(Integer.parseInt(sold))
                .setAvailable(Integer.parseInt(available))
                .setCategory(categoryDTO.toCategory());
    }
}
