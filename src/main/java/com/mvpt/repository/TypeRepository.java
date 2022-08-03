package com.mvpt.repository;

import com.mvpt.model.Role;
import com.mvpt.model.Type;
import com.mvpt.model.dto.TypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    @Query("SELECT NEW com.mvpt.model.dto.TypeDTO (" +
                "t.id, " +
                "t.name, " +
                "t.code) " +
            "FROM Type t ")
    List<TypeDTO> getAllTypeDTO();

    Type findByName(String name);
}
