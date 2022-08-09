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
                "u.activated, " +
                "u.createdAt," +
                "u.createdBy," +
                "u.updatedAt," +
                "u.updatedBy," +
                "u.role, " +
                "u.userInfo) " +
            "FROM User u " +
            "WHERE u.deleted = false ")
    List<UserDTO> getAllUserDTOByDeletedIsFalse();

    @Query("SELECT NEW com.mvpt.model.dto.UserDTO (" +
                "u.id, " +
                "u.email, " +
                "u.password, " +
                "u.activated, " +
                "u.createdAt," +
                "u.createdBy," +
                "u.updatedAt," +
                "u.updatedBy," +
                "u.role, " +
                "u.userInfo) " +
            "FROM User u " +
            "WHERE u.id = ?1 ")
    Optional<UserDTO> getUserDTOById(Long id);

    Boolean existsByEmail(String email);

    @Query("SELECT NEW com.mvpt.model.dto.UserDTO (" +
                "u.id, " +
                "u.email, " +
                "u.password, " +
                "u.activated, " +
                "u.createdAt," +
                "u.createdBy," +
                "u.updatedAt," +
                "u.updatedBy," +
                "u.role, " +
                "u.userInfo) " +
            "FROM User u WHERE u.email = ?1 AND u.id <> ?2 ")
    Optional<UserDTO> findUserDTOByEmailAndIdIsNot(String email, Long id);

    @Query("SELECT NEW com.mvpt.model.dto.UserDTO (" +
                "u.id, " +
                "u.email, " +
                "u.password, " +
                "u.activated, " +
                "u.createdAt," +
                "u.createdBy," +
                "u.updatedAt," +
                "u.updatedBy," +
                "u.role, " +
                "u.userInfo) " +
            "FROM User u " +
            "WHERE u.email = ?1")
    Optional<UserDTO> findUserDTOByEmail(String email);

    Optional<User> findByEmail(String email);

}
