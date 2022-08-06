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
public class CartPurchaseDTO implements Validator {

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
        return CartPurchaseDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CartPurchaseDTO cartImportDTO = (CartPurchaseDTO) target;

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

        if (quantity > 1000) {
            errors.rejectValue("quantity", "quantity.max", "The quantity max is 100");
            return;
        }

    }
}
