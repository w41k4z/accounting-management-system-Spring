package com.alain.accounting_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ela-admin")
public class MainController {

    @GetMapping("")
    public String index() {
        return "/app/user-space";
    }

    @GetMapping("/error")
    public String error(@RequestParam String error, Model model) {
        model.addAttribute("error", error);
        return "error/error";
    }
}
