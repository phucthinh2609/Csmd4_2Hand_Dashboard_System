package com.mvpt.controller;

import com.mvpt.model.dto.UserDTO;
import com.mvpt.service.user.UserService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    UserService userService;

    private String getPrincipal() {
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = "";
        }
        return email;
    }

    private UserDTO getUserDTO(){
        String email = getPrincipal();
        Optional<UserDTO> userDTOOptional = userService.findUserDTOByEmail(email);
        if (!userDTOOptional.isPresent()){
            return null;
        }
        return userDTOOptional.get();
    }

    @GetMapping()
    public ModelAndView showInventoryListPage() {
        ModelAndView modelAndView = new ModelAndView();
        UserDTO userDTO = getUserDTO();
        modelAndView.addObject("userDTO", userDTO);
        modelAndView.setViewName("/inventory/list");
        return modelAndView;
    }
}
