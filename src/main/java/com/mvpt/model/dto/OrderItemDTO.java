package com.mvpt.model.dto;

import com.mvpt.model.CartItem;
import com.mvpt.model.Order;
import com.mvpt.model.OrderItem;
import com.mvpt.model.Product;
import com.sun.org.apache.xpath.internal.operations.Or;
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
public class OrderItemDTO {

    private String id;

    private String price;

    private String quantity;

    private String totalPrice;

    @Valid
    private OrderDTO order;

    @Valid
    private ProductDTO product;

    public OrderItemDTO(Long id, BigDecimal price, int quantity, BigDecimal totalPrice, Order order, Product product) {
        this.id = String.valueOf(id);
        this.price = String.valueOf(price);
        this.quantity = String.valueOf(quantity);
        this.totalPrice = String.valueOf(totalPrice);
        this.order = order.toOrderDTO();
        this.product = product.toProductDTO();
    }

    public OrderItem toOrderItem() {
        return new OrderItem()
                .setId(Long.valueOf(id))
                .setPrice(new BigDecimal(price))
                .setQuantity(Integer.parseInt(quantity))
                .setTotalPrice(new BigDecimal(totalPrice))
                .setOrder(order.toOrder())
                .setProduct(product.toProduct());
    }
}
