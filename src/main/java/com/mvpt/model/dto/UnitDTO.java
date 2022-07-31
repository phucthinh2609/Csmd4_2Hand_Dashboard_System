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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UnitDTO {

    private String id;

    private String name;

    private String code;

    @Valid
    private LocationRegionDTO locationRegionDTO;

    public Unit toUnit() {
        return new Unit()
                .setId(Long.valueOf(id))
                .setName(name)
                .setCode(code)
                .setLocationRegion(locationRegionDTO.toLocationRegion());
    }
}
