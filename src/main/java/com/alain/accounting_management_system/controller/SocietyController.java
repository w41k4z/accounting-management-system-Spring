package com.alain.accounting_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alain.accounting_management_system.connection.AppDBCon;
import com.alain.accounting_management_system.model.Account;
import com.alain.accounting_management_system.model.ChartOfAccount;
import com.alain.accounting_management_system.model.Journal;
import com.alain.accounting_management_system.model.JournalCode;
import com.alain.accounting_management_system.model.ThirdPartyChartOfAccount;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ela-admin/society")
public class SocietyController {

    @GetMapping("/home-page/{page}")
    public String home(@PathVariable String page, HttpSession session, Model model) {
        try {
            Account account = (Account) session.getAttribute("account");
            model.addAttribute("account", account);
            model.addAttribute("general_ledger",
                    Journal.getGeneralLedger(new AppDBCon(), account.getSocietyAccounts()[0].getSocietyID()));
            return account != null ? "/app/society/" + page : "redirect:/ela-admin";
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
    }

    @GetMapping("/home-page/chart-of-account/{type}")
    public String chartOfAccount(@PathVariable String type, HttpSession session, Model model) {
        try {
            Account account = (Account) session.getAttribute("account");
            model.addAttribute("generalAccount", new ChartOfAccount().findAll(new AppDBCon(),
                    "WHERE society_id = '" + account.getSocietyAccounts()[0].getSocietyID() + "' ORDER BY id"));
            model.addAttribute("thirdPartyAccount", new ThirdPartyChartOfAccount().findAll(new AppDBCon(),
                    "WHERE society_id = '" + account.getSocietyAccounts()[0].getSocietyID() + "'"));
            return account == null ? "redirect:/ela-admin" : "app/society/chart-of-account/" + type;
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
    }

    @GetMapping("/home-page/journal/{type}")
    public String journal(@PathVariable String type, HttpServletRequest request, HttpSession session, Model model) {
        String date = request.getParameter("date");
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            String month = date == null ? "(SELECT EXTRACT(MONTH FROM CURRENT_DATE))" : date.split("-")[1];
            String year = date == null ? "(SELECT EXTRACT(YEAR FROM CURRENT_DATE))" : date.split("-")[0];
            Account account = (Account) session.getAttribute("account");
            model.addAttribute("journalCodes", new JournalCode().findAll(new AppDBCon()));
            model.addAttribute("journals", new Journal().findAll(new AppDBCon(),
                    "WHERE EXTRACT(MONTH FROM journal_date) = " + month + " AND EXTRACT(YEAR FROM journal_date) = "
                            + year + " ORDER BY id"));
            String view = type.contains("?") ? type.split("\\?")[0] : type;
            return account == null ? "redirect:/ela-admin" : "app/society/journal/" + view;
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
    }

    @PostMapping("/home-page/general-ledger/details")
    public String generalLedgerDetail(HttpServletRequest request, HttpSession session, Model model) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        Account account = (Account) session.getAttribute("account");
        try {
            model.addAttribute("journals", new Journal().findAll(new AppDBCon(), "WHERE society_id = '"
                    + account.getSocietyAccounts()[0].getSocietyID() + "' AND part_reference = '"
                    + request.getParameter("partRef")
                    + "' AND journal_date = '" + request.getParameter("date") + "'"));
            return "/app/society/journal/detail";
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
