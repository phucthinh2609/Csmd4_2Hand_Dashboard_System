package com.mvpt.service.situation;

import com.mvpt.model.Situation;
import com.mvpt.model.dto.SituationDTO;
import com.mvpt.service.IGeneralService;

import java.util.List;

public interface SituationService extends IGeneralService<Situation> {
    List<SituationDTO> getAllTypeDTO();

    Situation findByName(String name);
}
