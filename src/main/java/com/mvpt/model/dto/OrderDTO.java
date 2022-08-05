package com.mvpt.model.dto;

import com.mvpt.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class OrderDTO {

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

    public OrderDTO(Long id, BigDecimal grandTotal, int quantityTotal, User user, Type type, Situation situation, Unit unit) {
        this.id = String.valueOf(id);
        this.grandTotal = String.valueOf(grandTotal);
        this.quantityTotal = String.valueOf(quantityTotal);
        this.user = user.toUserDTO();
        this.type = type.toTypeDTO();
        this.situation = situation.toSituationDTO();
        this.unit = unit.toUnitDTO();
    }

    public Order toOrder() {
        return new Order()
                .setId(Long.valueOf(id))
                .setGrandTotal(new BigDecimal(Long.parseLong(grandTotal)))
                .setQuantityTotal(Integer.parseInt(quantityTotal))
                .setUser(user.toUser())
                .setType(type.toType())
                .setSituation(situation.toSituation())
                .setUnit(unit.toUnit());
    }
}
