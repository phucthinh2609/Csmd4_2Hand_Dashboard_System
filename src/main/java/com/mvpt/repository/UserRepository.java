package com.mvpt.repository;

import com.mvpt.model.User;
import com.mvpt.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT NEW com.mvpt.model.dto.UserDTO (" +
                "u.id, " +
                "u.email, " +
                "u.password, " +
                "u.isActive, " +
                "u.role, " +
                "u.userInfo) " +
            "FROM User u " +
            "WHERE u.deleted = false ")
    List<UserDTO> getAllUserDTOByDeletedIsFalse();

    @Query("SELECT NEW com.mvpt.model.dto.UserDTO (" +
                "u.id, " +
                "u.email, " +
                "u.password, " +
                "u.isActive, " +
                "u.role, " +
                "u.userInfo) " +
            "FROM User u " +
            "WHERE u.deleted = false ")
    Optional<UserDTO> getUserDTOById(Long id);
}
