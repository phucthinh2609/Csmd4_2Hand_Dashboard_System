package com.mvpt.model;

import com.mvpt.model.dto.UnitDTO;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "units")
@Accessors(chain = true)
public class Unit extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @OneToOne
    @JoinColumn(name = "location_region_id", nullable = false)
    private LocationRegion locationRegion;

    @OneToMany(targetEntity = Cart.class, mappedBy = "unit")
    private Set<Cart> carts;

    @OneToMany(targetEntity = Order.class, mappedBy = "unit")
    private Set<Order> orders;

    public UnitDTO toUnitDTO() {
        return new UnitDTO()
                .setId(String.valueOf(id))
                .setName(name)
                .setCode(code)
                .setLocationRegionDTO(locationRegion.toLocationRegionDTO());
    }
}
