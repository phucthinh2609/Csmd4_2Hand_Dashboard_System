package com.mvpt.repository;

import com.mvpt.model.Role;
import com.mvpt.model.Situation;
import com.mvpt.model.dto.SituationDTO;
import com.mvpt.model.dto.TypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SituationRepository extends JpaRepository<Situation, Long> {

    @Query("SELECT NEW com.mvpt.model.dto.SituationDTO (" +
                "t.id, " +
                "t.name, " +
                "t.code) " +
            "FROM Situation t ")
    List<SituationDTO> getAllRoleDTO();

    Situation findByName(String name);
}
