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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class RoleDTO {

    @NotBlank(message = "Role ID is required")
    @Pattern(regexp = "^[0-9]+$", message = "Category ID only digit")
    private String id;

    private String name;

    private String code;

    public RoleDTO(Long id, String name, String code) {
        this.id = String.valueOf(id);
        this.name = name;
        this.code = code;
    }

    public Role toRole() {
        return new Role()
                .setId(Long.valueOf(id))
                .setName(name)
                .setCode(code);
    }

}
