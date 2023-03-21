package com.alain.accounting_management_system.converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import fileActivity.Executor;

public class CsvConverter {

    private final List<String> validExtension = Arrays.asList(new String[] { "csv", "xls", "ods" });
    private File fileToConvert;

    // constructor
    public CsvConverter(File file) throws Exception {
        this.setFileToConvert(file);
    }

    // setter
    public void setFileToConvert(File file) throws Exception {
        String fileExtension = Executor.getFileExtension(file);
        if (!validExtension.contains(fileExtension.toLowerCase())) {
            throw new Exception("ERROR: Unsuported file format !\n\nSOURCE: " + fileExtension);
        }
        this.fileToConvert = file;
    }

    // getter
    public File getFileToConvert() {
        return this.fileToConvert;
    }

    // method
    public File convertToCsv() throws Exception {
        if (Executor.getFileExtension(this.getFileToConvert()).toLowerCase().equals("csv")) {
            return this.getFileToConvert();
        }
        File convertedFile = new File("CsvFile.csv");
        Writer writer = new OutputStreamWriter(new FileOutputStream(convertedFile), "UTF-8");

        // Load the workbook
        Workbook workbook = WorkbookFactory.create(this.getFileToConvert());

        // Get the first sheet
        Sheet sheet = workbook.getSheetAt(0);

        List<String[]> rows = new ArrayList<>();
        for (Row row : sheet) {
            List<String> cells = new ArrayList<>();
            for (Cell cell : row) {
                // Convert cell value to string and add to list
                String cellValue = cell.getCellType() == CellType.STRING ? cell.getStringCellValue()
                        : (cell.getNumericCellValue() + "").endsWith(".0") ? (int) cell.getNumericCellValue() + ""
                                : cell.getNumericCellValue() + "";

                cells.add(cellValue);
            }
            String[] rowArray = cells.toArray(new String[cells.size()]);
            rows.add(rowArray);
        }

        // Writing rows to CSV file
        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++) {
                writer.write(row[i]);
                if (i < row.length - 1) {
                    writer.write(";;");
                }
            }
            writer.write("\n");
        }

        writer.close();

        this.getFileToConvert().delete();
        return convertedFile;
    }
}
