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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class SituationDTO {

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
