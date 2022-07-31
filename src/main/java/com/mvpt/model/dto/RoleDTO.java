package com.mvpt.model.dto;

import com.mvpt.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class RoleDTO {

    private String id;

    private String name;

    private String code;

    public Role toRole() {
        return new Role()
                .setId(Long.valueOf(id))
                .setName(name)
                .setCode(code);
    }

}
