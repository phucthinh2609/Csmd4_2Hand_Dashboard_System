package com.mvpt.model;

import com.mvpt.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@Accessors(chain = true)
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(name = "url_image")
    private String urlImage;
    private String description;

    @Digits(integer = 12, fraction = 0)
    @Column(columnDefinition = "decimal default 0")
    private BigDecimal price;

    @Column(columnDefinition = "decimal default 0")
    private int quantity;

    @Column(columnDefinition = "integer default 0")
    private int sold;

    @Column(columnDefinition = "integer default 0")
    private int available;

    @Column(name = "is_imported", columnDefinition = "boolean default false")
    private boolean isImported;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(targetEntity = CartItem.class, mappedBy = "product", fetch = FetchType.EAGER)
    private Set<CartItem> cartItems;

    public ProductDTO toProductDTO() {
        return new ProductDTO()
                .setId(String.valueOf(id))
                .setTitle(title)
                .setSku(sku)
                .setUrlImage(urlImage)
                .setDescription(description)
                .setPrice(String.valueOf(price))
                .setQuantity(String.valueOf(quantity))
                .setSold(String.valueOf(sold))
                .setAvailable(String.valueOf(available))
                .setImported(isImported)
                .setCreatedAt(getCreatedAt())
                .setCreatedBy(getCreatedBy())
                .setUpdatedAt(getUpdatedAt())
                .setUpdatedBy(getUpdatedBy())
                .setCategory(category.toCategoryDTO());
    }
}
