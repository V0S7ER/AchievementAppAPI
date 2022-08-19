package com.example.api.controller;

import com.example.api.Global;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SuppressWarnings("unused")
public class DefaultController {

    @GetMapping("/")
    @PreAuthorize(value = Global.MAY_ALL_ROLES)
    public String getIndexPage(Model model) {
        return "index";
    }
}
