package com.mvpt.model.dto;

import com.mvpt.model.LocationRegion;
import com.mvpt.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UnitDTO {

//    @NotBlank(message = "Unit ID is required")
    @Pattern(regexp = "^[0-9]+$", message = "Unit ID only digit")
    private String id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Code is required")
    @Pattern(regexp = "^UNIT-+\\w{3}$", message = "\"Format unit code is 'UNIT-CDM'. In there: 'UNIT' is the unit code, 'CDM' is the business symbol")
    private String code;

    public UnitDTO(Long id, String name, String code, LocationRegion locationRegion) {
        this.id = String.valueOf(id);
        this.name = name;
        this.code = code;
        this.locationRegion = locationRegion.toLocationRegionDTO();
    }

    @Valid
    private LocationRegionDTO locationRegion;

    public Unit toUnit() {
        return new Unit()
                .setId(Long.valueOf(id))
                .setName(name)
                .setCode(code)
                .setLocationRegion(locationRegion.toLocationRegion());
    }
}
