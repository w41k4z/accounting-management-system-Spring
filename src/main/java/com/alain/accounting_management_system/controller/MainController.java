package com.alain.accounting_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class MainController {

    @GetMapping("")
    public String index() {
        return "redirect:/app/account/login";
    }
}
