package com.alain.accounting_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alain.accounting_management_system.model.Account;

import jakarta.servlet.http.HttpSession;
import orm.database.connection.DatabaseConnection;
import com.alain.accounting_management_system.connection.AppDBCon;

@Controller
@RequestMapping("/ela-admin/account")
public class AccountController {

    @GetMapping("/login")
    public String login(String error, Model model) {
        model.addAttribute("error", error == null ? "" : error);
        return "logIn/signIn";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String email, @RequestParam String password, Model model,
            HttpSession session) {
        DatabaseConnection connection = new AppDBCon();
        String result = "";
        try {
            Account account = Account.authenticate(connection, email, password);
            if (account != null) {
                session.setAttribute("account", account);
                result = "redirect:/ela-admin/society/home-page/dashboard";
            } else {
                result = this.login("Authentication failed. Please verify your informations !", model);
            }
        } catch (Exception e) {
            result = "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return result;
    }

    @PostMapping("/check-email")
    @ResponseBody
    public String checkEmail(@RequestParam String email) {
        DatabaseConnection connection = new AppDBCon();
        String result = "";
        try {
            Account[] accounts = new Account().findAll(connection, "WHERE email = '" + email + "'");
            result = accounts.length > 0 ? "found" : null;
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }

    @GetMapping("/register")
    public String register() {
        return "logIn/register";
    }
}
