package com.mvpt.controller.rest;

import com.mvpt.exception.DataInputException;
import com.mvpt.exception.EmailExistsException;
import com.mvpt.model.LocationRegion;
import com.mvpt.model.Role;
import com.mvpt.model.User;
import com.mvpt.model.UserInfo;
import com.mvpt.model.dto.UserDTO;
import com.mvpt.service.locationRegion.LocationRegionService;
import com.mvpt.service.product.ProductService;
import com.mvpt.service.role.RoleService;
import com.mvpt.service.user.UserService;
import com.mvpt.service.userInfo.UserInfoService;
import com.mvpt.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    LocationRegionService locationRegionService;

    @Autowired
    AppUtils appUtils;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<UserDTO> users = userService.getAllUserDTOByDeletedIsFalse();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<UserDTO> userDTO = userService.getUserDTOById(id);

        if (!userDTO.isPresent()){
            throw new DataInputException("User ID invalid");
        }

        return new ResponseEntity<>(userDTO.get(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        userDTO.setId(String.valueOf(0L));
        userDTO.getUserInfo().setId(String.valueOf(0L));
        userDTO.getUserInfo().getLocationRegion().setId(String.valueOf(0L));

        Boolean existEmail = userService.existsByEmail(userDTO.getEmail());

        if (existEmail) {
            throw new EmailExistsException("Email already exist");
        }

        Optional<Role> role = roleService.findById(userDTO.toUser().getRole().getId());

        if (!role.isPresent()) {
            throw new DataInputException("Role ID invalid!!!");
        }

        UserDTO newUserDTO = userService.saveDTO(userDTO);
        return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
//        try {
//
//
//        } catch (Exception ex) {
//            throw new DataInputException("Please contact management!!!");
//        }
    }
 }
