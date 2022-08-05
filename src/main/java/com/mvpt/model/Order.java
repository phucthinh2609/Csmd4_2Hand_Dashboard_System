package com.mvpt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mvpt.model.dto.CartDTO;
import com.mvpt.model.dto.OrderDTO;
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
@Table(name = "orders")
@Accessors(chain = true)
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

    @OneToMany(targetEntity = OrderItem.class, mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderItem> orderItems;

    public OrderDTO toOrderDTO() {
        return new OrderDTO()
                .setId(String.valueOf(id))
                .setGrandTotal(String.valueOf(grandTotal))
                .setQuantityTotal(String.valueOf(quantityTotal))
                .setUser(user.toUserDTO())
                .setType(type.toTypeDTO())
                .setSituation(situation.toSituationDTO())
                .setUnit(unit.toUnitDTO());
    }
}
