package com.mvpt.model.dto;

import com.mvpt.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CartDTO {

    private String id;

    private String grandTotal;

    private String quantityTotal;

    @Valid
    private UserDTO user;

    @Valid
    private TypeDTO type;

    @Valid
    private SituationDTO situation;

    @Valid
    private UnitDTO unit;

    public Cart toCart() {
        return new Cart()
                .setId(Long.valueOf(id))
                .setGrandTotal(new BigDecimal(Long.parseLong(grandTotal)))
                .setQuantityTotal(Integer.parseInt(quantityTotal))
                .setUser(user.toUser())
                .setType(type.toType())
                .setSituation(situation.toSituation())
                .setUnit(unit.toUnit());
    }
}
