package com.mvpt.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mvpt.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

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

    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;

    private String createdBy;

    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date updatedAt;

    private String updatedBy;


    public OrderDTO(Long id, BigDecimal grandTotal, int quantityTotal, User user, Type type, Situation situation, Unit unit, Date createdAt, String createdBy, Date updatedAt, String updatedBy) {
        this.id = String.valueOf(id);
        this.grandTotal = String.valueOf(grandTotal);
        this.quantityTotal = String.valueOf(quantityTotal);
        this.user = user.toUserDTO();
        this.type = type.toTypeDTO();
        this.situation = situation.toSituationDTO();
        this.unit = unit.toUnitDTO();
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public Order toOrder() {
        return (Order) new Order()
                .setId(Long.valueOf(id))
                .setGrandTotal(new BigDecimal(Long.parseLong(grandTotal)))
                .setQuantityTotal(Integer.parseInt(quantityTotal))
                .setUser(user.toUser())
                .setType(type.toType())
                .setSituation(situation.toSituation())
                .setUnit(unit.toUnit())
                .setCreatedAt(createdAt)
                .setCreatedBy(createdBy)
                .setUpdatedAt(updatedAt)
                .setUpdatedBy(updatedBy);
    }
}
