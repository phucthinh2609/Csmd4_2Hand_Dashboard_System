package com.mvpt.model;

import com.mvpt.model.dto.UserDTO;
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
@Table(name = "users")
@Accessors(chain = true)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @OneToMany(targetEntity = Cart.class, mappedBy = "user")
    private Set<Cart> carts;

    @OneToMany(targetEntity = Order.class, mappedBy = "user")
    private Set<Order> orders;

    public UserDTO toUserDTO() {
        return new UserDTO()
                .setId(String.valueOf(id))
                .setEmail(email)
                .setPassword(password)
                .setActive(isActive);
    }

}
