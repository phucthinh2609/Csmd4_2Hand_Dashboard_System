package com.mvpt.controller.rest;

import com.mvpt.exception.DataInputException;
import com.mvpt.exception.EmailExistsException;
import com.mvpt.model.LocationRegion;
import com.mvpt.model.Role;
import com.mvpt.model.User;
import com.mvpt.model.UserInfo;
import com.mvpt.model.dto.ChangePwDTO;
import com.mvpt.model.dto.UserDTO;
import com.mvpt.service.locationRegion.LocationRegionService;
import com.mvpt.service.product.ProductService;
import com.mvpt.service.role.RoleService;
import com.mvpt.service.user.UserService;
import com.mvpt.service.userInfo.UserInfoService;
import com.mvpt.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @Autowired
    private AuthenticationManager authenticationManager;

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

        Long roleId = Long.valueOf(userDTO.getRole().getId());
        String email = userDTO.getEmail();

        userDTO.setId(String.valueOf(0L));
        userDTO.getUserInfo().setId(String.valueOf(0L));
        userDTO.getUserInfo().getLocationRegion().setId(String.valueOf(0L));
        userDTO.setActivated(true);

        Boolean existEmail = userService.existsByEmail(email);

        if (existEmail) {
            throw new EmailExistsException("Email already exist");
        }

        Optional<Role> role = roleService.findById(roleId);

        if (!role.isPresent()) {
            throw new DataInputException("Role ID invalid!!!");
        }

        try {
            UserDTO newUserDTO = userService.saveDTO(userDTO);
            return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);

        } catch (Exception ex) {
            throw new DataInputException("Please contact management!!!");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> doUpdate(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Optional<UserDTO> currentUserDTO = userService.getUserDTOById(Long.valueOf(userDTO.getId()));

        String currentUserInfoId = currentUserDTO.get().getUserInfo().getId();
        String currentLocationRegionId = currentUserDTO.get().getUserInfo().getLocationRegion().getId();

        userDTO.getUserInfo().setId(currentUserInfoId);
        userDTO.getUserInfo().getLocationRegion().setId(currentLocationRegionId);

        Optional<UserDTO> currentEmailUserDTO = userService.findProductDTOByEmailAndIdIsNot(userDTO.getEmail(), Long.valueOf(userDTO.getId()));

        if (currentEmailUserDTO.isPresent()) {
            throw new EmailExistsException("Email already exists!!!");
        }

        Optional<Role> currentRole = roleService.findById(Long.valueOf(userDTO.getRole().getId()));

        if (!currentRole.isPresent()){
            throw new DataInputException("Role not define!!!");
        }

        try {
            UserDTO newUserDTO = userService.saveDTO(userDTO);
            return new ResponseEntity<>(newUserDTO, HttpStatus.OK);

        } catch (Exception ex) {
            throw new DataInputException("Please contact management!!!");
        }
    }

    @PostMapping("/suspended/{id}")
    public ResponseEntity<?> doSuspended(@Validated @PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {
            try {
                userOptional.get().setDeleted(true);
                userService.save(userOptional.get());

                return new ResponseEntity<>(HttpStatus.CREATED);

            } catch (DataIntegrityViolationException ex) {
                throw new DataInputException("Invalid suspense infomation");
            }
        } else {
            throw new DataInputException("Invalid user information");
        }

    }

    @PostMapping("/password")
    public ResponseEntity<?> doChangePassword(@RequestBody ChangePwDTO user) {

        Optional<UserDTO> userDTO = userService.getUserDTOById(Long.valueOf(user.getId()));

        if (!userDTO.isPresent()) {
            throw new DataInputException("User is not define");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.get().getEmail(), user.getPassword()));


        userDTO.get().setPassword(user.getNewPassword());

        userService.save(userDTO.get().toUser());

        return  new ResponseEntity<>(HttpStatus.OK);
    }

 }
