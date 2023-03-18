package com.alain.accounting_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alain.accounting_management_system.model.Account;
import orm.database.connection.DatabaseConnection;
import com.alain.accounting_management_system.connection.AppDBCon;

@Controller
@RequestMapping("/app/account")
public class AccountController {

    @GetMapping("/login")
    public String login() {
        return "logIn/signIn";
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public String authenticate(@RequestParam String email, @RequestParam String password) {
        DatabaseConnection connection = new AppDBCon();
        String result = "";
        try {
            Account account = Account.authenticate(connection, email, password);
            result = account != null ? "success" : "error";
        } catch (Exception e) {
            result = "error";
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

    @GetMapping("/home-page")
    public String home() {
        return "app/home";
    }

    @GetMapping("/error")
    public String handle404Error() {
        return "error-404";
    }
}
