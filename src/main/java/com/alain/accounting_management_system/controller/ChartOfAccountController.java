package com.alain.accounting_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alain.accounting_management_system.connection.AppDBCon;
import com.alain.accounting_management_system.model.ChartOfAccount;
import com.alain.accounting_management_system.model.Society;
import com.alain.accounting_management_system.model.ThirdPartyChartOfAccount;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ela-admin/society/chart-of-account")
public class ChartOfAccountController {

    @PostMapping("/new-general-account")
    public String addNewChartOfAccount(@RequestParam String accountNumber, @RequestParam String entitled,
            HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            Society currentSociety = (Society) session.getAttribute("account");

            ChartOfAccount newChartOfAccount = new ChartOfAccount();
            newChartOfAccount.setAccountNumber(accountNumber);
            newChartOfAccount.setEntitled(entitled);
            newChartOfAccount.setSocietyID(currentSociety.getSocietyID());

            newChartOfAccount.create(new AppDBCon());
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/chart-of-account/general";
    }

    @PostMapping("/new-third-party-account")
    public String addNewThirdPartyChartOfAccount(@RequestParam String accountNumber, @RequestParam String type,
            @RequestParam String name,
            @RequestParam String entitled,
            HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            ThirdPartyChartOfAccount newThirdPartyChartOfAccount = new ThirdPartyChartOfAccount();
            newThirdPartyChartOfAccount.setChartOfAccountID(accountNumber);
            newThirdPartyChartOfAccount.setKey(type);
            newThirdPartyChartOfAccount.setValue(name);

            newThirdPartyChartOfAccount.create(new AppDBCon());
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/chart-of-account/third-party";
    }
}
