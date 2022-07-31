package com.mvpt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 12, fraction = 0)
    @Column(name = "grand_total", nullable = false)
    private BigDecimal grandTotal;

    @Column(name = "quantity_total", nullable = false)
    private int quantityTotal;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = true)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "situation_id", nullable = true)
    private Situation situation;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = true)
    private Unit unit;

    @OneToMany(targetEntity = OrderItem.class, mappedBy = "order")
    private Set<OrderItem> orderItems;
}
