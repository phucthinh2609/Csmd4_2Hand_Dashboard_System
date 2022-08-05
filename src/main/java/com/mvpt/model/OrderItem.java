package com.mvpt.model;

import com.mvpt.model.dto.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_items")
@Accessors(chain = true)
public class OrderItem extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 12, fraction = 0)
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    @Digits(integer = 12, fraction = 0)
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public OrderItemDTO toOrderItemDTO() {
        return new OrderItemDTO()
                .setId(String.valueOf(id))
                .setPrice(String.valueOf(price))
                .setQuantity(String.valueOf(quantity))
                .setTotalPrice(String.valueOf(totalPrice))
                .setOrder(order.toOrderDTO())
                .setProduct(product.toProductDTO());
    }

}
