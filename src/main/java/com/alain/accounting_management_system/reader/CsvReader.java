package com.alain.accounting_management_system.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fileActivity.Executor;

public class CsvReader {

    private File csvFile;
    private String separator = ",";

    // constructor
    public CsvReader(File file, String separator) throws Exception {
        this.setCsvFile(file);
    }

    // setters
    public void setCsvFile(File file) throws Exception {
        if (!Executor.getFileExtension(file).toLowerCase().equals("csv")) {
            throw new Exception("ERROR: This is not a csv file !\n\nSource: " + file.getName());
        }
        this.csvFile = file;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    // getters
    public File getCsvFile() {
        return this.csvFile;
    }

    public String getSeparator() {
        return this.separator;
    }

    // method
    public List<List<String>> getContent() throws IOException {
        List<List<String>> content = new ArrayList<>();
        List<String> result = Files.readAllLines(this.getCsvFile().toPath());
        for (String line : result) {
            content.add(Arrays.asList(line.split(this.getSeparator())));
        }
        return content;
    }
}
