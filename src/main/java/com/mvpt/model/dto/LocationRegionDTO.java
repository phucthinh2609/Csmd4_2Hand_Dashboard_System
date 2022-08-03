package com.mvpt.model.dto;

import com.mvpt.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class LocationRegionDTO {

    private String id;

    @NotBlank(message = "Province ID is required")
    @Pattern(regexp = "^[0-9]+$", message = "Province ID only digit")
    private String provinceId;
    private String provinceName;

    @NotBlank(message = "District ID is required")
    @Pattern(regexp = "^[0-9]+$", message = "District ID only digit")
    private String districtId;
    private String districtName;

    @NotBlank(message = "Ward ID is required")
    @Pattern(regexp = "^[0-9]+$", message = "Ward ID only digit")
    private String wardId;
    private String wardName;

    @NotBlank(message = "Address is required")
    private String address;

    public LocationRegion toLocationRegion() {
        return new LocationRegion()
                .setId(Long.valueOf(id))
                .setProvinceId(Long.valueOf(provinceId))
                .setProvinceName(provinceName)
                .setDistrictId(Long.valueOf(districtId))
                .setDistrictName(districtName)
                .setWardId(Long.valueOf(wardId))
                .setWardName(wardName)
                .setAddress(address);
    }
}
