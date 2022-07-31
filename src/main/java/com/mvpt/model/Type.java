package com.mvpt.model;

import com.mvpt.model.dto.TypeDTO;
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
@Table(name = "types")
@Accessors(chain = true)
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @OneToMany(targetEntity = Cart.class, mappedBy = "type")
    private Set<Cart> carts;

    @OneToMany(targetEntity = Order.class, mappedBy = "type")
    private Set<Order> orders;

    public TypeDTO toTypeDTO(){
        return new TypeDTO()
                .setId(String.valueOf(id))
                .setName(name)
                .setCode(code);
    }
}
