package com.mvpt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CartImportDTO implements Validator {

    @NotBlank(message = "User ID is required")
    @Pattern(regexp = "^[0-9]+$", message = "User ID only digit")
    private String userId;

    @NotBlank(message = "Product ID is required")
    @Pattern(regexp = "^[0-9]+$", message = "Product ID only digit")
    private String productId;

    private String quantity;

    private String price;

    @NotBlank(message = "Type ID is required")
    @Pattern(regexp = "^[0-9]+$", message = "Type ID only digit")
    private String typeId;

    @NotBlank(message = "Type ID is required")
    @Pattern(regexp = "^[0-9]+$", message = "Type ID only digit")
    private String unitId;

    @Override
    public boolean supports(Class<?> clazz) {
        return CartImportDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CartImportDTO cartImportDTO = (CartImportDTO) target;

        //Validator quantity

        String quantityStr = cartImportDTO.getQuantity();

        if (quantityStr == null) {
            errors.rejectValue("quantity", "quantity.null(", "The quantity is not null");
            return;
        }

        if (quantityStr.isEmpty()) {
            errors.rejectValue("quantity", "quantity.isEmpty(", "The quantity is not empty");
            return;
        }

        if (!quantityStr.matches("(^$|[0-9]*$)")){
            errors.rejectValue("quantity", "quantity.matches", "The quantity amount only digit");
            return;
        }

        int quantity = Integer.parseInt(cartImportDTO.getQuantity());

        if (quantity < 1) {
            errors.rejectValue("quantity", "quantity.min", "The quantity min is 1");
            return;
        }

        if (quantity > 100) {
            errors.rejectValue("quantity", "quantity.max", "The quantity max is 100");
            return;
        }

        //Validator price

        String priceStr = cartImportDTO.getPrice();

        if (priceStr == null) {
            errors.rejectValue("price", "price.null(", "The price is not null");
            return;
        }

        if (priceStr.isEmpty()) {
            errors.rejectValue("price", "price.isEmpty(", "The price is not empty");
            return;
        }

        if (!priceStr.matches("(^$|[0-9]*$)")){
            errors.rejectValue("price", "price.matches", "The price only digit");
            return;
        }

        BigDecimal price = new BigDecimal(Long.parseLong(cartImportDTO.getPrice()));
        BigDecimal min = new BigDecimal(1L);
        BigDecimal max = new BigDecimal(1000L);

        if (price.compareTo(min) > 0) {
            errors.rejectValue("price", "price.min", "The price max is 1.000$");
            return;
        }

        if (price.compareTo(max) < 0) {
            errors.rejectValue("price", "price.max", "The price min is 1$");
            return;
        }

    }
}
