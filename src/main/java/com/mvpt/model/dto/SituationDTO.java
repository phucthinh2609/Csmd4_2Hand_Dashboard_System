package com.mvpt.model.dto;

import com.mvpt.model.Situation;
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
public class SituationDTO {

    @NotBlank(message = "Status ID is required")
    @Pattern(regexp = "^[0-9]+$", message = "Status ID only digit")
    private String id;

    private String name;

    private String code;

    public Situation toSituation() {
        return new Situation()
                .setId(Long.valueOf(id))
                .setName(name)
                .setCode(code);
    }
}
