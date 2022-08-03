package com.mvpt.repository;

import com.mvpt.model.Role;
import com.mvpt.model.dto.CategoryDTO;
import com.mvpt.model.dto.RoleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT NEW com.mvpt.model.dto.RoleDTO (" +
            "r.id, " +
            "r.name, " +
            "r.code) " +
            "FROM Role r ")
    List<RoleDTO> getAllRoleDTO();

    Role findByName(String name);
}
