package com.mvpt.model;

import com.mvpt.model.dto.SituationDTO;
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
@Table(name = "situations")
@Accessors(chain = true)
public class Situation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String code;

    @OneToMany(targetEntity = Cart.class, mappedBy = "situation", fetch = FetchType.EAGER)
    private Set<Cart> carts;

    @OneToMany(targetEntity = Order.class, mappedBy = "situation", fetch = FetchType.EAGER)
    private Set<Order> orders;

    public SituationDTO toSituationDTO() {
        return new SituationDTO()
                .setId(String.valueOf(id))
                .setName(name)
                .setCode(code);
    }
}
