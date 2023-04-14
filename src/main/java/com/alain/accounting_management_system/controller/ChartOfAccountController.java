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

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws Exception {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(multipartFile.getBytes());
        outputStream.close();
        return file;
    }

    // general account
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
            CsvReader csvReader = new CsvReader(csvConverter.convertToCsv(), ";");
            ChartOfAccount.insertImportedData(connection, csvReader.getContent(),
                    account.getSocietyAccounts()[0].getSocietyID());
            connection.commit();
            connection.close();
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/chart-of-account/general";
    }

    @PostMapping("/create-general-account")
    public String createChartOfAccount(@RequestParam String accountNumber, @RequestParam String entitled,
            HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            Account account = (Account) session.getAttribute("account");
            Society currentSociety = account.getSocietyAccounts()[0].getSociety();

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

    @PostMapping("/update-general-account")
    public String updateChartOfAccount(@RequestParam String accountID, @RequestParam String accountNumber,
            @RequestParam String entitled, HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            ChartOfAccount toUpdate = new ChartOfAccount();
            toUpdate.setAccountNumberID(accountID);
            toUpdate.setAccountNumber(accountNumber);
            toUpdate.setEntitled(entitled);

            toUpdate.update(new AppDBCon());
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/chart-of-account/general";
    }

    @PostMapping("/delete-general-account")
    public String deleteChartOfAccount(@RequestParam String accountID, HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            ChartOfAccount toDelete = new ChartOfAccount();
            toDelete.setAccountNumberID(accountID);
            toDelete.delete(new AppDBCon());
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/chart-of-account/general";
    }

    // third party account
    @PostMapping("/import-third-party-account")
    public String importThirdPartyChartOfAccount(@RequestParam MultipartFile file, HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            DatabaseConnection connection = new AppDBCon().defaultConnection();
            Account account = (Account) session.getAttribute("account");
            File uploadedFile = convertMultipartFileToFile(file);
            CsvConverter csvConverter = new CsvConverter(uploadedFile);
            CsvReader csvReader = new CsvReader(csvConverter.convertToCsv(), ";;");
            ThirdPartyChartOfAccount.insertImportedData(connection, csvReader.getContent(),
                    account.getSocietyAccounts()[0].getSocietyID());
            connection.commit();
            connection.close();
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/chart-of-account/third-party";
    }

    @PostMapping("/create-third-party-account")
    public String createThirdPartyChartOfAccount(@RequestParam String type, @RequestParam String entitled,
            HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            Account account = (Account) session.getAttribute("account");
            Society currentSociety = account.getSocietyAccounts()[0].getSociety();
            ThirdPartyChartOfAccount newThirdPartyChartOfAccount = new ThirdPartyChartOfAccount();
            newThirdPartyChartOfAccount.setSocietyID(currentSociety.getSocietyID());
            newThirdPartyChartOfAccount.setKey(type);
            newThirdPartyChartOfAccount.setValue(entitled);

            newThirdPartyChartOfAccount.create(new AppDBCon());
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/chart-of-account/third-party";
    }

    @PostMapping("/update-third-party-account")
    public String updateThirdPartyChartOfAccount(@RequestParam String accountID, @RequestParam String key,
            @RequestParam String value, HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            ThirdPartyChartOfAccount toUpdate = new ThirdPartyChartOfAccount();
            toUpdate.setThirdPartyChartOfAccountID(accountID);
            toUpdate.setKey(key);
            toUpdate.setValue(value);

            toUpdate.update(new AppDBCon());
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/chart-of-account/third-party";
    }

    @PostMapping("/delete-third-party-account")
    public String deleteThirdPartyChartOfAccount(@RequestParam String accountID, HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            ThirdPartyChartOfAccount toDelete = new ThirdPartyChartOfAccount();
            toDelete.setThirdPartyChartOfAccountID(accountID);
            toDelete.delete(new AppDBCon());
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/chart-of-account/third-party";
    }
}
