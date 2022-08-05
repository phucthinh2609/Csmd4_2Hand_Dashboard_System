package com.mvpt.repository;

import com.mvpt.model.Unit;
import com.mvpt.model.dto.RoleDTO;
import com.mvpt.model.dto.UnitDTO;
import com.mvpt.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    @Query("SELECT NEW com.mvpt.model.dto.UnitDTO (" +
                "u.id, " +
                "u.name, " +
                "u.code, " +
                "u.locationRegion) " +
            "FROM Unit u ")
    List<UnitDTO> getAllUnitDTO();

    @Query("SELECT NEW com.mvpt.model.dto.UnitDTO (" +
                "u.id, " +
                "u.name, " +
                "u.code, " +
                "u.locationRegion) " +
            "FROM Unit u " +
            "WHERE u.id = ?1")
    Optional<UnitDTO> getUnitDTOById(Long unitId);

    @Query("SELECT NEW com.mvpt.model.dto.UnitDTO (" +
                "u.id, " +
                "u.name, " +
                "u.code, " +
                "u.locationRegion) " +
            "FROM Unit u WHERE u.code = ?1 AND u.id <> ?2 ")
    Optional<UnitDTO> getUnitDTOByCodeAndIdIsNot(String code, Long id);
}
