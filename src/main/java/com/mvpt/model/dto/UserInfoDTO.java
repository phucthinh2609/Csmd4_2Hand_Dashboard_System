package com.mvpt.model.dto;

import com.mvpt.model.LocationRegion;
import com.mvpt.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserInfoDTO {

    private String id;

    @NotBlank(message = "Full Name is required")
    @Size(max = 50, message = "The length of full name must be between 5 and 50 characters")
    private String fullName;

    @NotBlank(message = "Phone is required")
    private String phone;

    private String urlImage;

    @Valid
    private LocationRegionDTO locationRegion;

    public UserInfo toUserInfo() {
        return new UserInfo()
                .setId(Long.valueOf(id))
                .setFullName(fullName)
                .setPhone(phone)
                .setUrlImage(urlImage)
                .setLocationRegion(locationRegion.toLocationRegion());
    }
}
