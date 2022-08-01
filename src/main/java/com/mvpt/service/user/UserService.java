package com.mvpt.service.user;

import com.mvpt.model.User;
import com.mvpt.model.dto.UserDTO;
import com.mvpt.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface UserService extends IGeneralService<User> {
    List<UserDTO> getAllUserDTOByDeletedIsFalse();

    Optional<UserDTO> getUserDTOById(Long id);

}
