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

    private static Object[][] getSheetData(String sheetName) {

        List<String[]> sheetData = ExcelReaderUtlity.getsheetData(DataProviders.FILE_PATH, sheetName);
        Object[][] data = new Object[sheetData.size()][sheetData.get(0).length];
        for (int i = 0; i < sheetData.size(); i++) {
            data[i] = sheetData.get(i);
        }

        return data;
    }

}
