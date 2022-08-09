package com.mvpt.service.user;

import com.mvpt.model.User;
import com.mvpt.model.dto.UserDTO;
import com.mvpt.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends IGeneralService<User>, UserDetailsService {
    List<UserDTO> getAllUserDTOByDeletedIsFalse();

    Optional<UserDTO> getUserDTOById(Long id);

    Boolean existsByEmail(String email);

    Optional<UserDTO> findProductDTOByEmailAndIdIsNot(String email, Long id);

    UserDTO saveDTO(UserDTO userDTO);

    Optional<User> findByEmail(String email);

    Optional<UserDTO> findUserDTOByEmail(String email);

}
