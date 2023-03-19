package com.alain.accounting_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alain.accounting_management_system.model.Account;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ela-admin/society")
public class SocietyController {

    @GetMapping("/home-page/{page}")
    public String home(@PathVariable String page, HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("account");
        model.addAttribute("account", account);
        return account != null ? "/app/society/" + page : "redirect:/ela-admin";
    }

    @GetMapping("/home-page/chart-of-account/{type}")
    public String chartOfAccount(@PathVariable String type, HttpSession session) {
        return session.getAttribute("account") == null ? "redirect:/ela-admin" : "app/society/chart-of-account/" + type;
    }

    @GetMapping("/disconnect")
    public String disconnect(HttpSession session) {
        session.invalidate();
        return "redirect:/ela-admin";
    }

}
