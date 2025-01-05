package com.gs.climaxrepoport.parser;

import com.gs.climaxrepoport.models.Client;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelParser implements Parser {

    @Override
    public List<Client> parse(String filePath) throws IOException {
        List<Client> clients = new ArrayList<>();

        try (InputStream file = new FileInputStream(filePath);
             Workbook workbook = filePath.endsWith(".xlsx") ? new XSSFWorkbook(file) : new HSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean isFirstRow = true;

            for (Row row : sheet) {

                if (row.getPhysicalNumberOfCells() == 5) {
                    try {
                        String nom = getStringCellValue(row.getCell(0));
                        String prenom = getStringCellValue(row.getCell(1));
                        int age = (int) getNumericCellValue(row.getCell(2));
                        String profession = getStringCellValue(row.getCell(3));
                        double salaire = getNumericCellValue(row.getCell(4));

                        clients.add(new Client(nom, prenom, age, profession, salaire));

                    } catch (Exception e) {
                        System.err.println("Erreur à la ligne " + (row.getRowNum() + 1) + " : " + e.getMessage());
                    }
                }
            }
        }
        return clients;
    }

    private String getStringCellValue(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        }
        return "";
    }

    private double getNumericCellValue(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        }
        return 0;
    }
}