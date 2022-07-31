package com.mvpt.model.dto;

import com.mvpt.model.Role;
import com.mvpt.model.User;
import com.mvpt.model.UserInfo;
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
public class UserDTO {

    private String id;

    private String email;

    private String password;

    private boolean isActive;

    @Valid
    private RoleDTO roleDTO;

    @Valid
    private UserInfoDTO userInfoDTO;

    public User toUser() {
        return new User()
                .setId(Long.valueOf(id))
                .setEmail(email)
                .setPassword(password)
                .setActive(isActive);
    }
}
