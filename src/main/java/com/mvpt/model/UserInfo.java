package com.mvpt.model;

import com.mvpt.model.dto.UserInfoDTO;
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
@Table(name = "user_infos")
@Accessors(chain = true)
public class UserInfo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phone;

    @Column(name = "url_image")
    private String urlImage;

    @OneToOne
    @JoinColumn(name = "location_region_id", nullable = false)
    private LocationRegion locationRegion;

    @OneToOne(mappedBy = "userInfo")
    private User user;

    public UserInfoDTO toUserInfoDTO() {
        return new UserInfoDTO()
                .setId(String.valueOf(id))
                .setFullName(fullName)
                .setPhone(phone)
                .setUrlImage(urlImage)
                .setLocationRegion(locationRegion.toLocationRegionDTO());
    }
}
