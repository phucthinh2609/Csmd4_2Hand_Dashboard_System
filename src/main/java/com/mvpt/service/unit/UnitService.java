package com.mvpt.service.unit;

import com.mvpt.model.Unit;
import com.mvpt.model.dto.UnitDTO;
import com.mvpt.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface UnitService extends IGeneralService<Unit> {

    List<UnitDTO> getAllUnitDTO();

    Optional<UnitDTO> getUnitDTOById(Long unitId);

    Optional<UnitDTO> getUnitDTOByCodeAndIdIsNot(String code, Long id);

}
