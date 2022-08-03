package com.mvpt.service.type;

import com.mvpt.model.Type;
import com.mvpt.model.dto.TypeDTO;
import com.mvpt.service.IGeneralService;

import java.util.List;

public interface TypeService extends IGeneralService<Type> {
    List<TypeDTO> getAllTypeDTO();


    Type findByName(String name);
}
