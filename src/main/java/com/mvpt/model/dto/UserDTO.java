package com.mvpt.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserDTO {

    private String id;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$", message = "Email must be with format: 'name@example.com'")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private boolean activated;

    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;
    private String createdBy;

    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date updatedAt;
    private String updatedBy;

    @Valid
    private RoleDTO role;

    @Valid
    private UserInfoDTO userInfo;

    public UserDTO(Long id, String email, String password, boolean activated, Date createdAt, String createdBy, Date updatedAt, String updatedBy, Role role, UserInfo userInfo) {
        this.id = String.valueOf(id);
        this.email = email;
        this.password = password;
        this.activated = activated;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.role = role.toRoleDTO();
        this.userInfo = userInfo.toUserInfoDTO();
    }

    public User toUser() {
        return (User) new User()
                .setId(Long.valueOf(id))
                .setEmail(email)
                .setPassword(password)
                .setActivated(activated)
                .setRole(role.toRole())
                .setUserInfo(userInfo.toUserInfo())
                .setCreatedAt(createdAt)
                .setCreatedBy(createdBy)
                .setUpdatedAt(updatedAt)
                .setUpdatedBy(updatedBy);
    }
}
