package com.alain.accounting_management_system.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alain.accounting_management_system.connection.AppDBCon;
import com.alain.accounting_management_system.converter.CsvConverter;
import com.alain.accounting_management_system.model.Account;
import com.alain.accounting_management_system.model.ChartOfAccount;
import com.alain.accounting_management_system.model.Society;
import com.alain.accounting_management_system.model.ThirdPartyChartOfAccount;
import com.alain.accounting_management_system.reader.CsvReader;

import jakarta.servlet.http.HttpSession;
import orm.database.connection.DatabaseConnection;

@Controller
@RequestMapping("/ela-admin/society/chart-of-account")
public class ChartOfAccountController {

    public File convertMultipartFileToFile(MultipartFile multipartFile) throws Exception {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(multipartFile.getBytes());
        outputStream.close();
        return file;
    }

    @PostMapping("/import-general-account")
    public String importChartOfAccount(@RequestParam MultipartFile file, HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            DatabaseConnection connection = new AppDBCon().defaultConnection();
            Account account = (Account) session.getAttribute("account");
            File uploadedFile = convertMultipartFileToFile(file);
            CsvConverter csvConverter = new CsvConverter(uploadedFile);
            CsvReader csvReader = new CsvReader(csvConverter.convertToCsv(), ";;");
            ChartOfAccount.insertImportedData(connection, csvReader.getContent(),
                    account.getSocietyAccounts()[0].getSocietyID());
            connection.commit();
            connection.close();
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/chart-of-account/general";
    }

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
