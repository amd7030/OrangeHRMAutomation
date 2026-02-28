package com.orangehrm.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.print.DocFlavor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.JDBCType.NUMERIC;
import static javax.management.openmbean.SimpleType.STRING;

public class ExcelReaderUtlity {


    public static List<String[]> getsheetData(String filePath, String sheetName) {

        List<String[]> sheetData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }
            //temp added to c
            for (String[] row : sheetData) {
                System.out.println("Columns: " + row.length);
            }
            for (Row row : sheet) {

                int columnCount = row.getLastCellNum();
                String[] rowData = new String[columnCount];

                for (int i = 0; i < columnCount; i++) {
                    Cell cell = row.getCell(i);

                    if (cell == null) {
                        rowData[i] = "";
                    } else {
                        rowData[i] = cell.toString().trim();
                    }
                }

                sheetData.add(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sheetData;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }

    }
}
