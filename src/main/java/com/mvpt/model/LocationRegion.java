package com.mvpt.model;

import com.mvpt.model.dto.LocationRegionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "location_regions")
@Accessors(chain = true)
public class LocationRegion extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "ward_id")
    private Long wardId;

    @Column(name = "ward_name")
    private String wardName;

    private String address;

    @OneToOne(mappedBy = "locationRegion")
    private UserInfo userInfo;

    @OneToOne(mappedBy = "locationRegion")
    private Unit unit;

    public LocationRegionDTO toLocationRegionDTO() {
        return new LocationRegionDTO()
                .setId(String.valueOf(id))
                .setProvinceId(String.valueOf(provinceId))
                .setProvinceName(provinceName)
                .setDistrictId(String.valueOf(districtId))
                .setDistrictName(districtName)
                .setWardId(String.valueOf(wardId))
                .setWardName(wardName)
                .setAddress(address);
    }
}

