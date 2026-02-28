package com.orangehrm.utilities;
import org.testng.annotations.DataProvider;

import java.util.List;

public class DataProviders {
    public static final String FILE_PATH = System.getProperty("user.dir")+"/src/test/resources/testdata/TestData.xlsx";

    @DataProvider
    public static Object[][] validLoginData() {
        return getSheetData("validLoginData");
    }

    @DataProvider
    public static Object[][] inValidLoginData() {
        return getSheetData("inValidLoginData");
    }
    @DataProvider(name = "adminUserData")
    public static Object[][] adminUserData() {
        return getSheetData("adminUserData");
    }

    @DataProvider(name = "AdduserData")
    public static Object[][] AdduserData()
    {
        return getSheetData("AdduserData");
    }
    private static Object[][] getSheetData(String sheetName) {

        List<String[]> sheetData =
                ExcelReaderUtlity.getsheetData(FILE_PATH, sheetName);

        if (sheetData == null || sheetData.size() <= 1) {
            throw new RuntimeException("No data found in sheet: " + sheetName);
        }

        int rowCount = sheetData.size() - 1;  // skip header
        int columnCount = sheetData.get(0).length;


        Object[][] data = new Object[rowCount][columnCount];

        for (int i = 1; i < sheetData.size(); i++) {
            data[i - 1] = sheetData.get(i);
        }

        return data;
    }

}
