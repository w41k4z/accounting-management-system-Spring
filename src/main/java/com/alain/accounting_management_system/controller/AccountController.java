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

    @GetMapping("/register")
    public String register() {
        return "logIn/register";
    }

    @PostMapping("/create_account")
    public String createAccount() {
        return "logIn/register";
    }

    @GetMapping("/error")
    public String handle404Error() {
        return "error-404";
    }
}
