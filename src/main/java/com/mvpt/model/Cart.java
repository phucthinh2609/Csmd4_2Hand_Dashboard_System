package com.mvpt.model;

import com.mvpt.model.dto.CartDTO;
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
@Table(name = "carts")
@Accessors(chain = true)
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 12, fraction = 0)
    @Column(name = "grand_total", nullable = false)
    private BigDecimal grandTotal;

    @Column(name = "quantity_total", nullable = false)
    private int quantityTotal;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "situation_id", nullable = false)
    private Situation situation;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @OneToMany(targetEntity = CartItem.class, mappedBy = "cart")
    private Set<CartItem> cartItems;

    public CartDTO toCartDTO() {
        return new CartDTO()
                .setId(String.valueOf(id))
                .setGrandTotal(String.valueOf(grandTotal))
                .setQuantityTotal(String.valueOf(quantityTotal))
                .setUser(user.toUserDTO())
                .setType(type.toTypeDTO())
                .setSituation(situation.toSituationDTO())
                .setUnit(unit.toUnitDTO());
    }
}
