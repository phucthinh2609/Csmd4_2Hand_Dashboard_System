package com.mvpt.model.dto;

import com.mvpt.model.Cart;
import com.mvpt.model.CartItem;
import com.mvpt.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CartItemDTO {

    private String id;

    private String price;

    private String quantity;

    private String totalPrice;

    @Valid
    private CartDTO cartDTO;

    @Valid
    private ProductDTO productDTO;

    public CartItem toCartItem() {
        return new CartItem()
                .setId(Long.valueOf(id))
                .setPrice(new BigDecimal(Long.parseLong(price)))
                .setQuantity(Integer.parseInt(quantity))
                .setTotalPrice(new BigDecimal(Long.parseLong(totalPrice)))
                .setCart(cartDTO.toCart());
    }
}
