package com.ttalang.admin.security.controller;

import com.ttalang.admin.security.config.auth.PrincipalDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping({"", "/"})
    public String index() {
        return "redirect:/login/form";
    }

    @GetMapping("/login/form")
    public String loginForm() {
        return "loginForm";
    }

}
