package com.mvpt.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/inventories")
public class InventoryController {

    @GetMapping()
    public ModelAndView showInventoryListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/inventory/list");
        return modelAndView;
    }
}
