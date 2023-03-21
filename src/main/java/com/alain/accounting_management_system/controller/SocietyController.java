package com.alain.accounting_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alain.accounting_management_system.connection.AppDBCon;
import com.alain.accounting_management_system.model.Account;
import com.alain.accounting_management_system.model.ChartOfAccount;
import com.alain.accounting_management_system.model.ThirdPartyChartOfAccount;

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
    public String chartOfAccount(@PathVariable String type, HttpSession session, Model model) {
        try {
            Account account = (Account) session.getAttribute("account");
            model.addAttribute("generalAccount", new ChartOfAccount().findAll(new AppDBCon(),
                    "WHERE society_id = '" + account.getSocietyAccounts()[0].getSocietyID() + "'"));
            model.addAttribute("thirdPartyAccount", new ThirdPartyChartOfAccount().findAll(new AppDBCon(),
                    "JOIN chart_of_account ON chart_of_account.id = chrt_acc_id WHERE chart_of_account.society_id = '"
                            + account.getSocietyAccounts()[0].getSocietyID() + "'"));
            return account == null ? "redirect:/ela-admin" : "app/society/chart-of-account/" + type;
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
    }

    @GetMapping("/disconnect")
    public String disconnect(HttpSession session) {
        session.invalidate();
        return "redirect:/ela-admin";
    }

}
