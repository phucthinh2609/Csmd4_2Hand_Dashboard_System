package com.mvpt.service.role;

import com.mvpt.model.Role;
import com.mvpt.model.dto.CategoryDTO;
import com.mvpt.model.dto.RoleDTO;
import com.mvpt.service.IGeneralService;

import java.util.List;

public interface RoleService extends IGeneralService<Role> {
    List<RoleDTO> getAllRoleDTO();


    Role findByName(String name);
}
