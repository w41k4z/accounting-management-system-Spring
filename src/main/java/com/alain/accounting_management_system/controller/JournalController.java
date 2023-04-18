package com.alain.accounting_management_system.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alain.accounting_management_system.connection.AppDBCon;
import com.alain.accounting_management_system.utils.converter.CsvConverter;
import com.alain.accounting_management_system.model.UserAccount;
import com.alain.accounting_management_system.model.Journal;
import com.alain.accounting_management_system.model.JournalCode;
import com.alain.accounting_management_system.utils.reader.CsvReader;

import jakarta.servlet.http.HttpSession;
import orm.database.connection.DatabaseConnection;

@Controller
@RequestMapping("/ela-admin/society/journal")
public class JournalController {

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws Exception {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(multipartFile.getBytes());
        outputStream.close();
        return file;
    }

    @PostMapping("/import-journal")
    public String importJournal(@RequestParam MultipartFile file, HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            DatabaseConnection connection = new AppDBCon().defaultConnection();
            UserAccount account = (UserAccount) session.getAttribute("account");
            File uploadedFile = convertMultipartFileToFile(file);
            CsvConverter csvConverter = new CsvConverter(uploadedFile);
            CsvReader csvReader = new CsvReader(csvConverter.convertToCsv(), ";");
            Journal.insertImportedData(connection, csvReader.getContent(),
                    account.getSocietyAccounts()[0].getSocietyID());
            connection.commit();
            connection.close();
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/journal/transaction-record";
    }

    // journal code
    @PostMapping("/create-journal-code")
    public String createJournalCode(@RequestParam String code, @RequestParam String entitled, HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            JournalCode newJournalCode = new JournalCode();
            newJournalCode.setCode(code);
            newJournalCode.setEntitled(entitled);

            newJournalCode.create(new AppDBCon());
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/journal/journal-code";
    }

    @PostMapping("/update-journal-code")
    public String updateJournalCode(@RequestParam String journalCodeID, @RequestParam String code,
            @RequestParam String entitled,
            HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            JournalCode journalCode = new JournalCode();
            journalCode.setJournalCodeID(journalCodeID);
            journalCode.setCode(code);
            journalCode.setEntitled(entitled);

            journalCode.update(new AppDBCon());
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/journal/journal-code";
    }

    @PostMapping("/delete-journal-code")
    public String deleteJournalCode(@RequestParam String journalCodeID, HttpSession session) {
        if (session.getAttribute("account") == null) {
            return "redirect:/ela-admin";
        }
        try {
            JournalCode journalCode = new JournalCode();
            journalCode.setJournalCodeID(journalCodeID);

            journalCode.delete(new AppDBCon());
        } catch (Exception e) {
            return "redirect:/ela-admin/error?error=" + e.getMessage();
        }
        return "redirect:/ela-admin/society/home-page/journal/journal-code";
    }
}
