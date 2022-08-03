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
import javax.validation.constraints.*;
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
    @Size(max = 50, message = "The length of tile must be between 5 and 50 characters")
    private String title;

    @NotBlank(message = "Sku is required")
//    @Pattern(regexp = "^([A-Z]{2}\\-){2}\\d{4}$", message = "\"Format sku is 'US-AP-1029'. In there: 'US' is the country, 'AP' is the item name, '1029' is the item code\"")
    private String sku;

    private String urlImage;

    @Size(max = 50, message = "Maximum description must be 50 characters")
    private String description;

    @Pattern(regexp = "^[0-9]+$", message = "Price only digit")
    private String price;

    @Pattern(regexp = "^[0-9]+$", message = "Quantity only digit")
    private String quantity;

    @Pattern(regexp = "^[0-9]+$", message = "Sold only digit")
    private String sold;

    @Pattern(regexp = "^[0-9]+$", message = "Available only digit")
    private String available;

    private boolean imported;

    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;

    private String createdBy;

    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date updatedAt;

    private String updatedBy;

    @Valid
    private CategoryDTO category;

    public ProductDTO(Long id, String title, String sku, String urlImage, String description, BigDecimal price, int quantity, int sold, int available, boolean imported, Date createdAt, String createdBy, Date updatedAt, String updatedBy, Category category) {
        this.id = String.valueOf(id);
        this.title = title;
        this.sku = sku;
        this.urlImage = urlImage;
        this.description = description;
        this.price = String.valueOf(price);
        this.quantity = String.valueOf(quantity);
        this.sold = String.valueOf(sold);
        this.available = String.valueOf(available);
        this.imported = imported;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;

        this.category = category.toCategoryDTO();
    }

    public Product toProduct() {
        return (Product) new Product()
                .setId(Long.valueOf(id))
                .setTitle(title)
                .setSku(sku)
                .setUrlImage(urlImage)
                .setDescription(description)
                .setPrice(new BigDecimal(Long.parseLong(price)))
                .setQuantity(Integer.parseInt(quantity))
                .setSold(Integer.parseInt(sold))
                .setAvailable(Integer.parseInt(available))
                .setImported(imported)
                .setCategory(category.toCategory())
                .setCreatedAt(createdAt)
                .setCreatedBy(createdBy)
                .setUpdatedAt(updatedAt)
                .setUpdatedBy(updatedBy);
    }
}
