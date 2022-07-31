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
    private UserDTO userDTO;

    @Valid
    private TypeDTO typeDTO;

    @Valid
    private SituationDTO situationDTO;

    @Valid
    private UnitDTO unitDTO;

    public Cart toCart() {
        return new Cart()
                .setId(Long.valueOf(id))
                .setGrandTotal(new BigDecimal(Long.parseLong(grandTotal)))
                .setQuantityTotal(Integer.parseInt(quantityTotal))
                .setUser(userDTO.toUser())
                .setType(typeDTO.toType())
                .setSituation(situationDTO.toSituation())
                .setUnit(unitDTO.toUnit());
    }
}
