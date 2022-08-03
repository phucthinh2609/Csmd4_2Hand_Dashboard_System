package com.mvpt.controller.rest;

import com.mvpt.model.Role;
import com.mvpt.model.dto.RoleDTO;
import com.mvpt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<RoleDTO> roleDTOList = roleRepository.getAllRoleDTO();
        return new ResponseEntity<>(roleDTOList, HttpStatus.OK);
    }
}
