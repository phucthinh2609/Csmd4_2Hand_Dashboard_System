package com.mvpt.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mvpt.model.Category;
import com.mvpt.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

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
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductDTO {

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

    private boolean isImported;

    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Asia/Ho Chi Minh")
    private Date createdAt;

    private String createdBy;

    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Asia/Ho Chi Minh")
    private Date updatedAt;

    private String updatedBy;

    @Valid
    private CategoryDTO category;

    public ProductDTO(Long id, String title, String sku, String urlImage, String description, BigDecimal price, int quantity, int sold, int available, boolean isImported, Date createdAt, String createdBy, Date updatedAt, String updatedBy, Category category) {
        this.id = String.valueOf(id);
        this.title = title;
        this.sku = sku;
        this.urlImage = urlImage;
        this.description = description;
        this.price = String.valueOf(price);
        this.quantity = String.valueOf(quantity);
        this.sold = String.valueOf(sold);
        this.available = String.valueOf(available);
        this.isImported = isImported;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;

        this.category = category.toCategoryDTO();
    }

    public Product toProduct() {
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
                .setImported(isImported)
                .setCategory(category.toCategory());
    }
}
